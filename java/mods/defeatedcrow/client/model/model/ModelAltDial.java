package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelAltDial extends ModelBase {
	// fields
	ModelRenderer dodai1;
	ModelRenderer dodai2;
	ModelRenderer tou1;
	ModelRenderer tou2;
	ModelRenderer tou3;
	ModelRenderer juwa1;
	ModelRenderer juwa2;
	ModelRenderer juwa3;
	ModelRenderer juwa4;
	ModelRenderer juwa5;
	ModelRenderer juwa6;
	ModelRenderer dial1;
	ModelRenderer dial2;
	ModelRenderer tuwa1;
	ModelRenderer tuwa2;
	ModelRenderer tuwa3;

	public ModelAltDial() {
		textureWidth = 64;
		textureHeight = 32;

		dodai1 = new ModelRenderer(this, 0, 0);
		dodai1.addBox(-4F, 7F, -4F, 8, 1, 8);
		dodai1.setRotationPoint(0F, 16F, 0F);
		dodai1.setTextureSize(64, 32);
		dodai1.mirror = true;
		setRotation(dodai1, 0F, 0F, 0F);
		dodai2 = new ModelRenderer(this, 0, 10);
		dodai2.addBox(-3.5F, 6F, -3.5F, 7, 1, 7);
		dodai2.setRotationPoint(0F, 16F, 0F);
		dodai2.setTextureSize(64, 32);
		dodai2.mirror = true;
		setRotation(dodai2, 0F, 0F, 0F);
		tou1 = new ModelRenderer(this, 0, 19);
		tou1.addBox(-1F, -2F, -1F, 2, 7, 2);
		tou1.setRotationPoint(0F, 16F, 0F);
		tou1.setTextureSize(64, 32);
		tou1.mirror = true;
		setRotation(tou1, 0F, 0F, 0F);
		tou2 = new ModelRenderer(this, 9, 19);
		tou2.addBox(-0.5F, -4F, -0.5F, 1, 10, 1);
		tou2.setRotationPoint(0F, 16F, 0F);
		tou2.setTextureSize(64, 32);
		tou2.mirror = true;
		setRotation(tou2, 0F, 0F, 0F);
		tou3 = new ModelRenderer(this, 14, 19);
		tou3.addBox(-4F, -1.8F, -0.5F, 3, 1, 1);
		tou3.setRotationPoint(0F, 16F, 0F);
		tou3.setTextureSize(64, 32);
		tou3.mirror = true;
		setRotation(tou3, 0F, 0F, 0.0698132F);
		juwa1 = new ModelRenderer(this, 25, 0);
		juwa1.addBox(-0.5F, -3F, -4.5F, 1, 1, 2);
		juwa1.setRotationPoint(0F, 16F, 0F);
		juwa1.setTextureSize(64, 32);
		juwa1.mirror = true;
		setRotation(juwa1, -0.7853982F, 0F, 0F);
		juwa2 = new ModelRenderer(this, 33, 0);
		juwa2.addBox(-2F, -5F, -6F, 4, 4, 2);
		juwa2.setRotationPoint(0F, 16F, 0F);
		juwa2.setTextureSize(64, 32);
		juwa2.mirror = true;
		setRotation(juwa2, -0.7853982F, 0F, 0F);
		juwa3 = new ModelRenderer(this, 46, 0);
		juwa3.addBox(-1.5F, -8F, 3F, 3, 3, 1);
		juwa3.setRotationPoint(0F, 16F, 0F);
		juwa3.setTextureSize(64, 32);
		juwa3.mirror = true;
		setRotation(juwa3, 1.082104F, 0F, 0F);
		juwa4 = new ModelRenderer(this, 46, 0);
		juwa4.addBox(-1.5F, -9.5F, 1.5F, 3, 3, 1);
		juwa4.setRotationPoint(0F, 16F, 0F);
		juwa4.setTextureSize(64, 32);
		juwa4.mirror = true;
		setRotation(juwa4, 0.4886922F, 0F, 0F);
		juwa5 = new ModelRenderer(this, 46, 0);
		juwa5.addBox(-0.5F, -8.7F, 1.5F, 1, 3, 3);
		juwa5.setRotationPoint(0F, 16F, 0F);
		juwa5.setTextureSize(64, 32);
		juwa5.mirror = true;
		setRotation(juwa5, 0.7853982F, 0.1396263F, -0.1396263F);
		juwa6 = new ModelRenderer(this, 46, 0);
		juwa6.addBox(-0.5F, -8.7F, 1.5F, 1, 3, 3);
		juwa6.setRotationPoint(0F, 16F, 0F);
		juwa6.setTextureSize(64, 32);
		juwa6.mirror = true;
		setRotation(juwa6, 0.7853982F, -0.1396263F, 0.1396263F);
		dial1 = new ModelRenderer(this, 33, 7);
		dial1.addBox(-3F, 3F, 1.5F, 6, 6, 1);
		dial1.setRotationPoint(0F, 16F, 0F);
		dial1.setTextureSize(64, 32);
		dial1.mirror = true;
		setRotation(dial1, -0.8726646F, 0F, 0F);
		dial2 = new ModelRenderer(this, 48, 7);
		dial2.addBox(-3F, 3F, 1.4F, 6, 6, 0);
		dial2.setRotationPoint(0F, 16F, 0F);
		dial2.setTextureSize(64, 32);
		dial2.mirror = true;
		setRotation(dial2, -0.8726646F, 0F, 0F);
		tuwa1 = new ModelRenderer(this, 33, 16);
		tuwa1.addBox(-5F, -3F, -1F, 2, 5, 2);
		tuwa1.setRotationPoint(0F, 15F, 0F);
		tuwa1.setTextureSize(64, 32);
		tuwa1.mirror = true;
		setRotation(tuwa1, 0F, 0F, 0F);
		tuwa2 = new ModelRenderer(this, 42, 16);
		tuwa2.addBox(-5.5F, 1F, -1.5F, 3, 1, 3);
		tuwa2.setRotationPoint(0F, 16F, 0F);
		tuwa2.setTextureSize(64, 32);
		tuwa2.mirror = true;
		setRotation(tuwa2, 0F, 0F, 0F);
		tuwa3 = new ModelRenderer(this, 42, 21);
		tuwa3.addBox(-6F, 2F, -2F, 4, 1, 4);
		tuwa3.setRotationPoint(0F, 16F, 0F);
		tuwa3.setTextureSize(64, 32);
		tuwa3.mirror = true;
		setRotation(tuwa3, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		dodai1.render(f5);
		dodai2.render(f5);
		tou1.render(f5);
		tou2.render(f5);
		tou3.render(f5);
		juwa1.render(f5);
		juwa2.render(f5);
		juwa3.render(f5);
		juwa4.render(f5);
		juwa5.render(f5);
		juwa6.render(f5);
		dial1.render(f5);
		dial2.render(f5);
		tuwa1.render(f5);
		tuwa2.render(f5);
		tuwa3.render(f5);
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
