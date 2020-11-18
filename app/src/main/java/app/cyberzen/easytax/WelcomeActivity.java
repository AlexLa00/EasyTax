package app.cyberzen.easytax;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

import app.cyberzen.easytax.ui.login.LoginActivity;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {
    private Button loginBtn;
    private TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        loginBtn = (Button) findViewById(R.id.LoginBtn);
        register = (TextView) findViewById(R.id.Register);

        register.setOnClickListener(this);


        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent loginW = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(loginW);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.Register:
                startActivity(new Intent(this, RegisterUser.class));
                break;
        }
    }
}