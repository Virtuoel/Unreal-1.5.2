package virtuoel.unreal.entity;

import virtuoel.unreal.MainMod;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class EntityTarydiumShard extends EntityThrowable
{
    public float explosionRadius = 0.0F;
    public int harvestLevel = 0;
    public int shardQuantity = 1;

	public EntityTarydiumShard(World par1World)
    {
        super(par1World);
    }

    public EntityTarydiumShard(World par1World, EntityLiving par2EntityLiving, int par3)
    {
        super(par1World, par2EntityLiving);
        this.shellThrower=par2EntityLiving;
    	this.shellThrowerName=par2EntityLiving.getEntityName();
    	this.harvestLevel=par3;
    	this.shardQuantity=(int)Math.pow(2, par3);
    }

    public EntityTarydiumShard(World par1World, double par2, double par4, double par6, int par7)
    {
        super(par1World, par2, par4, par6);
    	this.harvestLevel=par7;
    	this.shardQuantity=(int)Math.pow(2, par7);
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
    			return this.getShellThrower().getEntityName() + "' Stinger";
    		}else{
    			return this.getShellThrower().getEntityName() + "'s Stinger";
    		}
    	}
        return "a Tarydium Shard";
    }

    @Override
    protected float getGravityVelocity(){
    	return 0.0F;
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
            byte b0 = (byte)shardQuantity;

            par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this), b0);
            par1MovingObjectPosition.entityHit.hurtResistantTime=0;
        }else{
        	int i = par1MovingObjectPosition.blockX;
            int j = par1MovingObjectPosition.blockY;
            int k = par1MovingObjectPosition.blockZ;
            
            if(this.getThrower() instanceof EntityPlayer){
            	if((MinecraftForge.getBlockHarvestLevel(Block.blocksList[this.worldObj.getBlockId(i, j, k)],this.worldObj.getBlockMetadata(i, j, k),"pickaxe")<this.harvestLevel ||
            			MinecraftForge.getBlockHarvestLevel(Block.blocksList[this.worldObj.getBlockId(i, j, k)],this.worldObj.getBlockMetadata(i, j, k),"axe")<this.harvestLevel ||
            			MinecraftForge.getBlockHarvestLevel(Block.blocksList[this.worldObj.getBlockId(i, j, k)],this.worldObj.getBlockMetadata(i, j, k),"shovel")<this.harvestLevel) &&
            			Block.blocksList[this.worldObj.getBlockId(i, j, k)].getBlockHardness(this.worldObj,i, j, k)>=0){
                	if(this.worldObj.getBlockId(i, j, k)!=MainMod.allItems.oreBedrockium.blockID)
                	{
                		this.worldObj.destroyBlock(i, j, k, true);
            		}
                }else{
                	
                }
            }
        }

        if (!this.worldObj.isRemote)
        {
        	//this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, (float)this.explosionRadius , false);
            this.setDead();
        }
    }
}
