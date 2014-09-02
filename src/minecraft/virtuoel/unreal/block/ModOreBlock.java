package virtuoel.unreal.block;

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
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;

public class ModOreBlock extends ModBasicBlock {
	
	public ModOreBlock(int par1, Material material) {
		super(par1, material);
		
	}
	
	@Override
	//drops when broken with pickaxe
	public int idDropped(int par1, Random par2Random, int par3){
		if(this.blockID==MainMod.allItems.oreTarydium.blockID){
			return MainMod.allItems.shardTarydium.itemID;
		}else if(this.blockID==MainMod.allItems.oreTarydiumNether.blockID){
			return MainMod.allItems.gemTarydium.itemID;
		}else if(this.blockID==MainMod.allItems.oreMagnesium.blockID){
			return MainMod.allItems.dustMagnesium.itemID;
		}else if(this.blockID==MainMod.allItems.oreAsbestos.blockID){
			return MainMod.allItems.dustAsbestos.itemID;
		}
		return this.blockID;
		
	}
	
	@Override
	public boolean canDragonDestroy(World world, int x, int y, int z)
    {
        return blockID != MainMod.allItems.oreTarydiumEnd.blockID||
        	   blockID != MainMod.allItems.oreTarydiumObsidian.blockID;
    }
	
	@Override
	public int quantityDropped(Random random){
		
		if(this.blockID==MainMod.allItems.oreTarydium.blockID){
			return 4 + random.nextInt(5);
		}else if(this.blockID==MainMod.allItems.oreMagnesium.blockID){
			return 1 + random.nextInt(4);
		}
		
		return 1;
		
	}
	
	@Override
	public int quantityDroppedWithBonus(int par1, Random par2Random)
    {
        if (par1 > 0 && this.blockID != this.idDropped(0, par2Random, par1))
        {
            int j = par2Random.nextInt(par1 + 2) - 1;

            if (j < 0)
            {
                j = 0;
            }
            
            return this.quantityDropped(par2Random) * (j + 1);
            
        }
        else
        {
            return this.quantityDropped(par2Random);
        }
    }
	
	@Override
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
    {
        super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, par7);

        if(this.idDropped(par5, par1World.rand, par7) != this.blockID)
        {
            int j1 = 0;
            
            if(this.blockID == MainMod.allItems.oreTarydium.blockID){
                j1 = MathHelper.getRandomIntegerInRange(par1World.rand, 3, 7);
            }else if(this.blockID == MainMod.allItems.oreMagnesium.blockID||
            		 this.blockID == MainMod.allItems.oreAsbestos.blockID){
                j1 = MathHelper.getRandomIntegerInRange(par1World.rand, 1, 5);
            }
            
            this.dropXpOnBlockBreak(par1World, par2, par3, par4, j1);
        }
    }
	
	@Override
	public boolean canSustainPlant(World world, int x, int y, int z, ForgeDirection direction, IPlantable plant){
		int plantID = plant.getPlantID(world, x, y + 1, z);
		
		if (plantID == MainMod.allItems.cactusTarydium.blockID && 
				blockMaterial == ModMaterial.tarydium
		   /*(blockID == MainMod.allItems.oreTarydium.blockID||
			blockID == MainMod.allItems.oreTarydiumEnd.blockID||
			blockID == MainMod.allItems.oreTarydiumNether.blockID||
			blockID == MainMod.allItems.oreTarydiumObsidian.blockID)*/){
			return true;
		}
		
		return false;
	}
	
}