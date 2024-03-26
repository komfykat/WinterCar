import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DisposeButton extends Button{

    public DisposeButton(double x, double y) throws IOException {
        super(x, y);
        this.image = ImageIO.read(Billboard.class.getResourceAsStream("DisposeButton.png"));
        this.width = this.image.getWidth();
        this.height = this.image.getHeight();
    }

    public void onmousehit(ArrayList<Car> cars, ArrayList<Spawner> spawners){
        cars.clear();
        for (Spawner spawner : spawners){
            spawner.car = null;
            spawner.time = 0;
        }
    }
}
