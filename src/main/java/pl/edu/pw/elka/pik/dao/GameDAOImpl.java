package pl.edu.pw.elka.pik.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pw.elka.pik.model.CategorySimpleItem;
import pl.edu.pw.elka.pik.model.Game;
import pl.edu.pw.elka.pik.model.GameDetailItem;
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

    private static final List<GameSimpleItem> gamesList = new ArrayList<GameSimpleItem>();

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
    public List<GameSimpleItem> getGameSimpleItem(int from, int to)
    {
        if (gamesList.size() <= to ) trash();
        System.out.println(gamesList.subList(from, to).size());
        
        return gamesList.subList(from, to);
    }

    @Override
    public int getGameSimpleItemCount()
    {
        return gamesList.size();
    }



    @Override
    public byte[] getGameImage(int gameID)
    {
        //todo
        byte[] imageInByte = null;
        if (gameID > 0) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                BufferedImage image = ImageIO.read(new File("D:/test.jpg"));
                ImageIO.write(image, "jpg", baos);
                baos.flush();
                imageInByte = baos.toByteArray();

            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "IOException", e);
                imageInByte = new byte[0];
            } finally {
                try {
                    baos.close();
                } catch (IOException e) {
                    //faild
                    LOGGER.log(Level.SEVERE, "Close", e);
                }
            }
        }
        return imageInByte;
    }

    @Override
    public boolean deleteGame(int gameId)
    {
        boolean response = false;
        for (GameSimpleItem item : gamesList)
        {
            if (item.getId() == gameId)
            {
                response = gamesList.remove(item);
                break;
            }
        }
        return response;
    }

    private void trash()
    {
        System.out.println("Trash");
        for (int i = 0; i < 25; i++) {
            GameSimpleItem g = new GameSimpleItem();
            g.setId(i);
            g.setTitle("Name from " + i);
            gamesList.add(g);
        }
    }

    @Override
    public GameDetailItem getGameDetail(int gameId)
    {
        GameDetailItem detail = new GameDetailItem();
        for (GameSimpleItem item : gamesList)
        {
            if (item.getId() == gameId)
            {
                detail.setId(gameId);
                detail.setTitle(item.getTitle());
                detail.setDescription("Life Is Strange is a five part episodic game that sets out to revolutionise story based choice and consequence games by allowing the player to rewind time and affect the past, present and future. \n" +
                        "You are Max, a photography senior who saves her old friend Chloe by discovering she can rewind time. The pair soon find themselves exposed to the darker side of Arcadia Bay as they uncover the disturbing truth behind the sudden disappearance of a fellow student. \n" +
                        "Meanwhile, Max begins to have premonitions as she struggles to understand the implications of her power. She must quickly learn that changing the past can sometimes lead to a devastating future.");
                List<Integer> category = new ArrayList<Integer>();
                category.add(1);
                category.add(2);
                detail.getCategory().addAll(category);
            }
        }
        return detail;
    }

    @Override
    public List<CategorySimpleItem> getCategoryList() {
        List<CategorySimpleItem> categorys = new ArrayList<CategorySimpleItem>();
        CategorySimpleItem c1 = new CategorySimpleItem();
        c1.setId(1);
        c1.setName("Horror");
        CategorySimpleItem c2 = new CategorySimpleItem();
        c2.setId(2);
        c2.setName("RPG");
        CategorySimpleItem c3 = new CategorySimpleItem();
        c3.setId(3);
        c3.setName("Przygodowa");
        categorys.add(c1);
        categorys.add(c2);
        categorys.add(c3);
        return categorys;
    }
}
