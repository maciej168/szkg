package pl.edu.pw.elka.pik.model.db;

import java.io.Serializable;

/**
 * Created by mastah on 2015-05-26.
 */
public class GameImage implements Serializable {
    private int gameId;
    private byte[] image;

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
