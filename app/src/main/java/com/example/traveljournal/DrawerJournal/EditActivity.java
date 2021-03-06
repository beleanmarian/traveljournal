package com.example.traveljournal.DrawerJournal;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.traveljournal.DrawerJournal.ui.home.HomeFragment;
import com.example.traveljournal.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.Date;

public class EditActivity extends AppCompatActivity {

    private EditText tripName;
    private EditText tripDestination;
    private Button saveButton;

    private static FragmentManager fragmentManager;

    private DatePickerDialog datePickerDialog;
    private Button dateButton1;
    private Button dateButton2;

    private Calendar startDate;
    private Calendar endDate;

    //here we get the texts from HomeFragment
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.traveljournal.R.layout.activity_edit);

        initDatePicker();
        dateButton1 = findViewById(R.id.start_date_button);
        dateButton2 = findViewById(R.id.end_date_button);
        dateButton1.setText(getTodaysDate());
        dateButton2.setText(getTodaysDate());

        tripName = findViewById(R.id.trip_name_editText);
        tripDestination = findViewById(R.id.destination_editText);
        saveButton = findViewById(R.id.save_data);

        Intent intent = getIntent(); // get the data From Fragment
        String s = intent.getStringExtra("key");
        String s2 = intent.getStringExtra("key1");
        tripName.setText(s);
        tripDestination.setText(s2);

        fragmentManager = getSupportFragmentManager();//Get Fragment Manager


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tripNameEdit = tripName.getText().toString(); //store the edited value in a string variable
                String tripDestinationEdit = tripDestination.getText().toString();


                Fragment argumentFragment = new HomeFragment();//Get Fragment Instance
                Bundle data = new Bundle();//Use bundle to pass data
                data.putString("tripName", tripNameEdit);//put string, int, etc in bundle with a key value
                data.putString("tripDestination", tripDestinationEdit);//put string, int, etc in bundle with a key value
                argumentFragment.setArguments(data);//Finally set argument bundle to fragment

                fragmentManager.beginTransaction().replace(R.id.cstrLayout, argumentFragment).commit();//now replace the argument fragment
            }
        });

    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month += 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);

        return makeDateString(day, month, year);

    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                month += 1; //initially it is 0
                String date1 = makeDateString(day, month, year);
                String date2 = makeDateString(day, month, year);
                dateButton1.setText(date1);
                dateButton2.setText(date2);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;//a style for DatePicker

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }


    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    //here we convert from month no 1 to January, month no 2 to February  etc.
    private String getMonthFormat(int month) {
        if (month == 1)
            return "JAN";
        if (month == 2)
            return "FEB";
        if (month == 3)
            return "MAR";
        if (month == 4)
            return "APR";
        if (month == 5)
            return "MAY";
        if (month == 6)
            return "JUN";
        if (month == 7)
            return "JUL";
        if (month == 8)
            return "AUG";
        if (month == 9)
            return "SEP";
        if (month == 10)
            return "OCT";
        if (month == 11)
            return "NOV";
        if (month == 12)
            return "DEC";
        return "JAN";
    }

    //date picker
    public void openDatePicker(View view) {
        datePickerDialog.show();
    }
    public void openDatePicker2(View view) {
        datePickerDialog.show();
    }
}
