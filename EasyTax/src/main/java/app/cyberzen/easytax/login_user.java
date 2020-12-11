package app.cyberzen.easytax;
//CyberZen
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import app.cyberzen.easytax.auth.AuthListener;
import app.cyberzen.easytax.auth.GoogleAuth;
import app.cyberzen.easytax.model.User;


public class login_user extends AppCompatActivity {
    private GoogleAuth googleAuth;
    EditText usernameInput;
    EditText passwordInput;
    Button loginbtn;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;
    private CheckBox rememberme;
    private SharedPreferences mprefs;
    private static final String PREFS_NAME = "PrefsFile";
    String uname, usrpwd;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
        mprefs=getSharedPreferences(PREFS_NAME,MODE_PRIVATE);


        Button btn_google = findViewById(R.id.googlebtn);
        btn_google.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                auth();
            }
        });


        usernameInput = findViewById(R.id.Email);
        passwordInput = findViewById(R.id.password);

        rememberme=(CheckBox) findViewById(R.id.rememberCB);
        loginbtn=(Button) findViewById(R.id.login);
        loginbtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                login();
            }

        });

        //login remember me shared pref
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();
        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true) {
            usernameInput.setText(loginPreferences.getString("username", ""));
            passwordInput.setText(loginPreferences.getString("password", ""));
            rememberme.setChecked(true);
        }


        TextView login  = (TextView) findViewById(R.id.Help1);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewIn) {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://support.google.com/?hl=en/"));
                startActivity(myIntent);
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        googleAuth.activityResult(requestCode,resultCode,data);

    }
    @Override
    protected void onStart() {
        GoogleSignInAccount account= GoogleSignIn.getLastSignedInAccount(this);
        if(account != null){
        }

        super.onStart();
    }

    private void auth(){

        googleAuth = new GoogleAuth(login_user.this, new AuthListener() {

            @Override
            public void OnAuthentication(User user) {
                Intent intent = new Intent(login_user.this, HomeScreen.class);
                intent.putExtra("user", new Gson().toJson(user));
                startActivity(intent);
                finish();
            }
        });

    }

    public void login() {

        initilize();//initilize given data to a trimmed string

        //remember me
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(usernameInput.getWindowToken(), 0);

        if (rememberme.isChecked()) {
            loginPrefsEditor.putBoolean("saveLogin", true);
            loginPrefsEditor.putString("username", uname);
            loginPrefsEditor.putString("password", usrpwd);
            loginPrefsEditor.commit();
        }

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


