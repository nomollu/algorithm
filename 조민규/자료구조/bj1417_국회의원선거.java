package 미분류;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class bj1417_국회의원선거 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int dasom = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2)->o2.compareTo(o1));

        for(int i = 0; i < N-1 ; i++){
            pq.add(Integer.parseInt(br.readLine()));
        }

        int cnt = 0;
        while(!pq.isEmpty() && dasom <= pq.peek()){
            pq.add(pq.poll()-1);
            dasom++;
            cnt++;
        }

        System.out.println(cnt);
    }
}
