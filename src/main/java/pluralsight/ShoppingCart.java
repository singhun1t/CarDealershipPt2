package pluralsight;

import java.util.ArrayList;

public class ShoppingCart {
    private ArrayList<Vehicle> items;

    public ShoppingCart(){
        items = new ArrayList<>();
    }

    public void addItem(Vehicle vehicle){
        items.add(vehicle);

    }
    public void clear(){
        items.clear();
    }
    public ArrayList<Vehicle> getItems(){
        return items;
    }
    public double returnTotalPrice(){
        double totalPrice = 0.0;
        for (Vehicle cartItems : items){
            totalPrice += cartItems.getPrice();
        }
        return totalPrice;
    }
    public boolean isEmpty(){
        return items.isEmpty();
    }

}
