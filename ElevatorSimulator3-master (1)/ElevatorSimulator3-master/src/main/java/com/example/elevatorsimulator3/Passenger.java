package com.example.elevatorsimulator3;

public abstract class Passenger {
    protected int passengerID;
    protected int startFloor;
    protected int endFloor;
    public abstract boolean requestElevator(direction _direction, SimulationSettings _settings);
    // Getter method for startFloor
    public int getStartFloor() {
        return startFloor;
    }
    // Getter method for endFloor
    public int getEndFloor() {
        return endFloor;
    }
}

