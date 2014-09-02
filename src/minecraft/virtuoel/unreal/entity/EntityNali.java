package virtuoel.unreal.entity;

import virtuoel.unreal.MainMod;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityNali extends EntityAnimal {
    
    public EntityNali(World par1World) {
            super(par1World);
            this.texture = "mods/Unreal/textures/entities/mob/mobNali.png";
            //The below means if possible, it wont walk into water
            this.getNavigator().setAvoidsWater(true);
            this.getNavigator().setEnterDoors(true);
            //This is the hitbox size. I believe it starts in the center and grows outwards
            this.setSize(1.0F, 3.0F);
            //Pretty self-explanatory. 
            this.isImmuneToFire = false;
            float var2 = 0.25F;
            
            //Now, we have the AI. Each number in the addTask is a priority. 0 is the highest, the largest is lowest.
            //They should be set in the order which the mob should focus, because it can only do one thing at a time. I'll explain my choice for order below.
            //There are tonnes of tasks you can add. Look in the JavaDocs or other mob classes to find some more!

            //Swimming should ALWAYS be first. Otherwise if your mob falls in water, but it's running away from you or something it'll drown.
            this.tasks.addTask(0, new EntityAISwimming(this));

            //This makes the mob run away when you punch it
            this.tasks.addTask(1, new EntityAIPanic(this, 0.38F));

            //If you have mating code, this allows it to mate.
            this.tasks.addTask(2, new EntityAIMate(this, var2));

            //This code is used to get the mob to follow you (like cows with wheat). Here it's set to a custom fruit
            //this.tasks.addTask(3, new EntityAITempt(this, 0.3F, MainMod.allItems.gemTarydium.itemID, false));

            //If the mob is a child, it will follow it's parent.
            this.tasks.addTask(4, new EntityAIFollowParent(this, 0.28F));

            //This makes the mob walk around. Without it, it'd just stand still.
            this.tasks.addTask(5, new EntityAIWander(this, var2));

            //This makes the mob watch the nearest player, within a range set by the float.
            this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));

            //Finally, this makes it look around when it's not looking at a player or wandering.
            this.tasks.addTask(7, new EntityAILookIdle(this));
    }

    //This is required. If it's false, none of the above takes effect.
    public boolean isAIEnabled() {
            return true;
    }
    
    //Pretty obvious, set it's health!
    public int getMaxHealth() {
            return 10;
    }
    
    //The sound effect played when it's just living, like a cow mooing.
    protected String getLivingSound() {
            return "mob.spider.say";
    }
    
    //The sound made when it's attacked. Often it's the same as the normal say sound, but sometimes different (such as in the ender dragon)
    protected String getHurtSound() {
            return "mob.endermen.scream";
    }
    
    //The sound made when it actually dies.
    protected String getDeathSound() {
            return "mob.zombie.say";
    }

    //The sound the mob plays when walking around.  
    protected void playStepSound(int par1, int par2, int par3, int par4) {
            this.worldObj.playSoundAtEntity(this, "mob.zombie.step", 0.15F,  1.0F);
    }
    
    //A basic example of what a mob should drop on death. For more advanced examples, look at code for chicken or squid.
    protected int getDropItemId() {
            return Item.beefRaw.itemID;
    }
    
    //This is required regardless of if your animal can breed or not. Set to null if it can't breed - I wont cover breeding here.
    public EntityAgeable createChild(EntityAgeable var1) {
            return null;
    }
}
