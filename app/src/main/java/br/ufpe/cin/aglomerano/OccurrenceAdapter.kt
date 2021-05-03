package br.ufpe.cin.aglomerano

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.ufpe.cin.aglomerano.databinding.LineBinding

class OccurrenceAdapter(
        private val occurrences: MutableList<Occurrence>,
        private val inflater: LayoutInflater) :
    RecyclerView.Adapter<OccurrenceViewHolder>()
{
    override fun getItemCount(): Int = occurrences.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OccurrenceViewHolder {
        val binding = LineBinding.inflate(inflater, parent, false)
        return OccurrenceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OccurrenceViewHolder, position: Int) {
        holder.bindTo(occurrences[position])
    }
}