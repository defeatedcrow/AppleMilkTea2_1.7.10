package mods.defeatedcrow.client.entity;

import mods.defeatedcrow.client.model.model.ModelCocktail;
import mods.defeatedcrow.common.block.edible.BlockCocktail2;
import mods.defeatedcrow.common.entity.edible.PlaceableCocktail2;
import mods.defeatedcrow.handler.Util;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCocktail2Entity extends Render {

	private static final ResourceLocation cocktailTex = new ResourceLocation(
			"defeatedcrow:textures/entity/cocktail.png");
	private ModelCocktail model = new ModelCocktail();

	public RenderCocktail2Entity() {
		this.shadowSize = 0.3F;
		this.model = new ModelCocktail();
	}

	/**
	 * The render method used in RenderBoat that renders the boat model.
	 */
	public void render(PlaceableCocktail2 entity, double posX, double posY, double posZ, float round, float yaw) {
		ModelCocktail model = this.model;
		byte l = (byte) entity.getItemMetadata();
		if (l > 9)
			l = 9;
		float size = Util.getCupScale();
		float y = (float) posY + 1.55F * size - 0.10F * size;

		byte type = 0;// 0:ロング、1:ショート、2:ワイングラス/4:フローズン
		if (l == 6 || l == 7)
			type = 1;
		else if (l == 1 || l == 2 || l == 9)
			type = 2;

		byte deco = 0;// 0:なし
		if (l == 9)
			deco = 5;

		String innerTexPass = "defeatedcrow:textures/blocks/contents" + BlockCocktail2.contents[l] + ".png";
		ResourceLocation innerTex = new ResourceLocation(innerTexPass);
		this.bindTexture(innerTex);

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glColor4f(1.5F, 1.5F, 1.5F, 1.0F);
		GL11.glTranslatef((float) posX, (float) y, (float) posZ);
		GL11.glScalef(size, size, size);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glRotatef(round, 0.0F, 1.0F, 0.0F);
		model.renderInner((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, type);

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
		model.renderDeco((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, deco);

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
		model.renderGlass((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, type);

		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();

	}

	protected ResourceLocation getMelonTextures(PlaceableCocktail2 par1Entity) {
		return cocktailTex;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return this.getMelonTextures((PlaceableCocktail2) par1Entity);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		this.render((PlaceableCocktail2) par1Entity, par2, par4, par6, par8, par9);
	}
}
