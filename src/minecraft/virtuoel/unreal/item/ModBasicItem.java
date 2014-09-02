package virtuoel.unreal.item;

import java.util.List;

import virtuoel.unreal.MainMod;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.*;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class ModBasicItem extends Item {
	
	EnumAction toUse = EnumAction.none;
	public boolean modIconsFlag = true;
	
	public ModBasicItem(int par1) {
		super(par1);
		this.toUse = EnumAction.none;
	}
	
	public ModBasicItem(int par1, EnumAction par2) {
		super(par1);
		this.toUse = par2;
	}
	
	public ModBasicItem(int par1, boolean iconFlag) {
		super(par1);
		this.modIconsFlag = iconFlag;
		this.toUse = EnumAction.none;
	}
	
	public ModBasicItem(int par1, EnumAction par2, boolean iconFlag) {
		super(par1);
		this.modIconsFlag = iconFlag;
		this.toUse = par2;
	}
	
	@SideOnly(Side.CLIENT)
	/**
	 * allows items to add custom lines of information to the mouseover description
	 */
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
		if(this.itemID==MainMod.allItems.shardTarydium.itemID){
			par3List.add("Stinger Ammo");
		}else if(
				this.itemID==MainMod.allItems.eightball.itemID){
			par3List.add("Eightball Launcher Ammo");
		}else if(
				this.itemID==MainMod.allItems.redeemerMissile.itemID){
			par3List.add("Redeemer Ammo");
		}else if(
				this.itemID==MainMod.allItems.flakShell.itemID){
			par3List.add("Flak Cannon Ammo");
		}else if(
				this.itemID==MainMod.allItems.boatLava.itemID){
			par3List.add("WIP/NYI");
		}
	}
	
    @SideOnly(Side.CLIENT)
    /**
     * Return an item rarity from EnumRarity
     */
    public EnumRarity getRarity(ItemStack par1ItemStack)
    {
    	if(this.itemID==MainMod.allItems.shardTarydium.itemID||
    	   this.itemID==MainMod.allItems.gemTarydium.itemID||
    	   this.itemID==MainMod.allItems.weaponCasingBasic.itemID||
    	   this.itemID==MainMod.allItems.tarydPowerBasic.itemID||
    	   this.itemID==MainMod.allItems.chainsawBlade.itemID){
    		return EnumRarity.uncommon;
    	}else if(
    	   this.itemID==MainMod.allItems.weaponCasingAdv.itemID||
    	   this.itemID==MainMod.allItems.chainsawBladeHardened.itemID||
    	   this.itemID==MainMod.allItems.dispersionUpgrade.itemID||
    	   this.itemID==MainMod.allItems.warheadBody.itemID||
    	   this.itemID==MainMod.allItems.warheadCone.itemID||
    	   this.itemID==MainMod.allItems.warheadThruster.itemID){
    		return EnumRarity.rare;
		}else if(
		   this.itemID==MainMod.allItems.redeemerMissile.itemID||
		   this.itemID==MainMod.allItems.chainsawMotor.itemID||
		   this.itemID==MainMod.allItems.translocatorDisc.itemID){
			return EnumRarity.epic;
		}
        return par1ItemStack.isItemEnchanted() ? EnumRarity.rare : EnumRarity.common;
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister)//updateIcons
	{
		String itemName = getUnlocalizedName(new ItemStack(this));
		itemName=itemName.substring(5);
		String iconName;
		if(modIconsFlag){
			iconName = MainMod.modName+":"+itemName;
		}else{
			iconName = itemName;
		}
		itemIcon = iconRegister.registerIcon(iconName);
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack){
		return this.toUse;
    }
	
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
		if(this.toUse != EnumAction.none)
		{
			par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
		}
        return par1ItemStack;
    }
	
	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
		if(this.toUse != EnumAction.none)
		{
			return 72000;
		}
		return 0;
    }
	
}
