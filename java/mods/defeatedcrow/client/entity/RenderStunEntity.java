package mods.defeatedcrow.client.entity;

import mods.defeatedcrow.common.entity.dummy.EntityStunEffect;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderStunEntity extends Render {

	// 仮
	private static final ResourceLocation tex = new ResourceLocation("defeatedcrow:textures/entity/kinoko_red.png");

	@Override
	public void doRender(Entity par1Entity, double posX, double posY, double posZ, float round, float yaw) {
		// なにもしない
	}

	protected ResourceLocation getStunTextures(EntityStunEffect par1Entity) {
		return tex;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return this.getStunTextures((EntityStunEffect) entity);
	}

}
