package game.game_panel;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import game.dungeon.Dungeon;
import game.game_components.GamePanel;
import game.game_components.UIButton;
import game.utilities.ImageUtilities;

public class Menu extends GamePanel {
    private final Action exit = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    };

    public Menu(Dungeon dungeon, Action changePanel) {
        super(changePanel);
        add(new UIButton(changePanel, "dungeon", new Rectangle(GamePanel.screenWidth / 2 - 192, 380, 384, 96),
                ImageUtilities.getImage("UI", "StartButton")));
        add(new UIButton(changePanel, "options", new Rectangle(GamePanel.screenWidth / 2 - 192, 500, 384, 96),
                ImageUtilities.getImage("UI", "OptionsButton")));
        add(new UIButton(exit, "exit", new Rectangle(GamePanel.screenWidth / 2 - 192, 620, 384, 96),
                ImageUtilities.getImage("UI", "ExitButton")));
    }
}
