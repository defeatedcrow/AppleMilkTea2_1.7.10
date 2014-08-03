package mods.defeatedcrow.client.model.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.defeatedcrow.client.model.model.ModelLargeBottle;
import mods.defeatedcrow.common.tile.TileLargeBottle;
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
public class TileEntityBottleRenderer extends TileEntitySpecialRenderer
{
    private static ResourceLocation bottleTex = new ResourceLocation("defeatedcrow:textures/entity/largebottle.png");
    private static ResourceLocation glassTex = new ResourceLocation("defeatedcrow:textures/blocks/blueglass.png");
    private static ResourceLocation contentsTex = new ResourceLocation("defeatedcrow:textures/blocks/contents_milk.png");
    private static ResourceLocation woodTex = new ResourceLocation("textures/blocks/planks_oak.png");
    private static final String[] type = new String[] {"_shothu", "_sake", "_beer", "_wine", "_gin", "_rum", "_vodka", "_whiskey"};
    private static final String[] canisterType = new String[] {"_milk", "_milk", "_sugar", "_maple", "_juice", "_nuts", "_berryjam"};
    public static TileEntityBottleRenderer bottleRenderer;
    private ModelLargeBottle bottleModel = new ModelLargeBottle();

    public void renderTileEntityBottleAt(TileLargeBottle par1Tile, double par2, double par4, double par6, float par8)
    {
        this.setRotation(par1Tile, (float)par2, (float)par4, (float)par6);
    }

    /**
     * Associate a TileEntityRenderer with this TileEntitySpecialRenderer
     */
    public void setTileEntityRenderer(TileEntityRendererDispatcher par1TileEntityRenderer)
    {
        super.func_147497_a(par1TileEntityRenderer);
        bottleRenderer = this;
    }

    public void setRotation(TileLargeBottle par0Tile, float par1, float par2, float par3)
    {
        ModelLargeBottle model = this.bottleModel;
        byte l = (byte)(par0Tile.getBlockMetadata()& 15);
        short remShort = par0Tile.getRemainShort();
        
        if (l < 8) {
        	String texPass = new String (Util.getEntityTexturePassNoAlt() + "largebottle" + type[l] + ".png");
            bottleTex = new ResourceLocation(texPass);
            this.bindTexture(bottleTex);
            GL11.glPushMatrix();
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glTranslatef((float)par1 + 0.5F, (float)par2 + 1.5F, (float)par3 + 0.5F);
            GL11.glScalef(1.0F, -1.0F, -1.0F);
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);
            model.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            GL11.glPopMatrix();
        }
        else {
            int l2 = l & 7;
            String texPass2 = new String ("defeatedcrow:textures/blocks/contents" + canisterType[(l2)] + ".png");
            contentsTex = new ResourceLocation(texPass2);
            GL11.glPushMatrix();
            this.bindTexture(contentsTex);
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glTranslatef((float)par1 + 1.5F, (float)par2 + 1.5F, (float)par3 + 1.5F);
            
            switch (remShort)
            {
            case 0:
            	GL11.glTranslatef(-1.0F, -1.1175F, -1.0F);
                GL11.glScalef(1.0F, -0.175F, -1.0F);
                break;
            case 1:
            	GL11.glTranslatef(-1.0F, -1.0F, -1.0F);
                GL11.glScalef(1.0F, -0.25F, -1.0F);
                break;
            case 2:
            	GL11.glTranslatef(-1.0F, -0.85F, -1.0F);
                GL11.glScalef(1.0F, -0.375F, -1.0F);
                break;
            case 3:
            	GL11.glTranslatef(-1.0F, -0.675F, -1.0F);
                GL11.glScalef(1.0F, -0.5F, -1.0F);
                break;
            case 4:
            	GL11.glTranslatef(-1.0F, -0.45F, -1.0F);
                GL11.glScalef(1.0F, -0.675F, -1.0F);
                break;
            case 5:
            	GL11.glTranslatef(-1.0F, -0.35F, -1.0F);
                GL11.glScalef(1.0F, -0.75F, -1.0F);
                break;
            case 6:
            	GL11.glTranslatef(-1.0F, -0.175F, -1.0F);
                GL11.glScalef(1.0F, -0.875F, -1.0F);
                break;
            case 7:
            	GL11.glTranslatef(-1.0F, -0.0F, -1.0F);
                GL11.glScalef(1.0F, -1.0F, -1.0F);
                break;
                default:
                	GL11.glTranslatef(-1.0F, -0.0F, -1.0F);
                    GL11.glScalef(1.0F, -1.0F, -1.0F);
                    break;
            }
            
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);
            model.renderContents((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glPopMatrix();
            
            this.bindTexture(glassTex);
        	GL11.glPushMatrix();
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            
            GL11.glPolygonOffset(-1, -1);
            GL11.glEnable(GL11.GL_POLYGON_OFFSET_FILL);
            
            GL11.glEnable(GL11.GL_STENCIL_TEST);
            GL11.glClear(GL11.GL_STENCIL_BUFFER_BIT);
            GL11.glStencilFunc(GL11.GL_NOTEQUAL, 1, 1);
            GL11.glStencilOp(GL11.GL_KEEP, GL11.GL_KEEP, GL11.GL_REPLACE);
            
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
            GL11.glTranslatef((float)par1 + 0.5F, (float)par2 + 1.5F, (float)par3 + 0.5F);
            GL11.glScalef(1.0F, -1.0F, -1.0F);
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);
            model.renderCanister((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            
            GL11.glDisable(GL11.GL_STENCIL_TEST);
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            GL11.glDisable(GL11.GL_POLYGON_OFFSET_FILL);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glPopMatrix();
            
            this.bindTexture(woodTex);
            GL11.glPushMatrix();
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glTranslatef((float)par1 + 0.5F, (float)par2 + 1.5F, (float)par3 + 0.5F);
            GL11.glScalef(1.0F, -1.0F, -1.0F);
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);
            model.renderCanisterCap((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            GL11.glPopMatrix();
        }  
    }

    public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
    {
        this.renderTileEntityBottleAt((TileLargeBottle)par1TileEntity, par2, par4, par6, par8);
    }
}
