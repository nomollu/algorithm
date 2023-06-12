import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_Gold5_13164_행복유치원 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken()); //K개의 조
        int[] students = new int[N]; //원생들
        Difference[] differences = new Difference[N-1]; //인접한 원생들 차이 저장할 배열
        int ans = 0;

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            students[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<N-1; i++){
            differences[i] = new Difference(i,students[i+1] - students[i]);
        }

        Arrays.sort(differences);

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int i=0; i<K-1; i++){
            queue.add(differences[i].index); //조 나누기 위해 차이는 크고, 인덱스 작은 순으로 큐에 넣기
        }

        int startIndex = 0;
        while(!queue.isEmpty()){
            int endIndex = queue.poll();
            ans += students[endIndex] - students[startIndex];
            startIndex = endIndex+1;
        }
        ans += students[students.length-1] - students[startIndex]; //마지막 조도 포함해줘야함
        System.out.println(ans);

    }

    static class Difference implements Comparable<Difference>{
        int index, diff;

        public Difference(int index, int diff) {
            this.index = index;
            this.diff = diff;
        }

        @Override
        public int compareTo(Difference o) {
            if(this.diff == o.diff){ //차이가 같으면 인덱스 높은거 순으로
                return o.index - this.index;
            }
            return o.diff - this.diff;
        }
    }
}
