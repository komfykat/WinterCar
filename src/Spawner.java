import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Spawner {
    public double x;
    public double y;
    public Car blankcar;
    public Car car;
    public double time;
    public double spawnTime;
    public int number;
    public ArrayList<Integer> GlobalPath;
    public BlockGraph bg;
    public ArrayList<Despawner> despawners;
    public ArrayList<Despawner> nicedespawners;
    public int bgnumber;
    public Color color;

    public Spawner(double x, double y, Car blankcar, double spawnTime, int number, ArrayList<Despawner> despawners, BlockGraph bg) {
        this.x = x;
        this.y = y;
        this.blankcar = blankcar;
        this.spawnTime = spawnTime;
        this.number = number;
        this.despawners = despawners;
        this.bg = bg;
        bgnumber = bg.points.indexOf(new ArrayList<>(List.of(x, y)));
        makenicedespawners();
        if (bg.blocks.size() > 0) {
            this.GlobalPath = GetGlobalPath();
        }
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

    public void setBg(BlockGraph bg) {
        this.bg = bg;
        this.bgnumber = bg.points.indexOf(new ArrayList<>(List.of(x, y)));
    }

    public void updateCars(ArrayList<Car> cars, ArrayList<Block> blocks) throws IOException {
        if (time >= spawnTime) {
            if (car != null) {
                if (Math.sqrt(Math.pow(Math.abs(car.getX() - x), 2) + Math.pow(Math.abs(car.getY() - y), 2)) <= car.getV() * 1000 / 1000) {
                    time += Constants.tickOfSimulation * 1000;
                } else {
                    car = null;
                }
            } else {
                Car car1 = new Car(blankcar.getV(), blankcar.getWidth(), blankcar.getHeight(), this);
                if (this.GlobalPath == null){
//                    System.out.println("No road");
                }
                else if (this.GlobalPath.size() > 0) {
                    car1.findNextBlock();
                    cars.add(car1);
                    car1.cars = cars;
                    car = car1;
                    car.number = this.number;
                    time = 0;
//                    System.out.println("Spawned");
                }
                else{
//                    System.out.println("No road");
                }
            }
        } else {
            time += Constants.tickOfSimulation * 1000;
        }
    }

    public void makenicedespawners() {
        nicedespawners = new ArrayList<Despawner>();
        for (Despawner despawner : despawners) {
            if (despawner.number == this.number) {
                nicedespawners.add(despawner);
            }
        }
    }

    public ArrayList<Integer> GetGlobalPath() {
        if (nicedespawners.size() > 0 && bg.blocks.size() > 0) {
            int despawnermin = -1;
            double dmin = 1000000000;
            for (Despawner despawner : nicedespawners) {
                int bgdespawnernumber = bg.points.indexOf(new ArrayList<>(List.of(despawner.x, despawner.y)));
                if (bgnumber >= 0 && bgdespawnernumber >= 0) {
                    double d = bg.shortestPathDistance(bgnumber, bgdespawnernumber);
                    if (d < dmin) {
                        despawnermin = bgdespawnernumber;
                        dmin = d;
                    }
                }
            }
            if (despawnermin == -1) {
//                System.out.println("No Global Path! Rebuild your road!");
                return new ArrayList<Integer>();
            }
            return (bg.shortestPath(bgnumber, despawnermin));
        } else {
//            System.out.println("No Finish! Rebuild your road!");
            return new ArrayList<Integer>();
        }
    }
    public void paint(Graphics g){
        g.setColor(color);
        g.fillRect((int) x - 5, (int) y - 5, 10, 10);
        g.setColor(Color.black);
        g.fillRect((int) x - 3, (int) y - 3, 6, 6);
        g.setColor(Color.black);
    }
}
