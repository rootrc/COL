package game.dungeon.items;

import game.dungeon.inventory.Inventory;
import game.game_components.Factory;

public class ItemFactory extends Factory<Item> {
    private Inventory inventory;
    private ItemFileData data;

    public ItemFactory(Inventory inventory) {
        this.inventory = inventory;
        data = new ItemFileData();
    }

    private int idx = -1;
    private boolean[] arr = new boolean[6];
    private int last;

    public Item getItem() {
        idx++;
        if (idx == 0) {
            updateSpaghettiCodeThing(1);
            return new UICreatorItem(inventory, 1, data.getName(1));
        } else if (idx == 1) {
            if (Math.random() < 0.5) {
                updateSpaghettiCodeThing(2);
                return new UICreatorItem(inventory, 2, data.getName(2));
            }
            updateSpaghettiCodeThing(5);
            return new UICreatorItem(inventory, 5, data.getName(5));
        } else if (idx == 2) {
            if (last == 2) {
                if (Math.random() < 0.5) {
                    updateSpaghettiCodeThing(5);
                    return new UICreatorItem(inventory, 5, data.getName(5));
                }
                if (Math.random() < 0.5) {
                    updateSpaghettiCodeThing(3);
                    return new UICreatorItem(inventory, 3, data.getName(3));
                }
                updateSpaghettiCodeThing(4);
                return new UICreatorItem(inventory, 4, data.getName(4));
            } else if (last == 5) {
                if (Math.random() < 0.5) {
                    updateSpaghettiCodeThing(2);
                    return new UICreatorItem(inventory, 2, data.getName(2));
                }
                if (Math.random() < 0.5) {
                    updateSpaghettiCodeThing(3);
                    return new UICreatorItem(inventory, 3, data.getName(3));
                }
                updateSpaghettiCodeThing(4);
                return new UICreatorItem(inventory, 4, data.getName(4));
            }
        } else if (idx == 3) {
            if (last == 2 || last == 5) {
                if (Math.random() < 0.5) {
                    updateSpaghettiCodeThing(3);
                    return new UICreatorItem(inventory, 3, data.getName(3));
                }
                updateSpaghettiCodeThing(4);
                return new UICreatorItem(inventory, 4, data.getName(4));
            } else if (last == 3) {
                if (arr[2]) {
                    if (Math.random() < 0.5) {
                        updateSpaghettiCodeThing(5);
                        return new UICreatorItem(inventory, 5, data.getName(5));
                    }
                    updateSpaghettiCodeThing(4);
                    return new UICreatorItem(inventory, 4, data.getName(4));
                } else if (arr[5]) {
                    if (Math.random() < 0.5) {
                        updateSpaghettiCodeThing(2);
                        return new UICreatorItem(inventory, 2, data.getName(2));
                    }
                    updateSpaghettiCodeThing(4);
                    return new UICreatorItem(inventory, 4, data.getName(4));
                }
            } else if (last == 4) {
                if (arr[2]) {
                    if (Math.random() < 0.5) {
                        updateSpaghettiCodeThing(5);
                        return new UICreatorItem(inventory, 5, data.getName(5));
                    }
                    updateSpaghettiCodeThing(3);
                    return new UICreatorItem(inventory, 3, data.getName(3));
                } else if (arr[5]) {
                    if (Math.random() < 0.5) {
                        updateSpaghettiCodeThing(2);
                        return new UICreatorItem(inventory, 2, data.getName(2));
                    }
                    updateSpaghettiCodeThing(3);
                    return new UICreatorItem(inventory, 3, data.getName(3));
                }
            }
        } else if (idx == 4) {
            for (int i = 1; i <= 5; i++) {
                if (!arr[i]) {
                    return new UICreatorItem(inventory, i, data.getName(i));
                }
            }
        }
        return new UICreatorItem(inventory, 6, data.getName(6));
    }

    private void updateSpaghettiCodeThing(int i) {
        arr[i] = true;
        last = i;
    }
}