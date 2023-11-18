package homework2.task7.dao;

import homework2.task7.pojo.*;

import java.util.*;

public interface Dao {
    Receiver getReceiver(int num);
    ArrayList<Receiver> getReceivers();
    Expenses getExpense(int num);
    ArrayList<Expenses> getExpenses();
    Expenses loadExpense(int num);
    Receiver loadReceiver(int num);
    int addReceiver(Receiver receiverDto);
    int addExpense(Expenses expensesDto);
    boolean deleteExpense(Expenses expenses);
    boolean deleteReceiver(Receiver receiver);


}
