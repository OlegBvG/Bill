import java.util.*;

public class Main {
    public static void main(String[] args) {
        String billFile = "data/movementList.csv";
        ArrayList<Operation> bill = ParseBill.loadBillFromFile(billFile);

//        for (Operation op : bill) System.out.println(op); // Просмотр результата загрузки

        System.out.println("\nИТОГ по выписке: расход = " + OperationAnalyze.debitAll(bill)
                + "; приход = " + OperationAnalyze.creditAll(bill) + "\n");

        OperationAnalyze.itemOfExpenditure(bill);

    }

}



