package virtuoel.unreal;

import virtuoel.unreal.client.ClientProxy;
import virtuoel.unreal.entity.EntityFlak;
import virtuoel.unreal.entity.EntityNali;
import virtuoel.unreal.handler.ModFuelHandler;
import virtuoel.unreal.item.ModItems;
import virtuoel.unreal.tileentity.TileEntityEviscerator;
import virtuoel.unreal.world.ModWorldGen;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid="Unreal",name="Unreal",version="0.0.9")
@NetworkMod(clientSideRequired=true,serverSideRequired=false)



public class MainMod {
	
	@Instance("Unreal")
    public static MainMod instance;
	
	public static String modName = "Unreal";
	public static String modDisplayName = "Unreal";
	
	@SidedProxy(clientSide="virtuoel.unreal.client.ClientProxy", 
	serverSide="virtuoel.unreal.CommonProxy")
	
	public static CommonProxy proxy;
	//public static ClientProxy proxy;	
	
	public static ModItems allItems;
	
	//ToolMaterial
	
	public static EnumToolMaterial EnumToolMaterialTitanium = EnumHelper
		.addToolMaterial("Titanium", 2, 460, 6.0F, 2, 8);
	//4, 900, ?F ?, ?
	
	public static EnumToolMaterial EnumToolMaterialTarydiumAlloyRefined = EnumHelper
		.addToolMaterial("Refined Tarydium Alloy", 6, 7200, 20.0F, 15, 1);
	
	//ArmorMaterial
	
	public static EnumArmorMaterial EnumArmorMaterialShield = EnumHelper
		.addArmorMaterial("ShieldBelt", 20, new int[] {512, 512, 512, 512}, 0);
	
	//tabs
	
	public static CreativeTabs modTabAll = new CreativeTabs(modDisplayName) {
        public ItemStack getIconItemStack() {
            return new ItemStack(ModItems.tarydPowerBasic, 1, 0);
        }
        public String getTranslatedTabLabel(){
    		return MainMod.modDisplayName;
    	}
	};
	
	public static CreativeTabs modTabUnreal = new CreativeTabs(modDisplayName+" Combat") {
        public ItemStack getIconItemStack() {
            return new ItemStack(ModItems.gunRedeemer, 1, 0);
        }
        public String getTranslatedTabLabel(){
    		return MainMod.modDisplayName+" Combat";
    	}
	};
	
	public static CreativeTabs modTabUtility = new CreativeTabs(modDisplayName+" Utility and Extras") {
        public ItemStack getIconItemStack() {
            return new ItemStack(ModItems.recordUnreal, 1, 0);
        }
        public String getTranslatedTabLabel(){
    		return MainMod.modDisplayName+" Utility and Extras";
    	}
	};
	
	@PreInit
    public void preInit(FMLPreInitializationEvent event) {
        // Stub Method
	}
	
