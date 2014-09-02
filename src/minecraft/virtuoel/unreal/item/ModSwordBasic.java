package virtuoel.unreal.item;

import virtuoel.unreal.MainMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

// *REMEBER* Change "ItemPickaxe" to ItemAxe, ItemHoe, ItemSword, 
//etc if you are making those tools!

public class ModSwordBasic extends ItemSword {
	
	public ModSwordBasic(int ItemID, EnumToolMaterial material){
		super(ItemID, material);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister)//updateIcons
	{
		String itemName = getUnlocalizedName(new ItemStack(this));
		itemName=itemName.substring(5);
		itemIcon = iconRegister.registerIcon(MainMod.modName+":"+itemName);
	}
	
}