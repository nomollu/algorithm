package 자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj2346_풍선터뜨리기 {
    static int N; // 풍선의 개수
    static int[] balloons; // 풍선에 써진 숫자들
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        balloons = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            balloons[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder ans = new StringBuilder();
        int curPosition = 0; // 현재 위치
        for(int i = 0 ; i < N ; i++){
            int tmp = balloons[curPosition];

            // 현재 위치의 풍선을 터트림
            balloons[curPosition] = 0; // 현재 풍선을 터트림
            ans.append((curPosition+1) + " "); // 정답 문자열에 입력함
            if(i == N-1) break;

            // 풍선에 써 있는 숫자만큼 이동
            for(int j = Math.abs(tmp); j > 0 ; j--){ // 풍선 이동 초깃값
                if(tmp > 0){ // 값이 양수일 경우
                    curPosition = (curPosition+1) % N; // +1만큼 위치 이동
                }else { // 값이 음수일 경우
                    curPosition = curPosition-1 < 0 ? N-1 : curPosition-1; // -1만큼 위치 이동
                }

                if(balloons[curPosition] == 0) ++j; // 이미 터진 풍선이면 한번 더 이동
            }
        }
        System.out.println(ans);
    }
}
