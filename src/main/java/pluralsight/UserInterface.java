package pluralsight;


import java.util.ArrayList;
import java.util.Scanner;


public class UserInterface {
    Dealership dealership;
    private ShoppingCart shoppingCart = new ShoppingCart();

    private void init(){
        DealershipFileManager loader = new DealershipFileManager();
        this.dealership = loader.getDealership();
    }

    private void displayVehicles(ArrayList<Vehicle> vehiclesToDisplay){
        if(vehiclesToDisplay.isEmpty()){
            System.out.println("No vehicles found");
            return;
        }
        System.out.printf("%-15s %-15s %-20s %-15s %-17s %-13s %-15s %-15s %s\n",
                "VIN", "Year", "Make", "Model", "Type", "Color", "Mileage", "Price", "Dealership");
            for (Vehicle vehicle : vehiclesToDisplay){
                System.out.printf("%-15d %-15d %-20s %-15s %-17s %-13s %-15d %-15.2f %s\n", vehicle.getVin(),vehicle.getYear(),
                        vehicle.getMake(), vehicle.getModel(),vehicle.getVehicleType(), vehicle.getColor(), vehicle.getOdometer(),
                        vehicle.getPrice(), dealership.getName());
            }
    }
    public void display(){
        Scanner scanner  = new Scanner(System.in);
        init();
        boolean running = true;
        int choice = -1;
        while(running){
            System.out.println("Welcome to the dealership");
            System.out.println("1. Search by Price");
            System.out.println("2. Search by Model");
            System.out.println("3. Search by year of vehicle");
            System.out.println("4. Search by Color");
            System.out.println("5. Search by Mileage");
            System.out.println("6. Search by Vehicle Type");
            System.out.println("7. Get all Vehicles");
            System.out.println("8. Add vehicles");
            System.out.println("9. Remove vehicle");
            System.out.println("10. View Cart/Checkout");
            System.out.println("11. Quit");

            choice = scanner.nextInt();

            switch(choice){
                case 1:
                    processGetByPriceRequest();
                    break;
                case 2:
                    processGetByMakeModelRequest();
                    break;
                case 3:
                    processGetByYearRequest();
                    break;
                case 4:
                    processGetByColorRequest();
                    break;
                case 5:
                    processGetByMileageRequest();
                    break;
                case 6:
                    processGetByVehicleTypeRequest();
                    break;
                case 7:
                    processAllVehiclesRequest();
                    break;
                case 8:
                    processAddVehicleRequest();
                    break;
                case 9:
                    processRemoveVehicleRequest();
                    break;
                case 10:
                    processCheckoutRequest();
                    break;
                case 11:
                    running = false;
                    break;
            }
        }
    }
    public void processGetByPriceRequest(){
        System.out.println("What is the minimum price you want to search by");
        Scanner scanner = new Scanner(System.in);
        double minPrice = scanner.nextDouble();
        System.out.println("What is the maximum price you want to search by");
        double maxPrice = scanner.nextDouble();

        ArrayList<Vehicle> vehiclesByPrice = dealership.getVehiclesByPrice(minPrice,maxPrice);
        displayVehicles(vehiclesByPrice);
    }
    public void processGetByMakeModelRequest(){
        System.out.println("What is the Make of the vehicle you want to search for?");
        Scanner scanner = new Scanner (System.in);
        String make = scanner.nextLine();
        System.out.println("What is the Model of the vehicle you want to search for?");
        String model = scanner.nextLine();
        ArrayList<Vehicle> makeAndModel = dealership.getVehiclesByMakeModel(make,model);
        displayVehicles(makeAndModel);
    }
    public void processGetByYearRequest(){
        System.out.println("What is the minimum year of the vehicle you want to search by?");
        Scanner scanner = new Scanner(System.in);
        int minYear = scanner.nextInt();
        System.out.println("What is the latest year of the vehicle you want to search by?");
        int maxYear = scanner.nextInt();
        ArrayList<Vehicle> yearVehicle = dealership.getVehiclesByYear(minYear,maxYear);
        displayVehicles(yearVehicle);
    }
    public void processGetByColorRequest(){
        System.out.println("What color vehicle do you want to search by?");
        Scanner scanner = new Scanner(System.in);
        String userColor = scanner.nextLine();
        ArrayList<Vehicle> colorVehicle = dealership.getVehiclesByColor(userColor);
        displayVehicles(colorVehicle);
    }
    public void processGetByMileageRequest(){
        System.out.println("What is the lowest Mileage of the Vehicle do you want to search by?");
        Scanner scanner = new Scanner(System.in);
        int minMile = scanner.nextInt();
        System.out.println("What is the highest Mileage of the Vehicle you would like to search?");
        int maxMile = scanner.nextInt();
        ArrayList<Vehicle> mileageVehicle = dealership.getVehiclesByMileage(minMile,maxMile);
        displayVehicles(mileageVehicle);
    }
    public void processGetByVehicleTypeRequest(){
        System.out.println("What type of vehicle do you want to search by?");
        Scanner scanner = new Scanner(System.in);
        String vehicleType = scanner.nextLine();
        ArrayList<Vehicle> typeVehicle = dealership.getVehiclesByType(vehicleType);
        displayVehicles(typeVehicle);
    }

