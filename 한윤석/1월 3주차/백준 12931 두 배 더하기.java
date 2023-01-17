public class B12931_두배더하기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N =Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int arr [] = new int [N];
		int ans = 0;
		
		for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		while(true) {
			boolean allEven = true;
			boolean isFinished = true;
			
			for(int i=0; i<N; i++) {
				if(arr[i] != 0) isFinished = false;
				if(arr[i] % 2 != 0) {
					ans++;
					arr[i]--;
					allEven = false;
				}
			}
			
			if(isFinished) break;
			
			if(allEven) {
				for(int i=0; i<N; i++) arr[i] /= 2;
				ans++;
			}
		}
		System.out.println(ans);
	}
}
