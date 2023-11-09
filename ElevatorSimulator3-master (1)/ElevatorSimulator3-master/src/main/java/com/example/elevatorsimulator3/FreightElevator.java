package com.example.elevatorsimulator3;

public class FreightElevator extends Elevator {
    private int weightCapacity;

    public FreightElevator(int elevatorID, String elevatorType, int weightCapacity) {
        super(elevatorID, elevatorType);
        this.weightCapacity = weightCapacity;
    }

    public int getWeightCapacity() {
        return weightCapacity;
    }

    public void setWeightCapacity(int weightCapacity) {
        this.weightCapacity = weightCapacity;
    }
    @Override
    public boolean move(direction _direction) {
        //movement of the FreightElevator
        if (_direction == direction.UP) {
            //moving the elevator up
            int currentFloor = getCurrentFloor();
            setCurrentFloor(currentFloor + 1);
            System.out.println("FreightElevator moving up to floor: " + getCurrentFloor());
            //Checks floors, pick up/drop freight passengers, etc.
        } else if (_direction == direction.DOWN) {
            //moving the elevator down
            int currentFloor = getCurrentFloor();
            setCurrentFloor(currentFloor - 1);
            System.out.println("FreightElevator moving down to floor: " + getCurrentFloor());
            //Checks floors, pick up/drop freight passengers, etc.
        } else if (_direction == direction.IDLE) {
            //idle state, waiting for new freight requests
            System.out.println("FreightElevator is idle");
            //handles the idle state
        } else {
            System.out.println("Incorrect Input");
            return false;
        }
        return true;
    }
}

