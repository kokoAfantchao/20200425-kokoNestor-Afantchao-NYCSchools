package com.push.nycschools.utils

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors




open class AppExecutors(val diskIO : Executor = DiskIOThreadExecutor(),
                   val networkIO : Executor = Executors.newFixedThreadPool(THREAD_COUNT),
                   val mainThread : Executor = MainThreadExecutor()){

    companion object {
        private val THREAD_COUNT = 3
    }
}


 class DiskIOThreadExecutor(val  diskIO : Executor =Executors.newSingleThreadExecutor()  ) : Executor{

    override fun execute(command: Runnable) {
      diskIO.execute(command)
    }
}

class MainThreadExecutor(val mainThreadExecutor : Handler = Handler(Looper.getMainLooper()))  : Executor{

    override fun execute(command: Runnable) {
      mainThreadExecutor.post(command)
    }
}
