package com.pluralsight;



import java.io.*;

public class DealershipFileManager {
    String file = "contracts.csv";


    public Dealership getDealership() {

        Dealership dealership = new Dealership(" "," "," ");
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            boolean dealershipCreated = false;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 3 && !dealershipCreated) {
                    String businessName = parts[0];
                    String address = parts[1];
                    String phoneNumber = parts[2];
                    dealership = new Dealership(businessName, address, phoneNumber);
                    dealershipCreated = true;
                } else if (parts.length == 8 && dealership != null) {
                    int vin = Integer.parseInt(parts[0]);
                    int year = Integer.parseInt(parts[1]);
                    String make = parts[2];
                    String model = parts[3];
                    String vehicleType = parts[4];
                    String color = parts[5];
                    int odometer = Integer.parseInt(parts[6]);
                    double price = Double.parseDouble(parts[7]);
                    Vehicle vehicleInfo = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
                    dealership.addVehicle(vehicleInfo);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (dealership == null) {
            System.out.println("Dealership information not found");
        }
        return dealership;
    }

    public void saveDealership(Dealership dealership, String file) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone() + "\n");
            for (Vehicle vehicle : dealership.getAllVehicles()) {
                String line = String.format(
                        "%d|%d|%s|%s|%s|%s|%d|%.2f\n",
                        vehicle.getVin(), vehicle.getYear(), vehicle.getMake(), vehicle.getModel(), vehicle.getVehicleType(),
                        vehicle.getColor(), vehicle.getOdometer(), vehicle.getPrice()
                );
                bufferedWriter.write(line);
            }
            } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    }



