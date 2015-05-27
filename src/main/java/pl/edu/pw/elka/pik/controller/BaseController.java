package pl.edu.pw.elka.pik.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.pw.elka.pik.dao.CategoryDAO;
import pl.edu.pw.elka.pik.dao.GameDAO;
import pl.edu.pw.elka.pik.model.CategorySimpleItem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by Komatta on 2015-05-22.
 */
public class BaseController {
    private ThreadLocal<ObjectMapper> om = new ThreadLocal<ObjectMapper>() {
        @Override
        protected ObjectMapper initialValue() {
            return new ObjectMapper();
        }
    };

    public void write(OutputStream os, Object obj) throws IOException {
        om.get().writeValue(os, obj);
    }

    @Autowired
    private GameDAO gameDAO;

    @Autowired
    private CategoryDAO categoryDAO;

    @RequestMapping(value = "/getCategoryList", method = RequestMethod.GET)
    public void getCategoryList(HttpServletResponse response, HttpServletRequest request)
            throws ServletException, IOException {
        List<CategorySimpleItem> category = categoryDAO.getCategoryList();
        write(response.getOutputStream(), category);
    }
}
