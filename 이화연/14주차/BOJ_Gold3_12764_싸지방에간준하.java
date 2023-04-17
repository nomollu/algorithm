import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_Gold3_12764_싸지방에간준하 {
    static int N, use[], endTime[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        use = new int[N]; //각 자리 이용한 사람 수 저장할 배열
        endTime = new int[N]; //각 자리 종료시간 기록할 배열

        PriorityQueue<Computer> pq = new PriorityQueue<>();
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            pq.add(new Computer(start, end));
        }

        while(!pq.isEmpty()){
            Computer now = pq.poll();

            for(int i=0; i<N; i++){
                if(endTime[i] == 0){ //현재 인덱스 종료시간이 0이면 아직 자리가 비어있으므로 종료시간 갱신하고 다음 큐 꺼내기
                    endTime[i] = now.end;
                    use[i]++;
                    break;
                }else{
                    if(endTime[i] < now.start){ //현재 종료시간보다 시작시간이 크면 지금 인덱스 종료시간 갱신
                        endTime[i] = now.end;
                        use[i]++;
                        break;
                    }
                }
            }
        }

        int count = 0;
        for(int i=0; i<N; i++){
            if(use[i] != 0){ //0이면 그 자리 사용안한것
                count++;
            }
        }
        System.out.println(count);
        for(int i=0; i<N; i++){
            if(use[i] != 0){
                System.out.print(use[i]+" ");
            }
        }
    }

    static class Computer implements Comparable<Computer>{
        int start, end;

        public Computer(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Computer o) {
            if(this.start == o.start)
                return this.end - o.end;
            return this.start - o.start;
        }
    }
}
