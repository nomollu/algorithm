package 미분류;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj1058_친구 {

    static int ans = 0, maxAns = 0;
    public static void main(String[] args) throws IOException {
        // 입력, 변수 설정
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int ans = 0, maxAns = 0;
        int N = Integer.parseInt(br.readLine());
        char[][] adjMatrix = new char[N][N];
        for(int i = 0 ; i < N ; i++){
            adjMatrix[i] = br.readLine().toCharArray();
        }

        // i : 현재 사람, j : 1친구 후보, k : 2친구 후보
        for(int i = 0 ; i < N ; i++){
            boolean[] visited = new boolean[N]; // 방문 : 친구임이 이미 확인됨
            visited[i] = true;
            ans = 0;
            for(int j = 0 ; j < N ; j++){
                // 1친구를 찾는다.
                if(!visited[j] && adjMatrix[i][j] == 'Y'){
                    ans++;
                    visited[j] = true;
                    // 2친구를 찾는다.
                    for(int k = 0 ; k < N ; k++){
                        if(k != i && !visited[k] && adjMatrix[j][k] == 'Y') {
                            ans++;
                            visited[k] = true;
                        }
                    }
                }
            }
            maxAns = Math.max(maxAns, ans);
        }
        System.out.println(maxAns);
    }
}
