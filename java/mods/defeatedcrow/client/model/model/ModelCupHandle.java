package mods.defeatedcrow.client.model.model;

import mods.defeatedcrow.common.*;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelCupHandle extends ModelBase {
	// fields
	public ModelRenderer handlea1 = (new ModelRenderer(this, 0, 0)).setTextureSize(64, 32);
	public ModelRenderer handlea3 = (new ModelRenderer(this, 0, 0)).setTextureSize(64, 32);
	public ModelRenderer handlea2 = (new ModelRenderer(this, 0, 0)).setTextureSize(64, 32);

	public ModelRenderer handleb1 = (new ModelRenderer(this, 0, 0)).setTextureSize(64, 32);
	public ModelRenderer handleb3 = (new ModelRenderer(this, 0, 0)).setTextureSize(64, 32);
	public ModelRenderer handleb2 = (new ModelRenderer(this, 0, 0)).setTextureSize(64, 32);

	public ModelRenderer handlec1 = (new ModelRenderer(this, 0, 0)).setTextureSize(64, 32);
	public ModelRenderer handlec3 = (new ModelRenderer(this, 0, 0)).setTextureSize(64, 32);
	public ModelRenderer handlec2 = (new ModelRenderer(this, 0, 0)).setTextureSize(64, 32);

	public ModelRenderer handled1 = (new ModelRenderer(this, 0, 0)).setTextureSize(64, 32);
	public ModelRenderer handled3 = (new ModelRenderer(this, 0, 0)).setTextureSize(64, 32);
	public ModelRenderer handled2 = (new ModelRenderer(this, 0, 0)).setTextureSize(64, 32);

	// for Summer or JP rendering
	ModelRenderer sideF = new ModelRenderer(this, 0, 10);
	ModelRenderer sideB = new ModelRenderer(this, 0, 10);
	ModelRenderer sideR = new ModelRenderer(this, 15, 10);
	ModelRenderer sideL = new ModelRenderer(this, 15, 10);
	ModelRenderer Bottom = new ModelRenderer(this, 0, 0);

	public ModelCupHandle() {

		// south
		this.handlea1.addBox(0F, 0F, 0F, 2, 1, 2, 0.0F);
		this.handlea1.rotationPointX = -1.0F;
		this.handlea1.rotationPointY = 17.0F;
		this.handlea1.rotationPointZ = -5.0F;
		this.handlea1.mirror = true;
		this.handlea3.addBox(0F, 0F, 0F, 2, 1, 2, 0.0F);
		this.handlea3.rotationPointX = -1.0F;
		this.handlea3.rotationPointY = 22.0F;
		this.handlea3.rotationPointZ = -5.0F;
		this.handlea3.mirror = true;
		this.handlea2.addBox(0F, 0F, 0F, 2, 6, 1, 0.0F);
		this.handlea2.rotationPointX = -1.0F;
		this.handlea2.rotationPointY = 17.0F;
		this.handlea2.rotationPointZ = -6.0F;
		this.handlea2.mirror = true;
		// north
		handleb1.addBox(0F, 0F, 0F, 2, 1, 2);
		handleb1.setRotationPoint(-1F, 17F, 3F);
		handleb1.mirror = true;
		handleb3.addBox(0F, 0F, 0F, 2, 1, 2);
		handleb3.setRotationPoint(-1F, 22F, 3F);
		handleb3.mirror = true;
		handleb2.addBox(0F, 0F, 0F, 2, 6, 1);
		handleb2.setRotationPoint(-1F, 17F, 5F);
		handleb2.mirror = true;
		// east
		handlec3.addBox(0F, 0F, 0F, 2, 1, 2);
		handlec3.setRotationPoint(3F, 22F, -1F);
		handlec3.setTextureSize(64, 32);
		handlec3.mirror = true;
		handlec1.addBox(0F, 0F, 0F, 2, 1, 2);
		handlec1.setRotationPoint(3F, 17F, -1F);
		handlec1.setTextureSize(64, 32);
		handlec1.mirror = true;
		handlec2.addBox(0F, 0F, 0F, 1, 6, 2);
		handlec2.setRotationPoint(5F, 17F, -1F);
		handlec2.setTextureSize(64, 32);
		handlec2.mirror = true;
		// west
		handled1.addBox(0F, 0F, 0F, 2, 1, 2);
		handled1.setRotationPoint(-5F, 17F, -1F);
		handled1.mirror = true;
		handled3.addBox(0F, 0F, 0F, 2, 1, 2);
		handled3.setRotationPoint(-5F, 22F, -1F);
		handled3.mirror = true;
		handled2.addBox(0F, 0F, 0F, 1, 6, 2);
		handled2.setRotationPoint(-6F, 17F, -1F);
		handled2.mirror = true;

		// Summer and JP
		sideF.addBox(-3F, 0F, -3F, 6, 8, 1);
		sideF.setRotationPoint(0F, 16F, 0F);
		sideF.setTextureSize(64, 32);
		sideF.mirror = true;
		sideB.addBox(-3F, 0F, 2F, 6, 8, 1);
		sideB.setRotationPoint(0F, 16F, 0F);
		sideB.setTextureSize(64, 32);
		sideB.mirror = true;
		sideR.addBox(-3F, 0F, -2F, 1, 8, 4);
		sideR.setRotationPoint(0F, 16F, 0F);
		sideR.setTextureSize(64, 32);
		sideR.mirror = true;
		sideL.addBox(2F, 0F, -2F, 1, 8, 4);
		sideL.setRotationPoint(0F, 16F, 0F);
		sideL.setTextureSize(64, 32);
		sideL.mirror = true;
		Bottom.addBox(-2F, 0F, -2F, 4, 2, 4);
		Bottom.setRotationPoint(0F, 22F, 0F);
		Bottom.setTextureSize(64, 32);
		Bottom.mirror = true;
	}

	public void render(Entity par1Entity, float par2, float par3, float par4, byte par5, float par6, float par7) {
		// if (DCsConfig.useJapaneseCup)
		// {
		// this.sideB.render(par7);
		// this.sideF.render(par7);
		// this.sideL.render(par7);
		// this.sideR.render(par7);
		// this.Bottom.render(par7);
		// }
		// else
		// {
		if (par5 == 0) {
			this.handleb1.render(0.0625F);
			this.handleb2.render(0.0625F);
			this.handleb3.render(0.0625F);
		} else if (par5 == 1) {
			this.handlec1.render(0.0625F);
			this.handlec2.render(0.0625F);
			this.handlec3.render(0.0625F);
		} else if (par5 == 2) {
			this.handlea1.render(0.0625F);
			this.handlea2.render(0.0625F);
			this.handlea3.render(0.0625F);
		} else if (par5 == 4) {
			this.handled1.render(0.0625F);
			this.handled2.render(0.0625F);
			this.handled3.render(0.0625F);
		}
		// }
	}

	public void renderSummer(Entity par1Entity, float par2, float par3, float par4, float per5, float par6, float par7) {
		this.sideB.render(par7);
		this.sideF.render(par7);
		this.sideL.render(par7);
		this.sideR.render(par7);
		this.Bottom.render(par7);
	}

}
