package mods.defeatedcrow.common.config;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class DCsConfigCocktail {

	private final String BR = System.getProperty("line.separator");

	public static int[] potionIds = { 62, 12, 60 };
	public static int[] potionDur = { 1200, 1200, 2400 };
	public static int[] potionAmp = { 0, 0, 2 };

	public static String[] name = { "Russian Ballet", "Rum Flip", "Denki-Bran" };

	public static String[] massage = { "You can put any message here.", "This message is a tool tip display of items.",
			"This is a fake. Nobody knows the real recipe." };

	public static String[] recipe1 = { "bottleVodka", "bottleCassisliqueur", "foodBlockLemonade" };
	public static String[] recipe2 = { "bottleRum", "minecraft:egg", "dustSugar" };
	public static String[] recipe3 = { "bottleBrandy", "bottleGin", "bottleWine" };

	public static int[] color1 = { 20, 0, 20, 5 };
	public static int[] color2 = { 10, 20, 0, 9 };
	public static int[] color3 = { 5, 2, 0, 5 };

	public static int[] type = { 0, 0, 1 };
	public static int[] deco = { 0, 0, 0 };

	public static float[] colorf1 = new float[4];
	public static float[] colorf2 = new float[4];
	public static float[] colorf3 = new float[4];

	public void config(Configuration cfg) {
		try {
			cfg.load();
			cfg.addCustomCategoryComment(
					"cocktail_1",
					"You can edit the information of the original cocktails."
							+ BR
							+ "Name : Edit the cocktail name."
							+ BR
							+ "Massage : Edit the cocktail tool-tip."
							+ BR
							+ "Glass and Decoration : Edit the cocktail glass type and decoration fluits."
							+ BR
							+ "Color : Edit the cocktail color, red, green, blue, alpha. Please set color 0-100, and set alpha 0-10."
							+ BR
							+ "Potion data : Edit the cocktail effect. "
							+ BR
							+ "Recipe : Edit the cocktail recipe. oredictionary name or item unlocalized name can be used."
							+ BR
							+ "If you want to use vanilla items, describe the following: \"minecraft:itemname:metadata\"."
							+ BR + "If you want to use mod items, describe the following: \"modid:itemname:metadata\"."
							+ BR + "If your description was wrong, it will be clam.");

			Property name1 = cfg.get("cocktail_1", "Cocktail Name", name[0]);
			Property massage1 = cfg.get("cocktail_1", "Cocktail Massage", massage[0]);
			Property colorP1 = cfg.get("cocktail_1", "Cocktail Color", color1);
			Property type1 = cfg.get("cocktail_1", "Cocktail Glass Type", type[0],
					"Coose cocktail type. 0:short 1:long 2:wineglass");
			Property deco1 = cfg.get("cocktail_1", "Cocktail Decoration Type", deco[0],
					"Coose cocktail decoration. 0:none 1:lemon 2:lime 3:pine 4:apple");
			Property id1 = cfg.get("cocktail_1", "Effect Potion ID", potionIds[0]);
			Property dur1 = cfg.get("cocktail_1", "Effect Duration", potionDur[0]);
			Property amp1 = cfg.get("cocktail_1", "Effect Amplifier", potionAmp[0]);
			Property recipeP1 = cfg.get("cocktail_1", "Recipe", recipe1);

			Property name2 = cfg.get("cocktail_2", "Cocktail Name", name[1]);
			Property massage2 = cfg.get("cocktail_2", "Cocktail Massage", massage[1]);
			Property colorP2 = cfg.get("cocktail_2", "Cocktail Color", color2);
			Property type2 = cfg.get("cocktail_1", "Cocktail Glass Type", type[1],
					"Coose cocktail type. 0:short 1:long 2:wineglass");
			Property deco2 = cfg.get("cocktail_1", "Cocktail Decoration Type", deco[1],
					"Coose cocktail decoration. 0:none 1:lemon 2:lime 3:pine 4:apple");
			Property id2 = cfg.get("cocktail_2", "Effect Potion ID", potionIds[1]);
			Property dur2 = cfg.get("cocktail_2", "Effect Duration", potionDur[1]);
			Property amp2 = cfg.get("cocktail_2", "Effect Amplifier", potionAmp[1]);
			Property recipeP2 = cfg.get("cocktail_2", "Recipe", recipe2);

			Property name3 = cfg.get("cocktail_3", "Cocktail Name", name[2]);
			Property massage3 = cfg.get("cocktail_3", "Cocktail Massage", massage[2]);
			Property colorP3 = cfg.get("cocktail_3", "Cocktail Color", color3);
			Property type3 = cfg.get("cocktail_3", "Cocktail Glass Type", type[2],
					"Coose cocktail type. 0:short 1:long 2:wineglass");
			Property deco3 = cfg.get("cocktail_3", "Cocktail Decoration Type", deco[2],
					"Coose cocktail decoration. 0:none 1:lemon 2:lime 3:pine 4:apple");
			Property id3 = cfg.get("cocktail_3", "Effect Potion ID", potionIds[2]);
			Property dur3 = cfg.get("cocktail_3", "Effect Duration", potionDur[2]);
			Property amp3 = cfg.get("cocktail_3", "Effect Amplifier", potionAmp[2]);
			Property recipeP3 = cfg.get("cocktail_3", "Recipe", recipe3);

			name = new String[] { name1.getString(), name2.getString(), name3.getString() };
			massage = new String[] { massage1.getString(), massage2.getString(), massage3.getString() };
			potionIds = new int[] { id1.getInt(), id2.getInt(), id3.getInt() };
			potionDur = new int[] { dur1.getInt(), dur2.getInt(), dur3.getInt() };
			potionAmp = new int[] { amp1.getInt(), amp2.getInt(), amp3.getInt() };
			type = new int[] { type1.getInt(), type2.getInt(), type3.getInt() };
			deco = new int[] { deco1.getInt(), deco2.getInt(), deco3.getInt() };

			recipe1 = recipeP1.getStringList();
			color1 = colorP1.getIntList();
			colorf1 = new float[] { color1[0] * 0.1F, color1[1] * 0.1F, color1[2] * 0.1F, color1[3] * 0.1F };

			recipe2 = recipeP2.getStringList();
			color2 = colorP2.getIntList();
			colorf2 = new float[] { color2[0] * 0.1F, color2[1] * 0.1F, color2[2] * 0.1F, color2[3] * 0.1F };

			recipe3 = recipeP3.getStringList();
			color3 = colorP3.getIntList();
			colorf3 = new float[] { color3[0] * 0.1F, color3[1] * 0.1F, color3[2] * 0.1F, color3[3] * 0.1F };

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cfg.save();
		}
	}

}
