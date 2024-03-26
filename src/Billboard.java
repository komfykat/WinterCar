import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Billboard extends JFrame implements MouseListener, MouseMotionListener, KeyEventDispatcher {
    boolean game = false;
    int levelNumber = 0;
    int ConcreteSpent = 0;
    BufferedImage MenuImage = ImageIO.read(Billboard.class.getResourceAsStream("Menu.png"));
    BufferedImage beforeGameImage = ImageIO.read(Billboard.class.getResourceAsStream("beforeGame.png"));
    BufferedImage levelCompleteImage = ImageIO.read(Billboard.class.getResourceAsStream("LevelCompleteImage.png"));
    LevelButton Tutorial = new LevelButton(413, 703, 370, 135, 0);
    LevelButton Level1 = new LevelButton(825, 704, 128, 115, 1);
    LevelButton Level2 = new LevelButton(1013, 700, 109, 116, 2);
    LevelButton Level3 = new LevelButton(1196, 707, 109, 116, 3);
    LevelButton Level4 = new LevelButton(1379, 700, 109, 116, 4);
    ArrayList<LevelButton> levelButtons = new ArrayList<>(List.of(Tutorial, Level1, Level2, Level3, Level4));
    Car blankCar = new Car(50, 10, 10);

    ArrayList<Spawner> spawners0;
    ArrayList<Spawner> spawners1;
    ArrayList<Spawner> spawners2;
    ArrayList<Spawner> spawners3;
    ArrayList<Spawner> spawners4;
    ArrayList<ArrayList<Spawner>> levelSpawners;
    ArrayList<Despawner> despawners0;
    ArrayList<Despawner> despawners1;
    ArrayList<Despawner> despawners2;
    ArrayList<Despawner> despawners3;
    ArrayList<Despawner> despawners4;
    ArrayList<Despawner> despawner5;
    ArrayList<Despawner> despawner6;
    ArrayList<Despawner> despawner7;
    ArrayList<Despawner> despawner8;
    ArrayList<ArrayList<Despawner>> levelDespawners;

    ArrayList<Car> cars = new ArrayList<>();
    ArrayList<Block> blocks = new ArrayList<>();

    ArrayList<Spawner> spawners = new ArrayList<>();
    ArrayList<Despawner> despawners = new ArrayList<>();
    BufferedImage Blocksimage = ImageIO.read(Billboard.class.getResourceAsStream("Blocks.png"));
    BufferedImage Cellimage = ImageIO.read(Billboard.class.getResourceAsStream("Cell.png"));
    ArrayList<Rectangle2D> cells = new ArrayList<>();
    ArrayList<ArrayList<Integer>> cellsfull = new ArrayList<>();

    int mouseX;
    int mouseY;

    boolean Simulation = false;
    boolean Building = true;
    boolean levelComplete = false;
    boolean levelExited = false;
    boolean beforeGame = true;

    SimulationButton SimulationButton;
    DisposeButton DisposeButton;
    BuildingButton BuildingButton;
    RebuildButton RebuildButton;

    CrossroadBlockButton CSButton;
    CrossroadWithLightButton CSLButton;
    VBlockButton VButton;
    HBlockButton HButton;
    DLBlockButton DLButton;
    DRBlockButton DRButton;
    ULBlockButton ULButton;
    URBlockButton URButton;

    Button backButtonAfter;
    Button ToMainMenu;
    Button gotIt;
    Button levelExit;

    ArrayList<Button> BuildingButtons;
    int BuildingBlockNumber = 2;
    Block thisBuildingBlock;
    boolean BuildingBlockGood = false;

    double movingTime = 0;
    int carspassed = 0;

    Billboard() throws IOException {
        setSize(1900, 1000);
        BlockGraph bg = new BlockGraph(blocks);
        Spawner spawner1 = new Spawner(0, 155, blankCar, 1000, 0, despawners, bg);
        Spawner spawner2 = new Spawner(0, 255, blankCar, 1000, 1, despawners, bg);
        Spawner spawner3 = new Spawner(145, 0, blankCar, 1000, 2, despawners, bg);
        Spawner spawner4 = new Spawner(245, 0, blankCar, 1000, 3, despawners, bg);
        Spawner spawner5 = new Spawner(400, 145, blankCar, 1000, 4, despawners, bg);
        Spawner spawner6 = new Spawner(400, 245, blankCar, 1000, 5, despawners, bg);
        Spawner spawner7 = new Spawner(155, 400, blankCar, 1000, 6, despawners, bg);
        Spawner spawner8 = new Spawner(255, 400, blankCar, 1000, 7, despawners, bg);
        Spawner spawner9 = new Spawner(0, 355, blankCar, 1000, 1, despawners, bg);
        Spawner spawner10 = new Spawner(400, 345, blankCar, 1000, 2, despawners, bg);

        Despawner despawner1 = new Despawner(400, 155, cars, 0);
        Despawner despawner2 = new Despawner(400, 255, cars, 1);
        Despawner despawner3 = new Despawner(145, 400, cars, 2);
        Despawner despawner4 = new Despawner(245, 400, cars, 3);
        Despawner despawner5 = new Despawner(0, 145, cars, 4);
        Despawner despawner6 = new Despawner(0, 245, cars, 5);
        Despawner despawner7 = new Despawner(155, 0, cars, 6);
        Despawner despawner8 = new Despawner(255, 0, cars, 7);
        Despawner despawner9 = new Despawner(400, 355, cars, 1);
        Despawner despawner10 = new Despawner(0, 345, cars, 2);

        spawners0 = new ArrayList<>(List.of(spawner1));
        despawners0 = new ArrayList<>(List.of(despawner1));
        spawners1 = new ArrayList<>(List.of(spawner1, spawner2));
        despawners1 = new ArrayList<>(List.of(despawner1, despawner2));
        spawners2 = new ArrayList<>(List.of(spawner1, spawner2, spawner3, spawner4));
        despawners2 = new ArrayList<>(List.of(despawner1, despawner2, despawner3, despawner4));
        spawners3 = new ArrayList<>(List.of(spawner1, spawner2, spawner3, spawner4, spawner5, spawner6));
        despawners3 = new ArrayList<>(List.of(despawner1, despawner2, despawner3, despawner4, despawner5, despawner6));
        spawners4 = new ArrayList<>(List.of(spawner1, spawner2, spawner3, spawner4, spawner5, spawner6, spawner7, spawner8, spawner9, spawner10));
        despawners4 = new ArrayList<>(List.of(despawner1, despawner2, despawner3, despawner4, despawner5, despawner6, despawner7, despawner8, despawner9, despawner10));
        levelSpawners = new ArrayList<>(List.of(spawners0, spawners1, spawners2, spawners3, spawners4));
        levelDespawners = new ArrayList<>(List.of(despawners0, despawners1, despawners2, despawners3, despawners4));

        this.SimulationButton = new SimulationButton(this.getWidth() - 200, 0);
        this.DisposeButton = new DisposeButton(this.getWidth() - 200, SimulationButton.image.getHeight());
        this.BuildingButton = new BuildingButton(this.getWidth() - 200, SimulationButton.image.getHeight() * 2);
        this.RebuildButton = new RebuildButton(this.getWidth() - 200, SimulationButton.image.getHeight() * 3);

        this.CSButton = new CrossroadBlockButton(1200, 900);
        this.CSLButton = new CrossroadWithLightButton(1100, 900);
        this.VButton = new VBlockButton(1300, 900);
        this.HButton = new HBlockButton(1400, 900);
        this.DLButton = new DLBlockButton(1500, 900);
        this.DRButton = new DRBlockButton(1600, 900);
        this.ULButton = new ULBlockButton(1700, 900);
        this.URButton = new URBlockButton(1800, 900);
        BuildingButtons = new ArrayList<Button>(List.of(CSButton, VButton, HButton, DLButton, DRButton, ULButton, URButton, CSLButton));

        CrossroadWithTrafficLight block1 = new CrossroadWithTrafficLight(100, 100, 100, 100);
        CrossroadWithTrafficLight block2 = new CrossroadWithTrafficLight(200, 100, 100, 100);
        CrossroadWithTrafficLight block3 = new CrossroadWithTrafficLight(100, 200, 100, 100);
        CrossroadWithTrafficLight block4 = new CrossroadWithTrafficLight(200, 200, 100, 100);
//        blocks.add(block1);
//        blocks.add(block2);
//        blocks.add(block3);
//        blocks.add(block4);
//        for (Car car : cars) {
//            car.blocks = blocks;
//        }
//        for (Spawner spawner : spawners) {
//            spawner.setBg(new BlockGraph(blocks));
//            spawner.GlobalPath = spawner.GetGlobalPath();
//        }



        backButtonAfter = new Button(800, 300);
        backButtonAfter.image = ImageIO.read(Billboard.class.getResourceAsStream("BackButtonImage.png"));
        backButtonAfter.width = 200;
        backButtonAfter.height = 62;

        ToMainMenu = new Button(this.getWidth() - 200, SimulationButton.image.getHeight() * 4);
        ToMainMenu.image = ImageIO.read(Billboard.class.getResourceAsStream("ToMainMenu.png"));
        ToMainMenu.width = 200;
        ToMainMenu.height = 62;

        gotIt = new Button(1015, 497);
        gotIt.width = 447;
        gotIt.height = 139;

        levelExit = new Button(789, 94);
        levelExit.width = 200;
        levelExit.height = 64;


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);
        setVisible(true);
        addMouseListener(this);
        addMouseMotionListener(this);

        for (int i = 0; i < 19; i++) {
            ArrayList<Integer> a = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                Rectangle2D cell = new Rectangle2D.Double(i * 100, j * 100, 100, 100);
                a.add(0);
                cells.add(cell);
            }
            cellsfull.add(a);
        }
