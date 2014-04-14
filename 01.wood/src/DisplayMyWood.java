import java.io.IOException;
import java.io.PrintStream;

/**
 * Created by Алексей on 10.04.14.
 */
public class DisplayMyWood extends MyWood {
    MyWood wood;
    PrintStream output;

    public DisplayMyWood(MyWood myWood, PrintStream out) {
        try {
            this.wood = myWood;
            this.woodmansCatalog = myWood.woodmansCatalog;
            this.dataAboutWood = myWood.dataAboutWood;
            output = out;
            new WoodDisplay(myWood, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Action move(String name, Direction direction) throws Exception {

        Action newAction = super.move(name, direction);
        if (newAction == Action.WoodmanNotFound) {
            woodmansCatalog.remove(name);
            return Action.WoodmanNotFound;
        }
        Point newLocation = woodmansCatalog.get(name).GetLocation();
        //написать метод перерисовывающий лишь одну клетку

        WoodDisplay displayMyWood = new WoodDisplay(this, System.out);
        new WoodDisplay(wood, output);
//        displayMyWood.displaysCage(newLocation.getX(),newLocation.getY(),System.out);
//        displayMyWood.displaysCage(previewLocation.getX(),previewLocation.getY(),System.out);
        return Action.Ok;
    }
}
