fun main() = with(System.`in`.bufferedReader()) {
    val (N, K) = readLine().split(" ").map { it.toInt() }
    val array = IntArray(N)
    for (i in 0 until N) {
        array[i] = readLine().toInt()
    }

    solution(K, array)
}

private fun solution(sum: Int, arr: IntArray) {
    var result = 0
    var temp = sum
    for (index in arr.size - 1 downTo 0) {
        result += temp / arr[index]
        temp %= arr[index]
    }
    println(result)
}

