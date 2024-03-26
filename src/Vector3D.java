public class Vector3D {
    public double x;
    public double y;
    public double z;

    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public boolean right(Vector3D v) {
        double z = x * v.y - y * v.x;
        return z < 0;
    }

    public boolean onedirection(Vector3D v) {
        if ((x == v.x) && (y == v.y) && (z == v.z)) {
            return true;
        } else {
            return false;
        }
    }

}
