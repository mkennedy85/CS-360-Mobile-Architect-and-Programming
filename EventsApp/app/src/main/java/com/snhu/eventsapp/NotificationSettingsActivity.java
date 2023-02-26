package com.snhu.eventsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;

public class NotificationSettingsActivity extends AppCompatActivity {

    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_notication);

        Switch notificationsToggle = findViewById(R.id.notifyToggle);

        settings = PreferenceManager.getDefaultSharedPreferences(this);

        notificationsToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = settings.edit();
                if (isChecked) {
                    editor.putBoolean("pref_receive_notifications", true);
                    editor.commit();
                } else {
                    editor.putBoolean("pref_receive_notifications", false);
                    editor.commit();
                }
            }
        });
    }

    public void closeSettings(View view) {
        Intent intent = new Intent(getApplicationContext(), EventListActivity.class);
        startActivity(intent);
    }
}
