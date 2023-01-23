import java.io.StreamTokenizer

/**
 * 1. 스티커를 회시키지 않고 종이에서 떼낸다.
 * 2. 다른 스티커와 겹치거나 노트북을 벗어나지 않으면 스티커를 붙일 수 있는 위치를 찾는다.
 *  - 노트북의 위쪽부터 스티커를 채워갈려고 한다.
 *  - 스티커를 붙일 수 있는 위치가 여러 곳 있다면 가장 위쪽의 위치를 선택한다.
 *  - 위쪽에 해당하는 위치도 여러 곳이 존재한다면, 그 중에선 가장 왼쪽을 선택한다.
 * 3. 스티커를 붙인다.
 *  - 붙일 수 있는 위치가 전혀 없다면 시계 방향으로 90도 회전한다.
 *  - 2번을 반복한다.
 * 4. 네번 반복해서 스티커를 0 90 180 270 반복 시켜도 붙이지 못했다면 버린다.
 */

/**
 * 가장 많이 붙일 수 있는 경우의 수가 아니다.
 * 처음 붙일 수 있는 경우를 구하는 문제이다.
 */

private lateinit var sticker: Array<IntArray>
private lateinit var map: Array<IntArray>
private var answer = 0

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val m = input()
    val k = input()

    map = Array(n) { IntArray(m) }
    for (i in 0 until k) {
        val r = input()
        val c = input()
        sticker = Array(r) { IntArray(c) }

        for (j in 0 until r) {
            for (n in 0 until c) {
                sticker[j][n] = input()
            }
        }
        findLocation(n, m)
    }
    println(answer)
}

fun findLocation(n: Int, m: Int) {
    var r = sticker.size
    var c = sticker[0].size

    for (i in 0 until 4) {
        // 처음거가 아니라면 회전을 시켜야 한다.
        if (i != 0) {
            sticker = rotateSticker(r, c)
        }
        r = sticker.size
        c = sticker[0].size

        for (j in 0 until n) {
            for (k in 0 until m) {
                if (j + r > n || k + c > m) break

                // 붙일 수 있는지 확인
                if (isPasteSticker(j, k, r, c)) {
                    return
                }
            }
        }
    }
}

fun rotateSticker(r: Int, c: Int): Array<IntArray> {
    val newSticker = Array(c) { IntArray(r) }

    for (i in 0 until r) {
        for (j in 0 until c) {
            newSticker[j][r - i - 1] = sticker[i][j]
        }
    }

    return newSticker
}

fun isPasteSticker(x: Int, y: Int, r: Int, c: Int): Boolean {
    for (i in x until x + r) {
        for (j in y until y + c) {
            // 붙일 수 없으니 다음 rotate를 확인 하기 위해서 return false
            if (map[i][j] == 1 && sticker[i - x][j - y] == 1) {
                return false
            }
        }
    }

    for (i in x until x + r) {
        for (j in y until y + c) {
            if (sticker[i - x][j - y] == 1) {
                map[i][j] = 1
                answer++
            }
        }
    }

    return true
}
