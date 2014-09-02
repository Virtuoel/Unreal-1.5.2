package virtuoel.unreal.item;

import java.util.List;

import virtuoel.unreal.MainMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ModItemBlock extends ItemBlock{
	
	public ModItemBlock(int par1) {
		super(par1);
		// TODO Auto-generated constructor stub
	}
	
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
		if(super.onItemUse(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10)){
			return true;
		}else{
			
		}
		
		return false;
    }
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
		if(this.getBlockID()==MainMod.allItems.blockUUTC.blockID){
			par3List.add("Universally Usable Tarydium Composite");
		}else if(
				this.getBlockID()==MainMod.allItems.netherrackCompressed1x.blockID){
			par3List.add("9 Netherrack");
		}else if(
				this.getBlockID()==MainMod.allItems.netherrackCompressed2x.blockID){
			par3List.add("81 Netherrack");
		}else if(
				this.getBlockID()==MainMod.allItems.netherrackCompressed3x.blockID){
			par3List.add("729 Netherrack");
		}else if(
				this.getBlockID()==MainMod.allItems.netherrackCompressed4x.blockID){
			par3List.add("6561 Netherrack");
		}else if(
				this.getBlockID()==MainMod.allItems.endStoneCompressed1x.blockID){
			par3List.add("9 End Stone");
		}else if(
				this.getBlockID()==MainMod.allItems.endStoneCompressed2x.blockID){
			par3List.add("81 End Stone");
		}else if(
				this.getBlockID()==MainMod.allItems.endStoneCompressed3x.blockID){
			par3List.add("729 End Stone");
		}else if(
				this.getBlockID()==MainMod.allItems.endStoneCompressed4x.blockID){
			par3List.add("6561 End Stone");
		}
    }
}
