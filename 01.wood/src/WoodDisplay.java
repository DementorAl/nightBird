import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;

/**
 * Created by Алексей on 19.03.14.
 */
public class WoodDisplay extends MyWood {
    private static final HashMap<String, Character> displaysChar = new HashMap<String, Character>();
    private OutputStream outputWriter;
    private MyWood thisWood;

    public WoodDisplay(MyWood wood, OutputStream stream) throws IOException {
        int weightOfWood;
        int heightOfWood;
        thisWood = wood;
        outputWriter = stream;
        displaysChar.put("1111", '╬');                //по часовой стрелке, начиная слева
        displaysChar.put("0011", '╗');
        displaysChar.put("0100", '═');
        displaysChar.put("0101", '║');
        displaysChar.put("0100", '║');
        displaysChar.put("0001", '║');
        displaysChar.put("0110", '╔');
        displaysChar.put("1100", '╔');
        displaysChar.put("0111", '╦');
        //displaysChar.put("0011", '╝');
        displaysChar.put("0011", '╔');
        displaysChar.put("1010", '═');
        displaysChar.put("0010", '═');
        displaysChar.put("1000", '═');
        displaysChar.put("1011", '╣');
        displaysChar.put("0110", '╚');
        displaysChar.put("1101", '╩');
        displaysChar.put("1110", '╠');
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


    private void displaysCage(int i, int j, OutputStream outputWriter) throws IOException {
        OutputStreamWriter writerStream = new OutputStreamWriter(outputWriter);
        if (thisWood.dataAboutWood.get(i).charAt(j) == '1' || thisWood.dataAboutWood.get(i).charAt(j) == '0') {
            String bound = boundRoundCage(i, j);
            char[] check = new char[3];
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
        for (WoodCreate thisWoodman : woodmansCatalog.values()) {
            Point thisWoodmanPosition = thisWoodman.currentPosition;
            if (i == thisWoodmanPosition.getX() && j == thisWoodmanPosition.getY()) {
                writerStream.write(thisWoodman.GetName().substring(0, 1));
            }

        }

        writerStream.flush();


    }

    private String boundRoundCage(int i, int j) {
        if (i > 0 && j > 0 && i < thisWood.dataAboutWood.size() - 1 && j < thisWood.dataAboutWood.get(1).length() - 1) {
            String string = String.valueOf(thisWood.dataAboutWood.get(i).charAt(j - 1)) + String.valueOf(thisWood.dataAboutWood.get(i - 1).charAt(j))
                    + String.valueOf(thisWood.dataAboutWood.get(i).charAt(j + 1)) + String.valueOf(thisWood.dataAboutWood.get(i + 1).charAt(j));
            return string;
        }
        if (i == 0 && j > 0 && j < thisWood.dataAboutWood.get(0).length() - 1) {
            String string = String.valueOf(thisWood.dataAboutWood.get(i).charAt(j - 1)) + "0"
                    + String.valueOf(thisWood.dataAboutWood.get(i).charAt(j + 1)) + String.valueOf(thisWood.dataAboutWood.get(i + 1).charAt(j));
            return string;
        }
        if (i > 0 && j == 0 && i < thisWood.dataAboutWood.size() - 1) {
            String string = "0" + String.valueOf(thisWood.dataAboutWood.get(i - 1).charAt(j))
                    + String.valueOf(thisWood.dataAboutWood.get(i).charAt(j + 1)) + String.valueOf(thisWood.dataAboutWood.get(i + 1).charAt(j));
            return string;
        }
        if (i == thisWood.dataAboutWood.size() && j != 0 && j != thisWood.dataAboutWood.get(1).length() - 1) {
            String string = String.valueOf(thisWood.dataAboutWood.get(i).charAt(j - 1)) + String.valueOf(thisWood.dataAboutWood.get(i - 1).charAt(j))
                    + String.valueOf(thisWood.dataAboutWood.get(i).charAt(j + 1)) + "0";
            return string;
        }
        if (i == thisWood.dataAboutWood.size() - 1 && j == thisWood.dataAboutWood.get(1).length() - 1) {
            String string = String.valueOf(thisWood.dataAboutWood.get(i).charAt(j - 1)) + String.valueOf(thisWood.dataAboutWood.get(i - 1).charAt(j))
                    + "0" + "0";
            return string;
        }
        if (i == 0 && j < thisWood.dataAboutWood.get(1).length() - 1 && j > 0) {
            String string = String.valueOf(thisWood.dataAboutWood.get(i).charAt(j - 1)) + "0"
                    + String.valueOf(thisWood.dataAboutWood.get(i).charAt(j + 1)) + String.valueOf(thisWood.dataAboutWood.get(i + 1).charAt(j));
            return string;
        }
        if (i == 0 && j == 0) {
            String string = "0" + "0"
                    + String.valueOf(thisWood.dataAboutWood.get(i).charAt(j + 1)) + String.valueOf(thisWood.dataAboutWood.get(i + 1).charAt(j));
            return string;

        }
        if (i == 0 && j == thisWood.dataAboutWood.get(0).length() - 1) {
            String string = String.valueOf(thisWood.dataAboutWood.get(i).charAt(j - 1)) + "0"
                    + "0" + String.valueOf(thisWood.dataAboutWood.get(i + 1).charAt(j));
            return string;
        }
        if (i > 0 && i < thisWood.dataAboutWood.size() - 1 && j == 0) {
            String string = "0" + String.valueOf(thisWood.dataAboutWood.get(i - 1).charAt(j))
                    + String.valueOf(thisWood.dataAboutWood.get(i).charAt(j + 1)) + String.valueOf(thisWood.dataAboutWood.get(i + 1).charAt(j));
            return string;

        }
        if (i == thisWood.dataAboutWood.size() - 1 && j == 0) {
            String string = "0" + String.valueOf(thisWood.dataAboutWood.get(i - 1).charAt(j))
                    + String.valueOf(thisWood.dataAboutWood.get(i).charAt(j + 1)) + "0";
            return string;

        }
        if (i > 0 && i < thisWood.dataAboutWood.size() - 1 && j == 0) {
            String string = "0" + String.valueOf(thisWood.dataAboutWood.get(i - 1).charAt(j))
                    + String.valueOf(thisWood.dataAboutWood.get(i).charAt(j + 1)) + String.valueOf(thisWood.dataAboutWood.get(i + 1).charAt(j));
            return string;
        }
        if (i > 0 && i < thisWood.dataAboutWood.size() && j == thisWood.dataAboutWood.get(0).length()) {
            String string = String.valueOf(thisWood.dataAboutWood.get(i).charAt(j - 1)) + String.valueOf(thisWood.dataAboutWood.get(i - 1).charAt(j))
                    + "0" + String.valueOf(thisWood.dataAboutWood.get(i + 1).charAt(j));
            return string;
        }

//        String string = String.valueOf(thisWood.dataAboutWood.get(i).charAt(j - 1)) + String.valueOf(thisWood.dataAboutWood.get(i - 1).charAt(j))
//                + String.valueOf(thisWood.dataAboutWood.get(i).charAt(j + 1)) + String.valueOf(thisWood.dataAboutWood.get(i + 1).charAt(j));
//            return string;
//        }
//        if (i!=0&&j!=0){
//        String string = String.valueOf(thisWood.dataAboutWood.get(i).charAt(j - 1)) + String.valueOf(thisWood.dataAboutWood.get(i - 1).charAt(j))
//                + String.valueOf(thisWood.dataAboutWood.get(i).charAt(j + 1)) + String.valueOf(thisWood.dataAboutWood.get(i + 1).charAt(j));
//            return string;
//        }
//
//        if (i==0 ){
//            if(j!=0){
//            String string = String.valueOf(thisWood.dataAboutWood.get(i).charAt(j - 1)) + "0" + String.valueOf(thisWood.dataAboutWood.get(i).charAt(j + 1))
//                    + String.valueOf(thisWood.dataAboutWood.get(i + 1).charAt(j));
//            return string;}
//            else {
//                String string = "0" + "0" + String.valueOf(thisWood.dataAboutWood.get(i).charAt(j + 1))
//                        + String.valueOf(thisWood.dataAboutWood.get(i + 1).charAt(j));
//                return string;
//            }
//
//        }
//        if (i==thisWood.dataAboutWood.size()){
//            String string = String.valueOf(thisWood.dataAboutWood.get(i).charAt(j - 1)) + String.valueOf(thisWood.dataAboutWood.get(i - 1).charAt(j))
//                    + String.valueOf(thisWood.dataAboutWood.get(i).charAt(j + 1)) + "0";
//            return string;
//        }
//        if (j==0){
//
//            String string = "0" + String.valueOf(thisWood.dataAboutWood.get(i - 1).charAt(j)) + String.valueOf(thisWood.dataAboutWood.get(i).charAt(j + 1))
//                    + String.valueOf(thisWood.dataAboutWood.get(i + 1).charAt(j));
//            return string;
//        }
//        if (j==thisWood.dataAboutWood.get(1).length()){
//            String string = String.valueOf(thisWood.dataAboutWood.get(i).charAt(j - 1)) + String.valueOf(thisWood.dataAboutWood.get(i - 1).charAt(j))
//                    + "0" + String.valueOf(thisWood.dataAboutWood.get(i + 1).charAt(j));
//            return string;
//        }
//        if (i==thisWood.dataAboutWood.size()&& j==thisWood.dataAboutWood.get(1).length()){
//            String string = String.valueOf(thisWood.dataAboutWood.get(i).charAt(j - 1)) + String.valueOf(thisWood.dataAboutWood.get(i - 1).charAt(j))
//                    + "0" + "0";
//            return string;
//        }
        return "nothing";
    }


}
