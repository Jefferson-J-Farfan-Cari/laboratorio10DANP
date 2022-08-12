package org.danp.laboratorio10.workers

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class WorkA(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        workingA()
        return Result.success()
    }

    private fun workingA() {
        Log.e("TAG", "Work A")
    }
}