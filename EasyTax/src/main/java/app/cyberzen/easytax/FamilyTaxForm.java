package app.cyberzen.easytax;
//CyberZen
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FamilyTaxForm extends AppCompatActivity {
    Button submit;
    EditText yearlyincome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.family_form);
        submit = (Button)findViewById(R.id.personalTaxRefund);
        yearlyincome = (EditText)findViewById(R.id.yearlyIncome);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonCalculateClick(view);
                String income = yearlyincome.getText().toString();
                Intent intent = new Intent(FamilyTaxForm.this, TotalClaimAmount.class);
                intent.putExtra("key",income);
                startActivity(intent);
            }
        });
    }

    public void onButtonCalculateClick(View v) {
        double income, tax, total;
        int status = 0;
        tax = 0;

        EditText yearlyIncome = (EditText)findViewById(R.id.yearlyIncome);
        TextView t1 = (TextView)findViewById(R.id.taxesOwed);
        income = Integer.parseInt(yearlyIncome.getText().toString());

        //Canadian income tax reference
        //https://www.canada.ca/en/revenue-agency/services/tax/individuals/frequently-asked-questions-individuals/canadian-income-tax-rates-individuals-current-previous-years.html

        if (status == 0)
        {
            if (income <= 16700)
                tax = income * 0.505;
            else if (income <= 67900)
                tax = 16700 * 0.10 + (income - 16700) * 0.15;
            else if (income <= 137050)
                tax = 16700 * 0.10 + (67900 - 16700) * 0.15 + (income - 67900) * 0.25;
            else if (income <= 208850)
                tax = 16700 * 0.10 + (67900 - 16700) * 0.15 + (137050 - 67900) * 0.25 +
                        (income - 137050) * 0.28;
            else if (income <= 372950)
                tax = 16700 * 0.10 + (67900 - 16700) * 0.15 + (137050 - 67900) * 0.25 +
                        (208850 - 137050) * 0.28 + (income - 208850) * 0.33;
            else
                tax = 16700 * 0.10 + (67900 - 16700) * 0.15 +
                        (137050 - 67900) * 0.25 + (208850 - 137050) * 0.28 +
                        (372950 - 208850) * 0.33 + (income - 372950) * 0.35;
        }
        else{}
        total = ((tax * 100) / 100.0);
        yearlyIncome.setText("$" + Double.toString(total));
    }
}

