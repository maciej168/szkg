package pl.edu.pw.elka.pik.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pw.elka.pik.model.*;
import pl.edu.pw.elka.pik.model.db.Category;
import pl.edu.pw.elka.pik.model.db.Game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
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
            //TODO image
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
            detailItem.setId(game.getId());
            detailItem.setTitle(game.getName());
            detailItem.setDescription(game.getDescription());

            List<Integer> cats = new ArrayList<Integer>();
            for(Category c : game.getCategories()){
                cats.add(c.getId());
            }
            detailItem.setCategory(cats);
        }

        return detailItem;
    }

    @Override
    @Transactional
    public List<CategorySimpleItem> getCategoryList() {
        List<Category> ctg = sessionFactory.getCurrentSession()
                .createCriteria(Category.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

        List<CategorySimpleItem> categorys = new ArrayList<CategorySimpleItem>();
        for(Category c : ctg){
            categorys.add(new CategorySimpleItem(c));
        }
        return categorys;
    }

    private Set<Category> getGameCategories(Collection<Integer> categoryIds){
        List<Category> ctg = sessionFactory.getCurrentSession()
                .createCriteria(Category.class)
                .add(Restrictions.in("id",categoryIds))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        return new HashSet<Category>(ctg);
    }

    @Override
    @Transactional
    public int createGame(String gameTitle, List<Integer> gameCategory, String gameDescription, byte[] image) {
        Game game = new Game();
        game.setDescription(gameDescription);
        game.setName(gameTitle);
        game.setCategories(getGameCategories(gameCategory));
        try{
            sessionFactory.getCurrentSession().save(game);
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
        game.setCategories(getGameCategories(gameCategory));
        try {
            sessionFactory.getCurrentSession().update(game);
            //TODO image
        }catch (Exception e){
            LOGGER.warning("Error in updateGame - " + e.getMessage());
        }
    }
}
