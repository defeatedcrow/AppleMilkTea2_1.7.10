package mods.defeatedcrow.api.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;

/**
 * 柚子ガトリングが弾を発射する直前に呼ばれるイベント。 <br>
 * 弾・チャージの減少処理の前のため、消費内容も変更可能。
 * キャンセル可。
 */
@Cancelable
@Event.HasResult
public class ShootingGunEvent extends Event {

	public final World world;
	public final EntityPlayer player;
	public final ItemStack gun;
	public final float damage;
	public final int looseBullet;
	public final int looseCharge;

	public ShootingGunEvent(World thisWorld, EntityPlayer thisPlayer, ItemStack item, float dam, int bullet, int charge) {
		this.world = thisWorld;
		this.player = thisPlayer;
		this.gun = item;
		this.damage = dam;
		this.looseBullet = bullet;
		this.looseCharge = charge;
	}

}
