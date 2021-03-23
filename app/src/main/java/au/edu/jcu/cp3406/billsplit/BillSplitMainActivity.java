package au.edu.jcu.cp3406.billsplit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class BillSplitMainActivity extends AppCompatActivity {

    private Bill bill;
    private EditText billAmount;
    private EditText numberOfPeople;
    private TextView totalPerPersonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        billAmount = findViewById(R.id.billAmount);
        numberOfPeople = findViewById(R.id.numberOfPeople);
        totalPerPersonView = findViewById(R.id.totalPerPerson);
        bill = new Bill();

    }

    public void resetButtonClicked(View view) {
        bill.resetBill();
        billAmount.setText("");
        numberOfPeople.setText("");
        totalPerPersonView.setText(bill.toString());
    }

    public void calculateButtonClicked(View view) {

        // Get user input from UI
        int amount = Integer.parseInt(billAmount.getText().toString());
        int people = Integer.parseInt(numberOfPeople.getText().toString());

        // Update the Bill object values
        bill.setAmount(amount);
        bill.setNumberOfPeople(people);

        bill.calculateTotalPerPerson();
        totalPerPersonView.setText(bill.toString());
    }
}