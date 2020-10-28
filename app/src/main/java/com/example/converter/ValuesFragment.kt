package com.example.converter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_values.*


class ValuesFragment : Fragment() {

    private val viewModel: MyViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_values, container, false)

        val editTextLeft = view.findViewById<EditText>(R.id.editTextLeft)
        val editTextRight = view.findViewById<EditText>(R.id.editTextRight)

        viewModel.value.observe(viewLifecycleOwner, { newValue -> editTextLeft.setText(newValue) })
        viewModel.convertedValue.observe(viewLifecycleOwner, { newValue -> editTextRight.setText(newValue) })

        return view
    }

    companion object {

        fun newInstance(): ValuesFragment {
            return ValuesFragment()
        }
    }
}