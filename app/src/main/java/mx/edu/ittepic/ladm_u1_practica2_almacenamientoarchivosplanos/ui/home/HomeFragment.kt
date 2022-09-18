package mx.edu.ittepic.ladm_u1_practica2_almacenamientoarchivosplanos.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import mx.edu.ittepic.ladm_u1_practica2_almacenamientoarchivosplanos.MainActivity
import mx.edu.ittepic.ladm_u1_practica2_almacenamientoarchivosplanos.databinding.FragmentHomeBinding



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
            cad1 = binding.txtnombre.text.toString()
            cad2 = binding.txtedad.text.toString()
            cad3 = binding.txtdescripcion.text.toString()
            nombres[cont]=cad1
            edades[cont]=cad2
            descripciones[cont]=cad3
            registros[cont]= cad1 +"\n" + cad2 +"\n"+ cad3
            view?.let { it1 ->
                Snackbar.make(it1, "Se agrego correctamente \n Su numero de cita es el: "+cont, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
            borrar()
            cont=cont+1
        }
        //-------------------------------------------------------------


        binding.buscar.setOnClickListener {
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

        }

        binding.actualizar.setOnClickListener {
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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}