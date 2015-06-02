package mods.defeatedcrow.client.entity;

import mods.defeatedcrow.client.model.model.ModelSteak;
import mods.defeatedcrow.common.entity.edible.PlaceableSteak;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSteakEntity extends Render {

	private static final ResourceLocation steakTex = new ResourceLocation("defeatedcrow:textures/entity/steak.png");
	private static final ResourceLocation chickenTex = new ResourceLocation(
			"defeatedcrow:textures/entity/roastedchicken.png");
	private static final ResourceLocation hamaguriTex = new ResourceLocation(
			"defeatedcrow:textures/entity/hamaguri.png");

	private static final ResourceLocation plateTex = new ResourceLocation("defeatedcrow:textures/entity/steakplate.png");
	private static final ResourceLocation woodTex = new ResourceLocation("textures/blocks/planks_oak.png");
	private final RenderBlocks renderer = new RenderBlocks();

	/** instance of ModelBoat for rendering */
	protected ModelSteak model;

	public RenderSteakEntity() {
		this.shadowSize = 0.5F;
		this.model = new ModelSteak();
	}

	/**
	 * The render method used in RenderBoat that renders the boat model.
	 */
	public void render(PlaceableSteak entity, double posX, double posY, double posZ, float round, float yaw) {
		ModelSteak model = this.model;
		byte l = (byte) entity.getItemMetadata();

		if (l == 0 || l == 1) {
			this.bindTexture(steakTex);
		} else if (l == 2) {
			this.bindTexture(chickenTex);
		} else {
			this.bindTexture(hamaguriTex);
		}

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(2.0F, 2.0F, 2.0F, 1.0F);
		GL11.glTranslatef((float) posX, (float) posY + 1.20F, (float) posZ);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glRotatef(round, 0.0F, 1.0F, 0.0F);
		model.render((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, l);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();

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

	protected ResourceLocation getMelonTextures(PlaceableSteak par1Entity) {
		return steakTex;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return this.getMelonTextures((PlaceableSteak) par1Entity);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		this.render((PlaceableSteak) par1Entity, par2, par4, par6, par8, par9);
	}
}
