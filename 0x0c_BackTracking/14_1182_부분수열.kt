private var answer = 0

// 원소가 n개인 집합에서 부분집합의 갯수는 2n승이다.
fun main() = with(System.`in`.bufferedReader()) {
    val (n, s) = readLine().split(" ").map { it.toInt() }
    val arr: List<Int> = readLine().split(" ").map { it.toInt() }

    solution(n, s, 0, 0, arr)
    if(s == 0) answer-- // -> 이 부분을 놓쳐서 90프로에서 계속 오류가 났었다..
    println(answer)
}

fun solution(n: Int, s: Int, depth: Int, sum: Int, arr: List<Int>) {
    // 1. depth 즉 맨끝까지 들어갔을때 sum 값이 내가 찾는 s 값인지 확인
    if (depth == n) {
        if (sum == s) {
            answer++
        }
        return
    }
    // 2. 다음 수를 더하는 경우와 안더하는 경우를 나눠서 해준다.
    solution(n, s, depth + 1, sum, arr)
    solution(n, s, depth + 1, sum + arr[depth], arr)
}

