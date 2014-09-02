package virtuoel.unreal.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;

import virtuoel.unreal.MainMod;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class ModMultiTextureBlock extends ModBasicBlock
{
    @SideOnly(Side.CLIENT)
    private Icon topIcon;
    @SideOnly(Side.CLIENT)
    private Icon bottomIcon;
    @SideOnly(Side.CLIENT)
    private Icon northIcon;
    @SideOnly(Side.CLIENT)
    private Icon southIcon;
    @SideOnly(Side.CLIENT)
    private Icon eastIcon;
    @SideOnly(Side.CLIENT)
    private Icon westIcon;

    public ModMultiTextureBlock(int par1, Material material)
    {
        super(par1, material);
        this.setCreativeTab(MainMod.modTabAll);
    }
    
    public ModMultiTextureBlock(int par1, Material material, boolean iconFlag)
    {
        super(par1, material, iconFlag);
        this.setCreativeTab(MainMod.modTabAll);
    }

    @SideOnly(Side.CLIENT)

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    @Override
    public Icon getIcon(int par1, int par2)
    {
        return par1 == 1 ? this.topIcon : (par1 == 0 ? this.bottomIcon : (par1 == 2 ? this.northIcon : (par1 == 3 ? this.southIcon : (par1 == 5 ? this.eastIcon : (par1 == 4 ? this.westIcon : this.blockIcon)))));
    }

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
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
        this.topIcon = par1IconRegister.registerIcon(iconName+"_top");
        this.bottomIcon = par1IconRegister.registerIcon(iconName+"_bottom");
        this.northIcon = par1IconRegister.registerIcon(iconName+"_north");
        this.southIcon = par1IconRegister.registerIcon(iconName+"_south");
        this.eastIcon = par1IconRegister.registerIcon(iconName+"_east");
        this.westIcon = par1IconRegister.registerIcon(iconName+"_west");
    }
}
