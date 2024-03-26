import java.util.ArrayList;
import java.util.List;

public class BlockGraph {
    ArrayList<ArrayList<Double>> matrix;
    ArrayList<ArrayList<Double>> points;
    ArrayList<Block> blocks;

    public BlockGraph(ArrayList<Block> blocks) {
        this.blocks = blocks;
        points = new ArrayList<>();
        for (Block block : blocks) {
            for (Path path : block.paths) {
                ArrayList<Double> start_point = new ArrayList<>(List.of(path.getXs().get(0) + block.x, path.getYs().get(0) + block.y));
                ArrayList<Double> finish_point = new ArrayList<>(List.of(path.getXs().get(path.n - 1) + block.x, path.getYs().get(path.n - 1) + block.y));
                if (!points.contains(start_point)) {
                    points.add(start_point);
                }
                if (!points.contains(finish_point)) {
                    points.add(finish_point);
                }
            }
        }
        this.matrix = buildMatrix();
    }

    public ArrayList<ArrayList<Double>> buildMatrix() {
        int n = points.size();
        ArrayList<ArrayList<Double>> m = new ArrayList< >();
        for (int i = 0; i < n; ++i) {
            ArrayList<Double> a = new ArrayList<>();
            for (int j = 0; j < n; ++j) {
                a.add(0.0);
            }
            m.add(a);
        }
        for (Block block : blocks) {
            for (Path path : block.paths) {
                double x1 = path.getXs().get(0);
                double y1 = path.getYs().get(0);
                double x2 = path.getXs().get(path.n - 1);
                double y2 = path.getYs().get(path.n - 1);
                ArrayList<Double> start_point = new ArrayList<>(List.of(path.getXs().get(0) + block.x, path.getYs().get(0) + block.y));
                ArrayList<Double> finish_point = new ArrayList<>(List.of(path.getXs().get(path.n - 1) + block.x, path.getYs().get(path.n - 1) + block.y));
                int i = points.indexOf(start_point);
                int j = points.indexOf(finish_point);
                double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
                m.get(i).set(j, distance);
            }
        }
        return m;
    }

    public ArrayList<Integer> dfs(int i) {
        ArrayList<Integer> mark = new ArrayList<Integer>();
        for (int j = 0; j < points.size(); j++) {
            mark.add(0);
        }
        mark.set(i, 1);
        for (int U = 0; U < points.size(); U++) {
            double a = matrix.get(i).get(U);
            if (a > 0) {
                if (mark.get(U) == 0) {
                    dfs(U, mark);
                }
            }
        }
        return mark;
    }

    public void dfs(int i, ArrayList<Integer> mark) {
        mark.set(i, 1);
        for (int U = 0; U < points.size(); U++) {
            double a = matrix.get(i).get(U);
            if (a > 0) {
                if (mark.get(U) == 0) {
                    dfs(U, mark);
                }
            }
        }
    }

    public ArrayList<ArrayList<Integer>> shortestPath(int i) {
        double inf = 1000000000.0;
        ArrayList<Integer> C = dfs(i);
        ArrayList<Double> d = new ArrayList<Double>();
        ArrayList<ArrayList<Integer>> p = new ArrayList<>();
        ArrayList<Integer> A = new ArrayList<Integer>();
        ArrayList<Integer> B = new ArrayList<Integer>();
        for (int k = 0; k < points.size(); k++) {
            d.add(0.0);
            p.add(new ArrayList<Integer>());
            if (k == i) {
                d.set(k, 0.0);
                p.set(k, new ArrayList<>(List.of(k)));
                B.add(i);
            } else if (C.get(k) == 1) {
                d.set(k, inf);
                B.add(k);
            } else {
                d.set(k, -1.0);
            }
        }
        while (B.size() > 0) {
            int V = 0;
            double d_v = inf + 1;
            if (B.size() > 1) {
                for (int v : B) {
                    if (d.get(v) < d_v) {
                        V = v;
                        d_v = d.get(v);
                    }
                }
            } else {
                V = B.get(0);
                d_v = d.get(V);
            }
            for (int U = 0; U < points.size(); U++) {
                double a = matrix.get(V).get(U);
                if (a > 0) {
                    if (d.get(U) < d.get(V) + matrix.get(V).get(U)) {
                        d.set(U, d.get(U));
                    } else {
                        d.set(U, d.get(V) + matrix.get(V).get(U));
                        ArrayList<Integer> c = (ArrayList<Integer>) p.get(V).clone();
                        c.add(U);
                        p.set(U, c);
                    }
                }
            }
            A.add(V);
            B.remove((Integer) V);
        }
        return p;
    }
    public ArrayList<Integer> shortestPath(int i, int j){
        return shortestPath(i).get(j);
    }

    public ArrayList<Double> shortestPathDistance(int i) {
        double inf = 1000000000.0;
        ArrayList<Integer> C = dfs(i);
        ArrayList<Double> d = new ArrayList<Double>();
        ArrayList<ArrayList<Integer>> p = new ArrayList<>();
        ArrayList<Integer> A = new ArrayList<Integer>();
        ArrayList<Integer> B = new ArrayList<Integer>();
        for (int k = 0; k < points.size(); k++) {
            d.add(0.0);
            p.add(new ArrayList<Integer>());
            if (k == i) {
                d.set(k, 0.0);
                p.set(k, new ArrayList<>(List.of(k)));
                B.add(i);
            } else if (C.get(k) == 1) {
                d.set(k, inf);
                B.add(k);
            } else {
                d.set(k, -1.0);
            }
        }
        while (B.size() > 0) {
            int V = 0;
            double d_v = inf + 1;
            if (B.size() > 1) {
                for (int v : B) {
                    if (d.get(v) < d_v) {
                        V = v;
                        d_v = d.get(v);
                    }
                }
            } else {
                V = B.get(0);
                d_v = d.get(V);
            }
            for (int U = 0; U < points.size(); U++) {
                double a = matrix.get(V).get(U);
                if (a > 0) {
                    if (d.get(U) < d.get(V) + matrix.get(V).get(U)) {
                        d.set(U, d.get(U));
                    } else {
                        d.set(U, d.get(V) + matrix.get(V).get(U));
                        ArrayList<Integer> c = (ArrayList<Integer>) p.get(V).clone();
                        c.add(U);
                        p.set(U, c);
                    }
                }
            }
            A.add(V);
            B.remove((Integer) V);
        }
        return d;
    }
    public double shortestPathDistance(int i, int j){
        return shortestPathDistance(i).get(j);
    }

}
