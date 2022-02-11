package backend;

import java.util.ArrayList;
import java.util.Scanner;

public class Elevator {
    private final int ID;
    private int currLevel;
    private ArrayList<ElevatorOrder> elevatorOrders;
    private int finalDestination;
    private ElevatorDirection elevatorDirection;

    public Elevator(int ID) {
        this.ID = ID;
        this.currLevel = 0;
        this.elevatorOrders = new ArrayList<>();
        this.finalDestination = 0;
        this.elevatorDirection = ElevatorDirection.NONE;
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
        if (elevatorDirection == ElevatorDirection.UP) {
            currLevel++;
            openDoorsOrNot();
        } else if (elevatorDirection == ElevatorDirection.DOWN) {
            currLevel--;
            openDoorsOrNot();
        }
    }

    public ElevatorDirection getDirection(int userDirection, int currPosition) {
        if (userDirection < currPosition) {
            return ElevatorDirection.DOWN;
        } else if (userDirection > currPosition) {
            return ElevatorDirection.UP;
        } else {
            return ElevatorDirection.NONE;
        }
    }

    public void addDestination(ElevatorOrder elevatorOrder) {
        if (currLevel == finalDestination && !elevatorOrders.isEmpty()) {
            elevatorDirection = ElevatorDirection.DOWN;
        }
        if (!elevatorOrders.isEmpty() || currLevel < HelperFunctions.orderWithSmallestPosition(elevatorOrders)) {
            elevatorDirection = ElevatorDirection.UP;
            if (!elevatorOrders.isEmpty()) {
                finalDestination = HelperFunctions.orderWithBiggestPosition(elevatorOrders);
            }
        }
        if (!checkIfOrderIsInList(elevatorOrder.getUserPosition())) {
            elevatorOrders.add(elevatorOrder);
            if (elevatorDirection == ElevatorDirection.UP) {
                finalDestination = Math.max(finalDestination, elevatorOrder.getUserPosition());
            } else {
                finalDestination = Math.min(finalDestination, elevatorOrder.getUserPosition());
            }
        }

        System.out.println("Elevator nr " + ID + " is at " + currLevel + " lvl and the finalDestination is " + finalDestination + " lvl, lvl that was added is  " + elevatorOrder.getUserPosition());
    }

    public void openDoorsOrNot() {
        if (((currLevel == HelperFunctions.orderWithBiggestPosition(elevatorOrders)) && elevatorDirection == ElevatorDirection.DOWN) || (elevatorDirection == ElevatorDirection.UP && (currLevel == HelperFunctions.orderWithSmallestPosition(elevatorOrders)))) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Doors of elevator " + ID + " opened on " + currLevel);
            System.out.println("Please pass the floor user selected or type SKIP:");
            String input = scanner.next();
            while (true) {
                if (HelperFunctions.isNumeric(input)) {
                    addDestination(new ElevatorOrder(Integer.parseInt(input), getDirection(Integer.parseInt(input), currLevel)));
                    break;
                } else if (input.equals("SKIP")) {
                    break;
                } else {
                    System.out.println("I don't understand you, please retype what you mean");
                    input = scanner.next();
                }
            }
            removeOrderWithPosition(currLevel);
            if (elevatorOrders.isEmpty()) {
                elevatorDirection = ElevatorDirection.NONE;
            }
            System.out.println("#####################");
            for (ElevatorOrder elevatorOrder : elevatorOrders) {
                System.out.println(elevatorOrder.toString());
            }
            System.out.println("#####################");
        }
    }

    public void removeOrderWithPosition(int position) {
        for (ElevatorOrder elevatorOrder : elevatorOrders) {
            if (elevatorOrder.getUserPosition() == position) {
                elevatorOrders.remove(elevatorOrder);
                return;
            }
        }
    }

    public int getFinalDestination() {
        return finalDestination;
    }

    public boolean checkIfOrderIsInList(int position) {
        for (ElevatorOrder elevatorOrder : elevatorOrders) {
            if (elevatorOrder.getUserPosition() == position){
                return true;
            }
        }
        return false;
    }

    public void getInfo(){
        System.out.println("Elevator nr " + ID + " is at " + currLevel);
        System.out.println("It has following orders: ");
        System.out.println(elevatorOrders.toString());
        System.out.println("Final destination is floor nr "+finalDestination);
        System.out.println("Direction: "+elevatorDirection);
    }
}

