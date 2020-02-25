package example.wgtech.rxjava.chapter4

import java.util.*

internal object CommonUtilKotlin  {
    var startTime: Long = 0

    fun exampleStart() {
        startTime = System.currentTimeMillis()
    }

    fun exampleComplete() {
        startTime = System.currentTimeMillis()
    }

    fun sleep(mills: Int) {
        try {
            Thread.sleep(mills.toLong())
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    fun getThreadName(): String {
        var threadName = Thread.currentThread().name
        if (threadName.length > 30) {
            threadName = threadName.substring(0, 30) + "..."
        }

        return threadName
    }

    fun doSomething() {
        try {
            Thread.sleep(Random().nextInt(100).toLong())
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}