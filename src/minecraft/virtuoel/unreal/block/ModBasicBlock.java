package virtuoel.unreal.block;

import static net.minecraftforge.common.ForgeDirection.UP;

import java.util.List;
import java.util.Random;

import virtuoel.unreal.MainMod;
import virtuoel.unreal.block.material.ModMaterial;

import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderEnd;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;

public class ModBasicBlock extends Block {
	
	public int idToDrop = this.blockID;
	
	public ModBasicBlock(int par1, Material material) {
		super(par1, material);
	}
	
	public boolean modIconsFlag = true;
	
	public ModBasicBlock(int par1, Material material, boolean iconFlag) {
		super(par1, material);
		this.modIconsFlag = iconFlag;
	}
	
	@Override
	public boolean canDragonDestroy(World world, int x, int y, int z)
    {
        return !(blockID == MainMod.allItems.blockEnder.blockID ||
         	     blockID == MainMod.allItems.endStoneCompressed1x.blockID ||
                 blockID == MainMod.allItems.endStoneCompressed2x.blockID ||
                 blockID == MainMod.allItems.endStoneCompressed3x.blockID ||
                 blockID == MainMod.allItems.endStoneCompressed4x.blockID);
    }
	
    public boolean isFireSource(World world, int x, int y, int z, int metadata, ForgeDirection side)
    {
        return ((blockID == MainMod.allItems.netherrackCompressed1x.blockID ||
                 blockID == MainMod.allItems.netherrackCompressed2x.blockID ||
                 blockID == MainMod.allItems.netherrackCompressed3x.blockID ||
                 blockID == MainMod.allItems.netherrackCompressed4x.blockID)
                 && side == UP);
    }
	
	@Override
	public boolean isBeaconBase(World worldObj, int x, int y, int z, int beaconX, int beaconY, int beaconZ)
    {
        return (blockID == MainMod.allItems.blockTitanium.blockID ||
        		blockID == MainMod.allItems.blockTarydium.blockID ||
        		blockID == MainMod.allItems.blockMagnesium.blockID ||
        		blockID == MainMod.allItems.blockRutile.blockID ||
        		blockID == MainMod.allItems.blockEnder.blockID ||
        		blockID == MainMod.allItems.blockUUTC.blockID ||
        		blockID == MainMod.allItems.blockTarydiumAlloyRefined.blockID ||
        		blockID == MainMod.allItems.blockTarydiumAlloyUnrefined.blockID ||
        		blockID == MainMod.allItems.blockBlaze.blockID ||
        		blockID == MainMod.allItems.blockGhastTear.blockID ||
                blockID == MainMod.allItems.blockEnderCrystal.blockID||
                blockID == MainMod.allItems.blockBedrockium.blockID);
    }
	
	@Override
	public int idDropped(int par1, Random par2Random, int par3){
		//if(blockID == MainMod.allItems.stonePolished.blockID)return Block.stoneSingleSlab.blockID;
		return this.idToDrop;
	}
	
	@Override
	public int quantityDropped(Random random){
		//if(blockID == MainMod.allItems.stonePolished.blockID)return 2;
		return 1;
	}
	
	@Override
	public boolean canSustainPlant(World world, int x, int y, int z, ForgeDirection direction, IPlantable plant){
		int plantID = plant.getPlantID(world, x, y + 1, z);
		
		if (plantID == MainMod.allItems.cactusTarydium.blockID && 
				blockMaterial == ModMaterial.tarydium
			   /*(blockID == MainMod.allItems.blockTarydium.blockID ||
				blockID == MainMod.allItems.blockUUTC.blockID ||
        		blockID == MainMod.allItems.blockTarydiumAlloyRefined.blockID ||
        		blockID == MainMod.allItems.blockTarydiumAlloyUnrefined.blockID)*/){
			return true;
		}
		return false;
	}
	
	public void setModIconFlag(boolean iconFlag) {
		this.modIconsFlag = iconFlag;
	}
	
	public Block setIdToDrop(int idToDrop) {
		this.idToDrop = idToDrop;
		return this;
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
    {
		String blockName = getUnlocalizedName();
		blockName=blockName.substring(5);
		String iconName;
		if(modIconsFlag){
			iconName = MainMod.modName+":"+blockName;
		}else{
			iconName = blockName;
		}
		this.blockIcon = par1IconRegister.registerIcon(iconName);
	}
}
