package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelBowlJP extends ModelBase {
	// fields
	public ModelRenderer bottom1 = (new ModelRenderer(this, 0, 0)).setTextureSize(64, 32);
	public ModelRenderer bottom2 = (new ModelRenderer(this, 0, 0)).setTextureSize(64, 32);

	public ModelRenderer sideL = (new ModelRenderer(this, 0, 10)).setTextureSize(64, 32);
	public ModelRenderer sideR = (new ModelRenderer(this, 14, 10)).setTextureSize(64, 32);
	public ModelRenderer sideF = (new ModelRenderer(this, 0, 20)).setTextureSize(64, 32);
	public ModelRenderer sideB = (new ModelRenderer(this, 0, 25)).setTextureSize(64, 32);

	public ModelRenderer sideL2 = (new ModelRenderer(this, 33, 10)).setTextureSize(64, 32);
	public ModelRenderer sideR2 = (new ModelRenderer(this, 47, 10)).setTextureSize(64, 32);
	public ModelRenderer sideF2 = (new ModelRenderer(this, 33, 19)).setTextureSize(64, 32);
	public ModelRenderer sideB2 = (new ModelRenderer(this, 33, 23)).setTextureSize(64, 32);

	public ModelBowlJP() {

		bottom1.addBox(0F, 0F, 0F, 4, 1, 4);
		bottom1.setRotationPoint(-2F, 23F, -2F);
		bottom1.mirror = true;
		setRotation(bottom1, 0F, 0F, 0F);
		bottom2.addBox(0F, 0F, 0F, 6, 1, 6);
		bottom2.setRotationPoint(-3F, 22F, -3F);
		bottom2.mirror = true;
		setRotation(bottom2, 0F, 0F, 0F);
		sideL.addBox(0F, 0F, 0F, 1, 4, 6);
		sideL.setRotationPoint(3F, 18F, -3F);
		sideL.mirror = true;
		setRotation(sideL, 0F, 0F, 0F);
		sideR.addBox(0F, 0F, 0F, 1, 4, 6);
		sideR.setRotationPoint(-4F, 18F, -3F);
		sideR.mirror = true;
		setRotation(sideR, 0F, 0F, 0F);
		sideF.addBox(0F, -2F, 0F, 8, 4, 1);
		sideF.setRotationPoint(-4F, 20F, -4F);
		sideF.mirror = true;
		setRotation(sideF, 0F, 0F, 0F);
		sideB.addBox(0F, 0F, 0F, 8, 4, 1);
		sideB.setRotationPoint(-4F, 18F, 3F);
		sideB.mirror = true;
		setRotation(sideB, 0F, 0F, 0F);
		sideL2.addBox(0F, 0F, 0F, 1, 3, 6);
		sideL2.setRotationPoint(3F, 19F, -3F);
		sideL2.mirror = true;
		setRotation(sideL2, 0F, 0F, 0F);
		sideR2.addBox(0F, 0F, 0F, 1, 3, 6);
		sideR2.setRotationPoint(-4F, 19F, -3F);
		sideR2.mirror = true;
		setRotation(sideR2, 0F, 0F, 0F);
		sideF2.addBox(0F, 0F, 0F, 8, 3, 1);
		sideF2.setRotationPoint(-4F, 19F, -4F);
		sideF2.mirror = true;
		setRotation(sideF2, 0F, 0F, 0F);
		sideB2.addBox(0F, 0F, 0F, 8, 3, 1);
		sideB2.setRotationPoint(-4F, 19F, 3F);
		sideB2.mirror = true;
		setRotation(sideB2, 0F, 0F, 0F);
	}

	public void render(Entity par1Entity, float par2, float par3, float par4, byte par5, float par6, float par7) {
		super.render(par1Entity, par2, par3, par4, par5, par6, par7);
		this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		this.bottom1.render(par7);
		this.bottom2.render(par7);
		if (par5 == 0 || par5 == 4) {
			this.sideL2.render(par7);
			this.sideR2.render(par7);
			this.sideF2.render(par7);
			this.sideB2.render(par7);
		} else {
			this.sideL.render(par7);
			this.sideR.render(par7);
			this.sideF.render(par7);
			this.sideB.render(par7);
		}
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
