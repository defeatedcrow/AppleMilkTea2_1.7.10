package mods.defeatedcrow.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTart extends ModelBase{
	
	ModelRenderer plate;
    ModelRenderer sideB;
    ModelRenderer sideF;
    ModelRenderer sideL;
    ModelRenderer sideR;
    
    ModelRenderer main;
    ModelRenderer main2;
    
    ModelRenderer crop1;
    ModelRenderer crop2;
    ModelRenderer crop3;
  
  public ModelTart()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      plate = new ModelRenderer(this, 0, 0);
      plate.addBox(-7F, 0F, -7F, 14, 1, 14);
      plate.setRotationPoint(0F, 23F, 0F);
      plate.setTextureSize(64, 32);
      plate.mirror = true;
      setRotation(plate, 0F, 0F, 0F);
      sideB = new ModelRenderer(this, 0, 0);
      sideB.addBox(-6F, 0F, -5F, 1, 5, 10);
      sideB.setRotationPoint(0F, 18F, 0F);
      sideB.setTextureSize(64, 32);
      sideB.mirror = true;
      setRotation(sideB, 0F, 1.570796F, -0.0F);
      sideF = new ModelRenderer(this, 0, 0);
      sideF.addBox(-6F, 0F, -5F, 1, 5, 10);
      sideF.setRotationPoint(0F, 18F, 0F);
      sideF.setTextureSize(64, 32);
      sideF.mirror = true;
      setRotation(sideF, 0F, -1.570796F, -0.0F);
      sideL = new ModelRenderer(this, 0, 0);
      sideL.addBox(-6F, 0F, -5F, 1, 5, 10);
      sideL.setRotationPoint(0F, 18F, 0F);
      sideL.setTextureSize(64, 32);
      sideL.mirror = true;
      setRotation(sideL, 0F, 0F, -0.0F);
      sideR = new ModelRenderer(this, 0, 0);
      sideR.addBox(-6F, 0F, -5F, 1, 5, 10);
      sideR.setRotationPoint(0F, 18F, 0F);
      sideR.setTextureSize(64, 32);
      sideR.mirror = true;
      setRotation(sideR, 0F, 3.141593F, -0.0F);
      main = new ModelRenderer(this, 24, 0);
      main.addBox(-5F, 0F, -5F, 10, 3, 10);
      main.setRotationPoint(0F, 19F, 0F);
      main.setTextureSize(64, 32);
      main.mirror = true;
      setRotation(main, 0F, 0F, 0F);
      main2 = new ModelRenderer(this, 24, 13);
      main2.addBox(-5F, 0F, -5F, 10, 3, 10);
      main2.setRotationPoint(0F, 19F, 0F);
      main2.setTextureSize(64, 32);
      main2.mirror = true;
      setRotation(main2, 0F, 0F, 0F);
      crop1 = new ModelRenderer(this, 0, 0);
      crop1.addBox(0.5F, 0F, 0F, 1, 1, 1);
      crop1.setRotationPoint(0F, 18F, 0F);
      crop1.setTextureSize(64, 32);
      crop1.mirror = true;
      setRotation(crop1, 0F, 0F, 0F);
      crop2 = new ModelRenderer(this, 0, 0);
      crop2.addBox(-1F, 0F, -1.5F, 1, 1, 1);
      crop2.setRotationPoint(0F, 18F, 0F);
      crop2.setTextureSize(64, 32);
      crop2.mirror = true;
      setRotation(crop2, 0F, 0.5205006F, 0F);
      crop3 = new ModelRenderer(this, 0, 0);
      crop3.addBox(-1.5F, 0F, 0.5F, 1, 1, 1);
      crop3.setRotationPoint(0F, 18F, 0F);
      crop3.setTextureSize(64, 32);
      crop3.mirror = true;
      setRotation(crop3, 0F, 0.2602503F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, byte b0)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    sideB.render(f5);
    sideF.render(f5);
    sideL.render(f5);
    sideR.render(f5);
    if (b0 == 0)
    {
    	main.render(f5);
    }
    else
    {
    	main2.render(f5);
    }
  }
  
  public void renderCrops(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, byte b0)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    if (b0 != 0)
    {
    	crop1.render(f5);
        crop2.render(f5);
        crop3.render(f5);
    }
  }
  
  public void renderPlate(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, byte b0)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    plate.render(f5);
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
    this.plate.rotateAngleY = f3 / (180F / (float)Math.PI);
    this.sideF.rotateAngleY = -1.570796F + f3 / (180F / (float)Math.PI);
    this.sideB.rotateAngleY = 1.570796F + f3 / (180F / (float)Math.PI);
    this.sideR.rotateAngleY = 3.141593F + f3 / (180F / (float)Math.PI);
    this.sideL.rotateAngleY = f3 / (180F / (float)Math.PI);
    this.main.rotateAngleY = f3 / (180F / (float)Math.PI);
    this.main2.rotateAngleY = f3 / (180F / (float)Math.PI);
    this.crop1.rotateAngleY = f3 / (180F / (float)Math.PI);
    this.crop2.rotateAngleY = f3 / (180F / (float)Math.PI);
    this.crop3.rotateAngleY = f3 / (180F / (float)Math.PI);
  }

}
