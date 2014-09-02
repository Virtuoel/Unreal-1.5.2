package virtuoel.unreal.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;

public class EntityTranslocatorDisc extends EntityThrowable
{
	
	public EntityTranslocatorDisc(World par1World)
    {
        super(par1World);
    }

    public EntityTranslocatorDisc(World par1World, EntityLiving par2EntityLiving)
    {
        super(par1World, par2EntityLiving);
    }

    public EntityTranslocatorDisc(World par1World, double par2, double par4, double par6)
    {
        super(par1World, par2, par4, par6);
    }
    
    @Override
    public void onUpdate(){
    	super.onUpdate();
    	
    	if(this.getThrower().isDead||this.posY<=-320){
    		this.setDead();
    	}
    }

    @SideOnly(Side.CLIENT)
    public float getShadowSize()
    {
        return 1.0F;
    }
    
    public void tp(){
    	if (!this.worldObj.isRemote)
        {
            if (this.getThrower() != null && this.getThrower() instanceof EntityPlayerMP)
            {
                EntityPlayerMP entityplayermp = (EntityPlayerMP)this.getThrower();

                if (!entityplayermp.playerNetServerHandler.connectionClosed && entityplayermp.worldObj == this.worldObj)
                {
                	double newX = this.posX;
                	double newZ = this.posZ;
                	
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
                	
                    EnderTeleportEvent event = new EnderTeleportEvent(entityplayermp, newX, this.posY, newZ, 0);
                    if (!MinecraftForge.EVENT_BUS.post(event)){
                        this.getThrower().setPositionAndUpdate(event.targetX, event.targetY, event.targetZ);
                        this.getThrower().fallDistance = 0.0F;
                    }

                }
            }
            
        }
        this.setDead();
    }
    
    public boolean tp(EntityLiving par1EntityLiving){
    	if (!this.worldObj.isRemote)
        {
    		EntityLiving tpUser = this.worldObj.getPlayerEntityByName(par1EntityLiving.getEntityName());
            if (tpUser instanceof EntityPlayerMP)
            {
                EntityPlayerMP entityplayermp = (EntityPlayerMP)tpUser;
                
                if (!entityplayermp.playerNetServerHandler.connectionClosed && entityplayermp.worldObj == this.worldObj)
                {
                	double newX = this.posX;
                	double newZ = this.posZ;
                	
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
                	
                    EnderTeleportEvent event = new EnderTeleportEvent(entityplayermp, newX, this.posY, newZ, 0);
                    if (!MinecraftForge.EVENT_BUS.post(event)){
                    	tpUser.setPositionAndUpdate(event.targetX, event.targetY, event.targetZ);
                    	tpUser.fallDistance = 0.0F;
                    	this.setDead();
    					return true;
    				}else{
    					this.setDead();
    			    	return false;
    				}
                }
            }
        }
        this.setDead();
    	return false;
    }
    
    /*
    public boolean tp(EntityLiving par1EntityLiving){
    	if (!this.worldObj.isRemote)
    	{
    		//if(par1EntityLiving instanceof EntityPlayerMP){
    			//EntityPlayerMP entityplayermp = (EntityPlayerMP)par1EntityLiving;
    			
    			//if (!entityplayermp.playerNetServerHandler.connectionClosed && entityplayermp.worldObj == this.worldObj)
    			//{
    				double newX = this.posX;
    				double newZ = this.posZ;
    				
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
    				
    				EnderTeleportEvent event = new EnderTeleportEvent(par1EntityLiving, newX, this.posY, newZ, 0);
    				EntityPlayer playerToTp = this.worldObj.getPlayerEntityByName(par1EntityLiving.getEntityName());
    				if(playerToTp!=null){
        				event = new EnderTeleportEvent((EntityPlayerMP)playerToTp, newX, this.posY, newZ, 0);
    				}
    				if (!MinecraftForge.EVENT_BUS.post(event)){
    					par1EntityLiving.setPositionAndUpdate(event.targetX, event.targetY, event.targetZ);
    					par1EntityLiving.fallDistance = 0.0F;
    					//par1EntityLiving.setVelocity(0,0,0);
    			    	this.setDead();
    					return true;
    				}else{
    					this.setDead();
    			    	return false;
    				}
    			//}
    		//}
    	}
    	this.setDead();
    	return false;
    }*/
    
    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    
	@Override
	protected void onImpact(MovingObjectPosition movingobjectposition) {
		this.setVelocity2(0, 0, 0);
	}
	
	/**
     * Sets the velocity to the args. Args: x, y, z
     */
    public void setVelocity2(double par1, double par3, double par5)
    {
        this.motionX = par1;
        this.motionY = par3;
        this.motionZ = par5;

        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
        {
            float f = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
            this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(par1, par5) * 180.0D / Math.PI);
            this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(par3, (double)f) * 180.0D / Math.PI);
        }
    }
	
    
}
