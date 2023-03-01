package 재귀;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj2630_색종이만들기 {
    static int N; // 종이 한 변의 길이
    static int ans[] = new int[2];
    static int[][] map; // 색종이
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for(int i = 0 ; i < N ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cutPaper(0, 0, N);
        System.out.println(ans[0]);
        System.out.println(ans[1]);
    }

    public static void cutPaper(int startX, int startY, int len){
        // 색이 전체 동일한지 검사한다.
        int originColor = map[startX][startY];
        boolean isSame = true;
        for(int i = startX ; i < startX + len ; i++){
            for(int j = startY ; j < startY + len ; j++){
                if(map[i][j] != originColor){
                    isSame = false;
                    break;
                }
            }
            if(!isSame) break;
        }

        // 동일하면, 해당값++
        if(isSame) {
            ans[originColor]++;
            return;
        }

        // 동일하지 않으면, 4개로 나눈다.
        int newLen = len / 2;
        cutPaper(startX, startY, newLen);
        cutPaper(startX + newLen, startY, newLen);
        cutPaper(startX, startY + newLen, newLen);
        cutPaper(startX + newLen, startY + newLen, newLen);
    }
}
