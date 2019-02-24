package com.company;

public class StateKey {

    public int visitedStates = 0;
    public int parentState = 0;

    public StateKey(int visitedStates, int parentState){
        this.visitedStates = visitedStates;
        this.parentState = parentState;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof StateKey) {
            StateKey otherKey = (StateKey) other;
            return this.hashCode() == otherKey.hashCode();
        }
        return false;
    }

    @Override
    public int hashCode(){
        return 31 * this.visitedStates + this.parentState;
    }

    @Override
    public String toString() {
        return "visitedStates: " + Integer.toBinaryString(this.visitedStates) + " | parentState: " + Integer.toString(this.parentState) + " | numStates: " + Integer.toString(this.numStates);
    }
}
