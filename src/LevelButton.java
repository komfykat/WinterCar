import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class LevelButton extends Button{
    int levelnumber;

    public LevelButton(double x, double y, double width, double height, int levelnumber) throws IOException {
        super(x, y);
        this.width = width;
        this.height = height;
        this.levelnumber = levelnumber;
    }
    public int onmousehit(){
        return levelnumber;
    }
}
