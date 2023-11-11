package com.example.elevatorsimulator3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SimulationSettings {
    public int floors;
    public ArrayList<AddPassenger> add_passenger = new ArrayList<>();
    public ArrayList<PassengerRequestPercentage> passenger_request_percentage = new ArrayList<>();
    public int number_of_elevators;
    public int run_simulation;

    // Method to add passengers
    public void addPassenger(int floor, int startFloor, int endFloor, String passengerType, int quantity) {
        AddPassenger newPassenger = new AddPassenger(floor, startFloor, endFloor, passengerType, quantity);
        add_passenger.add(newPassenger);
    }
    public void readSettingsFromFile() {
        String fileName = "com/example/elevatorsimulator3/ElevatorSimulatorFile/ElevatorSimulatorInfo";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                parseLine(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseLine(String line) {
        String[] parts = line.split("\\s+");
        if (parts.length >= 2) {
            String key = parts[0];
            String value = parts[1];

            switch (key) {
                case "floors":
                    this.floors = Integer.parseInt(value);
                    break;
                case "number_of_elevators":
                    this.number_of_elevators = Integer.parseInt(value);
                    break;
                case "run_simulation":
                    this.run_simulation = Integer.parseInt(value);
                    break;

            }
        }
    }





//More code to be typed based on the classes, not finished




    @Override
    public String toString() {
        return super.toString();
    }
}

// NUM 1