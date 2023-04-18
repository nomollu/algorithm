package 백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj15654_N과M5 {
    static int N,M;
    static int[] input;
    static List<String> ans;
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        input = new int[N];
        ans = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            input[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(input);
        bt(0, new int[M], new boolean[N]);

        // 출력
        StringBuilder sb = new StringBuilder();
        int len = ans.size();
        for(int i = 0 ; i < len ; i++){
            sb.append(ans.get(i));
            if(i != len-1) sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void bt(int cnt, int[] selected, boolean[] visit){
        if(cnt == M){
            StringBuilder sb = new StringBuilder();
            for(int i = 0 ; i < selected.length ; i++){
                sb.append(selected[i] + " ");
            }
            ans.add(sb.toString());
            return;
        }

        for(int i = 0 ; i < N ; i++){
            if(visit[i]) continue;

            visit[i] = true;
            selected[cnt] = input[i];
            bt(cnt+1, selected, visit);
            visit[i] = false;
        }
    }
}
