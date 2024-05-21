package com.example.praktikum_6.repository;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.praktikum_6.ui.AddContactViewModel;
import com.example.praktikum_6.ui.ContactViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private static volatile ViewModelFactory INSTANCE;
    private final Application App;

    public ViewModelFactory(Application application) {
        
        App = application;
    }

    public static ViewModelFactory getInstance(Application application){
        if (INSTANCE == null){
            synchronized (ViewModelFactory.class){
                INSTANCE = new ViewModelFactory(application);
            }
        }
        return INSTANCE;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create (@NonNull Class<T> modelClass){
        if (modelClass.isAssignableFrom(ContactViewModel.class)){
            return (T) new ContactViewModel(App);
        } else if (modelClass.isAssignableFrom(AddContactViewModel.class)) {
            return (T) new AddContactViewModel(App);
        }
        throw new IllegalArgumentException("Unknown ViewModel class" + modelClass.getName());
    }
}
