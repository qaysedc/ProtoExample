package com.example.protoexample

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.protoexample.databinding.SimpleFragmentBinding

class SimpleFragment : Fragment() {

    private lateinit var binding : SimpleFragmentBinding
    private lateinit var viewModel: SimpleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.simple_fragment,
            container,
            false
        )
        viewModel = ViewModelProvider(this).get(SimpleViewModel::class.java)
        binding.button.setOnClickListener {viewModel.onButtonClicked()}
        viewModel.counter.observe(viewLifecycleOwner, Observer { value -> binding.textView.text = value.toString() })

        return binding.root
    }

}