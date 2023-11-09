package com.example.elevatorsimulator3;

public class ExpressElevator extends Elevator{
    public ExpressElevator(int elevatorID, String elevatorType) {
        super(elevatorID, elevatorType);
    }
    @Override
    public boolean move(direction _direction) {
        //the movement of the ExpressElevator
        if (_direction == direction.UP) {
            // move elevator up
            int currentFloor = getCurrentFloor();
            setCurrentFloor(currentFloor + 1);
            System.out.println("ExpressElevator moving up to floor: " + getCurrentFloor());
            //Checks floors, pick up/drop passengers, etc.
        } else if (_direction == direction.DOWN) {
            //move elevator down
            int currentFloor = getCurrentFloor();
            setCurrentFloor(currentFloor - 1);
            System.out.println("ExpressElevator moving down to floor: " + getCurrentFloor());
            //Check floors, pick up/drop passengers, etc.
        } else if (_direction == direction.IDLE) {
            //idle state, waiting for new requests
            System.out.println("ExpressElevator is idle");
            //handles idle state
        } else {
            System.out.println("Incorrect Input");
            return false;
        }
        return true;
    }
}
