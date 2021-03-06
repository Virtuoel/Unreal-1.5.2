package virtuoel.unreal.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityChainsawAttack extends EntityThrowable
{
	
	public EntityChainsawAttack(World par1World)
    {
        super(par1World);
    }

    public EntityChainsawAttack(World par1World, EntityLiving par2EntityLiving)
    {
        super(par1World, par2EntityLiving);
        this.shellThrower=par2EntityLiving;
    	this.shellThrowerName=par2EntityLiving.getEntityName();
    }

    public EntityChainsawAttack(World par1World, double par2, double par4, double par6)
    {
        super(par1World, par2, par4, par6);
    }
    
    private EntityLiving shellThrower;
    private String shellThrowerName = null;
    
    public EntityLiving getShellThrower()
    {
        if (this.shellThrower == null && this.shellThrowerName != null && this.shellThrowerName.length() > 0)
        {
            this.shellThrower = this.worldObj.getPlayerEntityByName(this.shellThrowerName);
        }

        return this.shellThrower;
    }
    
    @Override
    public String getEntityName()
    {
    	if(this.getShellThrower()!=null){
    		if(this.getShellThrower().getEntityName().substring(this.getShellThrower().getEntityName().length()-1).toLowerCase().equals('s')){
    			return this.getShellThrower().getEntityName() + "' Chainsaw";
    		}else{
    			return this.getShellThrower().getEntityName() + "'s Chainsaw";
    		}
    	}
        return "a Chainsaw";
    }

    @Override
    protected float getGravityVelocity(){
    	return 0.0F;
    }
    
    @Override
    public void onUpdate(){
    	super.onUpdate();
    	
    	if(this.posY>256+16||this.posY<-64||this.getDistanceToEntity(this.getShellThrower())>2.5){
    		this.setDead();
    	}
    }
    
    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    @Override
    protected void onImpact(MovingObjectPosition par1MovingObjectPosition)
    {
        if (par1MovingObjectPosition.entityHit != null)
        {
        	if (par1MovingObjectPosition.entityHit.getEntityName() != this.getShellThrower().getEntityName())
            {
        		byte b0 = 3;
        		par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this), b0);
        		par1MovingObjectPosition.entityHit.hurtResistantTime=0;
            }else{
            	if (!this.worldObj.isRemote)
            	{
            		this.setDead();
            	}
            }
        }else{
        	if (!this.worldObj.isRemote)
        	{
        		this.setDead();
        	}
        }
    }
}
