import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

class ParseBill {

    private static final int TYPE_ACCOUNT_INDEX = 0;
    private static final int ACCOUNT_NUMBER_INDEX = 1;
    private static final int ACCOUNT_VALUTA_INDEX = 2;
    private static final int DATE_OPERATION_INDEX = 3;
    private static final int REFERENCE_INDEX = 4;
    private static final int OPERATION_DESCRIPTOR_INDEX = 5;
    private static final int CREDIT_INDEX = 6;
    private static final int DEBIT_INDEX = 7;

    private static String dateFormat = "dd.MM.yyyy";
    static ArrayList<Operation> loadBillFromFile(String billFile)
    {
        ArrayList<Operation> bill = new ArrayList<>();
        try
        {
            List<String> lines = Files.readAllLines(Paths.get(billFile));

            int skipLine = 1;

            for (String line : lines) {
                if (0 < skipLine--) continue;

                String[] fragments = line.split(",");
                ArrayList<String> columnList = new ArrayList<>();

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

                int indexSlash = 1;
                if (columnList.get(OPERATION_DESCRIPTOR_INDEX).lastIndexOf("/") > 0){
                    indexSlash += columnList.get(OPERATION_DESCRIPTOR_INDEX).lastIndexOf("/");

                } else if (columnList.get(OPERATION_DESCRIPTOR_INDEX).lastIndexOf("\\") > 0) {
                    indexSlash += columnList.get(OPERATION_DESCRIPTOR_INDEX).lastIndexOf("\\");

                } else indexSlash = 17;


                bill.add(new Operation(
                        columnList.get(TYPE_ACCOUNT_INDEX),
                        columnList.get(ACCOUNT_NUMBER_INDEX),
                        columnList.get(ACCOUNT_VALUTA_INDEX),
                        (new SimpleDateFormat(dateFormat)).parse(columnList.get(DATE_OPERATION_INDEX)),
                        columnList.get(REFERENCE_INDEX),
                        columnList.get(OPERATION_DESCRIPTOR_INDEX).substring(indexSlash, 69).trim(),
                        Double.parseDouble(columnList.get(CREDIT_INDEX)),
                        Double.parseDouble(columnList.get(DEBIT_INDEX))
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
