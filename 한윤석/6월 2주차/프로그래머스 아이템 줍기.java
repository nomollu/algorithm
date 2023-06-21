public class P아이템줍기 {

	static int len; // 사각형 개수
	static boolean visit[][] = new boolean [51][51]; //좌표 방문 여부
	static int answer = Integer.MAX_VALUE;
	static int gRectangle [][]; // 사각형 정보
	static int gItemX, gItemY; //아이템 좌표
	static int d[][] = {{1,0},{-1,0},{0,1},{0,-1}};
	
	public static void main(String[] args) {
		int rec [][] = {{1,1,3,7},{2,2,7,4},{4,3,6,6}};
		System.out.println(solution(rec, 1,2,6,6));
	}

	static public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
		len = rectangle.length;
		gRectangle = rectangle;
		gItemX = itemX;
		gItemY = itemY;
        visit[characterX][characterY] = true;
        
        dfs(characterX, characterY, 0);
        return answer;
    }
	
	static void dfs(int x, int y, int dist) {
		// 아이템 좌표에 도달했으면
		if(x == gItemX && y == gItemY) {
			answer = Math.min(answer, dist);
			return;
		}
		visit[x][y] = true;
		
		outer:for(int i=0; i<4; i++) {
			int nx = x + d[i][0];
			int ny = y + d[i][1];
			boolean isAnyoneBoundary = false; // 어떤 도형의 외곽인 좌표 일부인지 여부
			
			if(nx <=0 || ny <= 0 || nx > 50 || ny > 50 || visit[nx][ny]) continue;

			for(int j=0; j<len; j++) {
				// 어떤 도형의 내부에 있는 좌표면 더 볼 필요 없음
				if(isInner(x, y, nx, ny, j)) continue outer;
				// 어떤 도형의 외곽선에 포함된 좌표라면 체크해줌
				if(isIncluded(x, y, nx, ny, j)) isAnyoneBoundary = true;
			}
			
			if(!isAnyoneBoundary) continue;
			
			dfs(nx, ny, dist+1);
		}
	}
	
	// [nx, ny]가 도형 i 내부에 포함되어있는지, 칸 건너진 ㅇ 여부
	static boolean isInner(int x, int y, int nx, int ny, int i) {
		int rec [] = gRectangle[i];

		if(nx > rec[0] && nx < rec[2] && ny > rec[1] && ny < rec[3]) return true;
		if(x == rec[0] && nx == rec[2] && ny > rec[1] && ny < rec[3]) return true;
		if(x == rec[2] && nx == rec[0] && ny > rec[1] && ny < rec[3]) return true;
		if(y == rec[1] && ny == rec[3] && nx > rec[0] && nx < rec[2]) return true;
		if(y == rec[3] && ny == rec[1] && nx > rec[0] && nx < rec[2]) return true;
		return false;
	}
	
	// [x,y]가 도형 i의 외곽선에 포함되어 있는지 여부
	static boolean isIncluded(int x, int y, int nx, int ny, int i) {
		int rec [] = gRectangle[i];
		
		// [x,y]와 [nx, ny]가 연결되어있는지 여부
		if(!(x >= rec[0] && x <= rec[2] && nx >= rec[0] && nx <= rec[2])) return false;
		if(!(y >= rec[1] && y <= rec[3] && ny >= rec[1] && ny <= rec[3])) return false;
		
		// 한 도형의 외곽선인지 여부
		if(nx == rec[0] && ny >= rec[1] && ny <= rec[3]) return true;
		if(nx == rec[2] && ny >= rec[1] && ny <= rec[3]) return true;
		if(ny == rec[1] && nx >= rec[0] && nx <= rec[2]) return true;
		if(ny == rec[3] && nx >= rec[0] && nx <= rec[2]) return true;
		return false;
	}
}
