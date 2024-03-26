import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class DLBlockButton extends Button{

    public DLBlockButton(double x, double y) throws IOException {
        super(x, y);
        this.image = ImageIO.read(Billboard.class.getResourceAsStream("DLBlock.png"));
        this.width = this.image.getWidth();
        this.height = this.image.getHeight();
    }

    public int onmousehit(){
        return 3;
    }
}
