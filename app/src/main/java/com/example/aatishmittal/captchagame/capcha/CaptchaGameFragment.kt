package com.example.aatishmittal.captchagame.capcha

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.example.aatishmittal.captchagame.R
import com.example.aatishmittal.captchagame.data.State

class CaptchaGameFragment : Fragment(){
    private lateinit var viewModel : CaptchaGameViewModel
    private lateinit var parentLayout : View
    private lateinit var progress : View
    private lateinit var image : ImageView
    private lateinit var timerText : TextView
    private lateinit var submitButton : Button
    private lateinit var editText: EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = (activity as CaptchaActivity).obtainViewModel()
        val view = inflate(context, R.layout.captcha_game_frag, null)
        parentLayout = view.findViewById(R.id.parent_layout)
        progress = view.findViewById(R.id.progress)
        image = view.findViewById(R.id.image)
        timerText = view.findViewById(R.id.time)
        submitButton = view.findViewById(R.id.submit)
        editText = view.findViewById(R.id.edit)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.start()
        setUpCaptcha()
        setupUiState()
        setUpTimerText()
        setUpSubmitButton()
    }

    private fun setupUiState()
    {
        viewModel.uiState.observe(this, Observer<State> {
            when(it)
            {
                State.LOADING -> {
                    parentLayout.visibility = View.GONE
                    progress.visibility = View.VISIBLE
                }

                State.QUIZ -> {
                    parentLayout.visibility = View.VISIBLE
                    progress.visibility = View.GONE
                }
                State.RESULT->{
                    viewModel.openResultScreen.call()
                }
            }
        })
    }

    private fun setUpCaptcha()
    {
        viewModel.currentCaptcha.observe(this, Observer {
            val id = activity?.resources?.getIdentifier(it?.name, "drawable", activity!!.packageName)
            if(id != null)
                image.setImageResource(id)
        })
    }

    private fun setUpTimerText()
    {
        viewModel.currentTimer.observe(this, Observer {
            timerText.text = ""+it
        })
    }

    private fun setUpSubmitButton()
    {
        submitButton.setOnClickListener {
            viewModel.submit(editText.text.toString())
            editText.text.clear()
        }
    }
}