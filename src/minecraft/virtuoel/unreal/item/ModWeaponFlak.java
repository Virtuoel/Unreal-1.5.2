package virtuoel.unreal.item;

import java.util.List;

import virtuoel.unreal.MainMod;
import virtuoel.unreal.entity.EntityFlakShell;


import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ModWeaponFlak extends ModWeaponBase {
    
	public static final String[] bowPullIconNameArray = new String[] {"flakCannon1", "flakCannon2"};
	
	//public Item ammo= MainMod.allItems.flakShell;
	
	@SideOnly(Side.CLIENT)
    private Icon[] iconArray;
	
	public ModWeaponFlak(int par1, Item weaponAmmo) {
		super(par1, weaponAmmo, 51, EnumAction.none);
		this.ammo=weaponAmmo;
		this.setMaxStackSize(1);
		this.setNoRepair();
		this.setFull3D();
		this.setMaxDamage(51);
		this.setHasSubtypes(true);
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
			if((par1ItemStack.getItemDamage()<par1ItemStack.getMaxDamage()||par3EntityPlayer.capabilities.isCreativeMode)){//&&par3EntityPlayer.getItemInUseCount()==0){
				if(!par3EntityPlayer.capabilities.isCreativeMode)par1ItemStack.setItemDamage(par1ItemStack.getItemDamage()+1);
				par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
				//par3EntityPlayer.setEating(false);
				par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 0.1F / (itemRand.nextFloat() * 0.4F + 0.8F));
				if (!par2World.isRemote)
				{
					par2World.spawnEntityInWorld(new EntityFlakShell(par2World, par3EntityPlayer));
				}

			}
		}
		return par1ItemStack;
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
	public Icon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining)
    {
		if(useRemaining>30&&useRemaining<=35){
			return iconArray[0];
		}
		if(useRemaining>20&&useRemaining<=30){
			return iconArray[1];
		}
		if(useRemaining>5&&useRemaining<=20){
			return iconArray[0];
		}
		if(useRemaining<=5&&!(useRemaining<=0)){
			return this.itemIcon;
		}
		return this.itemIcon;
		
    }
	
	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
		return 35;
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		String itemName = getUnlocalizedName(new ItemStack(this));
		itemName=itemName.substring(5);
		itemIcon = iconRegister.registerIcon(MainMod.modName+":"+itemName);
		
		this.iconArray = new Icon[bowPullIconNameArray.length];

        for (int i = 0; i < this.iconArray.length; ++i)
        {
            this.iconArray[i] = iconRegister.registerIcon(MainMod.modName+":"+bowPullIconNameArray[i]);
        }
	}
	
}
