import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Button {
    public double x;
    public double y;
    public double width;
    public double height;
    public BufferedImage image;

    public Button(double x, double y) throws IOException {
        this.image = ImageIO.read(Billboard.class.getResourceAsStream("Crossroad.png"));
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.x = x;
        this.y = y;

    }

    public void paint(Graphics g) {
        g.drawImage(image, (int) x, (int) y, null);
    }

    public boolean mouseHit(int mouseX, int mouseY) {
        Rectangle2D B = new Rectangle2D.Double(x, y, width, height);
        if (B.contains(mouseX, mouseY)) {
            return true;
        } else {
            return false;
        }
    }

    public int onmousehit() {
        return -1;
    }
}
