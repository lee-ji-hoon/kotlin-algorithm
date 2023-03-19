import java.io.StreamTokenizer
import java.util.*
import kotlin.collections.ArrayList

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input() // 정점의 개수
    val m = input() // 간선의 개수

    val adjList = Array(n + 1) { arrayListOf<Int>() } // 인접 리스트
    val visited = BooleanArray(n + 1) // 방문 여부

    // 인접 리스트 초기화
    repeat(m) {
        val u = input()
        val v = input()
        adjList[u].add(v)
        adjList[v].add(u)
    }

    var cnt = bfs(visited, adjList) // 초대할 친구 수

    println(cnt)
}

private fun bfs(
    visited: BooleanArray,
    adjList: Array<ArrayList<Int>>
): Int {
    val queue = LinkedList<Int>()
    queue.add(1) // 1번 노드부터 시작
    visited[1] = true // 1번 노드를 방문 표시

    var cnt = 0 // 초대할 친구 수
    var level = 0 // 친구와의 거리

    while (queue.isNotEmpty()) {
        val size = queue.size // 현재 레벨에 있는 노드 개수
        for (i in 0 until size) {
            val cur = queue.poll() // 큐에서 노드 하나를 꺼냄

            for (next in adjList[cur]) {
                if (visited[next].not()) { // 방문한 적 없는 노드일 때
                    visited[next] = true // 방문 표시
                    queue.add(next) // 큐에 추가
                    cnt++ // 초대할 친구 수 증가
                }
            }
        }
        level++ // 레벨 증가
        if (level == 2) break // 레벨이 2인 노드까지만 탐색
    }
    return cnt
}

