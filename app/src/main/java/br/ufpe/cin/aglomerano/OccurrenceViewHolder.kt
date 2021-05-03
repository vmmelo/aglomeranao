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

    init {
        binding.root.setOnClickListener {
            val c = binding.date.context

            //Intent Explicito
            val intentExplicit = Intent(c, OccurrenceActivity::class.java)
            intentExplicit.putExtra("nome", email)
            intentExplicit.putExtra("login", time)
            intentExplicit.putExtra("date", date)
            intentExplicit.putExtra("description", description)

            c.startActivity(intentExplicit)
        }
    }
    fun bindTo(occurrence : Occurrence) {
        email = occurrence.email.toString()
        time = occurrence.time
        date = occurrence.date.toString()
        description = occurrence.description.toString()

        binding.email.text = "Email: " + occurrence.email
        binding.time.text= "Hora:" + occurrence.time
        binding.date.text= "Data:" + occurrence.date
        binding.description.text= "Descrição: " + (occurrence.description?.take(20) ?: "") + "..."

    }
}