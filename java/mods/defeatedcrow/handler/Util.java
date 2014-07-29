package mods.defeatedcrow.handler;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import mods.defeatedcrow.common.*;

public class Util {
	
	private Util(){}
	
	//コンフィグで規定範囲外の数値を入れた時に、安全に動かすためのメソッドその1
	public static int getCupRender()
	{
		int l = DCsConfig.setCupTexture;
		if (l < 0) l = 1;
		else if (l > 3) l = 3;
		
		return l;
	}
	
	//その2
	public static int getTeppannReadyTime()
	{
		int l = DCsConfig.teppannReadyTime;
		if (l < 0) l = 1;
		else if (l > 60) l = 60;
		
		return l;
	}
	
	//その3
	public static int getCupStacksize()
	{
		int l = DCsConfig.cupStackSize;
		if (l <= 1) l = 1;
		else
		{
			if (l <= 3) l = 3;
			else
			{
				l = 8;
			}
		}
		return l;
	}
	
	public static int getHamaguriChanceValue()
	{
		int l = DCsConfig.clamChanceValue;
		if (l < 0) l = 1;
		else if (l > 100) l = 100;
		
		return l;
	}
	
	//コンフィグで規定範囲外の数値を入れた時に、安全に動かすためのメソッドその4
	//各Block、Itemクラス側からコンフィグ内容を確認するための中継地点
	//altテクスチャが用意されていない物は下のメソッドを使う
	public static String getTexturePass()
	{
		int l = DCsConfig.setAltTexturePass - 1;
		if (l < 0) l = 0;
		else if (l > 2) l = 2;
			
		return DCsAppleMilk.TEX_PASS[l];
	}
	
	public static String getTexturePassNoAlt()
	{
		int l = DCsConfig.setAltTexturePass - 1;
		if (l < 0) l = 0;
		else if (l > 1) l = 1;
			
		return DCsAppleMilk.TEX_PASS[l];
	}
	
	//entityはテクスチャのパスの記述方法が違うので別途作成。
	public static String getEntityTexturePass()
	{
		int l = DCsConfig.setAltTexturePass - 1;
		if (l < 0) l = 0;
		else if (l > 2) l = 2;
			
		return DCsAppleMilk.TEX_PASS_ENTITY[l];
	}
	
	public static String getEntityTexturePassNoAlt()
	{
		int l = DCsConfig.setAltTexturePass - 1;
		if (l < 0) l = 0;
		else if (l > 1) l = 1;
			
		return DCsAppleMilk.TEX_PASS_ENTITY[l];
	}
	
	//0,0=south, 1,90=west, 2,180=north, 3,-90=east
	//相変わらず方角を覚えられないため自分用に作ったメソッド
	public static final int[] METAX = new int[] {0, -1, 0, 1};
	
	public static final int[] METAZ = new int[] {-1, 0, 1, 0};
	
	public static final int[] RAD = new int[] {0, -90, 180, 90};
	
	//FMLの機能を利用した他MOD様のアイテム取得メソッド。
	//protectedにする意味は正直あんまりない。
	//そもそもこのメソッドの存在意義がない
	public static Item getModItem(String modId, String name)
	{
		Item ret = GameRegistry.findItem(modId, name);
		if (ret != null) {
			return ret;
		}
		else {
			return null;
		}
	}
	
	public static Block getModBlock(String modId, String name)
	{
		Block ret = GameRegistry.findBlock(modId, name);
		if (ret != null) {
			return ret;
		}
		else {
			return null;
		}
	}

	//現在地のバイオームを確認。
	//複数のクラスで利用するので、ここにまとめた。
	public static BiomeGenBase checkCurrentBiome(World world, EntityPlayer player)
	{
		BiomeGenBase biome = BiomeGenBase.plains;
		
		int x = MathHelper.floor_double(player.posX);
		int y = MathHelper.floor_double(player.posY);
		int z = MathHelper.floor_double(player.posZ);
		
		biome = world.getBiomeGenForCoords(x, z);
		
		return biome;
	}
	
	//新規追加ポーションを発生させる場合、ここのメソッドを中継する。（追加失敗対策）
	public static boolean addPotionEffectDC(EntityLivingBase living, PotionEffect effect)
	{
		boolean flag = DCsAppleMilk.succeedAddPotion;
		if (flag)
		{
			living.addPotionEffect(effect);
		}
		else
		{
			living.addPotionEffect(new PotionEffect(Potion.regeneration.id, 300, 0));
		}
		return flag;
	}

}
