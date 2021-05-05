package br.ufpe.cin.aglomerano

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import br.ufpe.cin.aglomerano.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    var occurrencesList: MutableList<Occurrence> = mutableListOf()

    companion object {
        private val TAG = "OccurrencesIndex"
    }

    private lateinit var binding : ActivityMainBinding
    private lateinit var database : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = Firebase.firestore

        val user = FirebaseAuth.getInstance().currentUser

        if(user != null) {
            binding.userName.text = if (user.displayName !== null && user.displayName !== "") user.displayName else user.email
        }

        binding.createOccurrence.setOnClickListener {
            startActivity(Intent(this@MainActivity, MapsActivity::class.java))
        }
        getOccurrences()
    }

    override fun onResume() {
        super.onResume()
        getOccurrences()
    }

    override fun onStart() {
        super.onStart()
        getOccurrences()
    }

    override fun onRestart() {
        super.onRestart()
        getOccurrences()
    }

    private fun getOccurrences() {
        val recyclerViewOccurrences = binding.occurrencesList
        occurrencesList = mutableListOf()
        database.collection("occurrences")
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        val data = document.data
                        Log.d(TAG, "${document.id} => ${document.data}")
                        if(data["latitude"] === null){
                            data["latitude"] = 0.0
                        }
                        if(data["longitude"] === null){
                            data["longitude"] = 0.0
                        }
                        if(data["placeName"] === null){
                            data["placeName"] = ""
                        }
                        occurrencesList.add(
                                Occurrence(
                                        data["userId"] as String,
                                        data["email"] as String,
                                        data["time"] as String,
                                        data["date"] as String,
                                        data["description"] as String,
                                        data["latitude"] as Double,
                                        data["longitude"] as Double,
                                        data["placeName"] as String,
                                        )
                        )
                    }
                    recyclerViewOccurrences.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
                        adapter = OccurrenceAdapter(occurrencesList,layoutInflater)
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error getting documents: ", exception)
                }

    }
}