package com.example.converter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels


class KeyboardFragment : Fragment(), View.OnClickListener {

    private val viewModel: MyViewModel by activityViewModels()
    private lateinit var service: Service

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        service = Service()

        val view =  inflater.inflate(R.layout.fragment_keyboard, container, false)

        view.findViewById<Button>(R.id.button0).setOnClickListener(this)
        view.findViewById<Button>(R.id.button1).setOnClickListener(this)
        view.findViewById<Button>(R.id.button2).setOnClickListener(this)
        view.findViewById<Button>(R.id.button3).setOnClickListener(this)
        view.findViewById<Button>(R.id.button4).setOnClickListener(this)
        view.findViewById<Button>(R.id.button5).setOnClickListener(this)
        view.findViewById<Button>(R.id.button6).setOnClickListener(this)
        view.findViewById<Button>(R.id.button7).setOnClickListener(this)
        view.findViewById<Button>(R.id.button8).setOnClickListener(this)
        view.findViewById<Button>(R.id.button9).setOnClickListener(this)
        view.findViewById<Button>(R.id.buttonDot).setOnClickListener(this)
        view.findViewById<Button>(R.id.buttonDelete).setOnClickListener(this)

        return view
    }

    override fun onClick(v: View?) {
        val button = v as Button

        when(button.id) {
            R.id.buttonDelete -> {
                if (viewModel.value.value != "") {
                    service.delClick(viewModel.value)
                }
            }
            R.id.buttonDot -> {
                if (!viewModel.value.value!!.contains('.')) {
                    service.dotClick(viewModel.value)
                }
            }
            else -> {
                service.numClick(viewModel.value, button.text.toString())
            }
        }
    }

    companion object {

        fun newInstance(): KeyboardFragment {
            return KeyboardFragment()
        }
    }
}