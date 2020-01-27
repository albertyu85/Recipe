package com.example.recipe.ui.detail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer

import com.example.recipe.R
import com.example.recipe.databinding.DetailFragmentBinding
import kotlinx.android.synthetic.main.detail_fragment.*

class DetailFragment : Fragment() {

    companion object {
        fun newInstance() = DetailFragment()
    }

    private lateinit var viewModel: DetailViewModel
    private lateinit var binding : DetailFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewModel.response.observe(this, Observer {
            detail_textView.text = it.results.size.toString()
        })
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }



}
