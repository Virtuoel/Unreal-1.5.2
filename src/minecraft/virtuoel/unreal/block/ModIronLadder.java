package virtuoel.unreal.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;

import virtuoel.unreal.MainMod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Direction;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import net.minecraftforge.common.ForgeDirection;
import static net.minecraftforge.common.ForgeDirection.*;

public class ModIronLadder extends Block
{
	public ModIronLadder(int par1)
    {
        super(par1, Material.iron);
        this.setCreativeTab(CreativeTabs.tabDecorations);
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
        return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns the bounding box of the wired rectangular prism to render.
     */
    @Override
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
        return super.getSelectedBoundingBoxFromPool(par1World, par2, par3, par4);
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        this.updateLadderBounds(par1IBlockAccess.getBlockMetadata(par2, par3, par4));
    }

    /**
     * Update the ladder block bounds based on the given metadata value.
     */
    public void updateLadderBounds(int par1)
    {
        float f = 0.125F;

        if (par1 == 2)
        {
            this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
        }

        if (par1 == 3)
        {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
        }

        if (par1 == 4)
        {
            this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }

        if (par1 == 5)
        {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
        }
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * The type of render function that is called for this block
     */
    @Override
    public int getRenderType()
    {
        return 8;
    }
    
    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    @Override
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        return par1World.isBlockSolidOnSide(par2 - 1, par3, par4, EAST ) ||
               par1World.isBlockSolidOnSide(par2 + 1, par3, par4, WEST ) ||
               par1World.isBlockSolidOnSide(par2, par3, par4 - 1, SOUTH) ||
               par1World.isBlockSolidOnSide(par2, par3, par4 + 1, NORTH) ||
               par1World.getBlockId(par2, par3+1, par4)==this.blockID;
    }
    
    @Override
    public void onBlockClicked(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer)
    {
    	//this.ladderExtendAttempt(par1World, par2, par3, par4, par5EntityPlayer);
    }
    
    public void ladderExtendAttempt(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer)
    {
    	
    	if(par5EntityPlayer.getCurrentEquippedItem()!=null){
    		System.out.println(par5EntityPlayer.getCurrentEquippedItem().itemID);
    		if(par5EntityPlayer.getCurrentEquippedItem().itemID==this.blockID){
    			if(par1World.getBlockId(par2, par3-1, par4)==this.blockID){
    				if(par1World.getBlockMetadata(par2, par3-1, par4)==par1World.getBlockMetadata(par2, par3, par4)){
    					((ModIronLadder)(Block.blocksList[par1World.getBlockId(par2, par3, par4)])).ladderExtendAttempt(par1World, par2, par3, par4, par5EntityPlayer);
    				}
    			}else if(par1World.getBlockId(par2, par3-1, par4)==0){
    				par1World.setBlock(par2, par3-1, par4, this.blockID, par1World.getBlockMetadata(par2, par3, par4), 3);
    			}
    		}
    	}
    }
    
    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    @Override
    public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9)
    {
        int j1 = par9;
        
        if ((j1 == 0 || par5 == 1) && par1World.getBlockId(par2, par3+1, par4)==this.blockID)
        {
            j1 = par1World.getBlockMetadata(par2, par3+1, par4);
        }
        
        if ((j1 == 0 || par5 == 2) && par1World.isBlockSolidOnSide(par2, par3, par4 + 1, NORTH))
        {
            j1 = 2;
        }

        if ((j1 == 0 || par5 == 3) && par1World.isBlockSolidOnSide(par2, par3, par4 - 1, SOUTH))
        {
            j1 = 3;
        }

        if ((j1 == 0 || par5 == 4) && par1World.isBlockSolidOnSide(par2 + 1, par3, par4, WEST))
        {
            j1 = 4;
        }

        if ((j1 == 0 || par5 == 5) && par1World.isBlockSolidOnSide(par2 - 1, par3, par4, EAST))
        {
            j1 = 5;
        }

        return j1;
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    @Override
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        int i1 = par1World.getBlockMetadata(par2, par3, par4);
        boolean flag = false;

        if (i1 == 2 && (par1World.isBlockSolidOnSide(par2, par3, par4 + 1, NORTH)||(par1World.getBlockMetadata(par2, par3+1, par4)==i1&&par1World.getBlockId(par2, par3+1, par4)==this.blockID)))
        {
            flag = true;
        }

        if (i1 == 3 && (par1World.isBlockSolidOnSide(par2, par3, par4 - 1, SOUTH)||(par1World.getBlockMetadata(par2, par3+1, par4)==i1&&par1World.getBlockId(par2, par3+1, par4)==this.blockID)))
        {
            flag = true;
        }

        if (i1 == 4 && (par1World.isBlockSolidOnSide(par2 + 1, par3, par4, WEST)||(par1World.getBlockMetadata(par2, par3+1, par4)==i1&&par1World.getBlockId(par2, par3+1, par4)==this.blockID)))
        {
            flag = true;
        }

        if (i1 == 5 && (par1World.isBlockSolidOnSide(par2 - 1, par3, par4, EAST)||(par1World.getBlockMetadata(par2, par3+1, par4)==i1&&par1World.getBlockId(par2, par3+1, par4)==this.blockID)))
        {
            flag = true;
        }

        if (!flag)
        {
            this.dropBlockAsItem(par1World, par2, par3, par4, i1, 0);
            par1World.setBlockToAir(par2, par3, par4);
        }

        super.onNeighborBlockChange(par1World, par2, par3, par4, par5);
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    @Override
    public int quantityDropped(Random par1Random)
    {
        return 1;
    }

    @Override
    public boolean isLadder(World world, int x, int y, int z)
    {
        return true;
    }
    
    @SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
    {
		String blockName = getUnlocalizedName();
		blockName=blockName.substring(5);
        this.blockIcon = par1IconRegister.registerIcon(MainMod.modName+":"+blockName);
    }
    
}
