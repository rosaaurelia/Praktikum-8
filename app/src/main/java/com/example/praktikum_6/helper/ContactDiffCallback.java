package com.example.praktikum_6.helper;

import androidx.recyclerview.widget.DiffUtil;

import com.example.praktikum_6.database.ContactModel;

import java.util.List;

public class ContactDiffCallback extends DiffUtil.Callback {

    private final List<ContactModel> oldData;
    private final List<ContactModel> newData;

    public ContactDiffCallback(List<ContactModel> oldData, List<ContactModel> newData){
        this.oldData = oldData;
        this.newData = newData;
    }

    @Override
    public int getOldListSize() {
        return oldData.size();
    }

    @Override
    public int getNewListSize() {
        return newData.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldData.get(oldItemPosition).getId() == newData.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final ContactModel oldContacts = oldData.get(oldItemPosition);
        final ContactModel newContacts = newData.get(newItemPosition);
        return oldContacts.getNumber().equals(newContacts.getName())
                && oldContacts.getNumber().equals(newContacts.getNumber())
                && oldContacts.getGroup().equals(newContacts.getGroup())
                && oldContacts.getInstagram().equals(newContacts.getInstagram());

    }
}
