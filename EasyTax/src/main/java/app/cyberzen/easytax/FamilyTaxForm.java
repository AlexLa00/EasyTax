package app.cyberzen.easytax;
//CyberZen
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class FamilyTaxForm extends AppCompatActivity {
    Button submit;
    EditText yearlyincome;
    Button saved;
    //tax algorithm
    private EditText emp_incomeInput,cap_gainInput,Tax_paidInput, rrspInput,numKidsInput;
    private Double employ_inc,cap_gain,rate,taxable,tot_tax;
    private Double taxes_paid, rrsp,rrsp_gain,tot_ded;
    Double uget,tax_due;
    int kids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.family_form);
        initilize();
        submit = (Button)findViewById(R.id.personalTaxRefundfam);
        employ_inc=cap_gain=rate=taxable=tax_due=tot_tax=
                taxes_paid=rrsp=rrsp_gain=tot_ded= uget=0.00;
        kids =0;
        yearlyincome = (EditText)findViewById(R.id.yearlyIncome);
        saved = (Button)findViewById(R.id.saveTaxRefund2fam);

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
                    saved = "Refund $"+uget;}

                Intent intent = new Intent(FamilyTaxForm.this, TotalClaimAmount.class);
                intent.putExtra("key",saved);
                startActivity(intent);
            }
        });
    }

    private void initilize() {
        emp_incomeInput=(EditText) findViewById(R.id.yearlyIncomefam);
        cap_gainInput=(EditText) findViewById(R.id.yearlyIncome2fam);
        Tax_paidInput=(EditText) findViewById(R.id.paidIncome2fam);
        rrspInput=(EditText) findViewById(R.id.paidIncome3fam);
        numKidsInput=(EditText) findViewById(R.id.numofKidsFam);
    }
    public void init(){
        if(emp_incomeInput.getText().toString().isEmpty()){emp_incomeInput.setText("0.00");}
        if(cap_gainInput.getText().toString().isEmpty()){cap_gainInput.setText("0.00");}
        if(Tax_paidInput.getText().toString().isEmpty()){Tax_paidInput.setText("0.00");}
        if(rrspInput.getText().toString().isEmpty()){rrspInput.setText("0.00");}
        if(numKidsInput.getText().toString().isEmpty()){numKidsInput.setText("0");}

        employ_inc = Double.parseDouble(emp_incomeInput.getText().toString());
        cap_gain = Double.parseDouble(cap_gainInput.getText().toString());
        taxes_paid = Double.parseDouble(Tax_paidInput.getText().toString());
        rrsp = Double.parseDouble(rrspInput.getText().toString());
        kids = Integer.parseInt(numKidsInput.getText().toString());


    }

    public void onButtonCalculateClick(View v) {
        init();
        taxable=employ_inc+(cap_gain*0.5);

        //deductions
        rrsp_gain=rrsp*0.027;
        tot_ded= taxes_paid+rrsp_gain;

        //find rate
        if(taxable<46605.00){rate=0.15;}
        if(taxable>46605.00&&taxable <93308.00){rate=0.205;}
        if(taxable>93308.00 &&taxable <144489.00){rate=0.26;}
        if(taxable>144489.00 &&taxable <205842.00){rate=0.29;}
        if(taxable>205842.00){rate=0.33;}

        //kids
        if(kids==1){rate=rate-0.01;}
        if(kids>1&&kids<=3){rate=rate-0.023;}
        if(kids<3){rate=rate-0.032;}

        //total tax
        tot_tax=taxable*rate;
        //the end result
        uget=tot_ded-tot_tax;

    }
}

