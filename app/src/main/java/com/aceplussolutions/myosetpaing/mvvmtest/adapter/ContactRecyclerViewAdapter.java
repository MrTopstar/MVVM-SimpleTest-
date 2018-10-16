package com.aceplussolutions.myosetpaing.mvvmtest.adapter;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aceplussolutions.myosetpaing.mvvmtest.R;
import com.aceplussolutions.myosetpaing.mvvmtest.model.Contact;

import java.util.List;

public class ContactRecyclerViewAdapter extends RecyclerView.Adapter<ContactRecyclerViewAdapter.ViewHolder> {

    private List<Contact> contactList;
    private View.OnLongClickListener onLongClickListener;

    public ContactRecyclerViewAdapter(List<Contact> contactList, View.OnLongClickListener onLongClickListener) {
        this.contactList = contactList;
        this.onLongClickListener = onLongClickListener;
    }

    @NonNull
    @Override
    public ContactRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.contist_list,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ContactRecyclerViewAdapter.ViewHolder viewHolder, int i) {

        Contact contact=contactList.get(i);
        viewHolder.name.setText(contact.getName());
        viewHolder.phno.setText(contact.getPhno());
        viewHolder.date.setText(contact.getContactDate().toLocaleString().substring(0,12));
        viewHolder.itemView.setTag(contact);
        viewHolder.itemView.setOnLongClickListener(onLongClickListener);
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView phno;
        private TextView date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.tvName);
            phno=itemView.findViewById(R.id.tvPhno);
            date=itemView.findViewById(R.id.tvDate);
        }
    }
}