    public void processAddVehicleRequest(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is the vin of the Vehicle?");
        int addVin = scanner.nextInt();
        scanner.nextLine();
        System.out.println("What is the Year of the Vehicle?");
        int addYear = scanner.nextInt();
        scanner.nextLine();
        System.out.println("What is the make of the Vehicle?");
        String addMake = scanner.nextLine();
        System.out.println("What is the model of the Vehicle?");
        String addModel = scanner.nextLine();
        System.out.println("What type of vehicle is it?");
        String addType = scanner.nextLine();
        System.out.println("What color is the vehicle?");
        String addColor = scanner.nextLine();
        System.out.println("How many miles are on the odometer?");
        int addOdometer = scanner.nextInt();
        scanner.nextLine();
        System.out.println("What is the price you paid for your vehicle?");
        double addPrice = scanner.nextDouble();

         Vehicle newVehicle = new Vehicle(addVin,addYear,addMake,addModel,addType,addColor,addOdometer,addPrice);
         dealership.addVehicle(newVehicle);
    }
    public void processRemoveVehicleRequest(){
        Scanner scanner = new Scanner (System.in);
        displayVehicles(dealership.getAllVehicles());
        System.out.println("What is the vin number of the vehicle you want to remove?");
        int vinToRemove = scanner.nextInt();

        Vehicle vehicleRemoval = null;
        for (Vehicle remove : dealership.getAllVehicles()){
                if (remove.getVin() == vinToRemove){
                    vehicleRemoval = remove;
                    break;
                }
        }
        if (vehicleRemoval!=null){
            dealership.removeVehicle(vehicleRemoval);
            System.out.println("Vehicle with Vin# " + vehicleRemoval.getVin() + "Has been removed");
        }else{
            System.out.println("Vehicle could not be found");
        }
    }
    public void processAllVehiclesRequest(){
        ArrayList<Vehicle> allVehicles = dealership.getAllVehicles();
        displayVehicles(allVehicles);
    }

    public void processCheckoutRequest(){
        Scanner scanner = new Scanner (System.in);

        System.out.println("Available Vehicles you can add to your cart");
        displayVehicles(dealership.getAllVehicles());

        System.out.println("Enter vin number of the vehicle you want to add to the cart. Type 1 to checkout");
        int userVin = scanner.nextInt();
        scanner.nextLine();

        while((userVin != 1)){
            Vehicle userVehicle = null;
            for(Vehicle v : dealership.getAllVehicles()){
                if(v.getVin() == userVin){
                    userVehicle = v;
                    break;
                }
            }
            if (userVehicle != null){
                shoppingCart.addItem(userVehicle);
                System.out.println("Vehicle has been added to cart");
            }else{
                System.out.println("invalid vin");
            }
            displayVehicles(dealership.getAllVehicles());

            System.out.println("Enter vin number of the vehicle you want to add to the cart. Type 1 to checkout");
            userVin = scanner.nextInt();
            scanner.nextLine();
        }
        if (!shoppingCart.isEmpty()) {
            System.out.println("\nItems in Your Cart:");
            displayVehicles(shoppingCart.getItems());

            double totalPrice = shoppingCart.returnTotalPrice();
            System.out.printf("\nTotal Price: $%.2f\n", totalPrice);

            System.out.print("Finish Purchase? Yes or No: ");
            String confirmation = scanner.nextLine().toUpperCase();
            if (confirmation.equalsIgnoreCase("Yes")) {

                shoppingCart.clear();
                System.out.println("Thank you for shopping");
            } else {
                System.out.println("Purchase has been canceled.");
            }
        } else {
            System.out.println("Your cart is empty");
        }
    }
}
