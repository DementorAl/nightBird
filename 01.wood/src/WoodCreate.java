/**
 * Created by Алексей on 28.02.14.
 */
public class WoodCreate implements Woodman {


    public String nameOfWoodman;
    public Point currentPosition;
    public Point start;
    public int lifeCount;


    public WoodCreate(String name, Point start) {

        this.nameOfWoodman = name;
        this.start = this.currentPosition = start;
        this.lifeCount = 3;


    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof WoodCreate) {
            if ((this.nameOfWoodman.equals(((WoodCreate) obj).nameOfWoodman)) || this.currentPosition.equals(((WoodCreate) obj).currentPosition)) {
                return true;
            } else return false;
        }
        return false;
    }

    @Override
    public int GetLifeCount() {
        return lifeCount;
    }

    @Override
    public String GetName() {
        return nameOfWoodman;
    }

    @Override
    public boolean Kill() {
        if (lifeCount >= 0) {
            lifeCount = lifeCount - 1;
            if (lifeCount >= 0) {
                return true;
            } else return false;
        } else
            return false;
    }

    @Override
    public void AddLife() {
        lifeCount = lifeCount + 1;

    }

    @Override
    public Point GetLocation() {
        return currentPosition;
    }

    @Override
    public void SetLocation(Point location) {
        currentPosition = location;

    }

    @Override
    public void MoveToStart() {
        currentPosition = start;

    }

}
