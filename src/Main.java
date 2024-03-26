import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        Billboard frame = new Billboard();
        while (true){
            frame.update();
            frame.repaint();
            Thread.sleep((long) (Constants.tickOfPaint * 1000));
        }


    }

}
