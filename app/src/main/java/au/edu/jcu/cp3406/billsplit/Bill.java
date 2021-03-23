package au.edu.jcu.cp3406.billsplit;

import java.text.DecimalFormat;

public class Bill {
    private float total;
    private float numberOfPeople;
    private float totalPerPerson;

    public Bill(int total, int numberOfPeople) {
        this.total = total;
        this.numberOfPeople = numberOfPeople;
    }

    public void calculateTotalPerPerson() {
        totalPerPerson = total / numberOfPeople;
    }

    public void resetBill() {
        total = numberOfPeople = totalPerPerson = 0;
    }

    public String toString() {
        DecimalFormat df = new DecimalFormat("#.00");
        String totalAsString = df.format(totalPerPerson);
        return "$" + totalAsString;
    }

}
