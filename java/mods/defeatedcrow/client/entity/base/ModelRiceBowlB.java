package mods.defeatedcrow.client.entity.base;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRiceBowlB extends ModelBase
{
	  //fields
	    ModelRenderer bottom1;
	    ModelRenderer bottom2;
	    ModelRenderer sideL2;
	    ModelRenderer sideR2;
	    ModelRenderer sideF2;
	    ModelRenderer sideB2;
	  
	  public ModelRiceBowlB()
	  {
	    textureWidth = 64;
	    textureHeight = 32;
	    
	      bottom1 = new ModelRenderer(this, 32, 0);
	      bottom1.addBox(0F, 0F, 0F, 4, 1, 4);
	      bottom1.setRotationPoint(-2F, 23F, -2F);
	      bottom1.setTextureSize(64, 32);
	      bottom1.mirror = true;
	      setRotation(bottom1, 0F, 0F, 0F);
	      bottom2 = new ModelRenderer(this, 0, 0);
	      bottom2.addBox(0F, 0F, 0F, 8, 1, 8);
	      bottom2.setRotationPoint(-4F, 22F, -4F);
	      bottom2.setTextureSize(64, 32);
	      bottom2.mirror = true;
	      setRotation(bottom2, 0F, 0F, 0F);
	      sideL2 = new ModelRenderer(this, 0, 10);
	      sideL2.addBox(0F, 0F, 0F, 1, 3, 6);
	      sideL2.setRotationPoint(3F, 19F, -3F);
	      sideL2.setTextureSize(64, 32);
	      sideL2.mirror = true;
	      setRotation(sideL2, 0F, 0F, 0F);
	      sideR2 = new ModelRenderer(this, 14, 10);
	      sideR2.addBox(0F, 0F, 0F, 1, 3, 6);
	      sideR2.setRotationPoint(-4F, 19F, -3F);
	      sideR2.setTextureSize(64, 32);
	      sideR2.mirror = true;
	      setRotation(sideR2, 0F, 0F, 0F);
	      sideF2 = new ModelRenderer(this, 0, 19);
	      sideF2.addBox(0F, 0F, 0F, 8, 3, 1);
	      sideF2.setRotationPoint(-4F, 19F, -4F);
	      sideF2.setTextureSize(64, 32);
	      sideF2.mirror = true;
	      setRotation(sideF2, 0F, 0F, 0F);
	      sideB2 = new ModelRenderer(this, 0, 23);
	      sideB2.addBox(0F, 0F, 0F, 8, 3, 1);
	      sideB2.setRotationPoint(-4F, 19F, 3F);
	      sideB2.setTextureSize(64, 32);
	      sideB2.mirror = true;
	      setRotation(sideB2, 0F, 0F, 0F);
	  }
	  
	  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	  {
	    super.render(entity, f, f1, f2, f3, f4, f5);
	    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	    bottom1.render(f5);
	    bottom2.render(f5);
	    sideL2.render(f5);
	    sideR2.render(f5);
	    sideF2.render(f5);
	    sideB2.render(f5);
	  }
	  
	  private void setRotation(ModelRenderer model, float x, float y, float z)
	  {
	    model.rotateAngleX = x;
	    model.rotateAngleY = y;
	    model.rotateAngleZ = z;
	  }
	  
	  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	  {
	    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	  }
}
