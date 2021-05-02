package br.ufpe.cin.aglomerano

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.ufpe.cin.aglomerano.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = "OccurrencesIndex"
    }

    private lateinit var binding : ActivityMainBinding
    private lateinit var database : FirebaseFirestore
//    private val occurrences: Array

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = Firebase.firestore

        val user = FirebaseAuth.getInstance().currentUser

        if(user != null) {
            binding.userName.text = user.displayName
        }

        binding.createOccurrence.setOnClickListener {
            startActivity(Intent(this@MainActivity, CreateOccurrenceActivity::class.java))
        }
    }


}