package mods.defeatedcrow.common.tile.appliance;

import mods.defeatedcrow.api.edibles.IEdibleItem;
import mods.defeatedcrow.api.recipe.IEvaporatorRecipe;
import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.fluid.DCsTank;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import buildcraft.api.transport.IPipeConnection;
import buildcraft.api.transport.IPipeTile.PipeType;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Optional.InterfaceList({ @Optional.Interface(
		iface = "buildcraft.api.transport.IPipeConnection",
		modid = "BuildCraft|Core"), })
public class TileEvaporator extends MachineBase implements IFluidHandler, IPipeConnection {

	public DCsTank productTank = new DCsTank(4000);

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		this.productTank = new DCsTank(4000);
		if (par1NBTTagCompound.hasKey("productTank")) {
			this.productTank.readFromNBT(par1NBTTagCompound.getCompoundTag("productTank"));
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		NBTTagCompound tank = new NBTTagCompound();
		this.productTank.writeToNBT(tank);
		par1NBTTagCompound.setTag("productTank", tank);
	}

	@Override
	public Packet getDescriptionPacket() {
		return super.getDescriptionPacket();
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		super.onDataPacket(net, pkt);
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		// 液体を取り出す部分
		boolean flag1 = false;
		boolean flag2 = false;
		int drainAmount = 0;
		Fluid fluid = null;
		ItemStack returnStack = null;

		if (this.productTank.getFluid() != null && this.productTank.getFluidType() != null) {
			fluid = this.productTank.getFluidType();
			flag1 = (fluid != null);
		}

		if (flag1 && this.itemstacks[4] != null) {
			ItemStack cur = this.itemstacks[4].copy();

			if (cur.getItem() == Item.getItemFromBlock(Blocks.sand)) {
				this.productTank.drain(this.productTank.getFluidAmount(), true);
			} else if (cur.getItem() == Items.bucket)// バケツの場合
			{
				returnStack = FluidContainerRegistry.fillFluidContainer(new FluidStack(this.productTank.getFluidType(),
						1000), new ItemStack(Items.bucket));
				flag2 = (returnStack != null && this.productTank.getFluidAmount() >= 1000 * cur.stackSize);
				drainAmount = 1000 * cur.stackSize;
			} else if (cur.getItem() == Item.getItemFromBlock(DCsAppleMilk.emptyBottle)) {
				returnStack = FluidContainerRegistry.fillFluidContainer(new FluidStack(this.productTank.getFluidType(),
						200), new ItemStack(DCsAppleMilk.emptyBottle));
				flag2 = (returnStack != null && this.productTank.getFluidAmount() >= 200 * cur.stackSize);
				drainAmount = 200 * cur.stackSize;
			} else // その他の場合は1000mBのみチェック
			{
				returnStack = FluidContainerRegistry.fillFluidContainer(new FluidStack(this.productTank.getFluidType(),
						1000), new ItemStack(cur.getItem(), 1, cur.getItemDamage()));
				flag2 = (returnStack != null && this.productTank.getFluidAmount() >= 1000 * cur.stackSize);
				drainAmount = 1000 * cur.stackSize;
			}
		}

		if (flag1 && flag2 && returnStack != null) {
			ItemStack result = new ItemStack(returnStack.getItem(), this.itemstacks[4].stackSize,
					returnStack.getItemDamage());

			if (this.productTank.drain(drainAmount, true) != null) {
				this.itemstacks[4] = result;
			}
		}

		this.markDirty();
	}

	// 調理中の矢印の描画
	@SideOnly(Side.CLIENT)
	public int getFluidAmountScaled(int par1) {
		return this.productTank.getFluidAmount() * par1 / 4000;
	}

	// GUI用にコンテナからの更新を受け取るメソッド。
	public void getGuiFluidUpdate(int id, int val) {
		if (id == 2)// ID
		{
			if (productTank.getFluid() == null) {
				productTank.setFluidById(val);
			} else {
				int amo = productTank.getFluidAmount();
				productTank.setFluidById(val);
			}
		} else if (id == 3)// amount
		{
			if (productTank.getFluid() == null) {
				productTank.setFluid((FluidStack) null);
			} else {
				productTank.getFluid().amount = val;
			}
		}
	}

	@Override
	public boolean canSmelt() {
		boolean flag1 = false;// レシピ
		boolean flag2 = false;// メイン完成スロット
		boolean flag3 = false;// 液体
		boolean flag4 = false;// 空容器

		ItemStack items = null;
		if (this.itemstacks[2] == null)
			return false;
		else {
			items = this.itemstacks[2].copy();
		}

		IEvaporatorRecipe recipe = RecipeRegisterManager.evaporatorRecipe.getRecipe(items);
		if (recipe == null)
			return false;

		ItemStack output = recipe.getOutput();
		FluidStack second = recipe.getSecondary();

		// 両方がnullのレシピはレシピとみなさない
		if ((output == null && second == null) || (items.stackSize < recipe.getInput().stackSize))
			return false;
		else {
			flag1 = true;
		}

		ItemStack container = null;
		if (items.getItem() instanceof IEdibleItem) {
			IEdibleItem edible = (IEdibleItem) items.getItem();
			container = edible.getReturnContainer(items.getItemDamage());
		} else if (items.getItem() == DCsAppleMilk.moromi) {

		} else if (items.getItem().hasContainerItem(items)) {
			container = items.getItem().getContainerItem(items);
		} else if (items.getItem() == DCsAppleMilk.itemLargeBottle)// 特殊条件
		{
			if (items.getItemDamage() > 16) {
				container = new ItemStack(DCsAppleMilk.itemLargeBottle, 1, items.getItemDamage() - 16);
			} else {
				container = new ItemStack(DCsAppleMilk.emptyBottle, 1, 0);
			}
		}

		if (this.itemstacks[3] == null || output == null) {
			flag2 = true;
		} else {
			if (this.itemstacks[3].isItemEqual(output)) {
				int result = this.itemstacks[3].stackSize + output.stackSize;
				flag2 = (result <= this.getInventoryStackLimit() && result <= output.getMaxStackSize());
			}
		}

		if (container != null && recipe.returnContainer()) {
			if (this.itemstacks[5] == null) {
				flag4 = true;
			} else {
				if (this.itemstacks[5].isItemEqual(container)) {
					int result = this.itemstacks[5].stackSize + container.stackSize;
					flag4 = (result <= this.getInventoryStackLimit() && result <= container.getMaxStackSize());
				}
			}
		} else {
			flag4 = true;
		}

		// 液体ありのレシピの場合
		if (second == null) {
			flag3 = true;
		} else {
			if (this.productTank.isEmpty()) {
				flag3 = true;
			} else {
				int fillAmount = this.productTank.fill(second, false);
				flag3 = fillAmount >= second.amount;
			}
		}

		// AMTLogger.debugInfo("Evaporator update : " + flag1 + ", " + flag2 +
		// ", " + flag3 + ", " + flag4);

		return flag1 && flag2 && flag3 && flag4;
	}

