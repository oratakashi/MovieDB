package com.oratakashi.oratamovie.ui.about

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.oratakashi.oratamovie.R
import com.oratakashi.oratamovie.databinding.FragmentAboutBinding
import com.oratakashi.viewbinding.core.viewBinding


class AboutFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    private val binding : FragmentAboutBinding by viewBinding()

    companion object {
        @JvmStatic
        fun newInstance() = AboutFragment()
    }
}