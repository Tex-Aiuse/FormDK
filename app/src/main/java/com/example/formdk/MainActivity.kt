package com.example.formdk

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.example.formdk.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root);

        binding.edtFullName.doOnTextChanged { _, _, _, _ -> validateName(); }
        binding.edtEmail.doOnTextChanged { _, _, _, _ -> validateEmail(); }
        binding.edtPhone.doOnTextChanged { _, _, _, _ -> validatePhone(); }
        binding.edtStudentId.doOnTextChanged { _, _, _, _ -> validateStudentId(); }
        binding.edtClass.doOnTextChanged { _, _, _, _ -> validateClass(); }

        binding.btnRegister.setOnClickListener {
            submitForm();
        }

        binding.btnToDetail.setOnClickListener {
            startActivity(Intent(this, DetailActivity::class.java));
        }
    }

    private fun validateName(): Boolean
    {
        val name = binding.edtFullName.text.toString().trim();

        if (name.isBlank())
        {
            binding.edtFullName.error = getString(R.string.err_name_empty);
            return false;
        }

        if (!name.matches(Regex("^[\\p{L} ]{2,}$")))
        {
            binding.edtFullName.error = getString(R.string.err_name_invalid);
            return false;
        }

        binding.edtFullName.error = null;
        return true;
    }

    private fun validateEmail(): Boolean
    {
        val email = binding.edtEmail.text.toString().trim();

        if (email.isBlank())
        {
            binding.edtEmail.error = getString(R.string.err_email_empty);
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            binding.edtEmail.error = getString(R.string.err_email_invalid);
            return false;
        }

        binding.edtEmail.error = null;
        return true;
    }

    private fun validatePhone(): Boolean
    {
        val phone = binding.edtPhone.text.toString().trim();

        if (!phone.matches(Regex("^0\\d{9}$")))
        {
            binding.edtPhone.error = getString(R.string.err_phone_invalid);
            return false;
        }

        binding.edtPhone.error = null;
        return true;
    }

    private fun validateStudentId(): Boolean
    {
        val id = binding.edtStudentId.text.toString().trim();

        if (!id.matches(Regex("^\\d{10,13}$")))
        {
            binding.edtStudentId.error = getString(R.string.err_student_id_invalid);
            return false;
        }

        binding.edtStudentId.error = null;
        return true;
    }

    private fun validateClass(): Boolean
    {
        val className = binding.edtClass.text.toString().trim();

        if (!className.matches(Regex("^\\d{2}[A-Za-z]{1,4}\\d{1,3}$")))
        {
            binding.edtClass.error = getString(R.string.err_class_invalid);
            return false;
        }

        binding.edtClass.error = null;
        return true;
    }

    private fun submitForm()
    {
        val allValid = validateName() and validateEmail() and validatePhone() and validateStudentId() and validateClass();

        if (!allValid)
            return;

        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra("EXTRA_NAME", binding.edtFullName.text.toString().trim());
            putExtra("EXTRA_EMAIL", binding.edtEmail.text.toString().trim());
            putExtra("EXTRA_PHONE", binding.edtPhone.text.toString().trim());
            putExtra("EXTRA_STUDENT_ID", binding.edtStudentId.text.toString().trim());
            putExtra("EXTRA_CLASS", binding.edtClass.text.toString().trim());
        }
        startActivity(intent);
    }
}