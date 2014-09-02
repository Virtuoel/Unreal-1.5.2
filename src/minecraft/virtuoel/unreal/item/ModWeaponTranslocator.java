package virtuoel.unreal.item;

import java.util.List;

import virtuoel.unreal.MainMod;
import virtuoel.unreal.entity.EntityTranslocatorDisc;


import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ModWeaponTranslocator extends ModWeaponBase {
	
	public EntityTranslocatorDisc disc = null;
	
	public boolean flag = true;
	
	public EntityPlayer user;

	public Icon itemIcon2;
	
	public ModWeaponTranslocator(int par1) {
		super(par1, null, 100, EnumAction.none);
	}
	
	@Override
	public Icon getIconFromDamage(int par1)
    {
		if(par1>50){
	        return this.itemIcon2;
		}
        return this.itemIcon;
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
		if(((ModWeaponTranslocator)(par1ItemStack.getItem())).disc==null&&par1ItemStack.getItemDamage()<par1ItemStack.getMaxDamage()){
			par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
			par3EntityPlayer.setEating(false);
			par1ItemStack.setItemDamage(100);
			par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 0.1F / (itemRand.nextFloat() * 0.4F + 0.8F));
			if (!par2World.isRemote)
			{
				EntityTranslocatorDisc newDisc = new EntityTranslocatorDisc(par2World, par3EntityPlayer);
				((ModWeaponTranslocator)(par1ItemStack.getItem())).setDisc(newDisc);
				par2World.spawnEntityInWorld(((ModWeaponTranslocator)(par1ItemStack.getItem())).disc);
			}
		
		}else{
			par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
			par3EntityPlayer.setEating(false);
			par1ItemStack.setItemDamage(1);
			boolean tpSound = true;
			if(((ModWeaponTranslocator)(par1ItemStack.getItem())).disc!=null){
				if(((ModWeaponTranslocator)(par1ItemStack.getItem())).disc.dimension==par3EntityPlayer.dimension&&((ModWeaponTranslocator)(par1ItemStack.getItem())).disc.posY<256+32){
					((ModWeaponTranslocator)(par1ItemStack.getItem())).disc.tp(par3EntityPlayer);
					tpSound = true;
					//par2World.playSoundAtEntity(par3EntityPlayer, "mob.endermen.portal", 1.0F, 1.0F);
				}else{
					tpSound = false;
					//par2World.playSoundAtEntity(par3EntityPlayer, "mob.wither.shoot", 0.5F, 1.0F);
				}
				if(((ModWeaponTranslocator)(par1ItemStack.getItem())).disc!=null){
					((ModWeaponTranslocator)(par1ItemStack.getItem())).disc.setDead();
					((ModWeaponTranslocator)(par1ItemStack.getItem())).disc=null;
				}
			}
			if(tpSound){
				par2World.playSoundAtEntity(par3EntityPlayer, "mob.endermen.portal", 1.0F, 1.0F);
			}else{
				par2World.playSoundAtEntity(par3EntityPlayer, "mob.wither.shoot", 0.5F, 1.0F);
			}
		}
		
		return par1ItemStack;
	}
	
	@SideOnly(Side.CLIENT)

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
		if(((ModWeaponTranslocator)(par1ItemStack.getItem())).disc!=null){
			double newX = ((ModWeaponTranslocator)(par1ItemStack.getItem())).disc.posX;
        	double newZ = ((ModWeaponTranslocator)(par1ItemStack.getItem())).disc.posZ;
        	
        	if(newX<0){
        		newX=(int)newX-.5;
        	}else{
        		newX=(int)newX+.5;
        	}
        	if(newZ<0){
        		newZ=(int)newZ-.5;
        	}else{
        		newZ=(int)newZ+.5;
        	}
        	if(this.disc.dimension==((ModWeaponTranslocator)(par1ItemStack.getItem())).disc.getThrower().dimension&&((ModWeaponTranslocator)(par1ItemStack.getItem())).disc.posY<256+32){
        		par3List.add("Active");
			}else{
				par3List.add("Out of Range");
			}
			par3List.add("X: " + newX);
        	par3List.add("Y: " + (int)((ModWeaponTranslocator)(par1ItemStack.getItem())).disc.posY);
        	par3List.add("Z: " + newZ);
        	
		}else{
    		par3List.add("Inactive");
			par3List.add("X: -");
        	par3List.add("Y: -");
        	par3List.add("Z: -");
		}
    }
    
	@Override
	public boolean onEntitySwing(EntityLiving entityLiving, ItemStack stack)
    {
		if(stack.getItemDamage()==100||((ModWeaponTranslocator)(stack.getItem())).disc!=null){
    		if(((ModWeaponTranslocator)(stack.getItem())).disc!=null){
    			((ModWeaponTranslocator)(stack.getItem())).disc.setDead();
    			((ModWeaponTranslocator)(stack.getItem())).disc=null;
    		}
    		entityLiving.worldObj.playSoundAtEntity(entityLiving, "mob.wither.shoot", 0.5F, 1.0F);
    		stack.setItemDamage(1);
    	}
		return true;
    }
	
	public void setDisc(EntityTranslocatorDisc discSet){
		disc = discSet;
	}
	
	/**
     * called when the player releases the use item button. Args: itemstack, world, entityplayer, itemInUseCount
     */
	@Override
    public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
    {
		if(par1ItemStack.getItemDamage()>=100&&((ModWeaponTranslocator)(par1ItemStack.getItem())).disc==null){
			par1ItemStack.setItemDamage(1);
		}else if(par1ItemStack.getItemDamage()<=1&&((ModWeaponTranslocator)(par1ItemStack.getItem())).disc!=null){
			par1ItemStack.setItemDamage(100);
		}
    }
	
	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5)
    {
		if(par1ItemStack.getItemDamage()>=100&&((ModWeaponTranslocator)(par1ItemStack.getItem())).disc==null){
			par1ItemStack.setItemDamage(1);
		}else if(par1ItemStack.getItemDamage()<=1&&((ModWeaponTranslocator)(par1ItemStack.getItem())).disc!=null){
			par1ItemStack.setItemDamage(100);
		}
    }
	
	@Override
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
		return 0;
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		String itemName = getUnlocalizedName(new ItemStack(this));
		itemName=itemName.substring(5);
		itemIcon = iconRegister.registerIcon(MainMod.modName+":"+itemName);
		itemIcon2 = iconRegister.registerIcon(MainMod.modName+":"+itemName+"Empty");
	}
	
}
