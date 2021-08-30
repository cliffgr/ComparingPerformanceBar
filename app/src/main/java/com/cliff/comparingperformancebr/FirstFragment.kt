package com.cliff.comparingperformancebr

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.cliff.comparingperformancebar.PercentageProgressBar
import com.cliff.comparingperformancebar.ValueProgressBar

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val progressBar: PercentageProgressBar = view.findViewById(R.id.percentageProgressBar)
        progressBar.setProgress(30.0f)

        val valueProgressBar: ValueProgressBar = view.findViewById(R.id.valuesProgressBar)
        valueProgressBar.setValues(10.0f, 7.0f)

        val valueProgressBarPercent: ValueProgressBar = view.findViewById(R.id.valuesProgressBarPercent)
        valueProgressBarPercent.setValues(10.0f, 7.0f)
    }
}