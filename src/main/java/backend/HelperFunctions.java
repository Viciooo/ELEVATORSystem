package backend;

import java.util.ArrayList;

public class HelperFunctions {
    public static boolean isNumeric(String string){
        return string.matches("-?(0|[1-9]\\d*)");
    }
    public static int smallestInt(ArrayList<Integer> integerArrayList){
        int tmp = Integer.MAX_VALUE;
        for(Integer integer:integerArrayList){
            tmp = Math.min(integer,tmp);
        }
        return tmp;
    }
    public static int biggestInt(ArrayList<Integer> integerArrayList){
        int tmp = Integer.MIN_VALUE;
        for(Integer integer:integerArrayList){
            tmp = Math.max(integer,tmp);
        }
        return tmp;
    }
}
