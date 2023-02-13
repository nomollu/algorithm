package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S1058_friends {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		char[][] arr = new char[N][N];
		for(int i=0; i<N; i++) arr[i] = br.readLine().toCharArray();
		
		int max = 0;
		for(int i=0; i<N; i++) { // 한명씩 탐색 시작
			int cnt = 0;
			for(int j=0; j<N; j++) { // 상대방이 2-친구인지 확인
				if(i==j || arr[i][j]=='N') continue;
				else { // 2친구이거나 그냥친구인 경우
					cnt++;
					if(arr[i][j]=='Y') { // 연결된 2친구가 없는지 확인
						for(int k=0; k<N; k++) {
							if(k==i || k==j) continue;
							else if(arr[j][k]=='Y' && arr[i][k]=='N') {
								if(k<j) cnt++; // k가 j보다 작은경우는 위에 j반복에서 이미 놓쳤으니 더해줌
								arr[i][k] = '2';
								arr[k][i] = '2';
							}
						}
					}
				}
			}
			max = Math.max(max, cnt);
		}
		
		System.out.println(max);
	}

}
