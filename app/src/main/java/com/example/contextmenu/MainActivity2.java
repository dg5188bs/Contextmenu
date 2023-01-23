package com.example.contextmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity implements View.OnCreateContextMenuListener {
    ListView Lv;
    TextView tv1;
    String[] arr = new String[20];
    Double[] sumArray = new Double[20];
    Double first;
    Double multiplier;
    int type;
    int index;
    Double sum;
    Intent si2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        si2 = new Intent(this,MainActivity3.class);
        sum = 0.0;
        tv1 = (TextView) findViewById(R.id.textView7);
        Lv = (ListView) findViewById(R.id.list);
        Intent gi = getIntent();
        first = gi.getDoubleExtra("first", -99999);
        multiplier = gi.getDoubleExtra("multiplier", -99999);
        type = gi.getIntExtra("type", -99999);
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, arr);
        Lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        Lv.setAdapter(adp);
        Lv.setOnCreateContextMenuListener(this);
        Lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                index = i;
                return false;
            }
        });


        if (type == 0) {
            Mathemtical();
        } else {
            Geometrical();
        }
    }

    public int e (String str){
        int s = str.indexOf(".");
        int e = str.indexOf("E");
        if(e==-1) {
            return (str.substring(s + 1)).length();
        }
        else{
            return (str.substring(s+1)).length()+Integer.parseInt(str.substring(e+1));
        }
    }
    public void Mathemtical() {
        for (int i = 0; i < 20; i++) {
            sumArray[i] = first;
            int check = e(Double.toString(first));
            String str = String.format("%.02f", first);
            if(check>2) {
                arr[i] = str + "E" + Integer.toString(check - 2);
            }
            else {
                arr[i] = str;
            }
            first = first+multiplier;
        }
    }

    public void Geometrical() {
        for (int i = 0; i < 20; i++) {
            sumArray[i] = first;
            int check = e(Double.toString(first));
            String str = String.format("%.02f", first);
            if(check>2) {
                arr[i] = str + "E" + Integer.toString(check - 2);
            }
            else {
                arr[i] = str;
            }
            first = first*multiplier;
        }
    }

    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("select");
        menu.add("index");
        menu.add("sum");
    }

    public boolean onContextItemSelected(MenuItem item) {
        String st=item.getTitle().toString();
        if (st.equals("index")) {
            tv1.setText(Integer.toString(index));
        }
        if(st.equals("sum")){
            System.out.println("fg");
            sum = 0.0;
            for(int k=0;k<=index;k++){
                sum = sum+sumArray[k];
            }
            int check = e(Double.toString(sum));
            String str = String.format("%.02f", sum);
            if(check>2) {
                tv1.setText(str + "E" + Integer.toString(check - 2));
            }
            else {
                tv1.setText(str);
            }
        }
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu){
        menu.add("credits");
        return  true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        String st = item.getTitle().toString();
        if(st.equals("credits")){
            startActivity(si2);
        }
        return true;
    }






    public void back(View view) {
        finish();
    }



}
