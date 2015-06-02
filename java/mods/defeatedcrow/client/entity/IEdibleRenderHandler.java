package mods.defeatedcrow.client.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * EdibleEntityのレンダ―モデルやテクスチャパスを返すインターフェイス。
 * ボツにつき現在不使用。
 */
@Deprecated
public interface IEdibleRenderHandler {

	ItemStack getItem();

	@SideOnly(Side.CLIENT)
	Class<? extends ModelBase> getModel();

	String getEntityTex(int meta);

	String getEntityGlowTex(int meta);

	String getEntityClearTex(int meta);

}
