package mods.defeatedcrow.client.model.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.defeatedcrow.client.model.model.ModelInsenceBase;
import mods.defeatedcrow.common.tile.TileInsenceBase;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

@SideOnly(Side.CLIENT)
public class TileEntityInsenceBaseRenderer extends TileEntitySpecialRenderer
{
    private static final ResourceLocation iceTex = new ResourceLocation("defeatedcrow:textures/entity/insencebase.png");
    public static TileEntityInsenceBaseRenderer thisRenderer;
    private ModelInsenceBase iceModel = new ModelInsenceBase();

    public void renderTileEntityIceAt(TileInsenceBase par1Tile, double par2, double par4, double par6, float par8)
    {
        this.setRotation(par1Tile, (float)par2, (float)par4, (float)par6);
    }

    /**
     * Associate a TileEntityRenderer with this TileEntitySpecialRenderer
     */
    public void setTileEntityRenderer(TileEntityRendererDispatcher par1TileEntityRenderer)
    {
        super.func_147497_a(par1TileEntityRenderer);
        thisRenderer = this;
    }

    public void setRotation(TileInsenceBase par0Tile, float par1, float par2, float par3)
    {
        ModelInsenceBase model = this.iceModel;
        boolean active = par0Tile.getActive();
        boolean hasItem = false;
        if (par0Tile.hasItem()) hasItem = true;

        this.bindTexture(iceTex);
        GL11.glPushMatrix();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glTranslatef((float)par1 + 0.5F, (float)par2 + 1.5F, (float)par3 + 0.5F);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);
        model.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
        
        if (hasItem)
        {
        	GL11.glPushMatrix();
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glTranslatef((float)par1 + 0.5F, (float)par2 + 1.5F, (float)par3 + 0.5F);
            GL11.glScalef(1.0F, -1.0F, -1.0F);
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.9F);
            model.renderCone((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            GL11.glPopMatrix(); 
        	
        	if (active)
        	{
        		GL11.glPushMatrix();
                GL11.glEnable(GL12.GL_RESCALE_NORMAL);
                
                GL11.glColor4f(2.0F, 2.0F, 2.0F, 1.0F);
                GL11.glTranslatef((float)par1 + 0.5F, (float)par2 + 1.5F, (float)par3 + 0.5F);
                GL11.glScalef(1.0F, -1.0F, -1.0F);
                GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.9F);
                model.renderGlow((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
                
                GL11.glDisable(GL12.GL_RESCALE_NORMAL);
                GL11.glPopMatrix(); 
        	}
        }
    }

    public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
    {
        this.renderTileEntityIceAt((TileInsenceBase)par1TileEntity, par2, par4, par6, par8);
    }
}
