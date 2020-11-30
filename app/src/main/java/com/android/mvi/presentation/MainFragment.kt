package com.android.mvi.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.mvi.R
import com.android.mvi.domain.model.Character
import com.android.mvi.domain.state.DataState
import com.android.mvi.presentation.adapter.CharacterRecyclerAdapter
import com.android.mvi.presentation.viewmodel.MainStateEvent
import com.android.mvi.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*


@AndroidEntryPoint
class MainFragment
constructor(
    private val logTag: String
): Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by  viewModels()
    private lateinit var characterRecyclerAdapter: CharacterRecyclerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerview()
        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetCharacterEvents)
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState) {
                is DataState.Success<List<Character>> -> {
                    displayProgressBar(false)
                    characterRecyclerAdapter.submitList(dataState.data)
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    Log.e(logTag, dataState.exception.message)
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        if (isDisplayed) {
            progress_bar.visibility = View.VISIBLE
        }
        else {
            progress_bar.visibility = View.GONE
        }
    }

    private fun initRecyclerview() {
        main_recyclerview.apply {
            layoutManager = LinearLayoutManager(this.context)

            val itemDecoration: CharacterRecyclerAdapter.TopSpacingDecoration =
                CharacterRecyclerAdapter.TopSpacingDecoration(30)
            addItemDecoration(itemDecoration)

            characterRecyclerAdapter = CharacterRecyclerAdapter()
            adapter = characterRecyclerAdapter
        }
    }
}