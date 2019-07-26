import java.util.*;

public class Main {
    public static void main(String[] args) {
        String billFile = "data/movementList.csv";
        ArrayList<Operation> bill = ParseBill.loadBillFromFile(billFile);

// Просмотр результата загрузки
//        for (Operation op : bill) System.out.println(op);

        double debitAll = bill.stream().mapToDouble(Operation::getDebit).sum();
        double creditAll = bill.stream().mapToDouble(Operation::getCredit).sum();
        System.out.println("\nИТОГ по выписке: расход = " + debitAll + "; приход = " + creditAll + "\n");

        bill.sort(Comparator.comparing(Operation::getOperationDescription));

        String itemOfExpenditure = "";
        double debitItem = 0;

        for (Operation op : bill) {

            if (op.getDebit() == Double.valueOf(0)) continue;

            if (itemOfExpenditure.compareTo(op.getOperationDescription()) != 0) {
                if (debitItem > 0) System.out.println("Статья расходов: " + itemOfExpenditure + " = " + debitItem);
                itemOfExpenditure = op.getOperationDescription();
                debitItem = 0;
            }

            debitItem += op.getDebit();
        }
        System.out.println("Статья расходов: " + itemOfExpenditure + " = " + debitItem);

        //получение расходных операций
//        Stream<Operation> debitBill= bill.stream()
//        .filter(o -> o.getDebit() > 0)
//        .sorted(Comparator.comparing(Operation::getOperationDescription)).forEach();
    }

}



