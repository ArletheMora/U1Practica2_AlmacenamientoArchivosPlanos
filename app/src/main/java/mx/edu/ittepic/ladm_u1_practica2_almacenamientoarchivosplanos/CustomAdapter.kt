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
import mx.edu.ittepic.ladm_u1_practica2_almacenamientoarchivosplanos.databinding.FragmentGalleryBinding
import mx.edu.ittepic.ladm_u1_practica2_almacenamientoarchivosplanos.ui.home.HomeFragment
import java.io.*
import java.lang.Exception
import java.text.FieldPosition
import kotlin.coroutines.coroutineContext


class CustomAdapter: RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    var listaRegistros = ArrayList<String>()
    val pacientes = arrayOfNulls<String>(10)
    val edad = arrayOfNulls<String>(10)
    val descripcion = arrayOfNulls<String>(10)

    var cont =0

    // = intArrayOf(R.drawable.citas, R.drawable.citas, R.drawable.citas, R.drawable.citas)



    class ViewHolder(val binding: CardLayoutBinding): RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, i: Int) {
        //abrirArchivo()

        try {
            var archivo = BufferedReader(
                InputStreamReader(
                    holder.binding.root.context.openFileInput("datos.txt")
                )
            )

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
                listaRegistros.add(v)
            }
            /*holder.binding.listaPrueba.adapter =  ArrayAdapter<String>(binding.root.context,
                android.R.layout.simple_list_item_1, listaRegistros)*/

        }catch (e:Exception){
            //mensaje error
        }

        //for(n in 0..1){
            //esto es para obtener el texto de cada registro del txt
            var temporal = listaRegistros.get(i).split("\n")
            pacientes[cont]=temporal[0]
            edad[cont]=temporal[1]
            descripcion[cont]=temporal[2]
            cont++
        //}

        holder.binding.itemTitle.text = "Nombre: "+pacientes.get(i)
        holder.binding.itemEdad.text = "Edad: "+edad.get(i)
        holder.binding.itemDetail.text = "Motivo: "+descripcion.get(i)
        holder.binding.itemImage.setImageResource(R.drawable.citas)
    }

    override fun getItemCount(): Int {

        return GlobalVariable.valor
    }



}