package com.mfthc.foodbookkotlin.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.mfthc.foodbookkotlin.databinding.FragmentFoodDetailBinding
import com.mfthc.foodbookkotlin.util.downloadImage
import com.mfthc.foodbookkotlin.util.makePlaceHolder
import com.mfthc.foodbookkotlin.viewmodel.FoodDetailViewModel
import kotlinx.coroutines.CoroutineScope


class FoodDetailFragment : Fragment() {

    private var _binding: FragmentFoodDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FoodDetailViewModel
    private var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFoodDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[FoodDetailViewModel::class.java]

        arguments?.let {
            id = FoodDetailFragmentArgs.fromBundle(it).foodID
        }
        viewModel.getRoomData(id)

        observeLiveData()

    }

    private fun observeLiveData() {
        viewModel.foodLiveData.observe(viewLifecycleOwner) {
            binding.foodImage.downloadImage(it.image, makePlaceHolder(requireContext()))
            binding.foodName.text = it.name
            binding.foodCalorie.text = it.calorie
            binding.foodCarbohydrate.text = it.carbohydrate
            binding.foodProtein.text = it.protein
            binding.foodFat.text = it.fat


        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}