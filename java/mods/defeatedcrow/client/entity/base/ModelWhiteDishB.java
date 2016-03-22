package mods.defeatedcrow.client.entity.base;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelWhiteDishB extends ModelBase {
	// fields
	ModelRenderer base;
	ModelRenderer b;
	ModelRenderer l;
	ModelRenderer ff;
	ModelRenderer r;
	ModelRenderer bl;
	ModelRenderer fl;
	ModelRenderer fr;
	ModelRenderer br;

	public ModelWhiteDishB() {
		textureWidth = 64;
		textureHeight = 32;
		float f = 3.141593F / 360.0F;

		base = new ModelRenderer(this, 0, 0);
		base.addBox(-3F, 1F, -3F, 6, 1, 6);
		base.setRotationPoint(0F, 22F, 0F);
		base.setTextureSize(64, 32);
		base.mirror = true;
		setRotation(base, 0F, 0F, 0F);
		b = new ModelRenderer(this, 0, 8);
		b.addBox(-3F, 1.3F, 2.5F, 6, 1, 4);
		b.setRotationPoint(0F, 22F, 0F);
		b.setTextureSize(64, 32);
		b.mirror = true;
		setRotation(b, 0.1396263F, 0F, 0F);
		l = new ModelRenderer(this, 0, 8);
		l.addBox(-3F, 1.2F, 2.5F, 6, 1, 4);
		l.setRotationPoint(0F, 22F, 0F);
		l.setTextureSize(64, 32);
		l.mirror = true;
		setRotation(l, 0.1396263F, 1.570796F, 0F);
		ff = new ModelRenderer(this, 0, 8);
		ff.addBox(-3F, 1.2F, 2.5F, 6, 1, 4);
		ff.setRotationPoint(0F, 22F, 0F);
		ff.setTextureSize(64, 32);
		ff.mirror = true;
		setRotation(ff, 0.1396263F, 3.141593F, 0F);
		r = new ModelRenderer(this, 0, 8);
		r.addBox(-3F, 1.3F, 2.5F, 6, 1, 4);
		r.setRotationPoint(0F, 22F, 0F);
		r.setTextureSize(64, 32);
		r.mirror = true;
		setRotation(r, 0.1396263F, 4.712389F, 0F);
		bl = new ModelRenderer(this, 0, 14);
		bl.addBox(2.5F, 1.5F, 2.5F, 4, 1, 4);
		bl.setRotationPoint(0F, 22F, 0F);
		bl.setTextureSize(64, 32);
		bl.mirror = true;
		setRotation(bl, 0.1396263F, 0F, -0.1396263F);
		fl = new ModelRenderer(this, 0, 14);
		fl.addBox(2.5F, 0.4F, 2.5F, 4, 1, 4);
		fl.setRotationPoint(0F, 22F, -9F);
		fl.setTextureSize(64, 32);
		fl.mirror = true;
		setRotation(fl, -12.0F * f, f, -12.0F * f);
		fr = new ModelRenderer(this, 0, 14);
		fr.addBox(2.5F, 1.5F, 2.5F, 4, 1, 4);
		fr.setRotationPoint(0F, 22F, 0F);
		fr.setTextureSize(64, 32);
		fr.mirror = true;
		setRotation(fr, 0.1396263F, 3.141593F, 0.1396263F);
		br = new ModelRenderer(this, 0, 14);
		br.addBox(2.5F, 0.4F, 2.5F, 4, 1, 4);
		br.setRotationPoint(-9F, 22F, 0F);
		br.setTextureSize(64, 32);
		br.mirror = true;
		setRotation(br, 12.0F * f, f, 12.0F * f);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		base.render(f5);
		b.render(f5);
		l.render(f5);
		ff.render(f5);
		r.render(f5);
		bl.render(f5);
		fl.render(f5);
		fr.render(f5);
		br.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}
