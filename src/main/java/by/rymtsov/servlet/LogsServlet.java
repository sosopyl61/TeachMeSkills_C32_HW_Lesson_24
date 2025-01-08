package by.rymtsov.servlet;

import by.rymtsov.constant.Constants;
import by.rymtsov.log.ServletLifecycleLogger;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebServlet("/logs")
public class LogsServlet extends HttpServlet implements Constants {

    private static final String LOG_PATH = Constants.LOG_FILE;

    @Override
    public void init(ServletConfig config) {
        ServletLifecycleLogger.info("LogsServlet initialized.");
    }

    @Override
    public void destroy() {
        ServletLifecycleLogger.info("LogsServlet destroyed.");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
        ServletLifecycleLogger.info("LogsServlet serviced.");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/html");

        try {
            if (!Files.exists(Paths.get(LOG_PATH))) {
                ServletLifecycleLogger.error("Log file not found.");
                return;
            }
            try (BufferedReader reader = new BufferedReader(new FileReader(LOG_PATH));
                 PrintWriter writer = resp.getWriter()) {

                writer.println("<h2>Logs file: </h2>");

                String line;
                while ((line = reader.readLine()) != null) {
                    writer.println("<li>" + line + "</li>");
                }
            } catch (IOException e) {
                ServletLifecycleLogger.error(e.getMessage());
            }
        } catch (Exception e) {
            ServletLifecycleLogger.error(e.getMessage());
        }
    }
}
