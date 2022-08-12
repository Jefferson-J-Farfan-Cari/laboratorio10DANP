package org.danp.laboratorio10.workers

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class WorkB (context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    override fun doWork(): Result {
        workingB()
        return Result.success()
    }

    private fun workingB() {
        Log.e("TAG", "Work B")
    }
}