package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelEggs extends ModelBase {
	// fields
	public ModelRenderer bottom1 = (new ModelRenderer(this, 0, 0)).setTextureSize(16, 16);
	public ModelRenderer bottom2 = (new ModelRenderer(this, 0, 0)).setTextureSize(16, 16);
	public ModelRenderer bottom3 = (new ModelRenderer(this, 0, 0)).setTextureSize(16, 16);
	public ModelRenderer bottom4 = (new ModelRenderer(this, 0, 0)).setTextureSize(16, 16);
	public ModelRenderer bottom5 = (new ModelRenderer(this, 0, 0)).setTextureSize(16, 16);
	public ModelRenderer bottom6 = (new ModelRenderer(this, 0, 0)).setTextureSize(16, 16);
	public ModelRenderer bottom7 = (new ModelRenderer(this, 0, 0)).setTextureSize(16, 16);
	public ModelRenderer bottom8 = (new ModelRenderer(this, 0, 0)).setTextureSize(16, 16);

	public ModelRenderer top1 = (new ModelRenderer(this, 0, 0)).setTextureSize(16, 16);
	public ModelRenderer top2 = (new ModelRenderer(this, 0, 0)).setTextureSize(16, 16);
	public ModelRenderer top3 = (new ModelRenderer(this, 0, 0)).setTextureSize(16, 16);
	public ModelRenderer top4 = (new ModelRenderer(this, 0, 0)).setTextureSize(16, 16);
	public ModelRenderer top5 = (new ModelRenderer(this, 0, 0)).setTextureSize(16, 16);
	public ModelRenderer top6 = (new ModelRenderer(this, 0, 0)).setTextureSize(16, 16);
	public ModelRenderer top7 = (new ModelRenderer(this, 0, 0)).setTextureSize(16, 16);
	public ModelRenderer top8 = (new ModelRenderer(this, 0, 0)).setTextureSize(16, 16);

	public ModelEggs() {

		bottom1 = new ModelRenderer(this, 0, 0);
		bottom1.addBox(0F, 0F, 0F, 3, 3, 3);
		bottom1.setRotationPoint(-6.5F, 21F, -5F);
		bottom1.setTextureSize(64, 32);
		bottom1.mirror = true;
		setRotation(bottom1, 0F, 0F, 0F);
		bottom2 = new ModelRenderer(this, 0, 0);
		bottom2.addBox(0F, 0F, 0F, 3, 3, 3);
		bottom2.setRotationPoint(-1.5F, 21F, -5F);
		bottom2.setTextureSize(64, 32);
		bottom2.mirror = true;
		setRotation(bottom2, 0F, 0F, 0F);
		bottom3 = new ModelRenderer(this, 0, 0);
		bottom3.addBox(0F, 0F, 0F, 3, 3, 3);
		bottom3.setRotationPoint(3.5F, 21F, -5F);
		bottom3.setTextureSize(64, 32);
		bottom3.mirror = true;
		setRotation(bottom3, 0F, 0F, 0F);
		bottom4 = new ModelRenderer(this, 0, 0);
		bottom4.addBox(0F, 0F, 0F, 3, 3, 3);
		bottom4.setRotationPoint(-4F, 21F, -1F);
		bottom4.setTextureSize(64, 32);
		bottom4.mirror = true;
		setRotation(bottom4, 0F, 0F, 0F);
		bottom5 = new ModelRenderer(this, 0, 0);
		bottom5.addBox(0F, 0F, 0F, 3, 3, 3);
		bottom5.setRotationPoint(1F, 21F, -1F);
		bottom5.setTextureSize(64, 32);
		bottom5.mirror = true;
		setRotation(bottom5, 0F, 0F, 0F);
		bottom6 = new ModelRenderer(this, 0, 0);
		bottom6.addBox(0F, 0F, 0F, 3, 3, 3);
		bottom6.setRotationPoint(-1.5F, 21F, 3F);
		bottom6.setTextureSize(64, 32);
		bottom6.mirror = true;
		setRotation(bottom6, 0F, 0F, 0F);
		bottom7 = new ModelRenderer(this, 0, 0);
		bottom7.addBox(0F, 0F, 0F, 3, 3, 3);
		bottom7.setRotationPoint(3.5F, 21F, 3F);
		bottom7.setTextureSize(64, 32);
		bottom7.mirror = true;
		setRotation(bottom7, 0F, 0F, 0F);
		bottom8 = new ModelRenderer(this, 0, 0);
		bottom8.addBox(0F, 0F, 0F, 3, 3, 3);
		bottom8.setRotationPoint(-6.5F, 21F, 3F);
		bottom8.setTextureSize(64, 32);
		bottom8.mirror = true;
		setRotation(bottom8, 0F, 0F, 0F);
		top1 = new ModelRenderer(this, 0, 0);
		top1.addBox(0F, 0F, 0F, 2, 1, 2);
		top1.setRotationPoint(-6F, 20F, -4.5F);
		top1.setTextureSize(64, 32);
		top1.mirror = true;
		setRotation(top1, 0F, 0F, 0F);
		top2 = new ModelRenderer(this, 0, 0);
		top2.addBox(0F, 0F, 0F, 2, 1, 2);
		top2.setRotationPoint(-1F, 20F, -4.5F);
		top2.setTextureSize(64, 32);
		top2.mirror = true;
		setRotation(top2, 0F, 0F, 0F);
		top3 = new ModelRenderer(this, 0, 0);
		top3.addBox(0F, 0F, 0F, 2, 1, 2);
		top3.setRotationPoint(4F, 20F, -4.5F);
		top3.setTextureSize(64, 32);
		top3.mirror = true;
		setRotation(top3, 0F, 0F, 0F);
		top4 = new ModelRenderer(this, 0, 0);
		top4.addBox(0F, 0F, 0F, 2, 1, 2);
		top4.setRotationPoint(-3.5F, 20F, -0.5F);
		top4.setTextureSize(64, 32);
		top4.mirror = true;
		setRotation(top4, 0F, 0F, 0F);
		top5 = new ModelRenderer(this, 0, 0);
		top5.addBox(0F, 0F, 0F, 2, 1, 2);
		top5.setRotationPoint(1.5F, 20F, -0.5F);
		top5.setTextureSize(64, 32);
		top5.mirror = true;
		setRotation(top5, 0F, 0F, 0F);
		top6 = new ModelRenderer(this, 0, 0);
		top6.addBox(0F, 0F, 0F, 2, 1, 2);
		top6.setRotationPoint(-6F, 20F, 3.5F);
		top6.setTextureSize(64, 32);
		top6.mirror = true;
		setRotation(top6, 0F, 0F, 0F);
		top7 = new ModelRenderer(this, 0, 0);
		top7.addBox(0F, 0F, 0F, 2, 1, 2);
		top7.setRotationPoint(-1F, 20F, 3.5F);
		top7.setTextureSize(64, 32);
		top7.mirror = true;
		setRotation(top7, 0F, 0F, 0F);
		top8 = new ModelRenderer(this, 0, 0);
		top8.addBox(0F, 0F, 0F, 2, 1, 2);
		top8.setRotationPoint(4F, 20F, 3.5F);
		top8.setTextureSize(64, 32);
		top8.mirror = true;
		setRotation(top8, 0F, 0F, 0F);
	}

	public void render(Entity par1Entity, float par2, float par3, float par4, byte par5, float par6, float par7) {
		super.render(par1Entity, par2, par3, par4, par5, par6, par7);
		this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		this.bottom1.render(par7);
		this.bottom2.render(par7);
		this.bottom3.render(par7);
		this.bottom4.render(par7);
		this.bottom5.render(par7);
		this.bottom6.render(par7);
		this.bottom7.render(par7);
		this.bottom8.render(par7);
		this.top1.render(par7);
		this.top2.render(par7);
		this.top3.render(par7);
		this.top4.render(par7);
		this.top5.render(par7);
		this.top6.render(par7);
		this.top7.render(par7);
		this.top8.render(par7);
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
