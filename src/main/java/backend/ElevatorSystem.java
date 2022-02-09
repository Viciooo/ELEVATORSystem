package backend;
import java.util.ArrayList;

public class ElevatorSystem {
    private final Elevator[] elevators;

    public ElevatorSystem(Elevator[] elevators) {
        this.elevators = elevators;
    }

    public ArrayList<ArrayList<Integer>> status(){
        ArrayList<ArrayList<Integer>> tmp = new ArrayList<>();
        for(Elevator elevator: elevators){
            tmp.add(elevator.status());
        }
        return tmp;
    }

    public void step(){

    }
}
