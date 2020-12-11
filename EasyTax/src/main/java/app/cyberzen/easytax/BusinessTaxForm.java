package app.cyberzen.easytax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.time.Instant;

public class BusinessTaxForm extends AppCompatActivity {
    Button submit;
    EditText yearlyincome;
    Button saved;

    //tax algorithm
    private EditText emp_incomeInput,cap_gainInput,Tax_paidInput, rrspInput;
    private Double employ_inc,cap_gain,rate,taxable,tot_tax;
    private Double taxes_paid, rrsp,rrsp_gain,tot_ded;
    Double uget,tax_due;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_form);
        yearlyincome = (EditText) findViewById(R.id.yearlyIncome);
        saved = (Button) findViewById(R.id.saveTaxRefund2bus);

        initilize();
        submit = (Button)findViewById(R.id.personalTaxRefundbus);
        employ_inc=cap_gain=rate=taxable=tax_due=tot_tax=
                taxes_paid=rrsp=rrsp_gain=tot_ded= uget=0.00;

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
                String saved = "";
                if(uget<0){
                    uget.toString();
                    saved = "owe $"+uget;}
                else {
                    uget.toString();
                    saved = "Ret $"+uget;}
                if(saved.length()>13){saved=saved.substring(0, 13);}

                Intent intent = new Intent(BusinessTaxForm.this, TotalClaimAmount.class);
                intent.putExtra("key",saved);
                startActivity(intent);
            }
        });


  }

    public void initilize(){
        emp_incomeInput=(EditText) findViewById(R.id.yearlyIncomebus);
        cap_gainInput=(EditText) findViewById(R.id.yearlyIncome2bus);
        Tax_paidInput=(EditText) findViewById(R.id.paidIncome2bus);
        rrspInput=(EditText) findViewById(R.id.paidIncome3bus);

    }

    public void init(){
        if(emp_incomeInput.getText().toString().isEmpty()){emp_incomeInput.setText("0");}
        if(cap_gainInput.getText().toString().isEmpty()){cap_gainInput.setText("0");}
        if(Tax_paidInput.getText().toString().isEmpty()){Tax_paidInput.setText("0");}
        if(rrspInput.getText().toString().isEmpty()){rrspInput.setText("0");}

        employ_inc = Double.parseDouble(emp_incomeInput.getText().toString());
        cap_gain = Double.parseDouble(cap_gainInput.getText().toString());
        taxes_paid = Double.parseDouble(Tax_paidInput.getText().toString());
        rrsp = Double.parseDouble(rrspInput.getText().toString());


    }
    public void onButtonCalculateClick(View v) {
        init();
        taxable=employ_inc+(cap_gain*0.5);


        //find rate
        if(taxable<5000000.00){rate=0.15+0.025;}
        if(taxable>500000.00&&taxable <1000000.00){rate=0.205+0.15;}
        if(taxable>1000000.00){rate=0.33+0.20;}

        //deductions
        if(rrsp<20000&&rrsp>5000){rate=rate-0.02;}
        if(rrsp>20000&&rrsp<40000){rate=rate-0.04;}
        if(rrsp>20000&&rrsp<40000){rate=rate-0.05;}
        if(rrsp>100000&&rrsp<300000){rate=rate-0.08;}
        if(rrsp>300000){rate=rate-0.11;}
        tot_ded= taxes_paid;

        //total tax
        tot_tax=taxable*rate;

        //the end result
        uget=tot_ded-tot_tax;

    }



}
