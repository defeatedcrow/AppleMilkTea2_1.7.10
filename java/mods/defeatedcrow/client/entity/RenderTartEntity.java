package mods.defeatedcrow.client.entity;

import mods.defeatedcrow.client.model.model.ModelTart;
import mods.defeatedcrow.common.entity.edible.PlaceableTart;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderTartEntity extends Render {

	private static final ResourceLocation tartTex = new ResourceLocation("defeatedcrow:textures/entity/tart.png");
	private static final ResourceLocation plateTex = new ResourceLocation("defeatedcrow:textures/entity/tartbase.png");
	private static final ResourceLocation mousseTex = new ResourceLocation(
			"defeatedcrow:textures/entity/moussecake.png");

	/** instance of ModelBoat for rendering */
	protected ModelTart model;

	public RenderTartEntity() {
		this.shadowSize = 0.5F;
		this.model = new ModelTart();
	}

	/**
	 * The render method used in RenderBoat that renders the boat model.
	 */
	public void render(PlaceableTart entity, double posX, double posY, double posZ, float round, float yaw) {
		ModelTart model = this.model;
		byte l = (byte) entity.getItemMetadata();

		if (l < 2) {
			this.bindTexture(tartTex);

			GL11.glPushMatrix();
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glColor4f(2.0F, 2.0F, 2.0F, 1.0F);
			GL11.glTranslatef((float) posX, (float) posY + 1.25F, (float) posZ);
			GL11.glScalef(1.0F, -1.0F, -1.0F);
			GL11.glRotatef(round, 0.0F, 1.0F, 0.0F);
			model.render((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, l);
			if (l == 1) {
				model.renderCrops((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, l);
			}
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glPopMatrix();
		} else {
			String mousse1 = "defeatedcrow:textures/blocks/contents_cocoa_milk.png";
			ResourceLocation mousseTex1 = new ResourceLocation(mousse1);
			String mousse2 = "defeatedcrow:textures/blocks/contents_milk.png";
			ResourceLocation mousseTex2 = new ResourceLocation(mousse2);

			String mousse3 = "defeatedcrow:textures/blocks/contents_lemon.png";
			if (l == 3) {
				mousse3 = "defeatedcrow:textures/blocks/contents_juice.png";
			}
			ResourceLocation mousseTex3 = new ResourceLocation(mousse3);

			this.bindTexture(mousseTex);

			GL11.glPushMatrix();
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glColor4f(2.0F, 2.0F, 2.0F, 1.0F);
			GL11.glTranslatef((float) posX, (float) posY + 1.25F, (float) posZ);
			GL11.glScalef(1.0F, -1.0F, -1.0F);
			GL11.glRotatef(round, 0.0F, 1.0F, 0.0F);
			model.renderMousseBase((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, l);

			this.bindTexture(mousseTex1);
			model.renderMousse1((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, l);

			this.bindTexture(mousseTex2);
			model.renderMousse2((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, l);

			this.bindTexture(mousseTex3);
			model.renderMousse3((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, l);

			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glPopMatrix();
		}

		// 以下、プレートの部分
		this.bindTexture(plateTex);

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(2.0F, 2.0F, 2.0F, 1.0F);
		GL11.glTranslatef((float) posX, (float) posY + 1.25F, (float) posZ);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glRotatef(round, 0.0F, 1.0F, 0.0F);
		this.model.renderPlate((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, l);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();

	}

	protected ResourceLocation getMelonTextures(PlaceableTart par1Entity) {
		return tartTex;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return this.getMelonTextures((PlaceableTart) par1Entity);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		this.render((PlaceableTart) par1Entity, par2, par4, par6, par8, par9);
	}
}
