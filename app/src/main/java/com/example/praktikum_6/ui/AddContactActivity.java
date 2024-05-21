package com.example.praktikum_6.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.praktikum_6.R;
import com.example.praktikum_6.database.ContactModel;
import com.example.praktikum_6.databinding.ActivityAddContactBinding;
import com.example.praktikum_6.repository.ViewModelFactory;

public class AddContactActivity extends AppCompatActivity {

    public static final String EXTRA_CONTACT = "extra_contact";
    private boolean isEdited = false;
    private ContactModel contactModel;
    private ActivityAddContactBinding binding;
    private AddContactViewModel addContactViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        binding = ActivityAddContactBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        addContactViewModel = obtainViewModel(AddContactActivity.this);

        contactModel = getIntent().getParcelableExtra(EXTRA_CONTACT);
        if (contactModel != null) {
            isEdited = true;
        } else {
            contactModel = new ContactModel();
        }

        String actionBarTitle, btnTitle;
        if (isEdited) {
            actionBarTitle = "Ubah";
            btnTitle = "Perbarui";

            binding.etName.setText(contactModel.getName());
            binding.etNumber.setText(contactModel.getNumber());
            binding.etGroup.setText(contactModel.getGroup());
            binding.etInstagram.setText(contactModel.getInstagram());
        }else {
            actionBarTitle = "Tambah";
            btnTitle = "Simpan";
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(actionBarTitle);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        binding.btnSubmit.setOnClickListener(v-> {
            String name = binding.etName.getText().toString();
            String number = binding.etNumber.getText().toString();
            String instagram = binding.etInstagram.getText().toString();
            String group = binding.etGroup.getText().toString();

            if (name.isEmpty() || number.isEmpty() || instagram.isEmpty() || group.isEmpty()){
                showToast((getString(R.string.error)));
            } else {
                contactModel.setName(name);
                contactModel.setNumber(number);
                contactModel.setGroup(group);
                contactModel.setInstagram(instagram);
                Intent intent = new Intent();
                intent.putExtra(EXTRA_CONTACT, contactModel);
                if (isEdited) {
                    addContactViewModel.update(contactModel);
                    showToast((getString(R.string.edit)));
                } else {
                    addContactViewModel.insert(contactModel);
                    showToast((getString(R.string.add)));
                }
                finish();
            }
        });

        binding.btnClear.setOnClickListener(v -> clearData());
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @NonNull
    private static AddContactViewModel obtainViewModel(AppCompatActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return new ViewModelProvider(activity, (ViewModelProvider.Factory) factory).get(AddContactViewModel.class);
    }
    private void clearData(){
        binding.etName.setText("");
        binding.etNumber.setText("");
        binding.etInstagram.setText("");
        binding.etGroup.setText("");
    }
}