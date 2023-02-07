public class B17276_배열돌리기 {
	
	static int map [][];
	static int n;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			map = new int [n][n];
			
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<n; j++) map[i][j] = Integer.parseInt(st.nextToken());
			}
			
			rotate(d);
			print();
		}
	}
	
	static void rotate(int d) {
		int time = (Math.abs(d) / 45) % 8; // 45도 방향으로 몇 번 돌릴지
		int step = n / 2; // 돌아갈 칸이 몇 칸 단위인지
		
		for(int i=0; i<time; i++) { //45도를 i번 돌리겠다
			for(int j=0; j<step; j++) { // depth 0부터 step까지 돌리겠다
				if(d > 0)rotate45(j, step);
				else rotateOpposite45(j, step);
			}
		}
		
		
	}
	
	static void rotate45(int depth, int step) {
		int temp = map[depth][depth];
		
		map[depth][depth] = map[step][depth];
		map[step][depth] = map[n-1-depth][depth];
		map[n-1-depth][depth] = map[n-1-depth][step];
		map[n-1-depth][step] = map[n-1-depth][n-1-depth];
		map[n-1-depth][n-1-depth] = map[step][n-1-depth];
		map[step][n-1-depth] = map[depth][n-1-depth];
		map[depth][n-1-depth] = map[depth][step];
		map[depth][step] = temp;
	}
	
	static void rotateOpposite45(int depth, int step) {
		int temp = map[depth][depth];
		
		map[depth][depth] = map[depth][step];
		map[depth][step] = map[depth][n-1-depth];
		map[depth][n-1-depth] = map[step][n-1-depth];
		map[step][n-1-depth] = map[n-1-depth][n-1-depth];
		map[n-1-depth][n-1-depth] = map[n-1-depth][step];
		map[n-1-depth][step] = map[n-1-depth][depth];
		map[n-1-depth][depth] = map[step][depth];
		map[step][depth] = temp;
	}
	
	static void print() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) System.out.print(map[i][j] + " ");
			System.out.println();
		}
	}
}
