package virtuoel.unreal.inventory;

import virtuoel.unreal.recipe.EviscerateRecipes;
import virtuoel.unreal.tileentity.TileEntityEviscerator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;

public class ContainerEviscerator extends Container
{
    private TileEntityEviscerator eviscerator;
    private int lastCookTime = 0;
    private int lastBurnTime = 0;
    private int lastItemBurnTime = 0;

    public ContainerEviscerator(InventoryPlayer par1InventoryPlayer, TileEntityEviscerator par2TileEntityEviscerator)
    {
        this.eviscerator = par2TileEntityEviscerator;
        this.addSlotToContainer(new Slot(par2TileEntityEviscerator, 0, 56, 17));
        this.addSlotToContainer(new Slot(par2TileEntityEviscerator, 1, 56, 53));
        this.addSlotToContainer(new SlotEviscerator(par1InventoryPlayer.player, par2TileEntityEviscerator, 2, 116, 35));
        int i;

        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(par1InventoryPlayer, i, 8 + i * 18, 142));
        }
    }

    public void addCraftingToCrafters(ICrafting par1ICrafting)
    {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate(this, 0, this.eviscerator.furnaceCookTime);
        par1ICrafting.sendProgressBarUpdate(this, 1, this.eviscerator.furnaceBurnTime);
        par1ICrafting.sendProgressBarUpdate(this, 2, this.eviscerator.currentItemBurnTime);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);

            if (this.lastCookTime != this.eviscerator.furnaceCookTime)
            {
                icrafting.sendProgressBarUpdate(this, 0, this.eviscerator.furnaceCookTime);
            }

            if (this.lastBurnTime != this.eviscerator.furnaceBurnTime)
            {
                icrafting.sendProgressBarUpdate(this, 1, this.eviscerator.furnaceBurnTime);
            }

            if (this.lastItemBurnTime != this.eviscerator.currentItemBurnTime)
            {
                icrafting.sendProgressBarUpdate(this, 2, this.eviscerator.currentItemBurnTime);
            }
        }

        this.lastCookTime = this.eviscerator.furnaceCookTime;
        this.lastBurnTime = this.eviscerator.furnaceBurnTime;
        this.lastItemBurnTime = this.eviscerator.currentItemBurnTime;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2)
    {
        if (par1 == 0)
        {
            this.eviscerator.furnaceCookTime = par2;
        }

        if (par1 == 1)
        {
            this.eviscerator.furnaceBurnTime = par2;
        }

        if (par1 == 2)
        {
            this.eviscerator.currentItemBurnTime = par2;
        }
    }

    public boolean canInteractWith(EntityPlayer par1EntityPlayer)
    {
        return this.eviscerator.isUseableByPlayer(par1EntityPlayer);
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 == 2)
            {
                if (!this.mergeItemStack(itemstack1, 3, 39, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (par2 != 1 && par2 != 0)
            {
                if (EviscerateRecipes.grinding().getGrindingResult(itemstack1) != null)
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if (TileEntityFurnace.isItemFuel(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
                    {
                        return null;
                    }
                }
                else if (par2 >= 3 && par2 < 30)
                {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false))
                    {
                        return null;
                    }
                }
                else if (par2 >= 30 && par2 < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 3, 39, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
        }

        return itemstack;
    }
}
