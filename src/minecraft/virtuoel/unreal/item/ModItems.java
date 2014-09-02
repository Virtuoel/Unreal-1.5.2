package virtuoel.unreal.item;

import virtuoel.unreal.MainMod;
import virtuoel.unreal.block.ModBasicBlock;
import virtuoel.unreal.block.ModCactusTarydium;
import virtuoel.unreal.block.ModEnderCrystalBlock;
import virtuoel.unreal.block.ModIronLadder;
import virtuoel.unreal.block.ModMultiTextureBlock;
import virtuoel.unreal.block.ModBedrockiumBlock;
import virtuoel.unreal.block.ModOreBlock;
import virtuoel.unreal.block.material.ModMaterial;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.*;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemCloth;
import net.minecraft.item.ItemRecord;

public class ModItems {
	
	//if resistance set *3 < 5*hardness, resistance = 5*hardness, from Block.java
	
	private static final int ITEM_ID_OFFSET = 256;
	
	//vanilla overrides
	
	
	
	//blocks
	
	public static final Block blockTitanium = new ModBasicBlock(3001, Material.iron)
	.setUnlocalizedName("blockTitanium").setHardness(5.0F)
	.setStepSound(Block.soundMetalFootstep).setResistance(10.0F)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Block oreTarydium = new ModOreBlock(3003, ModMaterial.tarydium)
	.setUnlocalizedName("oreTarydium").setHardness(4.0F)
	.setStepSound(Block.soundStoneFootstep).setResistance(1000.0F)
	.setLightValue(0.625F)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Block blockTarydium = new ModBasicBlock(3004, ModMaterial.tarydium)
	.setUnlocalizedName("blockTarydium").setHardness(6.0F)
	.setStepSound(Block.soundGlassFootstep).setResistance(1000.0F)
	.setLightValue(1.0F)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Block cactusTarydium = new ModCactusTarydium(3005)
	.setUnlocalizedName("cactusTarydium").setHardness(3.0F)
	.setStepSound(Block.soundClothFootstep).setResistance(1000.0F)
	.setLightValue(0.5F)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Block ladderIron = new ModIronLadder(3006)
	.setUnlocalizedName("ladderIron").setHardness(5.0F)
	.setStepSound(Block.soundLadderFootstep).setResistance(10.0F)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Block blockEnder = new ModBasicBlock(3007, Material.iron)
	.setUnlocalizedName("blockEnder").setHardness(3.0F).setLightValue(1.0F)
	.setStepSound(Block.soundGlassFootstep).setResistance(10.0F)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Block blockLeather = new ModBasicBlock(3008, Material.cloth, false)
	.setUnlocalizedName("itemframe_back").setHardness(0.8F)
	.setStepSound(Block.soundClothFootstep)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Block stoneMossySmooth = (ModBasicBlock)(new ModBasicBlock(3009, Material.rock))
	.setIdToDrop(Block.cobblestoneMossy.blockID)
	.setUnlocalizedName("stoneMossySmooth").setHardness(2.0F)
	.setStepSound(Block.soundStoneFootstep).setResistance(10.0F)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Block oreStone = new ModBasicBlock(3010, Material.rock)
	.setUnlocalizedName("oreStone").setHardness(3.0F)
	.setStepSound(Block.soundStoneFootstep).setResistance(5.0F)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Block tileStoneDecor = new ModBasicBlock(3011, Material.rock, false)
	.setUnlocalizedName("furnace_top").setHardness(3.0F)
	.setStepSound(Block.soundStoneFootstep).setResistance(5.0F)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Block oreTarydiumNether = new ModOreBlock(3012, ModMaterial.tarydium)
	.setUnlocalizedName("oreTarydiumNether").setHardness(3.0F)
	.setStepSound(Block.soundStoneFootstep)//.setLightValue(0.625F)
	.setResistance(1000.0F).setCreativeTab(MainMod.modTabAll);
	
