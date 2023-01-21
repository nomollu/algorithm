package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class bj5212_지구온난화 {

    public static void main(String[] args) throws IOException {

        int[] dx = {0,0,-1,1};
        int[] dy = {-1,1,0,0};
        int cnt = 0; // 몇 개의 면이 바다인지
        int minR = 0, maxR = 0, minC = 0, maxC = 0; // 상, 하, 좌, 우 얼만큼 들여쓴 채로 출력할 것인지
        boolean[] flag = new boolean[4]; // 상하좌우 들여쓰기 탈출 변수

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        char[][] map = new char[R][C];
        List<int[]> landPos = new ArrayList<>();

        for(int i = 0 ; i < R ; i++){
            String s = br.readLine();
            for(int j = 0 ; j < C ; j++){
                map[i][j] = s.charAt(j);
                if(map[i][j] == 'X') landPos.add(new int[]{i, j});
            }
        }

        // landPos의 모든 땅의 위치에서 3면 이상이 바다인지 탐색한다.
        for(int[] land : landPos){
            int x = land[0];
            int y = land[1];
            cnt = 0;

            for(int d = 0 ; d < 4 ; d++){
                if(isSea(map, R, C, x + dx[d], y + dy[d])) cnt++;
            }

            map[x][y] = cnt >= 3 ? 'O' : 'X'; // 3면 이상이 바다일 경우 O로 바꾼다.
        }

        // 가로 세로 구분없이, 한 줄이 통째로 바다면 출력하지 않고 생략해야 한다.
        // 상하좌우 4면을 어느만큼 들여써야 할지 값을 결정한다.
        while(!flag[0] || !flag[1] || !flag[2] || !flag[3]){

            // 좌
            for(int i = minR ; i < R - maxR ; i++){
                if(map[i][minC] == 'X'){
                    flag[0] = true;
                    break;
                }
            }
            if(!flag[0]) minC++;

            // 하
            for(int j = minC ; j < C - maxC ; j++){
                if(map[R - 1 - maxR][j] == 'X'){
                    flag[1] = true;
                    break;
                }
            }
            if(!flag[1]) maxR++;

            // 우
            for(int i = R - 1 - maxR ; i >= minR ; i--){
                if(map[i][C - 1 - maxC] == 'X'){
                    flag[2] = true;
                    break;
                }
            }
            if(!flag[2]) maxC++;

            // 상
            for(int j = C - 1 - maxC ; j >= minC ; j--){
                if(map[minR][j] == 'X'){
                    flag[3] = true;
                    break;
                }
            }
            if(!flag[3]) minR++;
        }

        printMap(map, R, C, minR, maxR, minC, maxC);
    }

    // 바다인지 판별하는 조건 함수
    // ** 백준 문제에는 "맵 바깥은 바다로 취급한다"가 빠져 있음 **
    public static boolean isSea(char[][] map, int R, int C, int x, int y){
        return (x < 0 || x >= R || y < 0 || y >= C) || map[x][y] == '.';
    }

    // 구한 변수의 값만큼 들여써서 맵을 출력한다.
    public static void printMap(char[][] arr, int row, int col, int minR, int maxR, int minC, int maxC){
        for(int i = minR ; i < row - maxR ; i++){
            for(int j = minC ; j < col - maxC ; j++){
                if(arr[i][j] == 'O') arr[i][j] = '.';
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }
}
