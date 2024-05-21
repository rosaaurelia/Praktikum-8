package com.example.praktikum_6.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.praktikum_6.database.ContactModel;
import com.example.praktikum_6.repository.ContactRepository;

public class AddContactViewModel extends AndroidViewModel {
    private ContactRepository repository;
    public AddContactViewModel(@NonNull Application application) {
        super(application);
        repository = new ContactRepository(application);
    }
    public void insert(ContactModel contactModel){
        repository.insert(contactModel);
    }

    public void update(ContactModel contactModel){
        repository.update(contactModel);
    }
    public void delete(ContactModel contactModel){
        repository.delete(contactModel);
    }
}

