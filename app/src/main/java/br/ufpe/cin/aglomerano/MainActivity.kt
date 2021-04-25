package br.ufpe.cin.aglomerano

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.ufpe.cin.aglomerano.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = FirebaseAuth.getInstance().currentUser

        if(user != null) {
            binding.userName.text = user.displayName
        }

        binding.createOccurrence.setOnClickListener {
            startActivity(Intent(this@MainActivity, CreateOccurrenceActivity::class.java))
        }
    }
}