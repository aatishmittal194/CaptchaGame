package com.example.aatishmittal.captchagame.capcha

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import com.example.aatishmittal.captchagame.R

class CaptchaResultFragment : Fragment(){
    private lateinit var viewModel : CaptchaResultViewModel
    private lateinit var resultAdapter : CaptchaResultAdapter
    private lateinit var recyclerView : RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = (activity as CaptchaActivity).obtainResultViewModel()
        val view = inflate(context, R.layout.captcha_result_frag, null)
        recyclerView = view.findViewById(R.id.recycler_view)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()
        viewModel.start()
    }

    private fun setupRecyclerView()
    {
        resultAdapter = CaptchaResultAdapter(activity!!.applicationContext)
        recyclerView.adapter = resultAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
        viewModel.getResultList().observe(this, Observer {
            resultAdapter.setList(it!!)
        })
    }
}