package app.cyberzen.easytax;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class BusinessTaxForm extends AppCompatActivity {
    Button submit;
    EditText yearlyincome;
    Button saved;
    EditText previousIncome;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_form);
        submit = (Button)findViewById(R.id.personalTaxRefund);
        yearlyincome = (EditText)findViewById(R.id.yearlyIncome);
        saved = (Button)findViewById(R.id.saveTaxRefund4);

        saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Progress Saved!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonCalculateClick(view);
                String income = yearlyincome.getText().toString();

                Intent intent = new Intent(BusinessTaxForm.this, TotalClaimAmount.class);
                intent.putExtra("key", income);

                startActivity(intent);
            }
        });
    }

    public void onButtonCalculateClick(View v) {
        double income, tax, total;
        int status = 0;
        int pIncome;
        tax = 0;

        EditText yearlyIncome = (EditText) findViewById(R.id.yearlyIncome);
        previousIncome = (EditText) findViewById(R.id.paidIncome3);
        pIncome = Integer.parseInt(previousIncome.getText().toString());
        TextView t1 = (TextView) findViewById(R.id.taxesOwed);
        income = Integer.parseInt(yearlyIncome.getText().toString());



        //Candian income tax reference
        //https://www.canada.ca/en/revenue-agency/services/tax/individuals/frequently-asked-questions-individuals/canadian-income-tax-rates-individuals-current-previous-years.html

        if (status == 0) {
            if (income <= 11950)
                tax = (income  * 0.10)- pIncome;
            else if (income <= 45500)
                tax = (11950 * 0.10 + (income - 11950) * 0.15) - pIncome;
            else if (income <= 117450)
                tax = (11950 * 0.10 + (45500 - 11950) * 0.15 + (income - 45500) * 0.25)- pIncome ;
            else if (income <= 190200)
                tax = (11950 * 0.10 + (45500 - 11950) * 0.15 + (117450 - 45500) * 0.25 +
                        (income - 117450) * 0.28) - pIncome;
            else if (income <= 372950)
                tax = (11950 * 0.10 + (45500 - 11950) * 0.15 + (117450 - 45500) * 0.25  +
                        (190200 - 117450) * 0.28 + (income - 190200) * 0.33) -pIncome;
            else
                tax = (11950 * 0.10 + (45500 - 11950) * 0.15 +
                        (117451 - 45500) * 0.25 + (190200 - 117450) * 0.28 +
                        (372950 - 190200) * 0.33 + (income - 372950) * 0.35) - pIncome ;
        } else {
        }
        total = ((tax * 100) / 100.0);
        yearlyIncome.setText("$" + Double.toString(total));

    }

}
