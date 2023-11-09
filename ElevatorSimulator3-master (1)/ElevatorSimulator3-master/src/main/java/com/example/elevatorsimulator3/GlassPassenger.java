package com.example.elevatorsimulator3;

public class GlassPassenger extends Passenger {
    private Building building; // Reference to the Building
    public GlassPassenger(int passengerID, int startFloor, int endFloor, Building building) {
        this.passengerID = passengerID;
        this.startFloor = startFloor;
        this.endFloor = endFloor;
        this.building = building; // Initialize the building reference
    }

    @Override
    public boolean requestElevator(direction _direction, SimulationSettings _settings) {
        //Checks the direction and the floors, and request an elevator accordingly
        if (_direction == direction.UP) { //requesting a glass elevator to go up
            // Based on startFloor and endFloor, requesting the glass elevator.
            int startFloor = getStartFloor();
            int endFloor = getEndFloor();

            //if the elevators can only move in one direction at a time
            if (startFloor < endFloor) {
                // Request glass elevator to go up if the end floor is higher than the start floor
                Elevator requestedElevator = findAvailableGlassElevator(startFloor);
                if (requestedElevator != null) {
                    System.out.println("Glass elevator requested for passenger ID: " + getPassengerID() +
                            " to go up from floor " + startFloor + " to floor " + endFloor);
                    return true; // Return true elevator has been successfully requested
                }
            }
        } else if (_direction == direction.DOWN) {
            //requesting glass elevator to go down
            //Based on startFloor and endFloor, requesting the glass elevator.
            int startFloor = getStartFloor();
            int endFloor = getEndFloor();

            // Assume elevators can only move in one direction at a time
            if (startFloor > endFloor) {
                // Request glass elevator to go down if the start floor is higher than the end floor
                Elevator requestedElevator = findAvailableGlassElevator(startFloor);
                if (requestedElevator != null) {
                    System.out.println("Glass elevator requested for passenger ID: " + getPassengerID() +
                            " to go down from floor " + startFloor + " to floor " + endFloor);
                    return true; // Returns true elevator successfully requested
                }
            }
        } else {
            System.out.println("Incorrect Input");
            return false;
        }

        System.out.println("No available glass elevator for passenger ID: " + getPassengerID());
        return false;
    }

    private Elevator findAvailableGlassElevator(int startFloor) {
        for (Elevator elevator : building.getElevators()) {
            if (elevator instanceof GlassElevator && elevator.getCurrentFloor() == startFloor) {
                return elevator;
            }
        }
        return null;
    }

    //methods for GlassPassenger
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
