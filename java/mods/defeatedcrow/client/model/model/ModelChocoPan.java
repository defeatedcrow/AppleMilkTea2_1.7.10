package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelChocoPan extends ModelBase {
	// fields
	public ModelRenderer contants1 = (new ModelRenderer(this, 0, 0)).setTextureSize(64, 32);
	public ModelRenderer contants2 = (new ModelRenderer(this, 0, 0)).setTextureSize(64, 32);
	public ModelRenderer contants3 = (new ModelRenderer(this, 0, 0)).setTextureSize(64, 32);

	public ModelChocoPan() {
		contants1.addBox(-5F, 0F, -5F, 10, 1, 10);
		contants1.setRotationPoint(0F, 21F, 0F);
		setRotation(contants1, 0F, 0F, 0F);
		contants2.addBox(-5F, 0F, -5F, 10, 3, 10);
		contants2.setRotationPoint(0F, 19F, 0F);
		setRotation(contants2, 0F, 0F, 0F);
		contants3.addBox(-5F, 0F, -5F, 10, 5, 10);
		contants3.setRotationPoint(0F, 17F, 0F);
		setRotation(contants3, 0F, 0F, 0F);
	}

	public void render(Entity par1Entity, float par2, float par3, float par4, byte par5, float par6, float par7) {
		super.render(par1Entity, par2, par3, par4, par5, par6, par7);
		this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		this.contants1.render(par7);
		if (par5 > 3) {
			this.contants2.render(par7);
			if (par5 > 7) {
				this.contants3.render(par7);
			}
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
