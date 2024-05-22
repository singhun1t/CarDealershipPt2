package pluralsight;

public class SalesContract extends Contract{
    protected double salesTaxAmount = 0.05;
    protected double recordingFee = 100.00;
    protected double processingFee ;
    protected boolean financeOption;


    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicle, boolean financeOption) {
        super(date, customerName, customerEmail, vehicle);
        this.financeOption = financeOption;
    }

    public double getProcessingFee(){
        if (this.getVehicle().getPrice() < 10000){
            processingFee = 295.0;
        }else{
            processingFee = 495.0;
        }
        return processingFee;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public double getSalesTaxAmount() {
        return salesTaxAmount;
    }

    @Override
    public double getTotalPrice() {
        return getVehicle().getPrice() + salesTaxAmount + recordingFee + getProcessingFee();
    }

    @Override
    public double getMonthlyPayment() {
        int numberOfPayments = 0;
        double interestRate = 0;
        if (financeOption) {
            if (getVehicle().getPrice() >= 10000) {
                numberOfPayments = 48;
                interestRate = 4.25 / 1200;
            } else {
                numberOfPayments = 24;
                interestRate = 5.25 / 1200;
            }

            double monthlyPayment = getTotalPrice() * (interestRate * Math.pow(1 + interestRate, numberOfPayments)) / (Math.pow(1 + interestRate, numberOfPayments) - 1);
            monthlyPayment = Math.round(monthlyPayment * 100);
            monthlyPayment /= 100;
            return monthlyPayment;
        } else {
            return 0.0;
        }
    }

    public boolean isFinanceOption() {
        return financeOption;
    }


    public void setFinanceOption(boolean financeOption) {
        this.financeOption = financeOption;
    }
}
