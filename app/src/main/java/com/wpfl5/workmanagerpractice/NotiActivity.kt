package com.wpfl5.workmanagerpractice

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.wpfl5.workmanagerpractice.utils.NotiUtil

class NotiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noti)

        findViewById<Button>(R.id.btnBasic).setOnClickListener {
            NotiUtil().basic(applicationContext)
        }

        findViewById<Button>(R.id.btnBigTextStyle).setOnClickListener {
            NotiUtil().bigTextStyle(applicationContext)
        }

        findViewById<Button>(R.id.btnBigPictureStyle).setOnClickListener {
            NotiUtil().bigPictureStyle(applicationContext, resources)
        }

        findViewById<Button>(R.id.btnBigTextStyle).setOnClickListener {
            NotiUtil().bigTextStyle(applicationContext)
        }

    }

}