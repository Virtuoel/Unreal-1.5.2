package virtuoel.unreal.recipe;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import virtuoel.unreal.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class EviscerateRecipes
{
    private static final EviscerateRecipes grindingBase = new EviscerateRecipes();

    /** The list of grinding results. */
    private Map grindingList = new HashMap();
    private HashMap<List<Integer>, ItemStack> metaGrindingList = new HashMap<List<Integer>, ItemStack>();

    /**
     * Used to call methods addGrinding and getGrindingResult.
     */
    public static final EviscerateRecipes grinding()
    {
        return grindingBase;
    }

    private EviscerateRecipes()
    {
        this.addGrinding(Block.oreIron.blockID, new ItemStack(ModItems.dustIron,2));
    }

    /**
     * Adds a grinding recipe.
     */
    public void addGrinding(int par1, ItemStack par2ItemStack)
    {
        this.grindingList.put(Integer.valueOf(par1), par2ItemStack);
    }

    public Map getGrindingList()
    {
        return this.grindingList;
    }

    /**
     * A metadata sensitive version of adding a furnace recipe.
     */
    public void addGrinding(int itemID, int metadata, ItemStack itemstack)
    {
        metaGrindingList.put(Arrays.asList(itemID, metadata), itemstack);
    }

    /**
     * Used to get the resulting ItemStack form a source ItemStack
     * @param item The Source ItemStack
     * @return The result ItemStack
     */
    public ItemStack getGrindingResult(ItemStack item) 
    {
        if (item == null)
        {
            return null;
        }
        ItemStack ret = (ItemStack)metaGrindingList.get(Arrays.asList(item.itemID, item.getItemDamage()));
        if (ret != null) 
        {
            return ret;
        }
        return (ItemStack)grindingList.get(Integer.valueOf(item.itemID));
    }

    public Map<List<Integer>, ItemStack> getMetaGrindingList()
    {
        return metaGrindingList;
    }
}
