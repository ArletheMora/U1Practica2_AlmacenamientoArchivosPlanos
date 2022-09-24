package mx.edu.ittepic.ladm_u1_practica2_almacenamientoarchivosplanos.ui.home

import android.R
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import mx.edu.ittepic.ladm_u1_practica2_almacenamientoarchivosplanos.GlobalVariable
import mx.edu.ittepic.ladm_u1_practica2_almacenamientoarchivosplanos.MainActivity
import mx.edu.ittepic.ladm_u1_practica2_almacenamientoarchivosplanos.databinding.FragmentHomeBinding
import java.io.*
import java.lang.Exception
import java.io.FileReader


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!



    //Arreglo para datos------------------

    var listaRegistros = ArrayList<String>()
    var posicionActualizar = -1


    var cad1: String = ""
    var cad2: String = ""
    var cad3: String = ""
    var cad4: String = ""
    var cont = 1


    //------------------------------------

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //metodo agregar-----------------------------


        binding.agregar.setOnClickListener {

            insertar()
            guardarEnArchivo()
            borrar()

            view?.let { it1 ->
                Snackbar.make(
                    it1,
                    "Se agrego correctamente \n Su numero de cita es el: " + cont,
                    Snackbar.LENGTH_LONG
                )
                    .setAction("Action", null).show()
            }
            cont=cont+1

            GlobalVariable.valor = listaRegistros.size

        }
        //-------------------------------------------------------------


        binding.buscar.setOnClickListener {
            try{
                guardarDesdeArchivo()
                posicionActualizar = Integer.parseInt(binding.txtnocita.text.toString())
                var temporal =
                    listaRegistros.get(Integer.parseInt(binding.txtnocita.text.toString()))
                        .split("\n")
                binding.txtnombre.setText(temporal[0])
                binding.txtedad.setText(temporal[1])
                binding.txtdescripcion.setText(temporal[2])
            }catch (e:Exception){
                AlertDialog.Builder(binding.root.context)
                    .setTitle("ERROR")
                    .setMessage("Algo salió mal. Asegurate de tener registros almacenados y de haber escrito el número de cita a buscar")
                    .show()
            }


        }

        binding.actualizar.setOnClickListener {
            try {
                posicionActualizar = Integer.parseInt(binding.txtnocita.text.toString())
                if(posicionActualizar ==-1){//SI estamos en -1 no hemos activado la opcion de actualizacion
                    context?.let { it1 ->
                        AlertDialog.Builder(it1)
                            .setTitle("ERROR")
                            .setMessage("NO SELECCIONO REGISTRO A ACTUALIZAR")
                            .show()
                    }
                    //return@setOnClickListener
                }
                var concatenacioon =
                    binding.txtnombre.text.toString() + "\n" + binding.txtedad.text.toString() + "\n" + binding.txtdescripcion.text.toString()
                listaRegistros.set(posicionActualizar, concatenacioon)
                binding.listacontactos.adapter = ArrayAdapter<String>(
                    binding.root.context,
                    R.layout.simple_list_item_1, listaRegistros
                )
                //binding.txtnombre.setText("")
                //binding.txtedad.setText("")
                //binding.txtdescripcion.setText("")
                posicionActualizar = -1
                guardarEnArchivo()
                borrar()

            }catch (e:Exception){
                AlertDialog.Builder(binding.root.context)
                    .setTitle("Error")
                    .setMessage("Algo salió mal. Asegurate de tener registros almacenados y de haber escrito el número de cita a buscar")
                    .show()
            }

        }

        binding.eliminar.setOnClickListener {
            try{
                listaRegistros.removeAt(Integer.parseInt(binding.txtnocita.text.toString()))
                guardarEnArchivo()
                borrar()
                GlobalVariable.valor = listaRegistros.size
            }catch (e:Exception){
                AlertDialog.Builder(binding.root.context)
                    .setTitle("ERROR")
                    .setMessage("Algo salió mal. Asegurate de tener registros almacenados y de haber escrito el número de cita a eliminar")
                    .show()
            }

        }

        return root
    }

    private fun borrar() {
        binding.txtnombre.setText("")
        binding.txtedad.setText("")
        binding.txtdescripcion.setText("")
        binding.txtnocita.setText("")
    }

    //------------------------------------------------------------
    private fun guardarDesdeArchivo() {
        try {
            var archivo = BufferedReader(InputStreamReader(
                binding.root.context.openFileInput("datos.txt")
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
                listaRegistros.add(v)

            }
            binding.listacontactos.adapter =  ArrayAdapter<String>(binding.root.context,
                android.R.layout.simple_list_item_1, listaRegistros)

        }catch (e:Exception){
            context?.let {
                AlertDialog.Builder(it)
                    .setTitle("Error")
                    .setMessage(e.message)
                    .setPositiveButton("OK"){d, i->}
                    .show()
            }
        }

    }
    //-------------------------------------------------------------

    private fun insertar(){

        var concatenacion = binding.txtnombre.text.toString() + "\n" + binding.txtedad.text.toString()+ "\n" + binding.txtdescripcion.text.toString()
        listaRegistros.add(concatenacion)

        //mostrar en lista                                      //this                  //conjunto de items                 //datos
        binding.listacontactos.adapter = ArrayAdapter<String>(binding.root.context, android.R.layout.simple_list_item_1, listaRegistros)


        binding.listacontactos.adapter = ArrayAdapter<String>(binding.root.context, android.R.layout.simple_list_item_1, listaRegistros)
    }

    //-----------------------------------------------------------------
    private  fun guardarEnArchivo(){

        try {
            var archivo = OutputStreamWriter(binding.root.context.openFileOutput("datos.txt", Activity.MODE_PRIVATE))

            var bufferContenido = ""

            for (dato in listaRegistros){
                bufferContenido += dato + "&&"
            }
            //el substring para evitar error
            bufferContenido = bufferContenido.substring(0, bufferContenido.lastIndexOf("&&"))
            archivo.write(bufferContenido)
            archivo.flush()
            archivo.close()

            Toast.makeText(context, "Se guardó correctamente", Toast.LENGTH_LONG)
                .show()

        }catch (e:Exception){
            context?.let {
                AlertDialog.Builder(it)
                    .setTitle("Error")
                    .setMessage(e.message)
                    .setPositiveButton("OK"){d, i->}
                    .show()
            }
        }
    }

    //-----------------------------------------------------



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}