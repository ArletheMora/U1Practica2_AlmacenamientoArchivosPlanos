package mx.edu.ittepic.ladm_u1_practica2_almacenamientoarchivosplanos.ui.home

import android.app.Activity
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
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
    val nombres = arrayOfNulls<String>(21)
    val edades = arrayOfNulls<String>(21)
    val descripciones = arrayOfNulls<String>(21)
    var registros = arrayOfNulls<String>(21)

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

        /*HACER LECTURA ANTES */
        binding.agregar.setOnClickListener {
            cad1 = binding.txtnombre.text.toString()
            cad2 = binding.txtedad.text.toString()
            cad3 = binding.txtdescripcion.text.toString()
            cad4 = binding.txtnocita.text.toString()
            nombres[cont]=cad1
            edades[cont]=cad2
            descripciones[cont]=cad3
            registros[cont]= cad1 +"\n" + cad2 +"\n"+ cad3
            view?.let { it1 ->
                Snackbar.make(it1, "Se agrego correctamente \n Su numero de cita es el: "+cont, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
            borrar()

            guardarEnArchivo( cad1 + "|" + cad2 + "|" + cad3 + "|" + cad4   )

            cont=cont+1
        }
        //-------------------------------------------------------------


        binding.buscar.setOnClickListener {
            try{
                if(binding.txtnocita.equals("")){
                    view?.let { it1 ->
                        Snackbar.make(it1, "No se ingreso No. de Cita", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()
                    }
                }else if(nombres[Integer.parseInt(binding.txtnocita.text.toString())].equals("") || edades[Integer.parseInt(binding.txtnocita.text.toString())].equals("") || descripciones[Integer.parseInt(binding.txtnocita.text.toString())].equals("")){
                    view?.let { it1 ->
                        Snackbar.make(it1, "No se encontro el registro", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()
                    }
                }else{
                    binding.txtnombre.setText(nombres[Integer.parseInt(binding.txtnocita.text.toString())])
                    binding.txtedad.setText(edades[Integer.parseInt(binding.txtnocita.text.toString())])
                    binding.txtdescripcion.setText(descripciones[Integer.parseInt(binding.txtnocita.text.toString())])
                }
            }catch (e: Exception){
                Toast.makeText(context, "No se ingresó No. de Cita", Toast.LENGTH_LONG)
                    .show()
            }


        }

        binding.actualizar.setOnClickListener {

            try {
                cad1 = binding.txtnombre.text.toString()
                cad2 = binding.txtedad.text.toString()
                cad3 = binding.txtdescripcion.text.toString()
                nombres[Integer.parseInt(binding.txtnocita.text.toString())]=cad1
                edades[Integer.parseInt(binding.txtnocita.text.toString())]=cad2
                descripciones[Integer.parseInt(binding.txtnocita.text.toString())]=cad3
                view?.let { it1 ->
                    Snackbar.make(it1, "Se actualizo el registro", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }

                borrar()
            }catch (e: Exception){
            Toast.makeText(context, "No se ingresó No. de Cita", Toast.LENGTH_LONG)
                .show()
        }


        }

        binding.eliminar.setOnClickListener {
            nombres[Integer.parseInt(binding.txtnocita.text.toString())]=""
            edades[Integer.parseInt(binding.txtnocita.text.toString())]=""
            descripciones[Integer.parseInt(binding.txtnocita.text.toString())]=""
            borrar()
            view?.let { it1 ->
                Snackbar.make(it1, "Se elimino el registro", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
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

    fun guardarEnArchivo(mensaje:String){
        try {
            var fileContents = ""

            //construir archivo
            var archivo = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).toString() + "/" + "datosCitas.txt"

            var file = File(archivo)

            if (!file.exists()) {       //si no existe se crea
                file.createNewFile()
            }else{                      //si existe que actualice

                val file1 = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
                    .toString() + "/datosCitas.txt")

                FileReader(file1).use {
                    val chars = CharArray(file1.length().toInt())
                    it.read(chars)
                    val fileContent = String(chars)
                    fileContents = fileContent.toString()
                }
            }

            val fileWriter = FileWriter(file)
            val bufferedWriter = BufferedWriter(fileWriter)
            if (fileContents != ""){
                bufferedWriter.write(fileContents + "-" + mensaje)
            }else{
                bufferedWriter.write(mensaje)
            }
            bufferedWriter.close()

            Toast.makeText(context, "GUARDADO", Toast.LENGTH_LONG)
                .show()
        }catch (e:Exception){
            Toast.makeText(context, e.message, Toast.LENGTH_LONG)
                .show()
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}