//        cellsfull.get(1).set(1, 1);

    }

    @Override
    public void paint(Graphics g) {
        BufferStrategy bufferStrategy = getBufferStrategy();
        if (bufferStrategy == null) {
            createBufferStrategy(2);
            bufferStrategy = getBufferStrategy();
        }
        g = bufferStrategy.getDrawGraphics();
        super.paint(g);
        if (beforeGame) {
            g.drawImage(beforeGameImage, 0, 0, null);
        } else if (!game) {
            if (!levelComplete) {
                g.drawImage(MenuImage, 0, 0, null);
            }
            else if (levelExited) {
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setFont(new Font("TimesRoman", Font.PLAIN, 24));
                    String str1 = "Your gametime: " + (int) movingTime;
                    String str2 = "Average cars per minute: " + (double) ((int) ((carspassed / movingTime) * 100)) / 100;
                    String str3 = "Concrete spent: " + ConcreteSpent;
                    String str4 = "Final rating: " + (int) (carspassed / movingTime / ConcreteSpent * 10000);
                    g2.drawString(str1, 800, 50);
                    g2.drawString(str2, 800, 100);
                    g2.drawString(str3, 800, 150);
                    g2.drawString(str4, 800, 200);
                    backButtonAfter.paint(g);
                }
        } else {

            for (Rectangle2D cell : cells) {
                g.drawImage(Cellimage, (int) cell.getX(), (int) cell.getY(), null);
//            g.drawRect((int) cell.getX(), (int) cell.getY(), (int) cell.getWidth(), (int) cell.getHeight());

            }
            for (Block block : blocks) {
                block.paint(g);
            }
            if (Building) {
                g.drawImage(Blocksimage, 1100, 800, null);
                if (thisBuildingBlock != null) {
                    int i = (int) (thisBuildingBlock.x / 100);
                    int j = (int) (thisBuildingBlock.y / 100);
                    if (i < 11 || i < 17 && j < 8 || j < 8 && j > 2) {
                        if (BuildingBlockGood) {
                            thisBuildingBlock.buildpaintgood(g);
                        } else {
                            thisBuildingBlock.buildpaintbad(g);
                        }

                    }
                }
            }


//        ArrayList<Car> carsnew = (ArrayList<Car>) cars.clone();
            for (Car car : cars) {
                car.paint(g);
            }

//        cars = carsnew;
            SimulationButton.paint(g);
            DisposeButton.paint(g);
            BuildingButton.paint(g);
            RebuildButton.paint(g);
            ToMainMenu.paint(g);

            for (Spawner spawner : spawners) {
                spawner.paint(g);
            }
            for (Despawner despawner : despawners) {
                despawner.paint(g);
            }
            if (levelComplete){
                g.drawImage(levelCompleteImage, 600, 0, null);
            }
//        System.out.println(Building + " " + Simulation);
//        CSButton.paint(g);
        }
        g.dispose();
        bufferStrategy.show();

    }

    public void update() throws IOException {
        if (Simulation) {
            for (Car cari : cars) {
                cari.update();
//                System.out.println("Updated");
            }
            for (Spawner spawneri : spawners) {
                spawneri.updateCars(cars, blocks);
            }
            for (Despawner despawneri : despawners) {
                int carsbefore = cars.size();
                despawneri.cars = cars;
                cars = despawneri.update();
                int carsnow = cars.size();
                carspassed += carsbefore - carsnow;
            }
            for (Block block : blocks){
                block.update();
            }
            movingTime += Constants.tickOfSimulation;
        }
//        System.out.println(Simulation);
//        System.out.println(movingTime + "   " + carspassed + "   " + carspassed / movingTime);
        if (game) {
            if (levelNumber == 0) {
                boolean flag = true;
                for (Despawner despawner : despawners) {
                    if (despawner.carsDeleted == 0) {
                        flag = false;
                    }
                }
                if (flag) {
                    levelComplete = true;
                    //game = false;
                    //Simulation = false;
                   // Building = true;
                }
            } else {
                if (carspassed / movingTime >= 1 && movingTime >= 60) {
                    boolean flag = true;
                    for (Despawner despawner : despawners) {
                        if (despawner.carsDeleted == 0) {
                            flag = false;
                        }
                    }
                    if (flag) {
                        levelComplete = true;
                        //game = false;
                        //Simulation = false;
                        //Building = true;
                    }
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        mouseX = e.getX();
//        mouseY = e.getY();
//        if (SimulationButton.mouseHit(mouseX, mouseY)) {
//            if (Building){
//                Building = false;
//                Simulation = true;
//            }
//            else{
//                Simulation = !Simulation;
//            }
//        }
//        if (DisposeButton.mouseHit(mouseX, mouseY)) {
//            DisposeButton.onmousehit(cars, spawners);
//        }
//        if (BuildingButton.mouseHit(mouseX, mouseY)){
//            if (Building){
//                Building = false;
//            }
//            else {
//                Building = true;
//                Simulation = false;
//                DisposeButton.onmousehit(cars, spawners);
//            }
//        }
//        else if (Building){
//            int i = mouseX / 100;
//            int j = mouseY / 100;
//            Block block = new HBlock(i * 100, j * 100, 100, 100);
//            blocks.add(block);
//            for (Car car : cars){
//                car.blocks = blocks;
//            }
//            for (Spawner spawner : spawners){
//                spawner.bg = new BlockGraph(blocks);
//                spawner.GlobalPath = spawner.GetGlobalPath();
//            }
//        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        if (beforeGame) {
            if (gotIt.mouseHit(mouseX, mouseY)) {
                beforeGame = false;
            }
        } else if (!game) {
            if (levelExited && levelComplete) {
                if (backButtonAfter.mouseHit(mouseX, mouseY)) {
                    levelComplete = false;
                    levelExited = false;
                    DisposeButton.onmousehit(cars, spawners);
                    movingTime = 0;
                    carspassed = 0;
                    this.blocks = new ArrayList<Block>();
                    for (Car car : cars) {
                        car.blocks = blocks;
                    }
                    for (Spawner spawner : spawners) {
                        spawner.setBg(new BlockGraph(blocks));
                        spawner.GlobalPath = spawner.GetGlobalPath();
                    }
                    cellsfull = new ArrayList<>();
                    for (int i = 0; i < 19; i++) {
                        ArrayList<Integer> a = new ArrayList<>();
                        for (int j = 0; j < 10; j++) {
                            a.add(0);
                        }
                        cellsfull.add(a);
                    }
                }
            } else {
                for (LevelButton levelButton : levelButtons) {
                    if (levelButton.mouseHit(mouseX, mouseY)) {
                        levelNumber = levelButton.onmousehit();
                        spawners = levelSpawners.get(levelNumber);
                        despawners = levelDespawners.get(levelNumber);
                        for (Spawner spawner : spawners) {
                            spawner.despawners = despawners;
                            spawner.makenicedespawners();
                        }
                        game = true;
                    }
                }
            }
        } else {
            if (levelComplete){
                if (levelExit.mouseHit(mouseX, mouseY)){
                    levelExited = true;
                    game = false;
                    Simulation = false;
                    Building = true;
                    for (Block block : blocks){
                        block.timeFromChange = 0;
                    }
                }
            }
            if (SimulationButton.mouseHit(mouseX, mouseY)) {
                if (Building) {
                    Building = false;
                    Simulation = true;
                } else {
                    Simulation = !Simulation;
                }
            }
            if (DisposeButton.mouseHit(mouseX, mouseY)) {
                DisposeButton.onmousehit(cars, spawners);
                movingTime = 0;
                carspassed = 0;
                for (Block block : blocks){
                    block.timeFromChange = 0;
                }
            }
            if (ToMainMenu.mouseHit(mouseX, mouseY)) {
                game = false;
                levelComplete = false;
                this.blocks = new ArrayList<Block>();
                for (Car car : cars) {
                    car.blocks = blocks;
                }
                for (Spawner spawner : spawners) {
                    spawner.setBg(new BlockGraph(blocks));
                    spawner.GlobalPath = spawner.GetGlobalPath();
                }
                movingTime = 0;
                DisposeButton.onmousehit(cars, spawners);
                for (Block block : blocks){
                    block.timeFromChange = 0;
                }
                carspassed = 0;
                ConcreteSpent = 0;
                cellsfull = new ArrayList<>();
                for (int i = 0; i < 19; i++) {
                    ArrayList<Integer> a = new ArrayList<>();
                    for (int j = 0; j < 10; j++) {
                        a.add(0);
                    }
                    cellsfull.add(a);
                }
            }
            if (RebuildButton.mouseHit(mouseX, mouseY)) {
                DisposeButton.onmousehit(cars, spawners);
                movingTime = 0;
                carspassed = 0;
                ConcreteSpent = 0;
                this.blocks = new ArrayList<Block>();
                for (Car car : cars) {
                    car.blocks = blocks;
                }
                for (Spawner spawner : spawners) {
                    spawner.setBg(new BlockGraph(blocks));
                    spawner.GlobalPath = spawner.GetGlobalPath();
                }
                if (Simulation) {
                    Simulation = false;
                    Building = true;
                }
                cellsfull = new ArrayList<>();
                for (int i = 0; i < 19; i++) {
                    ArrayList<Integer> a = new ArrayList<>();
                    for (int j = 0; j < 10; j++) {
                        a.add(0);
                    }
                    cellsfull.add(a);
                }
            }
            if (BuildingButton.mouseHit(mouseX, mouseY)) {
                if (Building) {
                    Building = false;
                } else {
                    Building = true;
                    Simulation = false;
                    DisposeButton.onmousehit(cars, spawners);
                    movingTime = 0;
                    carspassed = 0;
                }
            } else if (Building) {
                for (Button button : BuildingButtons) {
                    if (button.mouseHit(mouseX, mouseY)) {
                        BuildingBlockNumber = button.onmousehit();
                        System.out.println(BuildingBlockNumber);
                    }
                }
                int i = mouseX / 100;
                int j = mouseY / 100;
                if (i < 11 || (i < 17 && j < 8) || (j < 8 && j > 3)) {
                    Block block = null;
                    if (BuildingBlockNumber == 0) {
                        try {
                            ConcreteSpent += 25;
                            block = new Crossroad(i * 100, j * 100, 100, 100);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    if (BuildingBlockNumber == 1) {
                        try {
                            ConcreteSpent += 10;
                            block = new VBlock(i * 100, j * 100, 100, 100);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    if (BuildingBlockNumber == 2) {
                        try {
                            ConcreteSpent += 10;
                            block = new HBlock(i * 100, j * 100, 100, 100);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    if (BuildingBlockNumber == 3) {
                        try {
                            ConcreteSpent += 10;
                            block = new DLBlock(i * 100, j * 100, 100, 100);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    if (BuildingBlockNumber == 4) {
                        try {
                            ConcreteSpent += 10;
                            block = new DRBlock(i * 100, j * 100, 100, 100);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    if (BuildingBlockNumber == 5) {
                        try {
                            ConcreteSpent += 10;
                            block = new ULBlock(i * 100, j * 100, 100, 100);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    if (BuildingBlockNumber == 6) {
                        try {
                            ConcreteSpent += 10;
                            block = new URBlock(i * 100, j * 100, 100, 100);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    if (BuildingBlockNumber == 7){
                        try {
                            ConcreteSpent += 25;
                            block = new CrossroadWithTrafficLight(i * 100, j * 100, 100, 100);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    if (BuildingBlockGood) {
                        blocks.add(block);
                        for (Car car : cars) {
                            car.blocks = blocks;
                        }
                        for (Spawner spawner : spawners) {
                            spawner.setBg(new BlockGraph(blocks));
                            spawner.GlobalPath = spawner.GetGlobalPath();
                        }
                        cellsfull.get(i).set(j, 1);
                    }
                }
            }
        }
    }


    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (Building) {
            int i = e.getX() / 100;
            int j = e.getY() / 100;
            Block block = null;
            if (BuildingBlockNumber == 0) {
                try {
                    block = new Crossroad(i * 100, j * 100, 100, 100);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (BuildingBlockNumber == 1) {
                try {
                    block = new VBlock(i * 100, j * 100, 100, 100);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (BuildingBlockNumber == 2) {
                try {
                    block = new HBlock(i * 100, j * 100, 100, 100);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (BuildingBlockNumber == 3) {
                try {
                    block = new DLBlock(i * 100, j * 100, 100, 100);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (BuildingBlockNumber == 4) {
                try {
                    block = new DRBlock(i * 100, j * 100, 100, 100);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (BuildingBlockNumber == 5) {
                try {
                    block = new ULBlock(i * 100, j * 100, 100, 100);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (BuildingBlockNumber == 6) {
                try {
                    block = new URBlock(i * 100, j * 100, 100, 100);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (BuildingBlockNumber == 7){
                try {
                    block = new CrossroadWithTrafficLight(i * 100, j * 100, 100, 100);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            thisBuildingBlock = block;
            if (i < 11 || (i < 17 && j < 8) || (j < 8 && j > 3)) {
                if (cellsfull.get(i).get(j) == 1) {
                    BuildingBlockGood = false;
                } else {
                    BuildingBlockGood = true;
                }
            }
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        return false;
    }
}
