package mods.defeatedcrow.client.entity;

import mods.defeatedcrow.client.model.model.ModelCocktail;
import mods.defeatedcrow.common.block.edible.BlockCocktailSP;
import mods.defeatedcrow.common.block.edible.BlockCocktailSP.DecorationType;
import mods.defeatedcrow.common.block.edible.BlockCocktailSP.ModelType;
import mods.defeatedcrow.common.entity.edible.PlaceableCocktailSP;
import mods.defeatedcrow.handler.Util;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCocktailSPEntity extends Render {

	private static final ResourceLocation cocktailTex = new ResourceLocation(
			"defeatedcrow:textures/entity/cocktail.png");
	private ModelCocktail model = new ModelCocktail();

	public RenderCocktailSPEntity() {
		this.shadowSize = 0.3F;
		this.model = new ModelCocktail();
	}

	/**
	 * The render method used in RenderBoat that renders the boat model.
	 */
	public void render(PlaceableCocktailSP entity, double posX, double posY, double posZ, float round, float yaw) {
		ModelCocktail model = this.model;
		byte l = (byte) entity.getItemMetadata();
		float size = Util.getCupScale();
		float y = (float) posY + 1.55F * size - 0.10F * size;

		ModelType type = BlockCocktailSP.getGlassType(l);
		DecorationType deco = BlockCocktailSP.getDecoType(l);

		float[] color = BlockCocktailSP.getColorPropertySP(l);

		String innerTexPass = "defeatedcrow:textures/blocks/contents_cocktailbase.png";
		ResourceLocation innerTex = new ResourceLocation(innerTexPass);
		this.bindTexture(innerTex);

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glColor4f(color[0], color[1], color[2], color[3]);
		GL11.glTranslatef((float) posX, (float) y, (float) posZ);
		GL11.glScalef(size, size, size);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glRotatef(round, 0.0F, 1.0F, 0.0F);
		model.renderInnerSP((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, type);

		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();

		this.bindTexture(cocktailTex);

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef((float) posX, (float) y, (float) posZ);
		GL11.glScalef(size, size, size);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glRotatef(round, 0.0F, 1.0F, 0.0F);
		model.renderDecoSP((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, deco);

		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
		GL11.glTranslatef((float) posX, (float) y, (float) posZ);
		GL11.glScalef(size, size, size);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glRotatef(round, 0.0F, 1.0F, 0.0F);
		model.renderGlassSP((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, type);

		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();

	}

	protected ResourceLocation getMelonTextures(PlaceableCocktailSP par1Entity) {
		return cocktailTex;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return this.getMelonTextures((PlaceableCocktailSP) par1Entity);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		this.render((PlaceableCocktailSP) par1Entity, par2, par4, par6, par8, par9);
	}
}
