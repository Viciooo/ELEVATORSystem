package backend;

import java.util.ArrayList;

public class HelperFunctions {
    public static boolean isNumeric(String string) {
        return string.matches("-?(0|[1-9]\\d*)");
    }

    public static int orderWithSmallestPositionInThisDirection(ArrayList<ElevatorOrder> integerArrayList, ElevatorDirection elevatorDirection) {
        int tmp = Integer.MAX_VALUE;
        for (ElevatorOrder elevatorOrder : integerArrayList) {
            if (elevatorOrder.getUserDirection() == elevatorDirection) {
                tmp = Math.min(elevatorOrder.getUserPosition(), tmp);
            }
        }
        return tmp;
    }

    public static int orderWithBiggestPositionInThisDirection(ArrayList<ElevatorOrder> integerArrayList, ElevatorDirection elevatorDirection) {
        int tmp = Integer.MIN_VALUE;
        for (ElevatorOrder elevatorOrder : integerArrayList) {
            if (elevatorOrder.getUserDirection() == elevatorDirection) {
                tmp = Math.max(elevatorOrder.getUserPosition(), tmp);
            }
        }
        return tmp;
    }
}
