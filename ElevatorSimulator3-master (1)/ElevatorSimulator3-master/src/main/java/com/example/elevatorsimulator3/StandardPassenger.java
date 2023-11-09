package com.example.elevatorsimulator3;

public class StandardPassenger extends Passenger {
    private Building building; // Reference to the Building
    public StandardPassenger(int passengerID, int startFloor, int endFloor, Building building) {
        this.passengerID = passengerID;
        this.startFloor = startFloor;
        this.endFloor = endFloor;
        this.building = building; // Initialize the building reference
    }
    @Override
    public boolean requestElevator(direction _direction, SimulationSettings _settings) {
        //Checks the direction and the floors, and requests an elevator
        if (_direction == direction.UP) {
            //requesting an elevator to go up
            // Based on startFloor and endFloor, request the elevator.
            int startFloor = getStartFloor();
            int endFloor = getEndFloor();

            if (startFloor < endFloor) {
                // Request an elevator to go up if the end floor is higher than the start floor
                Elevator requestedElevator = findAvailableElevator(startFloor);
                if (requestedElevator != null) {
                    // asume the elevator is available and can accommodate the request
                    System.out.println("Standard passenger requested an elevator to go up from floor " + startFloor + " to floor " + endFloor);
                    return true; // Return true if an elevator is successfully requested.
                }
            }
        } else if (_direction == direction.DOWN) {
            //requesting an elevator to go down
            // Based on startFloor and endFloor, request elevator
            int startFloor = getStartFloor();
            int endFloor = getEndFloor();

            if (startFloor > endFloor) {
                // Request an elevator to go down if the start floor is higher than the end floor
                Elevator requestedElevator = findAvailableElevator(startFloor);
                if (requestedElevator != null) {
                    // assume the elevator is available and can accommodate the request
                    System.out.println("Standard passenger requested an elevator to go down from floor " + startFloor + " to floor " + endFloor);
                    return true; // Return true if an elevator is successfully requested.
                }
            }
        } else {
            System.out.println("Incorrect Input");
            return false;
        }

        System.out.println("No available elevator for the Standard passenger request");
        return false; // Return false if no elevator is requested.
    }

    private Elevator findAvailableElevator(int startFloor) {
        //finds an available elevator at the specified floor
        for (Elevator elevator : building.getElevators()) {
            // Checks if the elevator can be assigned based on current floor and availability
            if (elevator.getCurrentFloor() == startFloor && elevator.canAccommodateMorePassengers()) {
                return elevator;
            }
        }
        return null;
    }

    //methods for StandardPassenger
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
