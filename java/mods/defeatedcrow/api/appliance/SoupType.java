package mods.defeatedcrow.api.appliance;

public enum SoupType {
	EMPTY(0, "Empty", "defeatedcrow:textures/blocks/contents/water.png", "defeatedcrow:contents/water"),
	WATER(1, "Water", "defeatedcrow:textures/blocks/contents/water.png", "defeatedcrow:contents/water"),
	CHOCO(2, "Chocolate Fondue", "defeatedcrow:textures/blocks/contents/choco.png", "defeatedcrow:contents/choco"),
	OIL(3, "Oil", "defeatedcrow:textures/blocks/contents/oil.png", "defeatedcrow:contents/oil"),
	DASHI(4, "Dashi Soup", "defeatedcrow:textures/blocks/contents/dashi.png", "defeatedcrow:contents/dashi"),
	TONKOTU(5, "Tonkotu Soup", "defeatedcrow:textures/blocks/contents/tonkotu.png", "defeatedcrow:contents/tonkotu"),
	SHOYU(6, "Shoyu Soup", "defeatedcrow:textures/blocks/contents/shoyu.png", "defeatedcrow:contents/shoyu"),
	BLOOD(7, "Blood", "defeatedcrow:textures/blocks/contents/blood.png", "defeatedcrow:contents/blood"),
	PURPLE(8, "Darkness", "defeatedcrow:textures/blocks/contents/purple.png", "defeatedcrow:contents/purple"),
	CHEESE(9, "Cheese", "defeatedcrow:textures/blocks/contents/cheese.png", "defeatedcrow:contents/cheese");

	public final int id;
	public final String texture;
	public final String blockTexture;
	public final String display;

	private SoupType(int i, String d, String s, String b) {
		id = i;
		texture = s;
		display = d;
		blockTexture = b;
	}

	public static final SoupType[] types = {
			EMPTY,
			WATER,
			CHOCO,
			OIL,
			DASHI,
			TONKOTU,
			SHOYU,
			BLOOD,
			PURPLE,
			CHEESE };

	public static SoupType getType(int i) {
		switch (i) {
		case 0:
			return EMPTY;
		case 1:
			return WATER;
		case 2:
			return CHOCO;
		case 3:
			return OIL;
		case 4:
			return DASHI;
		case 5:
			return TONKOTU;
		case 6:
			return SHOYU;
		case 7:
			return BLOOD;
		case 8:
			return PURPLE;
		case 9:
			return CHEESE;
		default:
			return EMPTY;

		}

	}

}
