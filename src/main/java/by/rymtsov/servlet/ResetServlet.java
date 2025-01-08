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

/**
 * This servlet resets the counter
 * in CounterServlet.
 */

@WebServlet("/reset")
public class ResetServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletLifecycleLogger.info("ResetServlet initialized.");
    }

    @Override
    public void destroy() {
        ServletLifecycleLogger.info("ResetServlet destroyed.");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
        ServletLifecycleLogger.info("ResetServlet serviced.");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        session.setAttribute("visitCounter", 0);
        try {
            resp.sendRedirect("/count");
        } catch (IOException e) {
            ServletLifecycleLogger.error(e.getMessage());
        }
    }
}
