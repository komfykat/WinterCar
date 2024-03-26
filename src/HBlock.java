import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HBlock extends Block{

    public HBlock(double x, double y, double width, double height) throws IOException {
        super(new ArrayList<>(List.of(new Path())), x, y, width, height);
        double y1 = 45;
        double y2 = 55;
        this.image = ImageIO.read(Billboard.class.getResourceAsStream("HBlock.png"));
        this.goodimage = ImageIO.read(Billboard.class.getResourceAsStream("HBlockGoodImage.png"));
        this.badimage = ImageIO.read(Billboard.class.getResourceAsStream("HBlockBadImage.png"));
        Path pathLR = new Path(new ArrayList<>(List.of(0.0, width)), new ArrayList<>(List.of(y2, y2)));
        Path pathRL = new Path(new ArrayList<>(List.of(width, 0.0)), new ArrayList<>(List.of(y1, y1)));
        super.paths = new ArrayList<>(List.of(pathLR, pathRL));
        super.updatePaths();

    }

//    @Override
//    public void paint(Graphics g) {
//        if (isTemporary) {
//            g.setColor(Color.gray);
//        }
//        g.drawRect((int) this.x, (int) this.y, (int) width, (int)height);
//        double y0 = 40 + y;
//        double yt = 60 + y;
//        g.drawLine((int) x, (int)(y0), (int)(x + width), (int)(y0));
//        g.drawLine((int) x, (int)(yt), (int)(x + width), (int)(yt));
//        g.setColor(Color.black);
//    }
}
