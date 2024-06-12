package game.dungeon.room.room_UI;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

import game.dungeon.inventory.Item;
import game.game_components.PopupUI;
import game.utilities.ActionUtilities;
import game.utilities.ImageUtilities;

public class ChestUI extends PopupUI {
	private Action check;

	public ChestUI(Item item, Action flash) {
		super(320, 256, 8, "ChestFloor");
		GetItemButton getItemButton = new GetItemButton(this, flash, item, new Rectangle(getWidth() / 2 - 32, getHeight() / 2 - 32, 64, 64));
		add(getItemButton);
		
		getInputMap(2).put(KeyStroke.getKeyStroke("pressed E"), "getAll");
		getActionMap().put("getAll", ActionUtilities.combineActions(getItemButton.getAction(), new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				getInputMap(2).put(KeyStroke.getKeyStroke("pressed E"), "close");
			}
		}));
		check = ActionUtilities.createConfirmUI((Action) (new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				ChestUI.super.exitPanel();
			}
		}), "areYouSure");
	}

	@Override 
	public void update() {
		super.update();
		// System.out.println("hi");	
	}

	@Override
	public void buildImage(String tileSet) {
		super.buildImage(tileSet);
		Graphics2D g2d = getImage().createGraphics();
		g2d.setColor(new Color(165, 120, 85));
		g2d.drawImage(ImageUtilities.getImage("UI", "ChestSlot"), getWidth() / 2 - 46, getHeight() / 2 - 46, null);
		g2d.dispose();
	}

	@Override
	public void exitPanel() {
		if (getComponentCount() == 0) {
			super.exitPanel();
		} else {
			check.actionPerformed(null);
		}
	}

}