package com.example.converter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_values.*


class ValuesFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private val viewModel: MyViewModel by activityViewModels()
    private lateinit var jsonService: JSONService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        jsonService = JSONService()
        val view =  inflater.inflate(R.layout.fragment_values, container, false)

        val editTextLeft = view.findViewById<EditText>(R.id.editTextLeft)
        val editTextRight = view.findViewById<EditText>(R.id.editTextRight)
        val spinnerLeft = view.findViewById<Spinner>(R.id.spinnerLeft)
        val spinnerRight = view.findViewById<Spinner>(R.id.spinnerRight)

        viewModel.value.observe(viewLifecycleOwner, { newValue ->
            editTextLeft.setText(newValue)
            jsonService.convert(viewModel.value.value!!.toDouble(), viewModel.list.value!!,
                viewModel.item.value!!, viewModel.convertedItem.value!!)
        })
        viewModel.convertedValue.observe(viewLifecycleOwner, { newValue -> editTextRight.setText(newValue) })
        viewModel.list.observe(viewLifecycleOwner, { newValue ->
            val adapter = ArrayAdapter.createFromResource(requireActivity().applicationContext, viewModel.list.value!!, android.R.layout.simple_spinner_item)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerLeft.adapter = adapter
            spinnerRight.adapter = adapter
            jsonService.convert(viewModel.value.value!!.toDouble(), viewModel.list.value!!,
                viewModel.item.value!!, viewModel.convertedItem.value!!)
        })

        val adapter = ArrayAdapter.createFromResource(requireActivity().applicationContext, viewModel.list.value!!, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerLeft.adapter = adapter
        spinnerRight.adapter = adapter

        spinnerLeft.setSelection(adapter.getPosition(viewModel.item.value))
        spinnerRight.setSelection(adapter.getPosition(viewModel.convertedItem.value))

        spinnerLeft.onItemSelectedListener = this
        spinnerRight.onItemSelectedListener = this

        return view
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
        viewModel.item.value = spinnerLeft.selectedItem.toString()
        viewModel.convertedItem.value = spinnerRight.selectedItem.toString()
        jsonService.convert(viewModel.value.value!!.toDouble(), viewModel.list.value!!,
            viewModel.item.value!!, viewModel.convertedItem.value!!)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    fun changeList(id: Int) {
        viewModel.list.value = id
    }

    companion object {

        fun newInstance(): ValuesFragment {
            return ValuesFragment()
        }
    }
}