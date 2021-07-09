package com.sytoss.edu2021.repo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Getter
@ToString
public class CabinBOM {

    @Getter
    @Setter
    @JsonIgnore
    private int id;
    @Getter
    @Setter
    private int number;

    @Getter
    @Setter
    //TODO: yevgenyv : remove it
    private BuildingBOM building;

    @JsonIgnore
    @Setter
    private Integer[] floorButtons;
    @Setter
    @JsonIgnore
    private boolean isDoorOpened;
    @Setter
    @JsonIgnore
    private boolean isOverloaded;
    @Setter
    @JsonIgnore
    private Integer currentFloor;
    @Setter
    @JsonIgnore
    private EngineBOM engine = null;
    @JsonIgnore
    private Route route = null;

    public CabinBOM() {
        currentFloor = 1;
    }

    public CabinBOM(int number, BuildingBOM building) {
        this.number = number;
        this.building = building;
    }

    public CabinBOM(int startFloor, int endFloor) {
        setFloors(startFloor, endFloor);
        route = new Route();
        engine = new EngineBOM(route, new ArrayList<Floor>(Math.abs(startFloor) + Math.abs(endFloor)));
    }

    public void startMovement() {
        engine.move(currentFloor);
    }

    private void setFloors(int startFloor, int endFloor) {

        int floorsNumber = endFloor - startFloor + 1;
        floorButtons = new Integer[floorsNumber];

        int curfloor = startFloor;
        for (int i = 0; i < floorsNumber; i++) {
            floorButtons[i] = curfloor;
            ++curfloor;
        }

        this.currentFloor = startFloor;
    }

    /*public String getBuilding() {
        String result = "address: " + building.getAddress();
        return result;
    }*/

    /*public BuildingBOM getBuilding(){
        return building;
    }*/

    public void addFloorToStop(int floorNumber) {
        if (floorNumber < floorButtons[0] && floorNumber > floorButtons[floorButtons.length - 1]) {
            return;
        }
        route.addRoutFloor(currentFloor, floorNumber);
    }

    public void openDoor() {
        if (!isDoorOpened)
            isDoorOpened = true;
    }

    public void closeDoor() {
        if (isDoorOpened) {
            isDoorOpened = false;
        }
    }

    public String displayCabinInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(route.getDirection());
        stringBuilder.append(" ");
        stringBuilder.append(currentFloor);
        if (isOverloaded) {
            stringBuilder.append(" Overloaded!!!");
        }

        return stringBuilder.toString();
    }

    public void callEmergencyStop() {
        engine.callEmergencyStop();
    }

    public boolean isValid() {
        return number > 0;
    }
}