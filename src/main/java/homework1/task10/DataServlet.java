package homework1.task10;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import java.io.*;

@WebServlet("/data")
public class DataServlet extends HttpServlet {

    private boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private void sendResponse(HttpServletResponse resp, String message) throws IOException {
        resp.setContentType("text/html");
            try (PrintWriter out = resp.getWriter()) {
                out.println("<html>");
                out.println("<h2>" + message + "</h2>");
                out.println("<a href='/project/form.html'>BACK</a>");
                out.println("</html>");
    }
        }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String fname = req.getParameter("name");
            String fphone = req.getParameter("phone");
            String femail = req.getParameter("email");
            if (isNullOrEmpty(fname) || (isNullOrEmpty(fphone) && isNullOrEmpty(femail))) {
              resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
              sendResponse(resp, "Missing parameter");
            } else {
                sendResponse(resp, "OK");
            }
        } catch (Exception e) {
            throw new ServletException("Error processing request", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}





