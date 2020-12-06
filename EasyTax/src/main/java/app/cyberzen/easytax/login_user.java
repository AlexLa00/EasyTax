package app.cyberzen.easytax;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class login_user extends AppCompatActivity {
    EditText usernameInput;
    EditText passwordInput;
    Button loginbtn;
    String uname, usrpwd;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        usernameInput = findViewById(R.id.Email);
        passwordInput = findViewById(R.id.password);

        loginbtn=(Button) findViewById(R.id.login);
        loginbtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                login();
            }

        });

    }

    public void login() {

        initilize();//initilize given data to a trimmed string
        if (!Validate()) {
            Toast.makeText(this, "Login failed please try again", Toast.LENGTH_SHORT).show();
        }

        //database stuff
        Query checkUser= FirebaseDatabase.getInstance().getReference("users").orderByChild("phone").equalTo(uname);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){
                    usernameInput.setError(null);

                    String storedpwd = snapshot.child(uname).child("password").getValue(String.class);
                    if(storedpwd.equals(usrpwd)){
                        LoginSuccess();
                        passwordInput.setError(null);

                    }
                    else{
                        passwordInput.setError("password does not match");

                        //Toast.makeText(this, "password does not match!", Toast.LENGTH_SHORT).show();


                    }
                }
                else{
                    usernameInput.setError("username does not exist");


                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
               noUser();
            }
        });
    }


    private void initilize() {
        uname = usernameInput.getText().toString().trim();
        usrpwd = passwordInput.getText().toString().trim();


    }




    private boolean Validate() {
        boolean valid = true;

        if (uname.isEmpty()) {
            usernameInput.setError("Please enter a valid name");
            valid = false;
        }
        if (usrpwd.isEmpty()) {
            passwordInput.setError("Please enter a valid phone number");
            valid = false;
        }

        return valid;
    }
    public void noUser(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Failed to connect to database!")

                .setCancelable(false);



        AlertDialog alert = builder.create();

        alert.show();
        Toast.makeText(this, "Failed to connect to database", Toast.LENGTH_SHORT).show();
    }
    public void LoginSuccess() {

        Toast.makeText(this, "Successfully logged in!", Toast.LENGTH_SHORT).show();
        Intent loginW = new Intent(getApplicationContext(), HomeScreen.class);
        startActivity(loginW);
    }
}

