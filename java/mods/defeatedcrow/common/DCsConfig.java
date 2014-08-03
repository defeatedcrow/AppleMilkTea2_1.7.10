package mods.defeatedcrow.common;

import java.util.logging.Level;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import cpw.mods.fml.common.FMLLog;

public class DCsConfig {
	
	//potionID
	public static int potionIDImmunity = 60;
	public static int potionIDPrvExplode = 61;
	public static int potionIDPrvProjectile = 62;
	public static int potionIDReflex = 63;
	public static int potionIDAbsEXP = 64;
	public static int potionIDAbsHeal = 65;
	public static int potionIDSuffocation = 66;
	public static int potionIDPrvSuffocation = 67;
	
	//entity
	public static int entityIdMelon = 160;
	
	public static int entityIdIce = 150;
	public static int entityIdCup = 151;
	public static int entityIdCup2 = 152;
	public static int entityIdBowl = 153;
	public static int entityIdBowlJP = 154;
	public static int entityIdSteak = 155;
	public static int entityIdCocktail = 156;
	public static int entityIdAlcohol = 157;
	public static int entityIdSandwich = 158;
	public static int entityIdTart = 159;
	
	//villager関連
	public static int villagerRecipeID = 15;
	
	//コンフィグ項目の初期設定
	public static int teaTreeGenValue = 5;
	public static int clamChanceValue = 5;
	public static int setCupTexture = 1;
	public static int setAltTexturePass = 1;
	public static int teppannReadyTime = 30;
	public static int cupStackSize = 1;
	
	public static boolean useEXRecipe = false;
	public static boolean notGenTeaTree = false;
	public static boolean allowSlimeBallDic = true;
	public static boolean noRenderFoodsSteam = false;
	public static boolean disableFireSteater = false;
	public static boolean disableClam = false;
	public static boolean noWetGContainer = false;
	public static boolean teppannHardMode = false;
	public static boolean useSummerRender = false;
	public static boolean teppannRandomCookTime = false;
	public static boolean canExplodeMelon = true;
	public static boolean melonBreakBlock = false;
	public static boolean safetyChocolate = false;
	public static boolean allowInfinityWipes = true;
	public static boolean bonemealClam = true;
	public static boolean allowEdibleEntities = true;
	
	private final String BR = System.getProperty("line.separator");
	
