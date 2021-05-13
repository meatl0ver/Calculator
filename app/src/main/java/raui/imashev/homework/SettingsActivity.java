package raui.imashev.homework;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {


    private SwitchCompat switchTheme;
    private SwitchCompat switchLang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        switchTheme = findViewById(R.id.switchTheme);
        switchLang = findViewById(R.id.switchLang);
        boolean isLang = getResources().getString(R.string.app_name).equals("Домашняя работа");
        switchLang.setChecked(!isLang);

        int defaultNightMode = AppCompatDelegate.getDefaultNightMode();
        switchTheme.setChecked(defaultNightMode != AppCompatDelegate.MODE_NIGHT_YES);
    }


    public void onClickSave(View view) {

        if (switchTheme.isChecked()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        String lang;
        if (switchLang.isChecked()) {
            lang = "en";
        } else {
            lang = "ru";
        }
        Configuration config = new Configuration();
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        getResources().updateConfiguration(config, null);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}