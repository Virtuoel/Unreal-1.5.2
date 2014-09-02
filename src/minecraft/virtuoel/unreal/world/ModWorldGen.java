package virtuoel.unreal.world;

import java.util.Random;

import virtuoel.unreal.MainMod;


import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.IWorldGenerator;

public class ModWorldGen implements IWorldGenerator {

	private boolean debugFlag = false;

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch(world.provider.dimensionId){
		case -1: generateNether(world, random,chunkX*16,chunkZ*16);
		case 0 : generateSurface(world, random,chunkX*16,chunkZ*16);
		case 1 : generateEnd(world, random,chunkX*16,chunkZ*16);
		}
	}

	private void generateSurface(World world, Random random, int BlockX, int BlockZ) {
		doGenerateOre(world, random, BlockX, BlockZ, 
				16, 24, //range
				MainMod.allItems.oreRutile.blockID, 0, //id, data
				Block.stone.blockID, //replace
				5, 1, //base #, rand #
				7, //ore qty
				false, //emerald-type generation
				20 //debug orefinder block id
				);
		
		doGenerateOre(world, random, BlockX, BlockZ, 
				32, 64, //range
				MainMod.allItems.oreMagnesium.blockID, 0, //id, data
				Block.stone.blockID, //replace
				7, 1, //base #, rand #
				6, //ore qty
				false, //emerald-type generation
				21 //debug orefinder block id
				);
		
		doGenerateOre(world, random, BlockX, BlockZ, 
				6, 50, //range
				MainMod.allItems.oreTarydium.blockID, 0, //id, data
				Block.stone.blockID, //replace
				3, 6, //base #, rand #
				1, //ore qty
				true, //emerald-type generation
				22 //debug orefinder block id
				);
		
		doGenerateOre(world, random, BlockX, BlockZ, 
				6, 50, //range
				MainMod.allItems.oreStone.blockID, 0, //id, data
				Block.stone.blockID, //replace
				2, 2, //base #, rand #
				4, //ore qty
				false, //emerald-type generation
				23 //debug orefinder block id
				);
		
		doGenerateOre(world, random, BlockX, BlockZ, 
				2, 72, //range
				MainMod.allItems.oreAsbestos.blockID, 0, //id, data
				Block.stone.blockID, //replace
				6, 1, //base #, rand #
				5, //ore qty
				false, //emerald-type generation
				24 //debug orefinder block id
				);
		
		doGenerateOre(world, random, BlockX, BlockZ, 
				1, 4, //range
				MainMod.allItems.oreBedrockiumHidden.blockID, 0, //id, data
				Block.bedrock.blockID, //replace
				2, 2, //base #, rand #
				1, //ore qty
				true, //emerald-type generation
				25 //debug orefinder block id
				);
	}

	private void generateNether(World world, Random random, int BlockX, int BlockZ) {
		doGenerateOre(world, random, BlockX, BlockZ, 
				6, 126, //range
				MainMod.allItems.oreTarydiumNether.blockID, 0, //id, data
				Block.netherrack.blockID, //replace
				3, 6, //base #, rand #
				1, //ore qty
				true, //emerald-type generation
				20 //debug orefinder block id
				);
		
	}

	private void generateEnd(World world, Random random, int BlockX, int BlockZ) {
		doGenerateOre(world, random, BlockX, BlockZ, 
				24, 120, //range
				MainMod.allItems.oreTarydiumEnd.blockID, 0, //id, data
				Block.netherrack.blockID, //replace
				3, 6, //base #, rand #
				1, //ore qty
				true, //emerald-type generation
				20 //debug orefinder block id
				);
		
		doGenerateOre(world, random, BlockX, BlockZ, 
				24, 128, //range
				MainMod.allItems.oreTarydiumObsidian.blockID, 0, //id, data
				Block.obsidian.blockID, //replace
				3, 6, //base #, rand #
				1, //ore qty
				true, //emerald-type generation
				21 //debug orefinder block id
				);
		
	}

	private void doGenerateOre(World world, Random random, int BlockX, int BlockZ, int minY, int maxY, int oreID, int oreData, int worldRockID, int amountBase, int amountRandom, int amountOre, boolean singleBlock, int debugID){
		for(int i=0; i<amountBase+random.nextInt(amountRandom);i++){
			int Xcoord = BlockX + random.nextInt(16);
			int Zcoord = BlockZ + random.nextInt(16);
			int Ycoord = random.nextInt(maxY-minY)+minY;

			if(singleBlock){
				int idAtCoord = world.getBlockId(Xcoord, Ycoord, Zcoord);
				if (idAtCoord == worldRockID){
					world.setBlock(Xcoord, Ycoord, Zcoord, oreID, oreData, 2);
					if(debugFlag)world.setBlock(Xcoord, 130, Zcoord, debugID, 0, 2);
				}
			}else{
				new WorldGenMinable(oreID, amountOre)
				.generate(world, random, Xcoord, Ycoord, Zcoord);
				if(debugFlag)world.setBlock(Xcoord, 130, Zcoord, debugID, 0, 2);
			}
		}
	}
}
