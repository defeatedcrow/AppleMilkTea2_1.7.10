package mods.defeatedcrow.client.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.defeatedcrow.client.model.*;
import mods.defeatedcrow.client.model.model.ModelTeaCup;
import mods.defeatedcrow.common.block.*;
import mods.defeatedcrow.common.block.edible.BlockFilledCup;
import mods.defeatedcrow.common.config.DCsConfig;
import mods.defeatedcrow.common.entity.edible.*;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBoat;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

@SideOnly(Side.CLIENT)
public class RenderCupEntity extends Render
{
	private static final ResourceLocation cupTex = new ResourceLocation("defeatedcrow:textures/blocks/porcelain.png");
	private static final ResourceLocation cupSummerTex = new ResourceLocation("defeatedcrow:textures/blocks/blueglass.png");
    private ModelTeaCup model = new ModelTeaCup();

    public RenderCupEntity()
    {
        this.shadowSize = 0.3F;
        this.model = new ModelTeaCup();
    }

    /**
     * The render method used in RenderBoat that renders the boat model.
     */
    public void render(PlaceableCup1 entity, double par2, double par4, double par6, float par8, float par9)
    {
    	ModelTeaCup model = this.model;
        byte l = (byte)entity.getItemMetadata();

        String innerTexPass = "defeatedcrow:textures/blocks/contents" + BlockFilledCup.contents[l] + ".png";
        ResourceLocation innerTex = new ResourceLocation(innerTexPass);
        this.bindTexture(innerTex);
    	
    	GL11.glPushMatrix();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        
        GL11.glColor4f(1.5F, 1.5F, 1.5F, 0.8F);
        GL11.glTranslatef((float)par2, (float)par4 + 1.25F, (float)par6);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        GL11.glRotatef(par8, 0.0F, 1.0F, 0.0F);
        model.renderContents((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
    	
        if (DCsConfig.useSummerRender)
        {
        	this.bindTexture(cupSummerTex);
        	
        	GL11.glPushMatrix();
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
            GL11.glTranslatef((float)par2, (float)par4 + 1.25F, (float)par6);
            GL11.glScalef(1.0F, -1.0F, -1.0F);
            GL11.glRotatef(par8, 0.0F, 1.0F, 0.0F);
            model.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glPopMatrix();
        }
        else
        {
        	this.bindTexture(cupTex);
        	
        	GL11.glPushMatrix();
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glTranslatef((float)par2, (float)par4 + 1.25F, (float)par6);
            GL11.glScalef(1.0F, -1.0F, -1.0F);
            GL11.glRotatef(par8, 0.0F, 1.0F, 0.0F);
            model.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            GL11.glPopMatrix(); 
        } 
    }

    protected ResourceLocation getMelonTextures(PlaceableCup1 par1Entity)
    {
        return cupTex;
    }

    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.getMelonTextures((PlaceableCup1)par1Entity);
    }

    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.render((PlaceableCup1)par1Entity, par2, par4, par6, par8, par9);
    }
}
