package com.example.elevatorsimulator3;

import java.io.*;
import java.io.FileReader;

public class ElevatorSimulation {
    SimulationSettings _simulationSettings = new SimulationSettings();
    private Building building;

    public boolean initSimulation() {
        String fileName = "ElevatorSimulatorInfo.txt";
        _simulationSettings = readSettingsContent();
        building = new Building(_simulationSettings);
        runSimulation(_simulationSettings);
        return true;
    }

    private SimulationSettings readSettingsContent() {
        String filePath = "C:\\Users\\Gabe\\Downloads\\ElevatorSimulator3-master (1)\\ElevatorSimulator3-master\\ElevatorSimulatorInfo.txt";
        File file = new File(filePath);
        System.out.println("Absolute Path: " + file.getAbsolutePath());

        SimulationSettings currentSettings = new SimulationSettings();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                parseLine(line, currentSettings);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return currentSettings;
    }



    private void parseLine(String line, SimulationSettings currentSettings) {
        String[] tokens = line.split("\\s+");

        if (tokens.length > 0) {
            String command = tokens[0];

            switch (command) {
                case "floors":
                    currentSettings.floors = Integer.parseInt(tokens[1]);
                    break;
                case "add_passenger":
                    int floor = Integer.parseInt(tokens[1]);
                    int startFloor = Integer.parseInt(tokens[2]);
                    int endFloor = Integer.parseInt(tokens[3]);
                    String passengerType = tokens[4];
                    int quantity = Integer.parseInt(tokens[5]);
                    currentSettings.addPassenger(floor, startFloor, endFloor, passengerType, quantity);
                    break;
                case "elevator_type":
                    // handle elevator type command
                    break;
                case "request_percentage":
                    // handle request percentage command
                    break;
                case "passenger_request_percentage":
                    // handle passenger request percentage command
                    break;
                case "number_of_elevators":
                    currentSettings.number_of_elevators = Integer.parseInt(tokens[1]);
                    break;
                case "run_simulation":
                    currentSettings.run_simulation = Integer.parseInt(tokens[1]);
                    break;
                default:
                    // handle unrecognized command
                    break;
            }
        }
    }

    private boolean runSimulation(SimulationSettings simulationSettings) {
        int iterations = simulationSettings.run_simulation;

        for (int i = 0; i < iterations; i++) {
            simulateElevatorMovement();
            simulatePassengerActions();
            System.out.println("Simulation iteration: " +(i+1));
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
            System.out.println("Simulating elevator movement...");
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
                        System.out.println("Simulating passenger actions....");
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
}
   /* public static void main(String[] args) {

        try {
            FileReader reader = new FileReader("elevdsf");
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

    */
