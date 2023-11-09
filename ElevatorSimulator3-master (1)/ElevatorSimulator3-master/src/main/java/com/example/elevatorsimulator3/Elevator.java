package com.example.elevatorsimulator3;

import java.util.ArrayList;

enum direction{
    UP,
    IDLE,
    DOWN
}
public abstract class Elevator {
    protected int elevatorID;
    protected String elevatorType;

    protected int currentFloor;
    protected direction direction;
    private int maxCapacity; // Maximum capacity of the elevator
    protected ArrayList<Passenger> passengers; // List of passengers in the elevator

    public boolean move(direction _direction) {
        if (this.direction == direction.IDLE) {
            return true; // If the elevator is idle, it can adjust to any direction
        } else if (this.direction == _direction) {
            return true; // If it's moving in the same direction as the requested direction, it can adjust
        } else {
            return false; // Otherwise, it cannot adjust the requested direction
        }
    }

    public Elevator(int elevatorID, String elevatorType) {
        this.elevatorID = elevatorID;
        this.elevatorType = elevatorType;
        this.currentFloor = 1; // Assuming the elevator starts at ground floor
        this.direction = direction.IDLE; // Idle state at the beginning
        this.passengers = new ArrayList<>();
    }

    // Method to get passengers currently inside the elevator
    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    public Elevator(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.passengers = new ArrayList<>();
    }

    // Method to calculate and return the current load of the elevator
    public int getCurrentLoad() {
        return passengers.size(); // Assuming the load is based on the number of passengers

    }

    // Method to check if the elevator can have more passengers
    public boolean canAccommodateMorePassengers() {
        return passengers.size() < maxCapacity; // Return true if the elevator can have more passengers
    }
    // Method to add a passenger to the elevator
    public void addPassenger(Passenger passenger) {
        if (canAccommodateMorePassengers()) {
            passengers.add(passenger);
        } else {
            // Handle the case when the elevator is at full capacity
            System.out.println("Elevator is at full capacity. Cannot add more passengers.");
        }
    }

    public void removePassenger(Passenger passenger) {

        passengers.remove(passenger);
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }
}
