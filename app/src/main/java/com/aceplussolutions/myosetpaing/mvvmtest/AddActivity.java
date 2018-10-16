package com.aceplussolutions.myosetpaing.mvvmtest;

import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.aceplussolutions.myosetpaing.mvvmtest.model.Contact;
import com.aceplussolutions.myosetpaing.mvvmtest.viewModel.AddContactViewModel;
import com.aceplussolutions.myosetpaing.mvvmtest.viewModel.ContactListViewModel;

import java.util.Calendar;
import java.util.Date;

public class AddActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private Date date;
    private DatePickerDialog datePickerDialog;
    private Calendar calendar;

    private EditText edtName;
    private EditText edtPhno;

    private Button btnSetDate;

    private AddContactViewModel contactViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        edtName=findViewById(R.id.itemName);
        edtPhno=findViewById(R.id.itemPhno);
        btnSetDate=findViewById(R.id.dateButton);
        calendar=Calendar.getInstance();

        contactViewModel=ViewModelProviders.of(this).get(AddContactViewModel.class);

        datePickerDialog = new DatePickerDialog(this, AddActivity.this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtName.getText() == null || edtPhno.getText() == null || date == null)
                    Toast.makeText(AddActivity.this, "Missing fields", Toast.LENGTH_SHORT).show();
                else {
                    contactViewModel.addContact(new Contact(
                            edtName.getText().toString(),
                            edtPhno.getText().toString(),
                            date
                    ));
                    finish();
                }
            }
        });

        btnSetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        date = calendar.getTime();
    }
}
