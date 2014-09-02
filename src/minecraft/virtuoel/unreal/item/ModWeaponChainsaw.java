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

public class ModWeaponChainsaw extends ModWeaponBase {
	
	//public Item ammo = MainMod.allItems.shardTarydium;
	//int swingTick = 0;
	
	private int soundTick = 0;
	
	
	public ModWeaponChainsaw(int par1) {
		super(par1, null, 100, EnumAction.bow);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if(par3EntityPlayer.isSneaking()/*&&!par3EntityPlayer.isUsingItem()*/){
			if (!par2World.isRemote)
			{
				//this.consumeMode = !this.consumeMode;
				consumeMode = !consumeMode;
				//par3EntityPlayer.addChatMessage("[Unreal] Mode switch not yet implemented for this weapon.");
				if(consumeMode)par3EntityPlayer.addChatMessage("Motor Enabled.");
				if(!consumeMode)par3EntityPlayer.addChatMessage("Motor Disabled.");
			}
		}else{
			if(((ModWeaponChainsaw)par1ItemStack.getItem()).consumeMode/*this.consumeMode*/){
				if(par1ItemStack.getItemDamage()<par1ItemStack.getMaxDamage()||par3EntityPlayer.capabilities.isCreativeMode){
					par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
					par3EntityPlayer.setEating(false);
					//par1ItemStack.setItemDamage(par1ItemStack.getItemDamage()+1);
					/*
					if(itemRand.nextInt(3)!=0){
						par2World.playSoundAtEntity(par3EntityPlayer, "mob.irongolem.hit", 2.0F, 1.9F / (itemRand.nextFloat() * 0.4F + 0.8F));
	    			}
					 */
					if (!par2World.isRemote)
					{
						par2World.spawnEntityInWorld(new EntityChainsawAttack(par2World, par3EntityPlayer));
					}
				}
			}
		}
		return par1ItemStack;
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
	public int getDamageVsEntity(Entity par1Entity)
    {
		if(consumeMode){
	        return 20;
		}
		return 7;
    }

	@Override
    public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block)
    {
		if(!((ModWeaponChainsaw)par1ItemStack.getItem()).consumeMode){
	    	return 1.0F;
    	}
        if (par2Block.blockID == Block.web.blockID )
        {
            return 15.0F;
        }
        else
        {
            Material material = par2Block.blockMaterial;
            return par2Block.blockID != ModItems.cactusTarydium.blockID && par2Block.blockID != Block.cloth.blockID && material != Material.plants && material != Material.wood && material != Material.vine && material != Material.coral && material != Material.leaves && material != Material.pumpkin ? 1.0F : 10.0F;
        }
    }
	
    public boolean canHarvestBlock(Block par1Block)
    {
    	if(consumeMode){
    		Material material = par1Block.blockMaterial;
    		return !(par1Block.blockID != ModItems.cactusTarydium.blockID && par1Block.blockID != Block.web.blockID && par1Block.blockID != Block.cloth.blockID && material != Material.plants && material != Material.wood && material != Material.vine && material != Material.coral && material != Material.leaves && material != Material.pumpkin);
    	}
    	return false;
    }
	
	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
		if(consumeMode){
			par3List.add("Active");
		}else{
			par3List.add("Inactive");
		}
    }
	
	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
		return 1;
    }
	
	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, int X, int Y, int Z, EntityPlayer player)
    {
        return false;
    }
	
	@Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
		/*if(this.swingTick>=6){
			this.swingTick = 0;
			player.worldObj.playSoundAtEntity(player, "mob.irongolem.hit", 2.0F, 1.9F / (itemRand.nextFloat() * 0.4F + 0.8F));
		}*//*
		if(itemRand.nextInt(4)==3){
			player.worldObj.playSoundAtEntity(player, "mob.irongolem.hit", 2.0F, 1.9F / (itemRand.nextFloat() * 0.4F + 0.8F));
    	}*/
        return false;
    }
    
	@Override
	public boolean onEntitySwing(EntityLiving entityLiving, ItemStack stack)
    {
		//this.swingTick++;
		//if(this.swingTick>=8){
		//	this.swingTick = 0;
		/*if(itemRand.nextInt(5)==0){
			entityLiving.worldObj.playSoundAtEntity(entityLiving, "mob.irongolem.hit", 2.0F, 1.9F / (itemRand.nextFloat() * 0.4F + 0.8F));
    	}*/
		//}
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
		onUpdate(entityItem.getEntityItem(), entityItem.worldObj, entityItem, 0, true);
		
		
        return false;
    }
	
	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5){
		super.onUpdate(par1ItemStack, par2World, par3Entity, par4, par5);
		
		if(((ModWeaponChainsaw)par1ItemStack.getItem()).consumeMode && par5){
			((ModWeaponChainsaw)par1ItemStack.getItem()).soundTick++;
			if(((ModWeaponChainsaw)par1ItemStack.getItem()).soundTick>=3){
				((ModWeaponChainsaw)par1ItemStack.getItem()).soundTick  = 0;
				par2World.playSoundAtEntity(par3Entity, "mob.irongolem.hit", 1.0F, 1.9F / (itemRand.nextFloat() * 0.4F + 0.8F));
			}
		}else if(((ModWeaponChainsaw)par1ItemStack.getItem()).soundTick>0){
			((ModWeaponChainsaw)par1ItemStack.getItem()).soundTick = 0;
		}
		
		/*if(par1ItemStack.getItemDamage()>1){
			if(((EntityPlayer)par3Entity).inventory.consumeInventoryItem(this.ammo.itemID)){
				//System.out.println(par1ItemStack.getItemDamage()-1);
				par1ItemStack.setItemDamage(par1ItemStack.getItemDamage()-1);
			}
		}*/
	}
	
	
}
