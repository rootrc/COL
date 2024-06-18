package game.dungeon.items;


import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import game.dungeon.Dungeon;
import game.dungeon.inventory.Inventory;
import game.dungeon.room.room_UI.Note;
import game.utilities.ActionUtilities;

public class UICreatorItem extends Item {
    private int id;

    public UICreatorItem(Inventory inventory, int i, String name) {
        super(inventory, i - 1, name);
        id = i;
    }

    public void enable(int idx) {
        setUseItem(ActionUtilities.combineActions(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                Dungeon.clearUI();
            }
        }, ActionUtilities.openPopupUI(new Note(idx, id))));
    }
}
