package com.example.cookbook.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.cookbook.databinding.FragmentDetailBinding
import com.example.cookbook.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel
    val args: DetailFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : DetailViewModel by viewModels ()
        viewModel = tempViewModel
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDetailBinding.inflate(inflater,container,false)
        binding.viewModel = viewModel
        binding.recipeDetailToolbarName = "Tarif Detay"

        val recipeId = args.recipeId

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.recipeDetail(recipeId)
        }

        viewModel.recipeDetail.observe(viewLifecycleOwner) { recipe ->
            binding.recipeObject = recipe?.recipe
        }

        binding.buttonUpdate.setOnClickListener {
            val name = binding.editTextName.text.toString()
            val recipe = binding.editTextContent.text.toString()

            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.recipeUpdate(args.recipeId, name, recipe)
            }

            viewModel.recipeDetail.observe(viewLifecycleOwner) { recipe ->
                if (recipe != null) {
                    Toast.makeText(requireContext(), "Tarif Güncellendi", Toast.LENGTH_SHORT).show()
                    val action = DetailFragmentDirections.actionDetailFragmentToHomeFragment()
                    Navigation.findNavController(it).navigate(action)
                } else {
                    Toast.makeText(requireContext(), "Tarif Güncellenmedi", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return binding.root
    }

}
