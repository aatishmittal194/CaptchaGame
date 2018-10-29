package com.example.aatishmittal.captchagame.capcha

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.aatishmittal.captchagame.R
import com.example.aatishmittal.captchagame.data.CaptchaResult

class CaptchaResultAdapter(val context : Context) : RecyclerView.Adapter<CaptchaResultAdapter.ViewHolder>() {

    var resultList : ArrayList<CaptchaResult> = ArrayList()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(View.inflate(p0.context, R.layout.captcha_result_item, null))
    }

    override fun getItemCount(): Int {
        return resultList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val captchaResult = resultList.get(p1)
        val id = context.resources?.getIdentifier(captchaResult.captcha.name, "drawable", context.packageName)
        p0.image.setImageResource(id!!)
        p0.answer.text = captchaResult.captcha.answer
        if(captchaResult.isCorrect)
            p0.result.text = "Correct"
        else
            p0.result.text = "Incorrect"
    }

    fun setList(list : ArrayList<CaptchaResult>)
    {
        resultList = list
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
     val image = itemView.findViewById<ImageView>(R.id.image)!!
     val result = itemView.findViewById<TextView>(R.id.result)!!
     val answer = itemView.findViewById<TextView>(R.id.answer)!!

 }
}