package br.ufpe.cin.aglomerano

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.ufpe.cin.aglomerano.databinding.ActivityOccurrenceBinding

class OccurrenceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOccurrenceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOccurrenceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email = intent.getStringExtra("email")
        val time = intent.getStringExtra("time")
        val date = intent.getStringExtra("date")
        val description = intent.getStringExtra("description")
        val placeName = intent.getStringExtra("placeName")
        val latitude = intent.getDoubleExtra("latitude", 0.0)
        val longitude = intent.getDoubleExtra("longitude", 0.0)

        binding.detailsDateTime.text = getString(R.string.date_time, date, time)
        binding.detailsDescription.text = description
        binding.detailsEmail.text = "email: " + email
        binding.detailsPlaceName.text = "local: " + placeName

        binding.detailsBtnMap.setOnClickListener {
            val i = Intent(ACTION_VIEW)
            i.data = Uri.parse("geo:$latitude,$longitude")
            startActivity(i)
        }
    }
}