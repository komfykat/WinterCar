import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VBlock extends Block {

    public VBlock(double x, double y, double width, double height) throws IOException {
        super(new ArrayList<>(List.of(new Path())), x, y, width, height);
        double x1 = 45;
        double x2 = 55;
        this.image = ImageIO.read(Billboard.class.getResourceAsStream("VBlock.png"));
        this.goodimage = ImageIO.read(Billboard.class.getResourceAsStream("VBlockGoodImage.png"));
        this.badimage = ImageIO.read(Billboard.class.getResourceAsStream("VBlockBadImage.png"));
        Path pathDU = new Path(new ArrayList<>(List.of(x2, x2)), new ArrayList<>(List.of(height, 0.0)));
        Path pathUD = new Path(new ArrayList<>(List.of(x1, x1)), new ArrayList<>(List.of(0.0, height)));
        super.paths = new ArrayList<>(List.of(pathDU, pathUD));
        super.updatePaths();

    }

//    @Override
//    public void paint(Graphics g) {
//        if (isTemporary) {
//            g.setColor(Color.gray);
//        }
//        g.drawRect((int) this.x, (int) this.y, (int) width, (int) height);
//        double x0 = 0.4 * width;
//        double xt = 0.6 * width;
//        g.drawLine((int) (x0 + x), (int) y, (int) (x +x0), (int) (y + height));
//        g.drawLine((int) (x + xt), (int) y, (int) (x +xt), (int) (y + height));
//        g.setColor(Color.black);
//    }
}

