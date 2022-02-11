package backend;

import java.util.ArrayList;

public class ElevatorSystem {
    private ArrayList<Elevator> elevators;
    private ArrayList<ElevatorOrder> unassignedOrders;

    public ElevatorSystem(int numberOfElevators) {
        this.elevators = new ArrayList<>();
        for (int i = 0; i < numberOfElevators; i++) {
            elevators.add(new Elevator(i));
        }
        this.unassignedOrders = new ArrayList<>();
    }

    public ArrayList<ArrayList<Integer>> status() {
        ArrayList<ArrayList<Integer>> tmp = new ArrayList<>();
        for (Elevator elevator : elevators) {
            tmp.add(elevator.status());
        }
        return tmp;
    }

    public void tryPickupForUnassigned() {
        for (ElevatorOrder elevatorOrder : unassignedOrders) {
            pickup(elevatorOrder);
        }
    }


    public void pickup(ElevatorOrder elevatorOrder) {
        ElevatorDirection userDirection = elevatorOrder.getUserDirection();
        int userPosition = elevatorOrder.getUserPosition();
        Elevator bestElevator = null;
        int distanceBetweenUserAndElevator = Integer.MAX_VALUE;

//        chcemy minimalizować odległość lokalizacji windy:
//        - jeśli winda jedzie w tą stronę co my chcemy to minimalizujemy odległość obecnej lokalizacji windy
//        - jesli jedzie w przeciwną stronę to minimalizujemy odległość finalDestination, która zawsze jest najniższym/najwyższym piętrem do którego jedzie winda
//        - jeśli winda stoji w miejscu to w zasadzie obojętne bo wtedy obecna lokalizacja i finalna są sobie równe

        if (userDirection == ElevatorDirection.DOWN) {

            for (Elevator elevator : elevators) {
                if (((elevator.getElevatorDirection() == ElevatorDirection.DOWN || elevator.getElevatorDirection() == ElevatorDirection.NONE) && distanceBetweenUserAndElevator > elevator.getCurrLevel() - userPosition)) {
                    distanceBetweenUserAndElevator = Math.abs(elevator.getCurrLevel() - userPosition);
                    bestElevator = elevator;
                }
            }
        } else {
            for (Elevator elevator : elevators) {
                if ((elevator.getElevatorDirection() == ElevatorDirection.UP || elevator.getElevatorDirection() == ElevatorDirection.NONE) && distanceBetweenUserAndElevator > elevator.getCurrLevel() - userPosition) {
                    distanceBetweenUserAndElevator = elevator.getCurrLevel() - userPosition;
                    bestElevator = elevator;
                }
            }
        }
        if (bestElevator == null) {
            if (!unassignedOrders.contains(elevatorOrder)) {
                unassignedOrders.add(elevatorOrder);
                System.out.println("i was not assigned " + elevatorOrder.toString());
            }
        } else {
            bestElevator.addDestination(elevatorOrder);
        }
    }


    public void step() {
        for (Elevator elevator : elevators) {
            elevator.step();
        }
    }

    public void printStep() {
        for (ArrayList<Integer> elevatorStatus : this.status()) {
            System.out.println("Elevator nr " + elevatorStatus.get(0) + " is at lvl " + elevatorStatus.get(1) + " and its final destination is " + elevatorStatus.get(2));
        }
    }

    public void showAll() {
        System.out.println("$$$$$$$$$$$$$$$$$$");
        for (Elevator elevator : elevators) {
            elevator.getInfo();
        }
        System.out.println("$$$$$$$$$$$$$$$$$$");
    }


}
