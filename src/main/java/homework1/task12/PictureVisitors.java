package homework1.task12;

import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.concurrent.atomic.*;

@WebServlet("/new_visitors")
public class PictureVisitors extends HttpServlet {

    private AtomicInteger visitors = new AtomicInteger(0);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int visitorCount = visitors.incrementAndGet();
        try (FileWriter writer = new FileWriter("D:/work/jd2_homework/src/main/java/homework1/task12/newvisitors.txt", true)) {
            writer.write("Visitors: " + visitorCount + "\n");
        }
        BufferedImage image = new BufferedImage(500, 200, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 500, 200);
        graphics.setFont(new Font("Monospaced", Font.BOLD, 48));
        graphics.setColor(Color.BLACK);
        graphics.drawString("Visitors: " + visitorCount, 100, 100);
        resp.setContentType("image/png");
        ImageIO.write(image, "png", resp.getOutputStream());
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }
}
