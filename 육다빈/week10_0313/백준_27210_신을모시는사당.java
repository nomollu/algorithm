package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G27210_shrineToDeity {
	static int N;
	static int[] arr;
	
	// countMax : plus인 방향이 더 많이 포함된 경우 중 최댓값 계산
	static int countMax(int plus) { 
		int sum = 0; //return할 최댓값
		
		int tmp=0;
		for(int i=0; i<N; i++) {
			if(arr[i]==plus) { // plus방향이면 역방향이 나오기 전까지 계속 누적
				tmp += 1;
			}else { // 역방향이 나온 경우,
				sum = Math.max(sum, tmp); // 앞으로 계속 값이 차감될 것이니, 현재값과 기존 누적값 중 최댓값 저장
				while(i<N && arr[i]!=plus) { // 역방향이 끝날때까지 차감 진행
					tmp -= 1;
					i++;
				}
				i--;
				if(tmp<0) tmp=0; //역방향을 모두 돌았을 때 누적값이 음수면, 이 값은 다음 값에 누적해서 더할 필요가 없다.
			}
		}

		if(arr[N-1] == plus) sum = Math.max(sum, tmp); // 배열의 마지막이 순방향만 있어서, 마지막 누적값이 반영 안된경우 처리
		
		return sum;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		System.out.println(Math.max(countMax(1), countMax(2))); // 오른쪽과 왼쪽을 정방향으로 생각했을 때 최댓값 중 큰 값 출력
		
	}

}
