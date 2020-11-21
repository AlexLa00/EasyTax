package app.cyberzen.easytax;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;

public class HomeScreen extends AppCompatActivity {
    private Button personalTax, registeredComplete;
    private Button businessTax;
    private Button familyTax;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        personalTax = (Button) findViewById(R.id.PersonalTax);
        businessTax = (Button) findViewById(R.id.BusinessTax);
        familyTax = (Button) findViewById(R.id.FamilyTax);

        personalTax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPersonalTaxOne();
            }
        });

        businessTax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBusinessTaxOne();
            }
        });

        familyTax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFamilyTaxOne();
            }
        });

    }

    public void openPersonalTaxOne()
    {
        Intent intent = new  Intent(this,  Scroll.class);
        startActivity(intent);
    }

    public void openBusinessTaxOne()
    {
        Intent intent = new  Intent(this,  BusinessTaxForm.class);
        startActivity(intent);
    }

    public void openFamilyTaxOne()
    {
        Intent intent = new  Intent(this,  FamilyTaxForm.class);
        startActivity(intent);
    }
}