package DFS및BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj1039_교환 {
    static class Target{
        int num, cnt;

        public Target(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
    static int N, K, len, ans = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String inputN = st.nextToken();
        N = Integer.parseInt(inputN);
        K = Integer.parseInt(st.nextToken());
        len = inputN.length();
        ans = N;

        // (반례) 2자릿수이며, 0으로 끝나는 숫자는 스왑 시도조차 할 수 없다.
        if((N >= 10 && N <= 99) && N % 10 == 0){
            System.out.println(-1);
            return;
        }

        Queue<Target> queue = new LinkedList<>();
        boolean[][] visited = new boolean[1000001][K+1];
        queue.add(new Target(N, 0));
        visited[N][0] = true;

        while (!queue.isEmpty()){
            Target now = queue.poll();



            // K번 스왑했을 경우, 정답을 구해보자
            if(now.cnt == K){
                ans = Math.max(ans, now.num);
                continue;
            }

            for(int i = 0 ; i < len - 1 ; i++){
                for(int j = i+1 ; j < len ; j++){
                    int next = swap(i, j, now.num);

                    // j가 0이었을 경우, 이미 방문한 부분일 경우
                    if(next == -1 || visited[next][now.cnt + 1]) continue;

                    // 큐에 추가하고, 방문 처리
                    queue.add(new Target(next, now.cnt + 1));
                    visited[next][now.cnt + 1] = true;
                }
            }
        }


        System.out.println(ans == Integer.MIN_VALUE ? -1 : ans);
    }

    public static int swap(int i, int j, int num) {
        // (반례) i가 최고자릿수고, N[j] == 0일 경우는 하면 안된다.
        if (i == 0 && String.valueOf(num).charAt(j) == '0') {
            return -1;
        }

        StringBuilder sb = new StringBuilder(String.valueOf(num));
        String a = sb.substring(i,i+1);
        String b = sb.substring(j,j+1);
        sb.replace(i,i+1,b);
        sb.replace(j,j+1,a);

        return Integer.parseInt(sb.toString());
    }
}
