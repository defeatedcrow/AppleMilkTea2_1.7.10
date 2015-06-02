package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelChopsticks extends ModelBase {
	// fields
	public ModelRenderer cup = (new ModelRenderer(this, 5, 0)).setTextureSize(32, 16);
	public ModelRenderer stick1 = (new ModelRenderer(this, 0, 0)).setTextureSize(32, 16);
	public ModelRenderer stick2 = (new ModelRenderer(this, 0, 0)).setTextureSize(32, 16);
	public ModelRenderer stick3 = (new ModelRenderer(this, 0, 0)).setTextureSize(32, 16);
	public ModelRenderer stick4 = (new ModelRenderer(this, 0, 0)).setTextureSize(32, 16);
	public ModelRenderer stick5 = (new ModelRenderer(this, 0, 0)).setTextureSize(32, 16);
	public ModelRenderer stick6 = (new ModelRenderer(this, 0, 0)).setTextureSize(32, 16);

	public ModelChopsticks() {

		cup.addBox(0F, 0F, 0F, 4, 5, 4);
		cup.setRotationPoint(-2F, 19F, -2F);
		cup.mirror = true;
		setRotation(cup, 0F, 0F, 0F);
		stick1.addBox(0F, 0F, 0F, 1, 8, 1);
		stick1.setRotationPoint(1F, 14F, 0F);
		stick1.mirror = true;
		setRotation(stick1, 0F, 0F, 0.2094395F);
		stick2.addBox(0F, 0F, 0F, 1, 8, 1);
		stick2.setRotationPoint(1F, 14F, -2F);
		stick2.mirror = true;
		setRotation(stick2, 0.1745329F, 0F, 0.0872665F);
		stick3.addBox(0F, 0F, 0F, 1, 8, 1);
		stick3.setRotationPoint(0F, 14F, 1.5F);
		stick3.mirror = true;
		setRotation(stick3, -0.2617994F, 0F, 0F);
		stick4.addBox(0F, 0F, 0F, 1, 8, 1);
		stick4.setRotationPoint(-2F, 14F, 2F);
		stick4.mirror = true;
		setRotation(stick4, -0.2617994F, 0F, -0.1745329F);
		stick5.addBox(0F, 0F, 0F, 1, 8, 1);
		stick5.setRotationPoint(-2.5F, 15F, -2.5F);
		stick5.mirror = true;
		setRotation(stick5, 0.2617994F, 0F, -0.2617994F);
		stick6.addBox(0F, 0F, 0F, 1, 8, 1);
		stick6.setRotationPoint(-1F, 14F, -1F);
		stick6.mirror = true;
		setRotation(stick6, 0.0523599F, 0F, 0F);
	}

	public void render(Entity par1Entity, float par2, float par3, float par4, byte par5, float par6, float par7) {
		super.render(par1Entity, par2, par3, par4, par5, par6, par7);
		this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		this.cup.render(par7);
		if (par5 > 0) {
			this.stick1.render(par7);
			this.stick2.render(par7);
			this.stick3.render(par7);
			if (par5 > 1) {
				this.stick4.render(par7);
				if (par5 > 2) {
					this.stick5.render(par7);
					if (par5 > 3) {
						this.stick6.render(par7);
					}
				}
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
