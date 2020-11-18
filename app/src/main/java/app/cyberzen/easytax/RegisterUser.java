package app.cyberzen.easytax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import app.cyberzen.easytax.login_user;

public class RegisterUser extends AppCompatActivity {
    private EditText rname, remail, rpswd, rconfigpswd,rphone;
    private String name, email, password, configPassword,phone;
    private Button regbtn;

    //database values



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        rname =(EditText) findViewById(R.id.fullname_insert);
        rphone =(EditText) findViewById(R.id.phone_insert);
        remail =(EditText) findViewById(R.id.email_insert);
        rpswd =(EditText) findViewById(R.id.password_insert);
        rconfigpswd =(EditText) findViewById(R.id.confirm_password_insert);
        regbtn=(Button) findViewById(R.id.registerBtn);
        regbtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                register();
            }

        });


    }

    public void register()
    {

        initilize();//initilize given data to a trimmed string
        if(!Validate()){
            Toast.makeText(this,"Registrtion failed please try again",Toast.LENGTH_SHORT).show();
        }
        else{
            RegistrationSuccess();
        }
    }

    public void initilize()
    {
        name=rname.getText().toString().trim();
        phone=rphone.getText().toString().trim();
        email=remail.getText().toString().trim();
        password=rpswd.getText().toString().trim();
        configPassword=rconfigpswd.getText().toString().trim();
    }
    public boolean Validate(){
        boolean valid=true;

        if(name.isEmpty()||name.length()<3){
            rname.setError("Please enter a valid name");
            valid=false;
        }
        if(phone.isEmpty()||phone.length()!=10){
            rphone.setError("Please enter a valid phone number");
            valid=false;
        }
        if(email.isEmpty()||!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            remail.setError("Invalid email");
            valid=false;
        }
        Pattern pattern;
        Matcher matcher2;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher2 = pattern.matcher(password);
        if(password.isEmpty()||password.length()<7||!matcher2.matches()){
            rpswd.setError("Password must contain number, upper case & lowwer case and special Char");
            valid=false;
        }
        if(configPassword.isEmpty()||!configPassword.equals(password)){
            rconfigpswd.setError("Passwords does not match");
            valid=false;
        }
        return valid;
    }

    public void RegistrationSuccess(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");

        RegistrationHelper reghelper=new RegistrationHelper(phone, name,email, password);
        myRef.child(phone).setValue(reghelper);
        Toast.makeText(this,"Successfully Registered!",Toast.LENGTH_SHORT).show();
                Intent loginW = new Intent(getApplicationContext(), login_user.class);
                startActivity(loginW);

    }

}