package pl.edu.pw.elka.pik.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.pw.elka.pik.dao.CategoryDAO;
import pl.edu.pw.elka.pik.model.CategorySimpleItem;
import pl.edu.pw.elka.pik.model.db.Category;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Komatta on 2015-05-20.
 */

@Controller
@RequestMapping("/categoryList")
public class CategoryController extends BaseController {
    private static final Logger LOGGER = Logger.getLogger(CategoryController.class.getName());
    private static final int CATEGORIES_PER_PAGE = 10;

    @Autowired
    private CategoryDAO categoryDAO;

    @Autowired
    ServletContext context;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView printCategoryList() {
        ModelAndView model = new ModelAndView("categoryList/index");
        return model;
    }

    @RequestMapping(value = "/getCategoryList")
    public void getCategoryList(@RequestParam("pageNum") Integer pageNum, HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.severe("getCategoryList");
        List<CategorySimpleItem> listCategorys = categoryDAO.getCategorySimpleItem((pageNum - 1) * CATEGORIES_PER_PAGE, pageNum * CATEGORIES_PER_PAGE);
        write(response.getOutputStream(), listCategorys);
    }

    @RequestMapping(value = "/getCategoryDetail")
    public void getCategoryDetail(@RequestParam("categoryId") Integer categoryId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.severe("getCategoryDetail");
        CategorySimpleItem category = categoryDAO.getCategorySimpleItem(categoryId);
        write(response.getOutputStream(), category);
    }

    @RequestMapping(value = "/deleteCategory", method = RequestMethod.GET)
    public void deleteCategory(@RequestParam("categoryId") Integer categoryId, HttpServletResponse response, HttpServletRequest request)
            throws ServletException, IOException {
        if (categoryDAO.deleteCategory(categoryId)) {
            response.getWriter().write("OK");
        } else {
            response.getWriter().write("ERROR");
        }
    }

    @RequestMapping(value = "/saveCategory", method = RequestMethod.POST)
    public void saveCategory(@RequestParam(value = "categoryId", required = true) int categoryId,
                             @RequestParam(value = "categoryName", required = true) String categoryTitle,
                             HttpServletResponse response) throws IOException {
        if (categoryId == -1) {
            categoryId = categoryDAO.createCategory(categoryTitle);
        } else {
            categoryDAO.updateCategory(categoryId, categoryTitle);
        }
        CategorySimpleItem category = categoryDAO.getCategorySimpleItem(categoryId);
        write(response.getOutputStream(), category);
    }

}
