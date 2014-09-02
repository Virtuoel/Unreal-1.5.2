package virtuoel.unreal.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class Entity8BallRocket extends EntityThrowable
{
    public float explosionRadius = 2.0F;

	public Entity8BallRocket(World par1World)
    {
        super(par1World);
    }

    public Entity8BallRocket(World par1World, EntityLiving par2EntityLiving)
    {
        super(par1World, par2EntityLiving);
        this.shellThrower=par2EntityLiving;
    	this.shellThrowerName=par2EntityLiving.getEntityName();
    }

    public Entity8BallRocket(World par1World, double par2, double par4, double par6)
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
    			return this.getShellThrower().getEntityName() + "' Eightball Launcher";
    		}else{
    			return this.getShellThrower().getEntityName() + "'s Eightball Launcher";
    		}
    	}
        return "an Eightball Rocket";
    }
    
    @Override
    protected float getGravityVelocity(){
    	return 0.0F;
    }
    
    @Override
    public void onUpdate(){
    	super.onUpdate();
    	if(this.posY>256+16||this.posY<-64){
    		//this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, (float)this.explosionRadius , false);
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
            byte b0 = 16;

            par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this), b0);
            par1MovingObjectPosition.entityHit.hurtResistantTime=0;
        }else{
        	int i = par1MovingObjectPosition.blockX;
            int j = par1MovingObjectPosition.blockY;
            int k = par1MovingObjectPosition.blockZ;
            if(this.worldObj.getBlockId(i, j, k)==46){
            	this.worldObj.setBlockToAir(i, j, k);
            	EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(this.worldObj, (double)((float)i + 0.5F), (double)((float)j + 0.5F), (double)((float)k + 0.5F), this.getThrower());
                entitytntprimed.fuse = this.worldObj.rand.nextInt(entitytntprimed.fuse / 4) + entitytntprimed.fuse / 8;
                this.worldObj.spawnEntityInWorld(entitytntprimed);
            }
            //this.worldObj.destroyBlock(i, j, k, true);
        }

        if (!this.worldObj.isRemote)
        {
        	this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, (float)this.explosionRadius , false);
            
        	this.setDead();
        }
    }
}
