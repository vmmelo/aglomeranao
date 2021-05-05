package br.ufpe.cin.aglomerano

import android.content.Intent
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import br.ufpe.cin.aglomerano.databinding.LineBinding

class OccurrenceViewHolder(private val binding: LineBinding) :
    RecyclerView.ViewHolder(binding.root)
{
    var email : String = "Email"
    var time : String = "00:00"
    var date : String = "01/01/2021"
    var description : String = "Lorem Ipsum"
    var placeName : String = "Place Name"
    var latitude : Double = 0.0
    var longitude : Double = 0.0

    init {
        binding.root.setOnClickListener {
            val c = binding.description.context

            //Intent Explicit
            val intentExplicit = Intent(c, OccurrenceActivity::class.java)
            intentExplicit.putExtra("email", email)
            intentExplicit.putExtra("login", time)
            intentExplicit.putExtra("date", date)
            intentExplicit.putExtra("time", time)
            intentExplicit.putExtra("description", description)
            intentExplicit.putExtra("placeName", placeName)
            intentExplicit.putExtra("latitude", latitude)
            intentExplicit.putExtra("longitude", longitude)

            c.startActivity(intentExplicit)
        }
    }
    fun bindTo(occurrence : Occurrence) {
        email = occurrence.email.toString()
        time = occurrence.time
        date = occurrence.date.toString()
        description = occurrence.description.toString()
        placeName = occurrence.placeName.toString()
        latitude = occurrence.latitude ?: 0.0
        longitude = occurrence.longitude ?: 0.0

        binding.placeName.text = "Local: " + occurrence.placeName
        binding.dateTime.text= "Data/Hora:" + occurrence.date + occurrence.time
        binding.description.text= "Descrição: " + (occurrence.description?.take(20) ?: "") + "..."

    }
}