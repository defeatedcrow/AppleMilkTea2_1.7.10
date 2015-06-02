package mods.defeatedcrow.client.entity;

import mods.defeatedcrow.client.model.model.ModelSandwich;
import mods.defeatedcrow.common.entity.edible.PlaceableSandwich;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSandwichEntity extends Render {

	private static final ResourceLocation thisTex = new ResourceLocation("defeatedcrow:textures/entity/sandwich.png");

	/** instance of ModelBoat for rendering */
	protected ModelSandwich model;

	public RenderSandwichEntity() {
		this.shadowSize = 0.5F;
		this.model = new ModelSandwich();
	}

	/**
	 * The render method used in RenderBoat that renders the boat model.
	 */
	public void render(PlaceableSandwich entity, double posX, double posY, double posZ, float round, float yaw) {
		ModelSandwich model = this.model;
		byte l = (byte) entity.getItemMetadata();

		this.bindTexture(thisTex);

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(2.0F, 2.0F, 2.0F, 1.0F);
		GL11.glTranslatef((float) posX, (float) posY + 1.2F, (float) posZ);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glRotatef(round, 0.0F, 1.0F, 0.0F);
		model.render((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, l);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();

	}

	protected ResourceLocation getMelonTextures(PlaceableSandwich par1Entity) {
		return thisTex;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return this.getMelonTextures((PlaceableSandwich) par1Entity);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		this.render((PlaceableSandwich) par1Entity, par2, par4, par6, par8, par9);
	}
}
