package mods.defeatedcrow.client.entity;

import java.util.List;

import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;

/**
 * IEdibleRendetHandlerの登録用インターフェース。
 * これも没なので使われていない。
 */
@Deprecated
public interface IEdibleRenderRegister {

	List<? extends IEdibleRenderHandler> getList();

	IEdibleRenderHandler getHandler(ItemStack item);

	void register(ItemStack Item, Class<? extends ModelBase> model, String tex, String glowTex, String cloarTex);

}
