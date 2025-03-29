package com.example.mainscreen.favoritepage.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.models.Course
import com.example.mainscreen.databinding.FragmentFavoriteCoursesBinding
import com.example.mainscreen.favoritepage.viewmodel.FavoriteCoursesViewModel
import com.example.mainscreen.homepage.view.CoursesListAdapter
import com.example.mainscreen.room.OnFavoriteButtonClick
import kotlinx.coroutines.launch

class FavoriteCoursesFragment : Fragment(), OnFavoriteButtonClick {

    private var _binding: FragmentFavoriteCoursesBinding? = null
    private val binding get() = _binding!!
    private val adapter = CoursesListAdapter(this)

    private val viewModel: FavoriteCoursesViewModel by viewModels { FavoriteCoursesViewModel.Factory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteCoursesBinding.inflate(inflater, container, false)
        binding.favoriteCoursesRecyclerView.adapter = adapter
        binding.favoriteCoursesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.coursesListState.collect { data ->
                    adapter.submitList(data.toList())
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = FavoriteCoursesFragment()
    }

    override fun onButtonClick(course: Course, position: Int) {
        course.hasLike = !course.hasLike
        if (!course.hasLike) {
            viewModel.deleteACourse(course)
        }
        viewModel.getLists()
        adapter.notifyItemChanged(position)
        adapter.submitList(viewModel.coursesListState.value)
    }
}