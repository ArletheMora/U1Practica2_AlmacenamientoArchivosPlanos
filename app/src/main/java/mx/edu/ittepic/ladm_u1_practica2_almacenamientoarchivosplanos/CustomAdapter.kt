package mx.edu.ittepic.ladm_u1_practica2_almacenamientoarchivosplanos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.edu.ittepic.ladm_u1_practica2_almacenamientoarchivosplanos.databinding.ActivityMainBinding
import mx.edu.ittepic.ladm_u1_practica2_almacenamientoarchivosplanos.databinding.CardLayoutBinding
import java.text.FieldPosition

 class CustomAdapter: RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

     val titles = arrayOf("title 1", "title 2", "title 3", "title 4")
     val details = arrayOf("det 1", "det 2", "det 3", "det 4")
     val images = intArrayOf(R.drawable.citas, R.drawable.citas, R.drawable.citas, R.drawable.citas)

     class ViewHolder(val binding: CardLayoutBinding): RecyclerView.ViewHolder(binding.root){
     }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
         return ViewHolder(CardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
     }

     override fun onBindViewHolder(holder: ViewHolder, i: Int) {
         holder.binding.itemTitle.text = titles[i]
         holder.binding.itemDetail.text = details[i]
         holder.binding.itemImage.setImageResource(images[i])
     }

     override fun getItemCount(): Int {
         return titles.size
     }
    /*override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        //val v = LayoutInflater.from(viewGroup.context).inflate((R.layout.card_layout))
        //return ViewHolder(v)
        super.onCreateViewHolder(ViewHolder)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, i: Int){
        viewHolder.itemTitle.text = titles[i]
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView
        var itemTitle: TextView
        var itemDetail: TextView

        init {
            itemImage =
        }
    }*/

}