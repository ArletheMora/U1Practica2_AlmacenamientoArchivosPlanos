package mx.edu.ittepic.ladm_u1_practica2_almacenamientoarchivosplanos

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import mx.edu.ittepic.ladm_u1_practica2_almacenamientoarchivosplanos.databinding.ActivityMainBinding
import mx.edu.ittepic.ladm_u1_practica2_almacenamientoarchivosplanos.databinding.CardLayoutBinding
import mx.edu.ittepic.ladm_u1_practica2_almacenamientoarchivosplanos.ui.home.HomeFragment
import java.io.*
import java.lang.Exception
import java.text.FieldPosition
import kotlin.coroutines.coroutineContext

class CustomAdapter: RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

     val pacientes = arrayOf("")//arrayOf("title 1", "title 2", "title 3", "title 4")
     val edad = arrayOf("")//arrayOf("det 1", "det 2", "det 3", "det 4")
     val descripcion = arrayOf("")//arrayOf("det 1", "det 2", "det 3", "det 4")
         // = intArrayOf(R.drawable.citas, R.drawable.citas, R.drawable.citas, R.drawable.citas)
    var listaRegistros = ArrayList<String>()

     public var citas = arrayOf("")

     class ViewHolder(val binding: CardLayoutBinding): RecyclerView.ViewHolder(binding.root){
     }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
         return ViewHolder(CardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
     }

     override fun onBindViewHolder(holder: ViewHolder, i: Int) {
         //abrirArchivo()
         //Toast.makeText(holder.binding.root.context, "entró", Toast.LENGTH_LONG).show()
         guardarDesdeArchivo(holder)
        try {
            var temporal = listaRegistros[1].split("\n")
            println("temporal: " +temporal)

            val cita = citas[i].split("\\|")
            //pacientes[i] = cita[0]
            //edad[i] = cita[1]
            //descripcion[i] = cita[2]

            pacientes[i] = temporal[0]
            edad[i] = temporal[1]
            descripcion[i] = temporal[2]

            println("0"+temporal[0])
            println(temporal[1])
            println(temporal[2])
        }
        catch (e:Exception){
        }
         holder.binding.itemTitle.text = "gfsd"+pacientes[i]
         holder.binding.itemEdad.text = edad[i]
         holder.binding.itemDetail.text = descripcion[i]
         holder.binding.itemImage.setImageResource(R.drawable.citas)
     }

     override fun getItemCount(): Int {
         //return pacientes.size
         //return listaRegistros.size
         return listaRegistros.size
     }

    private fun guardarDesdeArchivo(holder: ViewHolder) {
        Toast.makeText(holder.binding.root.context, "entró", Toast.LENGTH_LONG)
            .show()
        try {
            //println( "ENTRÓ " )
            //Toast.makeText(holder.binding.root.context, "entró", Toast.LENGTH_LONG).show()

            var archivo = BufferedReader(InputStreamReader(
                holder.binding.root.context.openFileInput("datos.txt")
            ))

            var bufferContenido = ""

            //va a ir leyendo línea por línea
            var interactivo = archivo.lineSequence().iterator()

            while(interactivo.hasNext()) { //regresa un booleano si hay línea
                bufferContenido += interactivo.next()+"\n"
            }

            //vector para tener la  lista de contactos
            var vector = bufferContenido.split("&&")

            listaRegistros.clear()

            for (v in vector){
                println( "citas: " + v)
                listaRegistros.add(v)
            }
            //holder.binding.listacontactos.adapter =  ArrayAdapter<String>(binding.root.context,
              //  android.R.layout.simple_list_item_1, listaRegistros)

        }catch (e:Exception){
                AlertDialog.Builder(holder.binding.root.context)
                    .setTitle("Error")
                    .setMessage(e.message)
                    .setPositiveButton("OK"){d, i->}
                    .show()
            }
        }


     fun abrirArchivo(){
         try{
             var fileContents = ""
             val file1 = File(
                 Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
                    // .toString() + "/datos.txt")
                     .toString() + "/datosCitas.txt")

             FileReader(file1).use {
                 val chars = CharArray(file1.length().toInt())
                 it.read(chars)
                 val fileContent = String(chars)
                 fileContents = fileContent.toString()
             }

              citas = fileContents.split("-").toTypedArray()
             //println( "citas: " + citas)

/*
             for(i in citas.indices){
                 val cita = citas[i].toString().split("\\|")
                 println(cita.size)
                 pacientes[i] = cita[0]
                 edad[i] = cita[1]
                 descripcion[i] = cita[2]
                 println("cit: $cita")
             }*/
         }
         catch (e:Exception){

         }
     }

}