package mods.defeatedcrow.client.model.model;

import net.minecraft.src.*;
import net.minecraft.util.MathHelper;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelWipeBox extends ModelBase {
	// fields
	ModelRenderer Shape1 = new ModelRenderer(this, 0, 0).setTextureSize(64, 32);

	public ModelWipeBox() {
		Shape1.addBox(-5F, 0F, -5F, 10, 8, 10);
		Shape1.setRotationPoint(0F, 16F, 0F);
		Shape1.mirror = true;
	}

	public void render(Entity entity, float f, float f1, float f2, byte f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		if (f3 == 1 || f3 > 2)
			Shape1.render(f5);
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
