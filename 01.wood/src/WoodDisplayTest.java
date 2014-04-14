import junit.framework.TestCase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Алексей on 31.03.14.
 */
public class WoodDisplayTest extends TestCase {
    public void testDisplaycage() throws FileNotFoundException, Exception {
        FileInputStream fileInputStream = new FileInputStream("input.txt");
        WoodLoaderCreate woodLoaderCreate = new WoodLoaderCreate();
        MyWood wood = (MyWood) woodLoaderCreate.Load(fileInputStream);
        wood.createWoodman("man", new Point(1, 2));
        wood.createWoodman("man1", new Point(1, 1));
        try {
            WoodDisplay display = new WoodDisplay(wood, System.out);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
