package com.example.elevatorsimulator3;

public class AddPassenger {
    public int floor;
    public int startFloor;
    public int endFloor;
    public String passengerType;
    public int quantity;

    public AddPassenger(int floor, int startFloor, int endFloor, String passengerType, int quantity) {
        this.floor = floor;
        this.startFloor = startFloor;
        this.endFloor = endFloor;
        this.passengerType = passengerType;
        this.quantity = quantity;
    }
}
