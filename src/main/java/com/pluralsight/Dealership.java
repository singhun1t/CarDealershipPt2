package com.pluralsight;

import java.util.ArrayList;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private DealershipFileManager fileManager = new DealershipFileManager();

    ArrayList<Vehicle> inventory = new ArrayList<Vehicle>();


    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        inventory = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public ArrayList<Vehicle> getAllVehicles(){
        return inventory;
    }
    public ArrayList<Vehicle> getVehiclesByPrice(double min, double max){
       ArrayList<Vehicle> filter = new ArrayList<>();
       for (Vehicle selected : inventory){
           if(selected.getPrice() <= max && selected.getPrice() >=min){
               filter.add(selected);
           }
       }
       return filter;


    }
    public ArrayList<Vehicle> getVehiclesByMakeModel(String make, String model){
        ArrayList<Vehicle> modelFilter = new ArrayList<>();
        for (Vehicle selected : inventory){
            if(selected.getMake().equalsIgnoreCase(make) && selected.getModel().equalsIgnoreCase(model)){
                modelFilter.add(selected);
            }
        }
        return modelFilter;

    }
    public ArrayList<Vehicle> getVehiclesByYear(int min, int max){
        ArrayList<Vehicle> yearFilter = new ArrayList<>();
        for(Vehicle selected : inventory){
            if(selected.getPrice() <=max && selected.getPrice() >= min){
                yearFilter.add(selected);
            }
        }
        return yearFilter;
    }
    public ArrayList<Vehicle> getVehiclesByColor(String color){
        ArrayList<Vehicle> colorFilter = new ArrayList<>();
        for (Vehicle selected : inventory){
            if(selected.getColor().equalsIgnoreCase(color)){
                colorFilter.add(selected);
            }
        }
        return colorFilter;
    }
    public ArrayList<Vehicle> getVehiclesByMileage(int min, int max){
        ArrayList<Vehicle> mileageFilter = new ArrayList<>();
        for (Vehicle selected: inventory){
            if(selected.getOdometer() <=max && selected.getOdometer() >=min){
                mileageFilter.add(selected);
            }
        }
        return mileageFilter;
    }
    public ArrayList<Vehicle> getVehiclesByType(String vehicleType){
        ArrayList <Vehicle> typeFilter = new ArrayList<>();
        for (Vehicle selected : inventory){
            if (selected.getVehicleType().equalsIgnoreCase(vehicleType)){
                typeFilter.add(selected);
            }
        }
        return typeFilter;
    }
    public void addVehicle(Vehicle vehicle){
        inventory.add(vehicle);
        fileManager.saveDealership(this,"dealership.csv");
    }
    public void removeVehicle(Vehicle vehicle){
        inventory.remove(vehicle);
    }
}
