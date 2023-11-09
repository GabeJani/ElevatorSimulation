package com.example.elevatorsimulator3;

import javafx.beans.property.SimpleMapProperty;

import java.io.*;
import java.io.FileReader;

public class ElevatorSimulation {
    SimulationSettings _simulationSettings = new SimulationSettings();
    private Building building;

    public boolean initSimulation(String ElevatorSimulationInfo) {
        _simulationSettings = readSettingsContent(ElevatorSimulationInfo); // replace filename
        building = new Building(_simulationSettings);
        runSimulation(_simulationSettings);
        return true;
    }

    private SimulationSettings readSettingsContent(String fileName) {
        SimulationSettings currentSettings = new SimulationSettings();
        // Read Information from file and populate the currentSettings
        return currentSettings;
    }

    private boolean runSimulation(SimulationSettings simulationSettings) {
        int iterations = simulationSettings.run_simulation;

        for (int i = 0; i < iterations; i++) {
            simulateElevatorMovement();
            simulatePassengerActions();
        }

        return true;
    }

    private direction determineElevatorDirection(Elevator elevator) {
        int currentFloor = elevator.getCurrentFloor();

        if (elevator.getCurrentLoad() == 0) {
            return direction.IDLE; // If the elevator has no passengers, set it to IDLE
        } else {
            // if the elevator has passengers, this will show the direction based on the destinations
            boolean goingUp = false;
            boolean goingDown = false;

            for (Passenger passenger : elevator.getPassengers()) {
                int passengerDestination = passenger.getEndFloor();
                if (passengerDestination > currentFloor) {
                    goingUp = true; // At least one passenger wants to go up
                } else if (passengerDestination < currentFloor) {
                    goingDown = true; // At least one passenger wants to go down
                }
            }

            if (goingUp && goingDown) {
                return direction.IDLE; // If some passengers want to go up and others down, set to IDLE
            } else if (goingUp) {
                return direction.UP; // If passenger wants to go up, set direction UP
            } else if (goingDown) {
                return direction.DOWN; // If passenger wants to go down, set direction to DOWN
            } else {
                return direction.IDLE; // Default IDLE if no clear direction is shown
            }
        }
    }

    private void simulateElevatorMovement() {
        // Move each elevator in the building
        for (Elevator elevator : building.getElevators()) {
            // Determined direction for current elevator
            direction elevatorDirection = determineElevatorDirection(elevator);

            // Called move method with the determined direction
            elevator.move(elevatorDirection);
        }
    }

    private void simulatePassengerActions() {
        for (Floor floor : building.getFloors()) {
            // Simulates passenger requests on each floor
            for (Passenger passenger : floor.getWaitQueue()) {
                direction passengerDirection = determinePassengerDirection(passenger);
                boolean success = passenger.requestElevator(passengerDirection, _simulationSettings);

                if (success) {
                    // for when an elevator is requested
                    Elevator requestedElevator = building.assignElevator(passenger, passengerDirection);
                    if (requestedElevator != null) {
                        requestedElevator.addPassenger(passenger);
                        floor.removeFromWaitQueue(passenger);
                    }
                }
            }
        }
    }

    private direction determinePassengerDirection(Passenger passenger) {
        //the direction passenger wants to travel
        int startFloor = passenger.getStartFloor();
        int endFloor = passenger.getEndFloor();

        if (startFloor < endFloor) {
            return direction.UP;
        } else if (startFloor > endFloor) {
            return direction.DOWN;
        } else {
            return direction.IDLE;
        }
    }
    public static void main(String[] args) {

        try {
            FileReader reader = new FileReader("elevatorInput.txt");
            int data = reader.read();
            while(data != -1){
                System.out.print((char)data);
                data = reader.read();
            }
            reader.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }
}