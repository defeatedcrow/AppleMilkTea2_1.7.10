package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelSteak extends ModelBase {
	// fields
	ModelRenderer pork = (new ModelRenderer(this, 0, 8)).setTextureSize(32, 32);
	ModelRenderer carrot1 = (new ModelRenderer(this, 0, 18)).setTextureSize(32, 32);
	ModelRenderer carrot2 = (new ModelRenderer(this, 0, 18)).setTextureSize(32, 32);
	ModelRenderer carrot3 = (new ModelRenderer(this, 0, 18)).setTextureSize(32, 32);
	ModelRenderer potato1 = (new ModelRenderer(this, 0, 23)).setTextureSize(32, 32);
	ModelRenderer potato2 = (new ModelRenderer(this, 0, 23)).setTextureSize(32, 32);
	ModelRenderer beef = (new ModelRenderer(this, 0, 0)).setTextureSize(32, 32);
	ModelRenderer butter = (new ModelRenderer(this, 9, 18)).setTextureSize(32, 32);

	ModelRenderer body1 = (new ModelRenderer(this, 0, 0)).setTextureSize(32, 32);
	ModelRenderer body2 = (new ModelRenderer(this, 0, 3)).setTextureSize(32, 32);
	ModelRenderer leg1 = (new ModelRenderer(this, 0, 16)).setTextureSize(32, 32);
	ModelRenderer leg2 = (new ModelRenderer(this, 0, 16)).setTextureSize(32, 32);
	ModelRenderer leg3 = (new ModelRenderer(this, 3, 18)).setTextureSize(32, 32);
	ModelRenderer leg4 = (new ModelRenderer(this, 3, 18)).setTextureSize(32, 32);
	ModelRenderer leg5 = (new ModelRenderer(this, 18, 16)).setTextureSize(32, 32);
	ModelRenderer leg6 = (new ModelRenderer(this, 18, 16)).setTextureSize(32, 32);
	ModelRenderer leg7 = (new ModelRenderer(this, 18, 24)).setTextureSize(32, 32);
	ModelRenderer leg8 = (new ModelRenderer(this, 18, 24)).setTextureSize(32, 32);

	ModelRenderer bottom = (new ModelRenderer(this, 0, 16)).setTextureSize(32, 32);
	ModelRenderer bottom2 = (new ModelRenderer(this, 0, 0)).setTextureSize(32, 32);
	ModelRenderer top1 = (new ModelRenderer(this, 14, 8)).setTextureSize(32, 32);
	ModelRenderer top2 = (new ModelRenderer(this, 0, 8)).setTextureSize(32, 32);
	ModelRenderer Shape1 = (new ModelRenderer(this, 0, 22)).setTextureSize(32, 32);

	ModelRenderer wood = (new ModelRenderer(this, 0, 0)).setTextureSize(64, 32);
	ModelRenderer plate = (new ModelRenderer(this, 0, 17)).setTextureSize(64, 32);

	public ModelSteak() {

		pork.addBox(-4F, 0F, -5F, 8, 3, 5);
		pork.setRotationPoint(0F, 18F, 0F);
		pork.mirror = true;
		setRotation(pork, 0F, 0F, 0F);
		carrot1.addBox(5F, 0F, 0F, 1, 1, 3);
		carrot1.setRotationPoint(0F, 20F, 0F);
		carrot1.mirror = true;
		setRotation(carrot1, 0F, -0.4363323F, 0F);
		carrot2.addBox(4F, 0F, 1F, 1, 1, 3);
		carrot2.setRotationPoint(0F, 20F, 0F);
		carrot2.mirror = true;
		setRotation(carrot2, 0F, -0.4363323F, 0F);
		carrot3.addBox(-1F, 0F, 3F, 1, 1, 3);
		carrot3.setRotationPoint(0F, 21F, 0F);
		carrot3.mirror = true;
		setRotation(carrot3, 0.4363323F, 0.7853982F, 0F);
		potato1.addBox(-5F, 0F, 2F, 2, 2, 2);
		potato1.setRotationPoint(0F, 19F, 0F);
		potato1.mirror = true;
		setRotation(potato1, 0F, 0F, 0F);
		potato2.addBox(-3F, 0F, 3F, 2, 2, 2);
		potato2.setRotationPoint(0F, 19F, 0F);
		potato2.mirror = true;
		setRotation(potato2, 0F, 0F, 0F);
		beef.addBox(-4F, 0F, -5F, 8, 3, 5);
		beef.setRotationPoint(0F, 18F, 0F);
		beef.mirror = true;
		setRotation(beef, 0F, 0F, 0F);
		butter.addBox(-0.5F, 0F, -3F, 1, 1, 1);
		butter.setRotationPoint(0F, 17F, 0F);
		butter.mirror = true;
		setRotation(butter, 0F, 0F, 0F);

		body1.addBox(-3F, 0F, -6F, 6, 5, 10);
		body1.setRotationPoint(0F, 17F, 0F);
		body1.mirror = true;
		setRotation(body1, 0F, 0F, 0F);
		body2.addBox(-4F, 0F, -5F, 8, 4, 8);
		body2.setRotationPoint(0F, 18F, 0F);
		body2.mirror = true;
		setRotation(body2, 0F, 0F, 0F);
		leg1.addBox(3F, 0F, -2F, 2, 5, 7);
		leg1.setRotationPoint(0F, 16F, 0F);
		leg1.mirror = true;
		setRotation(leg1, 0F, 0F, 0F);
		leg2.addBox(-5F, 0F, -2F, 2, 5, 7);
		leg2.setRotationPoint(0F, 16F, 0F);
		leg2.mirror = true;
		setRotation(leg2, 0F, 0F, 0F);
		leg3.addBox(5F, 0F, -1F, 1, 4, 5);
		leg3.setRotationPoint(0F, 16.5F, 0F);
		leg3.mirror = true;
		setRotation(leg3, 0F, 0F, 0F);
		leg4.addBox(-6F, 0F, -1F, 1, 4, 5);
		leg4.setRotationPoint(0F, 16.5F, 0F);
		leg4.mirror = true;
		setRotation(leg4, 0F, 0F, 0F);
		leg5.addBox(3F, -1F, 2F, 2, 2, 4);
		leg5.setRotationPoint(0F, 18F, 0F);
		leg5.mirror = true;
		setRotation(leg5, 0.3141593F, 0F, 0F);
		leg6.addBox(-5F, -1F, 1F, 2, 2, 4);
		leg6.setRotationPoint(0F, 18F, 0F);
		leg6.mirror = true;
		setRotation(leg6, 0.3141593F, 0F, 0F);
		leg7.addBox(3F, 0F, 4F, 2, 1, 4);
		leg7.setRotationPoint(0F, 17F, 0F);
		leg7.mirror = true;
		setRotation(leg7, 0.3141593F, 0F, 0F);
		leg8.addBox(-5F, 0F, 4F, 2, 1, 4);
		leg8.setRotationPoint(0F, 17F, 0F);
		leg8.mirror = true;
		setRotation(leg8, 0.3141593F, 0F, 0F);

		bottom.addBox(-2F, 0F, -2F, 4, 1, 4);
		bottom.setRotationPoint(0F, 21F, 0F);
		bottom.mirror = true;
		setRotation(bottom, 0F, 0F, 0F);
		bottom2.addBox(-3F, 0F, -3F, 6, 1, 6);
		bottom2.setRotationPoint(0F, 20F, 0F);
		bottom2.mirror = true;
		setRotation(bottom2, 0F, 0F, 0F);
		top1.addBox(-2F, 0F, 4F, 4, 4, 1);
		top1.setRotationPoint(0F, 14F, 0F);
		top1.mirror = true;
		setRotation(top1, 0F, 0F, 0F);
		top2.addBox(-3F, 0F, 3F, 6, 6, 1);
		top2.setRotationPoint(0F, 14F, 0F);
		top2.mirror = true;
		setRotation(top2, 0F, 0F, 0F);
		Shape1.addBox(-2F, 0F, -2F, 4, 2, 4);
		Shape1.setRotationPoint(0F, 18F, 0F);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);

		wood.addBox(-8F, 0F, -8F, 16, 1, 16);
		wood.setRotationPoint(0F, 23F, 0F);
		wood.setTextureSize(64, 32);
		wood.mirror = true;
		setRotation(wood, 0F, 0F, 0F);

		plate.addBox(-7F, 0F, -7F, 14, 1, 14);
		plate.setRotationPoint(0F, 22F, 0F);
		plate.setTextureSize(64, 32);
		plate.mirror = true;
		setRotation(plate, 0F, 0F, 0F);
	}

	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7,
			byte par8) {
		super.render(par1Entity, par2, par3, par4, par5, par6, par7);
		this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);

		if (par8 == 0 || par8 == 1) {
			this.butter.render(par7);
			this.carrot1.render(par7);
			this.carrot2.render(par7);
			this.carrot3.render(par7);
			this.potato1.render(par7);
			this.potato2.render(par7);
			if (par8 == 0)
				this.beef.render(par7);
			if (par8 == 1)
				this.pork.render(par7);
		} else if (par8 == 2) {
			this.body1.render(par7);
			this.body2.render(par7);
			this.leg1.render(par7);
			this.leg2.render(par7);
			this.leg3.render(par7);
			this.leg4.render(par7);
			this.leg5.render(par7);
			this.leg6.render(par7);
			this.leg7.render(par7);
			this.leg8.render(par7);
		} else {
			this.bottom.render(par7);
			this.bottom2.render(par7);
			this.top1.render(par7);
			this.top2.render(par7);
			this.Shape1.render(par7);
		}
	}

	public void renderPlate(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7,
			byte par8) {
		super.render(par1Entity, par2, par3, par4, par5, par6, par7);
		this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		this.wood.render(par7);
		this.plate.render(par7);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.beef.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.pork.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.butter.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.carrot1.rotateAngleY = -0.4363323F + f3 / (180F / (float) Math.PI);
		this.carrot2.rotateAngleY = -0.4363323F + f3 / (180F / (float) Math.PI);
		this.carrot3.rotateAngleY = 0.7853982F + f3 / (180F / (float) Math.PI);
		this.potato1.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.potato2.rotateAngleY = f3 / (180F / (float) Math.PI);

		this.body1.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.body2.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.leg1.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.leg2.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.leg3.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.leg4.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.leg5.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.leg6.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.leg7.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.leg8.rotateAngleY = f3 / (180F / (float) Math.PI);

		this.top1.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.top2.rotateAngleY = f3 / (180F / (float) Math.PI);

		this.wood.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.plate.rotateAngleY = f3 / (180F / (float) Math.PI);

	}
}
