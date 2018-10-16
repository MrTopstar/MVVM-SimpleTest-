package com.aceplussolutions.myosetpaing.mvvmtest.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.aceplussolutions.myosetpaing.mvvmtest.database.AppDatabase;
import com.aceplussolutions.myosetpaing.mvvmtest.model.Contact;

public class AddContactViewModel extends AndroidViewModel {

    private AppDatabase appDatabase;

    public AddContactViewModel(@NonNull Application application) {
        super(application);
        appDatabase=AppDatabase.getDatabase(this.getApplication());

    }

    public void addContact(final Contact contact){

        new addAsyncTask(appDatabase).execute(contact);

    }

    private class addAsyncTask extends AsyncTask<Contact,Void,Void> {
        private AppDatabase database;

         addAsyncTask(AppDatabase appDatabase) {
            database=appDatabase;
        }

        @Override
        protected Void doInBackground(Contact... contacts) {
             database.itemAndContactModel().addContact(contacts[0]);
            return null;
        }
    }
}
