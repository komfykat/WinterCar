import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class SimulationButton extends Button {
    public SimulationButton(double x, double y) throws IOException {
        super(x, y);
        this.image = ImageIO.read(Billboard.class.getResourceAsStream("SimulationButton.png"));
        this.width = this.image.getWidth();
        this.height = this.image.getHeight();
    }
    public boolean onmousehit(boolean Simulation){
        return (!Simulation);
    }
}
