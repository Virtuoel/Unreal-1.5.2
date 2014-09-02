package virtuoel.unreal.item;

import java.util.List;

import virtuoel.unreal.MainMod;
import virtuoel.unreal.entity.EntityDispersionCharge;
import virtuoel.unreal.entity.EntityTarydiumShard;


import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ModWeaponDispersionPistol extends ModWeaponBase {
	
	@SideOnly(Side.CLIENT)
    private Icon[] iconArray;
	
	public int upgradeLevel = 1;
	public static final int MAX_LEVEL = 4;
	public static int durabilityPerFire = 100;
	public static int ammoMax = 50;
	public static int durabilityRechargeRate = 1;
	
	public ModWeaponDispersionPistol(int par1, Item weaponAmmo) {
		super(par1, weaponAmmo, (ammoMax*durabilityPerFire)+1, EnumAction.none);
		consumeMode = false;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if(par3EntityPlayer.isSneaking()){
			if (!par2World.isRemote)
			{
				//this.consumeMode = !this.consumeMode;
				consumeMode = !consumeMode;
				//par3EntityPlayer.addChatMessage("[Unreal] Mode switch not yet implemented.");
				if(consumeMode)par3EntityPlayer.addChatMessage("Auto-Upgrade Enabled.");
				if(!consumeMode)par3EntityPlayer.addChatMessage("Auto-Upgrade Disabled.");
			}
		}else{
			if(par1ItemStack.getItemDamage()<par1ItemStack.getMaxDamage()-durabilityPerFire||par3EntityPlayer.capabilities.isCreativeMode){
				par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
				//par3EntityPlayer.setEating(false);
				if(!par3EntityPlayer.capabilities.isCreativeMode)par1ItemStack.setItemDamage(par1ItemStack.getItemDamage()+durabilityPerFire);
				par2World.playSoundAtEntity(par3EntityPlayer, "mob.blaze.hit", 1.0F, 0.1F / (itemRand.nextFloat() * 0.4F + 0.8F));
				if (!par2World.isRemote)
				{
					par2World.spawnEntityInWorld(new EntityDispersionCharge(par2World, par3EntityPlayer, this.upgradeLevel));
				}
			}

		}
		return par1ItemStack;
	}
	
	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
		par3List.add("Ammo: " + (par1ItemStack.getMaxDamage()-par1ItemStack.getItemDamage())/100 + "/" + (par1ItemStack.getMaxDamage()-1)/100);
		par3List.add("Upgrades: " + (this.upgradeLevel-1));
		if(this.consumeMode){
			par3List.add("Auto-Upgrade On");
		}else{
			par3List.add("Auto-Upgrade Off");
		}
    }
	
	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
		return 20*this.upgradeLevel;
    }
	
	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5){
		super.onUpdate(par1ItemStack, par2World, par3Entity, par4, par5);
		if(this.consumeMode && this.upgradeLevel<this.MAX_LEVEL){
			if(((EntityPlayer)par3Entity).inventory.consumeInventoryItem(this.ammo.itemID)){
				this.upgradeLevel++;
			}
		}
		if(par1ItemStack.getItemDamage()>1){
			par1ItemStack.setItemDamage(par1ItemStack.getItemDamage()-durabilityRechargeRate);
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		String itemName = getUnlocalizedName(new ItemStack(this));
		itemName=itemName.substring(5);
		itemIcon = iconRegister.registerIcon(MainMod.modName+":"+itemName);
		
		this.iconArray = new Icon[this.MAX_LEVEL];

        for (int i = 1; i < this.iconArray.length; ++i)
        {
            this.iconArray[i] = iconRegister.registerIcon(MainMod.modName+":dispersionPistol"+Integer.toString(i));
        }
	}
	
}