	public static final Block oreTarydiumEnd = new ModOreBlock(3013, ModMaterial.tarydium)
	.setUnlocalizedName("oreTarydiumEnd").setHardness(3.0F)
	.setStepSound(Block.soundStoneFootstep)//.setLightValue(0.625F)
	.setResistance(1000.0F).setCreativeTab(MainMod.modTabAll);
	
	public static final Block oreTarydiumObsidian = new ModOreBlock(3014, ModMaterial.tarydium)
	.setUnlocalizedName("oreTarydiumObsidian").setHardness(50.0F)
	.setStepSound(Block.soundStoneFootstep)//.setLightValue(0.625F)
	.setResistance(1000.0F).setCreativeTab(MainMod.modTabAll);
	
	public static final Block oreRutile = new ModOreBlock(3002, Material.rock)
	.setUnlocalizedName("oreRutile").setHardness(3.0F)
	.setStepSound(Block.soundStoneFootstep).setResistance(5.0F)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Block blockRutile = new ModBasicBlock(3015, Material.iron)
	.setUnlocalizedName("blockRutile").setHardness(5.0F)
	.setStepSound(Block.soundMetalFootstep).setResistance(10.0F)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Block oreMagnesium = new ModOreBlock(3016, Material.rock)
	.setUnlocalizedName("oreMagnesium").setHardness(3.0F)
	.setStepSound(Block.soundStoneFootstep).setResistance(5.0F)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Block blockMagnesium = new ModBasicBlock(3017, Material.iron)
	.setUnlocalizedName("blockMagnesium").setHardness(3.0F)
	.setStepSound(Block.soundMetalFootstep).setResistance(5.0F)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Block blockUUTC = new ModBasicBlock(3018, ModMaterial.tarydium)
	.setUnlocalizedName("blockWildcard").setHardness(50.0F)
	.setStepSound(Block.soundMetalFootstep).setResistance(1000.0F)
	.setLightValue(1.0F)
	.setCreativeTab(CreativeTabs.tabAllSearch);
	
	public static final Block netherrackCompressed1x = new ModBasicBlock(3019, Material.rock)
	.setUnlocalizedName("netherrackCompressed1x").setHardness(0.8F)
	.setStepSound(Block.soundStoneFootstep)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Block netherrackCompressed2x = new ModBasicBlock(3020, Material.rock)
	.setUnlocalizedName("netherrackCompressed2x").setHardness(1.2F)
	.setStepSound(Block.soundStoneFootstep)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Block netherrackCompressed3x = new ModBasicBlock(3021, Material.rock)
	.setUnlocalizedName("netherrackCompressed3x").setHardness(1.6F)
	.setStepSound(Block.soundStoneFootstep)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Block netherrackCompressed4x = new ModBasicBlock(3022, Material.rock)
	.setUnlocalizedName("netherrackCompressed4x").setHardness(2.0F)
	.setStepSound(Block.soundStoneFootstep)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Block endStoneCompressed1x = new ModBasicBlock(3023, Material.rock)
	.setUnlocalizedName("endStoneCompressed1x").setHardness(6.0F)
	.setStepSound(Block.soundStoneFootstep)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Block endStoneCompressed2x = new ModBasicBlock(3024, Material.rock)
	.setUnlocalizedName("endStoneCompressed2x").setHardness(9.0F)
	.setStepSound(Block.soundStoneFootstep)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Block endStoneCompressed3x = new ModBasicBlock(3025, Material.rock)
	.setUnlocalizedName("endStoneCompressed3x").setHardness(12.0F)
	.setStepSound(Block.soundStoneFootstep)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Block endStoneCompressed4x = new ModBasicBlock(3026, Material.rock)
	.setUnlocalizedName("endStoneCompressed4x").setHardness(15.0F)
	.setStepSound(Block.soundStoneFootstep)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Block blockTarydiumAlloyUnrefined = new ModBasicBlock(3027, ModMaterial.tarydium)
	.setUnlocalizedName("blockTarydiumAlloyUnrefined").setHardness(100.0F)
	.setStepSound(Block.soundMetalFootstep).setResistance(1000.0F)
	.setLightValue(0.5F)
	.setCreativeTab(CreativeTabs.tabAllSearch);
	
