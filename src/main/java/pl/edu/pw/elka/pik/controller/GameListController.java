package pl.edu.pw.elka.pik.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
import pl.edu.pw.elka.pik.dao.GameDAO;
import pl.edu.pw.elka.pik.model.GameDetailItem;
import pl.edu.pw.elka.pik.model.GameSimpleItem;

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
@RequestMapping("/gameList")
public class GameListController extends BaseController {
    private static final Logger LOGGER = Logger.getLogger(GameListController.class.getName());
    private static final int GAMES_PER_PAGE = 10;

    @Autowired
    private GameDAO gameDAO;

    @Autowired
    ServletContext context;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView printGameList() {
        ModelAndView model = new ModelAndView("gameList/index");
        return model;
    }

    @RequestMapping(value = "/getGameList")
    public void getGameList(@RequestParam("pageNum") Integer pageNum, HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.severe("getGameList");
        List<GameSimpleItem> listGames = gameDAO.getGameSimpleItem((pageNum - 1) * GAMES_PER_PAGE, pageNum * GAMES_PER_PAGE);
        write(response.getOutputStream(), listGames);
    }

    @RequestMapping(value = "/getGameImge", method = RequestMethod.GET)
    public void showImage(@RequestParam("gameId") Integer gameItemId, HttpServletResponse response, HttpServletRequest request)
            throws ServletException, IOException {
        byte[] image = gameDAO.getGameImage(gameItemId);
        if (image != null) {
            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
            response.getOutputStream().write(image);
            response.getOutputStream().close();
        } else {
            throw new NoSuchRequestHandlingMethodException(request);
        }
    }

    @RequestMapping(value = "/deleteGame", method = RequestMethod.GET)
    public void deleteGame(@RequestParam("gameId") Integer gameId, HttpServletResponse response, HttpServletRequest request)
            throws ServletException, IOException {
        if (gameDAO.deleteGame(gameId)) {
            response.getWriter().write("OK");
        } else {
            response.getWriter().write("ERROR");
        }
    }

    @RequestMapping(value = "/getGameDetail", method = RequestMethod.GET)
    public void getGameDetail(@RequestParam("gameId") Integer gameId, HttpServletResponse response, HttpServletRequest request)
            throws ServletException, IOException {
        GameDetailItem detail = gameDAO.getGameDetail(gameId);
        write(response.getOutputStream(), detail);
    }

    @RequestMapping(value = "/saveGame", method = RequestMethod.POST)
    public void uploadImage(@RequestParam(value = "gameId", required = true) int gameId,
                            @RequestParam(value = "gameTitle", required = true) String gameTitle,
                            @RequestParam(value = "gameCategory", required = false) List<Integer> gameCategory,
                            @RequestParam(value = "gameDescription", required = false) String gameDescription,
                            @RequestParam(value = "file", required = false) MultipartFile file,
                            HttpServletResponse response) throws IOException {
        byte[] img = file == null?null:file.getBytes();
        if (gameId == -1) {
            gameId = gameDAO.createGame(gameTitle, gameCategory, gameDescription, img);
        } else {
            gameDAO.updateGame(gameId, gameTitle, gameCategory, gameDescription, img);
        }
        GameDetailItem detail = gameDAO.getGameDetail(gameId);
        write(response.getOutputStream(), detail);
    }

}
