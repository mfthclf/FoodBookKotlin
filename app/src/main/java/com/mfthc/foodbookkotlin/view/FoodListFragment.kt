package com.mfthc.foodbookkotlin.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mfthc.foodbookkotlin.databinding.FragmentFoodListBinding
import com.mfthc.foodbookkotlin.service.FoodAPI
import com.mfthc.foodbookkotlin.service.FoodAPIService
import com.mfthc.foodbookkotlin.viewmodel.FoodListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


class FoodListFragment : Fragment() {

    private var _binding: FragmentFoodListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FoodListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFoodListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[FoodListViewModel::class.java]
        viewModel.refreshData()

        binding.foodRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.foodErrorMessage.visibility = View.GONE
            binding.foodRecyclerView.visibility = View.GONE
            binding.foodProgressBar.visibility = View.VISIBLE
            viewModel.refreshDataFromInternet()
            binding.swipeRefreshLayout.isRefreshing = false

        }
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.foods.observe(viewLifecycleOwner) {
            binding.foodRecyclerView.visibility = View.VISIBLE
        }
        viewModel.foodErrorMessage.observe(viewLifecycleOwner) {
            if (it) {
                binding.foodErrorMessage.visibility = View.VISIBLE
                binding.foodRecyclerView.visibility = View.GONE
            } else {
                binding.foodErrorMessage.visibility = View.GONE
            }
        }
        viewModel.foodLoading.observe(viewLifecycleOwner) {
            if (it) {
                binding.foodErrorMessage.visibility = View.GONE
                binding.foodRecyclerView.visibility = View.GONE
                binding.foodProgressBar.visibility = View.VISIBLE
            } else {
                binding.foodProgressBar.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}