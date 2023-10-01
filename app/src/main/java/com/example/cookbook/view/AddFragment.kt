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
import com.example.cookbook.databinding.FragmentAddBinding
import com.example.cookbook.viewmodel.AddViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private lateinit var viewModel: AddViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater,container,false)

        val tempViewModel: AddViewModel by viewModels()
        viewModel = tempViewModel

        binding.viewModel = viewModel
        binding.addToolbarName = "Tarif Ekle"
        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonUpdate.setOnClickListener {
            val name = binding.editTextName.text.toString()
            val recipe = binding.editTextContent.text.toString()

            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.addRecipes(name, recipe)
            }

            viewModel.add.observe(viewLifecycleOwner) { recipe ->
                if (recipe != null) {
                    Toast.makeText(requireContext(), "Tarif Eklendi", Toast.LENGTH_SHORT).show()
                    val action = AddFragmentDirections.actionAddFragmentToHomeFragment()
                    Navigation.findNavController(it).navigate(action)
                } else {
                    Toast.makeText(requireContext(), "Tarif Eklenemedi", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}







