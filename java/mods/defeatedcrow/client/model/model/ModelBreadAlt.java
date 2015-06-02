package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBreadAlt extends ModelBase {
	// fields
	ModelRenderer bread1;
	ModelRenderer bread2;
	ModelRenderer bread3;
	ModelRenderer bread4;
	ModelRenderer bread5;

	public ModelBreadAlt() {
		textureWidth = 32;
		textureHeight = 32;

		bread1 = new ModelRenderer(this, 0, 0);
		bread1.addBox(-1F, -10F, 1.5F, 4, 16, 3);
		bread1.setRotationPoint(0F, 16F, 0F);
		bread1.setTextureSize(32, 32);
		bread1.mirror = true;
		setRotation(bread1, -0.2617994F, 0.3490659F, 0F);
		bread2 = new ModelRenderer(this, 0, 0);
		bread2.addBox(-0.5F, -10F, 1.5F, 4, 16, 3);
		bread2.setRotationPoint(0F, 16F, 0F);
		bread2.setTextureSize(32, 32);
		bread2.mirror = true;
		setRotation(bread2, -0.3396263F, -1.308997F, 0.2094395F);
		bread3 = new ModelRenderer(this, 16, 0);
		bread3.addBox(-2F, -8F, -2F, 3, 14, 3);
		bread3.setRotationPoint(0F, 16F, 0F);
		bread3.setTextureSize(32, 32);
		bread3.mirror = true;
		setRotation(bread3, -0.0872665F, -0.122173F, 0F);
		bread4 = new ModelRenderer(this, 16, 0);
		bread4.addBox(0F, -8F, 1.5F, 3, 14, 3);
		bread4.setRotationPoint(0F, 16F, 0F);
		bread4.setTextureSize(32, 32);
		bread4.mirror = true;
		setRotation(bread4, -0.2792527F, 1.919862F, 0.0872665F);
		bread5 = new ModelRenderer(this, 16, 0);
		bread5.addBox(-0.5F, -7F, -5F, 3, 14, 3);
		bread5.setRotationPoint(0F, 16F, 0F);
		bread5.setTextureSize(32, 32);
		bread5.mirror = true;
		setRotation(bread5, 0.0698132F, 1.047198F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		if (f3 > 0)// 0-5
		{
			this.bread1.render(f5);
			if (f3 > 1) {
				this.bread2.render(f5);
				if (f3 > 2) {
					this.bread3.render(f5);
					if (f3 > 3) {
						this.bread5.render(f5);
						if (f3 > 4) {
							this.bread4.render(f5);
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

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
	}

}
