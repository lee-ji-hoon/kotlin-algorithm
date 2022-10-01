
import java.io.BufferedReader

import java.io.IOException


// 퀸은 가로 세로 대각선 거리에 영향을 안받고 움직인다.
/** 이런식은 불가능하다.
 * |Q| | |
 * | |Q| |
 * | | | |
 *
 * 이러한 형태만 성립이 된다.
 * |Q|x|x|x|
 * |x|x|Q|x|
 * |x|x|x|Q|
 * |x|Q|x|x|
 */

private var count = 0
fun main() = with(System.`in`.bufferedReader()) {
    // 1. 입력 받기
    val N: Int = readLine().toInt()
    val chessboard = IntArray(N)

    solution(0, N, chessboard)
    println(count)
}

private fun solution(depth: Int, N: Int, chessboard: IntArray) {
    // 2. N개까지 들어왔을경우 count 증가
    if (depth == N) {
        count++
        return
    }

    // 3. 놓을 수 있는 위치인지 확인
    for (i in 0 until N) {
        chessboard[depth] = i
        if (isPassible(depth, chessboard)) {
            solution(depth+1, N, chessboard)
        }
    }
}

fun isPassible(col: Int, chessboard: IntArray): Boolean {
    for (i in 0 until col) {
        // 4. 현재열의 행과 i열의 행이 같은지 확인
        if (chessboard[col] == chessboard[i]) {
            return false
        // 5. 대각선 인경우도 확인
        // 이때 대각선을 어떻게 구할까 하다가 열의 차이와 행의 차이가 같은 경우
        } else if (col - i == Math.abs(chessboard[col] - chessboard[i])){
            return false
        }
    }
    return true
}
