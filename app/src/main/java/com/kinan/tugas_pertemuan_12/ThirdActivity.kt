package com.kinan.tugas_pertemuan_12

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.kinan.tugas_pertemuan_12.databinding.ActivityThirdBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityThirdBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.toolbar_ku))

        observeDucks()
        with(binding) {
            rvBookmark.layoutManager = LinearLayoutManager(this@ThirdActivity)

        }
        getAllDucks()
    }
    companion object{
        val firestore = FirebaseFirestore.getInstance() // ngehubungin ke firestorenya
        val duckCollectionRef = firestore.collection("duck") // ambil spesfic collection
        val duckListLiveData : MutableLiveData<List<Duck>> by lazy {
            MutableLiveData<List<Duck>>()
        } // buat live data

        fun insertDuck(duck: Duck){
            duckCollectionRef.add(duck)
        }
        fun updateDuck(duck: Duck){
            duckCollectionRef.document(duck.id).set(duck)
        }
        fun deleteDuck(duck: Duck){
            duckCollectionRef.document(duck.id).delete()
        }


    }
    fun getAllDucks() {
        duckCollectionRef.addSnapshotListener { value, error ->
            if (error != null) {
                Toast.makeText(this, "Error listening to characters data", Toast.LENGTH_SHORT)
                    .show()
            }
            val ducks = arrayListOf<Duck>()
            value?.forEach { documentReference ->
                ducks.add(
                    Duck(
                        documentReference.id,
                        documentReference.get("title").toString(),
                        documentReference.get("description").toString(),
                        documentReference.get("message").toString(),
                        documentReference.get("url").toString()
                    )
                )
                if (ducks.isNotEmpty()) {
                    duckListLiveData.postValue(ducks)
                } else {
                    Toast.makeText(this, "Bebek gagal ditambahkan", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
        fun observeDucks() {
            duckListLiveData.observe(this) { duck ->
                var listDuck = duck.toMutableList()
                binding.rvBookmark.adapter = DuckAdapter(listDuck, {
                    val intent = Intent(this, ForthActivity::class.java)
                    intent.putExtra("ID", it.id)
                    intent.putExtra("IMAGE", it.url)
                    startActivity(intent)
                }, {
                    deleteDuck(it)
                })
            }
        }
    }