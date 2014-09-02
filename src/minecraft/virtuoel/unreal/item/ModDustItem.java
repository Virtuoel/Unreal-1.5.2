package virtuoel.unreal.item;

import java.util.List;

import virtuoel.unreal.MainMod;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.*;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class ModDustItem extends Item {
	
	EnumAction toUse = EnumAction.none;
	
	public ModDustItem(int par1) {
		
		super(par1);
	}
	/*
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
		if(this.itemID==MainMod.allItems.shardTarydium.itemID){
			par3List.add("Stinger Ammo");
		}else if(
				this.itemID==MainMod.allItems.eightball.itemID){
			par3List.add("Eightball Launcher Ammo");
		}
	}*/
	/*
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack par1ItemStack)
    {
    	if(this.itemID==MainMod.allItems.shardTarydium.itemID||
    	   this.itemID==MainMod.allItems.gemTarydium.itemID||
    	   this.itemID==MainMod.allItems.weaponCasingBasic.itemID||
    	   this.itemID==MainMod.allItems.tarydPowerBasic.itemID){
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
    }*/
	
	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5){
		super.onUpdate(par1ItemStack, par2World, par3Entity, par4, par5);
		if(par3Entity instanceof EntityPlayer){
			if(par1ItemStack.itemID==MainMod.allItems.dustAsbestos.itemID){
				if(((EntityPlayer)par3Entity).inventory.armorItemInSlot(2)!=null&&
						((EntityPlayer)par3Entity).inventory.armorItemInSlot(2).itemID==
						MainMod.allItems.toxinSuit.itemID){
					if(itemRand.nextInt(100)==0){
						((EntityPlayer)par3Entity).inventory.armorItemInSlot(2).damageItem(1, (EntityPlayer)par3Entity);
	    			}
				}else{
					if(((EntityPlayer)par3Entity).getActivePotionEffect(Potion.poison)==null||
							((EntityPlayer)par3Entity).getActivePotionEffect(Potion.poison).duration<4){
						PotionEffect potioneffect = new PotionEffect(19, 59, 0, true);
						((EntityPlayer)par3Entity).addPotionEffect(potioneffect);
					}
				}
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
	
	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
		return 0;
    }
	
}
