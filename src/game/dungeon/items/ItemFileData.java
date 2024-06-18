package game.dungeon.items;

import game.utilities.FileOpener;

public class ItemFileData extends FileOpener {
    public final int N = 6;
    private final String names[] = new String[N];

    public ItemFileData() {
        super("item_data/names");
        for (int j = 0; j < N; j++) {
            names[j] = next();
        }
        closeFile();
    }

    public String getName(int i) {
        return names[i - 1];
    }
}
