package au.edu.jcu.cp3406.billsplit;

import java.text.DecimalFormat;

public class Bill {
    private float amount;
    private float numberOfPeople;
    private float totalPerPerson;

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setNumberOfPeople(float numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public void calculateTotalPerPerson() {
        totalPerPerson = amount / numberOfPeople;
    }

    public void resetBill() {
        amount = numberOfPeople = totalPerPerson = 0;
    }

    public String toString() {
        String totalAsString;
        if (totalPerPerson > 0) {
            DecimalFormat df = new DecimalFormat("#.00");
            totalAsString = df.format(totalPerPerson);
        } else {
            totalAsString = "0.00";
        }

        return "$" + totalAsString;
    }

}