	@Init
	public void load(FMLInitializationEvent event){
		
		// define items
		
		LanguageRegistry.addName(ModItems.debugTool, ".debugTool()");

		LanguageRegistry.addName(ModItems.ingotTitanium, "Titanium Ingot");
		LanguageRegistry.addName(ModItems.nuggetTitanium, "Titanium Nugget");
		LanguageRegistry.addName(ModItems.dustTitanium, "Titanium Dust");
		LanguageRegistry.addName(ModItems.ingotRutile, "Impure Titanium Ingot");
		LanguageRegistry.addName(ModItems.nuggetRutile, "Impure Titanium Nugget");
		LanguageRegistry.addName(ModItems.dustRutile, "Impure Titanium Dust");
		LanguageRegistry.addName(ModItems.ingotMagnesium, "Magnesium Ingot");
		LanguageRegistry.addName(ModItems.nuggetMagnesium, "Magnesium Nugget");
		LanguageRegistry.addName(ModItems.dustMagnesium, "Magnesium Dust");
		LanguageRegistry.addName(ModItems.swordTitanium, "Titanium Sword");
		LanguageRegistry.addName(ModItems.axeTitanium, "Titanium Axe");
		LanguageRegistry.addName(ModItems.pickTitanium, "Titanium Pickaxe");
		LanguageRegistry.addName(ModItems.hoeTitanium, "Titanium Hoe");
		LanguageRegistry.addName(ModItems.shovelTitanium, "Titanium Shovel");
		LanguageRegistry.addName(ModItems.gemTarydium, "Tarydium Crystal");
		LanguageRegistry.addName(ModItems.shardTarydium, "Tarydium Shard");
		LanguageRegistry.addName(ModItems.weaponCasingBasic, "Basic Weapon Casing");
		LanguageRegistry.addName(ModItems.tarydPowerBasic, "Tarydium Energy Crystal");
		LanguageRegistry.addName(ModItems.nuggetIron, "Iron Nugget");
		LanguageRegistry.addName(ModItems.caseTitanium, "Titanium Casing");
		LanguageRegistry.addName(ModItems.eightball, "Eightball Rocket");
		LanguageRegistry.addName(ModItems.shieldBelt, "Shield Belt");
		LanguageRegistry.addName(ModItems.gunEightball, "Eightball Launcher");
		LanguageRegistry.addName(ModItems.gunStinger, "Stinger");
		LanguageRegistry.addName(ModItems.gunBarrelIron, "Ammo Barrel");
		LanguageRegistry.addName(ModItems.gunTranslocator, "Translocator");
		LanguageRegistry.addName(ModItems.translocatorDisc, "Translocator Disc");
		LanguageRegistry.addName(ModItems.weaponCasingAdv, "Advanced Weapon Casing");
		LanguageRegistry.addName(ModItems.jumpBoots, "Jump Boots");
		LanguageRegistry.addName(ModItems.flakShell, "Flak Shell");
		LanguageRegistry.addName(ModItems.flak, "Flak");
		LanguageRegistry.addName(ModItems.gunFlak, "Flak Cannon");
		LanguageRegistry.addName(ModItems.flakShellWarm, "Heated Flak Shell");
		LanguageRegistry.addName(ModItems.flakShellCold, "Cold Flak Shell");
		LanguageRegistry.addName(ModItems.caseIron, "Iron Casing");
		LanguageRegistry.addName(ModItems.ingotStone, "Stone Ingot");
		LanguageRegistry.addName(ModItems.gunRedeemer, "Redeemer");
		LanguageRegistry.addName(ModItems.redeemerMissile, "Redeemer Warhead");
		LanguageRegistry.addName(ModItems.warheadCone, "Warhead Cone");
		LanguageRegistry.addName(ModItems.warheadBody, "Warhead Body");
		LanguageRegistry.addName(ModItems.warheadThruster, "Warhead Thruster");
		LanguageRegistry.addName(ModItems.boatLava, "Reinforced Boat");
		LanguageRegistry.addName(ModItems.meleeChainsaw, "Chainsaw");
		LanguageRegistry.addName(ModItems.chainPart, "Chain Link");
		LanguageRegistry.addName(ModItems.chainsawBlade, "Chainsaw Blade");
		LanguageRegistry.addName(ModItems.chainsawBladeHardened, "Hardened Chainsaw Blade");
		LanguageRegistry.addName(ModItems.chainsawMotor, "Chainsaw Motor");
		LanguageRegistry.addName(ModItems.dispersionUpgrade, "Brilithium Power Core");
		LanguageRegistry.addName(ModItems.gunDispersionPistol, "Dispersion Pistol");
		LanguageRegistry.addName(ModItems.gunBarrelIronLarge, "Bundled Ammo Barrel");
		LanguageRegistry.addName(ModItems.nuggetTarydiumAlloyRefined, "Refined Tarydium Alloy Nugget");
		LanguageRegistry.addName(ModItems.nuggetTarydiumAlloyUnrefined, "Unrefined Tarydium Alloy Nugget");
		LanguageRegistry.addName(ModItems.ingotTarydiumAlloyRefined, "Refined Tarydium Alloy Ingot");
		LanguageRegistry.addName(ModItems.ingotTarydiumAlloyUnrefined, "Unrefined Tarydium Alloy Ingot");
		LanguageRegistry.addName(ModItems.bucketMercury, "Mercury Bucket");
		LanguageRegistry.addName(ModItems.asbestosSuit, "Asbestos Suit");
		LanguageRegistry.addName(ModItems.dustIron, "Iron Dust");
		LanguageRegistry.addName(ModItems.dustGold, "Gold Dust");
		LanguageRegistry.addName(ModItems.dustTarydiumAlloyUnrefined, "Unrefined Tarydium Alloy Dust");
		LanguageRegistry.addName(ModItems.dustTarydiumAlloyRefined, "Refined Tarydium Alloy Dust");
		LanguageRegistry.addName(ModItems.toxinSuit, "Toxin Suit");
		LanguageRegistry.addName(ModItems.SCUBAGear, "SCUBA Gear");
		LanguageRegistry.addName(ModItems.rodTarydiumAlloyUnrefined, "Unrefined Tarydium Alloy Rod");
		LanguageRegistry.addName(ModItems.rodTarydiumAlloyRefined, "Refined Tarydium Alloy Rod");
		LanguageRegistry.addName(ModItems.swordTarydiumAlloyRefined, "Refined Tarydium Alloy Sword");
		LanguageRegistry.addName(ModItems.axeTarydiumAlloyRefined, "Refined Tarydium Alloy Axe");
		LanguageRegistry.addName(ModItems.pickTarydiumAlloyRefined, "Refined Tarydium Alloy Pickaxe");
		LanguageRegistry.addName(ModItems.hoeTarydiumAlloyRefined, "Refined Tarydium Alloy Hoe");
		LanguageRegistry.addName(ModItems.shovelTarydiumAlloyRefined, "Refined Tarydium Alloy Shovel");
		LanguageRegistry.addName(ModItems.SCUBATank, "SCUBA Tank (Filled)");
		LanguageRegistry.addName(ModItems.SCUBATankEmpty, "SCUBA Tank (Empty)");
		LanguageRegistry.addName(ModItems.toolVoiceBox, "Voice Box");
		LanguageRegistry.addName(ModItems.dustCoal, "Coal Dust");
		LanguageRegistry.addName(ModItems.dustCharcoal, "Charcoal Dust");
		LanguageRegistry.addName(ModItems.dustLapis, "Lapis Lazuli Dust");
		LanguageRegistry.addName(ModItems.dustDiamond, "Diamond Dust");
		LanguageRegistry.addName(ModItems.dustEmerald, "Emerald Dust");
		LanguageRegistry.addName(ModItems.dustNetherQuartz, "Nether Quartz Dust");
		LanguageRegistry.addName(ModItems.dustAsbestos, "Asbestos Dust");
		LanguageRegistry.addName(ModItems.dustObsidian, "Obsidian Dust");
		LanguageRegistry.addName(ModItems.crystalCinnabar, "Cinnabar");
		LanguageRegistry.addName(ModItems.dustCinnabar, "Cinnabar Dust");
		LanguageRegistry.addName(ModItems.nuggetBedrockium, "Bedrockium Nugget");
		LanguageRegistry.addName(ModItems.ingotBedrockium, "Bedrockium Ingot");
		
		//tools
		
        MinecraftForge.setToolClass(ModItems.pickTitanium, "pickaxe", 2);
        MinecraftForge.setToolClass(ModItems.axeTitanium, "axe", 2);
        MinecraftForge.setToolClass(ModItems.shovelTitanium, "shovel", 2);
        MinecraftForge.setToolClass(ModItems.pickTarydiumAlloyRefined, "pickaxe", 6);
        MinecraftForge.setToolClass(ModItems.axeTarydiumAlloyRefined, "axe", 6);
        MinecraftForge.setToolClass(ModItems.shovelTarydiumAlloyRefined, "shovel", 6);
        MinecraftForge.setToolClass(ModItems.meleeChainsaw, "axe", 4);
		
		//blocks
        
		GameRegistry.registerBlock(ModItems.blockTitanium, "blockTitanium");
		LanguageRegistry.addName(ModItems.blockTitanium, "Block of Titanium");
		MinecraftForge.setBlockHarvestLevel(ModItems.blockTitanium, "pickaxe", 2);
		
		GameRegistry.registerBlock(ModItems.blockRutile, "blockRutile");
		LanguageRegistry.addName(ModItems.blockRutile, "Impure Titanium Block");
		MinecraftForge.setBlockHarvestLevel(ModItems.blockRutile, "pickaxe", 2);
		
		GameRegistry.registerBlock(ModItems.oreRutile, "oreRutile");
		LanguageRegistry.addName(ModItems.oreRutile, "Rutile Ore");
		MinecraftForge.setBlockHarvestLevel(ModItems.oreRutile, "pickaxe", 2);
		
		GameRegistry.registerBlock(ModItems.blockMagnesium, "blockMagnesium");
		LanguageRegistry.addName(ModItems.blockMagnesium, "Block of Magnesium");
		MinecraftForge.setBlockHarvestLevel(ModItems.blockMagnesium, "pickaxe", 1);
		
		GameRegistry.registerBlock(ModItems.oreMagnesium, "oreMagnesium");
		LanguageRegistry.addName(ModItems.oreMagnesium, "Magnesium Ore");
		MinecraftForge.setBlockHarvestLevel(ModItems.oreMagnesium, "pickaxe", 1);
		
		GameRegistry.registerBlock(ModItems.blockTarydium, "blockTarydium");
		LanguageRegistry.addName(ModItems.blockTarydium, "Tarydium Block");
		MinecraftForge.setBlockHarvestLevel(ModItems.blockTarydium, "pickaxe", 3);
		
		GameRegistry.registerBlock(ModItems.oreTarydium, "oreTarydium");
		LanguageRegistry.addName(ModItems.oreTarydium, "Tarydium Ore");
		MinecraftForge.setBlockHarvestLevel(ModItems.oreTarydium, "pickaxe", 3);
		
		GameRegistry.registerBlock(ModItems.cactusTarydium, "cactusTarydium");
		LanguageRegistry.addName(ModItems.cactusTarydium, "Tarydium Infused Cactus");
		MinecraftForge.setBlockHarvestLevel(ModItems.cactusTarydium, "axe", 3);
		MinecraftForge.setBlockHarvestLevel(ModItems.cactusTarydium, "pickaxe", 16);
		
		GameRegistry.registerBlock(ModItems.ladderIron, "ladderIron");
		LanguageRegistry.addName(ModItems.ladderIron, "Iron Ladder");
		MinecraftForge.setBlockHarvestLevel(ModItems.ladderIron, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(ModItems.ladderIron, "axe", 16);
		
		GameRegistry.registerBlock(ModItems.blockEnder, "blockEnder");
		LanguageRegistry.addName(ModItems.blockEnder, "Ender Pearl Block");
		MinecraftForge.setBlockHarvestLevel(ModItems.blockEnder, "pickaxe", 2);
		
		GameRegistry.registerBlock(ModItems.blockLeather, "blockLeather");
		LanguageRegistry.addName(ModItems.blockLeather, "Leather Block");
		
		GameRegistry.registerBlock(ModItems.oreStone, "oreStone");
		LanguageRegistry.addName(ModItems.oreStone, "Stone Ore");
		MinecraftForge.setBlockHarvestLevel(ModItems.oreStone, "pickaxe", 0);
		
		GameRegistry.registerBlock(ModItems.stoneMossySmooth, "stoneMossySmooth");
		LanguageRegistry.addName(ModItems.stoneMossySmooth, "Mossy Stone");
		MinecraftForge.setBlockHarvestLevel(ModItems.stoneMossySmooth, "pickaxe", 0);
		
		GameRegistry.registerBlock(ModItems.tileStoneDecor, "tileStoneDecor");
		LanguageRegistry.addName(ModItems.tileStoneDecor, "Cobblestone Tile");
		MinecraftForge.setBlockHarvestLevel(ModItems.tileStoneDecor, "pickaxe", 0);
		
		GameRegistry.registerBlock(ModItems.oreTarydiumEnd, "oreTarydiumEnd");
		LanguageRegistry.addName(ModItems.oreTarydiumEnd, "Tarydium Ore");
		MinecraftForge.setBlockHarvestLevel(ModItems.oreTarydiumEnd, "pickaxe", 3);
		
		GameRegistry.registerBlock(ModItems.oreTarydiumNether, "oreTarydiumNether");
		LanguageRegistry.addName(ModItems.oreTarydiumNether, "Tarydium Ore");
		MinecraftForge.setBlockHarvestLevel(ModItems.oreTarydiumNether, "pickaxe", 3);
		
		GameRegistry.registerBlock(ModItems.oreTarydiumObsidian, "oreTarydiumObsidian");
		LanguageRegistry.addName(ModItems.oreTarydiumObsidian, "Tarydium Ore");
		MinecraftForge.setBlockHarvestLevel(ModItems.oreTarydiumObsidian, "pickaxe", 3);
		
		GameRegistry.registerBlock(ModItems.blockUUTC, "blockUUTC");
		LanguageRegistry.addName(ModItems.blockUUTC, "U.U.T.C. Block");
		MinecraftForge.setBlockHarvestLevel(ModItems.blockUUTC, "pickaxe", 3);
		
		GameRegistry.registerBlock(ModItems.netherrackCompressed1x, "netherrackCompressed1x");
		LanguageRegistry.addName(ModItems.netherrackCompressed1x, "Compressed Netherrack");
		MinecraftForge.setBlockHarvestLevel(ModItems.netherrackCompressed1x, "pickaxe", 0);
		
		GameRegistry.registerBlock(ModItems.netherrackCompressed2x, "netherrackCompressed2x");
		LanguageRegistry.addName(ModItems.netherrackCompressed2x, "Double Compressed Netherrack");
		MinecraftForge.setBlockHarvestLevel(ModItems.netherrackCompressed2x, "pickaxe", 0);
		
		GameRegistry.registerBlock(ModItems.netherrackCompressed3x, "netherrackCompressed3x");
		LanguageRegistry.addName(ModItems.netherrackCompressed3x, "Triple Compressed Netherrack");
		MinecraftForge.setBlockHarvestLevel(ModItems.netherrackCompressed3x, "pickaxe", 0);
		
		GameRegistry.registerBlock(ModItems.netherrackCompressed4x, "netherrackCompressed4x");
		LanguageRegistry.addName(ModItems.netherrackCompressed4x, "Quadruple Compressed Netherrack");
		MinecraftForge.setBlockHarvestLevel(ModItems.netherrackCompressed4x, "pickaxe", 0);
		
		GameRegistry.registerBlock(ModItems.endStoneCompressed1x, "endStoneCompressed1x");
		LanguageRegistry.addName(ModItems.endStoneCompressed1x, "Compressed End Stone");
		MinecraftForge.setBlockHarvestLevel(ModItems.endStoneCompressed1x, "pickaxe", 0);
		
		GameRegistry.registerBlock(ModItems.endStoneCompressed2x, "endStoneCompressed2x");
		LanguageRegistry.addName(ModItems.endStoneCompressed2x, "Double Compressed End Stone");
		MinecraftForge.setBlockHarvestLevel(ModItems.endStoneCompressed2x, "pickaxe", 0);
		
		GameRegistry.registerBlock(ModItems.endStoneCompressed3x, "endStoneCompressed3x");
		LanguageRegistry.addName(ModItems.endStoneCompressed3x, "Triple Compressed End Stone");
		MinecraftForge.setBlockHarvestLevel(ModItems.endStoneCompressed3x, "pickaxe", 0);
		
		GameRegistry.registerBlock(ModItems.endStoneCompressed4x, "endStoneCompressed4x");
		LanguageRegistry.addName(ModItems.endStoneCompressed4x, "Quadruple Compressed End Stone");
		MinecraftForge.setBlockHarvestLevel(ModItems.endStoneCompressed4x, "pickaxe", 0);
		
		GameRegistry.registerBlock(ModItems.blockTarydiumAlloyRefined, "blockTarydiumAlloyRefined");
		LanguageRegistry.addName(ModItems.blockTarydiumAlloyRefined, "Refined Tarydium Alloy Block");
		MinecraftForge.setBlockHarvestLevel(ModItems.blockTarydiumAlloyRefined, "pickaxe", 3);
		
		GameRegistry.registerBlock(ModItems.blockTarydiumAlloyUnrefined, "blockTarydiumAlloyUnrefined");
		LanguageRegistry.addName(ModItems.blockTarydiumAlloyUnrefined, "Unrefined Tarydium Alloy Block");
		MinecraftForge.setBlockHarvestLevel(ModItems.blockTarydiumAlloyUnrefined, "pickaxe", 3);
		
		GameRegistry.registerBlock(ModItems.blockFlint, "blockFlint");
		LanguageRegistry.addName(ModItems.blockFlint, "Block of Flint");
		MinecraftForge.setBlockHarvestLevel(ModItems.blockFlint, "pickaxe", 1);
		
		GameRegistry.registerBlock(ModItems.blockGunpowder, "blockGunpowder");
		LanguageRegistry.addName(ModItems.blockGunpowder, "Block of Gunpowder");
		MinecraftForge.setBlockHarvestLevel(ModItems.blockGunpowder, "pickaxe", 0);
		MinecraftForge.setBlockHarvestLevel(ModItems.blockGunpowder, "shovel", 0);
		
		GameRegistry.registerBlock(ModItems.blockBlaze, "blockBlaze");
		LanguageRegistry.addName(ModItems.blockBlaze, "Blaze Rod Block");
		MinecraftForge.setBlockHarvestLevel(ModItems.blockBlaze, "pickaxe", 1);
		
		GameRegistry.registerBlock(ModItems.blockGhastTear, "blockGhastTear");
		LanguageRegistry.addName(ModItems.blockGhastTear, "Crystalline Tear Block");
		MinecraftForge.setBlockHarvestLevel(ModItems.blockGhastTear, "pickaxe", 1);
		
		GameRegistry.registerBlock(ModItems.blockSlime, "blockSlime");
		LanguageRegistry.addName(ModItems.blockSlime, "Slime Block");
		
		GameRegistry.registerBlock(ModItems.blockNetherWart, "blockNetherWart");
		LanguageRegistry.addName(ModItems.blockNetherWart, "Compacted Nether Wart Block");
		
		GameRegistry.registerBlock(ModItems.oreAsbestos, "oreAsbestos");
		LanguageRegistry.addName(ModItems.oreAsbestos, "Asbestos Ore");
		MinecraftForge.setBlockHarvestLevel(ModItems.oreAsbestos, "pickaxe", 1);
		
		GameRegistry.registerBlock(ModItems.blockAsbestos, "blockAsbestos");
		LanguageRegistry.addName(ModItems.blockAsbestos, "Asbestos Block");
		MinecraftForge.setBlockHarvestLevel(ModItems.blockAsbestos, "pickaxe", 1);
		
		GameRegistry.registerBlock(ModItems.blockCoal, "blockCoal");
		LanguageRegistry.addName(ModItems.blockCoal, "Block of Coal");
		MinecraftForge.setBlockHarvestLevel(ModItems.blockCoal, "pickaxe", 0);
		
		GameRegistry.registerBlock(ModItems.blockCharcoal, "blockCharcoal");
		LanguageRegistry.addName(ModItems.blockCharcoal, "Block of Charcoal");
		MinecraftForge.setBlockHarvestLevel(ModItems.blockCharcoal, "pickaxe", 0);
		
		GameRegistry.registerBlock(ModItems.blockEnderCrystal, "blockEnderCrystal");
		LanguageRegistry.addName(ModItems.blockEnderCrystal, "Ender Crystal");
		MinecraftForge.setBlockHarvestLevel(ModItems.blockEnderCrystal, "pickaxe", 5);
		
		GameRegistry.registerBlock(ModItems.oreBedrockiumHidden, "oreBedrockiumHidden");
		LanguageRegistry.addName(ModItems.oreBedrockiumHidden, "Bedrock");
		
		GameRegistry.registerBlock(ModItems.oreBedrockium, "oreBedrockium");
		LanguageRegistry.addName(ModItems.oreBedrockium, "Bedrockium Ore");
		MinecraftForge.setBlockHarvestLevel(ModItems.oreBedrockium, "pickaxe", 3);
		
		GameRegistry.registerBlock(ModItems.blockBedrockium, "blockBedrockium");
		LanguageRegistry.addName(ModItems.blockBedrockium, "Bedrockium Block");
		MinecraftForge.setBlockHarvestLevel(ModItems.blockBedrockium, "pickaxe", 3);
		
		GameRegistry.registerBlock(ModItems.stonePolished, "stonePolished");
		LanguageRegistry.addName(ModItems.stonePolished, "Polished Stone");
		MinecraftForge.setBlockHarvestLevel(ModItems.stonePolished, "pickaxe", 0);
		
		GameRegistry.registerBlock(ModItems.blockCinnabar, "blockCinnabar");
		LanguageRegistry.addName(ModItems.blockCinnabar, "Block of Cinnabar");
		MinecraftForge.setBlockHarvestLevel(ModItems.blockCinnabar, "pickaxe", 2);
		
		GameRegistry.registerBlock(ModItems.obsidianNether, "obsidianNether");
		LanguageRegistry.addName(ModItems.obsidianNether, "Nether Infused Obsidian");
		MinecraftForge.setBlockHarvestLevel(ModItems.obsidianNether, "pickaxe", 3);
		
		GameRegistry.registerBlock(ModItems.blockCocoa, "blockCocoa");
		LanguageRegistry.addName(ModItems.blockCocoa, "Block of Cocoa");
		MinecraftForge.setBlockHarvestLevel(ModItems.blockCocoa, "axe", 0);
		
		//entities
		
		//EntityRegistry.registerModEntity(EntityFlak.class, "EntityFlak", 12, this, 1, 3, true);
		
		EntityRegistry.registerModEntity(EntityNali.class, "Nali", 2, this, 80, 3, true);
        //EntityRegistry.addSpawn(EntityNali.class, 5, 2, 6, EnumCreatureType.monster, BiomeGenBase.sky, BiomeGenBase.plains);
        LanguageRegistry.instance().addStringLocalization("entity.instance.Nali.name", "Nali");
		
		//renderers
		
		proxy.registerRenderers();
		
		//fluids
		
		//LiquidContainerRegistry.registerLiquid(new LiquidContainerData(new LiquidStack(Block.oreRedstone, LiquidContainerRegistry.BUCKET_VOLUME), new ItemStack(ModItems.bucketMercury), new ItemStack(Item.bucketEmpty)));
		
		//ore dict
		
		OreDictionary.registerOre("nuggetGold", Item.goldNugget);
		OreDictionary.registerOre("ingotGold", Item.ingotGold);
		OreDictionary.registerOre("blockGold", Block.blockGold);
		OreDictionary.registerOre("ingotIron", Item.ingotIron);
		OreDictionary.registerOre("blockIron", Block.blockIron);
		OreDictionary.registerOre("oreCoal", Block.oreCoal);
		OreDictionary.registerOre("oreNetherQuartz", Block.oreNetherQuartz);
		OreDictionary.registerOre("oreQuartz", Block.oreNetherQuartz);
		OreDictionary.registerOre("gemDiamond", Item.diamond);
		OreDictionary.registerOre("blockDiamond", Block.blockDiamond);
		OreDictionary.registerOre("gemEmerald", Item.emerald);
		OreDictionary.registerOre("blockEmerald", Block.blockEmerald);
		OreDictionary.registerOre("dustRedstone", Item.redstone);
		OreDictionary.registerOre("blockRedstone", Block.blockRedstone);
		OreDictionary.registerOre("blockLapis", Block.blockLapis);
		OreDictionary.registerOre("dustGunpowder", Item.gunpowder);
		OreDictionary.registerOre("cobblestone", Block.cobblestone);
		OreDictionary.registerOre("netherrack", Block.netherrack);
		OreDictionary.registerOre("rodBlaze", Item.blazeRod);
		OreDictionary.registerOre("blazeRod", Item.blazeRod);
		OreDictionary.registerOre("dustBlaze", Item.blazePowder);
		OreDictionary.registerOre("itemTear", Item.ghastTear);
		OreDictionary.registerOre("dustGlowstone", Item.lightStoneDust);
		OreDictionary.registerOre("crystalNetherQuartz", Item.netherQuartz);
		OreDictionary.registerOre("blockNetherQuartz",  new ItemStack(Block.blockNetherQuartz,1,OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("coal", new ItemStack(Item.coal,1,0));
		OreDictionary.registerOre("charcoal", new ItemStack(Item.coal,1,1));
		OreDictionary.registerOre("cropCocoa", new ItemStack(Item.dyePowder,1,3));
		OreDictionary.registerOre("gemLapis", new ItemStack(Item.dyePowder,1,4));
		OreDictionary.registerOre("record", Item.record13);
		OreDictionary.registerOre("record", Item.recordCat);
		OreDictionary.registerOre("record", Item.recordBlocks);
		OreDictionary.registerOre("record", Item.recordChirp);
		OreDictionary.registerOre("record", Item.recordFar);
		OreDictionary.registerOre("record", Item.recordMall);
		OreDictionary.registerOre("record", Item.recordMellohi);
		OreDictionary.registerOre("record", Item.recordStal);
		OreDictionary.registerOre("record", Item.recordStrad);
		OreDictionary.registerOre("record", Item.recordWard);
		OreDictionary.registerOre("record", Item.record11);
		OreDictionary.registerOre("record", Item.recordWait);
		OreDictionary.registerOre("slimeball", Item.slimeBall);
		
		OreDictionary.registerOre("virtuoel.unreal.debug", ModItems.debugTool);
		
		//OreDictionary.registerOre("oreTitanium", ModItems.oreTitanium);
		OreDictionary.registerOre("ingotTitanium", ModItems.ingotTitanium);
		OreDictionary.registerOre("dustTitanium", ModItems.dustTitanium);
		OreDictionary.registerOre("nuggetTitanium", ModItems.nuggetTitanium);
		OreDictionary.registerOre("blockTitanium", ModItems.blockTitanium);
		OreDictionary.registerOre("oreTarydium", ModItems.oreTarydium);
		OreDictionary.registerOre("shardTarydium", ModItems.shardTarydium);
		OreDictionary.registerOre("gemTarydium", ModItems.gemTarydium);
		OreDictionary.registerOre("blockTarydium", ModItems.blockTarydium);
		OreDictionary.registerOre("nuggetIron", ModItems.nuggetIron);
		OreDictionary.registerOre("blockEnder", ModItems.blockEnder);
		OreDictionary.registerOre("blockLeather", ModItems.blockLeather);
		OreDictionary.registerOre("oreStone", ModItems.oreStone);
		OreDictionary.registerOre("oreTarydium", ModItems.oreTarydiumEnd);
		OreDictionary.registerOre("oreTarydium", ModItems.oreTarydiumNether);
		OreDictionary.registerOre("oreTarydium", ModItems.oreTarydiumObsidian);
		OreDictionary.registerOre("oreEnderTarydium", ModItems.oreTarydiumEnd);
		OreDictionary.registerOre("oreEndTarydium", ModItems.oreTarydiumEnd);
		OreDictionary.registerOre("oreNetherTarydium", ModItems.oreTarydiumNether);
		OreDictionary.registerOre("oreTarydiumEnder", ModItems.oreTarydiumEnd);
		OreDictionary.registerOre("oreTarydiumEnd", ModItems.oreTarydiumEnd);
		OreDictionary.registerOre("oreTarydiumNether", ModItems.oreTarydiumNether);
		OreDictionary.registerOre("ingotStone", ModItems.ingotStone);
		OreDictionary.registerOre("oreMagnesium", ModItems.oreMagnesium);
		OreDictionary.registerOre("ingotMagnesium", ModItems.ingotMagnesium);
		OreDictionary.registerOre("dustMagnesium", ModItems.dustMagnesium);
		OreDictionary.registerOre("nuggetMagnesium", ModItems.nuggetMagnesium);
		OreDictionary.registerOre("blockMagnesium", ModItems.blockMagnesium);
		OreDictionary.registerOre("oreRutile", ModItems.oreRutile);
		OreDictionary.registerOre("ingotRutile", ModItems.ingotRutile);
		OreDictionary.registerOre("dustRutile", ModItems.dustRutile);
		OreDictionary.registerOre("nuggetRutile", ModItems.nuggetRutile);
		OreDictionary.registerOre("blockRutile", ModItems.blockRutile);
		OreDictionary.registerOre("netherrackCompressed1x", ModItems.netherrackCompressed1x);
		OreDictionary.registerOre("netherrackCompressed2x", ModItems.netherrackCompressed2x);
		OreDictionary.registerOre("netherrackCompressed3x", ModItems.netherrackCompressed3x);
		OreDictionary.registerOre("netherrackCompressed4x", ModItems.netherrackCompressed4x);
		OreDictionary.registerOre("endStoneCompressed1x", ModItems.endStoneCompressed1x);
		OreDictionary.registerOre("endStoneCompressed2x", ModItems.endStoneCompressed2x);
		OreDictionary.registerOre("endStoneCompressed3x", ModItems.endStoneCompressed3x);
		OreDictionary.registerOre("endStoneCompressed4x", ModItems.endStoneCompressed4x);
		OreDictionary.registerOre("blockTarydiumAlloyRefined", ModItems.blockTarydiumAlloyRefined);
		OreDictionary.registerOre("blockTarydiumAlloyUnrefined", ModItems.blockTarydiumAlloyUnrefined);
		OreDictionary.registerOre("ingotTarydiumAlloyRefined", ModItems.ingotTarydiumAlloyRefined);
		OreDictionary.registerOre("ingotTarydiumAlloyUnrefined", ModItems.ingotTarydiumAlloyUnrefined);
		OreDictionary.registerOre("nuggetTarydiumAlloyRefined", ModItems.nuggetTarydiumAlloyRefined);
		OreDictionary.registerOre("nuggetTarydiumAlloyUnrefined", ModItems.nuggetTarydiumAlloyUnrefined);
		OreDictionary.registerOre("blockFlint", ModItems.blockFlint);
		OreDictionary.registerOre("blockGunpowder", ModItems.blockGunpowder);
		OreDictionary.registerOre("dustIron", ModItems.dustIron);
		OreDictionary.registerOre("dustGold", ModItems.dustGold);
		OreDictionary.registerOre("dustTarydiumAlloyUnrefined", ModItems.dustTarydiumAlloyUnrefined);
		OreDictionary.registerOre("dustTarydiumAlloyRefined", ModItems.dustTarydiumAlloyRefined);
		OreDictionary.registerOre("rodTarydiumAlloyUnrefined", ModItems.rodTarydiumAlloyUnrefined);
		OreDictionary.registerOre("rodTarydiumAlloyRefined", ModItems.rodTarydiumAlloyRefined);
		OreDictionary.registerOre("blockBlaze", ModItems.blockBlaze);
		OreDictionary.registerOre("blockTear", ModItems.blockGhastTear);
		OreDictionary.registerOre("blockBlazeRod", ModItems.blockBlaze);
		OreDictionary.registerOre("blockGhastTear", ModItems.blockGhastTear);
		OreDictionary.registerOre("dustCoal", ModItems.dustCoal);
		OreDictionary.registerOre("dustCharcoal", ModItems.dustCharcoal);
		OreDictionary.registerOre("dustLapis", ModItems.dustLapis);
		OreDictionary.registerOre("dyeBlue", ModItems.dustLapis);
		OreDictionary.registerOre("dustDiamond", ModItems.dustDiamond);
		OreDictionary.registerOre("dustEmerald", ModItems.dustEmerald);
		OreDictionary.registerOre("dustNetherQuartz", ModItems.dustNetherQuartz);
		OreDictionary.registerOre("record", ModItems.recordUnreal);
		OreDictionary.registerOre("blockSlime", ModItems.blockSlime);
		OreDictionary.registerOre("cropNetherWart", Item.netherStalkSeeds);
		OreDictionary.registerOre("blockNetherWart", ModItems.blockNetherWart);
		OreDictionary.registerOre("oreAsbestos", ModItems.oreAsbestos);
		OreDictionary.registerOre("dustAsbestos", ModItems.dustAsbestos);
		OreDictionary.registerOre("blockAsbestos", ModItems.blockAsbestos);
		OreDictionary.registerOre("blockCoal", ModItems.blockCoal);
		OreDictionary.registerOre("blockCharcoal", ModItems.blockCharcoal);
		OreDictionary.registerOre("dustObsidian", ModItems.dustObsidian);
		OreDictionary.registerOre("dustCinnabar", ModItems.dustCinnabar);
		OreDictionary.registerOre("gemCinnabar", ModItems.crystalCinnabar);
		OreDictionary.registerOre("blockCinnabar", ModItems.blockCinnabar);
		OreDictionary.registerOre("oreBedrockium", ModItems.oreBedrockium);
		OreDictionary.registerOre("ingotBedrockium", ModItems.ingotBedrockium);
		OreDictionary.registerOre("nuggetBedrockium", ModItems.nuggetBedrockium);
		OreDictionary.registerOre("blockBedrockium", ModItems.blockBedrockium);
		OreDictionary.registerOre("blockCocoa", ModItems.blockCocoa);
		
		//buckets & fluids (temp)
		OreDictionary.registerOre("fluidWater", Item.bucketWater);
		OreDictionary.registerOre("fluidLava", Item.bucketLava);
		OreDictionary.registerOre("fluidMilk", Item.bucketMilk);
		OreDictionary.registerOre("fluidMercury", ModItems.bucketMercury);
		
		//worldgen
		
		GameRegistry.registerWorldGenerator(new ModWorldGen());
		
		//vanilla tweaks
		
		LanguageRegistry.addName(Block.cobblestoneMossy, "Mossy Cobblestone");
		LanguageRegistry.addName(Item.bucketMilk, "Milk Bucket");
		
		MinecraftForge.setBlockHarvestLevel(Block.melon, "axe", 0);
		
		final int CART_MAX_STACK_SIZE = 3;
		final int BOAT_MAX_STACK_SIZE = 3;
		final int SADDLE_MAX_STACK_SIZE = 3;
		if(Item.minecartEmpty.getItemStackLimit()<CART_MAX_STACK_SIZE)Item.minecartEmpty.setMaxStackSize(CART_MAX_STACK_SIZE);
		if(Item.minecartCrate.getItemStackLimit()<CART_MAX_STACK_SIZE)Item.minecartCrate.setMaxStackSize(CART_MAX_STACK_SIZE);
		if(Item.minecartHopper.getItemStackLimit()<CART_MAX_STACK_SIZE)Item.minecartHopper.setMaxStackSize(CART_MAX_STACK_SIZE);
		if(Item.minecartPowered.getItemStackLimit()<CART_MAX_STACK_SIZE)Item.minecartPowered.setMaxStackSize(CART_MAX_STACK_SIZE);
		if(Item.minecartTnt.getItemStackLimit()<CART_MAX_STACK_SIZE)Item.minecartTnt.setMaxStackSize(3);
		if(Item.boat.getItemStackLimit()<BOAT_MAX_STACK_SIZE)Item.boat.setMaxStackSize(BOAT_MAX_STACK_SIZE);
		if(Item.saddle.getItemStackLimit()<SADDLE_MAX_STACK_SIZE)Item.saddle.setMaxStackSize(SADDLE_MAX_STACK_SIZE);
		
		//flammability

        Block.setBurnProperties(ModItems.blockCoal.blockID, 30, 60);
        Block.setBurnProperties(ModItems.blockCharcoal.blockID, 30, 60);
		
		//handlers
		
		//GameRegistry.registerCraftingHandler(ModCrafting);
		GameRegistry.registerFuelHandler(new ModFuelHandler());
		GameRegistry.registerTileEntity(TileEntityEviscerator.class, "unreal.eviscerator");
		
		addOreRecipes();
		addSmelting();
		
	}
	
	public static void addSmelting(){
		GameRegistry.addSmelting(ModItems.oreRutile.blockID, 
				new ItemStack(ModItems.ingotRutile,1), 1.0F);
		
		GameRegistry.addSmelting(ModItems.cactusTarydium.blockID, 
				new ItemStack(ModItems.shardTarydium,1), 0.1F);
		
		GameRegistry.addSmelting(ModItems.tarydPowerBasic.itemID, 
				new ItemStack(ModItems.recordUnreal,1), 0.1F);
		
		GameRegistry.addSmelting(ModItems.flakShellCold.itemID, 
				new ItemStack(ModItems.flakShellWarm,1), 0.1F);
		
		GameRegistry.addSmelting(ModItems.oreStone.blockID, 
				new ItemStack(ModItems.ingotStone,1), 0.1F);
		
		GameRegistry.addSmelting(Block.cobblestoneMossy.blockID, 
				new ItemStack(ModItems.stoneMossySmooth,1), 0.1F);
		
		GameRegistry.addSmelting(ModItems.caseIron.itemID, 
				new ItemStack(Item.ingotIron,2), 0.1F);
		
		GameRegistry.addSmelting(ModItems.caseTitanium.itemID, 
				new ItemStack(ModItems.ingotTitanium,2), 0.1F);
		
		GameRegistry.addSmelting(ModItems.dustMagnesium.itemID, 
				new ItemStack(ModItems.ingotMagnesium,1), 0.1F);
		
		GameRegistry.addSmelting(ModItems.dustTitanium.itemID, 
				new ItemStack(ModItems.ingotTitanium,1), 0.1F);
		
		GameRegistry.addSmelting(ModItems.dustRutile.itemID, 
				new ItemStack(ModItems.ingotRutile,1), 0.1F);
		
		GameRegistry.addSmelting(ModItems.blockUUTC.blockID, 
				new ItemStack(ModItems.nuggetTarydiumAlloyUnrefined,1), 1.0F);
		
		GameRegistry.addSmelting(ModItems.dustIron.itemID, 
				new ItemStack(Item.ingotIron,1), 0.1F);
		
		GameRegistry.addSmelting(ModItems.dustGold.itemID, 
				new ItemStack(Item.ingotGold,1), 0.1F);
		
		GameRegistry.addSmelting(ModItems.dustTarydiumAlloyUnrefined.itemID, 
				new ItemStack(ModItems.ingotTarydiumAlloyUnrefined,1), 0.1F);
		
		GameRegistry.addSmelting(ModItems.dustTarydiumAlloyRefined.itemID, 
				new ItemStack(ModItems.ingotTarydiumAlloyRefined,1), 0.1F);

		GameRegistry.addSmelting(ModItems.gunBarrelIron.itemID, 
				new ItemStack(ModItems.nuggetIron,14), 0.1F);
		
		GameRegistry.addSmelting(ModItems.gunBarrelIronLarge.itemID, 
				new ItemStack(Item.ingotIron,12), 0.1F);
		
		GameRegistry.addSmelting(ModItems.chainPart.itemID, 
				new ItemStack(ModItems.nuggetIron,5), 0.1F);
		
		GameRegistry.addSmelting(ModItems.chainsawBlade.itemID, 
				new ItemStack(Item.ingotIron,6), 0.1F);
		
		GameRegistry.addSmelting(ModItems.ladderIron.blockID, 
				new ItemStack(Item.ingotIron,1), 0.1F);
		
	}
	
	public static void addOreRecipes(){
		//vanilla tweaks
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.appleGold,1,0), new Object[]{
			"GGG","GAG","GGG",'G',"ingotGold",'A',Item.appleRed,
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.appleGold,1,1), new Object[]{
			"GGG","GAG","GGG",'G',"blockGold",'A',Item.appleRed,
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.ingotGold,1), new Object[]{
			"III","III","III",'I',"nuggetGold",
			}));
		
		//TODO temporary debugTool recipes ---------------------------------------------------------------------------------------------------------------------------------
		
		//crafting
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.debugTool,4), new Object[]{
			"blockTarydium","blockIron","blockFlint","blockCoal",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Block.cobblestone,1), new Object[]{
			"virtuoel.unreal.debug",
			}));
		
		//fueling
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.debugTool,3), new Object[]{
			"virtuoel.unreal.debug","coal","coal",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.debugTool,3), new Object[]{
			"virtuoel.unreal.debug","charcoal","coal",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.debugTool,3), new Object[]{
			"virtuoel.unreal.debug","charcoal","charcoal",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.debugTool,3), new Object[]{
			"virtuoel.unreal.debug","coal","virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.debugTool,3), new Object[]{
			"virtuoel.unreal.debug","charcoal","virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.debugTool,4), new Object[]{
			"virtuoel.unreal.debug","charcoal","charcoal","virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.debugTool,4), new Object[]{
			"virtuoel.unreal.debug","coal","charcoal","virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.debugTool,4), new Object[]{
			"virtuoel.unreal.debug","coal","coal","virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.debugTool,13), new Object[]{
			"virtuoel.unreal.debug","fluidLava",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.debugTool,11), new Object[]{
			"virtuoel.unreal.debug","blockCoal",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.debugTool,10), new Object[]{
			"virtuoel.unreal.debug","blockCharcoal",
			}));
		
		//grinding
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.dustMagnesium,1), new Object[]{
			"ingotMagnesium","virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.dustMagnesium,4), new Object[]{
			"oreMagnesium","virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.dustRutile,1), new Object[]{
			"ingotRutile","virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.dustRutile,2), new Object[]{
			"oreRutile","virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.dustTitanium,1), new Object[]{
			"ingotTitanium","virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Item.lightStoneDust,4), new Object[]{
			Block.glowStone,"virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Block.cobblestoneMossy,1), new Object[]{
			ModItems.stoneMossySmooth,"virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Block.cobblestoneMossy,1), new Object[]{
			new ItemStack(Block.cobblestoneWall,1,1),"virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Block.cobblestoneMossy,1), new Object[]{
			new ItemStack(Block.stoneBrick,1,1),"virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Item.gunpowder,4), new Object[]{
			Block.tnt,"virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Item.blazePowder,4), new Object[]{
			"rodBlaze","virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Item.dyePowder,4,1), new Object[]{
			Block.plantRed,"virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Item.dyePowder,4,11), new Object[]{
			Block.plantYellow,"virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Item.netherQuartz,4), new Object[]{
			"blockNetherQuartz","virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Item.flint,1), new Object[]{
			Block.gravel,"virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.dustTarydiumAlloyUnrefined,1), new Object[]{
			"ingotTarydiumAlloyUnrefined","virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.dustTarydiumAlloyRefined,1), new Object[]{
			"ingotTarydiumAlloyRefined","virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.dustIron,1), new Object[]{
			"ingotIron","virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.dustIron,2), new Object[]{
			"oreIron","virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.dustGold,1), new Object[]{
			"ingotGold","virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.dustGold,2), new Object[]{
			"oreGold","virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.dustCoal,1), new Object[]{
			"coal","virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.dustCharcoal,1), new Object[]{
			"charcoal","virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.dustLapis,1), new Object[]{
			"gemLapis","virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.dustDiamond,1), new Object[]{
			"gemDiamond","virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.dustEmerald,1), new Object[]{
			"gemEmerald","virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.dustNetherQuartz,1), new Object[]{
			"crystalNetherQuartz","virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.dustObsidian,4), new Object[]{
			Block.obsidian,"virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.dustObsidian,4), new Object[]{
			ModItems.obsidianNether,"virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Item.silk,4), new Object[]{
			new ItemStack(Block.cloth,1,OreDictionary.WILDCARD_VALUE),"virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Block.sand,1), new Object[]{
			"cobblestone","virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.dustAsbestos,2), new Object[]{
			"oreAsbestos","virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.dustIron,3), new Object[]{
			Item.bucketEmpty,"virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.dustCinnabar,1), new Object[]{
			"gemCinnabar","virtuoel.unreal.debug",
			}));
		
		//refining
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.crystalCinnabar,1), new Object[]{
			"virtuoel.unreal.debug","oreRedstone",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.bucketMercury,1), new Object[]{
			"virtuoel.unreal.debug","dustCinnabar","virtuoel.unreal.debug","dustCinnabar",Item.bucketEmpty,"dustCinnabar","virtuoel.unreal.debug","dustCinnabar","virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.nuggetTarydiumAlloyRefined,9), new Object[]{
			"ingotTarydiumAlloyUnrefined","virtuoel.unreal.debug","fluidMercury",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Block.gravel,1), new Object[]{
			Item.flint,Block.cobblestone,"virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.oreTarydiumNether,1), new Object[]{
			ModItems.oreTarydium,"netherrackCompressed4x","virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.oreTarydiumEnd,1), new Object[]{
			ModItems.oreTarydiumNether,"endStoneCompressed4x","virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.oreTarydiumObsidian,1), new Object[]{
			ModItems.obsidianNether,"virtuoel.unreal.debug",ModItems.obsidianNether,ModItems.obsidianNether,ModItems.oreTarydiumEnd,ModItems.obsidianNether,ModItems.obsidianNether,ModItems.obsidianNether,ModItems.obsidianNether,
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.rodTarydiumAlloyRefined,1), new Object[]{
			"fluidMercury","virtuoel.unreal.debug","fluidMercury","fluidMercury","rodTarydiumAlloyUnrefined","fluidMercury","fluidMercury","fluidMercury",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.SCUBATank,1), new Object[]{
			ModItems.SCUBATankEmpty,"virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Item.coal,1,0), new Object[]{
			"dustCoal","virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Item.coal,1,1), new Object[]{
			"dustCharcoal","virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Item.dyePowder,1,4), new Object[]{
			"dustLapis","virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Item.diamond,1), new Object[]{
			"dustDiamond","virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Item.emerald,1), new Object[]{
			"dustEmerald","virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Item.netherQuartz,1), new Object[]{
			"dustNetherQuartz","virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Block.obsidian,1), new Object[]{
			"dustObsidian","dustObsidian","dustObsidian","dustObsidian","virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Item.gunpowder,1), new Object[]{
			Item.flint,Item.flint,"virtuoel.unreal.debug",Item.flint,Item.flint,
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Item.leather,1), new Object[]{
			Item.rottenFlesh,Item.sugar,Item.sugar,"virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Item.leather,2), new Object[]{
			Item.rottenFlesh,Item.sugar,Item.sugar,Item.rottenFlesh,Item.sugar,Item.sugar,"virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Block.whiteStone,1), new Object[]{
			Block.sandStone,Item.enderPearl,"virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Block.whiteStone,5), new Object[]{
			Block.obsidian,Block.obsidian,Block.obsidian,Block.obsidian,Block.obsidian,Item.enderPearl,"virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Block.obsidian,1), new Object[]{
			"fluidWater","fluidLava","virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Block.obsidian,2), new Object[]{
			"fluidWater","fluidLava","fluidWater","fluidLava","virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Block.obsidian,3), new Object[]{
			"fluidWater","fluidLava","fluidWater","fluidLava","fluidWater","fluidLava","virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Block.obsidian,4), new Object[]{
			"fluidWater","fluidLava","fluidWater","fluidLava","fluidWater","fluidLava","fluidWater","fluidLava","virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.crystalCinnabar,1), new Object[]{
			"dustCinnabar","virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.nuggetBedrockium,1), new Object[]{
			"fluidMercury","virtuoel.unreal.debug","fluidMercury","fluidMercury","oreBedrockium","fluidMercury","fluidMercury","fluidMercury","fluidMercury",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Block.dragonEgg,2), new Object[]{
			ModItems.blockEnderCrystal,"virtuoel.unreal.debug",ModItems.blockEnderCrystal,ModItems.blockEnderCrystal,Block.dragonEgg,ModItems.blockEnderCrystal,ModItems.blockEnderCrystal,"blockBedrockium",ModItems.blockEnderCrystal,
			}));
		
		//unrefining
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.debugTool,1), new Object[]{
			"virtuoel.unreal.debug","virtuoel.unreal.debug","fluidMercury",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.nuggetTarydiumAlloyUnrefined,1), new Object[]{
			"nuggetTarydiumAlloyRefined","virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.nuggetRutile,1), new Object[]{
			"nuggetTitanium","virtuoel.unreal.debug",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.SCUBATankEmpty,1), new Object[]{
			ModItems.SCUBATank,"virtuoel.unreal.debug",
			}));
		//TODO debug recipe end ---------------------------------------------------------------------------------------------------------------------------------
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.blockTitanium,1), new Object[]{
			"TTT","TTT","TTT",'T',"ingotTitanium",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.ingotTitanium,9), new Object[]{
			"T",'T',"blockTitanium",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.ingotTitanium,1), new Object[]{
			"TTT","TTT","TTT",'T',"nuggetTitanium",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.nuggetTitanium,9), new Object[]{
			"T",'T',"ingotTitanium",
			}));
		//
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.blockRutile,1), new Object[]{
			"TTT","TTT","TTT",'T',"ingotRutile",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.ingotRutile,9), new Object[]{
			"T",'T',"blockRutile",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.ingotRutile,1), new Object[]{
			"TTT","TTT","TTT",'T',"nuggetRutile",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.nuggetRutile,9), new Object[]{
			"T",'T',"ingotRutile",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.blockMagnesium,1), new Object[]{
			"TTT","TTT","TTT",'T',"ingotMagnesium",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.ingotMagnesium,9), new Object[]{
			"T",'T',"blockMagnesium",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.ingotMagnesium,1), new Object[]{
			"TTT","TTT","TTT",'T',"nuggetMagnesium",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.nuggetMagnesium,9), new Object[]{
			"T",'T',"ingotMagnesium",
			}));
		//
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.ingotTarydiumAlloyRefined,9), new Object[]{
			"T",'T',"blockTarydiumAlloyRefined",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.ingotTarydiumAlloyRefined,1), new Object[]{
			"TTT","TTT","TTT",'T',"nuggetTarydiumAlloyRefined",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.nuggetTarydiumAlloyRefined,9), new Object[]{
			"T",'T',"ingotTarydiumAlloyRefined",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.blockTarydiumAlloyRefined,1), new Object[]{
			"TTT","TTT","TTT",'T',"ingotTarydiumAlloyRefined",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.ingotTarydiumAlloyUnrefined,9), new Object[]{
			"T",'T',"blockTarydiumAlloyUnrefined",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.ingotTarydiumAlloyUnrefined,1), new Object[]{
			"TTT","TTT","TTT",'T',"nuggetTarydiumAlloyUnrefined",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.nuggetTarydiumAlloyUnrefined,9), new Object[]{
			"T",'T',"ingotTarydiumAlloyUnrefined",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.blockTarydiumAlloyUnrefined,1), new Object[]{
			"TTT","TTT","TTT",'T',"ingotTarydiumAlloyUnrefined",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.gemTarydium,1), new Object[]{
			"TTT","TTT","TTT",'T',"shardTarydium",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.shardTarydium,9), new Object[]{
			"T",'T',"gemTarydium",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.blockTarydium,1), new Object[]{
			"TTT","TTT","TTT",'T',"gemTarydium",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.gemTarydium,9), new Object[]{
			"T",'T',"blockTarydium",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.blockEnder,1), new Object[]{
			"CC","CC",'C',Item.enderPearl,
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.enderPearl,4), new Object[]{
			"C",'C',"blockEnder",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.blockLeather,1), new Object[]{
			"TTT","TTT","TTT",'T',Item.leather,
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.leather,9), new Object[]{
			"C",'C',"blockLeather",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.gunpowder,9), new Object[]{
			"T",'T',"blockGunpowder",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.blockFlint,1), new Object[]{
			"TTT","TTT","TTT",'T',Item.flint,
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.flint,9), new Object[]{
			"T",'T',"blockFlint",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.blockGunpowder,1), new Object[]{
			"TTT","TTT","TTT",'T',"dustGunpowder",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.blazeRod,9), new Object[]{
			"T",'T',"blockBlaze",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.blockBlaze,1), new Object[]{
			"TTT","TTT","TTT",'T',"rodBlaze",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.ghastTear,9), new Object[]{
			"T",'T',"blockTear",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.blockGhastTear,1), new Object[]{
			"TTT","TTT","TTT",'T',"itemTear",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.blockSlime,1), new Object[]{
			"TTT","TTT","TTT",'T',Item.slimeBall,
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.slimeBall,9), new Object[]{
			"T",'T',"blockSlime",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.blockNetherWart,1), new Object[]{
			"TTT","TTT","TTT",'T',"cropNetherWart",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.netherStalkSeeds,9), new Object[]{
			"T",'T',"blockNetherWart",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.blockAsbestos,1), new Object[]{
			"TTT","TTT","TTT",'T',"dustAsbestos",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.dustAsbestos,9), new Object[]{
			"C",'C',"blockAsbestos",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.blockCoal,1), new Object[]{
			"TTT","TTT","TTT",'T',"coal",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.coal,9,0), new Object[]{
			"C",'C',"blockCoal",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.blockCharcoal,1), new Object[]{
			"TTT","TTT","TTT",'T',"charcoal",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.coal,9,1), new Object[]{
			"C",'C',"blockCharcoal",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.ingotBedrockium,9), new Object[]{
			"T",'T',"blockBedrockium",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.ingotBedrockium,1), new Object[]{
			"TTT","TTT","TTT",'T',"nuggetBedrockium",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.nuggetBedrockium,9), new Object[]{
			"T",'T',"ingotBedrockium",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.blockBedrockium,1), new Object[]{
			"TTT","TTT","TTT",'T',"ingotBedrockium",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.crystalCinnabar,9), new Object[]{
			"T",'T',"blockCinnabar",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.blockCinnabar,1), new Object[]{
			"TTT","TTT","TTT",'T',"gemCinnabar",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.dyePowder,9,3), new Object[]{
			"T",'T',"blockCocoa",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.blockCocoa,1), new Object[]{
			"TTT","TTT","TTT",'T',"cropCocoa",
			}));
		//end blocks ---------------------------------------------------------------------------------------------------------------------------------
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.axeTitanium,1), new Object[]{
			"TT ","TS "," S ",'T',"ingotTitanium",'S',Item.stick,
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.hoeTitanium,1), new Object[]{
			"TT"," S"," S",'T',"ingotTitanium",'S',Item.stick,
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.pickTitanium,1), new Object[]{
			"TTT"," S "," S ",'T',"ingotTitanium",'S',Item.stick,
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.shovelTitanium,1), new Object[]{
			"T","S","S",'T',"ingotTitanium",'S',Item.stick,
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.swordTitanium,1), new Object[]{
			"T","T","S",'T',"ingotTitanium",'S',Item.stick,
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.cactusTarydium,1), new Object[]{
			"STS","TCT","STS",'T',"gemTarydium",'C',Block.cactus,'S',"shardTarydium",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.weaponCasingBasic,1), new Object[]{
			"TTL","NPT"," BT",'T',"ingotTitanium",'B',Block.stoneButton,'P',ModItems.tarydPowerBasic,'L',Block.lever,'N',"nuggetTitanium",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.caseTitanium,2), new Object[]{
			"STS","T T","STS",'T',"ingotTitanium",'S',"nuggetTitanium",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.tarydPowerBasic,1), new Object[]{
			"NTN","NCN","NTN",'T',"gemTarydium",'N',"shardTarydium",'C',ModItems.caseTitanium,
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.eightball,4), new Object[]{
			"NIF","PSB","NIF",'S',ModItems.cactusTarydium,'I',"ingotIron",'P',"blockGunpowder",'F',Item.fireballCharge,'N',"nuggetIron",'B',"blockIron"
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.nuggetIron,9), new Object[]{
			"I",'I',"ingotIron",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.ingotIron,1), new Object[]{
			"III","III","III",'I',"nuggetIron",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.shieldBelt,1), new Object[]{
			"TLT","O O","TPT",'T',"blockTitanium",'L',"blockEmerald",'O',"blockGold",'P',ModItems.tarydPowerBasic,
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.gunBarrelIron,4), new Object[]{
			"III","N N","III",'N',"nuggetIron",'I',"ingotIron",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.gunEightball,1,48), new Object[]{
			"RTT","BKT","TTC",'B',ModItems.gunBarrelIronLarge,'C',ModItems.weaponCasingAdv,'T',"ingotTitanium",'R',"dustRedstone",'K',ModItems.eightball,
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.gunStinger,1,100-9+1), new Object[]{
			"BTT","BRT","TCT",'B',ModItems.gunBarrelIron,'C',ModItems.weaponCasingBasic,'T',"ingotTitanium",'R',ModItems.gemTarydium,
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.translocatorDisc,1), new Object[]{
			"TRT","NPN","TDT",'T',"blockTitanium",'D',"blockDiamond",'P',"blockEnder",'R',Block.torchRedstoneActive,'N',Item.comparator,
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.gunTranslocator,1,1), new Object[]{
			"DBE","SCR","TTT",'T',"blockTitanium",'D',ModItems.translocatorDisc,'C',ModItems.weaponCasingAdv,'B',ModItems.gunBarrelIron,'S',Block.daylightSensor,'R',"blockRedstone",'E',"blockEnder",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.weaponCasingAdv,1), new Object[]{
			"DTQ","TCY","RYT",'T',"ingotTitanium",'D',"gemDiamond",'C',ModItems.weaponCasingBasic,'Q',"crystalNetherQuartz",'R',"dustRedstone",'Y',"gemTarydium",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.jumpBoots,1), new Object[]{
			"TRT","TCT","PBP",'T',"ingotTitanium",'C',ModItems.tarydPowerBasic,'B',Item.bootsIron,'R',"blockRedstone",'P',Block.pistonBase,
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.gunFlak,1,50-5+1), new Object[]{
			"SBG","IBT","ICY",'I',"ingotTitanium",'C',ModItems.weaponCasingBasic,'B',ModItems.gunBarrelIron,'S',ModItems.flakShell,'Y',"dyeOrange",'G',"dustGunpowder",'T',"blockTitanium"
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.flak,2), new Object[]{
			"nuggetMagnesium","nuggetIron","dustGunpowder",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.flakShellCold,1), new Object[]{
			ModItems.flak,ModItems.flak,ModItems.flak,ModItems.flak,ModItems.flak,ModItems.flak,ModItems.flak,ModItems.flak,ModItems.caseTitanium,
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.flakShell,1), new Object[]{
			ModItems.flakShellWarm,"dyeOrange",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.ladderIron,3), new Object[]{
			"B B","BIB","B B",'I',"ingotIron",'B',Block.fenceIron,
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.stoneBrick,4,1), new Object[]{
			"TT","TT",'T',ModItems.stoneMossySmooth,
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.tileStoneDecor,1), new Object[]{
			"TT","TT",'T',Block.cobblestone,
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.cobblestone,4), new Object[]{
			"T",'T',ModItems.tileStoneDecor,
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.caseIron,2), new Object[]{
			"STS","T T","STS",'T',"ingotIron",'S',"nuggetIron",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.flakShellCold,1), new Object[]{
			ModItems.flak,ModItems.flak,ModItems.flak,ModItems.flak,ModItems.flak,ModItems.flak,ModItems.flak,ModItems.flak,ModItems.caseIron,
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.blockUUTC,1), new Object[]{
			"STS","TBT","STS",'S',"ingotStone",'T',"blockEnder",'B',"blockTarydium",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.blockUUTC,1), new Object[]{
			"STS","TBT","STS",'T',"ingotStone",'S',"blockEnder",'B',"blockTarydium",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.blockUUTC,1), new Object[]{
			"STS","TBT","STS",'S',"nuggetBedrockium",'T',"blockEnder",'B',"blockTarydium",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.blockUUTC,1), new Object[]{
			"STS","TBT","STS",'T',"nuggetBedrockium",'S',"blockEnder",'B',"blockTarydium",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.gunRedeemer,1,101), new Object[]{
			"MOT","OYT","TTL",'O',Block.obsidian,'T',"blockTitanium",'Y',"blockTarydium",'M',ModItems.redeemerMissile,'L',new ItemStack(ModItems.gunEightball,1,OreDictionary.WILDCARD_VALUE),
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.redeemerMissile,1), new Object[]{
			"CBT",'C',ModItems.warheadCone,'B',ModItems.warheadBody,'T',ModItems.warheadThruster,
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.warheadCone,1), new Object[]{
			"NIB","BTE","NIB",'N',"nuggetIron",'I',"ingotIron",'B',"blockIron",'T',Item.minecartTnt,'E',"blockEnder",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.warheadBody,1), new Object[]{
			"BIB","TCT","BIB",'I',"ingotIron",'B',"blockIron",'T',Item.minecartTnt,'C',ModItems.tarydPowerBasic,
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.warheadThruster,1), new Object[]{
			"IBB","ERT","IBB",'I',"blockIron",'B',ModItems.eightball,'E',"blockEnder",'R',Block.blockRedstone,'T',Item.minecartTnt,
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.chainPart,4), new Object[]{
			"TBT","BIB","TBT",'I',"ingotIron",'B',Block.fenceIron,'T',"nuggetTitanium",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.chainsawBlade,1), new Object[]{
			"BCC","CIC","CCB",'B',Block.fenceIron,'C',ModItems.chainPart,'I',ModItems.caseIron,
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.chainsawBladeHardened,1), new Object[]{
			"DDT","BBB","DDT",'D',"gemDiamond",'T',"gemTarydium",'B',ModItems.chainsawBlade,
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.chainsawMotor,1), new Object[]{
			"BTC","WPI","BAF",'B',Block.fenceIron,'T',"blockTitanium",'C',ModItems.tarydPowerBasic,'W',Block.pressurePlateIron,'P',Block.pistonBase,'I',"blockIron",'A',ModItems.weaponCasingAdv,'F',new ItemStack(ModItems.gunFlak,1,OreDictionary.WILDCARD_VALUE),
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.meleeChainsaw,1,1), new Object[]{
			"BBT","CCT","BBM",'B',Block.fenceIron,'T',"blockTitanium",'C',ModItems.chainsawBladeHardened,'M',ModItems.chainsawMotor,
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.helmetChain,1), new Object[]{
			"CCC","C C",'C',ModItems.chainPart,
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.plateChain,1), new Object[]{
			"C C","CCC","CCC",'C',ModItems.chainPart,
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.legsChain,1), new Object[]{
			"CCC","C C","C C",'C',ModItems.chainPart,
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.bootsChain,1), new Object[]{
			"C C","C C",'C',ModItems.chainPart,
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.gunBarrelIronLarge,1), new Object[]{
			"BBB","BIB","BBB",'B',ModItems.gunBarrelIron,'I',"ingotIron",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.dispersionUpgrade,1), new Object[]{
			"CEC","BTB","CEC",'T',"blockTitanium",'E',"blockEnder",'B',ModItems.gunBarrelIronLarge,'C',ModItems.tarydPowerBasic,
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.gunDispersionPistol,1,1), new Object[]{
			"ETC","BAU","EGC",'T',"ingotTitanium",'E',"blockEnder",'B',ModItems.gunBarrelIronLarge,'A',ModItems.gunBarrelIron,'C',ModItems.tarydPowerBasic,'U',ModItems.dispersionUpgrade,'G',ModItems.weaponCasingBasic,
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.dustTitanium,1), new Object[]{
			"dustRutile","dustMagnesium",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.dustTitanium,2), new Object[]{
			"dustRutile","dustMagnesium","dustRutile","dustMagnesium",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.dustTitanium,3), new Object[]{
			"dustRutile","dustMagnesium","dustRutile","dustMagnesium","dustRutile","dustMagnesium",
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.dustTitanium,4), new Object[]{
			"dustRutile","dustMagnesium","dustRutile","dustMagnesium","dustRutile","dustMagnesium","dustRutile","dustMagnesium",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.oreDiamond,8), new Object[]{
			"UUU","UDU","UUU",'U',ModItems.blockUUTC,'D',"dyeLightBlue",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.oreCoal,8), new Object[]{
			"UUU","UDU","UUU",'U',ModItems.blockUUTC,'D',"dyeBlack",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.oreEmerald,8), new Object[]{
			"UUU","UDU","UUU",'U',ModItems.blockUUTC,'D',"dyeLime",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.oreGold,8), new Object[]{
			"UUU","UDU","UUU",'U',ModItems.blockUUTC,'D',"dyeYellow",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.oreIron,8), new Object[]{
			"UUU","UDU","UUU",'U',ModItems.blockUUTC,'D',"dyeLightGray",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.oreLapis,8), new Object[]{
			"UUU","UDU","UUU",'U',ModItems.blockUUTC,'D',"dyeBlue",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.oreNetherQuartz,8), new Object[]{
			"UUU","UDU","UUU",'U',ModItems.blockUUTC,'D',"dyeWhite",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.oreRedstone,8), new Object[]{
			"UUU","UDU","UUU",'U',ModItems.blockUUTC,'D',"dyeRed",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.blockTarydium,1), new Object[]{
			"U",'U',ModItems.blockUUTC,
			}));
		
		//compressed start ---------------------------------------------------------------------------------------------------------------------------------
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.netherrackCompressed1x,1), new Object[]{
			"TTT","TTT","TTT",'T',"netherrack",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.netherrack,9), new Object[]{
			"T",'T',"netherrackCompressed1x",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.netherrackCompressed2x,1), new Object[]{
			"TTT","TTT","TTT",'T',"netherrackCompressed1x",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.netherrackCompressed1x,9), new Object[]{
			"T",'T',"netherrackCompressed2x",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.netherrackCompressed3x,1), new Object[]{
			"TTT","TTT","TTT",'T',"netherrackCompressed2x",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.netherrackCompressed2x,9), new Object[]{
			"T",'T',"netherrackCompressed3x",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.netherrackCompressed4x,1), new Object[]{
			"TTT","TTT","TTT",'T',"netherrackCompressed3x",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.netherrackCompressed3x,9), new Object[]{
			"T",'T',"netherrackCompressed4x",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.endStoneCompressed1x,1), new Object[]{
			"TTT","TTT","TTT",'T',Block.whiteStone,
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.whiteStone,9), new Object[]{
			"T",'T',"endStoneCompressed1x",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.endStoneCompressed2x,1), new Object[]{
			"TTT","TTT","TTT",'T',"endStoneCompressed1x",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.endStoneCompressed1x,9), new Object[]{
			"T",'T',"endStoneCompressed2x",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.endStoneCompressed3x,1), new Object[]{
			"TTT","TTT","TTT",'T',"endStoneCompressed2x",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.endStoneCompressed2x,9), new Object[]{
			"T",'T',"endStoneCompressed3x",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.endStoneCompressed4x,1), new Object[]{
			"TTT","TTT","TTT",'T',"endStoneCompressed3x",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.endStoneCompressed3x,9), new Object[]{
			"T",'T',"endStoneCompressed4x",
			}));
		//compressed end ---------------------------------------------------------------------------------------------------------------------------------
		//slabs start -------------------------------------------------------------------------------------------------------------------------------------
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.stonePolished,1), new Object[]{
			"S","B","S",'S',new ItemStack(Block.stoneSingleSlab,1,0),'B',"slimeball",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.sandStone,1), new Object[]{
			"S","B","S",'S',new ItemStack(Block.stoneSingleSlab,1,1),'B',"slimeball",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.planks,1,0), new Object[]{
			"S","B","S",'S',new ItemStack(Block.stoneSingleSlab,1,2),'B',"slimeball",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.cobblestone,1), new Object[]{
			"S","B","S",'S',new ItemStack(Block.stoneSingleSlab,1,3),'B',"slimeball",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.brick,1), new Object[]{
			"S","B","S",'S',new ItemStack(Block.stoneSingleSlab,1,4),'B',"slimeball",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.stoneBrick,1), new Object[]{
			"S","B","S",'S',new ItemStack(Block.stoneSingleSlab,1,5),'B',"slimeball",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.netherBrick,1), new Object[]{
			"S","B","S",'S',new ItemStack(Block.stoneSingleSlab,1,6),'B',"slimeball",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.blockNetherQuartz,1,0), new Object[]{
			"S","B","S",'S',new ItemStack(Block.stoneSingleSlab,1,7),'B',"slimeball",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.planks,1,0), new Object[]{
			"S","B","S",'S',new ItemStack(Block.woodSingleSlab,1,0),'B',"slimeball",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.planks,1,1), new Object[]{
			"S","B","S",'S',new ItemStack(Block.woodSingleSlab,1,1),'B',"slimeball",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.planks,1,2), new Object[]{
			"S","B","S",'S',new ItemStack(Block.woodSingleSlab,1,2),'B',"slimeball",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.planks,1,3), new Object[]{
			"S","B","S",'S',new ItemStack(Block.woodSingleSlab,1,3),'B',"slimeball",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.stoneSingleSlab,2,0), new Object[]{
			"S",'S',ModItems.stonePolished,
			}));
		//slabs end
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.saddle,1), new Object[]{
			"LLL","LWL","I I",'L',"blockLeather",'I',"blockIron",'W',new ItemStack(Block.cloth,1,OreDictionary.WILDCARD_VALUE),
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.asbestosSuit,1), new Object[]{
			"DMD","GCG","OTO",'D',"dustAsbestos",'O',"blockAsbestos",'M',"fluidMercury",'C',new ItemStack(ModItems.toxinSuit,1,OreDictionary.WILDCARD_VALUE),'G',"blockMagnesium",'T',"blockTitanium",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.toxinSuit,1), new Object[]{
			"MGM","TKT","ARA",'M',Block.obsidian,'M',"fluidMilk",'G',Item.speckledMelon,'T',"blockTitanium",'K',new ItemStack(Item.plateChain,1,OreDictionary.WILDCARD_VALUE),'A',"blockAsbestos",'R',"blockRutile",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.rodTarydiumAlloyRefined,1), new Object[]{
			"STS","SES","STS",'T',"blockTitanium",'S',"dustTarydiumAlloyRefined",'E',"endStoneCompressed4x"
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.rodTarydiumAlloyUnrefined,1), new Object[]{
			"STS","SES","STS",'T',"blockTitanium",'S',"dustTarydiumAlloyUnrefined",'E',"endStoneCompressed4x"
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.axeTarydiumAlloyRefined,1), new Object[]{
			"TTE","TSE","ESE",'T',"blockTarydiumAlloyRefined",'S',"rodTarydiumAlloyRefined",'E',"endStoneCompressed4x"
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.hoeTarydiumAlloyRefined,1), new Object[]{
			"TTE","ESE","ESE",'T',"blockTarydiumAlloyRefined",'S',"rodTarydiumAlloyRefined",'E',"endStoneCompressed4x"
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.pickTarydiumAlloyRefined,1), new Object[]{
			"TTT","ESE","ESE",'T',"blockTarydiumAlloyRefined",'S',"rodTarydiumAlloyRefined",'E',"endStoneCompressed4x"
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.shovelTarydiumAlloyRefined,1), new Object[]{
			"ETE","ESE","ESE",'T',"blockTarydiumAlloyRefined",'S',"rodTarydiumAlloyRefined",'E',"endStoneCompressed4x"
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.swordTarydiumAlloyRefined,1), new Object[]{
			"ETE","ETE","ESE",'T',"blockTarydiumAlloyRefined",'S',"rodTarydiumAlloyRefined",'E',"endStoneCompressed4x"
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.SCUBATankEmpty,1), new Object[]{
			" MC","ISI","ICI",'M',"nuggetMagnesium",'C',ModItems.chainPart,'I',"nuggetIron",'S',ModItems.caseIron,
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.SCUBAGear,1), new Object[]{
			"BBB","AHA","ATA",'A',ModItems.SCUBATank,'T',"blockTitanium",'B',ModItems.gunBarrelIron,'H',new ItemStack(Item.helmetIron,1,OreDictionary.WILDCARD_VALUE),
			}));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.toolVoiceBox,1), new Object[]{
			ModItems.flakShell,"blockRedstone",Block.music,ModItems.chainsawBlade,ModItems.weaponCasingBasic,ModItems.eightball,ModItems.gunBarrelIron,ModItems.tarydPowerBasic,"record",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.blockEnderCrystal,1), new Object[]{
			"RUR","UTU","RUR",'R',"blockTarydiumAlloyRefined",'U',"blockTarydiumAlloyUnrefined",'T',"blockTear",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.blockEnderCrystal,1), new Object[]{
			"RUR","UTU","RUR",'U',"blockTarydiumAlloyRefined",'R',"blockTarydiumAlloyUnrefined",'T',"blockTear",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.blockEnderCrystal,1), new Object[]{
			"RUR","UTU","RUR",'U',"blockTarydiumAlloyUnrefined",'R',"dustTarydiumAlloyRefined",'T',Item.netherStar,
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.blockEnderCrystal,1), new Object[]{
			"RUR","UTU","RUR",'R',"blockTarydiumAlloyUnrefined",'U',"dustTarydiumAlloyRefined",'T',Item.netherStar,
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.netherrack,4), new Object[]{
			"CCC","CNC","CCC",'C',"cobblestone",'N',"blockNetherWart",
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.obsidianNether,4), new Object[]{
			"WOW","ONO","WOW",'O',Block.obsidian,'W',"netherrackCompressed1x",'N',Item.magmaCream,
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.stoneSingleSlab,6,0), new Object[]{
			"SSS",'S',ModItems.stonePolished,
			}));
		
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
        // Stub Method
	}
	
}
