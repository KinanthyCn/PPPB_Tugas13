package com.kinan.tugas_pertemuan_12

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.kinan.tugas_pertemuan_12.MainActivity.Companion.EXTRA_DUCK
import com.kinan.tugas_pertemuan_12.databinding.ActivitySecondBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class SecondActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivitySecondBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.toolbar_ku))

        val imgURL = intent.getStringExtra(EXTRA_DUCK)

        Glide.with(this).load(imgURL).into(binding.imageView)

        with(binding) {
            btnSubmit.setOnClickListener() {
                ThirdActivity.insertDuck(
                    Duck(
                        title = title.text.toString(),
                        description = description.text.toString(),
                        message = Message.text.toString(),
                        url = imgURL!!
                    )
                )
                val intent = Intent(this@SecondActivity, ThirdActivity::class.java)
                startActivity(intent)
            }

        }
    }




    private fun setEmptyField() {
        binding.title.setText("")
        binding.description.setText("")
        binding.Message.setText("")
    }
}


