import kotlin.system.measureTimeMillis

val SEQUENTIAL_THRESHOLD = 5000

fun compute(array: IntArray, low: Int, high: Int): Long {
    return if (high - low <= SEQUENTIAL_THRESHOLD) {
        (low until high)
                .map { array[it].toLong() }
                .sum()
    } else {
        val mid = low + (high - low) / 2
        val left = compute(array, low, mid)
        val right = compute(array, mid, high)
        return left + right
    }
}

fun main(args: Array<String>) {
    println("Sequential Sum  Demo")
    val list = mutableListOf<Int>()

    var limit = 20_000_000

    while (limit > 0) {
        list.add(limit--)
    }

    var result = 0L

    var time = measureTimeMillis {
        result = compute(list.toIntArray(), 0, list.size)
    }

     result = 0L

     time = measureTimeMillis {
        result = compute(list.toIntArray(), 0, list.size)
    }

    print("$result in $time ms")

}