	public static final Block blockTarydiumAlloyRefined = new ModBasicBlock(3028, ModMaterial.tarydium)
	.setUnlocalizedName("blockTarydiumAlloyRefined").setHardness(200.0F)
	.setStepSound(Block.soundMetalFootstep).setResistance(1000.0F)
	.setCreativeTab(CreativeTabs.tabAllSearch);
	
	public static final Block blockFlint = new ModBasicBlock(3029, Material.rock)
	.setUnlocalizedName("blockFlint").setHardness(3.0F)
	.setStepSound(Block.soundStoneFootstep).setResistance(5.0F)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Block blockGunpowder = new ModBasicBlock(3030, Material.sand)
	.setUnlocalizedName("blockGunpowder").setHardness(1.2F)
	.setStepSound(Block.soundSandFootstep).setResistance(2.0F)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Block blockBlaze = new ModBasicBlock(3031, Material.iron)
	.setUnlocalizedName("blockBlaze").setHardness(5.0F)
	.setStepSound(Block.soundStoneFootstep).setResistance(10.0F)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Block blockGhastTear = new ModBasicBlock(3032, Material.iron)
	.setUnlocalizedName("blockTear").setHardness(5.0F)
	.setStepSound(Block.soundGlassFootstep).setResistance(10.0F)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Block blockSlime = new ModBasicBlock(3033, Material.grass)
	.setUnlocalizedName("slime").setHardness(0.8F)
	.setStepSound(Block.soundGravelFootstep)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Block blockNetherWart = new ModBasicBlock(3034, Material.clay)
	.setUnlocalizedName("blockNetherWart").setHardness(0.8F)
	.setStepSound(Block.soundGravelFootstep)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Block oreAsbestos = new ModOreBlock(3035, Material.rock)
	.setUnlocalizedName("oreAsbestos").setHardness(3.0F)
	.setStepSound(Block.soundStoneFootstep).setResistance(5.0F)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Block blockAsbestos = new ModBasicBlock(3036, Material.rock)
	.setUnlocalizedName("blockAsbestos").setHardness(3.0F)
	.setStepSound(Block.soundStoneFootstep).setResistance(5.0F)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Block blockCoal = new ModBasicBlock(3037, Material.rock)
	.setUnlocalizedName("blockCoal").setHardness(3.0F)
	.setStepSound(Block.soundStoneFootstep).setResistance(5.0F)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Block blockCharcoal = new ModBasicBlock(3038, Material.rock)
	.setUnlocalizedName("blockCharcoal").setHardness(3.0F)
	.setStepSound(Block.soundStoneFootstep).setResistance(5.0F)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Block blockEnderCrystal = new ModEnderCrystalBlock(3039, ModMaterial.immobile)
	.setUnlocalizedName("blockEnderCrystal").setHardness(200.0F)
	.setStepSound(Block.soundGlassFootstep).setResistance(1000.0F)
	.setLightValue(1.0F)
	.setCreativeTab(CreativeTabs.tabAllSearch);
	
	public static final Block oreBedrockium = new ModBedrockiumBlock(3040, ModMaterial.bedrock)
	.setUnlocalizedName("oreBedrockium").setHardness(1000.0F)
	.setStepSound(Block.soundMetalFootstep).setResistance(500000.0F)
	.setCreativeTab(CreativeTabs.tabAllSearch);
	
	public static final Block oreBedrockiumHidden = new ModBedrockiumBlock(3041, Material.rock, false)
	.setUnlocalizedName("bedrock").setBlockUnbreakable()
	.setStepSound(Block.soundMetalFootstep).setResistance(8000000.0F)
	.setCreativeTab(CreativeTabs.tabAllSearch);
	
	public static final Block blockBedrockium = new ModBedrockiumBlock(3042, ModMaterial.bedrock)
	.setUnlocalizedName("blockBedrockium").setHardness(1000.0F)
	.setStepSound(Block.soundStoneFootstep).setResistance(500000.0F)
	.setCreativeTab(CreativeTabs.tabAllSearch);
	
