package mods.defeatedcrow.client.entity;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;

/**
 * EdibleEntityのレンダ―モデルやテクスチャパスを返すインターフェイス。
 * ボツにつき現在不使用。
 * */
public interface IEdibleRenderHandler {
	
	ItemStack getItem();
	
	@SideOnly(Side.CLIENT)
	Class<? extends ModelBase> getModel();
	
	String getEntityTex(int meta);
	
	String getEntityGlowTex(int meta);
	
	String getEntityClearTex(int meta);

}
