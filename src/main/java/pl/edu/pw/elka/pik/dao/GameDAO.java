package pl.edu.pw.elka.pik.dao;

import pl.edu.pw.elka.pik.model.Game;
import pl.edu.pw.elka.pik.model.GameSimpleItem;

import java.util.List;

/**
 * Created by Mikolaj on 2015-04-07.
 */
public interface GameDAO {
    List<Game> list();

    List<GameSimpleItem> getGameSimpleItem();
    List<GameSimpleItem> getGameSimpleItem(int from, int to);
    int getGameSimpleItemCount();
    byte[] getGameImage(int gameID);

}
