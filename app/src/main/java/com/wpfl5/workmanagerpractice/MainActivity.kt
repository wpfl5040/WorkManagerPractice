package com.wpfl5.workmanagerpractice

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.wpfl5.workmanagerpractice.worker.ProgressWorker
import com.wpfl5.workmanagerpractice.worker.ProgressWorker.Companion.Progress
import com.wpfl5.workmanagerpractice.worker.UploadWorker

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MainActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_noti).setOnClickListener {
            startActivity(Intent(this, NotiActivity::class.java))
        }

        findViewById<Button>(R.id.btn_upload_worker).setOnClickListener {
            val uploadWorkRequest: WorkRequest =
                    OneTimeWorkRequestBuilder<UploadWorker>()
                            .build()
            WorkManager
                    .getInstance(applicationContext)
                    .enqueue(uploadWorkRequest)
        }

        findViewById<Button>(R.id.btn_progress_worker).setOnClickListener {
            val progressRequest: WorkRequest =
                OneTimeWorkRequestBuilder<ProgressWorker>()
                        .build()


            WorkManager.getInstance(applicationContext)
                    // requestId is the WorkRequest id
                    .getWorkInfoByIdLiveData(progressRequest.id)
                    .observe(this, Observer { workInfo: WorkInfo? ->
                        if (workInfo != null) {
                            val progress = workInfo.progress
                            val value = progress.getInt(Progress, 0)
                            // Do something with progress information
                            Log.i(TAG, value.toString())
                        }
                    })
        }


    }
}