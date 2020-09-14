package com.example.multiplelanguageapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocals();
        setContentView(R.layout.activity_main);
        //Adding ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));

        Button changeLang = findViewById(R.id.changeLang);
        changeLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //show alertdialog to display list of language, one can be selected
                showChangeLangauge();
            }
        });
    }

    private void showChangeLangauge() {
        //array of language to display data in alert dialog
        final String[] listItems = {"French", "Hindi", "Nepali", "Urdu", "English"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        mBuilder.setTitle("Choose Language..");
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if(i==0){
                    //French
                    setLocal("fr");
                    recreate();
                }
               else if(i==1){
                    //Hindi
                    setLocal("hi");
                    recreate();
                }
                else if(i==2){
                    //Hindi
                    setLocal("ne");
                    recreate();
                }
                else if(i==3){
                    //Hindi
                    setLocal("ur");
                    recreate();
                }
                else if(i==4){
                    //Hindi
                    setLocal("en");
                    recreate();
                }
                //dismiss alert dialog when language selected
                dialog.dismiss();
            }
        });
        AlertDialog mDialog = mBuilder.create();
        //show alertDialog
        mDialog.show();
    }

    private void setLocal(String lan) {
        Locale locale = new Locale(lan);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());
        //save data to shared preferences
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lan);
        editor.apply();
    }
    //load language saved in shared preferences
    public  void loadLocals(){
        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        String language = prefs.getString("My_Lang", "");
        setLocal(language);
    }
}