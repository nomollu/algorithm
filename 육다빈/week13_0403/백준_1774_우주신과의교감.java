package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class G1774_universeGod {
	static int N, M;
	static Point[] pointList;
	static boolean[] visit;
	static List<Integer>[] edges;
	static PriorityQueue<Node> queue;
	
	static class Point {
		int i, j;
		
		Point(int i, int j){
			this.i = i;
			this.j = j;
		}
	}
	
	static class Node implements Comparable<Node>{
		int point;
		double dis;
		
		Node(int point, double dis){
			this.point = point;
			this.dis = dis;
		}
		
		@Override
		public int compareTo(Node o) {
			Double d =  this.dis;
			return d.compareTo(o.dis);
		}
	}
	
	static void calcDis(int a) { // a번째 노드와 나머지 점들 간 거리계산
		
		for(int b=1; b<=N; b++) {
			if(visit[b]) continue;
			
			double x = Math.pow(pointList[a].i - pointList[b].i, 2); //x^2
			double y = Math.pow(pointList[a].j - pointList[b].j, 2); //y^2
			double dis = Math.sqrt(x+y);
			
			queue.add(new Node(b, dis));
		}
	}
	
	static int checkVisit(int point) { // 기존에 연결되어있던 간선들 방문체크 후, 방문체크 횟수 반환
		int result = 1; // 본인의 방문
		visit[point] = true;
		
		for(int idx : edges[point]) {
			if(visit[idx]) continue;
			visit[idx] = true;
			calcDis(idx);
			result++;
		}
		
		return result;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		pointList = new Point[N+1];
		edges = new List[N+1];
		queue = new PriorityQueue<Node>();
		
		for(int i=1; i<=N+M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			if(i<=N) {
				edges[i] = new ArrayList<Integer>();
				pointList[i] = new Point(x, y);
			}else {
				edges[x].add(y);
				edges[y].add(x);
			}
		}
		
		
		visit = new boolean[N+1];
		
		int cnt = checkVisit(1);
		double sum = 0;

		calcDis(1);
		
		while(cnt<N) {
			Node now = queue.poll();
			if(visit[now.point]) continue;
			
			sum += now.dis;
			cnt += checkVisit(now.point);
			
			calcDis(now.point);
		}
		
		System.out.printf("%.2f", sum);
	}

}
