import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Алексей on 05.03.14.
 */
public class MyWood implements Wood {
    ArrayList<String> dataAboutWood = new ArrayList<String>();
    protected HashMap<String, WoodCreate> woodmansCatalog = new HashMap<String, WoodCreate>();

    public MyWood(ArrayList<String> strings) {
        dataAboutWood = strings;

    }

    public MyWood() {
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MyWood) {
            MyWood wood1 = (MyWood) obj;
            if (wood1.dataAboutWood.size() == this.dataAboutWood.size()) {
                for (int i = 0; i < this.dataAboutWood.size(); i++) {
                    if (!wood1.dataAboutWood.get(i).equals(this.dataAboutWood.get(i))) {
                        return false;
                    }
                }
            } else return false;
            return true;
        }
        return false;

    }

    @Override
    public void createWoodman(String name, Point start) throws Exception {
        if (!woodmansCatalog.containsKey(name)) {
            char c = dataAboutWood.get(start.getX()).charAt(start.getY());
            if (c != '1') {
                woodmansCatalog.put(name, new WoodCreate(name, start));
            } else throw new Exception("здесь стена");
        } else throw new Exception("игрок с таким именем уже существует");
    }

    public WoodCreate getWoodman(String str) throws Exception {
        if (woodmansCatalog.containsKey(str)) {
            return woodmansCatalog.get(str);
        } else throw new Exception("такого персонажа нет");
    }

    public int countOfWoodmans() {
        return woodmansCatalog.size();
    }

    @Override
    public Action move(String name, Direction direction) throws Exception {
        Point point = null;
        if (woodmansCatalog.containsKey(name)) {
            point = woodmansCatalog.get(name).currentPosition;
            switch (direction) {
                case Up:
                    point = point.MoveUp();
                    break;
                case Down:
                    point = point.MoveDown();
                    break;
                case Right:
                    point = point.MoveRigth();
                    break;
                case Left:
                    point = point.MoveLeft();
                    break;
                case None:
                    break;
            }
        } else
            return Action.WoodmanNotFound;

        if ((point.getX() < 0) || (point.getX() > dataAboutWood.size())) {
            System.out.println("неправильная координата x =" + woodmansCatalog.get(name).currentPosition.getX());
            return Action.Fail;

        }
        if ((point.getY() < 0) || (point.getY() > dataAboutWood.get(point.getX()).length())) {
            System.out.println("неправильная координата x =" + woodmansCatalog.get(name).currentPosition.getX());
            return Action.Fail;
        }
        Action action = yesOrNo(point, woodmansCatalog.get(name));

        return action;
    }

    private Action yesOrNo(Point point, WoodCreate character) {
        String buff = dataAboutWood.get(point.getX());
        if (buff.charAt(point.getY()) == '0') {
            character.SetLocation(point);
            return Action.Ok;
        }
        if (buff.charAt(point.getY()) == 'L') {
            character.AddLife();
            character.SetLocation(point);
            return Action.Life;
        }
        if (buff.charAt(point.getY()) == 'K') {
            character.Kill();
            character.SetLocation(point);
            if (character.lifeCount < 0) {
                woodmansCatalog.remove(character.GetName());
                return Action.WoodmanNotFound;
            }

            return Action.Dead;
        }

        if (buff.charAt(point.getY()) == '1') {
            return Action.Fail;
        }
        return null;
    }
}


