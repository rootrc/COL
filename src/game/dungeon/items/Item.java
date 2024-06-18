package game.dungeon.items;

import javax.swing.Action;
import javax.swing.ImageIcon;

import game.dungeon.inventory.Inventory;
import game.utilities.ActionUtilities;
import game.utilities.ImageUtilities;

public abstract class Item {
    private ImageIcon imageIcon;
    private ImageIcon rolloverIcon;
    private Action acquireItem;
    private String name;
    private Action useItem;

    public Item(Inventory inventory, int i, String name) {
        imageIcon = new ImageIcon(ImageUtilities.getImage("item_images", "itemTileSet", 0, i, 2));
        rolloverIcon = new ImageIcon(ImageUtilities.getImage("item_images", "itemTileSet1", 0, i, 2));
        this.name = name;
        acquireItem = ActionUtilities.addItem(inventory, this);
    }

    public abstract void enable(int idx);

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public ImageIcon getRolloverIcon() {
        return rolloverIcon;
    }

    public Action getAquireItem() {
        return acquireItem;
    }

    protected void setUseItem(Action action) {
        useItem = action;
    }

    public Action getUseItem() {
        return useItem;
    }

    public String getName() {
        return name;
    }

    public String getToolTip() {
        return name;
    }
}
