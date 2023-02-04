import java.io.StreamTokenizer
import java.util.*


fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val w = input()
    val L = input()
    val truck: Queue<Int> = LinkedList()
    for (i in 0 until n) {
        truck.offer(input())
    }
    var time = 0
    var weight = 0
    val q: Queue<Int> = LinkedList()

    //
    for (i in 0 until w) {
        q.add(0)
    }
    while (q.isNotEmpty()) {
        time++
        weight -= q.poll()
        if (truck.isNotEmpty()) {
            // 최대하중 보다 작아서 다리가 견딜 수 있는지 확인
            if (truck.peek() + weight <= L) {
                weight += truck.peek()
                q.offer(truck.poll())
            // 무게가 버틸 수 없다면 q에 아무것도 없음을 표시하기 위해 0을 넣어준다.
            } else {
                q.offer(0)
            }
        }
    }
    println(time)
}