	public static final Block stonePolished = new ModBasicBlock(3043, Material.rock, false)
	.setUnlocalizedName("stoneslab_top").setHardness(2.0F)
	.setStepSound(Block.soundStoneFootstep).setResistance(10.0F)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Block slabStoneSmooth = new ModBasicBlock(3044, Material.rock, false)
	.setUnlocalizedName("stone").setHardness(2.0F)
	.setStepSound(Block.soundStoneFootstep).setResistance(10.0F)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Block blockCinnabar = new ModBasicBlock(3045, Material.rock)
	.setUnlocalizedName("blockCinnabar").setHardness(5.0F)
	.setStepSound(Block.soundMetalFootstep).setResistance(10.0F)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Block obsidianNether = new ModBasicBlock(3046, ModMaterial.immobile)
	.setUnlocalizedName("obsidianNether").setHardness(50.0F)
	.setStepSound(Block.soundStoneFootstep).setResistance(2000.0F)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Block blockCocoa = new ModBasicBlock(3047, Material.wood)
	.setUnlocalizedName("blockCocoa").setHardness(1.0F)
	.setStepSound(Block.soundWoodFootstep)
	.setCreativeTab(MainMod.modTabAll);
	
	//items
	
	public static final Item ingotTitanium = new ModBasicItem(3200-ITEM_ID_OFFSET)
	.setUnlocalizedName("ingotTitanium")
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item shardTarydium = new ModBasicItem(3201-ITEM_ID_OFFSET)
	.setUnlocalizedName("shardTarydium")
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item gemTarydium = new ModBasicItem(3202-ITEM_ID_OFFSET)
	.setUnlocalizedName("gemTarydium")
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item swordTitanium = new ModSwordBasic(3203-ITEM_ID_OFFSET, MainMod.EnumToolMaterialTitanium)
	.setUnlocalizedName("swordTitanium")
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item pickTitanium = new ModPickBasic(3204-ITEM_ID_OFFSET, MainMod.EnumToolMaterialTitanium)
	.setUnlocalizedName("pickaxeTitanium")
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item shovelTitanium = new ModShovelBasic(3205-ITEM_ID_OFFSET, MainMod.EnumToolMaterialTitanium)
	.setUnlocalizedName("shovelTitanium")
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item axeTitanium = new ModAxeBasic(3206-ITEM_ID_OFFSET, MainMod.EnumToolMaterialTitanium)
	.setUnlocalizedName("axeTitanium")
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item hoeTitanium = new ModHoeBasic(3207-ITEM_ID_OFFSET, MainMod.EnumToolMaterialTitanium)
	.setUnlocalizedName("hoeTitanium")
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item nuggetTitanium = new ModBasicItem(3208-ITEM_ID_OFFSET)
	.setUnlocalizedName("nuggetTitanium")
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item weaponCasingBasic = new ModBasicItem(3209-ITEM_ID_OFFSET, EnumAction.block)
	.setUnlocalizedName("weaponCaseBasic2")
	.setFull3D()
	.setMaxStackSize(8)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item tarydPowerBasic = new ModBasicItem(3210-ITEM_ID_OFFSET)
	.setUnlocalizedName("powerCrystal8")
	.setMaxStackSize(1)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item caseTitanium = new ModBasicItem(3211-ITEM_ID_OFFSET)
	.setUnlocalizedName("caseTitanium")
	.setMaxStackSize(16)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item eightball = new ModBasicItem(3212-ITEM_ID_OFFSET)
	.setUnlocalizedName("eightball")
	.setMaxStackSize(16)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item nuggetIron = new ModBasicItem(3213-ITEM_ID_OFFSET)
	.setUnlocalizedName("nuggetIron")
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item shieldBelt = new ModShieldBelt(3214-ITEM_ID_OFFSET, MainMod.EnumArmorMaterialShield, 4, 2)
	.setUnlocalizedName("shieldBelt")
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item gunEightball = (new ModWeaponEightball(3215-ITEM_ID_OFFSET, MainMod.allItems.eightball))
	.setUnlocalizedName("eightballLauncher")
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item gunBarrelIron = new ModBasicItem(3216-ITEM_ID_OFFSET)
	.setUnlocalizedName("gunBarrelIron")
	.setMaxStackSize(16)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item gunStinger = (new ModWeaponStinger(3217-ITEM_ID_OFFSET, MainMod.allItems.shardTarydium))
	.setUnlocalizedName("stinger")
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item translocatorDisc = new ModBasicItem(3218-ITEM_ID_OFFSET)
	.setUnlocalizedName("translocatorDisc")
	.setMaxStackSize(1)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item gunTranslocator = new ModWeaponTranslocator(3219-ITEM_ID_OFFSET)
	.setUnlocalizedName("translocator")
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item weaponCasingAdv = new ModBasicItem(3220-ITEM_ID_OFFSET, EnumAction.block)
	.setUnlocalizedName("weaponCaseBasic1")
	.setFull3D()
	.setMaxStackSize(8)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item jumpBoots = new ModJumpBoots(3221-ITEM_ID_OFFSET, EnumArmorMaterial.IRON, 2, 3)
	.setUnlocalizedName("jumpBoots")
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item flakShell = new ModBasicItem(3222-ITEM_ID_OFFSET)
	.setUnlocalizedName("flakShell")
	.setMaxStackSize(16)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item flak = new ModBasicItem(3223-ITEM_ID_OFFSET)
	.setUnlocalizedName("flak")
	.setMaxStackSize(64)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item gunFlak = new ModWeaponFlak(3224-ITEM_ID_OFFSET, MainMod.allItems.flakShell)
	.setUnlocalizedName("flakCannon")
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item flakShellWarm = new ModBasicItem(3225-ITEM_ID_OFFSET)
	.setUnlocalizedName("flakShellWarm")
	.setMaxStackSize(16)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item flakShellCold = new ModBasicItem(3226-ITEM_ID_OFFSET)
	.setUnlocalizedName("flakShellCold")
	.setMaxStackSize(16)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item caseIron = new ModBasicItem(3227-ITEM_ID_OFFSET)
	.setUnlocalizedName("caseIron")
	.setMaxStackSize(16)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item ingotStone = new ModBasicItem(3228-ITEM_ID_OFFSET)
	.setUnlocalizedName("ingotStone")
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item redeemerMissile = new ModBasicItem(3229-ITEM_ID_OFFSET)
	.setUnlocalizedName("redeemerMissile")
	.setMaxStackSize(2)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item gunRedeemer = new ModWeaponRedeemer(3230-ITEM_ID_OFFSET, MainMod.allItems.redeemerMissile)
	.setUnlocalizedName("redeemer")
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item warheadCone = new ModBasicItem(3231-ITEM_ID_OFFSET)
	.setUnlocalizedName("warheadCone")
	.setMaxStackSize(1)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item warheadBody = new ModBasicItem(3232-ITEM_ID_OFFSET)
	.setUnlocalizedName("warheadBody")
	.setMaxStackSize(1)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item warheadThruster = new ModBasicItem(3233-ITEM_ID_OFFSET)
	.setUnlocalizedName("warheadThruster")
	.setMaxStackSize(1)
	.setCreativeTab(MainMod.modTabAll);

