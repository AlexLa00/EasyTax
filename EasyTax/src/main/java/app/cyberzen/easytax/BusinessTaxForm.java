package app.cyberzen.easytax;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class BusinessTaxForm extends AppCompatActivity {
    EditText tx1, tx2, tx3, tx4, tx5, tx6, tx7, tx8, tx9, tx10, tx11, tx12;
    Button cal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);

        cal = (Button) findViewById(R.id.getSum);
        cal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                tx1 = (EditText) findViewById(R.id.PTFA1);
                tx2 = (EditText) findViewById(R.id.PTFA2);
                tx3 = (EditText) findViewById(R.id.PTFA3);
                tx4 = (EditText) findViewById(R.id.PTFA4);

                float num1 = Float.parseFloat(tx1.getText().toString());
                float num2 = Float.parseFloat(tx2.getText().toString());
                float num3 = Float.parseFloat(tx3.getText().toString());
                float num4 = Float.parseFloat(tx4.getText().toString());

                tx5 = (EditText) findViewById(R.id.PTFA1);
                tx6 = (EditText) findViewById(R.id.PTFA2);
                tx7 = (EditText) findViewById(R.id.PTFA3);
                tx8 = (EditText) findViewById(R.id.PTFA4);

                float num5 = Float.parseFloat(tx5.getText().toString());
                float num6 = Float.parseFloat(tx6.getText().toString());
                float num7 = Float.parseFloat(tx7.getText().toString());
                float num8 = Float.parseFloat(tx8.getText().toString());

                tx9 = (EditText) findViewById(R.id.PTFA1);
                tx10 = (EditText) findViewById(R.id.PTFA2);
                tx11= (EditText) findViewById(R.id.PTFA3);
                tx12 = (EditText) findViewById(R.id.PTFA4);

                float num9 = Float.parseFloat(tx9.getText().toString());
                float num10 = Float.parseFloat(tx10.getText().toString());
                float num11 = Float.parseFloat(tx11.getText().toString());
                float num12 = Float.parseFloat(tx12.getText().toString());

                float sum = num1 + num2 + num3 + num4 + num5 + num6 + num7 + num8 + num9 + num10 + num11 + num12; //Answer

                EditText editText = (EditText)findViewById(R.id.PTFA13);
                editText.setText(String.valueOf(sum));
            }
        });


    }
}

