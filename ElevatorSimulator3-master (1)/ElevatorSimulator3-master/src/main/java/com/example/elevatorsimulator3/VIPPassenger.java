package com.example.elevatorsimulator3;

public class VIPPassenger extends Passenger {
    private Building building; // Reference to the Building

    public VIPPassenger(int passengerID, int startFloor, int endFloor, Building building) {
        this.passengerID = passengerID;
        this.startFloor = startFloor;
        this.endFloor = endFloor;
        this.building = building; // Initialize the building reference
    }

    @Override
    public boolean requestElevator(direction _direction, SimulationSettings _settings) {
        int startFloor = getStartFloor();
        int endFloor = getEndFloor();

        //Checks if the direction is valid for VIP passengers
        if (_direction == direction.UP || _direction == direction.DOWN) {
            // Accesses elevators through Building instance
            for (Elevator elevator : building.getElevators()) {
                if (elevator instanceof ExpressElevator) {
                    //requests VIP elevator based on start and end floors
                    if (elevator.getCurrentFloor() == startFloor || elevator.getCurrentFloor() == endFloor) {
                        elevator.move(_direction); // Moves VIP elevator towards requested direction
                        System.out.println("VIP elevator requested for passenger ID: " + getPassengerID());
                        return true; // Returns true, elevator has been successfully requested
                    }
                }
            }
        }

        // If no elevator available or suitable for the request
        System.out.println("No available VIP elevator for passenger ID: " + getPassengerID());
        return false;
    }

    //methods for VIPPassenger
    public int getStartFloor() {
        return startFloor;
    }

    public int getEndFloor() {
        return endFloor;
    }

    public int getPassengerID() {
        return passengerID;
    }
}
