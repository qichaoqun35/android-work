package com.example.app3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Intent mIntent = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Button buttonFile = findViewById(R.id.button_file);
        Button buttonShared = findViewById(R.id.button_shared);
        Button buttonContacts = findViewById(R.id.button_contacts);

        buttonFile.setOnClickListener(this);
        buttonShared.setOnClickListener(this);
        buttonContacts.setOnClickListener(this);

        mIntent = new Intent();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_file:
                mIntent.setClass(this,SaveUserActivity.class);
                break;
            case R.id.button_shared:
                mIntent.setClass(this,SaveUserActivity2.class);
                break;
            case R.id.button_contacts:
                mIntent.setClass(this,ContactsActivity.class);
                break;
                default:
        }
        startActivity(mIntent);
    }
}
