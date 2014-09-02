package virtuoel.unreal.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import virtuoel.unreal.MainMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockJukeBox;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ModRecord extends ItemRecord
{
    /** List of all record items and their names. */
    private static final Map records = new HashMap();

    /** The name of the record. */
    public final String recordName;
    public final String recordDisplayName;
    public final String recordMaker;

    protected ModRecord(int par1, String title, String composer, String display)
    {
        super(par1, title);
        this.recordName = title;
        this.recordDisplayName = display;
        this.maxStackSize = 1;
        this.recordMaker = composer;
        this.setCreativeTab(MainMod.modTabAll);
        records.put(title, this);
    }

    protected ModRecord(int par1, String title, String composer)
    {
        super(par1, title);
        this.recordName = title;
        this.recordDisplayName = title;
        this.maxStackSize = 1;
        this.recordMaker = composer;
        this.setCreativeTab(MainMod.modTabAll);
        records.put(title, this);
    }

    @SideOnly(Side.CLIENT)

    /**
     * Gets an icon index based on an item's damage value
     */
    public Icon getIconFromDamage(int par1)
    {
        return this.itemIcon;
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    @Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        if (par3World.getBlockId(par4, par5, par6) == Block.jukebox.blockID && par3World.getBlockMetadata(par4, par5, par6) == 0)
        {
            if (par3World.isRemote)
            {
                return true;
            }
            else
            {
                ((BlockJukeBox)Block.jukebox).insertRecord(par3World, par4, par5, par6, par1ItemStack);
                par3World.playAuxSFXAtEntity((EntityPlayer)null, 1005, par4, par5, par6, this.itemID);
                --par1ItemStack.stackSize;
                return true;
            }
        }
        else
        {
            return false;
        }
    }

    @SideOnly(Side.CLIENT)

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
        par3List.add(this.getRecordTitle());
    }

    @SideOnly(Side.CLIENT)

    /**
     * Return the title for this record.
     */
    public String getRecordTitle()
    {
        return this.recordMaker + " - " + this.recordDisplayName;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Return an item rarity from EnumRarity
     */
    public EnumRarity getRarity(ItemStack par1ItemStack)
    {
        return EnumRarity.rare;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Return the record item corresponding to the given name.
     */
    public static ModRecord getRecord(String par0Str)
    {
        return (ModRecord)records.get(par0Str);
    }

    @SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		String itemName = getUnlocalizedName(new ItemStack(this));
		itemName=itemName.substring(5);
		itemName += this.recordName;
		itemIcon = iconRegister.registerIcon(MainMod.modName+":" + itemName);
	}
	
}
