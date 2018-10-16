package com.aceplussolutions.myosetpaing.mvvmtest;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.aceplussolutions.myosetpaing.mvvmtest.adapter.ContactRecyclerViewAdapter;
import com.aceplussolutions.myosetpaing.mvvmtest.model.Contact;
import com.aceplussolutions.myosetpaing.mvvmtest.viewModel.ContactListViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements View.OnLongClickListener{

    private ContactListViewModel contactListViewModel;
    private ContactRecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddActivity.class));
            }
        });

        recyclerView=findViewById(R.id.rvList);
        recyclerViewAdapter=new ContactRecyclerViewAdapter(new ArrayList<Contact>(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);

        contactListViewModel=ViewModelProviders.of(this).get(ContactListViewModel.class);

        contactListViewModel.getContactList().observe(MainActivity.this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(@Nullable List<Contact> contacts) {
                recyclerViewAdapter.setContactList(contacts);
            }
        });

    }


    @Override
    public boolean onLongClick(View v) {
        Contact contact=(Contact)v.getTag();
        contactListViewModel.deleteContact(contact);
        return true;
    }
}
