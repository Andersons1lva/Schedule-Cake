package com.anderson.schedulecake.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.anderson.schedulecake.adapter.Adapter
import com.anderson.schedulecake.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

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

        val calendarView = binding.calendarView
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            initRecyclerView()
        }

    }

    private fun initRecyclerView(){
        // O RequireContext e por que estamos em um fragment se não seria apenas this.
        binding.rvEncomendas.layoutManager = LinearLayoutManager(requireContext())
        binding.rvEncomendas.setHasFixedSize(true)
        binding.rvEncomendas.adapter = Adapter(listEcomenda())
    }

    private fun  listEcomenda() = listOf(
        "Anderson", "Joyce", "Bryan", " Aline", "Hanry", "Marinês","Anderson", "Joyce", "Bryan", " Aline", "Hanry", "Marinês"
    )

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}