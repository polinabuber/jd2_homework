package homework1.task9;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import java.io.*;

@WebServlet("/visitors")
public class Visitors extends HttpServlet {
    private int visitors = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        visitors++;
        try (FileWriter writer = new FileWriter("D:/work/jd2_homework/src/main/java/homework1/task9/visitors.txt", true)) {
            writer.write("Visitors: " + visitors+ "\n");
            PrintWriter out = resp.getWriter();
            out.println("<body><h1>Visitors: " + visitors + "</h1>");
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

