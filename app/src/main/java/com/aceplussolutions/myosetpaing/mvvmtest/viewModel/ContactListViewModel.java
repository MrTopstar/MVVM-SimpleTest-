package com.aceplussolutions.myosetpaing.mvvmtest.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.aceplussolutions.myosetpaing.mvvmtest.database.AppDatabase;
import com.aceplussolutions.myosetpaing.mvvmtest.model.Contact;

import java.util.List;

public class ContactListViewModel extends AndroidViewModel {

    private final LiveData<List<Contact>> contactList;
    private AppDatabase appDatabase;

    public ContactListViewModel(@NonNull Application application) {
        super(application);
        appDatabase=AppDatabase.getDatabase(this.getApplication());
        contactList= appDatabase.itemAndContactModel().getContact();
    }

    public LiveData<List<Contact>> getContactList(){
        return contactList;
    }

    public void deleteContact(Contact contact){
        new deleteAsyncTask(appDatabase).execute(contact);
    }

    private class deleteAsyncTask extends AsyncTask<Contact,Void,Void> {

        private AppDatabase db;
        public deleteAsyncTask(AppDatabase appDatabase) {
            db=appDatabase;

        }

        @Override
        protected Void doInBackground(final Contact... contacts) {
            db.itemAndContactModel().delectContact(contacts[0]);
            return null;
        }
    }
}
