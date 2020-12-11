package app.cyberzen.easytax;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

public class TotalClaimAmount extends AppCompatActivity {

    TextView intro,amount;
    Button homebutton;
    Button saved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_claim_amount);
        intro = (TextView)findViewById(R.id.WelcomeIntro);
        amount = (TextView)findViewById(R.id.amountReturned);
        homebutton = (Button) findViewById(R.id.homebtn);
        saved = (Button)findViewById(R.id.savebtn2);


        saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Progress Saved!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TotalClaimAmount.this, HomeScreen.class);
                startActivity(intent);
            }
        });


        String income = getIntent().getStringExtra("key");

        amount.setText(income);

    }

}