	public void config(Configuration cfg)
	{
		
		try
		{
			cfg.load();
			
			Property DCpotionID = cfg.get("potionID", "Immunization", potionIDImmunity,
					"Set new potion ID for this mod. These must be bigger than 32, and smaller than 127.");
			Property DCpotionID2 = cfg.get("potionID", "Protection:Projectile", potionIDPrvProjectile,
					"Set new potion ID for this mod. These must be bigger than 32, and smaller than 127.");
			Property DCpotionID3 = cfg.get("potionID", "Protection:Explode", potionIDPrvExplode,
					"Set new potion ID for this mod. These must be bigger than 32, and smaller than 127.");
			Property DCpotionID4 = cfg.get("potionID", "Reflex", potionIDReflex,
					"Set new potion ID for this mod. These must be bigger than 32, and smaller than 127.");
			Property DCpotionID5 = cfg.get("potionID", "EXPAbsorption", potionIDAbsEXP,
					"Set new potion ID for this mod. These must be bigger than 32, and smaller than 127.");
			Property DCpotionID6 = cfg.get("potionID", "DamageAbsorption", potionIDAbsHeal,
					"Set new potion ID for this mod. These must be bigger than 32, and smaller than 127.");
			Property DCpotionID7 = cfg.get("potionID", "Suffocation", potionIDSuffocation,
					"Set new potion ID for this mod. These must be bigger than 32, and smaller than 127.");
			Property DCpotionID8 = cfg.get("potionID", "Protection:Suffocation", potionIDPrvSuffocation,
					"Set new potion ID for this mod. These must be bigger than 32, and smaller than 127.");
			
			Property TeaTreeValue = cfg.get("world setting", "Tea Tree Gen Probability", teaTreeGenValue,
					"Set the generation probability of tea tree.(1-20) Default value is 5.");
			Property ClamValue = cfg.get("world setting", "Clam Gen Probability", clamChanceValue,
					"Set the generation probability and the increase probability of clam.(1-12) Default value is 5.");
			Property EXRecipe = cfg.get("setting", "Use Extra Recipe", useEXRecipe,
					"Add recipe for crafting tea tree sapling.");
			Property noTeaTree = cfg.get("world setting", "No Gen TeaTree", notGenTeaTree,
					"Not generating tea tree on overworld.");
			Property allowSlimeBall = cfg.get("setting", "Allow Slimeball OreDic",
					allowSlimeBallDic, "Allow to add SlimeBall and Animalglue to Oredictionary.");
			Property noSteam = cfg.get("render setting", "Not Render Foods Steam",
					noRenderFoodsSteam, "Not Render Steam on foods of this mod.");
			Property noFirestarter = cfg.get("setting", "Disable Fierstarter",
					disableFireSteater, "Disable recipe for crafting firestarter.");
			Property noClam = cfg.get("world setting", "Disable Clams", disableClam,
					"Not generating clams on overworld.");
			Property nowetGC = cfg.get("Setting", "No Weathering Container",
					noWetGContainer, "Not weathering Gunpowdercontainer.");
			Property cupTex = cfg.get("render setting", "Set JPbowl Texture", setCupTexture,
					"Select the texture of the JP bowls."
					+ "1:cherry flowers, 2:blue pattern, 3:white porcelain");
			Property useSummer = cfg.get("render setting", "Use Summer Rendering", useSummerRender,
					"Use the Summer Rendering to the Cups.");
			Property teppannHard = cfg.get("setting", "Teppann Hard Mode", teppannHardMode,
					"Enable time limit to get the food from the iron plate.");
			Property teppannTime = cfg.get("setting", "Teppann Ready Time", teppannReadyTime,
					"Set the length of time limit to get the food from the iron plate.(min 1 - max 60)");
			Property teppannRandom = cfg.get("setting", "Randomly Teppann Cooking Time", teppannRandomCookTime,
					"Enable randomly cooking time of iron plate.");
			Property entityMelon = cfg.get("entityid", "EntityIDCompressedMelon", entityIdMelon);
			Property explodeMelon = cfg.get("entity", "EnableExplodeMelon", canExplodeMelon,
					"Allow the CompressedMelon explode.");
			Property cupStackSizeInt = cfg.get("setting", "Cups Stack Size", cupStackSize,
					"Set stack seize of filled cups. Please choose from the 1/3/8.");
			Property melonBreak = cfg.get("entity", "Melon not Break Block", melonBreakBlock,
					"Disable destruction by explosion of melon.");
			Property safetyChoco = cfg.get("setting", "Safety Chocolate Gift", safetyChocolate,
					"Disable explosion of the heartfelt chocolate gift.");
			Property infinityWipes = cfg.get("setting", "Allow Infinity Wipes", allowInfinityWipes,
					"Allow the WipeBox generate a paper infinitely.");
			Property bonemealClams = cfg.get("setting", "Allow Clam Fertilizer", bonemealClam,
					"Allow to use the clam and the clam container as fertilizer.");
			Property texPass = cfg.get("render setting", "Set Texture Type Number", setAltTexturePass,
					"Select the texture type number."
					+ "1:default(x16 tex), 2:use x32 tex");
			Property cafeRecipe = cfg.get("entity", "New Villager ID", villagerRecipeID,
					"Set the number of new villager ID.");
			Property edibleEntity = cfg.get("render setting", "Enable Edible Entity", allowEdibleEntities,
					"Some food blocks are placed as a entity. If false, these are placed as a block. (just same as old version.)");
			
			Property entityIce = cfg.get("entityid", "EntityIDIceCream", entityIdIce);
			Property entityCup = cfg.get("entityid", "EntityIDCup", entityIdCup);
			Property entityCup2 = cfg.get("entityid", "EntityIDCup2", entityIdCup2);
			Property entityBowl = cfg.get("entityid", "EntityIDBowl", entityIdBowl);
			Property entityBowl2 = cfg.get("entityid", "EntityIDJPBowl", entityIdBowlJP);
			Property entitySteak = cfg.get("entityid", "EntityIDSteak", entityIdSteak);
			Property entityCocktail = cfg.get("entityid", "EntityIDCocktail", entityIdCocktail);
			Property entityAlcohol = cfg.get("entityid", "EntityIDAlcoholCup", entityIdAlcohol);
			Property entitySandwich = cfg.get("entityid", "EntityIDSandwich", entityIdSandwich);
			Property entityTart = cfg.get("entityid", "EntityIDIceTart", entityIdTart);
			
			potionIDImmunity = DCpotionID.getInt();
			potionIDPrvExplode = DCpotionID3.getInt();
			potionIDPrvProjectile = DCpotionID2.getInt();
			potionIDReflex = DCpotionID4.getInt();
			potionIDAbsEXP = DCpotionID5.getInt();
			potionIDAbsHeal = DCpotionID6.getInt();
			potionIDSuffocation = DCpotionID7.getInt();
			potionIDPrvSuffocation = DCpotionID8.getInt();
			
			teaTreeGenValue = TeaTreeValue.getInt();
			setCupTexture = cupTex.getInt();
			teppannReadyTime = teppannTime.getInt();
			cupStackSize = cupStackSizeInt.getInt();
			setAltTexturePass = texPass.getInt();
			clamChanceValue = ClamValue.getInt();
			villagerRecipeID = cafeRecipe.getInt();
			
			useEXRecipe = EXRecipe.getBoolean(false);
			notGenTeaTree = noTeaTree.getBoolean(false);
			allowSlimeBallDic = allowSlimeBall.getBoolean(true);
			noRenderFoodsSteam = noSteam.getBoolean(false);
			disableFireSteater = noFirestarter.getBoolean(false);
			disableClam = noClam.getBoolean(false);
			noWetGContainer = nowetGC.getBoolean(false);
			teppannHardMode = teppannHard.getBoolean(false);
			useSummerRender = useSummer.getBoolean(false);
			teppannRandomCookTime = teppannRandom.getBoolean(false);
			melonBreakBlock = melonBreak.getBoolean(false);
			safetyChocolate = safetyChoco.getBoolean(false);
			bonemealClam = bonemealClams.getBoolean(false);
			allowEdibleEntities = edibleEntity.getBoolean(true);
			
			entityIdMelon = entityMelon.getInt();
			canExplodeMelon = explodeMelon.getBoolean(false);
			
			entityIdIce = entityIce.getInt();
			entityIdCup = entityCup.getInt();
			entityIdCup2 = entityCup2.getInt();
			entityIdBowl = entityBowl.getInt();
			entityIdBowlJP = entityBowl2.getInt();
			entityIdSteak = entitySteak.getInt();
			entityIdCocktail = entityCocktail.getInt();
			entityIdAlcohol = entityAlcohol.getInt();
			entityIdSandwich = entitySandwich.getInt();
			entityIdTart = entityTart.getInt();
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cfg.save();
		}
	}

}
