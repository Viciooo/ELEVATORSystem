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
        if (currLevel < finalDestination) {
            currLevel++;
            openDoorsOrNot();
        } else if (currLevel > finalDestination) {
            currLevel--;
            openDoorsOrNot();
        } else if (elevatorDirection == ElevatorDirection.DOWN) {
            finalDestination = HelperFunctions.orderWithSmallestPositionInThisDirection(elevatorOrders, elevatorDirection);
            if (finalDestination == Integer.MAX_VALUE && !elevatorOrders.isEmpty()) {
                elevatorDirection = ElevatorDirection.UP;
                finalDestination = HelperFunctions.orderWithBiggestPositionInThisDirection(elevatorOrders, elevatorDirection);
                currLevel++;
            } else {
                currLevel--;
            }
            openDoorsOrNot();
        } else if (elevatorDirection == ElevatorDirection.UP) {
            finalDestination = HelperFunctions.orderWithBiggestPositionInThisDirection(elevatorOrders, elevatorDirection);
            if (finalDestination == Integer.MIN_VALUE && !elevatorOrders.isEmpty()) {
                elevatorDirection = ElevatorDirection.DOWN;
                finalDestination = HelperFunctions.orderWithSmallestPositionInThisDirection(elevatorOrders, elevatorDirection);
                currLevel--;
            } else {
                currLevel++;
            }
            openDoorsOrNot();
        }
    }

    public ElevatorDirection createDirection(int userDirection, int currPosition) {
        if (userDirection < currPosition) {
            return ElevatorDirection.DOWN;
        } else if (userDirection > currPosition) {
            return ElevatorDirection.UP;
        } else {
            return ElevatorDirection.NONE;
        }
    }

    public void addDestination(ElevatorOrder elevatorOrder) {
        if (currLevel == finalDestination && elevatorOrder.getUserDirection() == ElevatorDirection.DOWN) {
            elevatorDirection = ElevatorDirection.DOWN;
        }
        if (currLevel == finalDestination && elevatorOrder.getUserDirection() == ElevatorDirection.UP) {
            elevatorDirection = ElevatorDirection.UP;
        }
        if (!checkIfOrderIsInList(elevatorOrder.getUserPosition())) {
            elevatorOrders.add(elevatorOrder);
            finalDestination = Math.max(finalDestination, elevatorOrder.getUserPosition());
        }
    }

    public void openDoorsOrNot() {
        if (((currLevel == HelperFunctions.orderWithBiggestPositionInThisDirection(elevatorOrders, elevatorDirection)) && elevatorDirection == ElevatorDirection.DOWN) || (elevatorDirection == ElevatorDirection.UP && (currLevel == HelperFunctions.orderWithSmallestPositionInThisDirection(elevatorOrders, elevatorDirection)))) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Doors of elevator " + ID + " opened on " + currLevel);
            System.out.println("Please pass the floor user selected or type SKIP:");
            String input = scanner.next();
            while (true) {
                if (HelperFunctions.isNumeric(input)) {
                    addDestination(new ElevatorOrder(Integer.parseInt(input), createDirection(Integer.parseInt(input), currLevel)));
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
            if (elevatorOrder.getUserPosition() == position) {
                return true;
            }
        }
        return false;
    }

    public void getInfo() {
        System.out.println("_____________________________");
        System.out.println("Elevator nr " + ID + " is at " + currLevel);
        System.out.println("It has following orders: ");
        System.out.println(elevatorOrders.toString());
        System.out.println("Final destination is floor nr " + finalDestination);
        System.out.println("Direction: " + elevatorDirection);
        System.out.println("_____________________________");
    }

    public ElevatorDirection getElevatorDirection() {
        return elevatorDirection;
    }
}

