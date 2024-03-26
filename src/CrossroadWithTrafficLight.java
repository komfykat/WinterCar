import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CrossroadWithTrafficLight extends Block {

    public boolean trafficLightPosition = false;
    ArrayList<Integer> leftPaths = new ArrayList<>(List.of(0, 4, 5));
    ArrayList<Integer> rightPaths = new ArrayList<>(List.of(1, 6, 7));
    ArrayList<Integer> upPaths = new ArrayList<>(List.of(2, 8, 9));
    ArrayList<Integer> downPaths = new ArrayList<>(List.of(3, 10, 11));
    BufferedImage image1 = ImageIO.read(Billboard.class.getResourceAsStream("CrossroadWithLight1.png"));
    BufferedImage image2 = ImageIO.read(Billboard.class.getResourceAsStream("cs.png"));

    public CrossroadWithTrafficLight(double x, double y, double width, double height) throws IOException {
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

    public void update() {
        timeFromChange += Constants.tickOfSimulation * 1000;
        if (timeFromChange > 8000) {
            trafficLightPosition = !trafficLightPosition;
//            System.out.println("Changed");
            timeFromChange = 0;
        }
    }

    @Override
    public boolean carStop(Car car) {
        if (car.onCrossroad()) {
            car.setFromTrafficLight(true);
            return false;
        } else {
            Rectangle2D carPoint = new Rectangle2D.Double(car.BlockX - 5, car.BlockY - 5, 10, 10);
            Rectangle2D leftSector = new Rectangle2D.Double(35, 40, 5, 20);
            Rectangle2D rightSector = new Rectangle2D.Double(60, 40, 5, 20);
            Rectangle2D upSector = new Rectangle2D.Double(40, 35, 20, 5);
            Rectangle2D downSector = new Rectangle2D.Double(40, 60, 20, 5);
            if (trafficLightPosition == true) {
                if (leftPaths.contains(paths.indexOf(car.path))) {
                    return (leftSector.intersects(carPoint));
                } else if (rightPaths.contains(paths.indexOf(car.path))) {
                    return (rightSector.intersects(carPoint));
                } else {
                    return false;
                }

            } else {
                if (upPaths.contains(paths.indexOf(car.path))) {
                    return (upSector.intersects(carPoint));
                } else if (downPaths.contains(paths.indexOf(car.path))) {
                    return (downSector.intersects(carPoint));
                }
                else{
                    return false;
                }
            }
        }
    }
    @Override
    public void paint(Graphics g){
        g.setColor(Color.black);
        g.drawRect((int)x, (int)y, 100, 100);
        if (!trafficLightPosition){
            g.drawImage(image1, (int) x, (int) y, null);
        }
        else{
            g.drawImage(image2, (int) x, (int) y, null);
        }
    }

}