	public static final Item boatLava = new ModBasicItem(3234-ITEM_ID_OFFSET)//ModLavaBoat(3234-ITEM_ID_OFFSET)
	.setUnlocalizedName("boatLava")
	.setMaxStackSize(3)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item meleeChainsaw = new ModWeaponChainsaw(3235-ITEM_ID_OFFSET)
	.setUnlocalizedName("chainsaw")
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item chainPart = new ModBasicItem(3236-ITEM_ID_OFFSET)
	.setUnlocalizedName("chainPart")
	.setMaxStackSize(32)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item chainsawBlade = new ModBasicItem(3237-ITEM_ID_OFFSET)
	.setUnlocalizedName("chainsawBlade")
	.setMaxStackSize(8)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item chainsawBladeHardened = new ModBasicItem(3238-ITEM_ID_OFFSET)
	.setUnlocalizedName("chainsawBladeHardened")
	.setMaxStackSize(8)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item chainsawMotor = new ModBasicItem(3239-ITEM_ID_OFFSET, EnumAction.block)
	.setUnlocalizedName("chainsawMotor")
	.setMaxStackSize(4)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item gunBarrelIronLarge = new ModBasicItem(3240-ITEM_ID_OFFSET)
	.setUnlocalizedName("gunBarrelIronLarge")
	.setMaxStackSize(8)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item dispersionUpgrade = new ModBasicItem(3241-ITEM_ID_OFFSET)
	.setUnlocalizedName("dispersionUpgrade")
	.setMaxStackSize(ModWeaponDispersionPistol.MAX_LEVEL)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item gunDispersionPistol = new ModWeaponDispersionPistol(3242-ITEM_ID_OFFSET,MainMod.allItems.dispersionUpgrade)
	.setUnlocalizedName("dispersionPistol")
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item dustMagnesium = new ModDustItem(3243-ITEM_ID_OFFSET)
	.setUnlocalizedName("dustMagnesium")
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item dustRutile = new ModDustItem(3244-ITEM_ID_OFFSET)
	.setUnlocalizedName("dustRutile")
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item dustTitanium = new ModBasicItem(3245-ITEM_ID_OFFSET)
	.setUnlocalizedName("dustTitanium")
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item ingotMagnesium = new ModBasicItem(3246-ITEM_ID_OFFSET)
	.setUnlocalizedName("ingotMagnesium")
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item ingotRutile = new ModBasicItem(3247-ITEM_ID_OFFSET)
	.setUnlocalizedName("ingotRutile")
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item nuggetMagnesium = new ModBasicItem(3248-ITEM_ID_OFFSET)
	.setUnlocalizedName("nuggetMagnesium")
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item nuggetRutile = new ModBasicItem(3249-ITEM_ID_OFFSET)
	.setUnlocalizedName("nuggetRutile")
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item toolVoiceBox = new ModToolVoiceBox(3250-ITEM_ID_OFFSET)
	.setUnlocalizedName("voiceBox")
	.setMaxStackSize(1)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item ingotTarydiumAlloyUnrefined = new ModBasicItem(3251-ITEM_ID_OFFSET)
	.setUnlocalizedName("ingotTarydiumAlloyUnrefined")
	.setCreativeTab(CreativeTabs.tabAllSearch);
	
