import kotlin.math.sqrt

// https://www.acmicpc.net/problem/1929
fun main() = with(System.`in`.bufferedReader()) {
    val sb = StringBuilder()
    val (N, M) = readLine().split(" ").map { it.toInt() }
    val isPrime = BooleanArray(M + 1) { true }
    val sqrtOfM = sqrt(M.toDouble()).toInt()
    for (i in 2..sqrtOfM) {
        findPrimeNume3(i, M, isPrime)
    }
    printPrimeNum(N, M, isPrime, sb)
}

// 2의 배수 3의 배수 sqrt의 배수를 걸러준다.
private fun findPrimeNume3(i: Int, M: Int, isPrime: BooleanArray) {
    var j = 2
    while (i * j <= M) {
        if (isPrime[i * j]) isPrime[i * j] = false
        j++
    }
}

private fun printPrimeNum(N: Int, M: Int, isPrime: BooleanArray, sb: StringBuilder) {
    for (i in N..M) {
        if (i == 0 || i == 1) continue
        if (isPrime[i]) sb.appendLine(i)
    }
    println(sb)
}

fun findPrimeNum2(num: Int): Boolean {
    if (num == 1) return false
    val sqrt = sqrt(num.toDouble()).toInt()
    for (i in 2..sqrt) {
        if (num % i == 0) {
            return false
        }
    }
    return true
}

// 소수는 1과 자신만을 약수로 갖는다.
fun findPrimeNum1(num: Int): Boolean {
    if (num == 1) return false
    if (num == 2) return true
    for (i in 2 until num) {
        if (num % i == 0) return false
    }
    return true
}
