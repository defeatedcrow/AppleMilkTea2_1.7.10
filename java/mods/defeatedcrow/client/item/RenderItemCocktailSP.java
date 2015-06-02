package mods.defeatedcrow.client.item;

import mods.defeatedcrow.client.model.model.ModelCocktail;
import mods.defeatedcrow.common.block.edible.BlockCocktailSP;
import mods.defeatedcrow.common.block.edible.BlockCocktailSP.DecorationType;
import mods.defeatedcrow.common.block.edible.BlockCocktailSP.ModelType;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;

public class RenderItemCocktailSP implements IItemRenderer {

	private static final ResourceLocation resource = new ResourceLocation("defeatedcrow:textures/entity/cocktail.png");
	private ModelCocktail model = new ModelCocktail();

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return canRendering(item, type);
	}

	private boolean canRendering(ItemStack item, ItemRenderType type) {
		switch (type) {
		case ENTITY:
		case EQUIPPED:
		case EQUIPPED_FIRST_PERSON:
		case INVENTORY:
			return true;
		default:
			return false;
		}
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		switch (helper) {
		case INVENTORY_BLOCK:
		case ENTITY_BOBBING:
		case ENTITY_ROTATION:
			return true;
		default:
			return false;
		}
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		if (canRendering(item, type)) {

			GL11.glPushMatrix();
			/*
			 * 描画する種類によって回転, 平行移動を行う.
			 */
			switch (type) {
			case INVENTORY:
				glMatrixForRenderInInventory();
				break;
			case EQUIPPED:
			case EQUIPPED_FIRST_PERSON:
				glMatrixForRenderInEquipped();
				break;
			case ENTITY:
				glMatrixForRenderInEntity();
			default:
				break;
			}
			/*
			 * リソースをTextureMangerにbindし, modelのrenderを呼んで描画する.
			 */
			int l = item.getItemDamage();
			ModelType glass = BlockCocktailSP.getGlassType(l);
			DecorationType deco = BlockCocktailSP.getDecoType(l);
			float[] color = BlockCocktailSP.getColorPropertySP(l);

			String innerTexPass = "defeatedcrow:textures/blocks/contents_cocktailbase.png";
			ResourceLocation innerTex = new ResourceLocation(innerTexPass);
			FMLClientHandler.instance().getClient().getTextureManager().bindTexture(innerTex);

			// GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

			GL11.glColor4f(color[0], color[1], color[2], color[3]);
			model.renderInnerSP((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, glass);

			GL11.glDisable(GL11.GL_BLEND);
			// GL11.glPopMatrix();

			FMLClientHandler.instance().getClient().getTextureManager().bindTexture(resource);

			// GL11.glPushMatrix();

			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			model.renderDecoSP((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, deco);

			// GL11.glPopMatrix();

			// GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

			GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
			model.renderGlassSP((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, glass);

			GL11.glDisable(GL11.GL_BLEND);
			GL11.glPopMatrix();
		}
	}

	/*
	 * インベントリ内での描画位置の調整.
	 */
	private void glMatrixForRenderInInventory() {
		GL11.glRotatef(-180F, 1.0F, 0.0F, 0.0F);
		GL11.glTranslatef(0.0F, -1.0F, 0.0F);
	}

	/*
	 * 装備状態での描画位置の調整.
	 */
	private void glMatrixForRenderInEquipped() {
		GL11.glRotatef(-210F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(0F, 0.0F, 0.0F, 1.0F);
		GL11.glRotatef(-0F, 0.0F, 1.0F, 0.0F);
		GL11.glScalef(1.2F, 1.2F, 1.2F);
		GL11.glTranslatef(0.4F, -1.5F, 0.0F);
	}

	/*
	 * ドロップ状態での描画位置の調整.
	 */
	private void glMatrixForRenderInEntity() {
		GL11.glRotatef(-180F, 1.0F, 0.0F, 0.0F);
		GL11.glTranslatef(0.0F, -1.5F, 0.0F);
	}

}
