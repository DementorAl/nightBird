import org.junit.Test;

import java.io.FileInputStream;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Алексей on 13.04.14.
 */
public class DisplayMyWoodTest {
    @Test
    public void testMove() throws Exception {
        FileInputStream fileInputStream = new FileInputStream("input.txt");
        WoodLoaderCreate woodLoaderCreate = new WoodLoaderCreate();
        MyWood wood = (MyWood) woodLoaderCreate.Load(fileInputStream);
        DisplayMyWood displayMyWood = new DisplayMyWood(wood, System.out);
        wood.createWoodman("man", new Point(1, 1));
        wood.createWoodman("man1", new Point(4, 1));
        displayMyWood.move("man", Direction.None);
        displayMyWood.move("man1", Direction.None);
        displayMyWood.move("man", Direction.Down);
        displayMyWood.move("man", Direction.Down);
        displayMyWood.move("man1", Direction.Up);

    }

    @Test
    public void testNotFound() throws Exception {
        FileInputStream fileInputStream = new FileInputStream("input.txt");
        WoodLoaderCreate woodLoaderCreate = new WoodLoaderCreate();
        MyWood wood = (MyWood) woodLoaderCreate.Load(fileInputStream);
        DisplayMyWood displayMyWood = new DisplayMyWood(wood, System.out);
        wood.createWoodman("man", new Point(1, 1));
        wood.createWoodman("man1", new Point(4, 1));
        displayMyWood.move("man", Direction.None);
        displayMyWood.move("man", Direction.Right);
        displayMyWood.move("man", Direction.None);
        displayMyWood.move("man", Direction.None);


        assertEquals(Action.WoodmanNotFound, displayMyWood.move("man", Direction.None));


    }
}
