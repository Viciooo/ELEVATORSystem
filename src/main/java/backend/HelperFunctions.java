package backend;

import java.util.ArrayList;

public class HelperFunctions {
    public static boolean isNumeric(String string){
        return string.matches("-?(0|[1-9]\\d*)");
    }
    public static int orderWithSmallestPosition(ArrayList<ElevatorOrder> integerArrayList){
        int tmp = Integer.MAX_VALUE;
        for(ElevatorOrder elevatorOrder:integerArrayList){
            tmp = Math.min(elevatorOrder.getUserPosition(),tmp);
        }
        return tmp;
    }
    public static int orderWithBiggestPosition(ArrayList<ElevatorOrder> integerArrayList){
        int tmp = Integer.MIN_VALUE;
        for(ElevatorOrder elevatorOrder:integerArrayList){
            tmp = Math.max(elevatorOrder.getUserPosition(),tmp);
        }
        return tmp;
    }
}
