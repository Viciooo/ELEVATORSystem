package backend;

import java.util.Scanner;

public class Engine {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Pass number of elevators: ");
        ElevatorSystem elevatorSystem = new ElevatorSystem(scanner.nextInt());
        System.out.println();
        String stepPassed = "";
        System.out.println("To end simulation type - END");
        System.out.println("To go down from 10'th floor type - d10");
        System.out.println("In order to go up from floor 0 type - U0");
        System.out.println("To skip type - SKIP");
        System.out.println();

//        todo some bug occurred with input

        while(stepPassed != "END"){
            System.out.println("Pass the move:");
            stepPassed = scanner.next();
            if(stepPassed.startsWith("U") || stepPassed.startsWith("D")){
                if(stepPassed.substring(1).matches("-?(0|[1-9]\\d*)")){
                    int numberOfFloor = Integer.parseInt(stepPassed.substring(1));
                    if(stepPassed.startsWith("U")){
                        elevatorSystem.pickup(numberOfFloor,DirectionsOfElevator.UP);
                    }else{
                        elevatorSystem.pickup(numberOfFloor,DirectionsOfElevator.DOWN);
                    }
                    elevatorSystem.step();
                }
            }else if(stepPassed.equals("SKIP")){
                elevatorSystem.step();
            }else{
                System.out.println("I can not understand you, please retype what you really mean");
            }
        }

    }
}
