/*TODO:
    finish addTransaction method
 * Finish readAllEntries method
*/

package org.example;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

//This class will contain methods to read from and write to the transactions.csv file
public class TransactionOrganizer {
    //declaring List
    private ArrayList<Transactions> transList;

    //default constructor
    public TransactionOrganizer() {

        transList = new ArrayList<>();
    }


    //initializes transList and adds entries to it
    public void addTransaction(Transactions entry) {
        transList.add(entry);
        makeTransaction(transList);

    }


    //method
    public void makeTransaction(ArrayList<Transactions> list) {
        try (BufferedWriter bufWriter = new BufferedWriter(new FileWriter("transactions.csv", true))) {

            for (Transactions transaction : list) {
                String info =
                        transaction.getTransDate() + "|" +
                                transaction.getTransTime() + "|" +
                                transaction.getDescription() + "|" +
                                transaction.getVendor() + "|" +
                                String.format("%.2f", transaction.getAmount());

                bufWriter.write(info + "\n");
                System.out.println("\nThank you. Your entry has been saved.");
            } //end of for loop
            bufWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }// end of make transaction method


    //May need to change else statement
    public ArrayList<Transactions> readEntries() {
        try (BufferedReader transFileReader = new BufferedReader(new FileReader("transactions.csv"))) {
            String transactionString;
            while ((transactionString = transFileReader.readLine()) != null) {
                String[] transactionData = transactionString.split("\\|");

                if (transactionData.length == 5) {
                    LocalDate transDate = LocalDate.parse(transactionData[0]);
                    LocalTime transTime = LocalTime.parse(transactionData[1]);
                    String description = transactionData[2];
                    String vendor = transactionData[3];
                    Double amount = Double.parseDouble(transactionData[4]);

                    // Creates a Transaction object and stores it
                    Transactions entry = new Transactions(transDate, transTime, description, vendor, amount);
                    transList.add(entry);

                } //else {
                //Fix here
                // System.out.println("Invalid data provided.");
                // }
            } //end of while loop
            transFileReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return transList;

    }//end of read entry
}





