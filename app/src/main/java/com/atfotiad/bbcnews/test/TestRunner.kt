package com.atfotiad.bbcnews.test

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.atfotiad.bbcnews.NewsApplication

class TestRunner : AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, NewsApplication::class.java.name, context)
    }
}
