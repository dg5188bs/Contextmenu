package com.example.contextmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    ToggleButton tg;
    EditText et1, et2;
    Double first,multiplier;
    int type;
    Intent si;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tg = (ToggleButton) findViewById(R.id.toggleButton);
        et1 = (EditText) findViewById(R.id.editTextNumberSigned);
        et2 = (EditText) findViewById(R.id.editTextNumberSigned2);
        si = new Intent(this,MainActivity3.class);
    }

    public boolean validnumber(String str) {
        if (str.equals("") || str.equals("-") || str.equals(".") || str.equals("-.")) {
            return false;
        }
        else {
            return true;
        }
    }


    public void Series(View view) {
        String st1 = et1.getText().toString();
        boolean legalnum1 = validnumber(st1);
        String st2 = et2.getText().toString();
        boolean legalnum2 = validnumber(st2);
        if(legalnum1 && legalnum2) {
            first = Double.parseDouble(st1);
            multiplier = Double.parseDouble(st2);
            if(tg.isChecked()){
                type = 1;
            }
            else{
                type = 0;
            }
            Intent si = new Intent(this,MainActivity2.class);
            si.putExtra("first",first);
            si.putExtra("multiplier",multiplier);
            si.putExtra("type",type);
            startActivity(si);
        }
        else{
            Toast.makeText(this, "One or more of the numbers is invalid!", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        menu.add("credits");
        return  true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        String st = item.getTitle().toString();
        if(st.equals("credits")){
            startActivity(si);
        }
        return true;
    }
}