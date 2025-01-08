package by.rymtsov.servlet;

import by.rymtsov.constant.Constants;
import by.rymtsov.log.ServletLifecycleLogger;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This servlet shows info about the developer.
 */

@WebServlet("/about")
public class AboutMeServlet extends HttpServlet implements Constants {

    private static final String ABOUT_ME_PATH = Constants.ABOUT_ME_PATH;

    @Override
    public void init(ServletConfig config) {
        ServletLifecycleLogger.info("AboutMeServlet initialized.");
    }

    @Override
    public void destroy() {
        ServletLifecycleLogger.info("AboutMeServlet destroyed.");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
        ServletLifecycleLogger.info("AboutMeServlet serviced.");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/html");
        try {
            String message = new String(Files.readAllBytes(Paths.get(ABOUT_ME_PATH)));
            resp.getWriter().write(message);
        } catch (IOException e) {
            ServletLifecycleLogger.error(e.getMessage());
        }
    }
}
