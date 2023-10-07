package org.tumo.myapplication.xml

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.tumo.myapplication.R

class ActivityWithFragmentNavigation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_navigation)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, BlankFragmentFirst.newInstance("", ""))
                .commitNow()
        }

    }
}