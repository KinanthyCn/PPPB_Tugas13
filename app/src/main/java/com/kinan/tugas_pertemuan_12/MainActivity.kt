package com.kinan.tugas_pertemuan_12

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.kinan.tugas_pertemuan_12.R
import com.kinan.tugas_pertemuan_12.databinding.ActivityMainBinding
import com.kinan.tugas_pertemuan_12.model.Bebek
import com.kinan.tugas_pertemuan_12.network.ApiClient
import com.kinan.tugas_pertemuan_12.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.toolbar_ku))

        val client = ApiClient.getInstance()



        with(binding){
            btnShuffle.setOnClickListener(){
                shuffle(client)
            }
            favoriteButton.setOnClickListener(){
                addBookmark()
            }
        }





    }
    companion object{
        val EXTRA_DUCK = "img_duck"
        var data_duck = ""

    }
    fun shuffle(client : ApiService){
        val response : Call<Bebek> = client.getRandomDuck()
        response.enqueue(object : Callback<Bebek> {
            override fun onResponse(call: Call<Bebek>, response: Response<Bebek>) {
                if (response.isSuccessful){
                    val data = response.body()
                    if (data != null){
                        data_duck = data.imgUrl
                        setImage(this@MainActivity, data.imgUrl)

                        Toast.makeText(this@MainActivity, "yeay, kamu berhasil liat bebek", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@MainActivity, "huhuhu, bebeknya udah mati", Toast.LENGTH_SHORT).show()
                    }

                }
                else if (response.code() == 401) {
                    Toast.makeText(this@MainActivity, "sorry, kamu belum beruntung", Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(this@MainActivity, "mau gimana lagi", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Bebek>, t: Throwable) {
                Toast.makeText(this@MainActivity, "maaf, bebeknya malu", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun setImage(context : Context, url : String) {
        Glide.with(context).load(url).centerCrop().into(binding.imageDuck)
    }
    fun addBookmark(){
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra(EXTRA_DUCK, data_duck)
        startActivity(intent)
    }
}