package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelPanHandle extends ModelBase {
	// fields
	public ModelRenderer handlea1 = (new ModelRenderer(this, 0, 0)).setTextureSize(32, 32);
	public ModelRenderer handlea2 = (new ModelRenderer(this, 0, 0)).setTextureSize(32, 32);
	public ModelRenderer handlea3 = (new ModelRenderer(this, 0, 0)).setTextureSize(32, 32);
	public ModelRenderer handlea4 = (new ModelRenderer(this, 0, 0)).setTextureSize(32, 32);

	public ModelPanHandle() {

		handlea1.addBox(6F, 0F, -1.5F, 2, 1, 3);
		handlea1.setRotationPoint(0F, 16F, 0F);
		handlea2.addBox(-8F, 0F, -1.5F, 2, 1, 3);
		handlea2.setRotationPoint(0F, 16F, 0F);
		handlea3.addBox(-1.5F, 0F, 6F, 3, 1, 2);
		handlea3.setRotationPoint(0F, 16F, 0F);
		handlea4.addBox(-1.5F, 0F, -8F, 3, 1, 2);
		handlea4.setRotationPoint(0F, 16F, 0F);
	}

	public void render(Entity par1Entity, float par2, float par3, float par4, byte par5, float par6, float par7) {
		if (par5 == 0 || par5 == 2) {
			this.handlea1.render(0.0625F);
			this.handlea2.render(0.0625F);
		} else {
			this.handlea3.render(0.0625F);
			this.handlea4.render(0.0625F);
		}
	}

}
