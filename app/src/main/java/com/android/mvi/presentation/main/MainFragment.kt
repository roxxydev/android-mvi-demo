package com.android.mvi.presentation.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.mvi.R
import com.android.mvi.domain.model.Character
import com.android.mvi.domain.state.DataState
import com.android.mvi.presentation.UiUtil
import com.android.mvi.presentation.adapter.CharacterRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*

@AndroidEntryPoint
class MainFragment: Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by  viewModels()
    private lateinit var characterRecyclerAdapter: CharacterRecyclerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerview()
        subscribeObservers()
        viewModel.setStateEvent(MainIntent.GetCharactersIntent)
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState) {

                is DataState.SUCCESS<MainDataState> -> {
                    displayProgressBar(false)

                    dataState.data?.let { it ->
                        when {
                            it.characters.isEmpty() -> {
                                val toastMsg = getString(R.string.empty_results)
                                UiUtil.displayToast(requireContext(), toastMsg)
                            }
                            it.characters.isNotEmpty() -> {
                                updateList(it.characters)
                            }
                            it.cacheCharacters.isNotEmpty() -> {
                                updateList(it.cacheCharacters)
                            }
                        }
                    }
                }

                is DataState.LOADING -> {
                    displayProgressBar(dataState.loading)
                }

                is DataState.ERROR -> {
                    displayProgressBar(dataState.loading)
                    dataState.stateMessage?.message?.let {
                        UiUtil.displayToast(requireContext(), it)
                    }
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

    private fun updateList(characters: List<Character>?) {
        characters?.let {
            characterRecyclerAdapter.submitList(it)
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