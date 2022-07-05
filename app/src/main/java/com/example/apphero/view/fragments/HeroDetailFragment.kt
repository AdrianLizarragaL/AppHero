package com.example.apphero.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.apphero.databinding.FragmentHeroDetailBinding
import com.example.apphero.viewmodel.HeroViewModel

class HeroDetailFragment : Fragment() {
    private var _binding: FragmentHeroDetailBinding? = null
    private val binding get() = _binding

    lateinit var viewModel: HeroViewModel

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
        _binding = FragmentHeroDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.itemDataSelected?.let{ data->
            Glide.with(requireContext()).load(data.images.sm).into(binding!!.img)
            binding!!.tvName.text = data.name
            binding!!.tvGenero.text = data.appearance.gender
            binding!!.tvRaza.text = data.appearance.race
            binding!!.tvFullName.text = data.biography.fullName
            binding!!.tvLugarNacimiento.text = data.biography.placeOfBirth
            binding!!.tvEditorial.text = data.biography.publisher
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        fun newInstance() = HeroDetailFragment()
    }

}