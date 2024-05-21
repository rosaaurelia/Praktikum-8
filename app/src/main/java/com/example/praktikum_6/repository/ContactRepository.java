package com.example.praktikum_6.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.praktikum_6.database.ContactDAO;
import com.example.praktikum_6.database.ContactDB;
import com.example.praktikum_6.database.ContactModel;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ContactRepository {
    private ContactDAO DataDAO;
//    private LiveData<List<ContactModel>> allContacts;
    private ExecutorService executorService;

    public ContactRepository(Application application){
        ContactDB data = ContactDB.getDatabase(application);
        DataDAO = data.contactDAO();
//        allContacts = DataDAO.getAllContacts();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<ContactModel>> getAllContacts(){

        return DataDAO.getAllContacts();
    }

    public void insert(final ContactModel contactModel){
        executorService.execute(()-> DataDAO.insert(contactModel));
    }

    public void delete(final ContactModel contactModel){
        executorService.execute(()-> DataDAO.delete(contactModel));
    }

    public void update(final ContactModel contactModel){
        executorService.execute(()-> DataDAO.update(contactModel));
    }
}
