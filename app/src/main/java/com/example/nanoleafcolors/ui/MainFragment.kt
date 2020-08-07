package com.example.nanoleafcolors.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nanoleafcolors.R
import com.example.nanoleafcolors.adapters.ColorsAndCountAdapter
import com.example.nanoleafcolors.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        //Observing String Of Selected Colors
        viewModel.selectedColors.observe(viewLifecycleOwner, Observer { selectedColors ->
            binding.txvSelectedColors.text = selectedColors
        })

        binding.apply {
            //getting text from color button clicked
            rgColors.setOnCheckedChangeListener { group, checkedId ->
                val colorClicked = rgColors.findViewById<RadioButton>(checkedId).text
                onColorClick(colorClicked.toString())
                rgColors.findViewById<RadioButton>(checkedId).isChecked = false
            }
            //clearing the selected colors
            btnClear.setOnClickListener {
                viewModel.onClear()
            }

            //splitting the string into colors and displaying them in recyclerview
            btnCalculate.setOnClickListener {
                rvColors.layoutManager = LinearLayoutManager(requireContext())
                rvColors.adapter =
                    ColorsAndCountAdapter(viewModel.selectedColorsWithCounts(), requireContext())
                executePendingBindings()
            }
        }
    }

    private fun onColorClick(colorClicked: String) {
        viewModel.onClickColor(colorClicked)
    }

}








