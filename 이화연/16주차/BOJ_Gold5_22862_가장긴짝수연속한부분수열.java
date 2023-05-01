import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_Gold5_22862_가장긴짝수연속한부분수열 {
    static int N,K, arr[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;
        int start = 0; //시작 위치
        int now = 0; //현재 위치
        int count = 0; //홀수 개수
        while (now < N) {
            if (count < K) {
                if (arr[now] % 2 != 0) { //홀수면 개수 증가
                    count++;
                }
                now++; //다음 위치로
                ans = Math.max(now - start - count, ans);
            }else{ // 삭제 횟수 없을때
                if (arr[now] % 2 == 0) { //현재 짝수면
                    now++; //다음 위치로
                    ans = Math.max(now - start - count, ans);
                } else { //홀수면
                    if (arr[start] % 2 != 0) { //시작 위치가 홀수면 개수 감소
                        count--;
                    }
                    start++; //시작 위치 증가
                }
            }
        }

        System.out.println(ans);
    }
}
