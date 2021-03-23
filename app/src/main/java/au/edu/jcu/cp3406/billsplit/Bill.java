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
        DecimalFormat df = new DecimalFormat("#.00");
        String totalAsString = df.format(totalPerPerson);
        return "$" + totalAsString;
    }

}
