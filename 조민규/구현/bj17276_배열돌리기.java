package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj17276_배열돌리기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int T;
    static int N, D; // 배열의 크기, 각도
    static int[][][] arr; // 배열1,2
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for(int t = 1 ; t <= T ; t++){
            test();
        }
    }

    public static void test() throws IOException {
        // 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        arr = new int[2][N][N];
        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j++){
                arr[0][i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        // 배열 돌리기
        // i : 깊이, j : 회전 횟수
        for(int i = 0 ; i < N/2 ; i++){
            for(int j = 0 ; j < Math.abs(D)/45 ; j++){
                spin(i, D >= 0 ? true : false);
            }
        }

        show(arr[(Math.abs(D)/45) % 2]);
    }

    public static void spin(int depth, boolean isPlus){
        // 상
        for(int i = depth ; i <= N/2 - depth ; i++){
            if(isPlus){
                arr[(Math.abs(D)/45) % 2][depth][i] = arr[1 - ((Math.abs(D)/45) % 2)][depth][i];
            } else {
                arr[(Math.abs(D)/45) % 2][i][depth] = arr[1 - ((Math.abs(D)/45) % 2)][depth][i];
            }
        }
        for(int i = N/2 - depth + 1 ; i < N ; i++){
            if(isPlus){
                arr[(Math.abs(D)/45) % 2][i - N/2][N-1-depth] = arr[1 - ((Math.abs(D)/45) % 2)][depth][i];
            } else {
                arr[(Math.abs(D)/45) % 2][depth][i - N/2] = arr[1 - ((Math.abs(D)/45) % 2)][depth][i];
            }
        }
        
        // 우
        for(int j = depth + 1 ; j <= N/2 - depth ; j++){
            if(isPlus){
                arr[(Math.abs(D)/45) % 2][j + N/2][N-1-depth] = arr[1 - ((Math.abs(D)/45) % 2)][j][N-1-depth];
            } else {
                //arr[(Math.abs(D)/45) % 2][1][1] = arr[1 - ((Math.abs(D)/45) % 2)][depth][];
            }
        }
        for(int j = N/2 - depth ; j < N ; j++){
            if(isPlus){

            } else {

            }
        }
        
        // 하
        
        // 좌
    }

    public static void show(int[][] arr){
        for(int i = 0 ; i < arr.length ; i++){
            for(int j = 0 ; j < arr[i].length ; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
