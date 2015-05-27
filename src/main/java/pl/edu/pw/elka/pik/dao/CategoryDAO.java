package pl.edu.pw.elka.pik.dao;

import pl.edu.pw.elka.pik.model.CategorySimpleItem;
import pl.edu.pw.elka.pik.model.db.Category;

import java.util.List;

/**
 * Created by mastah on 2015-05-27.
 */
public interface CategoryDAO {
    List<CategorySimpleItem> getCategoryList();

    List<CategorySimpleItem> getCategorySimpleItem(int from, int to);

    CategorySimpleItem getCategorySimpleItem(int categoryId);

    Category getCategory(int categoryId);

    int getCategoryCount();

    boolean deleteCategory(int category);

    int createCategory(String name);

    void updateCategory(int category, String name);

}
