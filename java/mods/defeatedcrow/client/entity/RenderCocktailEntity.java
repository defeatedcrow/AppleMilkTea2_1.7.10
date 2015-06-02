package mods.defeatedcrow.client.entity;

import mods.defeatedcrow.client.model.model.ModelCocktail;
import mods.defeatedcrow.common.block.edible.BlockCocktail;
import mods.defeatedcrow.common.entity.edible.PlaceableCocktail;
import mods.defeatedcrow.handler.Util;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCocktailEntity extends Render {

	private static final ResourceLocation cocktailTex = new ResourceLocation(
			"defeatedcrow:textures/entity/cocktail.png");
	private ModelCocktail model = new ModelCocktail();

	public RenderCocktailEntity() {
		this.shadowSize = 0.3F;
		this.model = new ModelCocktail();
	}

	/**
	 * The render method used in RenderBoat that renders the boat model.
	 */
	public void render(PlaceableCocktail entity, double posX, double posY, double posZ, float round, float yaw) {
		ModelCocktail model = this.model;
		byte l = (byte) entity.getItemMetadata();
		float size = Util.getCupScale();
		float y = (float) posY + 1.55F * size - 0.10F * size;

		byte type = 0;// 0:ロング、1:ショート、2:ワイングラス/4:フローズン
		if (l == 0 || l == 1)
			type = 5;
		else if (l < 5 || l == 14 || l == 15)
			type = 1;
		else if (l == 10)
			type = 2;
		else if (l == 6)
			type = 6;

		byte deco = 0;// 0:なし、1:レモン、2:ライム、3:パイン、4:アップル
		if (l == 5 || l == 7 || l == 9 || l == 12)
			deco = 2;
		else if (l == 0 || l == 3 || l == 8)
			deco = 1;
		else if (l == 6)
			deco = 3;
		else if (l == 14)
			deco = 4;

		String innerTexPass = "defeatedcrow:textures/blocks/contents" + BlockCocktail.contents[l] + ".png";
		if (l == 7) {
			innerTexPass = "defeatedcrow:textures/blocks/contents_lemon_gradient2.png";
		}
		ResourceLocation innerTex = new ResourceLocation(innerTexPass);
		this.bindTexture(innerTex);

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.8F);
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

	protected ResourceLocation getMelonTextures(PlaceableCocktail par1Entity) {
		return cocktailTex;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return this.getMelonTextures((PlaceableCocktail) par1Entity);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		this.render((PlaceableCocktail) par1Entity, par2, par4, par6, par8, par9);
	}
}
