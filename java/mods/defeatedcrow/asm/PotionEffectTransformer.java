package mods.defeatedcrow.asm;

import net.minecraft.launchwrapper.IClassTransformer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import cpw.mods.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;

/**
 * Original code was created by A.K. on 14/07/07.
 */
public class PotionEffectTransformer implements IClassTransformer, Opcodes {

	private static final String TARGET_CLASS_NAME = "net.minecraft.potion.PotionEffect";

	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass) {
		if (!AppleMilkCorePlugin.allowLoad || AppleMilkCorePlugin.forcedDisable) {
			return basicClass;
		}
		if (!transformedName.equals(TARGET_CLASS_NAME)) {
			return basicClass;
		}
		try {
			AppleMilkCorePlugin.logger.info("Start transforming PotionEffect Class");
			ClassReader classReader = new ClassReader(basicClass);
			ClassWriter classWriter = new ClassWriter(1);
			classReader.accept(new CustomVisitor(name, classWriter), 8);
			AppleMilkCorePlugin.logger.info("Finish transforming PotionEffect Class");
			return classWriter.toByteArray();
		} catch (Exception e) {
			throw new RuntimeException("failed : PotionEffectTransformer loading", e);
		}
	}

	/*
	 * Custom ClassVisitor
	 * visitMethodでメソッドを一から書き直すことが出来る。
	 */
	class CustomVisitor extends ClassVisitor {
		// 難読化後のクラス名。FMLDeobfuscatingRemapper.INSTANCE.mapMethodNameを使う際に使用。
		String owner;

		public CustomVisitor(String owner, ClassVisitor cv) {
			super(Opcodes.ASM4, cv);
			this.owner = owner;
		}

		static final String TARGET_METHOD_NAME1 = "func_76455_a";// onUpdate
		static final String TARGET_METHOD_NAME_DEBUG1 = "onUpdate";
		static final String TARGET_METHOD_DESC1 = "(Lnet/minecraft/entity/EntityLivingBase;)Z";// method description

		static final String TARGET_METHOD_NAME2 = "func_82722_b";// readCustomPotionEffectFromNBT
		static final String TARGET_METHOD_NAME_DEBUG2 = "readCustomPotionEffectFromNBT";
		static final String TARGET_METHOD_DESC2 = "(Lnet/minecraft/nbt/NBTTagCompound;)Lnet/minecraft/potion/PotionEffect;";// method
																															// description

		static final String TARGET_METHOD_NAME3 = "func_76456_a";// getPotionID
		static final String TARGET_METHOD_NAME_DEBUG3 = "getPotionID";
		static final String TARGET_METHOD_DESC3 = "()I";// method description

		@Override
		public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
			// onUpdateメソッドの書き換え
			if (TARGET_METHOD_NAME1.equals(FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(owner, name, desc))
					&& TARGET_METHOD_DESC1.equals(FMLDeobfuscatingRemapper.INSTANCE.mapMethodDesc(desc))) {
				AppleMilkCorePlugin.logger.info("Transforming onUpdate method");
				return new CustomMethodVisitor1(this.api, super.visitMethod(access, name, desc, signature, exceptions));
			}
			// readCustomPotionEffectFromNBTメソッドの書き換え
			if (TARGET_METHOD_NAME2.equals(FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(owner, name, desc))
					&& TARGET_METHOD_DESC2.equals(FMLDeobfuscatingRemapper.INSTANCE.mapMethodDesc(desc))) {
				AppleMilkCorePlugin.logger.info("Transforming readCustomPotionEffectFromNBT method");
				return new CustomMethodVisitor2(this.api, super.visitMethod(access, name, desc, signature, exceptions));
			}
			// getPotionIDメソッドの書き換え
			if (TARGET_METHOD_NAME3.equals(FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(owner, name, desc))
					&& TARGET_METHOD_DESC3.equals(FMLDeobfuscatingRemapper.INSTANCE.mapMethodDesc(desc))) {
				AppleMilkCorePlugin.logger.info("Transforming getPotionID method");
				return new CustomMethodVisitor3(this.api, super.visitMethod(access, name, desc, signature, exceptions));
			}
			return super.visitMethod(access, name, desc, signature, exceptions);
		}
	}

	/*
	 * Custom MethodVisitor
	 * visit**Methodで、InsnNodeの入れ替えや、追加等出来る。
	 */
	class CustomMethodVisitor1 extends MethodVisitor {
		public CustomMethodVisitor1(int api, MethodVisitor mv) {
			super(api, mv);
		}

		// visitFieldInsnメソッドの1回めの呼び出しで処理するためのフラグ
		boolean check = false;

		@Override
		public void visitFieldInsn(int opcode, String owner, String name, String desc) {
			// 判定。ここは実は、checkだけ見ればよい。
			if (opcode == GETFIELD && desc.equals("I") && !check) {
				check = true;
				AppleMilkCorePlugin.logger.info("onUpdate:change id in [0 - 255]");
				// これは、PUTFIELDとのペア
				super.visitVarInsn(ALOAD, 0);
				// この２つはペア
				super.visitVarInsn(ALOAD, 0);
				super.visitFieldInsn(GETFIELD, "net/minecraft/potion/PotionEffect", "field_76462_a", "I");// potionID
				// ここで、スタックに数字が１つスタックされる。
				// 256をスタック
				super.visitIntInsn(SIPUSH, 256);
				// スタックされた２つの数字を加算する
				super.visitInsn(IADD);
				// 加算された数字がスタックされる。
				// もう一度256をスタック
				super.visitIntInsn(SIPUSH, 256);
				// スタックされている２つの数字のうち、あとの数字で最初の数字の剰余をとる
				super.visitInsn(IREM);
				// 剰余がスタックされる。
				// スタックされた数字をフィールドに代入する
				super.visitFieldInsn(PUTFIELD, "net/minecraft/potion/PotionEffect", "field_76462_a", "I");// potionID
			}
			super.visitFieldInsn(opcode, owner, name, desc);
		}
	}

	class CustomMethodVisitor2 extends MethodVisitor {
		public CustomMethodVisitor2(int api, MethodVisitor mv) {
			super(api, mv);
		}

		// visitMethodInsnが複数あるため、直前のfieldNameを保存する。
		String fieldName = "";

		@Override
		public void visitLdcInsn(Object cst) {
			if (cst.equals("Id")) {
				fieldName = (String) cst;
			}
			super.visitLdcInsn(cst);
		}

		// 識別用description
		static final String TARGET_DESC = "(Ljava/lang/String;)B";

		@Override
		public void visitMethodInsn(int opcode, String owner, String name, String desc) {
			super.visitMethodInsn(opcode, owner, name, desc);
			// 処理を割りこませる部分を判定
			if (opcode == INVOKEVIRTUAL && TARGET_DESC.equals(desc) && fieldName.equals("Id")) {
				AppleMilkCorePlugin.logger.info("readCustomPotionEffectFromNBT:change id in [0 - 255]");
				// 256をスタック
				super.visitIntInsn(SIPUSH, 256);
				// スタックされた２つの数字を加算する
				super.visitInsn(IADD);
				// 加算された数字がスタックされる。
				// もう一度256をスタック
				super.visitIntInsn(SIPUSH, 256);
				// スタックされている２つの数字のうち、あとの数字で最初の数字の剰余をとる
				super.visitInsn(IREM);
				// 剰余がスタックされる。
				// 代入してないが、このメソッドが呼ばれたあと、代入処理が呼ばれている。PotionEffectクラスのバイトコードを参照のこと。
			}
		}
	}

	class CustomMethodVisitor3 extends MethodVisitor {
		public CustomMethodVisitor3(int api, MethodVisitor mv) {
			super(api, mv);
		}

		@Override
		public void visitFieldInsn(int opcode, String owner, String name, String desc) {
			// 1回しか呼ばれないので、判定なしで、処理を挟み込む。
			AppleMilkCorePlugin.logger.info("getPotionID:change id in [0 - 255]");
			super.visitFieldInsn(opcode, owner, name, desc);
			super.visitIntInsn(SIPUSH, 256);
			super.visitInsn(IADD);
			super.visitIntInsn(SIPUSH, 256);
			super.visitInsn(IREM);
			// 代入してないが、このメソッドが呼ばれたあと、代入処理が呼ばれている。PotionEffectクラスのバイトコードを参照のこと。
		}
	}
}
