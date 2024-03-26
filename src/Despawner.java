import java.awt.*;
import java.util.ArrayList;

public class Despawner {
    public double x;
    public double y;
    public ArrayList<Car> cars;
    public int number;
    public Color color;
    public int carsDeleted = 0;

    public Despawner(double x, double y, ArrayList<Car> cars, int number) {
        this.x = x;
        this.y = y;
        this.cars = cars;
        this.number = number;
        if (number == 0){
            color = Color.red;
        }
        else if (number == 1){
            color = Color.blue;
        }
        else if (number == 2){
            color = Color.magenta;
        }
        else if (number == 3){
            color = Color.ORANGE;
        }
        else if (number == 4){
            color = Color.CYAN;
        }
        else if (number == 5){
            color = Color.PINK;
        }
        else if (number == 6){
            color = Color.YELLOW;
        }
        else if (number == 7){
            color = Color.GREEN;
        }
    }

    public ArrayList<Car> update(){
        int carsbefore = cars.size();
        cars.removeIf(car -> Math.pow(x - car.getX(), 2) + Math.pow(y - car.getY(), 2) <= Constants.epsilon * Constants.epsilon && car.number == this.number);
        int carsnow = cars.size();
        carsDeleted += carsnow - carsbefore;
        return cars;
    }
    public void paint(Graphics g){
        g.setColor(color);
        g.fillRect((int) x - 5, (int) y - 5, 10, 10);
        g.setColor(Color.black);
    }
}
