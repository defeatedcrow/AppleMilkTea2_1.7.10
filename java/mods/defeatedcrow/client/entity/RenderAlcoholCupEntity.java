package mods.defeatedcrow.client.entity;

import mods.defeatedcrow.client.model.model.ModelAlcoholCup;
import mods.defeatedcrow.common.block.edible.BlockAlcoholCup;
import mods.defeatedcrow.common.entity.edible.PlaceableAlcoholCup;
import mods.defeatedcrow.handler.Util;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderAlcoholCupEntity extends Render {

	private static final ResourceLocation cocktailTex = new ResourceLocation(
			"defeatedcrow:textures/entity/cocktail.png");
	private static final ResourceLocation atukanTex = new ResourceLocation("defeatedcrow:textures/entity/atukan.png");
	private ModelAlcoholCup model = new ModelAlcoholCup();

	public RenderAlcoholCupEntity() {
		this.shadowSize = 0.3F * Util.getCupSize();
		this.model = new ModelAlcoholCup();
	}

	/**
	 * The render method used in RenderBoat that renders the boat model.
	 */
	public void render(PlaceableAlcoholCup entity, double posX, double posY, double posZ, float round, float yaw) {
		ModelAlcoholCup model = this.model;
		byte l = (byte) entity.getItemMetadata();
		byte type = 0;
		if (l == 2 || l == 12 || l == 13) {
			type = 1;
		}
		float size = Util.getCupScale();
		float y = (float) posY + 1.55F * size - 0.10F * size;

		if (l == 0) {
			this.bindTexture(atukanTex);

			GL11.glPushMatrix();
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);

			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glTranslatef((float) posX, (float) posY + 1.5F * size - 0.1F, (float) posZ);
			GL11.glScalef(size, size, size);
			GL11.glScalef(1.0F, -1.0F, -1.0F);
			GL11.glRotatef(round, 0.0F, 1.0F, 0.0F);
			model.renderAtukan((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glPopMatrix();
		} else {

			// 中身
			String innerTexPass = "defeatedcrow:textures/blocks/contents" + BlockAlcoholCup.contents[l] + ".png";
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
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

			GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
			GL11.glTranslatef((float) posX, (float) y, (float) posZ);
			GL11.glScalef(size, size, size);
			GL11.glScalef(1.0F, -1.0F, -1.0F);
			GL11.glRotatef(round, 0.0F, 1.0F, 0.0F);
			model.renderGlass((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, type);
			model.renderIce((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, type);

			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glPopMatrix();
		}

	}

	protected ResourceLocation getMelonTextures(PlaceableAlcoholCup par1Entity) {
		return cocktailTex;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return this.getMelonTextures((PlaceableAlcoholCup) par1Entity);
	}

	@Override
	public void doRender(Entity par1Entity, double x, double y, double z, float round, float yaw) {
		this.render((PlaceableAlcoholCup) par1Entity, x, y, z, round, yaw);
	}
}
