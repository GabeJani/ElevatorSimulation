package com.example.elevatorsimulator3;

import java.util.ArrayList;

public class PassengerRequestPercentage {
    public String passengerType;
    public double percentage;

    public PassengerRequestPercentage(String passengerType, double percentage) {
        this.passengerType = passengerType;
        this.percentage = percentage;
    }

    public String getPassengerType() {

        return passengerType;
    }

    public void setPassengerType(String passengerType) {

        this.passengerType = passengerType;
    }

    public double getPercentage() {

        return percentage;
    }

    public void setPercentage(double percentage) {

        this.percentage = percentage;
    }

    // Method to read passenger request percentages from SimulationSettings
    public static PassengerRequestPercentage[] readFromSimulationSettings(SimulationSettings settings) {
        ArrayList<PassengerRequestPercentage> passengerPercentages = settings.passenger_request_percentage;
        PassengerRequestPercentage[] percentages = new PassengerRequestPercentage[passengerPercentages.size()];

        for (int i = 0; i < passengerPercentages.size(); i++) {
            PassengerRequestPercentage percentage = new PassengerRequestPercentage(
                    passengerPercentages.get(i).getPassengerType(),
                    passengerPercentages.get(i).getPercentage()
            );
            percentages[i] = percentage;
        }

        return percentages;
    }
}
