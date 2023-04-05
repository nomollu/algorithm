package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G25307_sirusDepartmentstoreTour {
	static int N, M, K;
	static int[][] map;
	static int[] di = {-1, 0, 1, 0};
	static int[] dj = {0, 1, 0, -1};
	
	static class Point{
		int i, j;
		Point(int i, int j){
			this.i = i;
			this.j = j;
		}
	}
	
	// replaceMannequin : 마네킹과의 거리가 K이하긴 구간들의 값을 3으로 변환
	static void replaceMannequin(Queue<Point> queue) {
		int cnt=0;
		while(!queue.isEmpty() && ++cnt<=K) {
			int size = queue.size();
		
			for(int s=0; s<size; s++) {
				Point now = queue.poll();
			
				for(int d=0; d<4; d++) {
					int ni = now.i + di[d];
					int nj = now.j + dj[d];
					
					if(ni<0 || ni>=N || nj<0 || nj>=M || map[ni][nj]==3) continue;
					map[ni][nj] = 3;
					queue.add(new Point(ni, nj));
				}
			}
		} 
	}
	
	// findChair : 시루의 위치에서부터 가장 최단거리의 의자 찾기
	static int findChair(Point siru) {
		Queue<Point> queue = new LinkedList<Point>();
		queue.add(siru);
		
		int depth=1; // 시루가 움직인 횟수
		while(!queue.isEmpty()) {
			int size = queue.size();
			
			for(int s=0; s<size; s++) {
				Point now = queue.poll();
			
				for(int d=0; d<4; d++) {
					int ni = now.i + di[d];
					int nj = now.j + dj[d];
				
					if(ni<0 || ni>=N || nj<0 || nj>=M || 
							map[ni][nj]==1 || map[ni][nj]==3 || map[ni][nj]==4) continue;
					
					if(map[ni][nj]==2) return depth; // 의자 발견
					map[ni][nj] = 4; // 시루가 방문한 구간은 4로 visit처리
					queue.add(new Point(ni, nj));
				}
			}
			depth++;
		}
		return -1;
	}
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		Queue<Point> mannequin = new LinkedList<Point>();
		Point siru = null;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j]==3) mannequin.add(new Point(i, j));
				else if(map[i][j]==4) siru = new Point(i, j);
			}
		}
		
		replaceMannequin(mannequin);
		
		System.out.println(findChair(siru));
	}

}
