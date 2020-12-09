 package app.cyberzen.easytax;
//CyberZen
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;


public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {
    private Button loginBtn;
    private TextView register;
    final Context context = this;

    Dialog myDialog;
    Button btnYes,BtnNo;

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
                Intent loginW = new Intent(getApplicationContext(), login_user.class);
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

    @Override
    public void onBackPressed() {
        MyCustomAlertDialog();
    }
    public void MyCustomAlertDialog()
    {
        myDialog = new Dialog(WelcomeActivity.this);
        myDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        myDialog.setContentView(R.layout.customdialog);
        myDialog.setTitle("My Customer Dialog");
        BtnNo = (Button)myDialog.findViewById(R.id.noBtn);
        btnYes = (Button)myDialog.findViewById(R.id.yesBtn);

        BtnNo.setEnabled(true);
        btnYes.setEnabled(true);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });

        BtnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.cancel();
            }
        });
        myDialog.show();
    }
}