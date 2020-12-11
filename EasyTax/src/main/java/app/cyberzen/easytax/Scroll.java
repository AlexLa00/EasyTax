package app.cyberzen.easytax;
//CyberZen
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class Scroll extends AppCompatActivity {
    Button submit;
    EditText yearlyincome;
    EditText previousIncome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        submit = (Button)findViewById(R.id.personalTaxRefund);
        yearlyincome = (EditText)findViewById(R.id.yearlyIncome);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonCalculateClick(view);
                String income = yearlyincome.getText().toString();
                Intent intent = new Intent(Scroll.this, TotalClaimAmount.class);
                intent.putExtra("key",income);
                startActivity(intent);
            }
        });
    }

    public void onButtonCalculateClick(View v) {
        double income, tax, total;
        int status = 0;
        int pIncome;
        tax = 0;

        previousIncome = (EditText) findViewById(R.id.paidIncome2);
        EditText yearlyIncome = (EditText)findViewById(R.id.yearlyIncome);
        TextView t1 = (TextView)findViewById(R.id.taxesOwed);
        income = Integer.parseInt(yearlyIncome.getText().toString());
        pIncome = Integer.parseInt(previousIncome.getText().toString());

        //Candian income tax reference
        //https://www.canada.ca/en/revenue-agency/services/tax/individuals/frequently-asked-questions-individuals/canadian-income-tax-rates-individuals-current-previous-years.html

        if (status == 0)
        {
            if (income <= 8350)
                tax = (income * 0.505) - pIncome;
            else if (income <= 33950)
                tax = (8350 * 0.10 + (income - 8350) * 0.15) - pIncome;
            else if (income <= 82250)
                tax = (8350 * 0.10 + (33950 - 8350) * 0.15 + (income - 33950) * 0.25) - pIncome;
            else if (income <= 171550)
                tax = (8350 * 0.10 + (33950 - 8350) * 0.15 + (82250 - 33950) * 0.25 +
                        (income - 82250) * 0.28) - pIncome;
            else if (income <= 372950)
                tax = (8350 * 0.10 + (33950 - 8350) * 0.15 + (82250 - 33950) * 0.25 +
                        (171550 - 82250) * 0.28 + (income - 171550) * 0.33) - pIncome;
            else
                tax = (8350 * 0.10 + (33950 - 8350) * 0.15 +
                        (82250 - 33950) * 0.25 + (171550 - 82250) * 0.28 +
                        (372950 - 171550) * 0.33 + (income - 372950) * 0.35) - pIncome;
        }
        else{}
        total = ((tax * 100) / 100.0);
        yearlyIncome.setText("$" + Double.toString(total));
    }
}

