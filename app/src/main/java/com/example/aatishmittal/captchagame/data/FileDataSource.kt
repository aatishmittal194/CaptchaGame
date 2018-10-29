package com.example.aatishmittal.captchagame.data

import android.content.Context
import com.example.aatishmittal.captchagame.R
import com.example.android.architecture.blueprints.todoapp.util.AppExecutors
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader

class FileDataSource(private val apiExecutors: AppExecutors, val context : Context) : DataSource{

    override fun getCaptchaList(callback: DataSource.CaptchaListCallback) {
        apiExecutors.diskIO.execute{
            var gson = Gson()
            val inputStream = context.resources.openRawResource(R.raw.captcha)
            val reader = BufferedReader(InputStreamReader(inputStream))
            val stringBuilder = StringBuilder()
            var line: String? = reader.readLine()
            do {
                stringBuilder.append(line)
                line = reader.readLine()
            } while (line != null)

            val type = object : TypeToken<List<Captcha>>() {}.type
            val captchaList: List<Captcha> = gson.fromJson(stringBuilder.toString(), type)

            apiExecutors.mainThread.execute{
                callback.onSuccess(captchaList)
            }
        }
    }
}