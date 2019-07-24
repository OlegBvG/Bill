import java.text.SimpleDateFormat;
import java.util.Date;


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

    public void setTypeAccount(String typeAccount) {
        this.typeAccount = typeAccount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountValuta() {
        return accountValuta;
    }

    public void setAccountValuta(String accountValuta) {
        this.accountValuta = accountValuta;
    }

    public Date getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(Date dateOperation) {
        this.dateOperation = dateOperation;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getOperationDescription() {
        return operationDescription;
    }

    public void setOperationDescription(String operationDescription) {
        this.operationDescription = operationDescription;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public Double getDebit() {
        return debit;
    }

    public void setDebit(Double debit) {
        this.debit = debit;
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


/* public String toString()
    {
        return name + " - " + salary + " - " +
                (new SimpleDateFormat("dd.MM.yyyy")).format(workStart);
    }*/
}
