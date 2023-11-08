package homework2.task7.dao;

import homework2.task7.pojo.*;

import java.util.*;

public interface Dao {
    Receiver getReceiver(int num);
    ArrayList<Receiver> getReceivers();
    Expenses getExpense(int num);
    ArrayList<Expenses> getExpenses();
    int addReceiver(Receiver receiverDto);
    int addExpense(Expenses expensesDto);
}