	public static final Item nuggetTarydiumAlloyUnrefined = new ModBasicItem(3252-ITEM_ID_OFFSET)
	.setUnlocalizedName("nuggetTarydiumAlloyUnrefined")
	.setCreativeTab(CreativeTabs.tabAllSearch);
	
	public static final Item ingotTarydiumAlloyRefined = new ModBasicItem(3253-ITEM_ID_OFFSET)
	.setUnlocalizedName("ingotTarydiumAlloyRefined")
	.setCreativeTab(CreativeTabs.tabAllSearch);
	
	public static final Item nuggetTarydiumAlloyRefined = new ModBasicItem(3254-ITEM_ID_OFFSET)
	.setUnlocalizedName("nuggetTarydiumAlloyRefined")
	.setCreativeTab(CreativeTabs.tabAllSearch);
	
	public static final Item bucketMercury = new ModBasicItem(3255-ITEM_ID_OFFSET)
	.setUnlocalizedName("bucketMercury")
	.setContainerItem(Item.bucketEmpty)
	.setMaxStackSize(1)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item asbestosSuit = new ModAsbestosSuit(3256-ITEM_ID_OFFSET, EnumArmorMaterial.CHAIN, 1, 1)
	.setUnlocalizedName("asbestosSuit")
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item dustTarydiumAlloyRefined = new ModDustItem(3257-ITEM_ID_OFFSET)
	.setUnlocalizedName("dustTarydiumAlloyRefined")
	.setCreativeTab(CreativeTabs.tabAllSearch);
	
