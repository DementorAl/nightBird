import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.HashMap;

/**
 * Created by Алексей on 19.03.14.
 */
public class WoodDisplay extends MyWood {
    private static final HashMap<String, Character> displaysChar = new HashMap<String, Character>();
    private OutputStream outputWriter;
    private MyWood thisWood;

    public WoodDisplay(MyWood wood, PrintStream stream) throws IOException {
        int weightOfWood;
        int heightOfWood;
        thisWood = wood;
        outputWriter = stream;


        displaysChar.put("1111", '╬');
        displaysChar.put("0111", '╠');
        displaysChar.put("1110", '╩');
        displaysChar.put("0110", '╚');
        displaysChar.put("1101", '╣');
        displaysChar.put("0101", '║');
        displaysChar.put("0100", '║');
        displaysChar.put("0001", '║');
        displaysChar.put("1100", '╝');
        displaysChar.put("1011", '╦');
        displaysChar.put("0110", '╚');
        displaysChar.put("1010", '═');
        displaysChar.put("1000", '═');
        displaysChar.put("0010", '═');
        displaysChar.put("0011", '╔');
        displaysChar.put("1001", '╗');
        displaysChar.put("0000", '╬');

        weightOfWood = thisWood.dataAboutWood.get(1).length();
        heightOfWood = thisWood.dataAboutWood.size();

        for (int i = 0; i < weightOfWood; i++) {
            for (int j = 0; j < heightOfWood; j++) {
                displaysCage(i, j, outputWriter);

            }
            outputWriter.write('\n');

        }
    }


    public void displaysCage(int i, int j, OutputStream outputWriter) throws IOException {
        OutputStreamWriter writerStream = new OutputStreamWriter(outputWriter);
//        for (WoodCreate thisWoodman : woodmansCatalog.values()) {
//            Point thisWoodmanPosition = thisWoodman.currentPosition;
//            if (i == thisWoodmanPosition.getX() && j == thisWoodmanPosition.getY()) {
//                writerStream.write(thisWoodman.GetName().substring(0, 1));
//            }
//
//        }

        if (thisWood.dataAboutWood.get(i).charAt(j) == '0') {
            for (WoodCreate thisWoodman : thisWood.woodmansCatalog.values()) {
                Point thisWoodmanPosition = thisWoodman.currentPosition;
                if (i == thisWoodmanPosition.getX() && j == thisWoodmanPosition.getY()) {
                    writerStream.write(thisWoodman.GetName().substring(0, 1));
                } else writerStream.write(' ');

            }


        }
        if (thisWood.dataAboutWood.get(i).charAt(j) == '1') {
            String bound = boundRoundCage(i, j);
            char[] check;
            check = bound.toCharArray();
            bound = "";
            for (int u = 0; u < 4; u++) {
                if (check[u] != '1' && check[u] != '0') {
                    check[u] = '0';
                }
                bound += check[u];
            }
            writerStream.write(displaysChar.get(bound));


        }
        if (thisWood.dataAboutWood.get(i).charAt(j) == 'K') {
            writerStream.write('◙');
        }
        if (thisWood.dataAboutWood.get(i).charAt(j) == 'L') {
            writerStream.write('♥');
        }

        writerStream.flush();


    }

    private String boundRoundCage(int y, int x) {
        String result = "";
        result += (x > 0 && thisWood.dataAboutWood.get(y).charAt(x - 1) == '1') ? '1' : '0';
        result += (y > 0 && thisWood.dataAboutWood.get(y - 1).charAt(x) == '1') ? '1' : '0';
        result += (x < thisWood.dataAboutWood.get(y).length() - 1 && thisWood.dataAboutWood.get(y).charAt(x + 1) == '1') ? '1' : '0';
        result += (y < thisWood.dataAboutWood.size() - 1 && thisWood.dataAboutWood.get(y + 1).charAt(x) == '1') ? '1' : '0';
//        System.out.println("x = " + x + "; y = " + y + ": " + result);
        return result;

    }


}
