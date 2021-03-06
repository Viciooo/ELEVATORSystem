import java.util.Objects;
import java.util.Scanner;

public class Engine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        while(true){
            System.out.println("Pass number of elevators: ");
            input = scanner.next();
            if(HelperFunctions.isNumeric(input) && Integer.parseInt(input) > 0){
                break;
            }else{
                System.out.println("Passed number of elevators is either not correct or is not really a number");
            }
        }
        ElevatorSystem elevatorSystem = new ElevatorSystem(Integer.parseInt(input));


        System.out.println();
        String stepPassed = "";
        System.out.println("Type HELP for more info");
        System.out.println("Type START to start simulation");
        while (!stepPassed.equals("START")) {
            stepPassed = scanner.next().toUpperCase();
            if (Objects.equals(stepPassed, "HELP")) {
                help();
                System.out.println("Type START to start simulation");
            } else if (!Objects.equals(stepPassed, "START")) {
                System.out.println("Such command does not exist, try again");
            }
        }

        while (!stepPassed.equals("END")) {
            System.out.println("Pass the move:");
            stepPassed = scanner.next().toUpperCase();
            if (stepPassed.startsWith("U") || stepPassed.startsWith("D")) {
                if (HelperFunctions.isNumeric(stepPassed.substring(1))) {
                    int numberOfFloor = Integer.parseInt(stepPassed.substring(1));
                    if (stepPassed.startsWith("U")) {
                        elevatorSystem.pickup(new ElevatorOrder(numberOfFloor, ElevatorDirection.UP));
                    } else {
                        elevatorSystem.pickup(new ElevatorOrder(numberOfFloor, ElevatorDirection.DOWN));
                    }
                    elevatorSystem.step();
                    elevatorSystem.printStep();
                }
            } else if (stepPassed.equals("SKIP")) {
                elevatorSystem.step();
                elevatorSystem.printStep();

            } else if (stepPassed.equals("SHOW-ALL")) {
                elevatorSystem.showAll();
                elevatorSystem.step();
                elevatorSystem.printStep();
            } else {
                System.out.println("Command does not exist, please retype what You meant");
            }
            elevatorSystem.tryPickupForUnassigned();
        }

    }

    private static void help() {
        System.out.println("To end simulation type - END");
        System.out.println("To go down from 10'th floor type - D10");
        System.out.println("In order to go up from floor 0 type - U0");
        System.out.println("To skip type - SKIP");
        System.out.println("To skip and show all data about system type - SHOW-ALL");
        System.out.println();
    }
}
