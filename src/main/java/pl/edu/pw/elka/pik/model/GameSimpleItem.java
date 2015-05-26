package pl.edu.pw.elka.pik.model;

import pl.edu.pw.elka.pik.model.db.Game;

import java.io.Serializable;

/**
 * Created by Komatta on 2015-05-21.
 */
public class GameSimpleItem implements Serializable{
    private int id;
    private String title;

    public GameSimpleItem(){}
    public GameSimpleItem(Game game){
        setId(game.getId());
        setTitle(game.getName());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
