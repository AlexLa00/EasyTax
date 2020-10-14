package app.cyberzen.easytax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import app.cyberzen.easytax.ui.login.LoginActivity;

public class WelcomeActivity extends AppCompatActivity {
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        loginBtn =(Button) findViewById(R.id.LoginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent loginW = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(loginW);
            }
        });
    }

    }
