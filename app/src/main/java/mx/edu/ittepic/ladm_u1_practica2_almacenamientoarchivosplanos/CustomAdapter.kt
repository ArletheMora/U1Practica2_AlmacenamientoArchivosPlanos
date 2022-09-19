package mx.edu.ittepic.ladm_u1_practica2_almacenamientoarchivosplanos

import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.edu.ittepic.ladm_u1_practica2_almacenamientoarchivosplanos.databinding.ActivityMainBinding
import mx.edu.ittepic.ladm_u1_practica2_almacenamientoarchivosplanos.databinding.CardLayoutBinding
import mx.edu.ittepic.ladm_u1_practica2_almacenamientoarchivosplanos.ui.home.HomeFragment
import java.io.*
import java.lang.Exception
import java.text.FieldPosition

 class CustomAdapter: RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

     val pacientes = arrayOf("title 1", "title 2", "title 3", "title 4")
     val edad = arrayOf("det 1", "det 2", "det 3", "det 4")
     val descripcion = arrayOf("det 1", "det 2", "det 3", "det 4")
         // = intArrayOf(R.drawable.citas, R.drawable.citas, R.drawable.citas, R.drawable.citas)



     class ViewHolder(val binding: CardLayoutBinding): RecyclerView.ViewHolder(binding.root){
     }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
         return ViewHolder(CardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
     }

     override fun onBindViewHolder(holder: ViewHolder, i: Int) {
         abrirArchivo()

         holder.binding.itemTitle.text = pacientes[i]
         holder.binding.itemDetail.text = edad[i]
         holder.binding.itemImage.setImageResource(R.drawable.citas)
     }

     override fun getItemCount(): Int {
         return pacientes.size
     }

     fun abrirArchivo(){
         try{
             var fileContents = ""
             val file1 = File(
                 Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
                 .toString() + "/datosCitas.txt")

             FileReader(file1).use {
                 val chars = CharArray(file1.length().toInt())
                 it.read(chars)
                 val fileContent = String(chars)
                 fileContents = fileContent.toString()
             }

             val citas = fileContents.split("-")
             println( "citas: " + citas)
             for(i in citas.indices){
                 val cita = citas[i].toString().split("\\|")
                 println(cita)
                 pacientes[i] = cita[0]
                 edad[i] = cita[1]
                 descripcion[i] = cita[2]
                 println("cit: $cita")
             }

         }
         catch (e:Exception){

         }
     }

}