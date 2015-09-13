package mods.defeatedcrow.handler;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
import net.minecraftforge.oredict.OreDictionary;

public class FluidContMap {
	private FluidContMap() {
	}

	public static final FluidContMap instance = new FluidContMap();

	private static List<BottlePack> bottleList = new ArrayList<BottlePack>();

	public static void Register(Fluid f, FluidContainerData data) {
		if (f == null || data == null || data.filledContainer == null)
			return;

		boolean flag = true;
		BottlePack target = null;
		for (BottlePack b : bottleList) {
			if (b.getFluid() == f) {
				target = b;
			}
		}

		if (target != null) {
			for (ItemStack item : target.getAllContainer()) {
				if (isSameItem(data.filledContainer, item)) {
					flag = false;
				}
			}
			if (flag) {
				target.addList(data);
			}
		} else {
			BottlePack pack = instance.new BottlePack(f, data);
			bottleList.add(pack);
		}
	}

	private static boolean isSameItem(ItemStack in, ItemStack tar) {
		if (in == null || tar == null) {
			return false;
		} else {
			if (in.getItem() == tar.getItem()) {
				int a = in.getMaxDamage();
				int b = tar.getItemDamage();
				return a == b || b == OreDictionary.WILDCARD_VALUE;
			} else {
				return false;
			}
		}
	}

	public static BottlePack getPack(Fluid f) {
		if (bottleList.isEmpty()) {
			return null;
		}

		for (BottlePack b : bottleList) {
			if (b.getFluid() == f) {
				return b;
			}
		}
		return null;
	}

	public class BottlePack {
		public final Fluid fluid;
		public final List<FluidContainerData> dataList = new ArrayList<FluidContainerData>();

		public BottlePack(Fluid f, FluidContainerData d) {
			this.fluid = f;
			dataList.add(d);
		}

		public Fluid getFluid() {
			return fluid;
		}

		public List<FluidContainerData> getList() {
			return dataList;
		}

		public boolean addList(FluidContainerData data) {
			if (data != null && data.filledContainer != null) {
				return dataList.add(data);
			}
			return false;
		}

		public boolean isFilledContainer(ItemStack cont) {
			if (cont == null || cont.getItem() == null || dataList.isEmpty()) {
				return false;
			}

			boolean flag = false;
			for (FluidContainerData d : dataList) {
				if (match(cont, d.filledContainer)) {
					flag = true;
				}
			}
			return flag;
		}

		public List<ItemStack> getAllContainer() {
			List<ItemStack> ret = new ArrayList<ItemStack>();
			if (!dataList.isEmpty()) {
				for (FluidContainerData d : dataList) {
					if (d.filledContainer != null) {
						ret.add(d.filledContainer);
					}
				}
			}
			return ret;
		}

		private boolean match(ItemStack a, ItemStack b) {
			if (a != null && b != null) {
				if (a.getItem() == b.getItem()) {
					return a.getItemDamage() == b.getItemDamage() || b.getItemDamage() == OreDictionary.WILDCARD_VALUE;
				}
			}
			return false;
		}

	}

}
