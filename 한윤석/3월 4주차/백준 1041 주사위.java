public class B1041_주사위 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int dice [] = new int [6]; //주사위 정보
		int cases [][] = {{0,1,2},{0,1,3},{0,2,4},{0,3,4},{1,2,5},{1,3,5},{2,4,5},{3,4,5}};
		long ans = 0; //정답
		long minInfo [] = new long [4]; //[0] sum, [1] 제일 낮은 거, [2] 2째 낮은 수, [3] 제일 큰 수

		//주사위 정보 입력
		for(int i=0; i<6; i++) dice[i] = sc.nextInt();
		
		//주사위의 맞닿는 면들 검사해보며 3면 합이 제일 낮은 것 찾기
		for(int i=0; i<8; i++) {
			int sum = dice[cases[i][0]] + dice[cases[i][1]] + dice[cases[i][2]];
			int arr [] = {dice[cases[i][0]], dice[cases[i][1]], dice[cases[i][2]]};
			Arrays.sort(arr);
			
			if(i == 0 || minInfo[0] > sum) {
				minInfo[0] = sum;
				minInfo[1] = arr[0];
				minInfo[2] = arr[1];
				minInfo[3] = arr[2];
			}
		}
		
		int max = Math.max(dice[0], Math.max(dice[1], Math.max(dice[2], Math.max(dice[3], Math.max(dice[4], dice[5])))));
		
		if(N== 1) ans = dice[0] + dice[1] + dice[2] + dice[3] + dice[4] + dice[5] - max;
		else {
			//3면 보이는 블럭
			ans += minInfo[0] * 4;
			
			//2면 보이는 블럭
			ans += (minInfo[1] + minInfo[2]) * (N-2) * 8; //센터
			ans += (minInfo[1] + minInfo[2]) * 4; //맨아래 꼭지점
			
			//1면 보이는 블럭
			ans += minInfo[1] * (N-2) * (N-1) * 4; //옆면
			ans += minInfo[1] * (N-2) * (N-2); //맨위
		}
		
		System.out.println(ans);
	}

}