	public static final Item dustTarydiumAlloyUnrefined = new ModDustItem(3258-ITEM_ID_OFFSET)
	.setUnlocalizedName("dustTarydiumAlloyUnrefined")
	.setCreativeTab(CreativeTabs.tabAllSearch);
	
	public static final Item dustIron = new ModDustItem(3259-ITEM_ID_OFFSET)
	.setUnlocalizedName("dustIron")
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item dustGold = new ModDustItem(3260-ITEM_ID_OFFSET)
	.setUnlocalizedName("dustGold")
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item toxinSuit = new ModToxinSuit(3261-ITEM_ID_OFFSET, EnumArmorMaterial.CHAIN, 2, 1)
	.setUnlocalizedName("toxinSuit")
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item SCUBAGear = new ModSCUBAGear(3262-ITEM_ID_OFFSET, EnumArmorMaterial.GOLD, 2, 0)
	.setUnlocalizedName("SCUBAGear")
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item rodTarydiumAlloyUnrefined = new ModBasicItem(3263-ITEM_ID_OFFSET)
	.setUnlocalizedName("rodTarydiumAlloyUnrefined").setFull3D()
	.setCreativeTab(CreativeTabs.tabAllSearch);
	
	public static final Item rodTarydiumAlloyRefined = new ModBasicItem(3264-ITEM_ID_OFFSET)
	.setUnlocalizedName("rodTarydiumAlloyRefined").setFull3D()
	.setCreativeTab(CreativeTabs.tabAllSearch);
	
	public static final Item swordTarydiumAlloyRefined = new ModSwordBasic(3265-ITEM_ID_OFFSET, MainMod.EnumToolMaterialTarydiumAlloyRefined)
	.setUnlocalizedName("swordTarydiumAlloyRefined")
	.setCreativeTab(CreativeTabs.tabAllSearch);
	
	public static final Item pickTarydiumAlloyRefined = new ModPickBasic(3266-ITEM_ID_OFFSET, MainMod.EnumToolMaterialTarydiumAlloyRefined)
	.setUnlocalizedName("pickaxeTarydiumAlloyRefined")
	.setCreativeTab(CreativeTabs.tabAllSearch);
	
	public static final Item shovelTarydiumAlloyRefined = new ModShovelBasic(3267-ITEM_ID_OFFSET, MainMod.EnumToolMaterialTarydiumAlloyRefined)
	.setUnlocalizedName("shovelTarydiumAlloyRefined")
	.setCreativeTab(CreativeTabs.tabAllSearch);
	
	public static final Item axeTarydiumAlloyRefined = new ModAxeBasic(3268-ITEM_ID_OFFSET, MainMod.EnumToolMaterialTarydiumAlloyRefined)
	.setUnlocalizedName("axeTarydiumAlloyRefined")
	.setCreativeTab(CreativeTabs.tabAllSearch);
	
	public static final Item hoeTarydiumAlloyRefined = new ModHoeBasic(3269-ITEM_ID_OFFSET, MainMod.EnumToolMaterialTarydiumAlloyRefined)
	.setUnlocalizedName("hoeTarydiumAlloyRefined")
	.setCreativeTab(CreativeTabs.tabAllSearch);
	
