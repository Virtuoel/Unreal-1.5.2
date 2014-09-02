package virtuoel.unreal.block;

import java.util.Random;

import virtuoel.unreal.MainMod;

import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.liquids.IBlockLiquid;

public class ModFluidBlock /*extends ModBasicBlock*/ implements IBlockLiquid{

	@Override
	public int stillLiquidId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isMetaSensitive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int stillLiquidMeta() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean willGenerateSources() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getFlowDistance() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public byte[] getLiquidRGB() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLiquidBlockTextureFile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NBTTagCompound getLiquidProperties() {
		// TODO Auto-generated method stub
		return null;
	}
	/*
	public ModFluidBlock(int par1, Material material) {
		super(par1, material);
		
	}*/
	
}