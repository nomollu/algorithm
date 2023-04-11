public class Main {
	
	static int R,C,K;
	static God [][] map;
	static boolean visit [][];
	static int d[][] = {{0,1},{1,0},{0,-1},{-1,0},{1,1},{1,-1},{-1,1},{-1,-1}};
	static God strongest, weakest;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new God[R][C];
		
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) map[i][j] = new God(i, j, Integer.parseInt(st.nextToken()), 0, false);
		}
	
		for(int i=1; i<=K; i++) {
			setWeakest(i);
			setStrongest();
			attack();
			if(isGodAlone()) break;
			store();
		}
		
		printStrongest();
	}
	
	static void setWeakest(int k) {
		weakest = new God(0, 0, Integer.MAX_VALUE, 0, true);
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				God g = map[i][j];
				
				if(g.life <= 0) continue;
				
				if(g.life < weakest.life) weakest = new God(g.r, g.c, g.life, g.last, true);
				else if(g.life == weakest.life){
					if(g.last > weakest.last) weakest = new God(g.r, g.c, g.life, g.last, true);
					else if(g.last == weakest.last){
						if(g.r + g.c > weakest.r + weakest.c) weakest = new God(g.r, g.c, g.life, g.last, true);
						else if(g.r + g.c == weakest.r + weakest.c){
							if(g.c > weakest.c) weakest = new God(g.r, g.c, g.life, g.last, true);
						}
					}
				}
			}//end for j
		} // end for i
	
		weakest.life += R + C;
		weakest.last=k;
		map[weakest.r][weakest.c] = new God(weakest.r, weakest.c, weakest.life, weakest.last, weakest.isActioned);
	}
	
	static void setStrongest() {
		strongest = new God(0, 0, 0, Integer.MAX_VALUE, true);
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j].life <= 0 || (weakest.r == i && weakest.c == j)) continue;
				
				God g = map[i][j];
				
				if(g.life > strongest.life) strongest = new God(g.r, g.c, g.life, g.last, true);
				else if(g.life == strongest.life){
					if(g.last < strongest.last) strongest = new God(g.r, g.c, g.life, g.last, true);
					else if(g.last == strongest.last) {
						if(g.r + g.c < strongest.r + strongest.c) strongest = new God(g.r, g.c, g.life, g.last, true);
						else if(g.r + g.c == strongest.r + strongest.c){
							if(g.c < strongest.c) strongest = new God(g.r, g.c, g.life, g.last, true);
						}
					}
				}
			} //end for j
		} // end for i
		
		map[strongest.r][strongest.c] = new God(strongest.r, strongest.c, strongest.life, strongest.last, strongest.isActioned);
	}
	
	static void attack() {
		Route attackRoute = getAttackRoute();
		
		if(attackRoute != null) laser(attackRoute);
		else bomb();
	}
	
	static Route getAttackRoute() {
		Route attackRoute = null;
		visit = new boolean[R][C];
		Queue<Route> q = new LinkedList<>();
		q.add(new Route(weakest.r, weakest.c, ""));
		visit[weakest.r][weakest.c] = true;
		
		while(!q.isEmpty()) {
			Route r = q.poll();
			
			if(r.r == strongest.r && r.c == strongest.c) return r;
			
			for(int i=0; i<4; i++) {
				int nr = r.r + d[i][0];
				int nc = r.c + d[i][1];
				
				if(nr == R) nr = 0;
				if(nr == -1) nr = R-1;
				if(nc == C) nc = 0;
				if(nc == -1) nc = C-1;
				
				if(visit[nr][nc] || map[nr][nc].life <= 0) continue;
				
				visit[nr][nc] = true;
				q.add(new Route(nr, nc, r.route + nr + " " + nc + " "));
			}
		}
		
		return attackRoute;
	}
	
	static void laser(Route attackRoute) {
		int power = weakest.life;
		StringTokenizer st = new StringTokenizer(attackRoute.route);
		
		while(st.hasMoreTokens()) {
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			if(!st.hasMoreTokens()) { //최종 목적지인 경우
				map[r][c].life -= power;
			}else map[r][c].life -= power/2; //목적지까지 가는 경로라면
			
			map[r][c].isActioned = true;
		}
	}
	
	static void bomb() {
		int power = weakest.life;
		
		for(int i=0; i<8; i++) {
			int nr = strongest.r + d[i][0];
			int nc = strongest.c + d[i][1];
			
			if(nr == R) nr = 0;
			if(nr == -1) nr = R-1;
			if(nc == C) nc = 0;
			if(nc == -1) nc = C-1;
			
			//벽이거나 나 자신이면 패스
			if(map[nr][nc].life <= 0 || (nr == weakest.r && nc == weakest.c)) continue;
			
			map[nr][nc].life -= power/2;
			map[nr][nc].isActioned = true;
		}
		
		map[strongest.r][strongest.c].life -= power; 
	}
	
	static void store() {
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j].life <= 0) continue;
				if(!map[i][j].isActioned) map[i][j].life++;
				map[i][j].isActioned = false;
			}
		}
	}
	
	static boolean isGodAlone() {
		int cnt = 0;
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j].life > 0) cnt++;
			}
		}
		
		return cnt <= 1;
	}
	
	static void printStrongest() {
		int strongestLife = 0;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j].life <= 0) continue;
				strongestLife = Math.max(strongestLife, map[i][j].life);
			}
		}
		System.out.println(strongestLife);
	}
	
	static void print() {
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) System.out.print(map[i][j].life + " ");
			System.out.println();
		}System.out.println();
	}
	
	static class Route{
		int r, c;
		String route;
		public Route(int r, int c, String route) {
			this.r = r;
			this.c = c;
			this.route = route;
		}
	}

	static class God {
		int r, c, life, last;
		boolean isActioned;
		public God(int r, int c, int life, int last, boolean isActioned) {
			this.r = r;
			this.c = c;
			this.life = life;
			this.last = last;
			this.isActioned = isActioned;
		}
	}
}
