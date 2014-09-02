package virtuoel.unreal.item;

import java.util.List;

import virtuoel.unreal.MainMod;
import virtuoel.unreal.entity.EntityChainsawAttack;
import virtuoel.unreal.entity.EntityTarydiumShard;


import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ModToolVoiceBox extends ModBasicItem {
	
	private int soundTick = 0;
	
	public ModToolVoiceBox(int par1) {
		super(par1);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		par3EntityPlayer.dropOneItem(false);
		return par1ItemStack;
	}

    @SideOnly(Side.CLIENT)
    /**
     * Return an item rarity from EnumRarity
     */
    public EnumRarity getRarity(ItemStack par1ItemStack)
    {
        return EnumRarity.uncommon;
    }
	
	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, int X, int Y, int Z, EntityPlayer player)
    {
        return false;
    }

    /**
     * Called by the default implemetation of EntityItem's onUpdate method, allowing for cleaner 
     * control over the update of the item without having to write a subclass.
     * 
     * @param entityItem The entity Item
     * @return Return true to skip any further update code.
     */
	@Override
    public boolean onEntityItemUpdate(EntityItem entityItem)
    {
		super.onEntityItemUpdate(entityItem);
		//onUpdate(entityItem.getEntityItem(), entityItem.worldObj, entityItem, 0, true);
		((ModToolVoiceBox)entityItem.getEntityItem().getItem()).soundTick++;
		if(((ModToolVoiceBox)entityItem.getEntityItem().getItem()).soundTick>=3){
			((ModToolVoiceBox)entityItem.getEntityItem().getItem()).soundTick  = 0;
			switch(itemRand.nextInt(7)){
				case 0:
					entityItem.worldObj.playSoundAtEntity(entityItem, "mob.irongolem.hit", 1.0F, 1.9F / (itemRand.nextFloat() * 0.4F + 0.8F));
					break;
				case 1:
					entityItem.worldObj.playSoundAtEntity(entityItem, "random.breath", 2.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
					break;
				case 2:
					entityItem.worldObj.playSoundAtEntity(entityItem, "mob.blaze.hit", 1.0F, 0.1F / (itemRand.nextFloat() * 0.4F + 0.8F));
					break;
				case 3:
					entityItem.worldObj.playSoundAtEntity(entityItem, "tile.piston.out", 0.5F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
					entityItem.worldObj.playSoundAtEntity(entityItem, "fireworks.launch", 1.0F, 0.1F / (itemRand.nextFloat() * 0.4F + 0.8F));
					break;
				case 4:
					entityItem.worldObj.playSoundAtEntity(entityItem, "random.explode", 0.5F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
					break;
				case 5:
					entityItem.worldObj.playSoundAtEntity(entityItem, "random.bow", 1.0F, 0.1F / (itemRand.nextFloat() * 0.4F + 0.8F));
					break;
				default:
					return false;
			}
		}
        return false;
    }
	
	/*
	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5){
		super.onUpdate(par1ItemStack, par2World, par3Entity, par4, par5);
		
		if(par5){
			((ModToolNoiseBox)par1ItemStack.getItem()).soundTick++;
			if(((ModToolNoiseBox)par1ItemStack.getItem()).soundTick>=3){
				((ModToolNoiseBox)par1ItemStack.getItem()).soundTick  = 0;
				par2World.playSoundAtEntity(par3Entity, "mob.irongolem.hit", 1.0F, 1.9F / (itemRand.nextFloat() * 0.4F + 0.8F));
			}
		}else if(((ModToolNoiseBox)par1ItemStack.getItem()).soundTick>0){
			((ModToolNoiseBox)par1ItemStack.getItem()).soundTick = 0;
		}
	}*/
	
}
