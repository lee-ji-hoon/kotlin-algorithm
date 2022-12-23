import kotlin.math.sqrt

fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()

    val isPrime = BooleanArray(N + 1) { true }
    isPrime[0] = false
    isPrime[1] = false
    val sqrtOfNum = sqrt(N.toDouble()).toInt()

    // 소수 구하기
    for (i in 2..sqrtOfNum) {
        findPrimeNum(i, N, isPrime)
    }

    val primeNum = arrayListOf<Int>()
    for (i in 1..N) {
        if (isPrime[i]) primeNum.add(i)
    }

    var start = 0
    var end = 0
    var count = 0
    var sum = 0

    // 2. 연속합으로 주어진 정수 구별
    while (true) {
        if (sum >= N) {
            sum -= primeNum[start++]
        } else if (end == primeNum.size) {
            break
        } else {
            sum += primeNum[end++]
        }
        if (N == sum) count++
    }
    println(count)
}

// 2의 배수 3의 배수 sqrt의 배수를 걸러준다.
private fun findPrimeNum(i: Int, N: Int, isPrime: BooleanArray) {
    var j = 2
    while (i * j <= N) {
        if (isPrime[i * j]) isPrime[i * j] = false
        j++
    }
}
