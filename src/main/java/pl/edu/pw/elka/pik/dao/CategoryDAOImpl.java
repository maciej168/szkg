package pl.edu.pw.elka.pik.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pw.elka.pik.model.CategorySimpleItem;
import pl.edu.pw.elka.pik.model.db.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by mastah on 2015-05-27.
 */
public class CategoryDAOImpl implements CategoryDAO {

    private SessionFactory sessionFactory;

    private static final Logger LOGGER = Logger.getLogger(GameDAOImpl.class.getName());

    public CategoryDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
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

    @Override
    @Transactional
    public List<CategorySimpleItem> getCategorySimpleItem(int from, int to) {
        List<Category> listUser = sessionFactory.getCurrentSession()
                .createCriteria(Category.class)
                .setFirstResult(from)
                .setMaxResults(to)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        List<CategorySimpleItem> list = new ArrayList<CategorySimpleItem>();
        for(Category g : listUser){
            list.add(new CategorySimpleItem(g));
        }

        return list;
    }

    @Override
    @Transactional
    public CategorySimpleItem getCategorySimpleItem(int categoryId) {
        return new CategorySimpleItem(getCategory(categoryId));
    }

    @Override
    public Category getCategory(int categoryId) {
        return (Category) sessionFactory.getCurrentSession()
                .createCriteria(Category.class)
                .add(Restrictions.eq("id", categoryId))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
    }

    @Override
    @Transactional
    public int getCategoryCount() {
        Integer categories = (Integer) sessionFactory.getCurrentSession()
                .createCriteria(Category.class)
                .setProjection(Projections.rowCount()).uniqueResult();
        return categories==null ? -1 : categories;
    }

    @Override
    @Transactional
    public boolean deleteCategory(int category) {
        boolean response = false;
        try {
            Category cat = new Category();
            cat.setId(category);
            sessionFactory.getCurrentSession().delete(cat);

            response = true;
        }catch (Exception e){
            LOGGER.warning("Error in deleteCategory - " + e.getMessage());
        }
        return response;
    }

    @Override
    @Transactional
    public int createCategory(String name) {
        Category category = new Category();
        category.setName(name);
        try{
            sessionFactory.getCurrentSession().save(category);
        }catch (Exception e){
            LOGGER.warning("Error in createCategory - " + e.getMessage());
        }
        return category.getId();
    }

    @Override
    @Transactional
    public void updateCategory(int categoryId, String name) {
        Category category = new Category();
        category.setId(categoryId);
        category.setName(name);
        try{
            sessionFactory.getCurrentSession().update(category);
        }catch (Exception e){
            LOGGER.warning("Error in updateCategory - " + e.getMessage());
        }
    }
}
