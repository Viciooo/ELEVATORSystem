package backend;

import java.util.ArrayList;

public class ElevatorSystem {
    private ArrayList<Elevator> elevators;

    public ElevatorSystem(int numberOfElevators) {
        this.elevators = new ArrayList<>();
        for (int i = 0; i < numberOfElevators; i++) {
            elevators.add(new Elevator(i));
        }
    }

    public ArrayList<ArrayList<Integer>> status() {
        ArrayList<ArrayList<Integer>> tmp = new ArrayList<>();
        for (Elevator elevator : elevators) {
            tmp.add(elevator.status());
        }
        return tmp;
    }

    public void pickup(int userPosition, DirectionsOfElevator directionOfUser) {
        Elevator bestSameDirection = null;
        Elevator bestRevertDirection = null;
        if (directionOfUser == DirectionsOfElevator.DOWN) {
            int distanceOfElevatorsGoingDownOrNone = Integer.MAX_VALUE;
            // Ustawiam odległość na max, bo chcę ją minimalizować jeśli winda jedzie w tą samą stronę lub jest wolna
            int distanceOfElevatorsGoingUp = -1;
            // Ustawiam odległość na -1 bo chcemy maksymalizować odległość od windy która jedzie w przeciwną stronę.
            // W przypadku gdy wiele wind jedzie do góry lepsza jest ta, która będzie dalej bo szybciej będzie zmuszona do zawrócenia,
            // bo chcę ją minimalizować jeśli winda jedzie w tą samą stronę lub jest wolna.

            for (Elevator elevator : elevators) {
                if (elevator.getCurrLevel() >= elevator.getFinalDestination()) {
                    if (distanceOfElevatorsGoingDownOrNone > elevator.getCurrLevel() - userPosition) {
                        distanceOfElevatorsGoingDownOrNone = elevator.getCurrLevel() - userPosition;
                        bestSameDirection = elevator;
                    }
                } else {
                    if (distanceOfElevatorsGoingUp < elevator.getCurrLevel() - userPosition) {
                        distanceOfElevatorsGoingUp = elevator.getCurrLevel() - userPosition;
                        bestRevertDirection = elevator;
                    }
                }
            }

            if (distanceOfElevatorsGoingDownOrNone < distanceOfElevatorsGoingUp) {
                bestSameDirection.addDestination(userPosition);
            } else {
                assert bestRevertDirection != null;
                bestRevertDirection.addDestination(userPosition);
            }

        } else {
            int distanceOfElevatorsGoingUpOrNone = Integer.MAX_VALUE;
            // Ustawiam odległość na max, bo chcę ją minimalizować jeśli winda jedzie w tą samą stronę lub jest wolna
            int distanceOfElevatorsGoingDown = -1;
            // Ustawiam odległość na -1 bo chcemy maksymalizować odległość od windy która jedzie w przeciwną stronę.
            // W przypadku gdy wiele wind jedzie do góry lepsza jest ta, która będzie dalej bo szybciej będzie zmuszona do zawrócenia,
            // bo chcę ją minimalizować jeśli winda jedzie w tą samą stronę lub jest wolna.

            for (Elevator elevator : elevators) {
                if (elevator.getCurrLevel() <= elevator.getFinalDestination()) {
                    if (distanceOfElevatorsGoingUpOrNone > elevator.getCurrLevel() - userPosition) {
                        distanceOfElevatorsGoingUpOrNone = elevator.getCurrLevel() - userPosition;
                        bestSameDirection = elevator;
                    }
                } else {
                    if (distanceOfElevatorsGoingDown < elevator.getCurrLevel() - userPosition) {
                        distanceOfElevatorsGoingDown = elevator.getCurrLevel() - userPosition;
                        bestRevertDirection = elevator;
                    }
                }
            }
            if (distanceOfElevatorsGoingUpOrNone < distanceOfElevatorsGoingDown) {
                bestSameDirection.addDestination(userPosition);
            } else {
                assert bestRevertDirection != null;
                bestRevertDirection.addDestination(userPosition);
            }
        }
    }


    public void step() {
        for (Elevator elevator : elevators) {
            elevator.step();
        }
    }

    public void printStep(){
        for(ArrayList<Integer> elevatorStatus: this.status()){
            System.out.println("Elevator nr "+ elevatorStatus.get(0) + " is at lvl "+elevatorStatus.get(1) + " and its final destination is "+elevatorStatus.get(2));
        }
    }
}
