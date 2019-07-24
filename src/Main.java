import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import static java.lang.Double.valueOf;

public class Main
{
    private static String billFile = "data/movementList.csv";
    private static String dateFormat = "dd.MM.yyyy";

    public static void main(String[] args) {
        ArrayList<Operation> bill = loadBillFromFile();

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
//        .sorted(Comparator.comparing(Operation::getOperationDescription));
    }

    private static ArrayList<Operation> loadBillFromFile()
    {
        ArrayList<Operation> bill = new ArrayList<>();
        try
        {
            List<String> lines = Files.readAllLines(Paths.get(billFile));

            int skipLine = 1;

            for (String line : lines) {
                if (0 < skipLine--) continue;

                    String[] fragments = line.split(",");
                    ArrayList<String> columnList = new ArrayList<String>();

                    for (int i = 0; i < fragments.length; i++) {
                        //Если колонка начинается на кавычки или заканчивается на кавычки
                        if (IsColumnPart(fragments[i])) {
                            String lastText = columnList.get(columnList.size() - 1);
                            columnList.set(columnList.size() - 1, lastText.replace("\"","") + "."+ fragments[i].replace("\"",""));
                            i++;
                        } else {
                            columnList.add(fragments[i]);
                        }
                    }

                int slash = 1;
                if (columnList.get(5).lastIndexOf("/") > 0){
                    slash += columnList.get(5).lastIndexOf("/");

                } else if (columnList.get(5).lastIndexOf("\\") > 0) {
                    slash += columnList.get(5).lastIndexOf("\\");

                } else slash = 17;


                bill.add(new Operation(
                       columnList.get(0),
                        columnList.get(1),
                        columnList.get(2),
                        (new SimpleDateFormat(dateFormat)).parse(columnList.get(3)),
                        columnList.get(4),
                        columnList.get(5).substring(slash, 69).trim(),
                        valueOf(columnList.get(6)),
                        valueOf(columnList.get(7))
                ));

            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return bill;
    }

    //Проверка является ли колонка частью другой колонки
    private static boolean IsColumnPart(String text) {
        String trimText = text.trim();
        //Если в тексте одна кавычка и текст на нее заканчиваеться значит это часть предыдущей колонки
        return trimText.indexOf("\"") == trimText.lastIndexOf("\"") && trimText.endsWith("\"");
    }

   }



