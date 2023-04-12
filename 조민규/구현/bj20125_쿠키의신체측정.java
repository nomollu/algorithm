package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj20125_쿠키의신체측정 {
    static int[] di = {-1,1,0,0};
    static int[] dj = {0,0,-1,1};
    static int N; // map의 크기
    static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N+1][N+1];
        int[] heart = new int[2]; // 심장의 위치
        int[] ans = new int[5]; // 왼쪽 팔, 오른쪽 팔, 허리, 왼쪽 다리, 오른쪽 다리
        for(int i = 1 ; i <= N ; i++){
            String str = br.readLine();
            for(int j = 1 ; j <= N ; j++){
                map[i][j] = str.charAt(j-1);
            }
        }

        // 심장 구하기
        for(int i = 1 ; i <= N ; i++){
            for(int j = 1 ; j <= N ; j++){
                if(map[i][j] == '*'){
                    int cnt = 0;
                    for(int d = 0 ; d < 4 ; d++){
                        int nx = i + di[d];
                        int ny = j + dj[d];
                        if(isValid(nx, ny) && map[nx][ny] == '*') cnt++;
                    }
                    if(cnt == 4){
                        heart[0] = i;
                        heart[1] = j;
                    }
                }
            }
        }

        // 왼팔
        int i = heart[0];
        int j = heart[1] - 1;
        while(isValid(i, j) && map[i][j] == '*'){
            ans[0]++;
            j--;
        }

        // 오른팔
        j = heart[1] + 1;
        while(isValid(i, j) && map[i][j] == '*'){
            ans[1]++;
            j++;
        }

        // 허리
        i = heart[0] + 1;
        j = heart[1];
        while(isValid(i, j) && map[i][j] == '*'){
            ans[2]++;
            i++;
        }

        int leftLegX = i;
        int leftLegY = j - 1;
        int rightLegX = i;
        int rightLegY = j + 1;

        // 왼쪽 다리
        while(isValid(leftLegX, leftLegY) && map[leftLegX][leftLegY] == '*'){
            ans[3]++;
            leftLegX++;
        }

        // 오른쪽 다리
        while(isValid(rightLegX, rightLegY) && map[rightLegX][rightLegY] == '*'){
            ans[4]++;
            rightLegX++;
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        sb.append(heart[0]).append(" ").append(heart[1]).append("\n");
        for(int z = 0 ; z < 5 ; z++){
            sb.append(ans[z]).append(" ");
        }
        System.out.println(sb.toString());
    }

    public static boolean isValid(int x, int y){
        return x > 0 && x <= N && y > 0 && y <= N;
    }
}
