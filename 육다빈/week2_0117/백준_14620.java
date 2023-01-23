package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S14620_flowerRoad {
	static int N;
	static int[] di = {-1, 0, 1, 0};
	static int[] dj = {0, 1, 0, -1};
	static int[][] map;
	static int[][] cost;
	static int[][] visit;
	
	static int calcCost(int i, int j) { 
		int sum = map[i][j];
		for(int d=0; d<4; d++) sum += map[i+di[d]][j+dj[d]];
		return sum;
	}
	
	static boolean isOkay(int ni, int nj) {
		for(int i=0; i<2; i++) {
				if(Math.abs(ni-visit[i][0]) + Math.abs(nj-visit[i][1]) <= 2) return false;
		}
		return true;
	}
	
	static int comb(int n, int cnt, int sum) {
		System.out.print(n + " -> ");
		if(cnt==3) {
			System.out.println(" ////");
			return sum;
		}
		int max = 0;
		for(int k=n; k<N*N; k++) {
			int ni = k / N;
			int nj = k % N;
			if(ni==0 || ni==N-1 || nj==0 || nj==N-1 || isOkay(ni, nj)) continue;
			visit[cnt][0] = ni;
			visit[cnt][1] = nj;
			max = Math.max(max, comb(k+1, cnt+1, sum+cost[ni][nj]));
		}
		return max;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		cost = new int[N][N];
		visit = new int[3][2];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=1; i<N-1; i++) {
			for(int j=1; j<N-1; j++) cost[i][j] = calcCost(i, j);
			System.out.println(Arrays.toString(cost[i]));
		}
		
		
		System.out.println(comb(0, 0, 0));
	}

}
