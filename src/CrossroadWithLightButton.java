import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class CrossroadWithLightButton extends Button{

    public CrossroadWithLightButton(double x, double y) throws IOException {
        super(x, y);
        this.width = this.image.getWidth();
        this.height = this.image.getHeight();
    }
    public int onmousehit(){
        return 7;
    }
}
