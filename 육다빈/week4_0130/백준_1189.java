package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1189_comebackHome {
	static int R, C, K, result=0;
	static char[][] map;
	static boolean[][] visit;
	static int[] di = {-1, 0, 1, 0};
	static int[] dj = {0, 1, 0, -1};
	
	static void dfs(int i, int j, int cnt) {
		if(i==0 && j==C-1) {
			if(cnt==K) result++;
			return;
		}else if(cnt==K) {
			return;
		}
		for(int d=0; d<4; d++) {
			int ni = i+di[d];
			int nj = j+dj[d];
			if(ni>=0 && ni<R && nj>=0 && nj<C && map[ni][nj]!='T' && !visit[ni][nj]) {
				visit[ni][nj] = true;
				dfs(ni, nj, cnt+1);
				visit[ni][nj] = false;
			}
			
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visit = new boolean[R][C];
		
		for(int i=0; i<R; i++) map[i] = br.readLine().toCharArray();
		
		visit[R-1][0] = true;
		dfs(R-1, 0, 1);
		System.out.println(result);
		
	}

}
