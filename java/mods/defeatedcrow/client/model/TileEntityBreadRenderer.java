package mods.defeatedcrow.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.defeatedcrow.common.tile.TileBread;
import mods.defeatedcrow.handler.Util;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

@SideOnly(Side.CLIENT)
public class TileEntityBreadRenderer extends TileEntitySpecialRenderer
{
    private static final ResourceLocation BreadTex = new ResourceLocation(Util.getEntityTexturePassNoAlt() + "breadbasket.png");
    private static final ResourceLocation BottleTex = new ResourceLocation(Util.getEntityTexturePassNoAlt() + "bottlebasket.png");
    public static TileEntityBreadRenderer BreadRenderer;
    private ModelBreads breadModel = new ModelBreads();

    public void renderTileEntityBreadAt(TileBread par1TileBread, double par2, double par4, double par6, float par8)
    {
        this.setRotation((float)par2, (float)par4, (float)par6, par1TileBread.blockMetadata, par1TileBread);
    }

    /**
     * Associate a TileEntityRenderer with this TileEntitySpecialRenderer
     */
    public void setTileEntityRenderer(TileEntityRendererDispatcher par1TileEntityRenderer)
    {
        super.func_147497_a(par1TileEntityRenderer);
        BreadRenderer = this;
    }

    public void setRotation(float par1, float par2, float par3, int par4, TileBread tile)
    {
        ModelBreads modelBread = this.breadModel;
        byte l = (byte)tile.getBlockMetadata();
        
        GL11.glPushMatrix();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glTranslatef((float)par1 + 0.5F, (float)par2 + 1.5F, (float)par3 + 0.5F);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);
        
        if (l < 6) {
        	this.bindTexture(BreadTex);
            this.breadModel.render((Entity)null, 0.0F, 0.0F, 0.0F, l, 0.0F, 0.0625F);
        }
        else {
        	this.bindTexture(BottleTex);
            this.breadModel.renderBottle((Entity)null, 0.0F, 0.0F, 0.0F, l, 0.0F, 0.0625F);
        }
        
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
    }

    public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
    {
        this.renderTileEntityBreadAt((TileBread)par1TileEntity, par2, par4, par6, par8);
    }
}
