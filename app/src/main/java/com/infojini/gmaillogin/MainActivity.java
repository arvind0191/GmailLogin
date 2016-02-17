package com.infojini.gmaillogin;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.infojini.gmaillogin.DataClass.UserInformation;
import com.infojini.gmaillogin.Myinterface.OngmailDataListener;

public class MainActivity extends GmailLoginActivity implements OngmailDataListener


{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
    }


    @Override
    public void OnErrorListener(String Message) {
        Toast.makeText(MainActivity.this,Message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnSuccessListener(UserInformation userInformation) {
        Toast.makeText(MainActivity.this,"Welcome :- "+userInformation.Name,Toast.LENGTH_SHORT).show();
    }
}
