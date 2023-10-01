package com.example.cookbook.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.cookbook.R
import com.example.cookbook.databinding.ItemBinding
import com.example.cookbook.model.Recipe
import com.example.cookbook.util.gecisYap
import com.example.cookbook.view.HomeFragmentDirections
import com.example.cookbook.viewmodel.HomeViewModel

class RecipeAdapter(
    var recipeList: List<Recipe>,
    var viewModel: HomeViewModel
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    inner class RecipeViewHolder(binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var binding: ItemBinding

        init {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item, parent, false)
        return RecipeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipeList.get(position)
        val t = holder.binding

        t.selectedRecipe = recipe

        t.card.setOnClickListener {
            var id = recipe.recipe_id
            val gecis =
                HomeFragmentDirections.actionHomeFragmentToDetailFragment(recipeId = id)
            Navigation.gecisYap(it, gecis)
        }
    }

}