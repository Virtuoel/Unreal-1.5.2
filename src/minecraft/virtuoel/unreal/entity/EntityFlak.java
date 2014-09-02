package virtuoel.unreal.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityFlak extends EntityThrowable
{
    public float explosionRadius = 0.0F;
    
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

	public EntityFlak(World par1World)
    {
        super(par1World);
    }

    public EntityFlak(World par1World, EntityLiving par2EntityLiving)
    {
        super(par1World, par2EntityLiving);
    }

    public EntityFlak(World par1World, double par2, double par4, double par6)
    {
        super(par1World, par2, par4, par6);
    }
    
    public EntityFlak(World par1World, double par2, double par4, double par6, EntityLiving par7EntityLiving)
    {
    	super(par1World, par2, par4, par6);
    	this.shellThrower=par7EntityLiving;
    	if(par7EntityLiving!=null){
    		this.shellThrowerName=par7EntityLiving.getEntityName();
    	}
    }
    
    @Override
    public String getEntityName()
    {
    	if(this.getShellThrower()!=null){
    		if(this.getShellThrower().getEntityName().substring(this.getShellThrower().getEntityName().length()-1).toLowerCase().equals('s')){
    			return this.getShellThrower().getEntityName() + "' Flak Cannon";
    		}else{
    			return this.getShellThrower().getEntityName() + "'s Flak Cannon";
    		}
    	}
        return "some loose flak";
    }
    
    @Override
    public void onUpdate(){
    	super.onUpdate();
    	if(this.posY>256+16||this.posY<-64){
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
            byte b0 = 4;

            par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this), b0);
            par1MovingObjectPosition.entityHit.hurtResistantTime=0;
        }
        
        if (!this.worldObj.isRemote)
        {
            this.setDead();
        }
    }
    
}
