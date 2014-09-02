package virtuoel.unreal.item;

import java.util.List;

import virtuoel.unreal.MainMod;
import virtuoel.unreal.entity.Entity8BallRocket;
import virtuoel.unreal.entity.EntityRedeemerMissile;


import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ModWeaponRedeemer extends ModWeaponBase {
	
	//public Item ammo = MainMod.allItems.redeemerMissile;
	
	public ModWeaponRedeemer(int par1, Item weaponAmmo) {
		super(par1, weaponAmmo, 201, EnumAction.none);
	}
	
	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
		par3List.add("Ammo: " + (par1ItemStack.getMaxDamage()-par1ItemStack.getItemDamage())/100 + "/" + (par1ItemStack.getMaxDamage()-1)/100);
		if(this.consumeMode){
			par3List.add("Auto-Reload On");
		}else{
			par3List.add("Auto-Reload Off");
		}
    }

    @SideOnly(Side.CLIENT)

    /**
     * Return an item rarity from EnumRarity
     */
    public EnumRarity getRarity(ItemStack par1ItemStack)
    {
        return EnumRarity.epic;
    }
	
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if(par3EntityPlayer.isSneaking()){
			if (!par2World.isRemote)
			{
				//this.consumeMode = !this.consumeMode;
				consumeMode = !consumeMode;
				//if(consumeMode)par3EntityPlayer.addChatMessage("[Unreal] Mode switch not yet implemented.");
				if(consumeMode)par3EntityPlayer.addChatMessage("Auto-Reload Enabled.");
				if(!consumeMode)par3EntityPlayer.addChatMessage("Auto-Reload Disabled.");
			}
		}else{
			if(par1ItemStack.getItemDamage()<par1ItemStack.getMaxDamage()||par3EntityPlayer.capabilities.isCreativeMode){
				if(!par3EntityPlayer.capabilities.isCreativeMode)par1ItemStack.setItemDamage(par1ItemStack.getItemDamage()+100);	
				par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
				par2World.playSoundAtEntity(par3EntityPlayer, "random.explode", 0.5F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
				if (!par2World.isRemote)
				{
					par2World.spawnEntityInWorld(new EntityRedeemerMissile(par2World, par3EntityPlayer));
				}
			}
		}
		return par1ItemStack;
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
		return 30;
    }
	
	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5){
		super.onUpdate(par1ItemStack, par2World, par3Entity, par4, par5);
		if(this.consumeMode && par1ItemStack.getItemDamage()>1){
			if(((EntityPlayer)par3Entity).inventory.consumeInventoryItem(this.ammo.itemID)){
				par1ItemStack.setItemDamage(par1ItemStack.getItemDamage()-100);
			}
		}
	}
	
}
