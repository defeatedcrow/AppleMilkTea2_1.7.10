package mods.defeatedcrow.client.model.model;

import mods.defeatedcrow.common.block.edible.BlockCocktailSP.DecorationType;
import mods.defeatedcrow.common.block.edible.BlockCocktailSP.ModelType;
import net.minecraft.src.*;
import net.minecraft.util.MathHelper;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCocktail extends ModelBase {
	ModelRenderer lemon = new ModelRenderer(this, 32, 0).setTextureSize(64, 32);
	ModelRenderer lime = new ModelRenderer(this, 32, 5).setTextureSize(64, 32);
	ModelRenderer pine = new ModelRenderer(this, 32, 10).setTextureSize(64, 32);
	ModelRenderer apple = new ModelRenderer(this, 32, 15).setTextureSize(64, 32);
	ModelRenderer bubble = new ModelRenderer(this, 32, 21).setTextureSize(64, 32);

	ModelRenderer bottom = new ModelRenderer(this, 0, 0).setTextureSize(64, 32);
	ModelRenderer Aleg = new ModelRenderer(this, 0, 0).setTextureSize(64, 32);
	ModelRenderer Aleg2 = new ModelRenderer(this, 0, 0).setTextureSize(64, 32);
	ModelRenderer Aside1 = new ModelRenderer(this, 0, 0).setTextureSize(64, 32);
	ModelRenderer Aside2 = new ModelRenderer(this, 0, 0).setTextureSize(64, 32);
	ModelRenderer Aside3 = new ModelRenderer(this, 0, 0).setTextureSize(64, 32);
	ModelRenderer Aside4 = new ModelRenderer(this, 0, 0).setTextureSize(64, 32);
	ModelRenderer Bside1 = new ModelRenderer(this, 0, 0).setTextureSize(64, 32);
	ModelRenderer Bside2 = new ModelRenderer(this, 0, 0).setTextureSize(64, 32);
	ModelRenderer Bside3 = new ModelRenderer(this, 0, 0).setTextureSize(64, 32);
	ModelRenderer Bside4 = new ModelRenderer(this, 0, 0).setTextureSize(64, 32);
	ModelRenderer Cside1 = new ModelRenderer(this, 0, 0).setTextureSize(64, 32);
	ModelRenderer Cside2 = new ModelRenderer(this, 0, 0).setTextureSize(64, 32);
	ModelRenderer Cside3 = new ModelRenderer(this, 0, 0).setTextureSize(64, 32);
	ModelRenderer Cside4 = new ModelRenderer(this, 0, 0).setTextureSize(64, 32);

	ModelRenderer icecube = new ModelRenderer(this, 0, 16).setTextureSize(64, 32);
	ModelRenderer icecube2 = new ModelRenderer(this, 0, 17).setTextureSize(64, 32);

	ModelRenderer inner1 = new ModelRenderer(this, 0, 0).setTextureSize(64, 32);
	ModelRenderer inner2 = new ModelRenderer(this, 0, 0).setTextureSize(64, 32);
	ModelRenderer inner3 = new ModelRenderer(this, 0, 0).setTextureSize(64, 32);

	public ModelCocktail() {
		lemon.addBox(2F, -1F, 0F, 5, 4, 1);
		lemon.setRotationPoint(0F, 14F, 0F);
		lemon.mirror = true;
		setRotation(lemon, 0F, 0F, -0.2094395F);
		lime.addBox(2F, 0F, 0F, 5, 4, 1);
		lime.setRotationPoint(0F, 14F, 0F);
		lime.mirror = true;
		setRotation(lime, 0F, 0F, -0.2094395F);
		pine.addBox(2F, 0F, 0F, 5, 4, 1);
		pine.setRotationPoint(0F, 13F, 0F);
		pine.mirror = true;
		setRotation(pine, 0F, 0F, -0.2094395F);
		apple.addBox(2F, 0F, 0F, 5, 4, 1);
		apple.setRotationPoint(0F, 13F, 0F);
		apple.mirror = true;
		setRotation(apple, 0F, 0F, -0.2094395F);

		bottom.addBox(-3F, 0F, -3F, 6, 1, 6);
		bottom.setRotationPoint(0F, 23F, 0F);
		bottom.mirror = true;
		setRotation(bottom, 0F, 0F, 0F);
		Aleg.addBox(-0.5F, 0F, -0.5F, 1, 4, 1);
		Aleg.setRotationPoint(0F, 19F, 0F);
		Aleg.mirror = true;
		setRotation(Aleg, 0F, 0F, 0F);
		Aleg2.addBox(-3F, 0F, -3F, 6, 2, 6);
		Aleg2.setRotationPoint(0F, 18F, 0F);
		Aleg2.mirror = true;
		setRotation(Aleg2, 0F, 0F, 0F);
		Aside1.addBox(-4F, 0F, -4F, 8, 3, 1);
		Aside1.setRotationPoint(0F, 16F, 0F);
		Aside1.mirror = true;
		setRotation(Aside1, 0F, 0F, 0F);
		Aside2.addBox(-4F, 0F, 3F, 8, 3, 1);
		Aside2.setRotationPoint(0F, 16F, 0F);
		Aside2.mirror = true;
		setRotation(Aside2, 0F, 0F, 0F);
		Aside3.addBox(3F, 0F, -3F, 1, 3, 6);
		Aside3.setRotationPoint(0F, 16F, 0F);
		Aside3.mirror = true;
		setRotation(Aside3, 0F, 0F, 0F);
		Aside4.addBox(-4F, 0F, -3F, 1, 3, 6);
		Aside4.setRotationPoint(0F, 16F, 0F);
		Aside4.mirror = true;
		setRotation(Aside4, 0F, 0F, 0F);
		Bside1.addBox(-4F, 0F, -4F, 8, 9, 1);
		Bside1.setRotationPoint(0F, 14F, 0F);
		Bside1.mirror = true;
		setRotation(Bside1, 0F, 0F, 0F);
		Bside2.addBox(-4F, -1F, 3F, 8, 9, 1);
		Bside2.setRotationPoint(0F, 15F, 0F);
		Bside2.mirror = true;
		setRotation(Bside2, 0F, 0F, 0F);
		Bside3.addBox(-4F, 0F, -3F, 1, 9, 6);
		Bside3.setRotationPoint(0F, 14F, 0F);
		Bside3.mirror = true;
		setRotation(Bside3, 0F, 0F, 0F);
		Bside4.addBox(3F, 0F, -3F, 1, 9, 6);
		Bside4.setRotationPoint(0F, 14F, 0F);
		Bside4.mirror = true;
		setRotation(Bside4, 0F, 0F, 0F);
		Cside1.addBox(-4F, 0F, -4F, 8, 6, 1);
		Cside1.setRotationPoint(0F, 13F, 0F);
		Cside1.mirror = true;
		setRotation(Cside1, 0F, 0F, 0F);
		Cside2.addBox(-4F, 0F, 3F, 8, 6, 1);
		Cside2.setRotationPoint(0F, 13F, 0F);
		Cside2.mirror = true;
		setRotation(Cside2, 0F, 0F, 0F);
		Cside3.addBox(3F, 0F, -3F, 1, 6, 6);
		Cside3.setRotationPoint(0F, 13F, 0F);
		Cside3.mirror = true;
		setRotation(Cside3, 0F, 0F, 0F);
		Cside4.addBox(-4F, 0F, -3F, 1, 6, 6);
		Cside4.setRotationPoint(0F, 13F, 0F);
		Cside4.mirror = true;
		setRotation(Cside4, 0F, 0F, 0F);

		icecube.addBox(-1F, 0F, -1F, 3, 3, 3);
		icecube.setRotationPoint(0F, 20F, 0F);
		icecube.mirror = true;
		setRotation(icecube, 0.4560576F, 0F, -0.1319841F);
		icecube2.addBox(-2F, 0F, -2F, 3, 3, 3);
		icecube2.setRotationPoint(0F, 18F, 0F);
		icecube2.mirror = true;
		setRotation(icecube2, -0.1745329F, 0.2648976F, -0.8922867F);

		inner1.addBox(-3F, 0F, -3F, 6, 7, 6);
		inner1.setRotationPoint(0F, 16F, 0F);
		inner1.mirror = true;
		setRotation(inner1, 0F, 0F, 0F);

		inner2.addBox(-3F, 0F, -3F, 6, 2, 6);
		inner2.setRotationPoint(0F, 16.5F, 0F);
		inner2.mirror = true;
		setRotation(inner2, 0F, 0F, 0F);

		inner3.addBox(-2F, 0F, -2F, 4, 1, 4);
		inner3.setRotationPoint(0F, 15.5F, 0F);

		inner3.mirror = true;
		setRotation(inner3, 0F, 0F, 0F);

		bubble.addBox(-3F, 0F, -3F, 6, 1, 6);
		bubble.setRotationPoint(0F, 15.5F, 0F);
		bubble.mirror = true;
		setRotation(bubble, 0F, 0F, 0F);
	}

	public void renderInner(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, byte b0) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		byte b = (byte) (b0 & 4);

		if (b0 == 0) {
			inner1.render(f5);
		} else {
			inner2.render(f5);
			if (b == 4)
				inner3.render(f5);
		}
	}

	public void renderInnerSP(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, ModelType type) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		if (type == ModelType.LONG) {
			inner1.render(f5);
		} else {
			inner2.render(f5);
		}
	}

	public void renderDeco(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, byte b0) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		if (b0 == 1)
			lime.render(f5);
		else if (b0 == 2)
			lemon.render(f5);
		else if (b0 == 3)
			pine.render(f5);
		else if (b0 == 4)
			apple.render(f5);
		else if (b0 == 5)
			bubble.render(f5);
	}

	public void renderDecoSP(Entity entity, float f, float f1, float f2, float f3, float f4, float f5,
			DecorationType type) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		if (type == DecorationType.LIME)
			lime.render(f5);
		else if (type == DecorationType.LEMON)
			lemon.render(f5);
		else if (type == DecorationType.PINE)
			pine.render(f5);
		else if (type == DecorationType.APPLE)
			apple.render(f5);
	}

	public void renderGlass(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, byte b0) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		byte b = (byte) (b0 & 3);

		bottom.render(f5);
		if (b == 0) {
			Bside1.render(f5);
			Bside2.render(f5);
			Bside3.render(f5);
			Bside4.render(f5);
		} else if (b == 2) {
			Aleg.render(f5);
			Aleg2.render(f5);

			Cside1.render(f5);
			Cside2.render(f5);
			Cside3.render(f5);
			Cside4.render(f5);
		} else {
			Aside1.render(f5);
			Aside2.render(f5);
			Aside3.render(f5);
			Aside4.render(f5);
			Aleg.render(f5);
			Aleg2.render(f5);
		}
	}

	public void renderGlassSP(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, ModelType type) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		bottom.render(f5);
		if (type == ModelType.LONG) {
			Bside1.render(f5);
			Bside2.render(f5);
			Bside3.render(f5);
			Bside4.render(f5);
		} else if (type == ModelType.WINE) {
			Aleg.render(f5);
			Aleg2.render(f5);

			Cside1.render(f5);
			Cside2.render(f5);
			Cside3.render(f5);
			Cside4.render(f5);
		} else if (type == ModelType.SHORT) {
			Aside1.render(f5);
			Aside2.render(f5);
			Aside3.render(f5);
			Aside4.render(f5);
			Aleg.render(f5);
			Aleg2.render(f5);
		}
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.inner1.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.inner2.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.inner3.rotateAngleY = f3 / (180F / (float) Math.PI);

		this.bottom.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.Aleg.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.Aleg2.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.Bside1.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.Bside2.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.Bside3.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.Bside4.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.Cside1.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.Cside2.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.Cside3.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.Cside4.rotateAngleY = f3 / (180F / (float) Math.PI);

		this.apple.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.pine.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.lemon.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.lime.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.bubble.rotateAngleY = f3 / (180F / (float) Math.PI);
	}

}
