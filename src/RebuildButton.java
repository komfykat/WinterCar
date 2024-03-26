import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class RebuildButton extends Button {

    public RebuildButton(double x, double y) throws IOException {
        super(x, y);
        this.image = ImageIO.read(Billboard.class.getResourceAsStream("RebuildButton.png"));
        this.width = this.image.getWidth();
        this.height = this.image.getHeight();
    }
}
