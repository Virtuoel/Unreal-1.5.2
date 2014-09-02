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
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ModWeaponBase extends ModBasicItem {
	
	public Item ammo;
	protected boolean consumeMode = true;
	
	public ModWeaponBase(int par1, Item weaponAmmo, int damage, EnumAction action) {
		super(par1, action);
		this.ammo=weaponAmmo;
		this.setMaxStackSize(1);
		this.setNoRepair();
		this.setFull3D();
		this.setMaxDamage(damage);
		this.setHasSubtypes(true);
	}

    @SideOnly(Side.CLIENT)

    /**
     * Return an item rarity from EnumRarity
     */
    public EnumRarity getRarity(ItemStack par1ItemStack)
    {
        return EnumRarity.rare;
    }
	
	@SideOnly(Side.CLIENT)

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
		par3List.add("Ammo: " + (par1ItemStack.getMaxDamage()-par1ItemStack.getItemDamage()) + "/" + (par1ItemStack.getMaxDamage()-1));
    }
	
	@Override
	public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block)
    {
        return -1.0F;
    }
	
	@Override
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 1));
    }
	
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		return par1ItemStack;
	}
	
	@Override
	public int getDamageVsEntity(Entity par1Entity)
    {
        return 0;
    }
	
	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, int X, int Y, int Z, EntityPlayer player)
    {
        return true;
    }
    
	@Override
	public boolean onEntitySwing(EntityLiving entityLiving, ItemStack stack)
    {
        return true;
    }
	
	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
		return 0;
    }
	
	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5){
		if(par1ItemStack.getItemDamage()>par1ItemStack.getMaxDamage()){
			par1ItemStack.setItemDamage(par1ItemStack.getMaxDamage());
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
