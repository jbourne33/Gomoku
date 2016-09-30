package gomoku;

import javafx.scene.control.Label;

/**
 * Created by ben on 9/27/16.
 */
public class Move extends Label {
    private int x;
    private int y;
    private int player = -1;

    public Move(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    public int x() {
        return this.x;
    }

    public int y() {
        return this.y;
    }

    public void setPlayer(int num) { player = num; }

    public int getPlayer() {
        return this.player;
    }

}
