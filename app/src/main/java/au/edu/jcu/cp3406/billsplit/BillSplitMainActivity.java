package au.edu.jcu.cp3406.billsplit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.MessageFormat;

public class BillSplitMainActivity extends AppCompatActivity {

    private Bill bill;
    private EditText billAmount;
    private EditText numberOfPeople;
    private TextView totalPerPersonView;
    private int amount;
    private int people;
    private int tipAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        billAmount = findViewById(R.id.billAmount);
        numberOfPeople = findViewById(R.id.numberOfPeople);
        totalPerPersonView = findViewById(R.id.totalPerPerson);

        tipAmount = 0; // default value

        if (savedInstanceState == null) {
            bill = new Bill();
        } else {
            amount = savedInstanceState.getInt("amount");
            people = savedInstanceState.getInt("people");
            tipAmount = savedInstanceState.getInt("tipAmount");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("amount", amount);
        outState.putInt("people", people);
        outState.putInt("tipAmount", tipAmount);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SettingsActivity.SETTINGS_REQUEST) {
            if (resultCode == RESULT_OK) {
                if (data!=null) {
                    tipAmount = data.getIntExtra("tipAmount", 0);
                }
            }
        }
    }

    public void settingsClicked(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivityForResult(intent, SettingsActivity.SETTINGS_REQUEST);
    }

    public void resetButtonClicked(View view) {
        bill.resetBill();
        billAmount.setText("");
        numberOfPeople.setText("");
        totalPerPersonView.setText(bill.toString());
    }

    public void calculateButtonClicked(View view) {

        // Get user input from UI
        amount = Integer.parseInt(billAmount.getText().toString());
        people = Integer.parseInt(numberOfPeople.getText().toString());

        // Update the Bill object values
        bill.setAmount(amount);
        bill.setNumberOfPeople(people);

        bill.calculateTotalPerPerson(tipAmount);
        String perPersonString = bill.toString();
        String output;

        if (tipAmount > 0) {
            output = MessageFormat.format("{0}/ person (includes {1}% tip)", perPersonString, tipAmount);
        } else {
            output = perPersonString + "/ person";
        }

        totalPerPersonView.setText(output);
    }
}