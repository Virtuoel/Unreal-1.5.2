package virtuoel.unreal.block;

import static net.minecraftforge.common.ForgeDirection.UP;

import java.util.List;
import java.util.Random;

import virtuoel.unreal.MainMod;

import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockStep;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderEnd;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;

public class ModBedrockiumBlock extends ModBasicBlock {
	
	public ModBedrockiumBlock(int par1, Material material) {
		super(par1, material);
	}
	
	public ModBedrockiumBlock(int par1, Material material, boolean iconFlag) {
		super(par1, material, iconFlag);
	}
	
	@Override
	public boolean canDragonDestroy(World world, int x, int y, int z)
    {
        return false;
    }
	
    public boolean isFireSource(World world, int x, int y, int z, int metadata, ForgeDirection side)
    {
    	if ((world.provider instanceof WorldProviderEnd) && 
    			(blockID == MainMod.allItems.oreBedrockiumHidden.blockID ||
   			 	 blockID == MainMod.allItems.oreBedrockium.blockID ||
    			 blockID == MainMod.allItems.blockBedrockium.blockID)
    			 && side == UP)
        {
            return true;
        }
        return false;
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        if (blockID == MainMod.allItems.oreBedrockiumHidden.blockID && par5EntityPlayer.getCurrentEquippedItem() != null && par5EntityPlayer.getCurrentEquippedItem().itemID == MainMod.allItems.bucketMercury.itemID)
        {
        	if (!par5EntityPlayer.capabilities.isCreativeMode)
            {
            	if(--par5EntityPlayer.getCurrentEquippedItem().stackSize>0){
                	par5EntityPlayer.setCurrentItemOrArmor(0,new ItemStack(Item.bucketEmpty,1,0));
            	}else if (!par5EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Item.bucketEmpty,1,0)))
                {
                	par5EntityPlayer.dropPlayerItem(new ItemStack(Item.bucketEmpty,1,0));
                }
            }
        	
			par1World.playSoundEffect((double)par2 + 0.5D, (double)par3 + 0.5D, (double)par4 + 0.5D, "random.fizz", 1.0F, par1World.rand.nextFloat() * 0.1F + 0.9F);
        	if(par1World.rand.nextInt(4)==0)par1World.setBlock(par2, par3, par4, MainMod.allItems.oreBedrockium.blockID, 0, 3);
            return true;
        }
        else
        {
            return super.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9);
        }
    }
	
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
    {
        int id = idPicked(world, x, y, z);
        
        if (id == MainMod.allItems.oreBedrockiumHidden.blockID)
        {
        	return new ItemStack(Block.bedrock.blockID, 1, 0);
        }
        
        return super.getPickBlock(target, world, x, y, z);
    }
	
	public boolean canCreatureSpawn(EnumCreatureType type, World world, int x, int y, int z)
    {
        return false;
    }
	
}