package mods.defeatedcrow.common.config;

import net.minecraft.util.MathHelper;

public class PropertyHandler {

	private PropertyHandler() {
	}

	private static DustDifficulty currentDustDef = DustDifficulty.NORMAL;
	private static ChargeDifficulty currentChargeDef = ChargeDifficulty.NORMAL;
	private static ConversionRate currentConversionRate = ConversionRate.NORMAL;

	public static void loadConfig() {
		currentDustDef = DustDifficulty.getDifficulty(DCsConfig.dustDif);
		currentChargeDef = ChargeDifficulty.getDifficulty(DCsConfig.chargeDif);
		currentConversionRate = ConversionRate.getRate(DCsConfig.exchangeDif);
	}

	public static int[] getDustGen() {
		return new int[] { currentDustDef.mainProductNum, currentDustDef.subProductNum };
	}

	public static int ChargeGenRate() {
		return currentChargeDef.rate;
	}

	public static int rateRF() {
		return currentConversionRate.rate_RF;
	}

	public static int rateGF() {
		return currentConversionRate.rate_GF;
	}

	public static int rateEU() {
		return currentConversionRate.rate_EU;
	}

	public enum DustDifficulty {
		SWEET(3, 1), NORMAL(2, 1), BITTER(1, 0), HARD(0, 1);

		public final int mainProductNum;
		public final int subProductNum;

		public static final DustDifficulty[] DIFFICULTY = { SWEET, NORMAL, BITTER, HARD };

		private DustDifficulty(int main, int sub) {
			this.mainProductNum = main;
			this.subProductNum = sub;
		}

		public static DustDifficulty getDifficulty(int id) {
			int i = MathHelper.clamp_int(id, 0, DIFFICULTY.length);
			return DIFFICULTY[i];
		}

	}

	public enum ChargeDifficulty {
		SWEET(800), NORMAL(400), BITTER(200), HARD(40);

		public final int rate;

		public static final ChargeDifficulty[] DIFFICULTY = { SWEET, NORMAL, BITTER, HARD };

		private ChargeDifficulty(int rate) {
			this.rate = rate;
		}

		public static ChargeDifficulty getDifficulty(int id) {
			int i = MathHelper.clamp_int(id, 0, DIFFICULTY.length);
			return DIFFICULTY[i];
		}

	}

	public enum ConversionRate {
		NO_CHANGE(1, 1, 1), WEAKER_CHARGE(5, 2, 1), NORMAL(10, 3, 2), HIGH_CHARGE(20, 6, 4), HARDER_CHARGE(100, 30, 20);

		public final int rate_RF;
		public final int rate_GF;
		public final int rate_EU;

		public static final ConversionRate[] DIFFICULTY = { NO_CHANGE, WEAKER_CHARGE, NORMAL, HIGH_CHARGE,
				HARDER_CHARGE };

		private ConversionRate(int rf, int gf, int eu) {
			this.rate_RF = rf;
			this.rate_GF = gf;
			this.rate_EU = eu;
		}

		public static ConversionRate getRate(int id) {
			int i = MathHelper.clamp_int(id, 0, DIFFICULTY.length);
			return DIFFICULTY[i];
		}

	}

}
