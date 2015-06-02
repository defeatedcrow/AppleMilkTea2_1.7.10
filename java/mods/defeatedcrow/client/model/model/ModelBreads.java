package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelBreads extends ModelBase {
	// fields
	public ModelRenderer bread1 = (new ModelRenderer(this, 0, 0)).setTextureSize(32, 32);
	public ModelRenderer bread2 = (new ModelRenderer(this, 0, 0)).setTextureSize(32, 32);
	public ModelRenderer bread3 = (new ModelRenderer(this, 0, 0)).setTextureSize(32, 32);
	public ModelRenderer bread4 = (new ModelRenderer(this, 0, 8)).setTextureSize(32, 32);
	public ModelRenderer bread5 = (new ModelRenderer(this, 0, 8)).setTextureSize(32, 32);

	ModelRenderer bottom1 = (new ModelRenderer(this, 0, 0)).setTextureSize(32, 32);
	ModelRenderer bottom2 = (new ModelRenderer(this, 0, 0)).setTextureSize(32, 32);
	ModelRenderer bottom3 = (new ModelRenderer(this, 0, 0)).setTextureSize(32, 32);
	ModelRenderer bottom4 = (new ModelRenderer(this, 0, 0)).setTextureSize(32, 32);
	ModelRenderer bottom5 = (new ModelRenderer(this, 0, 0)).setTextureSize(32, 32);
	ModelRenderer bottom6 = (new ModelRenderer(this, 0, 0)).setTextureSize(32, 32);
	ModelRenderer bottom7 = (new ModelRenderer(this, 0, 0)).setTextureSize(32, 32);
	ModelRenderer bottom8 = (new ModelRenderer(this, 0, 0)).setTextureSize(32, 32);
	ModelRenderer bottom9 = (new ModelRenderer(this, 0, 0)).setTextureSize(32, 32);
	ModelRenderer middle1 = (new ModelRenderer(this, 0, 0)).setTextureSize(32, 32);
	ModelRenderer middle2 = (new ModelRenderer(this, 0, 0)).setTextureSize(32, 32);
	ModelRenderer middle3 = (new ModelRenderer(this, 0, 0)).setTextureSize(32, 32);
	ModelRenderer middle4 = (new ModelRenderer(this, 0, 0)).setTextureSize(32, 32);
	ModelRenderer middle5 = (new ModelRenderer(this, 0, 0)).setTextureSize(32, 32);
	ModelRenderer middle6 = (new ModelRenderer(this, 0, 0)).setTextureSize(32, 32);
	ModelRenderer middle7 = (new ModelRenderer(this, 0, 0)).setTextureSize(32, 32);
	ModelRenderer middle8 = (new ModelRenderer(this, 0, 0)).setTextureSize(32, 32);
	ModelRenderer middle9 = (new ModelRenderer(this, 0, 0)).setTextureSize(32, 32);
	ModelRenderer top1 = (new ModelRenderer(this, 16, 0)).setTextureSize(32, 32);
	ModelRenderer top2 = (new ModelRenderer(this, 16, 0)).setTextureSize(32, 32);
	ModelRenderer top3 = (new ModelRenderer(this, 16, 0)).setTextureSize(32, 32);
	ModelRenderer top4 = (new ModelRenderer(this, 16, 0)).setTextureSize(32, 32);
	ModelRenderer top5 = (new ModelRenderer(this, 16, 0)).setTextureSize(32, 32);
	ModelRenderer top6 = (new ModelRenderer(this, 16, 0)).setTextureSize(32, 32);
	ModelRenderer top7 = (new ModelRenderer(this, 16, 0)).setTextureSize(32, 32);
	ModelRenderer top8 = (new ModelRenderer(this, 16, 0)).setTextureSize(32, 32);
	ModelRenderer top9 = (new ModelRenderer(this, 16, 0)).setTextureSize(32, 32);

	public ModelBreads() {

		bread1.addBox(0F, 0F, 0F, 10, 3, 4);
		bread1.setRotationPoint(-4F, 20F, 3F);
		bread1.mirror = true;
		this.setRotation(bread1, 0.3490659F, 0.418879F, 0F);
		bread2.addBox(0F, 0F, 0F, 10, 3, 4);
		bread2.setRotationPoint(4F, 20F, -7F);
		bread2.mirror = true;
		this.setRotation(bread2, 1.3174533F, -1.02173F, -1.3759587F);
		bread3.addBox(0F, 0F, 0F, 10, 3, 4);
		bread3.setRotationPoint(-3F, 20F, -4F);
		bread3.mirror = true;
		this.setRotation(bread3, 0.6174533F, -0.7726646F, -1.0457718F);
		bread5.addBox(0F, 0F, 0F, 5, 3, 5);
		bread5.setRotationPoint(0F, 17F, -6F);
		bread5.mirror = true;
		this.setRotation(bread5, 0.5061455F, 0.1745329F, -0.2268928F);
		bread4.addBox(0F, 0F, 0F, 5, 3, 5);
		bread4.setRotationPoint(-6F, 19F, -6F);
		bread4.mirror = true;
		this.setRotation(bread4, 0.5235988F, 0.2094395F, -0.5585054F);

		bottom1.addBox(-7F, 0F, -7F, 4, 7, 4);
		bottom1.setRotationPoint(0F, 15F, 0F);
		bottom1.mirror = true;
		setRotation(bottom1, 0F, 0F, 0F);
		bottom2.addBox(-2F, 0F, -7F, 4, 7, 4);
		bottom2.setRotationPoint(0F, 15F, 0F);
		bottom2.mirror = true;
		setRotation(bottom2, 0F, 0F, 0F);
		bottom3.addBox(3F, 0F, -7F, 4, 7, 4);
		bottom3.setRotationPoint(0F, 15F, 0F);
		bottom3.mirror = true;
		setRotation(bottom3, 0F, 0F, 0F);
		bottom4.addBox(-7F, 0F, -2F, 4, 7, 4);
		bottom4.setRotationPoint(0F, 15F, 0F);
		bottom4.mirror = true;
		setRotation(bottom4, 0F, 0F, 0F);
		bottom5.addBox(-2F, 0F, -2F, 4, 7, 4);
		bottom5.setRotationPoint(0F, 15F, 0F);
		bottom5.mirror = true;
		setRotation(bottom5, 0F, 0F, 0F);
		bottom6.addBox(3F, 0F, -2F, 4, 7, 4);
		bottom6.setRotationPoint(0F, 15F, 0F);
		bottom6.mirror = true;
		setRotation(bottom6, 0F, 0F, 0F);
		bottom7.addBox(-7F, 0F, 3F, 4, 7, 4);
		bottom7.setRotationPoint(0F, 15F, 0F);
		bottom7.mirror = true;
		setRotation(bottom7, 0F, 0F, 0F);
		bottom8.addBox(-2F, 0F, 3F, 4, 7, 4);
		bottom8.setRotationPoint(0F, 15F, 0F);
		bottom8.mirror = true;
		setRotation(bottom8, 0F, 0F, 0F);
		bottom9.addBox(3F, 0F, 3F, 4, 7, 4);
		bottom9.setRotationPoint(0F, 15F, 0F);
		bottom9.mirror = true;
		setRotation(bottom9, 0F, 0F, 0F);
		middle1.addBox(-6.5F, 0F, -6.5F, 3, 2, 3);
		middle1.setRotationPoint(0F, 13F, 0F);
		middle1.mirror = true;
		setRotation(middle1, 0F, 0F, 0F);
		middle2.addBox(0F, 0F, -6.5F, 3, 2, 3);
		middle2.setRotationPoint(-1.5F, 13F, 0F);
		middle2.mirror = true;
		setRotation(middle2, 0F, 0F, 0F);
		middle3.addBox(3.5F, 0F, -6.5F, 3, 2, 3);
		middle3.setRotationPoint(0F, 13F, 0F);
		middle3.mirror = true;
		setRotation(middle3, 0F, 0F, 0F);
		middle4.addBox(-6.5F, 0F, -1.5F, 3, 2, 3);
		middle4.setRotationPoint(0F, 13F, 0F);
		middle4.mirror = true;
		setRotation(middle4, 0F, 0F, 0F);
		middle5.addBox(-1.5F, 0F, -1.5F, 3, 2, 3);
		middle5.setRotationPoint(0F, 13F, 0F);
		middle5.mirror = true;
		setRotation(middle5, 0F, 0F, 0F);
		middle6.addBox(3.5F, 0F, -1.5F, 3, 2, 3);
		middle6.setRotationPoint(0F, 13F, 0F);
		middle6.mirror = true;
		setRotation(middle6, 0F, 0F, 0F);
		middle7.addBox(-6.5F, 0F, 3.5F, 3, 2, 3);
		middle7.setRotationPoint(0F, 13F, 0F);
		middle7.mirror = true;
		setRotation(middle7, 0F, 0F, 0F);
		middle8.addBox(-1.5F, 0F, 3.5F, 3, 2, 3);
		middle8.setRotationPoint(0F, 13F, 0F);
		middle8.mirror = true;
		setRotation(middle8, 0F, 0F, 0F);
		middle9.addBox(3.5F, 0F, 3.5F, 3, 2, 3);
		middle9.setRotationPoint(0F, 13F, 0F);
		middle9.mirror = true;
		setRotation(middle9, 0F, 0F, 0F);
		top1.addBox(-6F, 0F, -6F, 2, 4, 2);
		top1.setRotationPoint(0F, 9F, 0F);
		top1.mirror = true;
		setRotation(top1, 0F, 0F, 0F);
		top2.addBox(-1F, 0F, -6F, 2, 4, 2);
		top2.setRotationPoint(0F, 9F, 0F);
		top2.mirror = true;
		setRotation(top2, 0F, 0F, 0F);
		top3.addBox(4F, 0F, -6F, 2, 4, 2);
		top3.setRotationPoint(0F, 9F, 0F);
		top3.mirror = true;
		setRotation(top3, 0F, 0F, 0F);
		top4.addBox(-6F, 0F, -1F, 2, 4, 2);
		top4.setRotationPoint(0F, 9F, 0F);
		top4.mirror = true;
		setRotation(top4, 0F, 0F, 0F);
		top5.addBox(-1F, 0F, -1F, 2, 4, 2);
		top5.setRotationPoint(0F, 9F, 0F);
		top5.mirror = true;
		setRotation(top5, 0F, 0F, 0F);
		top6.addBox(4F, 0F, -1F, 2, 4, 2);
		top6.setRotationPoint(0F, 9F, 0F);
		top6.mirror = true;
		setRotation(top6, 0F, 0F, 0F);
		top7.addBox(-6F, 0F, 4F, 2, 4, 2);
		top7.setRotationPoint(0F, 9F, 0F);
		top7.mirror = true;
		setRotation(top7, 0F, 0F, 0F);
		top8.addBox(-1F, 0F, 4F, 2, 4, 2);
		top8.setRotationPoint(0F, 9F, 0F);
		top8.mirror = true;
		setRotation(top8, 0F, 0F, 0F);
		top9.addBox(4F, 0F, 4F, 2, 4, 2);
		top9.setRotationPoint(0F, 9F, 0F);
		top9.mirror = true;
		setRotation(top9, 0F, 0F, 0F);
	}

	public void render(Entity par1Entity, float par2, float par3, float par4, byte par5, float par6, float par7) {
		super.render(par1Entity, par2, par3, par4, par5, par6, par7);
		this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		if (par5 > 0)// 0-5
		{
			this.bread1.render(par7);
			if (par5 > 1) {
				this.bread2.render(par7);
				if (par5 > 2) {
					this.bread3.render(par7);
					if (par5 > 3) {
						this.bread5.render(par7);
						if (par5 > 4) {
							this.bread4.render(par7);
						}
					}
				}
			}
		}
	}

	public void renderBottle(Entity par1Entity, float par2, float par3, float par4, byte par5, float par6, float par7) {
		super.render(par1Entity, par2, par3, par4, par5, par6, par7);
		this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		if (par5 > 5)// 6-14
		{
			this.bottom1.render(par7);
			this.middle1.render(par7);
			this.top1.render(par7);
			if (par5 > 6) {
				this.bottom2.render(par7);
				this.middle2.render(par7);
				this.top2.render(par7);
				if (par5 > 7) {
					this.bottom3.render(par7);
					this.middle3.render(par7);
					this.top3.render(par7);
					if (par5 > 8) {
						this.bottom4.render(par7);
						this.middle4.render(par7);
						this.top4.render(par7);
						if (par5 > 9) {
							this.bottom5.render(par7);
							this.middle5.render(par7);
							this.top5.render(par7);
							if (par5 > 10) {
								this.bottom6.render(par7);
								this.middle6.render(par7);
								this.top6.render(par7);
								if (par5 > 11) {
									this.bottom7.render(par7);
									this.middle7.render(par7);
									this.top7.render(par7);
									if (par5 > 12) {
										this.bottom8.render(par7);
										this.middle8.render(par7);
										this.top8.render(par7);
										if (par5 > 13) {
											this.bottom9.render(par7);
											this.middle9.render(par7);
											this.top9.render(par7);
										}
									}
								}
							}
						}
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
