package com.example.apphero.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apphero.R
import com.example.apphero.databinding.FragmentHeroListBinding
import com.example.apphero.model.HeroDataModel
import com.example.apphero.view.adapter.ItemsAdapter
import com.example.apphero.viewmodel.HeroViewModel


class HeroListFragment : Fragment(), ClickListener {
    lateinit var viewModel: HeroViewModel
    lateinit var binding: FragmentHeroListBinding
    private var mAdapter: ItemsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = activity?.let{
            ViewModelProvider(it).get(HeroViewModel::class.java)
        }!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_hero_list, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAdapter = ItemsAdapter(this)
        binding.recyclerview.layoutManager = LinearLayoutManager(context)
        binding.recyclerview.adapter = mAdapter

        viewModel.listState.observe(viewLifecycleOwner){
            mAdapter?.setItems(list = it)
            binding.progress.isInvisible = true
        }

        viewModel.progressState.observe(viewLifecycleOwner){
            binding.progress.isVisible = true
        }

        viewModel.fetchHeroData()
    }

    override fun itemSelect(data: HeroDataModel){
        viewModel.setItemSelection(data)

        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(android.R.id.content, HeroDetailFragment.newInstance())
            ?.addToBackStack(null)
            ?.commit()
    }
}

interface ClickListener{
    fun itemSelect(data: HeroDataModel)
}