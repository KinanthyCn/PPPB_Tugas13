package com.kinan.tugas_pertemuan_12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.kinan.tugas_pertemuan_12.databinding.ActivityForthBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ForthActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityForthBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val imgUrl = intent.getStringExtra("IMAGE")
        Glide.with(this).load(imgUrl).into(binding.imageView)

        with(binding){
            btnSubmit.setOnClickListener{
                ThirdActivity.updateDuck(
                    Duck(
                        id = intent.getStringExtra("ID").toString(),
                        title = title.text.toString(),
                        description = description.text.toString(),
                        message = Message.text.toString(),
                        url = imgUrl!!
                    )
                )
                finish()
            }
        }
    }


}