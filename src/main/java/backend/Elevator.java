package backend;

import java.util.ArrayList;

public class Elevator {
    private int ID;
    private int currLevel;
    private ArrayList<Integer> selectedLevels;
    private int finalDestination;
    private int upsCounter;
    private int downsCounter;

    public Elevator(int ID) {
        this.ID = ID;
        this.currLevel = 0;
        this.selectedLevels = new ArrayList<>();
        this.finalDestination = 0;
        this.upsCounter = 0;
        this.downsCounter = 0;
    }

    public void update(){

    }

    public void pickup(int numOfFloorWhereBtnPressed,boolean goUp){
        if(goUp){
            upsCounter++;
        }else {
            downsCounter++;
        }
        selectedLevels.add(numOfFloorWhereBtnPressed);
    }

    public ArrayList<Integer> status(){
        ArrayList<Integer> tmp = new ArrayList<>();
        tmp.add(ID);
        tmp.add(currLevel);
        tmp.add(finalDestination);
        return tmp;
    }

    public void step(){

    }
}

