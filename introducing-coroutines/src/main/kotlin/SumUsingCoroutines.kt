//val SEQUENTIAL_THRESHOLD = 5000

import kotlinx.coroutines.*
import kotlinx.coroutines.async
import kotlin.system.*
import kotlin.system.measureTimeMillis

suspend  fun computecr(array: IntArray, low: Int, high: Int): Long {
    return if (high - low <= SEQUENTIAL_THRESHOLD) {
        (low until high)
                .map { array[it].toLong() }
                .sum()
    } else {
        val mid = low + (high - low) / 2
        val left = kotlinx.coroutines.async { compute(array, low, mid) }
        val right = compute(array, mid, high)
        return left.await() + right
    }
}

fun main(args: Array<String>) = runBlocking {
    val list = mutableListOf<Int>()

    var limit = 20_000_000

    while (limit > 0) {
        list.add(limit--)
    }

    var result = 0L

    var time = measureTimeMillis {
        result = computecr(list.toIntArray(), 0, list.size)
    }

    result = 0L

    time = measureTimeMillis {
        result = computecr(list.toIntArray(), 0, list.size)
    }

    print("$result in $time ms")

}