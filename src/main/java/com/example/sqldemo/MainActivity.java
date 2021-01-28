package com.example.sqldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.sqldemo.DatabaseHelper.COLUMN_CITY;
import static com.example.sqldemo.DatabaseHelper.COLUMN_COUNTRY;
import static com.example.sqldemo.DatabaseHelper.COLUMN_EMAIL;
import static com.example.sqldemo.DatabaseHelper.COLUMN_GROUP_ID;
import static com.example.sqldemo.DatabaseHelper.COLUMN_GROUP_INFO;
import static com.example.sqldemo.DatabaseHelper.COLUMN_INFO;
import static com.example.sqldemo.DatabaseHelper.COLUMN_PASSWORD;
import static com.example.sqldemo.DatabaseHelper.COLUMN_ROOM;
import static com.example.sqldemo.DatabaseHelper.COLUMN_SUM;
import static com.example.sqldemo.DatabaseHelper.COLUMN_UNIVERSITY;
import static com.example.sqldemo.DatabaseHelper.TABLE_CITY;
import static com.example.sqldemo.DatabaseHelper.TABLE_GROUPS;
import static com.example.sqldemo.DatabaseHelper.TABLE_ROOM;
import static com.example.sqldemo.DatabaseHelper.TABLE_STUDENTS;
import static com.example.sqldemo.DatabaseHelper.TABLE_UNIVERSITY;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnLogin, btnCreateAccount;
    EditText et_name, et_email, et_password;
    Spinner groupSpinner1, groupSpinner2, groupSpinner3, groupSpinner4;
    CheckBox checkBox;

    DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;
    List<String> list1 = new ArrayList<String>();
    List<String> list2 = new ArrayList<String>();
    List<String> list3 = new ArrayList<String>();
    List<String> list4 = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();




    public void initViews(){
        btnLogin = findViewById(R.id.btnLogin);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);
        et_email = findViewById(R.id.et_email);
        et_name = findViewById(R.id.et_name);
        et_password = findViewById(R.id.et_password);
        groupSpinner1 = findViewById(R.id.groupSpinner1);
        groupSpinner2 = findViewById(R.id.groupSpinner2);
        groupSpinner3 = findViewById(R.id.groupSpinner3);
        groupSpinner4 = findViewById(R.id.groupSpinner4);
        checkBox = findViewById(R.id.checbox1);


        databaseHelper = new DatabaseHelper(this);
        sqLiteDatabase = databaseHelper.getWritableDatabase();



        initSpinner1();
        initSpinner2();
        initSpinner3();
        initSpinner4();

        btnCreateAccount.setOnClickListener(this);
        btnLogin.setOnClickListener(this);


    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checbox1:
                if (checked){
                    Toast.makeText(this, "Your choice: YES", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Your choice: NO", Toast.LENGTH_SHORT).show();
                }
                // Put some meat on the sandwich
        }
    }



    public void initSpinner1(){
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_CARS, null);

        if (cursor != null && cursor.getCount() > 0){
            while(cursor.moveToNext()){
                String univerName = cursor.getString(cursor.getColumnIndex(COLUMN_CARS));

                Log.i("Database", "University: " + univerName);

                list1.add(univerName);
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_group, list1);
        groupSpinner1.setAdapter(adapter);
    }

    public void initSpinner2(){
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_GROUPS, null);

        if (cursor != null && cursor.getCount() > 0){
            while(cursor.moveToNext()){
                String gInfo = cursor.getString(cursor.getColumnIndex(COLUMN_GROUP_INFO));
                String gSum = cursor.getString(cursor.getColumnIndex(COLUMN_SUM));
                String gId = cursor.getString(cursor.getColumnIndex(COLUMN_GROUP_ID));

                Log.i("Database", "Info: " + gInfo);
                Log.i("Database", "Sum: " + gSum);
                Log.i("Database", "ID: " + gId);

                list2.add(gInfo);
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_group, list2);
        groupSpinner2.setAdapter(adapter);
    }

    public void initSpinner3(){
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_CITY, null);

        if (cursor != null && cursor.getCount() > 0){
            while(cursor.moveToNext()){
                String countryName = cursor.getString(cursor.getColumnIndex(COLUMN_COUNTRY));
                String cityName = cursor.getString(cursor.getColumnIndex(COLUMN_CITY));

                Log.i("Database", "Country: " + countryName);
                Log.i("Database", "City: " + cityName);

                list3.add(cityName);
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_group, list3);
        groupSpinner3.setAdapter(adapter);
    }

    private void initSpinner4() {
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_ROOM, null);

        if (cursor != null && cursor.getCount() > 0){
            while(cursor.moveToNext()){
                String room = cursor.getString(cursor.getColumnIndex(COLUMN_ROOM));

                Log.i("Database", "Room: " + room);

                list4.add(room);
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_group, list4);
        groupSpinner4.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnCreateAccount:
                boolean createAccount = true;
                if (et_name.getText().toString().isEmpty()){
                    et_name.setError("Try again!");
                    createAccount = false;
                }

                if (et_email.getText().toString().isEmpty()){
                    et_email.setError("Try again!");
                    createAccount = false;
                }

                if (et_password.getText().toString().isEmpty()){
                    et_password.setError("Try again!");
                    createAccount = false;
                }

                if (createAccount){
                    ContentValues userValues = new ContentValues();
                    userValues.put(COLUMN_INFO, et_name.getText().toString());
                    userValues.put(COLUMN_EMAIL, et_email.getText().toString());
                    userValues.put(COLUMN_PASSWORD, et_password.getText().toString());

                    sqLiteDatabase.insert(TABLE_RIDERS, null, userValues);

                    Toast.makeText(this, "Create Account success!", Toast.LENGTH_SHORT).show();
                    showDatabasedData();
                }else{
                    Toast.makeText(this, "Create Account failed!", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btnLogin:
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
        }
    }

    private void showDatabasedData() {
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_RIDERS, null);

        if (cursor != null && cursor.getCount() > 0){
            while(cursor.moveToNext()){
                String fName = cursor.getString(cursor.getColumnIndex(COLUMN_INFO));
                String email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
                String password = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));

                Log.i("Database", "fullname: " + fName);
                Log.i("Database", "email: " + email);
                Log.i("Database", "password: " + password);
            }
        }
    }




}