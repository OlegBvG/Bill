import java.util.ArrayList;
import java.util.Comparator;

class OperationAnalyze {
    static double debitAll(ArrayList<Operation> bill) {
        return bill.stream().mapToDouble(Operation::getDebit).sum();
    }

    static double creditAll(ArrayList<Operation> bill) {
        return bill.stream().mapToDouble(Operation::getCredit).sum();
    }

    static void itemOfExpenditure(ArrayList<Operation> bill) {
        bill.sort(Comparator.comparing(Operation::getOperationDescription));

        String itemOfExpenditure = "";
        double debitItem = 0;

        for (Operation op : bill) {

            if (op.getDebit() == 0) continue;

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
