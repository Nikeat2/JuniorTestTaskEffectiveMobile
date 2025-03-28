package com.example.mainscreen.homepage.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.retrofit.RetrofitClient
import com.example.domain.AppRepositoryImpl
import com.example.mainscreen.basefragment.BaseFragment
import com.example.mainscreen.databinding.FragmentMainScreenBinding
import com.example.mainscreen.homepage.viewmodel.HomePageViewModel
import kotlinx.coroutines.launch

class MainScreenFragment : BaseFragment() {
    override fun shouldShowBottomNav() = true

    private var _binding: FragmentMainScreenBinding? = null
    private val binding get() = _binding!!
    private val adapter = CoursesListAdapter()

    private val viewModel: HomePageViewModel by viewModels()
    private lateinit var repository: AppRepositoryImpl


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        binding.coursesRecyclerView.adapter = adapter
        binding.coursesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toPublishDateTextView.setOnClickListener {
            adapter.submitList(viewModel.sortItemsByDateDescending(adapter.currentList))
        }

        repository = AppRepositoryImpl(RetrofitClient.getInstance(requireContext()).allPersonApi)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.getResult(repository)
                viewModel.coursesListState.collect { list ->
                    adapter.submitList(list.toList())
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainScreenFragment()
    }
}