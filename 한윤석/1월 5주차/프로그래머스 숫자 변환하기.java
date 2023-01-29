static int solution(int x, int y, int n) {
		int ans = -1;
		Queue<int []> q= new LinkedList<>();
		q.add(new int [] {y, 0}); //y에서부터 시작해야 함
		
		while(!q.isEmpty()) {
			int [] info = q.poll();
			
			if(info[0] == x) {
				ans = info[1];
				break;
			}
			
      //3의 배수인지, 2의 배수인지에 대한 조건이 추가되어야 시간초과가 안 남
			if(info[0] - n >= x) q.add(new int [] {info[0] - n, info[1]+1});
			if(info[0] % 2 == 0 && info[0] / 2 >= x) q.add(new int [] {info[0] / 2, info[1]+1});
			if(info[0] % 3 == 0 && info[0] / 3 >= x) q.add(new int [] {info[0] / 3, info[1]+1});
		}
		
		return ans;
}
