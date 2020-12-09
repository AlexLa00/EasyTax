package app.cyberzen.easytax;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.ActionBar;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.appcompat.widget.Toolbar;
        import androidx.core.view.GravityCompat;
        import androidx.drawerlayout.widget.DrawerLayout;
        import androidx.fragment.app.Fragment;
        import androidx.fragment.app.FragmentManager;
        import androidx.fragment.app.FragmentTransaction;

        import android.content.Intent;
        import android.os.Bundle;
        import android.os.Handler;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageButton;
        import android.widget.TextView;
        import android.widget.Toast;


        import com.google.android.gms.auth.api.signin.GoogleSignIn;
        import com.google.android.gms.auth.api.signin.GoogleSignInClient;
        import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.android.material.navigation.NavigationView;
        import com.google.gson.Gson;

        import app.cyberzen.easytax.model.User;

public class HomeScreen extends AppCompatActivity {
    private Button personalTax, registeredComplete;
    private Button familyTax;
    private DrawerLayout mDrawerLayout;
    private Button logout;

    private GoogleSignInClient googleSignInClient;

    public void openPersonalTaxOne() {
        Intent intent = new Intent(this, Scroll.class);
        startActivity(intent);
    }

    public void openBusinessTaxOne() {
        Intent intent = new Intent(this, BusinessTaxForm.class);
        startActivity(intent);
    }

    public void openFamilyTaxOne() {
        Intent intent = new Intent(this, FamilyTaxForm.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        personalTax = (Button) findViewById(R.id.PersonalTax);
        Button businessTax = (Button) findViewById(R.id.BusinessTax);
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
//Signout
       // logout=findViewById(R.id.nav_logout);




        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setHomeAsUpIndicator(R.drawable.ic_drawer);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView mNavigationView = findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment newFragment;
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                menuItem.setChecked(true);

                mDrawerLayout.closeDrawers();

                int id = menuItem.getItemId();
                if (id == R.id.nav_save) {
                   newFragment = new MenuSave();
                   transaction.replace(R.id.content_frame, newFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();

                } else if (id == R.id.nav_help) {
                    newFragment = new MenuHelp();
                    transaction.replace(R.id.content_frame, newFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();

                } else if (id == R.id.nav_setting) {
                    newFragment = new MenuSetting();
                    transaction.replace(R.id.content_frame, newFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();

                } else if (id == R.id.nav_logout) {
                    newFragment = new MenuSetting();
                    transaction.replace(R.id.content_frame, newFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();


                }
                Toast.makeText(getApplicationContext(), menuItem.getTitle(),
                        Toast.LENGTH_LONG).show();
                return true;
            }
        });
        mDrawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Respond when the drawer is opened
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Respond when the drawer is closed
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }
                }
        );

    }
    private void signOut(){
        googleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
    }
    private void revokeAccess() {
        googleSignInClient.revokeAccess()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;

        }

        return super.onOptionsItemSelected(item);
    }}