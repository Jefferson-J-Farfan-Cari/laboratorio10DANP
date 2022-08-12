package org.danp.laboratorio10.workers

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

var COUNTER = 1

class Counter(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    override fun doWork(): Result {
        Log.e("Question", "2")
        count()
        return Result.success()
    }

    private fun count() {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
        val formatted = current.format(formatter)
        Log.e("TAG COUNTER", "COUNTER VALUE: $COUNTER, DATE: $formatted")
        COUNTER += 1
    }
}