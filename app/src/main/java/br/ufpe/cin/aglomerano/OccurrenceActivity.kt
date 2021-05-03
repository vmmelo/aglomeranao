package br.ufpe.cin.aglomerano

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

        binding.detailsDate.text = date
        binding.detailsTime.text = time
        binding.detailsDescription.text = description
        binding.detailsEmail.text = email
    }
}