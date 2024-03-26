import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Crossroad extends Block{

    public Crossroad(double x, double y, double width, double height) throws IOException {
        super(new ArrayList<>(List.of(new Path())), x, y, width, height);
        double x1 = 45;
        double x2 = 55;
        double y1 = 45;
        double y2 = 55;
        isCrossroad = true;
        this.image = ImageIO.read(Billboard.class.getResourceAsStream("Crossroad.png"));
        this.goodimage = ImageIO.read(Billboard.class.getResourceAsStream("CrossroadGoodImage.png"));
        this.badimage = ImageIO.read(Billboard.class.getResourceAsStream("CrossroadBadImage.png"));
        Path pathLR = new Path(new ArrayList<>(List.of(0.0, width)), new ArrayList<>(List.of(y2, y2)));
        Path pathRL = new Path(new ArrayList<>(List.of(width, 0.0)), new ArrayList<>(List.of(y1, y1)));
        Path pathUD = new Path(new ArrayList<>(List.of(x1, x1)), new ArrayList<>(List.of(0.0, height)));
        Path pathDU = new Path(new ArrayList<>(List.of(x2, x2)), new ArrayList<>(List.of(height, 0.0)));
        Path pathLD = new Path(new ArrayList<>(List.of(0.0, x1, x1)), new ArrayList<>(List.of(y2, y2, height)));
        Path pathLU = new Path(new ArrayList<>(List.of(0.0, x2, x2)), new ArrayList<>(List.of(y2, y2, 0.0)));
        Path pathRU = new Path(new ArrayList<>(List.of(width, x2, x2)), new ArrayList<>(List.of(y1, y1, 0.0)));
        Path pathRD = new Path(new ArrayList<>(List.of(width, x1, x1)), new ArrayList<>(List.of(y1, y1, height)));
        Path pathUL = new Path(new ArrayList<>(List.of(x1, x1, 0.0)), new ArrayList<>(List.of(0.0, y1, y1)));
        Path pathUR = new Path(new ArrayList<>(List.of(x1, x1, width)), new ArrayList<>(List.of(0.0, y2, y2)));
        Path pathDL = new Path(new ArrayList<>(List.of(x2, x2, 0.0)), new ArrayList<>(List.of(height, y1, y1)));
        Path pathDR = new Path(new ArrayList<>(List.of(x2, x2, width)), new ArrayList<>(List.of(height, y2, y2)));
        super.paths = new ArrayList<>(List.of(pathLR, pathRL, pathUD, pathDU, pathLD, pathLU, pathRU, pathRD, pathUL, pathUR, pathDL, pathDR));
        super.updatePaths();

    }

//    @Override
//    public void paint(Graphics g) {
//        if (isTemporary) {
//            g.setColor(Color.gray);
//        }
//        g.drawRect((int) this.x, (int) this.y, (int) width, (int)height);
//        int x1 = (int) (0.4 * width) + (int) this.x;
//        int x2 = (int) (0.6 * width) + (int) this.x;
//        int y1 = (int) (0.4 * height) + (int) this.y;
//        int y2 = (int) (0.6 * height) + (int) this.y;
//        int w = (int) width + (int) this.x;
//        int h = (int) height + (int) this.y;
//        g.drawLine((int) this.x, y1, x1, y1);
//        g.drawLine(x1, y1, x1, (int) this.y);
//        g.drawLine(x2, (int) this.y, x2, y1);
//        g.drawLine(x2, y1, w, y1);
//        g.drawLine(w, y2, x2, y2);
//        g.drawLine(x2, y2, x2, h);
//        g.drawLine((int) this.x, y2, x1, y2);
//        g.drawLine(x1, y2, x1, h);
//        g.setColor(Color.black);
//        g.drawImage(image, (int)x, (int)y, null);
//    }
}
