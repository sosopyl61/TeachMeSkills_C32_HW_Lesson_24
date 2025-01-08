package by.rymtsov.servlet;

import by.rymtsov.log.ServletLifecycleLogger;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * This servlet show parameters from web.xml
 */

@WebServlet("/settings")
public class SettingsServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletLifecycleLogger.info("SettingsServlet initialized.");
    }

    @Override
    public void destroy() {
        ServletLifecycleLogger.info("SettingsServlet destroyed.");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
        ServletLifecycleLogger.info("SettingsServlet serviced.");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String appName = getServletContext().getInitParameter("appName");
        String appVersion = getServletContext().getInitParameter("appVersion");
        String developerName = getServletContext().getInitParameter("developerName");
        String supportEmail = getServletContext().getInitParameter("supportEmail");
        resp.setContentType("text/html");
        try {
            PrintWriter writer = resp.getWriter();
            writer.println("<h2>App name: " + appName + "</h2>");
            writer.println("<h2>App version: " + appVersion + "</h2>");
            writer.println("<h2>Developer name: " + developerName + "</h2>");
            writer.println("<h2>Support email: " + supportEmail + "</h2>");
        } catch (IOException e) {
            ServletLifecycleLogger.error(e.getMessage());
        }
    }
}
