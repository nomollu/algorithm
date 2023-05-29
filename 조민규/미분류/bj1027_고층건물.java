package 미분류;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj1027_고층건물 {
    static int N;
    static int[] tower;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        tower = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            tower[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;
        // i : 현재 빌딩
        for(int i = 0 ; i < N ; i++){

            int cnt = 0;
            boolean notSee = false;
            // j : 현재 빌딩에서 보고자 하는 빌딩
            for(int j = 0 ; j < N ; j++){
                if(i == j) continue;

                // perHeight을 구한다.
                double perHeight = (double)Math.abs(tower[i] - tower[j]) / Math.abs(i-j);

                if(i < j){
                    for(int k = i+1 ; k < j ; k++){
                        if(notSee) break;

                    }
                } else {
                    for(int k = i-1 ; k > j ; k++){
                        if(notSee) break;

                    }
                }

                if(!notSee) cnt++;
            }

            ans = Math.max(ans, cnt);
        }

        System.out.println(ans);
    }

    public static double getDegree(int row, int col){
        double byun = Math.sqrt(Math.pow((double)row, 2) + Math.pow((double)col, 2));

        return Math.toDegrees(Math.acos((double)row / byun));
    }
    //                double degree = getDegree(Math.abs(i - j), Math.abs(tower[i] - tower[j]));
}
