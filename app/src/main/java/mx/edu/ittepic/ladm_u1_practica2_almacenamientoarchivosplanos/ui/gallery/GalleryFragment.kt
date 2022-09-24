package mx.edu.ittepic.ladm_u1_practica2_almacenamientoarchivosplanos.ui.gallery

import android.R
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import mx.edu.ittepic.ladm_u1_practica2_almacenamientoarchivosplanos.CustomAdapter
import mx.edu.ittepic.ladm_u1_practica2_almacenamientoarchivosplanos.databinding.ActivityMainBinding
import mx.edu.ittepic.ladm_u1_practica2_almacenamientoarchivosplanos.databinding.FragmentGalleryBinding
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    //---------------------

    var listaRegistros = ArrayList<String>()
    var posicionActualizar = -1

    //---------------------

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val adapter = CustomAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        //------------------------------------------------------------

        /*binding.mostrar.setOnClickListener {
            try{
                guardarDesdeArchivo()

                    for(i in 0..1){
                        //esto es para obtener el texto de cada registro del txt
                        listaRegistros.get(i)
                       /* binding.txtnombre.setText(temporal[0])
                        binding.txtedad.setText(temporal[1])
                        binding.txtdescripcion.setText(temporal[2])*/
                    }

            }catch (e: Exception){
                AlertDialog.Builder(binding.root.context)
                    .setTitle("ERROR")
                    .setMessage("Algo salió mal. Asegurate de tener registros almacenados y de haber escrito el número de cita a buscar")
                    .show()
            }
        }*/

        //------------------------------------------------------------

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /*private fun guardarDesdeArchivo() {
        try {
            var archivo = BufferedReader(
                InputStreamReader(
                binding.root.context.openFileInput("datos.txt")
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
            /*binding.listaPrueba.adapter =  ArrayAdapter<String>(binding.root.context,
                R.layout.simple_list_item_1, listaRegistros)*/

        }catch (e:Exception){
            context?.let {
                AlertDialog.Builder(it)
                    .setTitle("Error")
                    .setMessage(e.message)
                    .setPositiveButton("OK"){d, i->}
                    .show()
            }
        }

    }*/
}