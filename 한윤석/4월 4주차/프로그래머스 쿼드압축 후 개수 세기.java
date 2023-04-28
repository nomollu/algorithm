package programmers;

public class P쿼드압축후개수세기 {
	
	static int N;
	static int [] answer = new int [2];
	static int [][] map;

	public static void main(String[] args) {
		int arr [][] = {{1,1,1,1,1,1,1,1},{0,1,1,1,1,1,1,1},{0,0,0,0,1,1,1,1},{0,1,0,0,1,1,1,1},{0,0,0,0,0,0,1,1},{0,0,0,0,0,0,0,1},{0,0,0,0,1,0,0,1},{0,0,0,0,1,1,1,1}};
		int ans [] = solution(arr);
		System.out.println(ans[0] + " " + ans[1]);
	}

	static int [] solution (int [][] arr) {
		map = arr;
		N = arr.length;
		
		zip(0, 0, N);
		return answer;
	}
	
	static void zip(int sr, int sc, int len) {
		if(len < 1) return;
		
		int oneNum = 0;
		int zeroNum = 0;
		
		for(int i=sr; i<sr+len; i++) {
			for(int j=sc; j<sc+len; j++) {
				if(map[i][j] == 1) oneNum++;
				else zeroNum++;
			}
		}
		
		if(oneNum == 0 || zeroNum == 0) {
			answer[0] += zeroNum == 0 ? 0 : 1;
			answer[1] += zeroNum == 0 ? 1 : 0;
		}else {
			zip(sr, sc, len/2);
			zip(sr, sc + len/2, len/2);
			zip(sr + len/2, sc, len/2);
			zip(sr + len/2, sc + len/2, len/2);
		}
	}
}
