import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DLBlock extends Block {

    public DLBlock(double x, double y, double width, double height) throws IOException {
        super(new ArrayList<>(List.of(new Path())), x, y, width, height);
        double x1 = 45;
        double x2 = 55;
        double y1 = 45;
        double y2 = 55;
        this.image = ImageIO.read(Billboard.class.getResourceAsStream("DLBlock.png"));
        this.goodimage = ImageIO.read(Billboard.class.getResourceAsStream("DLBlockGoodImage.png"));
        this.badimage = ImageIO.read(Billboard.class.getResourceAsStream("DLBlockBadImage.png"));
        Path pathDL = new Path(new ArrayList<>(List.of(x2, x2, 0.0)), new ArrayList<>(List.of(height, y1, y1)));
        Path pathLD = new Path(new ArrayList<>(List.of(0.0, x1, x1)), new ArrayList<>(List.of(y2, y2, height)));
        super.paths = new ArrayList<>(List.of(pathDL, pathLD));
        super.updatePaths();
    }

//    @Override
//    public void paint(Graphics g) {
//        if (isTemporary) {
//            g.setColor(Color.gray);
//        }
//        g.drawRect((int) this.x, (int) this.y, (int) width, (int) height);
//        int x0 = (int) (0.4 * width);
//        int xt = (int) (0.6 * width);
//        int y0 = (int) (0.4 * height);
//        int yt = (int) (0.6 * height);
//        g.drawLine((int) (x + x0), (int) (y + height), (int) (x + x0), (int) (y + yt));
//        g.drawLine((int) (x + x0), (int) (y + yt), (int) x, (int) (y + yt));
//        g.drawLine((int) (x + xt), (int) (y + height), (int) (x + xt), (int) (y + y0));
//        g.drawLine((int) (x + xt), (int) (y + y0), (int) x, (int) (y + y0));
//        g.setColor(Color.black);
//    }
}

