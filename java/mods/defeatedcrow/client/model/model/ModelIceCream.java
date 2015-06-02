package mods.defeatedcrow.client.model.model;

import net.minecraft.src.*;
import net.minecraft.util.MathHelper;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelIceCream extends ModelBase {
	// fields
	ModelRenderer dish1;
	ModelRenderer dish2;
	ModelRenderer dish3;
	ModelRenderer dish4;
	ModelRenderer dish5;
	ModelRenderer dish6;
	ModelRenderer dish7;
	ModelRenderer white;
	ModelRenderer pink;
	ModelRenderer orange;
	ModelRenderer yellow;
	ModelRenderer brown;
	ModelRenderer cocoa;
	ModelRenderer green;
	ModelRenderer berry;
	ModelRenderer lime;
	ModelRenderer red;
	ModelRenderer grape;
	ModelRenderer mint;
	ModelRenderer orange2;
	ModelRenderer soda;

	public ModelIceCream() {

		dish1 = new ModelRenderer(this, 0, 0);
		dish1.addBox(-2F, 0F, -2F, 4, 1, 4);
		dish1.setRotationPoint(0F, 23F, 0F);
		dish1.setTextureSize(64, 32);
		dish1.mirror = true;
		setRotation(dish1, 0F, 0F, 0F);
		dish2 = new ModelRenderer(this, 0, 0);
		dish2.addBox(-0.5F, 0F, -0.5F, 1, 3, 1);
		dish2.setRotationPoint(0F, 20F, 0F);
		dish2.setTextureSize(64, 32);
		dish2.mirror = true;
		setRotation(dish2, 0F, 0F, 0F);
		dish3 = new ModelRenderer(this, 0, 0);
		dish3.addBox(-2.5F, 0F, -2.5F, 5, 1, 5);
		dish3.setRotationPoint(0F, 19F, 0F);
		dish3.setTextureSize(64, 32);
		dish3.mirror = true;
		setRotation(dish3, 0F, 0F, 0F);
		dish4 = new ModelRenderer(this, 0, 0);
		dish4.addBox(-3F, -3F, -3F, 6, 3, 1);
		dish4.setRotationPoint(0F, 19F, 0F);
		dish4.setTextureSize(64, 32);
		dish4.mirror = true;
		setRotation(dish4, 0.5235988F, 0F, 0F);
		dish5 = new ModelRenderer(this, 0, 0);
		dish5.addBox(-3F, -3F, 2F, 6, 3, 1);
		dish5.setRotationPoint(0F, 19F, 0F);
		dish5.setTextureSize(64, 32);
		dish5.mirror = true;
		setRotation(dish5, -0.5235988F, 0F, 0F);
		dish6 = new ModelRenderer(this, 0, 0);
		dish6.addBox(-3F, -3F, -3F, 1, 3, 6);
		dish6.setRotationPoint(0F, 19F, 0F);
		dish6.setTextureSize(64, 32);
		dish6.mirror = true;
		setRotation(dish6, 0F, 0F, -0.5235988F);
		dish7 = new ModelRenderer(this, 0, 0);
		dish7.addBox(2F, -3F, -3F, 1, 3, 6);
		dish7.setRotationPoint(0F, 19F, 0F);
		dish7.setTextureSize(64, 32);
		dish7.mirror = true;
		setRotation(dish7, 0F, 0F, 0.5235988F);
		white = new ModelRenderer(this, 0, 9);
		white.addBox(-2F, 0F, -2F, 4, 3, 4);
		white.setRotationPoint(0F, 16F, 0F);
		white.setTextureSize(64, 32);
		white.mirror = true;
		setRotation(white, 0F, 0F, 0F);
		pink = new ModelRenderer(this, 0, 16);
		pink.addBox(-2F, 0F, -2F, 4, 3, 4);
		pink.setRotationPoint(0F, 16F, 0F);
		pink.setTextureSize(64, 32);
		pink.mirror = true;
		setRotation(pink, 0F, 0F, 0F);
		orange = new ModelRenderer(this, 0, 23);
		orange.addBox(-2F, 0F, -2F, 4, 3, 4);
		orange.setRotationPoint(0F, 16F, 0F);
		orange.setTextureSize(64, 32);
		orange.mirror = true;
		setRotation(orange, 0F, 0F, 0F);
		yellow = new ModelRenderer(this, 16, 9);
		yellow.addBox(-2F, 0F, -2F, 4, 3, 4);
		yellow.setRotationPoint(0F, 16F, 0F);
		yellow.setTextureSize(64, 32);
		yellow.mirror = true;
		setRotation(yellow, 0F, 0F, 0F);
		brown = new ModelRenderer(this, 16, 16);
		brown.addBox(-2F, 0F, -2F, 4, 3, 4);
		brown.setRotationPoint(0F, 16F, 0F);
		brown.setTextureSize(64, 32);
		brown.mirror = true;
		setRotation(brown, 0F, 0F, 0F);
		cocoa = new ModelRenderer(this, 16, 23);
		cocoa.addBox(-2F, 0F, -2F, 4, 3, 4);
		cocoa.setRotationPoint(0F, 16F, 0F);
		cocoa.setTextureSize(64, 32);
		cocoa.mirror = true;
		setRotation(cocoa, 0F, 0F, 0F);
		green = new ModelRenderer(this, 32, 9);
		green.addBox(-2F, 0F, -2F, 4, 3, 4);
		green.setRotationPoint(0F, 16F, 0F);
		green.setTextureSize(64, 32);
		green.mirror = true;
		setRotation(green, 0F, 0F, 0F);
		berry = new ModelRenderer(this, 32, 16);
		berry.addBox(-2F, 0F, -2F, 4, 3, 4);
		berry.setRotationPoint(0F, 16F, 0F);
		berry.setTextureSize(64, 32);
		berry.mirror = true;
		setRotation(berry, 0F, 0F, 0F);
		lime = new ModelRenderer(this, 32, 23);
		lime.addBox(-2F, 0F, -2F, 4, 3, 4);
		lime.setRotationPoint(0F, 16F, 0F);
		lime.setTextureSize(64, 32);
		lime.mirror = true;
		setRotation(lime, 0F, 0F, 0F);
		red = new ModelRenderer(this, 48, 9);
		red.addBox(-2F, 0F, -2F, 4, 3, 4);
		red.setRotationPoint(0F, 16F, 0F);
		red.setTextureSize(64, 32);
		red.mirror = true;
		setRotation(red, 0F, 0F, 0F);
		grape = new ModelRenderer(this, 48, 16);
		grape.addBox(-2F, 0F, -2F, 4, 3, 4);
		grape.setRotationPoint(0F, 16F, 0F);
		grape.setTextureSize(64, 32);
		grape.mirror = true;
		setRotation(grape, 0F, 0F, 0F);
		mint = new ModelRenderer(this, 48, 23);
		mint.addBox(-2F, 0F, -2F, 4, 3, 4);
		mint.setRotationPoint(0F, 16F, 0F);
		mint.setTextureSize(64, 32);
		mint.mirror = true;
		setRotation(mint, 0F, 0F, 0F);
		orange2 = new ModelRenderer(this, 32, 2);
		orange2.addBox(-2F, 0F, -2F, 4, 3, 4);
		orange2.setRotationPoint(0F, 16F, 0F);
		orange2.setTextureSize(64, 32);
		orange2.mirror = true;
		setRotation(orange2, 0F, 0F, 0F);
		soda = new ModelRenderer(this, 48, 2);
		soda.addBox(-2F, 0F, -2F, 4, 3, 4);
		soda.setRotationPoint(0F, 16F, 0F);
		soda.setTextureSize(64, 32);
		soda.mirror = true;
		setRotation(soda, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, byte b0) {
		super.render(entity, f, f1, f2, f3, f4, f5);

		if (b0 == 0)
			white.render(f5);
		else if (b0 == 1)
			orange.render(f5);
		else if (b0 == 2)
			green.render(f5);
		else if (b0 == 3)
			cocoa.render(f5);
		else if (b0 == 4)
			brown.render(f5);
		else if (b0 == 5)
			pink.render(f5);
		else if (b0 == 6)
			yellow.render(f5);
		else if (b0 == 7)
			lime.render(f5);
		else if (b0 == 8)
			red.render(f5);
		else if (b0 == 9)
			berry.render(f5);
		else if (b0 == 10)
			grape.render(f5);
		else if (b0 == 11)
			mint.render(f5);
		else if (b0 == 12)
			orange2.render(f5);
		else if (b0 == 13)
			soda.render(f5);
	}

	public void renderClear(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, byte b0) {
		super.render(entity, f, f1, f2, f3, f4, f5);

		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		dish1.render(f5);
		dish2.render(f5);
		dish3.render(f5);
		dish4.render(f5);
		dish5.render(f5);
		dish6.render(f5);
		dish7.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}
