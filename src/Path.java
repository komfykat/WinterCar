import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Path {
    private ArrayList<Double> Xs;
    private ArrayList<Double> Ys;
    public int n;
    Block block;
    public Path(ArrayList<Double> xs, ArrayList<Double> ys) {
        Xs = xs;
        Ys = ys;
        n = Xs.size();
    }
    public Path(){
    }

    public ArrayList<Double> getXs() {
        return Xs;
    }

    public void setXs(ArrayList<Double> xs) {
        Xs = xs;
    }

    public ArrayList<Double> getYs() {
        return Ys;
    }

    public void setYs(ArrayList<Double> ys) {
        Ys = ys;
    }
}
