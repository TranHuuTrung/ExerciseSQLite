package com.huutrung.exercisesqlite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Add extends AppCompatActivity {
    private EditText edtName, edtNumber, edtAddress;
    private Button btnAdd, btnCancle;
    private MyDataBase dataBase;
    private String gendle;
    private RadioButton rdMale;
    private RadioButton rdFemale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        init();
        dataBase = new MyDataBase(this, "contacts.sqlite", null, 1);
    }
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.btnCancle:
                finish();
                break;
            case R.id.btnAdd:
                Add();
                finish();
                MainActivity.getDataContacts();
                break;
        }
    }
    private void Add() {

        String name=edtName.getText().toString();
        String number=edtNumber.getText().toString();
        String address=edtAddress.getText().toString();
        if(name==""||number==""||address=="")
        {
            Toast.makeText(this, "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }
        else {
            Date time = new Date();
            SimpleDateFormat dinhDangTime = new SimpleDateFormat("HH:mm");
            String showTime = dinhDangTime.format(time.getTime());
            //Date date = new Date();
            SimpleDateFormat dinhDangDate = new SimpleDateFormat("dd/MM/yyyy ");
            String showDate = dinhDangDate.format(time.getTime());
            if (rdFemale.isChecked()){
                gendle="Female";
            }
            else {
                gendle="Male";
            }
            Toast.makeText(this, gendle, Toast.LENGTH_SHORT).show();
            dataBase.QueryData("CREATE TABLE IF  NOT EXISTS Contact(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(200),number VARCHAR(200)" +
                    ",address VARCHAR(200), date VARCHAR(200), time VARCHAR(200), gendle VARCHAR(200))");
            dataBase.QueryData("INSERT INTO Contact VALUES(null,'"+name+"', '"+address+"'," +
                    "'"+number+"', '"+showDate+"', '"+showTime+"', '"+gendle+"')");

        }
    }

    private void init() {
        edtAddress = (EditText) findViewById(R.id.edtAddress);
        edtName = (EditText) findViewById(R.id.edtName);
        edtNumber = (EditText) findViewById(R.id.edtNumber);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        rdFemale= (RadioButton) findViewById(R.id.rdFemale);
        rdMale= (RadioButton) findViewById(R.id.rdMale);
        btnCancle = (Button) findViewById(R.id.btnCancle);
    }
}
