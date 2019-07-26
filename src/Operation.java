import java.text.SimpleDateFormat;
import java.util.Date;

public class Operation {

    private String typeAccount;
    private String accountNumber;
    private String accountValuta;
    private Date dateOperation;
    private String reference;
    private String operationDescription;
    private Double credit;
    private Double debit;

    public Operation(String typeAccount, String accountNumber, String accountValuta, Date dateOperation,
                     String reference, String operationDescription, Double credit, Double debit) {
        this.typeAccount = typeAccount;
        this.accountNumber = accountNumber;
        this.accountValuta = accountValuta;
        this.dateOperation = dateOperation;
        this.reference = reference;
        this.operationDescription = operationDescription;
        this.credit = credit;
        this.debit = debit;
    }

    public String getTypeAccount() {
        return typeAccount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountValuta() {
        return accountValuta;
    }

    public Date getDateOperation() {
        return dateOperation;
    }

    public String getReference() {
        return reference;
    }

    public String getOperationDescription() {
        return operationDescription;
    }

    public Double getCredit() {
        return credit;
    }

    public Double getDebit() {
        return debit;
    }

    @Override
    public String toString() {
        return "Операция {" +
                "typeAccount='" + typeAccount + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", accountValuta='" + accountValuta + '\'' +
                ", dateOperation=" + (new SimpleDateFormat("dd.MM.yyyy")).format(dateOperation) +
                ", reference='" + reference + '\'' +
                ", operationDescription='" + operationDescription + '\'' +
                ", credit=" + credit +
                ", debit=" + debit +
                '}';
    }
}
