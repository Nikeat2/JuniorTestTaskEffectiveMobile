package com.example.mainscreen.homepage.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.room.OnFavoriteButtonClick
import com.example.domain.Course
import com.example.mainscreen.basefragment.BaseFragment
import com.example.mainscreen.databinding.FragmentMainScreenBinding
import com.example.mainscreen.homepage.viewmodel.HomePageViewModel
import kotlinx.coroutines.launch

class MainScreenFragment : BaseFragment(), OnFavoriteButtonClick {

    private var _binding: FragmentMainScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomePageViewModel
    private val adapter = CoursesListAdapter(this)

    override fun shouldShowBottomNav() = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        binding.coursesRecyclerView.adapter = adapter
        viewModel = ViewModelProvider(
            requireActivity(),
            HomePageViewModel.Factory
        )[HomePageViewModel::class.java]
        binding.coursesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toPublishDateTextView.setOnClickListener {
            adapter.submitList(viewModel.sortItemsByDateDescending(adapter.currentList))
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.coursesListState.collect { list ->
                    adapter.submitList(list.toList())
                }
            }
        }
    }

    override fun onButtonClick(courseEntity: Course, position: Int) {
        courseEntity.hasLike = !courseEntity.hasLike
        adapter.notifyItemChanged(position)
        if (courseEntity.hasLike) {
            viewModel.saveACourse(courseEntity)
        } else {
            viewModel.deleteACourse(courseEntity)
        }
        viewModel.updateState(adapter.currentList.toList())
        adapter.submitList(viewModel.coursesListState.value)
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainScreenFragment()
    }

}