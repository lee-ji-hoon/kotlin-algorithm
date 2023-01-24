import java.io.StreamTokenizer

class Point(var x: Int, var y: Int)

private var answer = Int.MAX_VALUE

/**
 * 집과 치킨집의 좌표를 각각 list에 담아둔다.
 * 치킨집이 open한 개수가 m과 같다면 m개의 치킨집 중 최단 거리 계산
 * 탐색을 시작하는 지점이 치킨집 list의 사이즈가 벗어나게 되면 탐색을 종료
 */

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val m = input()

    val map = Array(n) { IntArray(n) }
    val person = arrayListOf<Point>()
    val chicken = arrayListOf<Point>()

    for (i in 0 until n) {
        for (j in 0 until n) {
            val data = input()
            map[i][j] = data
            if (data == 1) {
                person.add(Point(i, j))
            } else if (data == 2) {
                chicken.add(Point(i, j))
            }
        }
    }
    val isOpened = BooleanArray(chicken.size)
    findChicken(0, 0, isOpened, map, n, m, person, chicken)
    println(answer)
}

fun findChicken(
    start: Int,
    count: Int,
    opened: BooleanArray,
    map: Array<IntArray>,
    n: Int,
    m: Int,
    person: ArrayList<Point>,
    chicken: ArrayList<Point>
) {
    if (count == m) {
        var res = 0
        for (i in 0 until person.size) {
            var temp = Int.MAX_VALUE

            // 집과 치킨집 중 열려있는 치킨집의 모든 거리를 비교해서 최소값 갱신
            for (j in 0 until chicken.size) {
                if (opened[j]) {
                    val distance = (Math.abs(person[i].x - chicken[j].x) + Math.abs(person[i].y - chicken[j].y))
                    temp = Math.min(temp, distance)
                }
            }
            res += temp
        }
        answer = Math.min(answer, res)
        return
    }

    for (i in start until chicken.size) {
        opened[i] = true
        findChicken(i + 1, count + 1, opened, map, n, m, person, chicken)
        opened[i] = false
    }
}
