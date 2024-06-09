package game.utilities.game_components;

import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.Action;
import javax.swing.JButton;

public abstract class GameButton extends JButton {
    public GameButton(Action action, String actionCommand, Rectangle rect) {
        super(action);
        setBounds(rect.x, rect.y, rect.width, rect.height);
        setActionCommand(actionCommand);

        setLayout(null);
        setBorderPainted(false);
        setBorder(null);
        setFocusable(false);
        setMargin(new Insets(0, 0, 0, 0));

        setContentAreaFilled(false);

        // setIcon(new ImageIcon(ImageUtilities.getImage("item_data", "itemTileSet")));
        // setRolloverIcon();
        // setPressedIcon();
        // setDisabledIcon();

    }

}
