package homework2.task7;

import homework2.task7.dao.*;
import homework2.task7.pojo.*;

import java.io.*;
import java.text.*;
import java.util.*;

public class Main {
    public static Date convertStringToDate(String dateStr){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = formatter.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    public static void main(String[] args) {
        DaoImpl dao = new DaoImpl(ExpensesSessionFactory.getSessionFactory());


        // Extracting identifiers from Client and ClientDetails entities
        ArrayList<Client> clients = dao.getClients();
        for (Client client : clients) {
            Serializable clientId = dao.getIdentifier(client);
            System.out.println("Client ID: " + clientId);
        }

        ArrayList<ClientDetails> clientDetailsList = dao.getClientDetails();
        for (ClientDetails clientDetails : clientDetailsList) {
            Serializable clientDetailsId = dao.getIdentifier(clientDetails);
            System.out.println("ClientDetails ID: " + clientDetailsId);
        }
//
//        // Retrieve all expenses from the database
//        ArrayList<Expenses> expenses = dao.getExpenses();
//        for (Expenses expense : expenses) {
//            System.out.println(expense);
//        }
//
//        // Retrieve all receivers from the database
//        ArrayList<Receiver> receivers = dao.getReceivers();
//        for (Receiver receiver : receivers) {
//            System.out.println(receiver);
//        }
//
//        // Retrieve a specific expense with id ? from the database
//        Expenses expense = dao.getExpense(25);
//        System.out.println(expense);
//
//        // Retrieve a specific receiver with id ? from the database
//        Receiver receiver = dao.getReceiver(5);
//        System.out.println(receiver);
//
//        // Create a new expense and add it to the database
//        Expenses newExpense = new Expenses();
//        newExpense.setId(47);
//        newExpense.setPaydate(convertStringToDate("2020-07-27"));
//        newExpense.setReceiver(receiver);
//        newExpense.setValue(100.0);
//        dao.addExpense(newExpense);
//
//        // Create a new receiver and add it to the database
//        Receiver newReceiver = new Receiver();
//        newReceiver.setId(6);
//        newReceiver.setName("Test");
//        dao.addReceiver(newReceiver);

    }


}





