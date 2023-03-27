package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G14500_tetromino {
	static int N, M;
	static int[][] map, visit;
	static int[] di = {-1, 0, 1, 0};
	static int[] dj = {0, 1, 0, -1};
	
	static int dfs(int i, int j, int cnt, int sum) {
		if(cnt==4) {
			return sum;
		}
		visit[i][j] = -1;
		int max = -1;
		for(int d=0; d<4; d++) {
			int ni = i + di[d];
			int nj = j + dj[d];
			if(ni<0 || ni>=N || nj<0 || nj>=M || visit[ni][nj]!=0) continue;
			max = Math.max(max, dfs(ni, nj, cnt+1, sum+map[ni][nj]));
		}
		visit[i][j] = 0;
		return max;
	}
	
	static int checkT(int i, int j) {
		int result=map[i][j], cnt=0;
		int min = Integer.MAX_VALUE;
		for(int d=0; d<4; d++) {
			int ni = i + di[d];
			int nj = j + dj[d];
			if(ni<0 || ni>=N || nj<0 || nj>=M) continue;
			cnt++;
			result += map[ni][nj];
			min = Math.min(min, map[ni][nj]);
		}
		if(cnt==4) return result-min;
		else if(cnt==3) return result;
		else return -1;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}

		// 1. 한줄로 연결되는 4종류의 테트로미노 검사
		int result = -1;
		visit = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				result = Math.max(result, dfs(i, j, 1, map[i][j]));
			}
		}
		
		// 2. T자형 테트로미노 검사
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				result = Math.max(result, checkT(i, j));
			}
		}
		
		System.out.println(result);
	}

}
