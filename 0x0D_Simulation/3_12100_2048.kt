import java.io.StreamTokenizer

// https://jellyinghead.tistory.com/53
// 참고해서 풀이

/**
 * 총 5번의 이동으로 가장 큰 수를 구해야 한다.
 * 상하좌우 이동한뒤 백트래킹을 이용해서 구현!
 */

private var answer = 2
private lateinit var map: Array<IntArray>

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    map = Array(n) { IntArray(n) { input() } }

    startGame(0, n)
    println(answer)
}

fun startGame(count: Int, n: Int) {
    if (count == 5) {
        checkMax(n)
        return
    }
    val copyMap = Array(n) { IntArray(n) }
    for (i in 0 until n) {
        copyMap[i] = map[i].clone()
    }
    for (i in 0 until 4) {
        moveBlock(i, n)
        startGame(count + 1, n)
        for (j in 0 until n) {
            map[j] = copyMap[j].clone()
        }
    }
}

fun moveBlock(dir: Int, n: Int) {
    when (dir) {
        // 위로 몰기
        0 -> {
            for (i in 0 until n) {
                var index = 0
                var block = 0
                for (j in 0 until n) {
                    if (map[j][i] != 0) {
                        if (block == map[j][i]) {
                            map[index - 1][i] = block * 2
                            block = 0
                            map[j][i] = 0
                        } else {
                            block = map[j][i]
                            map[j][i] = 0
                            map[index][i] = block
                            index++
                        }
                    }
                }
            }
        }
        // 아래로 몰기
        1 -> {
            for (i in 0 until n) {
                var index = n - 1
                var block = 0
                for (j in n - 1 downTo 0) {
                    if (map[j][i] != 0) {
                        if (block == map[j][i]) {
                            map[index + 1][i] = block * 2
                            block = 0
                            map[j][i] = 0
                        } else {
                            block = map[j][i]
                            map[j][i] = 0
                            map[index][i] = block
                            index--
                        }
                    }
                }
            }
        }
        // 왼족으로 몰기
        2 -> {
            for (i in 0 until n) {
                var index = 0
                var block = 0
                for (j in 0 until n) {
                    if (map[i][j] != 0) {
                        if (block == map[i][j]) {
                            map[i][index - 1] = block * 2
                            block = 0
                            map[i][j] = 0
                        } else {
                            block = map[i][j]
                            map[i][j] = 0
                            map[i][index] = block
                            index++
                        }
                    }
                }
            }
        }
        // 오른쪽으로 몰기
        3 -> {
            for (i in 0 until n) {
                var index = n - 1
                var block = 0
                for (j in n - 1 downTo 0) {
                    if (map[i][j] != 0) {
                        if (block == map[i][j]) {
                            map[i][index + 1] = block * 2
                            block = 0
                            map[i][j] = 0
                        } else {
                            block = map[i][j]
                            map[i][j] = 0
                            map[i][index] = block
                            index--
                        }
                    }
                }
            }
        }
    }
}

fun checkMax(n: Int) {
    for (i in 0 until n) {
        for (j in 0 until n) {
            answer = Math.max(answer, map[i][j])
        }
    }
}
