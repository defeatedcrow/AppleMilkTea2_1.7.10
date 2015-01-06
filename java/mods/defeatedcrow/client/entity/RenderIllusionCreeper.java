package mods.defeatedcrow.client.entity;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import mods.defeatedcrow.common.entity.dummy.EntityIllusionMobs;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderIllusionCreeper extends Render{
	
	private static final ResourceLocation texture = new ResourceLocation("textures/entity/creeper/creeper.png");

	private ModelBase creeperModel;
	
	 public RenderIllusionCreeper()
	 {
	        this.shadowSize = 0.5F;
	        this.creeperModel = new ModelCreeper(0.0F);
	 }
	
	 public void render(EntityIllusionMobs entity, double par2, double par4, double par6, float par8, float par9)
	 {
	    	ModelCreeper model = (ModelCreeper) this.creeperModel;

	        this.bindTexture(texture);
	        
	        GL11.glPushMatrix();
	        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
	        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	        GL11.glTranslatef((float)par2, (float)par4 + 0.5F, (float)par6);
	        GL11.glScalef(1.0F, -1.0F, -1.0F);
	        GL11.glRotatef(par8, 0.0F, 1.0F, 0.0F);
	        model.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
	        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
	        GL11.glPopMatrix();
	        
	 }

	 protected ResourceLocation getThisTextures(EntityIllusionMobs par1Entity)
	 {
		 	return texture;
	 }

	 @Override
	 protected ResourceLocation getEntityTexture(Entity par1Entity)
	 {
	     	return this.getThisTextures((EntityIllusionMobs)par1Entity);
	 }

	 @Override
	 public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	 {
	     	this.render((EntityIllusionMobs)par1Entity, par2, par4, par6, par8, par9);
	}

}
