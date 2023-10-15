package homework1.task11;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import java.io.*;

@WebServlet("/hello_user")
public class HelloServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String info = req.getHeader("User-Agent");
        String browser = getBrowser(info);
        out.println("<html>");
        out.println("<h2>" + "Hello user: " + browser + "</h2>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private String getBrowser(String info) {
        if (info.contains("MSIE") || info.contains("Trident")) {
            return "Internet Explorer";
        } else if (info.contains("Edg")) {
            return "Edge";
        } else if (info.contains("Firefox")) {
            return "Firefox";
        } else if (info.contains("Chrome")) {
            return "Chrome";
        } else if (info.contains("Opera")) {
            return "Opera";
        } else if (info.contains("Safari")) {
            return "Safari";
        } else {
            return "unknown browser";
        }
    }


}
