package com.example.converter

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_values.*


class ValuesFragment : Fragment(), AdapterView.OnItemSelectedListener, View.OnClickListener {

    private val viewModel: MyViewModel by activityViewModels()
    private lateinit var service: Service
    private lateinit var spinnerLeft: Spinner
    private lateinit var spinnerRight: Spinner
    private lateinit var clipboard: ClipboardManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        service = Service()
        val view =  inflater.inflate(R.layout.fragment_values, container, false)

        clipboard = requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val editTextLeft = view.findViewById<EditText>(R.id.editTextLeft)
        val editTextRight = view.findViewById<EditText>(R.id.editTextRight)
        editTextLeft.setOnClickListener(::onEditTextClick)
        editTextRight.setOnClickListener(::onEditTextClick)
        spinnerLeft = view.findViewById<Spinner>(R.id.spinnerLeft)
        spinnerRight = view.findViewById<Spinner>(R.id.spinnerRight)
        view.findViewById<ImageButton>(R.id.imageButton).setOnClickListener(this)

        viewModel.value.observe(viewLifecycleOwner, { newValue ->
            editTextLeft.setText(newValue)

            convert()
        })

        viewModel.convertedValue.observe(viewLifecycleOwner, { newValue -> editTextRight.setText(newValue) })

        viewModel.list.observe(viewLifecycleOwner, { newValue ->
            val adapter = ArrayAdapter.createFromResource(requireActivity().applicationContext, viewModel.list.value!!, android.R.layout.simple_spinner_item)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            spinnerLeft.adapter = adapter
            spinnerRight.adapter = adapter

            convert()
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

        convert()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    fun convert() {
        var valueToConvert = viewModel.value.value!!
        if (valueToConvert.isNotEmpty()) {
            if (valueToConvert[valueToConvert.length - 1] == '.') {
                valueToConvert += '0'
            }
            viewModel.convertedValue.value = service.convert(viewModel.value.value!!.toDouble(), viewModel.list.value!!,
                viewModel.item.value!!, viewModel.convertedItem.value!!).toString()
        }
        else {
            viewModel.convertedValue.value = ""
        }
    }

    override fun onClick(v: View?) {
        val button = v as ImageButton

        when(button.id) {
            R.id.imageButton -> {
                viewModel.value.value = viewModel.convertedValue.value.also {
                    viewModel.convertedValue.value = viewModel.value.value
                }
                val temp = spinnerLeft.selectedItemPosition
                spinnerLeft.setSelection(spinnerRight.selectedItemPosition).also {
                    spinnerRight.setSelection(temp)
                }
            }
        }
    }

    private fun onEditTextClick(v: View?) {
        clipboard.setPrimaryClip(ClipData.newPlainText("Value", (v as EditText).text))
        Toast.makeText(context, "Copied to clipboard", Toast.LENGTH_SHORT).show()
    }

    fun changeList(id: Int) {
        viewModel.list.value = id
    }

    companion object {

        fun newInstance(): ValuesFragment {
            return ValuesFragment()
        }
    }
}