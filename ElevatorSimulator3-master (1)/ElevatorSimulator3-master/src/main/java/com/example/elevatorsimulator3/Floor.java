package com.example.elevatorsimulator3;

import java.util.ArrayList;

public class Floor {
    ArrayList<Passenger> waitQueue = new ArrayList<>();
    ArrayList<Passenger> completedQueue = new ArrayList<>();
    ArrayList<Elevator> currentElevators = new ArrayList<>();

    public void addToWaitQueue(Passenger passenger) {
        waitQueue.add(passenger);
    }

    public void removeFromWaitQueue(Passenger passenger) {
        waitQueue.remove(passenger);
    }

    public void addToCompletedQueue(Passenger passenger) {
        completedQueue.add(passenger);
    }

    public void removeFromCompletedQueue(Passenger passenger) {
        completedQueue.remove(passenger);
    }

    public void addElevator(Elevator elevator) {
        currentElevators.add(elevator);
    }

    public void removeElevator(Elevator elevator) {
        currentElevators.remove(elevator);
    }

    public ArrayList<Passenger> getWaitQueue() {
        return waitQueue;
    }

    public ArrayList<Passenger> getCompletedQueue() {
        return completedQueue;
    }

    public ArrayList<Elevator> getCurrentElevators() {
        return currentElevators;
    }

}
