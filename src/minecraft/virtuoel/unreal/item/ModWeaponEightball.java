package virtuoel.unreal.item;

import java.util.List;

import virtuoel.unreal.MainMod;
import virtuoel.unreal.entity.Entity8BallRocket;


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

public class ModWeaponEightball extends ModWeaponBase {
	
	//public Item ammo = MainMod.allItems.eightball;
	
	public ModWeaponEightball(int par1, Item weaponAmmo) {
		super(par1, weaponAmmo, 49, EnumAction.none);
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
				par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
				//par3EntityPlayer.setEating(false);
				if(!par3EntityPlayer.capabilities.isCreativeMode)par1ItemStack.setItemDamage(par1ItemStack.getItemDamage()+1);	
				par2World.playSoundAtEntity(par3EntityPlayer, "tile.piston.out", 0.5F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
				par2World.playSoundAtEntity(par3EntityPlayer, "fireworks.launch", 1.0F, 0.1F / (itemRand.nextFloat() * 0.4F + 0.8F));
				//"random.explode"
				if (!par2World.isRemote)
				{
					par2World.spawnEntityInWorld(new Entity8BallRocket(par2World, par3EntityPlayer));
				}
			}
		}
		return par1ItemStack;
	}
	
	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
		super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
		if(this.consumeMode){
			par3List.add("Auto-Reload On");
		}else{
			par3List.add("Auto-Reload Off");
		}
    }
	
	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5){
		super.onUpdate(par1ItemStack, par2World, par3Entity, par4, par5);
		if(this.consumeMode && par1ItemStack.getItemDamage()>1){
			if(((EntityPlayer)par3Entity).inventory.consumeInventoryItem(this.ammo.itemID)){
				par1ItemStack.setItemDamage(par1ItemStack.getItemDamage()-1);
			}
		}

	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
		return 60;
    }
	
}
