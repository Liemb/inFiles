package com.example.infiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    EditText et;
    TextView tv;
    String st,str;

    /**
     * @author liem buchuk
     * @since 27/12/19
     * starting the app
     * text- the EditText object
     * paste- the TextView object
     * str- String value
     * str1- all the Strings together
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et =(EditText) findViewById(R.id.et);
        tv =(TextView) findViewById(R.id.tv);

        try {
            FileInputStream fis= openFileInput("test.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuffer sb = new StringBuffer();
            str = br.readLine();
            tv.setText(str);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * adding the new string to the other and saving them.
     */
    public void savebtn(View view) throws IOException {
        st= et.getText().toString();
        if (!st.contentEquals("null")){
            try {
                FileOutputStream fos = openFileOutput("test.txt",MODE_PRIVATE);
                OutputStreamWriter osw = new OutputStreamWriter(fos);
                BufferedWriter bw = new BufferedWriter(osw);
                if(str!=null){
                    str= str+st;}
                else{str=st;}
                bw.write(str);
                bw.close();
                tv.setText(str);
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }}

    }

    /**
     * reseting the data
     */
    public void reset(View view) {
        try {
            FileOutputStream fos = openFileOutput("test.txt",MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);
            str="";
            bw.write(str);
            bw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        et.setText("");
        tv.setText("");
    }

    /**
     * saving data in files and exiting the app.
     */
    public void exit(View view) {
        st= et.getText().toString();
        if(!st.contentEquals("null")){
            try {
                FileOutputStream fos = openFileOutput("test.txt",MODE_PRIVATE);
                OutputStreamWriter osw = new OutputStreamWriter(fos);
                BufferedWriter bw = new BufferedWriter(osw);
                if(str!=null){
                    str= str+st;}
                else{str=st;}
                bw.write(str);
                bw.close();
                tv.setText(str);
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finish();}
    }

    /**
     * inflating the  menu
     * @param menu
     * @return
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * moving to the credits activity
     * @param menu
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem menu) {
        String st = menu.getTitle().toString();
        if ((st.equals("credits"))) {
            Intent gi = new Intent(this, credits.class);
            startActivity(gi);
        }
        return true;
    }

}