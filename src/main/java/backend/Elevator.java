package backend;

import java.util.ArrayList;
import java.util.Scanner;

public class Elevator {
    private final int ID;
    private int currLevel;
    private ArrayList<Integer> selectedLevels;
    private int finalDestination;
    private boolean goingUp;

    public Elevator(int ID) {
        this.ID = ID;
        this.currLevel = 0;
        this.selectedLevels = new ArrayList<>();
        this.finalDestination = 0;
        this.goingUp = true;
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
        if(currLevel == finalDestination && !selectedLevels.isEmpty()){
            goingUp = false;
        }
        if(selectedLevels.isEmpty() || currLevel < HelperFunctions.smallestInt(selectedLevels)){
            goingUp = true;
            finalDestination = HelperFunctions.biggestInt(selectedLevels);
        }
        selectedLevels.add(lvl);
        if(goingUp){
            finalDestination = Math.max(finalDestination, lvl);
        }else{
            finalDestination = Math.min(finalDestination, lvl);
        }
        System.out.println(lvl + " and the finalDestination is "+ finalDestination + " currLvl is "+ currLevel);
    }

    public void openDoorsOrNot() {
        if (selectedLevels.contains(currLevel)) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Doors of elevator " + ID + " opened on " + currLevel);
            System.out.println("Please pass the floor user selected or type SKIP:");
            String input = scanner.next();
            while(true){
                if(HelperFunctions.isNumeric(input)){
                    addDestination(Integer.parseInt(input));
                    break;
                }else if (input.equals("SKIP")){
                    break;
                }else{
                    System.out.println("I don't understand you, please retype what you mean");
                    input = scanner.next();
                }
            }
            selectedLevels.remove(Integer.valueOf(currLevel));
            System.out.println("selected lvls "+ selectedLevels);
        }
    }

    public int getFinalDestination() {
        return finalDestination;
    }
}

