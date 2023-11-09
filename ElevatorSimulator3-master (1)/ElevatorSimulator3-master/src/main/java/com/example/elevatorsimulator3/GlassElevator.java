package com.example.elevatorsimulator3;

public class GlassElevator extends Elevator{
    private int weightCapacity;

    public GlassElevator(int elevatorID, String elevatorType, int weightCapacity) {
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
        //movement of the GlassElevator
        if (_direction == direction.UP) {
            //moving the elevator up
            int currentFloor = getCurrentFloor();
            setCurrentFloor(currentFloor + 1);
            System.out.println("GlassElevator moving up to floor: " + getCurrentFloor());
            //Checks floors, pick up/drop passengers, etc.
        } else if (_direction == direction.DOWN) {
            //moving the elevator down
            int currentFloor = getCurrentFloor();
            setCurrentFloor(currentFloor - 1);
            System.out.println("GlassElevator moving down to floor: " + getCurrentFloor());
            //Checks floors, pick up/drop passengers, etc.
        } else {
            System.out.println("Incorrect Input");
            return false;
        }
        return true;
    }
}
