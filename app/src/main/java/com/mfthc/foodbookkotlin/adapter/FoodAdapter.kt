package com.mfthc.foodbookkotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.mfthc.foodbookkotlin.databinding.RecyclerRowBinding
import com.mfthc.foodbookkotlin.model.Food
import com.mfthc.foodbookkotlin.util.downloadImage
import com.mfthc.foodbookkotlin.util.makePlaceHolder
import com.mfthc.foodbookkotlin.view.FoodListFragmentDirections

class FoodAdapter(val foodList:ArrayList<Food>) : RecyclerView.Adapter<FoodAdapter.FoodHolder>() {


    class FoodHolder(val binding:RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FoodHolder(binding)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    override fun onBindViewHolder(holder: FoodHolder, position: Int) {
        holder.binding.foodNameTextView.text = foodList[position].name
        holder.binding.foodCalorieTextView.text = foodList[position].calorie

        holder.itemView.setOnClickListener{
            val action = FoodListFragmentDirections.actionFoodListFragmentToFoodDetailFragment(foodList[position].id)
            Navigation.findNavController(it).navigate(action)
        }
        holder.binding.imageView.downloadImage(foodList[position].image, makePlaceHolder(holder.itemView.context))
    }
    fun refreshFoodList(newFoodList:List<Food>){
        foodList.clear()
        foodList.addAll(newFoodList)
        notifyDataSetChanged()
    }
}