package com.example.elevatorsimulator3;

import java.io.FileNotFoundException;
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

//More code to be typed based on the classes, not finished




    @Override
    public String toString() {
        return super.toString();
    }
}
