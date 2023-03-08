package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class bj14267_회사문화1 {
    static int N, M;
    static int[] goodCount;
    static List<Integer>[] worker;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 입력 1
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        goodCount = new int[N+1];
        worker = new ArrayList[N+1];
        for(int i = 1 ; i <= N ; i++){
            worker[i] = new ArrayList<>();
        }
        // 입력 2
        st = new StringTokenizer(br.readLine());
        Integer.parseInt(st.nextToken());
        for (int i = 2 ; i <= N ; i++){
            worker[Integer.parseInt(st.nextToken())].add(i);
        }
        // 입력 3~5
        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int who = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            goodCount[who] += weight;
        }

        dfs(1);

        // 출력
        for(int i = 1 ; i < goodCount.length ; i++){
            System.out.print(goodCount[i] + " ");
        }
        System.out.println();
    }

    // 내리 더해주기
    public static void dfs(int now){
        // 현재 내가 가지고 있는 직속부하들에게 내 칭찬점수를 더해준다.
        for(int w : worker[now]){
            goodCount[w] += goodCount[now];
            dfs(w);
        }
    }
}
