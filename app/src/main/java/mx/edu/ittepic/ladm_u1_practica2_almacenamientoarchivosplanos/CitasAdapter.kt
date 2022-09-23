package mx.edu.ittepic.ladm_u1_practica2_almacenamientoarchivosplanos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mx.edu.ittepic.ladm_u1_practica2_almacenamientoarchivosplanos.databinding.CardLayoutBinding

class CitasAdapter(items:List<Cita>): RecyclerView.Adapter<CitasAdapter.ViewHolder>() {
    class ViewHolder(val binding: CardLayoutBinding): RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return CitasAdapter.ViewHolder(CardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}