package 자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj2075_N번째큰수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i = 0 ; i < N ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j++){
                pq.add(Long.parseLong(st.nextToken()));
            }
        }

        Long ans = 0L;
        for(int i = 0 ; i < N ; i++){
            if(i == N-1) ans = pq.poll();
            else pq.poll();
        }
        System.out.println(ans);
    }
}
