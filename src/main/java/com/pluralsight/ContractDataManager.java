package com.pluralsight;

import java.io.*;

public class ContractDataManager {

    public void saveContract(Contract contract) {



            try {
                String contractFile = "contracts.csv";
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(contractFile, true));


                if (contract instanceof SalesContract) {
                 //  String financeOption = ((SalesContract) contract).isFinanceOption() ? "yes" : "no";

                    bufferedWriter.write( " Sale" + "|" + contract.getDate() + "|" + contract.getCustomerName()+ "|" + contract.getCustomerEmail() + "|"
                            + contract.getVehicle().getVin() + "|" + contract.getVehicle().getYear() + "|" + contract.getVehicle().getMake()
                    + "|" + contract.getVehicle().getModel() + "|" + contract.getVehicle().getVehicleType() + "|" + contract.getVehicle().getColor()
                    + "|" + contract.getVehicle().getOdometer() + "|" + contract.getVehicle().getPrice() + "|" + ((SalesContract) contract).getSalesTaxAmount()
                    + "|" + ((SalesContract) contract).getRecordingFee() + "|" + ((SalesContract) contract).isFinanceOption() + "|" + contract.getMonthlyPayment() );
                    bufferedWriter.newLine();
                }else if(contract instanceof LeaseContract){

                    bufferedWriter.write( " Lease" + "|" + contract.getDate() + "|" + contract.getCustomerName()+ "|" + contract.getCustomerEmail() + "|"
                            + contract.getVehicle().getVin() + "|" + contract.getVehicle().getYear() + "|" + contract.getVehicle().getMake()
                            + "|" + contract.getVehicle().getModel() + "|" + contract.getVehicle().getVehicleType() + "|" + contract.getVehicle().getColor()
                            + "|" + contract.getVehicle().getOdometer() + "|" + contract.getVehicle().getPrice() + "|" +
                            "|" + ((LeaseContract) contract).getExpectedEndingValue() + "|"  + ((LeaseContract)contract).getMonthlyPayment() );
                    bufferedWriter.newLine();
                }
                bufferedWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

