package com.example.cookbook.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.cookbook.R
import com.example.cookbook.adapter.RecipeAdapter
import com.example.cookbook.databinding.FragmentHomeBinding
import com.example.cookbook.util.gecisYap
import com.example.cookbook.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        val tempViewModel: HomeViewModel by viewModels()
        viewModel = tempViewModel

        binding.homeFragment = this

        viewModel.getRecipes()

        viewModel.recipesList.observe(viewLifecycleOwner) {
            adapter = RecipeAdapter(it, viewModel)
            binding.Rv.adapter = adapter
            adapter.notifyDataSetChanged()
        }

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.toolbar_menu, menu)

                val item = menu.findItem(R.id.action_search)
                val searchView = item.actionView as SearchView
                searchView.setOnQueryTextListener(this@HomeFragment)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        return binding.root
    }

    fun fabClick(it: View) {
        Navigation.gecisYap(R.id.action_homeFragment_to_addFragment, it)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        viewLifecycleOwner.lifecycleScope.launch {
            if (query != null) {
                viewModel.Search(query)
                viewModel.recipeSearch.observe(viewLifecycleOwner) {
                    adapter = RecipeAdapter(it.recipes, viewModel)
                    binding.Rv.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
            }
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        viewLifecycleOwner.lifecycleScope.launch {
            if (newText != null) {
                viewModel.Search(newText)
                viewModel.recipeSearch.observe(viewLifecycleOwner) {
                    adapter = RecipeAdapter(it.recipes, viewModel)
                    binding.Rv.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
            }
        }
        return true
    }



}