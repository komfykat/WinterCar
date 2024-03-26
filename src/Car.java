import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Car {
    private double x;
    private double y;
    public double BlockX;
    public double BlockY;
    public int number;
    private double v;
    public double vx;
    public double vy;
    private double angle;
    private double width;
    private double height;
    public Path path;
    public Block block;
    public int PathPosition = 0;
    public boolean inMotion = true;
    public boolean inPath = true;
    public Color color;
    public boolean fromTrafficLight = false;
    public ArrayList<Block> blocks;
    public ArrayList<Car> cars;
    public Spawner spawner;
    public ArrayList<Integer> GlobalPath;
    public BlockGraph bg;
    public int GlobalPathPosition = 0;
    BufferedImage image = ImageIO.read(Billboard.class.getResourceAsStream("Car.png"));

    public Car(double blockX, double blockY, double v, double width, double height, Path path, Block block, ArrayList<Block> blocks) throws IOException {
        BlockX = blockX;
        BlockY = blockY;
        this.v = v;
        this.width = width;
        this.height = height;
        this.path = path;
        this.block = block;
        this.x = BlockX + this.block.getX();
        this.y = BlockY + this.block.getY();
        this.blocks = blocks;;
    }

    public Car(double v, double width, double height, Spawner spawner) throws IOException {

        this.v = v;
        this.width = width;
        this.height = height;
        this.x = spawner.x - width / 2;
        this.y = spawner.y - height / 2;
        this.spawner = spawner;
        this.bg = spawner.bg;
        this.blocks = bg.blocks;
        GlobalPath = spawner.GlobalPath;
    }

    public Car(double v, double width, double height) throws IOException {
        this.v = v;
        this.width = width;
        this.height = height;
    }

    public double getX() {
        if (block != null) {
            x = BlockX + block.x;
        }
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        if (block != null) {
            y = BlockY + block.y;
        }
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setFromTrafficLight(boolean fromTrafficLight) {
        this.fromTrafficLight = fromTrafficLight;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getV() {
        return v;
    }

    public void setV(double v) {
        this.v = v;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void update() {
        boolean flag = true;
        if (block.carStop(this)){
            flag = false;
        }
        for (Car car : cars) {
            if (!car.equals(this)) {
                if (check(car)) {
                    flag = false;
                }
            }

        }
        System.out.println(this.fromTrafficLight);
        if (flag) {
            this.move();
        } else {
            this.stop();
        }
        if (inPath) {
            if (inMotion) {
                if (PathPosition < path.n) {
                    double nextX = path.getXs().get(PathPosition);
                    double nextY = path.getYs().get(PathPosition);
                    if (abs(BlockX - nextX) <= Constants.epsilon && abs(BlockY - nextY) <= Constants.epsilon && PathPosition < path.n) {
                        PathPosition++;
                    }
                    if (abs(BlockX - nextX) <= Constants.epsilon && abs(BlockY - nextY) <= Constants.epsilon && PathPosition == path.n) {
                        inPath = false;
                    } else {
                        nextX = path.getXs().get(PathPosition);
                        nextY = path.getYs().get(PathPosition);
                    }
                    double vx = v * (nextX - BlockX) / Math.sqrt(Math.pow(nextX - BlockX, 2) + Math.pow(nextY - BlockY, 2));
                    double vy = v * (nextY - BlockY) / Math.sqrt(Math.pow(nextX - BlockX, 2) + Math.pow(nextY - BlockY, 2));
                    this.vx = vx;
                    this.vy = vy;
//                System.out.println(vx + " " + vy);

                    BlockX += vx * Constants.tickOfSimulation;
                    BlockY += vy * Constants.tickOfSimulation;
                } else {
                    inPath = false;
                }
//                System.out.println(nextX + " " + nextY);

//                System.out.println(BlockX + " " + BlockY + "\n");
            }
        } else {
            boolean flag2 = findNextBlock();
            this.fromTrafficLight = false;
            if (!flag2) {
                this.stop();
            }
        }


    }


    public double distance(Car car) {
        return Math.sqrt(Math.pow(x - car.x, 2) + Math.pow(y - car.y, 2));
    }


    public boolean atRight(Car car) {
        Vector3D a = new Vector3D(Constants.check * vx, Constants.check * vy, 0);
        Vector3D b = new Vector3D(Constants.check * car.vx, Constants.check * car.vy, 0);
        return (a.right(b));
    }


    public void stop() {
        inMotion = false;
    }

    public void move() {
        inMotion = true;
    }

    public boolean findNextBlock() {
        for (Block block : blocks) {
            for (Path path : block.paths) {
                ArrayList<Double> startPoint1 = new ArrayList<>(List.of(path.getXs().get(0) + block.x, path.getYs().get(0) + block.y));
                int startPoint = bg.points.indexOf(startPoint1);
                ArrayList<Double> finishPoint1 = new ArrayList<>(List.of(path.getXs().get(path.n - 1) + block.x, path.getYs().get(path.n - 1) + block.y));
                int finishPoint = bg.points.indexOf(finishPoint1);
                if (startPoint == GlobalPath.get(GlobalPathPosition) && finishPoint == GlobalPath.get(GlobalPathPosition + 1)) {
                    this.block = block;
                    this.path = path;
                    PathPosition = 0;
                    GlobalPathPosition += 1;
                    inPath = true;
                    BlockX = path.getXs().get(0);
                    BlockY = path.getYs().get(0);
                    return true;
                }
            }
        }
        return false;
    }


    public boolean check(Car car) {
        Line2D a = new Line2D.Double(x, y, x + vx * Constants.check, y + vy * Constants.check);
        Rectangle2D b = new Rectangle2D.Double(car.x - 5, car.y - 5, 10, 10);
        Rectangle2D b2 = new Rectangle2D.Double(car.x - 8, car.y - 8, 16, 16);
        Line2D b1 = new Line2D.Double(car.x, car.y, car.x + car.vx * Constants.check, car.y + car.vy * Constants.check);
        if (fromTrafficLight){
            return (a.intersects(b));
//            return false;
        }
        else if (onCrossroad()) {
            if (car.onCrossroad()) {
                if (abs(vx / car.vx - vy / car.vy) <= 0.1 || !car.inMotion) {
                    return (a.intersects(b));
                } else {
                    return (a.intersectsLine(b1) && this.atRight(car));
                }
            } else {
                return false;
            }
        } else {
            if (car.onCrossroad()) {
                if (abs(vx / car.vx - vy / car.vy) <= 0.1 || !car.inMotion) {
                    return (a.intersects(b) || a.intersects(b2));
                } else {
                    return a.intersectsLine(b1) || a.intersects(b2);
                }

//                return (a.intersectsLine(b1) || a.intersects(b));
            } else {
                if (abs(vx / car.vx - vy / car.vy) <= 0.1 || !car.inMotion) {
                    return (a.intersects(b));
                } else {
                    return (a.intersectsLine(b1) && this.atRight(car));
                }
            }
        }

    }

    public boolean onCrossroad() {
        if (this.block.isCrossroad) {
            Rectangle2D a = new Rectangle2D.Double(40, 40, 20, 20);
            Point2D b = new Point2D.Double(BlockX, BlockY);
            Rectangle2D b1 = new Rectangle2D.Double(BlockX - 4, BlockY - 4, 8, 8);
            return (a.contains(b) || b1.intersects(a));
        } else {
            return false;
        }
    }

    public boolean intersect(Car car) {
//        if (!this.inMotion) {
        Line2D a = new Line2D.Double(x, y, x + vx * Constants.check, y + vy * Constants.check);
        if (!car.inMotion || (car.vx == 0 && car.vy == 0)) {
            Rectangle2D b = new Rectangle2D.Double(car.x - 5, car.y - 5, 10, 10);
            return a.intersects(b);
        } else {
            Line2D b = new Line2D.Double(car.x, car.y, car.x + car.vx * Constants.check, car.y + car.vy * Constants.check);
            return a.intersectsLine(b);
        }
    }
//    }


    public Car copy() throws IOException {
        return (new Car(v, width, height));
    }

    public void paint(Graphics g) {
//        g.setColor(color);
//       g.fillRect((int) (getX() - width / 2), (int) (getY() - height / 2), (int) width, (int) height);
        double turnAngle = Math.atan2(vy, vx);
        this.angle = turnAngle;
        AffineTransform tx = AffineTransform.getRotateInstance(turnAngle, image.getWidth() / 2, image.getHeight() / 2);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        g.drawImage(op.filter(image, null), (int) (getX() - image.getWidth() / 2), (int) (getY() - image.getHeight() / 2), null);
    }


}
