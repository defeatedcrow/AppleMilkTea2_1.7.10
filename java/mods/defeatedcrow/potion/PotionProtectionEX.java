package mods.defeatedcrow.potion;

import mods.defeatedcrow.api.potion.PotionBaseAMT;
import net.minecraft.util.DamageSource;

/**
 * 中身はゲッターセッターだけ。
 * 実際の動作はDamage時のイベントに依存する。
 */
public class PotionProtectionEX extends PotionBaseAMT {

	// これがtrueだと全ての効果をあわせもつ。特殊条件で5秒程度付くような使い方をする予定。
	private boolean allProtection = false;

	// 飛び道具のダメージを無効化する。
	private boolean projectileProtection = false;

	// 爆発ダメージ無効化
	private boolean explodeProtection = false;

	// 窒息ダメージ無効化
	private boolean suffocationProtection = false;

	// それ以外のダメージソースを指定する。上記と併用もできるが、一種しか指定できない。
	private DamageSource preventSource;

	public PotionProtectionEX(int par1, boolean par2, int par3, boolean all, boolean exp, boolean proj,
			DamageSource source, int x, int y) {
		super(par1, par2, par3, x, y);
		this.allProtection = all;
		this.explodeProtection = exp;
		this.projectileProtection = proj;
		this.preventSource = source;
	}

	public PotionProtectionEX setAiiProtection() {
		this.allProtection = true;
		return this;
	}

	public PotionProtectionEX setProtectProj() {
		this.projectileProtection = true;
		return this;
	}

	public PotionProtectionEX setProtectExplode() {
		this.explodeProtection = true;
		return this;
	}

	public PotionProtectionEX setProtectSuffocation() {
		this.suffocationProtection = true;
		return this;
	}

	public PotionProtectionEX setPreventSource(DamageSource par1Source) {
		this.preventSource = par1Source;
		return this;
	}

	public boolean getAllProtection() {
		return this.allProtection;
	}

	public boolean getProjProtection() {
		return this.projectileProtection;
	}

	public boolean getExplodeProtection() {
		return this.explodeProtection;
	}

	public boolean getSuffocationProtection() {
		return this.suffocationProtection;
	}

	public DamageSource getPreventSource() {
		return this.preventSource;
	}

}
