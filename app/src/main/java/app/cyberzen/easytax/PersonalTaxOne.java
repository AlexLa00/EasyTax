package app.cyberzen.easytax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PersonalTaxOne extends AppCompatActivity {
    private Button buttonPT1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_tax_one);

        buttonPT1 = (Button) findViewById(R.id.buttonPT1);
        buttonPT1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPersonalTaxTwo();
            }
        });
    }
    public void openPersonalTaxTwo(){
        Intent intent = new  Intent(this, PersonalTaxTwo.class);
        startActivity(intent);
    }
}


