package com.android.mvi.presentation

import  androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.android.mvi.R
import com.android.mvi.domain.model.Character
import com.android.mvi.domain.state.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG: String = "AppDebug"

    private val viewModel: MainViewModel by  viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetCharacterEvents)
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this, Observer { dataState ->
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
