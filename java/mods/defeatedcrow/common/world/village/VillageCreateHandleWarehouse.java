package mods.defeatedcrow.common.world.village;

import java.util.List;
import java.util.Random;

import net.minecraft.util.MathHelper;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageCreationHandler;

public class VillageCreateHandleWarehouse implements IVillageCreationHandler {

	@Override
	public StructureVillagePieces.PieceWeight getVillagePieceWeight(Random random, int i) {
		return new StructureVillagePieces.PieceWeight(ComponentVillageWarehouse.class, 20,
				MathHelper.getRandomIntegerInRange(random, i, i + 1));
	}

	@Override
	public Class<?> getComponentClass() {
		return ComponentVillageWarehouse.class;
	}

	@Override
	public Object buildComponent(StructureVillagePieces.PieceWeight villagePiece,
			StructureVillagePieces.Start startPiece, List pieces, Random random, int p3, int p4, int p5, int p6, int p7) {
		StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p3, p4, p5, 0, 0,
				0, 6, 9, 8, p6);
		return StructureComponent.findIntersecting(pieces, structureboundingbox) == null ? new ComponentVillageWarehouse(
				startPiece, p7, random, structureboundingbox, p6) : null;
	}

}
