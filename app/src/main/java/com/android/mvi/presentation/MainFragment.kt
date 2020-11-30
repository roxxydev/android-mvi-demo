package com.android.mvi.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.android.mvi.R
import com.android.mvi.domain.model.Character
import com.android.mvi.domain.state.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*


@AndroidEntryPoint
class MainFragment
constructor(
    private val somString: String
): Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by  viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetCharacterEvents)
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState) {
                is DataState.Success<List<Character>> -> {
                    displayProgressBar(false)
                    appendCharacterTitles(dataState.data)
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exception.message)
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }

    private fun displayError(message: String?) {
        if (message != null) {
            text.text = message
        }
        else {
            text.text = "Unknown error"
        }
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        if (isDisplayed) {
            progress_bar.visibility = View.VISIBLE
        }
        else {
            progress_bar.visibility = View.GONE
        }
    }

    private fun appendCharacterTitles(characters: List<Character>) {
        val sb = StringBuilder()
        for (character in characters) {
            sb.append(character.title + "\n")
        }
        text.text = sb.toString()
    }
}