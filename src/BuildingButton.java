import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class BuildingButton extends Button {

    public BuildingButton(double x, double y) throws IOException {
        super(x, y);
        this.image = ImageIO.read(Billboard.class.getResourceAsStream("BuildingButton.png"));
        this.width = this.image.getWidth();
        this.height = this.image.getHeight();
    }
    public boolean onmousehit(boolean Building){
        return (!Building);
    }
}
