package homework1.task9;

import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import java.io.*;
import java.util.concurrent.atomic.*;

@WebServlet("/visitors")
public class Visitors extends HttpServlet {
    private AtomicInteger visitors = new AtomicInteger(0);
    //is this a correct method for ensuring thread safety?
    //or better use "private int visitors = 0;"

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int visitorCount = visitors.incrementAndGet();
        String currentDir = System.getProperty("user.dir");
        String filePath = currentDir +"/visitors.txt";
        try (FileWriter writer = new FileWriter(filePath, true);
             PrintWriter out = resp.getWriter()) {
            writer.write("Visitors: " + visitorCount + "\n");
            out.println("<body><h1>Visitors: " + visitorCount + "</h1>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }
}

