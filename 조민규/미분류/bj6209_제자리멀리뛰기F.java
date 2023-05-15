package 미분류;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj6209_제자리멀리뛰기 {
    static int D,N,M; // 돌섬-탈출구 거리, 돌섬 수, 제거할 수 있는 돌섬 수
    static int[] islands;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        D = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        islands = new int[N];
        for(int i = 0 ; i < N ; i++){
            islands[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(islands);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0 ; i < N+1 ; i++){
            if(i == 0){
                pq.add(islands[0]);
            } else if (i == N){
                pq.add(D - islands[N-1]);
            } else {
                pq.add(islands[i] - islands[i-1]);
            }
        }

        int last = 0;
        for(int i = 0 ; i <= M ; i++) {
            last = pq.poll();
        }
        while(!pq.isEmpty()){
            int now = pq.poll();
            if(now > last) break;
        }


        System.out.println(pq.poll());
    }
}
