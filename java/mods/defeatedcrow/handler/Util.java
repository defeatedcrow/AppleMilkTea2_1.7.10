package mods.defeatedcrow.handler;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.config.DCsConfig;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;

public class Util {

	private Util() {
	}

	// コンフィグで規定範囲外の数値を入れた時に、安全に動かすためのメソッドその1
	public static int getCupRender() {
		int l = DCsConfig.setCupTexture;
		if (l < 0)
			l = 1;
		else if (l > 3)
			l = 3;

		return l;
	}

	// その2
	public static int getTeppannReadyTime() {
		int l = DCsConfig.teppannReadyTime;
		if (l < 0)
			l = 1;
		else if (l > 60)
			l = 60;

		return l;
	}

	// その3
	public static int getCupStacksize() {
		int l = DCsConfig.cupStackSize;
		if (l <= 1)
			l = 1;
		else {
			if (l <= 3)
				l = 3;
			else {
				l = 8;
			}
		}
		return l;
	}

	public static int getHamaguriChanceValue() {
		int l = DCsConfig.clamChanceValue;
		if (l < 0)
			l = 1;
		else if (l > 100)
			l = 100;

		return l;
	}

	public static int getPrincessChanceValue() {
		int l = DCsConfig.princessChanceValue;
		if (l < 0)
			l = 1;
		else if (l > 100)
			l = 100;

		return l;
	}

	// コンフィグで規定範囲外の数値を入れた時に、安全に動かすためのメソッドその4
	// 各Block、Itemクラス側からコンフィグ内容を確認するための中継地点
	// altテクスチャが用意されていない物は下のメソッドを使う
	public static String getTexturePass() {
		int l = DCsConfig.setAltTexturePass - 1;
		if (l < 0)
			l = 0;
		else if (l > 2)
			l = 2;

		return DCsAppleMilk.TEX_PASS[l];
	}

	public static String getTexturePassNoAlt() {
		int l = DCsConfig.setAltTexturePass - 1;
		if (l < 0)
			l = 0;
		else if (l > 1)
			l = 1;

		return DCsAppleMilk.TEX_PASS[l];
	}

	// entityはテクスチャのパスの記述方法が違うので別途作成。
	public static String getEntityTexturePass() {
		int l = DCsConfig.setAltTexturePass - 1;
		if (l < 0)
			l = 0;
		else if (l > 2)
			l = 2;

		return DCsAppleMilk.TEX_PASS_ENTITY[l];
	}

	public static String getEntityTexturePassNoAlt() {
		int l = DCsConfig.setAltTexturePass - 1;
		if (l < 0)
			l = 0;
		else if (l > 1)
			l = 1;

		return DCsAppleMilk.TEX_PASS_ENTITY[l];
	}

	public static String getEntityTexturePassAlt() {
		return DCsAppleMilk.TEX_PASS_ALT[0];
	}

	public static float getCupScale() {
		float f = (float) DCsConfig.setCupScale;
		if (f < 0.01F)
			f = 0.01F;
		else if (f > 10.0F)
			f = 10.0F;

		return f;
	}

	public static float getCupSize() {
		float f = (float) DCsConfig.setCupScale;
		if (f < 0.5F)
			f = 0.5F;
		else if (f > 2.0F)
			f = 2.0F;

		return 0.3F * f;
	}

	// 0,0=south, 1,90=west, 2,180=north, 3,-90=east
	// 相変わらず方角を覚えられないため自分用に作ったメソッド
	public static final int[] METAX = new int[] {
			0,
			-1,
			0,
			1 };

	public static final int[] METAZ = new int[] {
			-1,
			0,
			1,
			0 };

	public static final int[] RAD = new int[] {
			0,
			-90,
			180,
			90 };

	// FMLの機能を利用した他MOD様のアイテム取得メソッド。
	// protectedにする意味は正直あんまりない。
	// そもそもこのメソッドの存在意義がない
	public static Item getModItem(String modId, String name) {
		Item ret = GameRegistry.findItem(modId, name);
		if (ret != null) {
			return ret;
		} else {
			return null;
		}
	}

	public static Block getModBlock(String modId, String name) {
		Block ret = GameRegistry.findBlock(modId, name);
		if (ret != null) {
			return ret;
		} else {
			return null;
		}
	}

	// 現在地のバイオームを確認。
	// 複数のクラスで利用するので、ここにまとめた。
	public static BiomeGenBase checkCurrentBiome(World world, EntityPlayer player) {
		BiomeGenBase biome = BiomeGenBase.plains;

		int x = MathHelper.floor_double(player.posX);
		int y = MathHelper.floor_double(player.posY);
		int z = MathHelper.floor_double(player.posZ);

		biome = world.getBiomeGenForCoords(x, z);

		return biome;
	}

	// 新規追加ポーションを発生させる場合、ここのメソッドを中継する。（追加失敗対策）
	public static boolean addPotionEffectDC(EntityLivingBase living, PotionEffect effect) {
		boolean flag = DCsAppleMilk.succeedAddPotion;
		if (flag) {
			living.addPotionEffect(effect);
		} else {
			living.addPotionEffect(new PotionEffect(Potion.regeneration.id, 300, 0));
		}
		return flag;
	}

	// nullチェック用
	public static boolean notEmptyItem(ItemStack item) {
		return item != null && item.stackSize != 0 && item.getItem() != null;
	}

	// OreNameから一つだけアイテムを得る
	public static ItemStack getOreStack(String ore) {
		if (OreDictionary.doesOreNameExist(ore) && !OreDictionary.getOres(ore).isEmpty())
			return new ItemStack(OreDictionary.getOres(ore).get(0).getItem(), 9, OreDictionary.getOres(ore).get(0)
					.getItemDamage());
		else
			return null;
	}

	// デバッグモード
	public static boolean checkDebugModePass(String pass) {
		byte[] b = null;
		String get = "";
		MessageDigest md5;

		try {
			md5 = MessageDigest.getInstance("MD5");
			md5.update(pass.getBytes());
			b = md5.digest();
		} catch (NoSuchAlgorithmException e) {
			AMTLogger.logger.warn("Failed to check password...", e);
		}

		get = getStringFromBytes(b);
		AMTLogger.debugInfo("Get String : " + get);

		if (!get.isEmpty()) {
			boolean match = get.matches("7805f2fa0adc68cd9a8f7cb2135e0b57");
			AMTLogger.info("DebugMode : " + match);
			return match;
		}

		return true;
	}

	private static String getStringFromBytes(byte[] b) {

		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < b.length; i++) {

			if ((b[i] & 0xff) < 0x10) {
				builder.append("0");
			}
			builder.append(Integer.toHexString(0xff & b[i]));
		}

		return builder.toString();
	}

}
