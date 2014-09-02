package virtuoel.unreal.block.material;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class ModMaterial extends Material
{
	
	public static final Material tarydium = new ModMaterial(MapColor.iceColor).setRequiresTool().setImmovableMobility();
	public static final Material bedrock = new ModMaterial(MapColor.stoneColor).setRequiresTool().setImmovableMobility();
	public static final Material immobile = new ModMaterial(MapColor.stoneColor).setRequiresTool().setImmovableMobility();
	
	public ModMaterial(MapColor par1MapColor) {
		super(par1MapColor);
	}
	
	/** Bool defining if the block can burn or not. */
	private boolean canBurn;
	
	/**
	 * Determines whether blocks with this material can be "overwritten" by other blocks when placed - eg snow, vines
	 * and tall grass.
	 */
	private boolean replaceable;
	
	/** Indicates if the material is translucent */
	private boolean isTranslucent;
	
	/**
	 * Determines if the material can be harvested without a tool (or with the wrong tool)
	 */
	private boolean requiresNoTool = true;
	
	/**
	 * Mobility information flag. 0 indicates that this block is normal, 1 indicates that it can't push other blocks, 2
	 * indicates that it can't be pushed.
	 */
	private int mobilityFlag;
	private boolean field_85159_M;
	
	/**
	 * Returns if blocks of these materials are liquids.
	 */
	public boolean isLiquid()
	{
		return false;
	}
	
	public boolean isSolid()
	{
		return true;
	}
	
	/**
	 * Will prevent grass from growing on dirt underneath and kill any grass below it if it returns true
	 */
	public boolean getCanBlockGrass()
	{
		return true;
	}
	
	/**
	 * Returns if this material is considered solid or not
	 */
	public boolean blocksMovement()
	{
		return true;
	}
	
	/**
	 * Marks the material as translucent
	 */
	private ModMaterial setTranslucent()
	{
		this.isTranslucent = true;
		return this;
	}
	
	/**
	 * Makes blocks with this material require the correct tool to be harvested.
	 */
	protected ModMaterial setRequiresTool()
	{
		this.requiresNoTool = false;
		return this;
	}
	
	/**
	 * Set the canBurn bool to True and return the current object.
	 */
	protected ModMaterial setBurning()
	{
		this.canBurn = true;
		return this;
	}
	
	/**
	 * Returns if the block can burn or not.
	 */
	public boolean getCanBurn()
	{
		return this.canBurn;
	}
	
	/**
	 * Sets {@link #replaceable} to true.
	 */
	public ModMaterial setReplaceable()
	{
		this.replaceable = true;
		return this;
	}
	
	/**
	 * Returns whether the material can be replaced by other blocks when placed - eg snow, vines and tall grass.
	 */
	public boolean isReplaceable()
	{
		return this.replaceable;
	}
	
	/**
	 * Indicate if the material is opaque
	 */
	public boolean isOpaque()
	{
		return this.isTranslucent ? false : this.blocksMovement();
	}
	
	/**
	 * Returns true if the material can be harvested without a tool (or with the wrong tool)
	 */
	public boolean isToolNotRequired()
	{
		return this.requiresNoTool;
	}
	
	/**
	 * Returns the mobility information of the material, 0 = free, 1 = can't push but can move over, 2 = total
	 * immobility and stop pistons.
	 */
	public int getMaterialMobility()
	{
		return this.mobilityFlag;
	}
	
	/**
	 * This type of material can't be pushed, but pistons can move over it.
	 */
	protected ModMaterial setNoPushMobility()
	{
		this.mobilityFlag = 1;
		return this;
	}
	
	/**
	 * This type of material can't be pushed, and pistons are blocked to move.
	 */
	protected ModMaterial setImmovableMobility()
	{
		this.mobilityFlag = 2;
		return this;
	}
	
	/**
	 * Set as harvestable in any case.
	 */
	protected ModMaterial setAlwaysHarvested()
	{
		this.field_85159_M = true;
		return this;
	}
	
	/**
	 * Check to see if we can harvest it in any case.
	 */
	public boolean isAlwaysHarvested()
	{
		return this.field_85159_M;
	}
	
}
