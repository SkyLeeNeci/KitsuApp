package karpenko.test.kitsuapp.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import karpenko.test.kitsuapp.R
import karpenko.test.kitsuapp.databinding.FragmentMangaListBinding
import karpenko.test.kitsuapp.view.adapter.MangaListAdapter
import karpenko.test.kitsuapp.viewmodel.MangaListViewModel

class MangaListFragment : Fragment() {

    private lateinit var binding: FragmentMangaListBinding
    private val viewModel: MangaListViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        ) [MangaListViewModel::class.java]
    }

    private var mangaAdapter = MangaListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMangaListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) viewModel.loadDataFromRemote() else viewModel.loadDataFromDatabase()

        observeViewModel()

        binding.animeListRV.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mangaAdapter
        }

        binding.swipeToRefresh.setOnRefreshListener {
            binding.animeListRV.visibility = View.GONE
            binding.errorMessageTV.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
            viewModel.loadDataFromRemote()
            binding.swipeToRefresh.isRefreshing = false
        }

    }

    private fun observeViewModel(){

        viewModel.listOfMangaLiveData.observe(viewLifecycleOwner){
            it?.let {
                binding.animeListRV.visibility = View.VISIBLE
                mangaAdapter.mangaAttributesList = it
            }
        }

        viewModel.loadingLiveData.observe(viewLifecycleOwner){
            it?.let {
                binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
                if (it){
                    binding.errorMessageTV.visibility = View.GONE
                    binding.animeListRV.visibility = View.GONE
                }
            }
        }

        viewModel.mangaLoadErrorLiveData.observe(viewLifecycleOwner){
            it?.let {
                binding.errorMessageTV.visibility = if (it) View.VISIBLE else View.GONE
                if (it) binding.progressBar.visibility = View.GONE
            }
        }

    }
}