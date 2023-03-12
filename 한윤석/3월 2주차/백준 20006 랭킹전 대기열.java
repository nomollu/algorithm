public class B20006_랭킹전대기열 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int p = Integer.parseInt(st.nextToken()); //player count
		int m = Integer.parseInt(st.nextToken());  //room limit
		List<List<User>> rooms = new LinkedList<>();
		
		for(int i=0; i<p; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken()); //level
			String n = st.nextToken(); //nickname
			
			if(rooms.size() == 0) {
				List<User> list = new LinkedList<>();
				rooms.add(list);
				rooms.get(0).add(new User(l, n));
			}else {
				boolean flag = true;
				
				for(int j=0; j<rooms.size(); j++) {
					List<User> list = rooms.get(j);
					
					if(list.size() >= m) continue;
					
					User admin = list.get(0);
					if(admin.level + 10 >= l && admin.level - 10 <= l) {
						rooms.get(j).add(new User(l, n));
						flag = false;
						break;
					}
				}
				
				if(flag) {
					List<User> list = new LinkedList<>();
					list.add(new User(l, n));
					rooms.add(list);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(List<User> list : rooms) {
			Collections.sort(list);
			
			if(list.size() == m) sb.append("Started!").append("\n");
			else sb.append("Waiting!").append("\n");
			
			for(User u : list) sb.append(u.level).append(" ").append(u.nickname).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static class User implements Comparable<User>{
		String nickname;
		int level;
		public User(int level, String nickname) {
			this.nickname = nickname;
			this.level = level;
		}
		@Override
		public int compareTo(User o) {
			return this.nickname.compareTo(o.nickname);
		}
	}
}
