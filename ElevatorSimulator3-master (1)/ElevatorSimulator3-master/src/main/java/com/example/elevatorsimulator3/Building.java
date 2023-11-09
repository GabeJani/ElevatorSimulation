package com.example.elevatorsimulator3;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Building {
    ArrayList<Floor> Floors = new ArrayList<>();
    private ArrayList<Elevator> elevators = new ArrayList<>();
    private SimulationSettings _simulationSettings;

    public Building(SimulationSettings settings) {
        this._simulationSettings = settings;
        initializeFloors();
        initializeElevators();
    }

    private void initializeFloors() {
        for (int i = 0; i < _simulationSettings.floors; i++) {
            Floors.add(new Floor());
        }
    }

    private void initializeElevators() {
        for (int i = 0; i < _simulationSettings.number_of_elevators; i++) {
            String passengerType = _simulationSettings.add_passenger.get(i).passengerType; // Get passenger type for the elevator
            elevators.add(createElevator(passengerType));
        }
    }

    private Elevator createElevator(String elevatorType) {
        // Create the respective elevator based on the type
        switch (elevatorType) {
            case "Standard":
                return new StandardElevator(1, "Standard");
            case "VIP":
                return new ExpressElevator(2, "Express"); // Change to VIP Elevator class if available
            case "Freight":
                return new FreightElevator(3, "Freight", 200);
            case "Glass":
                return new GlassElevator(4, "Glass", 200);
            default:
                // Default to Standard Elevator
                return new StandardElevator(1, "Standard");
        }
    }

    public Elevator assignElevator(Passenger passenger, direction passengerDirection) {
        Elevator assignedElevator = null;
        int minLoad = Integer.MAX_VALUE; // Initialize with a high value

        int passengerStartFloor = passenger.startFloor; // Assuming startFloor is a public field

        for (Elevator elevator : elevators) {
            //if the elevator can accommodate the passenger's direction and is at the same floor
            if (elevator.getCurrentFloor() == passengerStartFloor && elevator.move(passengerDirection)) {
                if (elevator.getCurrentLoad() < minLoad) {
                    assignedElevator = elevator;
                    minLoad = elevator.getCurrentLoad();
                }
            }
        }

        return assignedElevator;
    }

    public ArrayList<Elevator> getElevators() {
        return elevators;
    }
    public ArrayList<Floor> getFloors() {
        return Floors;
    }
}
