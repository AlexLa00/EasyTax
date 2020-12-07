package app.cyberzen.easytax;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;


public class MenuSetting extends Fragment {

    private Button saveBtn;
    private Switch mySwitch;
    private RadioButton radio_brown;
    private RadioButton radio_purple;
    private RadioButton radio_gray;
    private RadioButton radio_DEFAULT;
    private LinearLayout settings;

    private RadioGroup radioGroup;
    private RadioButton radioButton;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    public static final String SWITCH1 = "switch1";

    private DrawerLayout mDrawerLayout;

    private boolean switchOnOff;

    public MenuSetting() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = (View) inflater.inflate(R.layout.menu_set, container, false);

        saveBtn = (Button)view.findViewById(R.id.ButtonSettingsSave);

        final LinearLayout settings =(LinearLayout) view.findViewById(R.id.settings);

        radioGroup = (RadioGroup)view.findViewById(R.id.colorGroup);
        radio_brown = (RadioButton) view.findViewById(R.id.BrownColorRadio);
        radio_purple = (RadioButton) view.findViewById(R.id.PurpleColorRadio);
        radio_gray = (RadioButton) view.findViewById(R.id.GreyColorRadio);
        radio_DEFAULT = (RadioButton) view.findViewById(R.id.DefaultColorRadio);

        radio_brown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settings.setBackgroundColor(Color.rgb(150,75,0));

            }
        });

        radio_purple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settings.setBackgroundColor(Color.rgb(128,0,128));

            }
        });

        radio_gray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settings.setBackgroundColor(Color.GRAY);

            }
        });

        radio_DEFAULT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settings.setBackgroundColor(Color.GREEN);

            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                saveData(view);
            }
        });
        loadData();

        return view;
    }

    public void saveData(View v)
    {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        editor.apply();
        Toast.makeText(getActivity(), "Data saved", Toast.LENGTH_SHORT).show();

    }
    private void fragmentAction(final View view) {

        Spinner mSpinner = (Spinner) view.findViewById(R.id.fontSpinner);

        ArrayAdapter<String> madapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.fontSize));
        madapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(madapter);
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

    }

    }