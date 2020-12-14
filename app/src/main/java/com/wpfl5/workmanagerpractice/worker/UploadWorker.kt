package com.wpfl5.workmanagerpractice.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class UploadWorker(appContext: Context, workerParameters: WorkerParameters): Worker(appContext, workerParameters){
    override fun doWork(): Result {
        upload()
        Log.d("UploadWorker", "success")
        return Result.success()
    }


    private fun upload() {

    }
}