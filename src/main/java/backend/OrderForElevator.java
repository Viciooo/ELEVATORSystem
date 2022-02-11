package backend;

public class OrderForElevator {
    private final ElevatorDirection userDirection;
    private final int userPosition;

    public OrderForElevator(int whereIsUser, ElevatorDirection upOrDown) {
        this.userDirection = upOrDown;
        this.userPosition = whereIsUser;
    }

    public ElevatorDirection getUserDirection() {
        return userDirection;
    }

    public int getUserPosition() {
        return userPosition;
    }
}
