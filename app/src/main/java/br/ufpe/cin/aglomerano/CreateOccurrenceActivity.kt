package br.ufpe.cin.aglomerano

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.ufpe.cin.aglomerano.databinding.ActivityCreateOccurrenceBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CreateOccurrenceActivity : AppCompatActivity() {

    companion object {
        private val TAG = "CreateOccurrence"
    }

    var latitude: Double = 0.0
    var longitude: Double = 0.0
    var placeName: String? = ""

    private var locationPermissionGranted = false

    private lateinit var binding : ActivityCreateOccurrenceBinding
    private lateinit var database : FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val user = FirebaseAuth.getInstance().currentUser

        binding = ActivityCreateOccurrenceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        latitude = intent.getDoubleExtra("latitude", 0.0)
        longitude = intent.getDoubleExtra("longitude", 0.0)
        placeName = intent.getStringExtra("name")

        binding.placeName.text = placeName

        database = Firebase.firestore
        binding.saveOccurence.setOnClickListener {

            if(user != null) {
                writeNewOccurrence(
                        user.uid,
                        user.email,
                        binding.occurrenceTime.text.toString(),
                        binding.occurrenceDate.text.toString(),
                        binding.occurrenceDescription.text.toString()
                )
            }

        }
    }

    private fun writeNewOccurrence(userId: String, email: String?, time: String, date: String, description: String) {
        val occurrence = Occurrence(userId, email, time, date, description, latitude, longitude, placeName)
        Log.d(TAG, occurrence.toString())
        database.collection("occurrences")
                .add(occurrence)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "occurrence added with ID: ${documentReference.id}")
                    Toast.makeText(this@CreateOccurrenceActivity, resources.getString(R.string.occurrence_saved), Toast.LENGTH_LONG).show()
                    val i = Intent(this, MainActivity::class.java)
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(i)
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding occurrence", e)
                }
    }

}