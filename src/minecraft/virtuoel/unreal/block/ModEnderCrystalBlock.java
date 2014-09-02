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
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderEnd;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;

public class ModEnderCrystalBlock extends ModMultiTextureBlock {
	
	public ModEnderCrystalBlock(int par1, Material material) {
		super(par1, material);
	}
	
	public ModEnderCrystalBlock(int par1, Material material, boolean iconFlag) {
		super(par1, material, iconFlag);
	}
	
	@Override
	public boolean canDragonDestroy(World world, int x, int y, int z)
    {
        return false;
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
    	if (par1World.isRemote)
        {
            return true;
        }
    	else
    	{
            List list = par1World.getEntitiesWithinAABB(EntityEnderCrystal.class, AxisAlignedBB.getAABBPool().getAABB((double)par2 + this.minX, (double)par3-2 + this.minY, (double)par4 + this.minZ, (double)par2 + this.maxX, (double)par3 + this.maxY, (double)par4 + this.maxZ));
            boolean flag1 = list.isEmpty();
    		if ((par1World.getBlockId(par2, par3-2, par4) == MainMod.allItems.oreBedrockiumHidden.blockID ||
    				par1World.getBlockId(par2, par3-2, par4) == MainMod.allItems.oreBedrockium.blockID ||
    				par1World.getBlockId(par2, par3-2, par4) == MainMod.allItems.blockBedrockium.blockID ||
    				par1World.getBlockId(par2, par3-2, par4) == Block.bedrock.blockID) &&
    				(par1World.getBlockId(par2, par3-1, par4) == 0 ||
    				par1World.getBlockId(par2, par3-1, par4) == Block.fire.blockID) &&
    				flag1)
    		{
    			par1World.setBlock(par2, par3, par4, 0, 0, 3);
    			par1World.playSoundEffect((double)par2 + 0.5D, (double)par3 + 0.5D, (double)par4 + 0.5D, "mob.ghast.fireball", 1.0F, par1World.rand.nextFloat() * 0.1F + 0.9F);
    			par1World.setBlock(par2, par3-1, par4, Block.fire.blockID, 0, 3);
    			EntityEnderCrystal entityendercrystal = new EntityEnderCrystal(par1World);
    			entityendercrystal.setLocationAndAngles((double)((float)par2 + 0.5F), (double)(par3 - 2), (double)((float)par4 + 0.5F), par1World.rand.nextFloat() * 360.0F, 0.0F);
    			par1World.spawnEntityInWorld(entityendercrystal);
    			return true;
    		}
    		else
    		{
    			return super.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9);
    		}
    	}
    }
	
	public boolean canCreatureSpawn(EnumCreatureType type, World world, int x, int y, int z)
    {
        return false;
    }
	
}