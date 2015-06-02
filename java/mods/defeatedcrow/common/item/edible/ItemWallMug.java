package mods.defeatedcrow.common.item.edible;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.defeatedcrow.common.AchievementRegister;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.plugin.SSector.LoadSSectorPlugin;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemWallMug extends Item {

	private static final String[] contents = new String[] { "_tea", "_green", "_cocoa", "_coffee" };
	private static final String[] contentsFruit = new String[] { "_foam", "_foam", "_nuts", "_berry" };

	private static final String[] drinkType = new String[] { "Tea", "Green Tea", "Cocoa", "Coffee" };
	private static final String[] milkType = new String[] { "None", "Milk", "Condenced Milk", "Soy" };
	private static final String[] sugarType = new String[] { "None", "Sugar", "Maple", "Honey" };
	private static final String[] fruitType = new String[] { "None", "Foam", "Nuts", "Berry" };
	private static final String[] potionType = new String[] { "Regeneration", "Dig Speed", "Resistance", "Damage Boost" };
	private static final String[] timeType = new String[] { "(60sec)", "(2min)", "(4min)", "(5min)" };

	@SideOnly(Side.CLIENT)
	private IIcon[] thisTex;
	@SideOnly(Side.CLIENT)
	private IIcon[] topTex;

	public ItemWallMug() {
		super();
		this.setMaxStackSize(8);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	// 飲食時の処理
	@Override
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		if (!par3EntityPlayer.capabilities.isCreativeMode) {
			--par1ItemStack.stackSize;
		}
		if (!par3EntityPlayer.inventory.addItemStackToInventory(new ItemStack(this))) {
			par3EntityPlayer.entityDropItem(new ItemStack(this), 1);
		}

		int meta = par1ItemStack.getItemDamage();
		int type = checkType(meta);
		int milk = checkMilkType(meta);
		int sugar = checkSugarType(meta);
		int fruit = checkFruitType(meta);
		int[] time = { 600, 1800, 3000, 5400 };
		int m = 600 + time[milk];

		if (!par2World.isRemote) {
			if (type == 0)// Tea
			{
				if (par3EntityPlayer.isPotionActive(Potion.regeneration.id)) {
					int d = par3EntityPlayer.getActivePotionEffect(Potion.regeneration).getDuration();

					par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.regeneration.id, (d + m), sugar));
				} else {
					par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.regeneration.id, m, sugar));
				}
			} else if (type == 1)// Green Tea
			{
				if (par3EntityPlayer.isPotionActive(Potion.digSpeed.id)) {
					int d = par3EntityPlayer.getActivePotionEffect(Potion.digSpeed).getDuration();

					par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.digSpeed.id, (d + m), sugar));
				} else {
					par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.digSpeed.id, m, sugar));
				}
			} else if (type == 2)// Cocoa
			{
				if (par3EntityPlayer.isPotionActive(Potion.resistance.id)) {
					int d = par3EntityPlayer.getActivePotionEffect(Potion.resistance).getDuration();

					par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.resistance.id, (d + m), sugar));
				} else {
					par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.resistance.id, m, sugar));
				}
			} else if (type == 3)// Coffee
			{
				if (par3EntityPlayer.isPotionActive(Potion.damageBoost.id)) {
					int d = par3EntityPlayer.getActivePotionEffect(Potion.damageBoost).getDuration();

					par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.damageBoost.id, (d + m), sugar));
				} else {
					par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.damageBoost.id, m, sugar));
				}
			}

			// int moist = milk + 3;
			// this.addSSMoisture(moist, 1.5F, par3EntityPlayer);
		}

		return par1ItemStack;
	}

	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.drink;
	}

	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		return 32;
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
		return par1ItemStack;
	}

	@SideOnly(Side.CLIENT)
	// マウスオーバー時の表示情報
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
		int l = par1ItemStack.getItemDamage();
		String data = new String("customize: ");
		int type = checkType(l);
		if (l < 0)
			l = 0;

		int milk = checkMilkType(l);
		if (milk > 0)
			data = data + milkType[milk] + ", ";

		int sugar = checkSugarType(l);
		if (sugar > 0)
			data = data + sugarType[sugar] + ", ";

		int fruit = checkFruitType(l);
		if (fruit > 0)
			data = data + fruitType[fruit];

		if (l < 4)
			data = data + "Black";
		par3List.add(new String("type: " + drinkType[type]));
		par3List.add(data);
		String[] num = new String[] { "I", "II", "III", "IV" };
		par3List.add(new String(potionType[type] + " " + num[sugar] + " " + timeType[milk]));
	}

	private int checkType(int par1) {
		int m = par1 & 3;// 中身の種類
		return m;
	}

	private int checkMilkType(int par1)// 効果時間延長＆上書き化
	{
		int m = par1 >> 2;// 右にシフト。4から1へ。
		m = (m & 3);// 4、8の桁をチェック。無、牛乳、練乳、豆乳の順。
		return m;
	}

	private int checkSugarType(int par1)// 効果レベル上昇
	{
		int m = par1 >> 4;// 右にシフト。16から1へ。
		m = (m & 3);// 16、32の桁をチェック。無、砂糖、メイプル、ハチミツ
		return m;
	}

	private int checkFruitType(int par1) {
		int m = par1 >> 6;// 右にシフト。64から1へ。
		m = (m & 3);// 64、128の桁をチェック。無、ミルクフォームのみ、ナッツ、ベリージャム
		return m;
	}

	// 以下はサブタイプやアイコン登録など
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1) {
		int j = par1 & 7;
		return this.thisTex[j];
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int par1, int par2) {
		int type = this.checkFruitType(par1);

		return par2 == 0 ? this.topTex[type] : super.getIconFromDamageForRenderPass(par1, par2);
	}

	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses() {
		return true;
	}

	@Override
	public IIcon getIcon(ItemStack stack, int pass) {
		int m = stack.getMaxDamage();
		int type = m & 7;
		int fruit = this.checkFruitType(m);
		if (pass == 0) {
			return this.topTex[fruit];
		} else {
			return getIconFromDamageForRenderPass(stack.getItemDamage(), pass);
		}
	}

	@Override
	public int getMetadata(int par1) {
		return par1;// ここではダメージ値通りのものを返す
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		return super.getUnlocalizedName() + "_" + par1ItemStack.getItemDamage();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		par3List.add(new ItemStack(this, 1, 0));
		par3List.add(new ItemStack(this, 1, 1));
		par3List.add(new ItemStack(this, 1, 2));
		par3List.add(new ItemStack(this, 1, 3));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {

		this.thisTex = new IIcon[8];
		this.topTex = new IIcon[4];

		for (int i = 0; i < 4; ++i) {
			this.thisTex[i] = par1IconRegister.registerIcon("defeatedcrow:wallmug" + contents[i]);
			this.thisTex[i + 4] = par1IconRegister.registerIcon("defeatedcrow:wallmug" + contents[i] + "_milk");
		}

		for (int i = 0; i < 4; ++i) {
			this.topTex[i] = par1IconRegister.registerIcon("defeatedcrow:wallmug" + contents[i]);
		}
	}

	// //SextiarySector連携要素
	// private void addSSMoisture(int par1, float par2, EntityPlayer par3EntityPlayer)
	// {
	// int heal = par1;
	// float saturation = par2;
	// if (DCsAppleMilk.SuccessLoadSSector)
	// {
	// LoadSSectorPlugin.addStatus(heal, saturation, 0, 0.0F, par3EntityPlayer);
	// }
	// }

}
