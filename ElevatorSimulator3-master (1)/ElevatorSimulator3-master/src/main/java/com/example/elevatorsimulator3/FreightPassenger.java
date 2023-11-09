package com.example.elevatorsimulator3;

public class FreightPassenger extends Passenger {
    private Building building; // Reference to the Building
    public FreightPassenger(int passengerID, int startFloor, int endFloor, Building building) {
        this.passengerID = passengerID;
        this.startFloor = startFloor;
        this.endFloor = endFloor;
        this.building = building; // Initialize building reference
    }

    @Override
    public boolean requestElevator(direction _direction, SimulationSettings _settings) {
        int startFloor = getStartFloor();
        int endFloor = getEndFloor();

        // Checks if the direction is valid for freight elevator
        if (_direction == direction.UP || _direction == direction.DOWN) {
            // Accesses elevators through the Building instance
            for (Elevator elevator : building.getElevators()) {
                if (elevator instanceof FreightElevator) {
                    //requests a freight elevator based on start and end floors
                    if (elevator.getCurrentFloor() == startFloor || elevator.getCurrentFloor() == endFloor) {
                        elevator.move(_direction); // Moves the freight elevator towards the requested direction
                        System.out.println("Freight elevator requested for passenger ID: " + getPassengerID());
                        return true; // Returns true if the elevator is successfully requested
                    }
                }
            }
        }

        // If no elevator is available for the request
        System.out.println("No available freight elevator for passenger ID: " + getPassengerID());
        return false;
    }

    //methods for FreightPassenger
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
