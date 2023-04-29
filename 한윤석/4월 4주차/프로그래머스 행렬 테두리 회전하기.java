public class P행렬테두리회전하기 {

	static int[] answer;
	static int arr[][];
	static int R,C;

	public int[] solution(int rows, int columns, int[][] queries) {
		arr = new int [rows+1][columns+1];
		R = rows;
		C = columns;
		answer = new int [queries.length];
		
		// 배열에 숫자 채우기
		int num = 1;
		for(int i=1; i<=R; i++) for(int j=1; j<=C; j++) arr[i][j] = num++;
		
		num = 0;
		for(int [] query : queries) rotate(query[0], query[1], query[2], query[3], num++);
		
    return answer;
  }
	
	static void rotate(int sr, int sc, int er, int ec, int idx) {
		int temp = arr[sr+1][sc];
		int min = Integer.MAX_VALUE; //이번 회전에서 최소값
		
		// ->
		for(int i=sc; i<=ec; i++) {
			int temp2 = arr[sr][i];
			arr[sr][i] = temp;
			temp = temp2;
			min = Math.min(min, temp2);
		}
		
		// ↓
		for(int i=sr+1; i<=er; i++) {
			int temp2 = arr[i][ec];
			arr[i][ec] = temp;
			temp = temp2;
			min = Math.min(min, temp2);
		}
		
		// <-
		for(int i=ec-1; i>=sc; i--) {
			int temp2 = arr[er][i];
			arr[er][i] = temp;
			temp = temp2;
			min = Math.min(min, temp2);
		}
		
		// ↑
		for(int i=er-1; i>=sr; i--) {
			int temp2 = arr[i][sc];
			arr[i][sc] = temp;
			temp = temp2;
			min = Math.min(min, temp2);
		}
		
		answer[idx] = min;
	}
}
