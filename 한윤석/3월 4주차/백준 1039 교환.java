public class B1039_교환 {

	static int ans = -1;
	static boolean visit []; //방문 체크
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		bfs(st.nextToken(), Integer.parseInt(st.nextToken()));
		
		System.out.println(ans);
	}
	
	static void bfs(String start, int K) {
		Queue<Info> q = new LinkedList<>();
		q.add(new Info(start, 0));
		
		while(!q.isEmpty()) {
			int size = q.size();
			visit = new boolean[(int)Math.pow(10, start.length())+1];
			
			//교환 횟수 갱신 때 마다 방문 체크 해야 함
			while(size-- > 0) {
				Info i = q.poll();
				
				//K번 다 교환했을 때
				if(i.cnt == K) {
					int num = Integer.parseInt(i.s);
					ans = Math.max(ans, num);
					continue;
				}
				
				//앞에서부터 하나씩 교환 해 봄
				for(int j=0; j<i.s.length()-1; j++) {
					for(int k=j+1; k<i.s.length(); k++) {
						StringBuilder temp = new StringBuilder(i.s);
						char c1 = i.s.charAt(j); 
						char c2 = i.s.charAt(k);
						temp.setCharAt(j, c2); //j번 인덱스의 값을 k번 인덱스의 값으로
						temp.setCharAt(k, c1); //k번 인덱스의 값을 j번 인덱스의 값으로
						int result = Integer.parseInt(temp.toString());
						
						//0이 맨 앞으로 오는 경우거나, 이미 방문했던 값이면
						if(j == 0 && c2=='0' || visit[result]) continue;
							
						q.add(new Info(temp.toString(), i.cnt+1));
						visit[result] = true;
					}
				}
			}
		}
	}
	
	static class Info{
		String s; //숫자
		int cnt; //몇 번 교환했는지
		public Info(String s, int cnt) {
			this.s = s;
			this.cnt = cnt;
		}
	}
}
