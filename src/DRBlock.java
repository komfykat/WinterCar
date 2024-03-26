import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DRBlock extends Block {

    public DRBlock(double x, double y, double width, double height) throws IOException {
        super(new ArrayList<>(List.of(new Path())), x, y, width, height);
        double x1 = 45;
        double x2 = 55;
        double y1 = 45;
        double y2 = 55;
        this.image = ImageIO.read(Billboard.class.getResourceAsStream("DRBlock.png"));
        this.goodimage = ImageIO.read(Billboard.class.getResourceAsStream("DRBlockGoodImage.png"));
        this.badimage = ImageIO.read(Billboard.class.getResourceAsStream("DRBlockBadImage.png"));
        Path pathDR = new Path(new ArrayList<>(List.of(x2, x2, width)), new ArrayList<>(List.of(height, y2, y2)));
        Path pathRD = new Path(new ArrayList<>(List.of(width, x1, x1)), new ArrayList<>(List.of(y1, y1, height)));
        super.paths = new ArrayList<>(List.of(pathDR, pathRD));
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
//        g.drawLine((int) x + x0, (int) y + (int) height, (int) x + x0, (int) y + y0);
//        g.drawLine((int) x + x0, (int) y + y0, (int) (x+ width), (int)(y + y0));
//        g.drawLine((int) x + xt, (int) y + (int) height, (int) x + xt, (int) y + yt);
//        g.drawLine((int) x + xt, (int) y + yt, (int) x + (int) width, (int) y + yt);
//        g.setColor(Color.black);
//    }
}

