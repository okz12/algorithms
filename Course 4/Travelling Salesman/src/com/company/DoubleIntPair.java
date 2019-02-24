package com.company;

public class DoubleIntPair {
    public double distance = 0;
    public int parentState = 0;

    public DoubleIntPair(double distance, int parentState){
        this.distance = distance;
        this.parentState = parentState;
    }

    @Override
    public String toString() {
        return "Distance: " + Integer.toString(this.distance) + " | parentState: " + Integer.toString(this.parentState);
    }
}
