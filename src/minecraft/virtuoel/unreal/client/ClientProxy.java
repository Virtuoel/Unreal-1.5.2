package virtuoel.unreal.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.client.renderer.entity.RenderBoat;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.EntityList;
import net.minecraftforge.client.MinecraftForgeClient;
import virtuoel.unreal.CommonProxy;
import virtuoel.unreal.MainMod;
import virtuoel.unreal.entity.EntityFlak;
import virtuoel.unreal.entity.EntityFlakShell;
import virtuoel.unreal.entity.EntityLavaBoat;
import virtuoel.unreal.entity.EntityNali;
import virtuoel.unreal.entity.EntityTarydiumShard;
import virtuoel.unreal.model.ModelNali;
import virtuoel.unreal.render.RenderNali;

public class ClientProxy extends CommonProxy {
        
        @Override
        public void registerRenderers() {
        	
        	//entities
    		
    		EntityRegistry.registerGlobalEntityID(EntityFlakShell.class, "FlakShell", EntityRegistry.findGlobalUniqueEntityId());
    		EntityRegistry.registerGlobalEntityID(EntityFlak.class, "Flak", EntityRegistry.findGlobalUniqueEntityId());
    		EntityRegistry.registerGlobalEntityID(EntityTarydiumShard.class, "TarydiumShard", EntityRegistry.findGlobalUniqueEntityId());
    		EntityRegistry.registerGlobalEntityID(EntityNali.class, "Nali", EntityRegistry.findGlobalUniqueEntityId(), 48, 102);
    		
    		//TODO Update
    		EntityRegistry.registerGlobalEntityID(EntityLavaBoat.class, "LavaBoat", EntityRegistry.findGlobalUniqueEntityId());

    		
    		//renderers
    		
    		RenderingRegistry.registerEntityRenderingHandler(EntityFlakShell.class, new RenderSnowball(MainMod.allItems.flakShell));
    		RenderingRegistry.registerEntityRenderingHandler(EntityFlak.class, new RenderSnowball(MainMod.allItems.flak));
    		RenderingRegistry.registerEntityRenderingHandler(EntityTarydiumShard.class, new RenderSnowball(MainMod.allItems.shardTarydium));
        	RenderingRegistry.registerEntityRenderingHandler(EntityNali.class, new RenderNali(new ModelNali(), 0.5F));
    		//TODO Update
        	RenderingRegistry.registerEntityRenderingHandler(EntityLavaBoat.class, new RenderBoat());
        }
}
