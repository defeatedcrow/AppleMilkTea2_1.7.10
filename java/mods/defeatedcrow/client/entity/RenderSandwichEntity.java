package mods.defeatedcrow.client.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.defeatedcrow.client.model.*;
import mods.defeatedcrow.client.model.model.ModelSandwich;
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
public class RenderSandwichEntity extends Render
{
	private static final ResourceLocation thisTex = new ResourceLocation("defeatedcrow:textures/entity/sandwich.png");
    
    /** instance of ModelBoat for rendering */
    protected ModelSandwich model;

    public RenderSandwichEntity()
    {
        this.shadowSize = 0.5F;
        this.model = new ModelSandwich();
    }

    /**
     * The render method used in RenderBoat that renders the boat model.
     */
    public void render(PlaceableSandwich entity, double par2, double par4, double par6, float par8, float par9)
    {
    	ModelSandwich model = this.model;
        byte l = (byte)entity.getItemMetadata();

        this.bindTexture(thisTex);
        
        GL11.glPushMatrix();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glColor4f(2.0F, 2.0F, 2.0F, 1.0F);
        GL11.glTranslatef((float)par2, (float)par4 + 1.3F, (float)par6);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        GL11.glRotatef(par8, 0.0F, 1.0F, 0.0F);
        model.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, l);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
        
    }

    protected ResourceLocation getMelonTextures(PlaceableSandwich par1Entity)
    {
        return thisTex;
    }

    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.getMelonTextures((PlaceableSandwich)par1Entity);
    }

    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.render((PlaceableSandwich)par1Entity, par2, par4, par6, par8, par9);
    }
}
