import java.util.Date;

public class Operation {

    private String numberAccount;
    private String currency;
    private Date dateOperation;
    private String reference;
    private String operationDescription;
    private String organization;
    private double coming;
    private double consumption;

    Operation(String numberAccount, String currency, Date dateOperation, String reference, String operationDescription, String coming, String consumption){
        this.numberAccount = numberAccount;
        this.currency = currency;
        this.dateOperation = dateOperation;
        this.reference = reference;
        String[] b = operationDescription.replace("548673++++++1028", "").trim().split("     ");
        this.organization = b[0];
        this.operationDescription = operationDescription;
        this.coming = Double.parseDouble(coming);
        if (!consumption.contains("\"")){
            this.consumption = Double.parseDouble(consumption);
        }
        else{

            String s = consumption.replaceAll("\"", "").replaceAll(",", ".");
            this.consumption = Double.parseDouble(s);
        }
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getNumberAccount() {
        return numberAccount;
    }

    public void setNumberAccount(String numberAccount) {
        this.numberAccount = numberAccount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Date getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(Date dateOperation) {
        this.dateOperation = dateOperation;
    }

    public String getOperationDescription() {
        return operationDescription;
    }

    public  void setOperationDescription(String operationDescription) {
        this.operationDescription = operationDescription;
    }

    public  double getComing() {
        return coming;
    }

    public void setComing(double coming) {
        this.coming = coming;
    }

    public double getConsumption() {
        return consumption;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }


}
