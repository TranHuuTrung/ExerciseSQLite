package com.huutrung.exercisesqlite;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static MyDataBase dataBase;
    private static List<Contact> arrContacts;
    private static ContactAdapter contactAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        arrContacts = new ArrayList<Contact>();
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        contactAdapter = new ContactAdapter(arrContacts, this);
        recyclerView.setAdapter(contactAdapter);

        //create database
        dataBase = new MyDataBase(this, "contacts.sqlite", null, 1);
        //create
        dataBase.QueryData("CREATE TABLE IF  NOT EXISTS Contact(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(200),number VARCHAR(200),address VARCHAR(200), date VARCHAR(200), time VARCHAR(200), gendle VARCHAR(200))");
        getDataContacts();
    }

    public static void getDataContacts() {
        arrContacts.clear();
        Cursor dataContacts = dataBase.getData("SELECT * FROM Contact");
        while (dataContacts.moveToNext()) {
            int id = dataContacts.getInt(0);
            String name = dataContacts.getString(1);
            String address = dataContacts.getString(2);
            String number = dataContacts.getString(3);
            String date = dataContacts.getString(4);
            String time = dataContacts.getString(5);
            String gendle = dataContacts.getString(6);
            arrContacts.add(new Contact(id, name, address, number, date, time, gendle));
            contactAdapter.notifyDataSetChanged();
        }
    }

    public void diaglogDelete(final String name, final int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to delete contact  " + name + "?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dataBase.QueryData("DELETE FROM Contact WHERE id='" + id + "'");
                Toast.makeText(MainActivity.this, "Deleted contact "+name, Toast.LENGTH_SHORT).show();
                getDataContacts();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addContact:
                addDataBase();
                break;
            case R.id.deleteAll:
                dataBase.QueryData("DROP TABLE Contact");
                getDataContacts();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addDataBase() {
        Intent mIntent = new Intent(MainActivity.this, Add.class);
        startActivity(mIntent);
    }
}