	@Override
	public void onProgress() {
		// 結局canSmelt()と同じことをしていて無駄な感じはする
		// すでに判定は済んでるし、二重にやる必要無いような…
		boolean flag1 = false;
		boolean flag2 = false;
		boolean flag3 = false;

		ItemStack items = this.itemstacks[2];
		if (items == null)
			return;

		IEvaporatorRecipe recipe = RecipeRegisterManager.evaporatorRecipe.getRecipe(items);
		if (recipe == null)
			return;

		ItemStack output = recipe.getOutput();
		FluidStack second = recipe.getSecondary();
		if (output == null && second == null)
			return;
		else {
			flag1 = true;
		}

		ItemStack container = null;
		if (items.getItem() instanceof IEdibleItem) {
			IEdibleItem edible = (IEdibleItem) items.getItem();
			container = edible.getReturnContainer(items.getItemDamage());
		} else if (items.getItem().hasContainerItem(items)) {
			container = items.getItem().getContainerItem(items);
		} else if (items.getItem() == DCsAppleMilk.itemLargeBottle)// 特殊条件
		{
			if (items.getItemDamage() > 16) {
				container = new ItemStack(DCsAppleMilk.itemLargeBottle, 1, items.getItemDamage() - 16);
			} else {
				container = new ItemStack(DCsAppleMilk.emptyBottle, 1, 0);
			}
		}

		// 材料を減らし、返却アイテムが有る場合は返却スロットへ。
		if (this.itemstacks[2] != null) {
			this.itemstacks[2].stackSize -= recipe.getInput().stackSize;

			if (this.itemstacks[2].stackSize <= 0) {
				this.itemstacks[2] = null;
			}

			if (container != null && recipe.returnContainer()) {
				if (this.itemstacks[5] == null) {
					this.itemstacks[5] = container.copy();
				} else if (this.itemstacks[5].isItemEqual(container)) {
					this.itemstacks[5].stackSize += container.stackSize;
				}
			}

			flag2 = true;
		}

		if (flag1 && flag2) {
			String out = output == null ? "Empty" : output.toString();
			String sec = second == null ? "Empty" : second.getFluid().getLocalizedName(second);
			AMTLogger.debugInfo("current recipe : " + out + ", " + sec);

			// 次に完成品を完成品スロットへ
			if (output != null) {
				if (this.itemstacks[3] == null) {
					this.itemstacks[3] = output.copy();
				} else if (this.itemstacks[3].isItemEqual(output)) {
					this.itemstacks[3].stackSize += output.stackSize;
				}
			}

			if (this.productTank.isEmpty()) {
				this.productTank.setFluid(second);
			} else if (this.productTank.getFluid().isFluidEqual(second))// secondのnull判定も兼ねてる
			{
				this.productTank.fill(second, true);
			}

			this.markDirty();
		}
	}

	/* ====== 以下、インベントリ関係 ====== */

	/*
	 * フードプロセッサーの場合
	 * 燃料スロット：0
	 * 燃料空容器の排出スロット：1
	 * 材料スロット：2
	 * 完成品スロット：3,4
	 * 材料のから容器排出：5
	 * 上記にプラスして排出用液体スロットひとつ
	 */

	@Override
	public int getSizeInventory() {
		return 6;
	}

	@Override
	protected int[] slotsTop() {
		return new int[] { 0, 2 };
	}

	@Override
	protected int[] slotsBottom() {
		return new int[] { 1, 3, 4, 5 };
	}

	@Override
	protected int[] slotsSides() {
		return new int[] { 0, 1, 2, 3, 4, 5 };
	}

	@Override
	public String getInventoryName() {
		return "Evaporator";
	}

	/* ====== 以下、IFluidHandlerの実装メソッド ====== */

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		if (resource == null)
			return null;
		if (productTank.getFluidType() == resource.getFluid())
			return productTank.drain(resource.amount, doDrain);
		return null;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return this.productTank.drain(maxDrain, doDrain);
	}

	// 外部からの液体の受け入れはなし
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		return 0;
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return false;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[] { productTank.getInfo() };
	}

	// BuildCraft対応
	@Optional.Method(modid = "BuildCraft|Core")
	@Override
	public ConnectOverride overridePipeConnection(PipeType type, ForgeDirection with) {
		return type == PipeType.FLUID ? ConnectOverride.CONNECT : ConnectOverride.DISCONNECT;
	}

}
