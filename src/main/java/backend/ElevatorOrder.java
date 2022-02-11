package backend;

public class ElevatorOrder {
    private final ElevatorDirection userDirection;
    private final int userPosition;

    public ElevatorOrder(int whereIsUser, ElevatorDirection upOrDown) {
        this.userDirection = upOrDown;
        this.userPosition = whereIsUser;
    }

    public ElevatorDirection getUserDirection() {
        return userDirection;
    }

    public int getUserPosition() {
        return userPosition;
    }

    public String toString() {
        return "[ position: " + userPosition + ", direction: " + userDirection + " ]";
    }
}
