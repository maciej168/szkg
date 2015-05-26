package pl.edu.pw.elka.pik.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pw.elka.pik.model.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
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
    @Transactional
    public List<GameSimpleItem> getGameSimpleItem(int from, int to) {
        List<Game> listUser = sessionFactory.getCurrentSession()
                .createCriteria(Game.class)
                .setFirstResult(from)
                .setMaxResults(to)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        List<GameSimpleItem> list = new ArrayList<GameSimpleItem>();
        for(Game g : listUser){
            list.add(new GameSimpleItem(g));
        }

        return list;
    }

    @Override
    public int getGameSimpleItemCount() {
        return gamesList.size();
    }


    @Override
    public byte[] getGameImage(int gameID) {
        //todo
        byte[] imageInByte = null;
        if (gameID > 0) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                InputStream in = this.getClass().getClassLoader().getResourceAsStream("placeholder.jpg");
                BufferedImage image = ImageIO.read(in);
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
    @Transactional
    public boolean deleteGame(int gameId) {
        boolean response = false;
        try {
            Game game = new Game();
            game.setId(gameId);
            sessionFactory.getCurrentSession().delete(game);
            response = true;
        }catch (Exception e){
            LOGGER.warning("Error in deleteGame - " + e.getMessage());
        }
        return response;
    }

    @Override
    @Transactional
    public GameDetailItem getGameDetail(int gameId) {
        Game game = (Game) sessionFactory.getCurrentSession()
                .createCriteria(Game.class)
                .add(Restrictions.eq("id",gameId))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();

        GameDetailItem detailItem = new GameDetailItem();

        if(game != null) {
            detailItem.setDescription(game.getDescription());
        }

        List<Integer> category = new ArrayList<Integer>();//TODO
        category.add(1);
        category.add(2);

        detailItem.getCategory().addAll(category);
        return detailItem;
    }

    @Override
    @Transactional
    public List<CategorySimpleItem> getCategoryList() {
//        List<Category> ctg = sessionFactory.getCurrentSession()
//                .createCriteria(Category.class)
//                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
//
//        List<CategorySimpleItem> categorys = new ArrayList<CategorySimpleItem>();
//        for(Category c : ctg){
//            categorys.add(new CategorySimpleItem(c));
//        }

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

    @Override
    @Transactional
    public int createGame(String gameTitle, List<Integer> gameCategory, String gameDescription, byte[] image) {
        Game game = new Game();
        game.setDescription(gameDescription);
        game.setName(gameTitle);
        try{
            sessionFactory.getCurrentSession().save(game);
            //TODO categories
            //TODO image
        }catch (Exception e){
            LOGGER.warning("Error in createGame - " + e.getMessage());
        }
        return game.getId();
    }

    @Override
    @Transactional
    public void updateGame(int gameId, String gameTitle, List<Integer> gameCategory, String gameDescription, byte[] image) {
        Game game = new Game();
        game.setId(gameId);
        game.setName(gameTitle);
        game.setDescription(gameDescription);
        try {
            sessionFactory.getCurrentSession().update(game);
            //TODO categories
            //TODO image
        }catch (Exception e){
            LOGGER.warning("Error in updateGame - " + e.getMessage());
        }
    }
}
