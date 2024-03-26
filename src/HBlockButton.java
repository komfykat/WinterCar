import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class HBlockButton extends Button{

    public HBlockButton(double x, double y) throws IOException {
        super(x, y);
        this.width = this.image.getWidth();
        this.height = this.image.getHeight();
    }
    public int onmousehit(){
        return 2;
    }
}
