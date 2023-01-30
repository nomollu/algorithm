package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class S2468_safeZone {
	static int N;
	static int[][] map;
	static boolean[][] visit;
	static int di[] = {-1, 0, 1, 0};
	static int dj[] = {0, 1, 0, -1};
	
	static class Point{
		int i, j;
		Point(int i, int j){
			this.i = i;
			this.j = j;
		}
	}
	
	static void bfs(int i, int j, int h) {
		Queue<Point> queue = new LinkedList<Point>();
		queue.add(new Point(i, j));
		visit[i][j] = true;
		
		while(!queue.isEmpty()) {
			int size = queue.size();
			
			for(int s=0; s<size; s++) {
				Point p = queue.poll();
				
				for(int d=0; d<4; d++) {
					int ni = p.i + di[d];
					int nj = p.j + dj[d];
					
					if(ni<0 || ni>=N || nj<0 || nj>=N || map[ni][nj]<=h || visit[ni][nj]) continue;
					queue.add(new Point(ni, nj));
					visit[ni][nj] = true;
				}
			}
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		Queue<Point> queue = new LinkedList<>();
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				queue.add(new Point(i, j));
			}
		}
		
		int result = 1;
		for(int h=1; h<=100; h++) {
			visit = new boolean[N][N];
			int size = queue.size();
			int cnt = 0;
			
			for(int j=0; j<size; j++) {
				Point now = queue.poll(); 
			
				if(map[now.i][now.j]>h) {
					queue.add(now); //현재 높이보다 높으니 다음 높이에서 탐색 대상이 됨
					
					if(!visit[now.i][now.j]) { // 이번 높이에서 방문한 적 없으면 너비탐색
						bfs(now.i, now.j, h);
						cnt++;
					}
				}
			}
			result = Math.max(result, cnt);
			if(queue.isEmpty()) break;
		}
		
		System.out.println(result);
	}

}