	public static final Item SCUBATank = new ModBasicItem(3270-ITEM_ID_OFFSET)
	.setUnlocalizedName("SCUBATankFilled")
	.setMaxStackSize(4)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item SCUBATankEmpty = new ModBasicItem(3271-ITEM_ID_OFFSET)
	.setUnlocalizedName("SCUBATankEmpty")
	.setMaxStackSize(4)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item dustCoal = new ModDustItem(3272-ITEM_ID_OFFSET)
	.setUnlocalizedName("dustCoal")
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item dustCharcoal = new ModDustItem(3273-ITEM_ID_OFFSET)
	.setUnlocalizedName("dustCharcoal")
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item dustLapis = new ModDustItem(3274-ITEM_ID_OFFSET)
	.setUnlocalizedName("dustLapis")
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item dustDiamond = new ModDustItem(3275-ITEM_ID_OFFSET)
	.setUnlocalizedName("dustDiamond")
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item dustEmerald = new ModDustItem(3276-ITEM_ID_OFFSET)
	.setUnlocalizedName("dustEmerald")
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item dustNetherQuartz = new ModDustItem(3277-ITEM_ID_OFFSET)
	.setUnlocalizedName("dustNetherQuartz")
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item dustAsbestos = new ModDustItem(3278-ITEM_ID_OFFSET)
	.setUnlocalizedName("dustAsbestos")
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item dustObsidian = new ModDustItem(3279-ITEM_ID_OFFSET)
	.setUnlocalizedName("dustObsidian")
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item crystalCinnabar = new ModDustItem(3280-ITEM_ID_OFFSET)
	.setUnlocalizedName("gemCinnabar")
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item nuggetBedrockium = new ModDustItem(3281-ITEM_ID_OFFSET)
	.setUnlocalizedName("nuggetBedrockium")
	.setCreativeTab(CreativeTabs.tabAllSearch);
	
	public static final Item ingotBedrockium = new ModDustItem(3282-ITEM_ID_OFFSET)
	.setUnlocalizedName("ingotBedrockium")
	.setCreativeTab(CreativeTabs.tabAllSearch);
	
	public static final Item dustCinnabar = new ModDustItem(3283-ITEM_ID_OFFSET)
	.setUnlocalizedName("dustCinnabar")
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item debugTool = new ModBasicItem(3999-ITEM_ID_OFFSET)
	.setUnlocalizedName("powerCrystal0")
	.setMaxStackSize(16)
	.setCreativeTab(MainMod.modTabAll);
	
	public static final Item recordUnreal = (new ModRecord(4000-ITEM_ID_OFFSET, "FlightCastle", "Unreal", "Long Flight"))
	.setUnlocalizedName("record")
	.setCreativeTab(MainMod.modTabAll);
	
	//item-blocks
	
	/*
	static{
		Item.itemsList[netherrackCompressed1x.blockID] = (new ModItemBlock(netherrackCompressed1x.blockID - ITEM_ID_OFFSET)).setUnlocalizedName("netherrackCompressed1x");
		Item.itemsList[netherrackCompressed2x.blockID] = (new ModItemBlock(netherrackCompressed2x.blockID - ITEM_ID_OFFSET)).setUnlocalizedName("netherrackCompressed2x");
		Item.itemsList[netherrackCompressed3x.blockID] = (new ModItemBlock(netherrackCompressed3x.blockID - ITEM_ID_OFFSET)).setUnlocalizedName("netherrackCompressed3x");
		Item.itemsList[netherrackCompressed4x.blockID] = (new ModItemBlock(netherrackCompressed4x.blockID - ITEM_ID_OFFSET)).setUnlocalizedName("netherrackCompressed4x");
		Item.itemsList[endStoneCompressed1x.blockID] = (new ModItemBlock(endStoneCompressed1x.blockID - ITEM_ID_OFFSET)).setUnlocalizedName("endStoneCompressed1x");
		Item.itemsList[endStoneCompressed2x.blockID] = (new ModItemBlock(endStoneCompressed2x.blockID - ITEM_ID_OFFSET)).setUnlocalizedName("endStoneCompressed2x");
		Item.itemsList[endStoneCompressed3x.blockID] = (new ModItemBlock(endStoneCompressed3x.blockID - ITEM_ID_OFFSET)).setUnlocalizedName("endStoneCompressed3x");
		Item.itemsList[endStoneCompressed4x.blockID] = (new ModItemBlock(endStoneCompressed4x.blockID - ITEM_ID_OFFSET)).setUnlocalizedName("endStoneCompressed4x");
	}*/
	
}
