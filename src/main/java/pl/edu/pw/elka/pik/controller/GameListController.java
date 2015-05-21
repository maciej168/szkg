package pl.edu.pw.elka.pik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.pw.elka.pik.dao.GameDAO;
import pl.edu.pw.elka.pik.model.Game;
import pl.edu.pw.elka.pik.model.GameSimpleItem;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Komatta on 2015-05-20.
 */

@Controller
@RequestMapping("/gameList")
public class GameListController
{
    private static final Logger LOGGER = Logger.getLogger(GameListController.class.getName());

    private static final int GAMES_PER_PAGE = 10;

    @Autowired
    private GameDAO gameDAO;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView printGameList(HttpServletRequest request, HttpServletResponse response)
    {
        //LOGGER.log(Level.SEVERE, "requestPage: " + request.getParameter("requestPage"));
        String requestPage = request.getParameter("requestPage");
        List<GameSimpleItem> listGames = null;
        int currentPage = 1;
        if (requestPage != null)
        {

            try
            {
                int reqP = Integer.parseInt(requestPage);
                listGames = gameDAO.getGameSimpleItem((reqP - 1) * GAMES_PER_PAGE, reqP * GAMES_PER_PAGE);
                currentPage = reqP;
            } catch (NumberFormatException e)
            {
                LOGGER.log(Level.SEVERE, "Invalid parameter requestPage: " + requestPage);
                listGames = gameDAO.getGameSimpleItem();
            }

        }
        else
        {
            listGames = gameDAO.getGameSimpleItem();
        }

        ModelAndView model = new ModelAndView("gameList/index");
        model.addObject("gameList", listGames);
        model.addObject("currentPage", currentPage);
        model.addObject("maxPage", (gameDAO.getGameSimpleItemCount() / GAMES_PER_PAGE) + 1);
        return model;
    }

    @RequestMapping(value = "/imageDisplay", method = RequestMethod.GET)
    public void showImage(@RequestParam("id") Integer gameItemId, HttpServletResponse response,HttpServletRequest request)
            throws ServletException, IOException
    {
        byte[] image = gameDAO.getGameImage(gameItemId);
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.getOutputStream().write(image);
        response.getOutputStream().close();
    }
}
