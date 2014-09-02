package virtuoel.unreal.handler;

import virtuoel.unreal.item.ModItems;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public class ModFuelHandler implements IFuelHandler{
	
	@Override
	public int getBurnTime(ItemStack fuel) {
		if(fuel==null)return 0;
		if(fuel.getItem().itemID==ModItems.blockBlaze.blockID){
			return 2400*9;
		}else if(fuel.getItem().itemID==ModItems.blockCoal.blockID){
			return 1600*10;
		}else if(fuel.getItem().itemID==ModItems.blockCharcoal.blockID){
			return 1600*9;
		}else if(fuel.getItem().itemID==ModItems.debugTool.itemID){
			return 1600;
		}
		return 0;
	}
	
}
