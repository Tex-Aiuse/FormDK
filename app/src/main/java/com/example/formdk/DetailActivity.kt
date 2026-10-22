package com.example.formdk

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.formdk.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(layoutInflater);
        setContentView(binding.root);

        val noData = getString(R.string.msg_no_data);

        with(binding)
        {
            tvName.text = "Họ tên: " + (intent.getStringExtra("EXTRA_NAME") ?: noData);
            tvEmail.text = "Email: " + (intent.getStringExtra("EXTRA_EMAIL") ?: noData);
            tvPhone.text = "SĐT: " + (intent.getStringExtra("EXTRA_PHONE") ?: noData);
            tvStudentId.text = "MSV: " + (intent.getStringExtra("EXTRA_STUDENT_ID") ?: noData);
            tvClass.text = "Lớp: " + (intent.getStringExtra("EXTRA_CLASS") ?: noData);
        }

        binding.btnBack.setOnClickListener {
            val backIntent = Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP;
            }
            startActivity(backIntent);
        }
    }
}