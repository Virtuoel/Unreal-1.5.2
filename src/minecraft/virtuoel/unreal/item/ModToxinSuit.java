package virtuoel.unreal.item;

import virtuoel.unreal.MainMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ModToxinSuit extends ItemArmor{

	public ModToxinSuit(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
		super(par1, par2EnumArmorMaterial, par3, par4);
		setNoRepair();
	}
	
	@Override
	public boolean isValidArmor(ItemStack stack, int armorType)
    {
		if (armorType == 1)
	    {
	        return true;
	    }
		
	    return false;
    }
	
    @SideOnly(Side.CLIENT)

    /**
     * Return an item rarity from EnumRarity
     */
    public EnumRarity getRarity(ItemStack par1ItemStack)
    {
        return EnumRarity.uncommon;
    }
	
	/**
     * Return whether this item is repairable in an anvil.
     */
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
        return MainMod.allItems.ingotTitanium.itemID == par2ItemStack.itemID ? true : false;
    }
    
    @Override
    public void onArmorTickUpdate(World par1World, EntityPlayer par2EntityPlayer, ItemStack par3ItemStack){
    	if(par3ItemStack.itemID==this.itemID&&par3ItemStack.getItemDamage()<par3ItemStack.getMaxDamage()){
    		if(par2EntityPlayer.getActivePotionEffect(Potion.poison)!=null){
    			par2EntityPlayer.removePotionEffect(19);
    			//if(itemRand.nextInt(10)==0){
					par3ItemStack.damageItem(1, par2EntityPlayer);
					//par3ItemStack.setItemDamage(par3ItemStack.getItemDamage()+1);
    			//}
    		}
    		if(par2EntityPlayer.getActivePotionEffect(Potion.wither)!=null){
    			par2EntityPlayer.removePotionEffect(20);
				par3ItemStack.damageItem(5, par2EntityPlayer);
				//par3ItemStack.setItemDamage(par3ItemStack.getItemDamage()+5);
    		}
    		if(par2EntityPlayer.getActivePotionEffect(Potion.hunger)!=null){
    			par2EntityPlayer.removePotionEffect(17);
				par3ItemStack.damageItem(2, par2EntityPlayer);
				//par3ItemStack.setItemDamage(par3ItemStack.getItemDamage()+5);
    		}
    	}
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
