
/**
 * 4를 예시로 들면 1+2+1, 2+1+1, 1+1+2 이런 식으로 순서만 달라도 다른 경우의 수로 생각
 * 합이 입력한 값이 될 때까지 1,2,3 각각 더하고 만약 누적된 값이 입력값 보다 높으면 answer 누적 안하고 return 해서 스택 pop하기
 */

private var answer = 0
private var temp = 0

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val arr = IntArray(n)
    for (i in 0 until n) {
        temp = readLine().toInt()
        solution(0)
        arr[i] = answer
        answer = 0
    }
    for (i in arr) println(i)
}

private fun solution(sum: Int) {
    if (sum > temp) {
        return
    }
    if (sum == temp) {
        answer++
    } else {
        solution(sum + 1)
        solution(sum + 2)
        solution(sum + 3)
    }
}
