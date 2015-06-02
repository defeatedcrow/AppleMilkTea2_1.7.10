package mods.defeatedcrow.common.world.village;

import java.util.List;
import java.util.Random;

import net.minecraft.util.MathHelper;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageCreationHandler;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;

public class VillageCreateHandleCafe implements IVillageCreationHandler {

	@Override
	public StructureVillagePieces.PieceWeight getVillagePieceWeight(Random random, int i) {
		return new StructureVillagePieces.PieceWeight(ComponentVillageCafe.class, 20,
				MathHelper.getRandomIntegerInRange(random, i, i + 1));
	}

	@Override
	public Class<?> getComponentClass() {
		return ComponentVillageCafe.class;
	}

	@Override
	public Object buildComponent(StructureVillagePieces.PieceWeight villagePiece,
			StructureVillagePieces.Start startPiece, List pieces, Random random, int p3, int p4, int p5, int p6, int p7) {
		StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p3, p4, p5, -1,
				-2, -1, 9, 8, 9, p6);
		return StructureComponent.findIntersecting(pieces, structureboundingbox) == null ? new ComponentVillageCafe(
				startPiece, p7, random, structureboundingbox, p6) : null;
	}

}
