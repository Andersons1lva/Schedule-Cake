package com.anderson.schedulecake.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anderson.schedulecake.adapter.Adapter
import com.anderson.schedulecake.auth.LoginActivity
import com.anderson.schedulecake.databinding.FragmentHomeBinding
import com.anderson.schedulecake.helper.SharedPreferencesHelper
import com.anderson.schedulecake.model.Cliente
import com.anderson.schedulecake.model.Encomenda
import com.anderson.schedulecake.model.User
import com.anderson.schedulecake.network.apiService
import com.anderson.schedulecake.network.client
import kotlinx.coroutines.cancel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: Adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater,container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = Adapter(mutableListOf())

        binding.rvEncomendas.layoutManager = LinearLayoutManager(requireContext())
        binding.rvEncomendas.setHasFixedSize(true)
        binding.rvEncomendas.adapter = adapter

        val calendarView = binding.calendarView
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            if (dayOfMonth != null) {
                obterClientes()
            }
        }

    }

    private fun obterClientes(){
        val token = obterToken()
        if (token != null){
            apiService().getCliente("Bearer $token").enqueue(object : Callback<List<Cliente>>{
                override fun onResponse(call: Call<List<Cliente>>, response: Response<List<Cliente>>
                ) {
                    if (response.isSuccessful){
                        val clientes = response.body()

                        clientes?.let {
                            adapter.atualizarClientes(clientes)
                        }
                    }else{
                        // Trate o erro de acordo com a resposta do servidor
                        Toast.makeText(requireContext(), "Erro: ${response.code()}", Toast.LENGTH_SHORT)
                    }
                }

                override fun onFailure(call: Call<List<Cliente>>, t: Throwable) {
                    // Trate o erro de comunicação
                    Toast.makeText(requireContext(), "Erro de comunicação: ${t.message}", Toast.LENGTH_SHORT)
                }

            })
        }else{
            // Handle the case where the token is null (not stored in SharedPreferences)
            Toast.makeText(requireContext(), "Token não encontrado", Toast.LENGTH_SHORT).show()
        }
    }

    private fun obterToken(): String? {
        val preferences: SharedPreferences = requireActivity().getSharedPreferences("tokenLogin", Context.MODE_PRIVATE)
        return preferences.getString("token", null)
    }

    private fun obterEncomendas(){

    }

//    private fun initRecyclerView(){
//        // O RequireContext e por que estamos em um fragment se não seria apenas this.
//        binding.rvEncomendas.layoutManager = LinearLayoutManager(requireContext())
//        binding.rvEncomendas.setHasFixedSize(true)
//        binding.rvEncomendas.adapter = Adapter(listEcomenda())
//
//        binding.rvEncomendas.setOnClickListener {
//            Toast.makeText(context,"Você clicou aqui",Toast.LENGTH_SHORT)
//        }
//    }


    private fun  listEcomenda() = listOf(
        "Anderson", "Joyce", "Bryan", " Aline", "Hanry", "Marinês","Anderson", "Joyce", "Bryan", " Aline", "Hanry", "Marinês"
    )

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onDestroyView() {
        // Limpeza de recursos, cancelamento de corrotinas, etc.
        viewLifecycleOwner.lifecycleScope.cancel() // Cancela todas as corrotinas associadas a este escopo

        super.onDestroyView()
    }
}