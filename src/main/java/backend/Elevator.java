package backend;

import java.util.ArrayList;

public class Elevator {
    private final int ID;
    private int currLevel;
    private ArrayList<Integer> selectedLevels;
    private int finalDestination;

    public Elevator(int ID) {
        this.ID = ID;
        this.currLevel = 0;
        this.selectedLevels = new ArrayList<>();
        this.finalDestination = 0;
    }

    public ArrayList<Integer> status() {
        ArrayList<Integer> tmp = new ArrayList<>();
        tmp.add(ID);
        tmp.add(currLevel);
        tmp.add(finalDestination);
        return tmp;
    }

    public int getCurrLevel() {
        return currLevel;
    }

    public void step() {
        if (currLevel < finalDestination) {
            currLevel++;
            openDoorsOrNot();
        }else if(currLevel > finalDestination){
            currLevel--;
            openDoorsOrNot();
        }
    }

    public void addDestination(int lvl) {
        this.selectedLevels.add(lvl);
        finalDestination = Math.max(finalDestination, lvl);
    }

    public void openDoorsOrNot() {
        if (selectedLevels.contains(currLevel)) {
            System.out.println("Doors of elevator" + ID + " opened on " + currLevel);
            selectedLevels.remove(currLevel);
        }
    }

    public int getFinalDestination() {
        return finalDestination;
    }
}

