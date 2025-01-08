package by.rymtsov.servlet;

import by.rymtsov.log.ServletLifecycleLogger;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * This is servlet that counts
 * how many times user visited the page.
 */

@WebServlet("/count")
public class CountServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletLifecycleLogger.info("CountServlet initialized.");
    }

    @Override
    public void destroy() {
        ServletLifecycleLogger.info("CountServlet destroyed.");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
        ServletLifecycleLogger.info("CountServlet serviced.");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        Integer visitCounter = (Integer) session.getAttribute("visitCounter");
        if (visitCounter == null) {
            visitCounter = 1;
        } else {
            visitCounter++;
        }
        session.setAttribute("visitCounter", visitCounter);
        resp.setContentType("text/html");
        try {
            PrintWriter writer = resp.getWriter();
            writer.println("<h2>Page was visited " + visitCounter + " times.</h2>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
