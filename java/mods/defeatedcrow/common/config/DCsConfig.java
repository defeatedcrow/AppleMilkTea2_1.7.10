package mods.defeatedcrow.common.config;

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
	public static int potionIDHallucinations = 68;
	public static int potionIDConfinement = 69;
	
	//entity
	public static int entityIdMelon = 160;
	public static int entityIdSilkMelon = 161;
	public static int entityIdKinoko = 162;
	public static int entityIdStun = 164;
	public static int entityIdIllusion = 165;
	public static int entityIdMissile = 166;
	public static int entityIdBullet = 167;
	
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
	public static int entityIdCocktail2 = 163;
	public static int entityIdCocktailSP = 168;
	
	//villager関連
	public static int villagerRecipeID = 15;
	public static int villagerRecipe2ID = 16;
	
	//コンフィグ項目の初期設定
	public static int teaTreeGenValue = 5;
	public static int clamChanceValue = 5;
	public static int princessChanceValue = 5;
	public static int setCupTexture = 1;
	public static int setAltTexturePass = 2;
	public static int teppannReadyTime = 30;
	public static int cupStackSize = 1;
	public static int charmRemain = 0;
	public static int batteryUpdate = 4;
	public static double setCupScale = 1.0D;
	
	//難易度関係
	public static int dustDif = 1;
	public static int chargeDif = 1;
	public static int exchangeDif = 2;
	public static boolean altModRecipe = true;
	
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
	public static boolean fearMelon = false;
	public static boolean enableMobBlock[] = {true, true, true, true, true};
	public static boolean hardLeatherRecipe = true;
	public static boolean disableMissileExplosion = false;
	public static boolean PvPProhibitionMode = false;
	public static boolean yuzuCropBurn = true;
	
	public static String debugPass = "Input the password here";
	
	
	private final String BR = System.getProperty("line.separator");
	
	public void config(Configuration cfg)
	{
		
		try
		{
			cfg.load();
			
			cfg.addCustomCategoryComment("potionID",
					"Set new potion ID for this mod. These must be bigger than 32, and smaller than 127.");
			
			cfg.addCustomCategoryComment("entityid",
					"Set new entity ID for this mod. If you set 0, use Forge automatic assignment function.");
			
			cfg.addCustomCategoryComment("debug setting",
					"It only for the authors of this mod.");
			
			cfg.addCustomCategoryComment("difficulty setting",
					"Change difficulty of this mod. If configuration changes causes balance collapse, I do not support.");
			
			cfg.addCustomCategoryComment("render setting",
					"This setting is for such as display and model.");
			
			cfg.addCustomCategoryComment("world setting",
					"This setting is for world generation.");
			
			cfg.addCustomCategoryComment("entity setting",
					"This setting is for entities of this mod.");
			
			cfg.addCustomCategoryComment("setting",
					"This setting is for some sistem of this mod.");
			
			Property DCpotionID = cfg.get("potionID", "Immunization", potionIDImmunity);
			Property DCpotionID2 = cfg.get("potionID", "Protection:Projectile", potionIDPrvProjectile);
			Property DCpotionID3 = cfg.get("potionID", "Protection:Explode", potionIDPrvExplode);
			Property DCpotionID4 = cfg.get("potionID", "Reflex", potionIDReflex);
			Property DCpotionID5 = cfg.get("potionID", "EXPAbsorption", potionIDAbsEXP);
			Property DCpotionID6 = cfg.get("potionID", "DamageAbsorption", potionIDAbsHeal);
			Property DCpotionID7 = cfg.get("potionID", "Suffocation", potionIDSuffocation);
			Property DCpotionID8 = cfg.get("potionID", "Protection:Suffocation", potionIDPrvSuffocation);
			Property DCpotionID9 = cfg.get("potionID", "Protection:Hallucination", potionIDHallucinations);
			Property DCpotionID10 = cfg.get("potionID", "Protection:Confinement", potionIDConfinement);
			
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
			Property explodeMelon = cfg.get("entity setting", "Enable Explode Melon", canExplodeMelon,
					"Allow the Compressed Melon explode.");
			Property cupStackSizeInt = cfg.get("setting", "Cups Stack Size", cupStackSize,
					"Set stack seize of filled cups. Please choose from the 1/3/8.");
			Property melonBreak = cfg.get("entity setting", "Melon not Break Block", melonBreakBlock,
					"Disable destruction by explosion of melon.");
			Property melonFear = cfg.get("entity setting", "Silky Melon of Fear", fearMelon,
					"Silky Melon can destroy all blocks except blocks that coordinates Y = 1.");
			Property missileExplosion = cfg.get("entity setting", "Dsiable Missile Explosion", disableMissileExplosion,
					"Disable explosion of missiles generated from the Fossil Scale.");
			Property safetyChoco = cfg.get("setting", "Safety Chocolate Gift", safetyChocolate,
					"Disable explosion of the heartfelt chocolate gift.");
			Property infinityWipes = cfg.get("setting", "Allow Infinity Wipes", allowInfinityWipes,
					"Allow the WipeBox generate a paper infinitely.");
			Property bonemealClams = cfg.get("setting", "Allow Clam Fertilizer", bonemealClam,
					"Allow to use the clam and the clam container as fertilizer.");
			Property texPass = cfg.get("render setting", "Set Texture Type Number", setAltTexturePass,
					"Select the texture type number."
					+ "1:default(x16 tex), 2:use x32 tex");
			Property cafeRecipe = cfg.get("entity setting", "New Villager ID", villagerRecipeID,
					"Set the number of new villager ID.");
			Property edibleEntity = cfg.get("render setting", "Enable Edible Entity", allowEdibleEntities,
					"Some food blocks are placed as a entity. If false, these are placed as a block. (just same as old version.)");
			Property princessChance = cfg.get("setting", "Princess Chance Value", princessChanceValue,
					"Set the generation probability of princess clam. Default value is 5.");
			Property radenLimit = cfg.get("setting", "Hard Mode Wind Charm", charmRemain,
					"Set a limit on the number of times to use the Raden Charm (Wind)." + BR
					+ "If you set 0, disable Hard Mode.");
			Property mobBlock = cfg.get("setting", "Enable Mob Drop Container", enableMobBlock,
							"Enable to add some compression recipes of mob drop items."
							+ BR + "Rotten flesh, Bone, Spider eye, Ender parl, and Slime ball.");
			Property hardLeather = cfg.get("setting", "Hard Mode Leather Recipe", hardLeatherRecipe,
					"Enable hard mode that make a leather from a rotten flesh.");
			Property batteryCycle = cfg.get("setting", "Battery Update Cycle", batteryUpdate,
					"Set the update cycle tick of the device using the battery.");
			Property debug = cfg.get("debug setting", "Debug Mode Pass", debugPass,
					"Input the password for starting in debug mode. This is only for developer.");
			Property noPvP = cfg.get("setting", "PvP Prohibition Mode", PvPProhibitionMode,
					"Disable the damage caused by items of this MOD against player."
					+ BR + "(For example, Yuzu Gatling, Silky Melon, etc.)");
			Property cupScale = cfg.get("render setting", "Set Drink Entity Scale", setCupScale,
					"Select the scale of drink entity (like a cup). please set 0.01F - 10.0F");
			Property cropBurn = cfg.get("setting", "Yuzu Crop Burn In Device", yuzuCropBurn,
					"Yuzu crops can be burned in the chargeable appliance, and generate 80 charge.");
			
			Property dustDifP = cfg.get("difficulty setting", "JawCrusher Dust Gen", dustDif,
					"Change difficulty of the JawCrusher recipe." + BR
					+ "0:sweet 1:normal 2:bitter 3:hard");
			
			Property chargeDifP = cfg.get("difficulty setting", "Battery Charge Gen", chargeDif,
					"Change difficulty of the battery charge amount." + BR
					+ "0:sweet 1:normal 2:bitter 3:hard");
			
			Property exchangeDifP = cfg.get("difficulty setting", "Exchange Rate of Charge", exchangeDif,
					"Change Rate of the charge exchange to the another energy." + BR
					+ "please set 0-4. smaller are fewer conversion, "
					+ BR + "and bigger are need more energy to exchange to AMT-Charge.");
			
			Property altModR = cfg.get("difficulty setting", "Another Mod Recipe", altModRecipe,
					"Enable Recipes added the another mod machines.");
			
			
			Property entityIce = cfg.get("entityid", "EntityIDIceCream", entityIdIce);
			Property entityCup = cfg.get("entityid", "EntityIDCup", entityIdCup);
			Property entityCup2 = cfg.get("entityid", "EntityIDCup2", entityIdCup2);
			Property entityBowl = cfg.get("entityid", "EntityIDBowl", entityIdBowl);
			Property entityBowl2 = cfg.get("entityid", "EntityIDJPBowl", entityIdBowlJP);
			Property entitySteak = cfg.get("entityid", "EntityIDSteak", entityIdSteak);
			Property entityCocktail = cfg.get("entityid", "EntityIDCocktail", entityIdCocktail);
			Property entityAlcohol = cfg.get("entityid", "EntityIDAlcoholCup", entityIdAlcohol);
			Property entitySandwich = cfg.get("entityid", "EntityIDSandwich", entityIdSandwich);
			Property entityTart = cfg.get("entityid", "EntityIDTart", entityIdTart);
			Property entitySMelon = cfg.get("entityid", "EntityIDSilkMelon", entityIdSilkMelon);
			Property entityKinoko = cfg.get("entityid", "EntityIDKinoko", entityIdKinoko);
			Property entityCocktail2 = cfg.get("entityid", "EntityIDCocktail2", entityIdCocktail2);
			Property entityStun = cfg.get("entityid", "EntityIDStun", entityIdStun);
			Property entityIllusion = cfg.get("entityid", "EntityIDIllusion", entityIdIllusion);
			Property entityMissile = cfg.get("entityid", "EntityIDAnchorMissile", entityIdMissile);
			Property entityBullet = cfg.get("entityid", "EntityIDYuzuBullet", entityIdBullet);
			Property entityCocktailSP = cfg.get("entityid", "EntityIDCocktailSP", entityIdCocktailSP);
			
			potionIDImmunity = DCpotionID.getInt();
			potionIDPrvExplode = DCpotionID3.getInt();
			potionIDPrvProjectile = DCpotionID2.getInt();
			potionIDReflex = DCpotionID4.getInt();
			potionIDAbsEXP = DCpotionID5.getInt();
			potionIDAbsHeal = DCpotionID6.getInt();
			potionIDSuffocation = DCpotionID7.getInt();
			potionIDPrvSuffocation = DCpotionID8.getInt();
			potionIDHallucinations = DCpotionID9.getInt();
			potionIDConfinement = DCpotionID10.getInt();
			
			teaTreeGenValue = TeaTreeValue.getInt();
			setCupTexture = cupTex.getInt();
			teppannReadyTime = teppannTime.getInt();
			cupStackSize = cupStackSizeInt.getInt();
			setAltTexturePass = texPass.getInt();
			clamChanceValue = ClamValue.getInt();
			villagerRecipeID = cafeRecipe.getInt();
			villagerRecipe2ID = cafeRecipe.getInt() + 1;
			princessChanceValue = princessChance.getInt();
			charmRemain = radenLimit.getInt();
			batteryUpdate = batteryCycle.getInt();
			
			dustDif = dustDifP.getInt();
			chargeDif = chargeDifP.getInt();
			exchangeDif = exchangeDifP.getInt();
			altModRecipe = altModR.getBoolean();
			
			setCupScale = cupScale.getDouble();
			
			PropertyHandler.loadConfig();
			
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
			enableMobBlock = mobBlock.getBooleanList();
			hardLeatherRecipe = hardLeather.getBoolean(false);
			disableMissileExplosion = missileExplosion.getBoolean(false);
			PvPProhibitionMode = noPvP.getBoolean(false);
			yuzuCropBurn = cropBurn.getBoolean(true);
			
			entityIdMelon = entityMelon.getInt();
			canExplodeMelon = explodeMelon.getBoolean(false);
			fearMelon = melonFear.getBoolean(false);
			
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
			entityIdSilkMelon = entitySMelon.getInt();
			entityIdKinoko = entityKinoko.getInt();
			entityIdCocktail2 = entityCocktail2.getInt();
			entityIdStun = entityStun.getInt();
			entityIdIllusion = entityIllusion.getInt();
			entityIdMissile = entityMissile.getInt();
			entityIdBullet = entityBullet.getInt();
			entityIdCocktailSP = entityCocktailSP.getInt();
			
			debugPass = debug.getString();
			
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
