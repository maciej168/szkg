package pl.edu.pw.elka.pik.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pw.elka.pik.model.Game;
import pl.edu.pw.elka.pik.model.GameSimpleItem;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by Mikolaj on 2015-04-07.
 */
public class GameDAOImpl implements GameDAO {

    private SessionFactory sessionFactory;

    public GameDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private static final Logger LOGGER = Logger.getLogger(GameDAOImpl.class.getName());

    @Override
    @Transactional
    public List<Game> list() {
        @SuppressWarnings("unchecked")
        List<Game> listUser = (List<Game>) sessionFactory.getCurrentSession()
                .createCriteria(Game.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

        return listUser;
    }

    @Override
    public List<GameSimpleItem> getGameSimpleItem()
    {
        //todo
        List<GameSimpleItem> gamesList = new ArrayList<GameSimpleItem>();
        for (int i = 0; i < 5; i++) {
            GameSimpleItem g = new GameSimpleItem();
            g.setId(i);
            g.setTitle("Name " + i);
            gamesList.add(g);
        }
        return gamesList;
    }

    @Override
    public List<GameSimpleItem> getGameSimpleItem(int from, int to) {
        List<GameSimpleItem> gamesList = new ArrayList<GameSimpleItem>();
        for (int i = 0; i < 5; i++) {
            GameSimpleItem g = new GameSimpleItem();
            g.setId(i);
            g.setTitle("Name from " + i);
            gamesList.add(g);
        }
        return gamesList;
    }

    @Override
    public int getGameSimpleItemCount()
    {
        //todo
        return 23;
    }



    @Override
    public byte[] getGameImage(int gameID)
    {
        //todo
        byte[] imageInByte = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            BufferedImage image = ImageIO.read(new File("D:/test.jpg"));
            ImageIO.write(image, "jpg", baos);
            baos.flush();
            imageInByte = baos.toByteArray();

        } catch (IOException e)
        {
            LOGGER.log(Level.SEVERE, "IOException", e);
            imageInByte = new byte[0];
        } finally
        {
            try {
                baos.close();
            } catch (IOException e) {
                //faild
                LOGGER.log(Level.SEVERE, "Close", e);
            }
        }
        return imageInByte;
    }
}
