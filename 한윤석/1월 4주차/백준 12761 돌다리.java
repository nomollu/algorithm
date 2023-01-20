public class B12761_돌다리 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int A = sc.nextInt();
		int B = sc.nextInt();
		int N = sc.nextInt();
		int M = sc.nextInt();
		boolean visit [] = new boolean [100001];
		int ans = Integer.MAX_VALUE;
		Queue<int []> q = new LinkedList<>();
		visit[N] = true;
		q.add(new int [] {N, 0});
		
		while(!q.isEmpty()) {
			int info [] = q.poll();
			
			if(info[0] == M) {
				ans = Math.min(ans, info[1]);
				continue;
			}
			
			if(info[0] - 1 > 0 && !visit[info[0] - 1]) {
				visit[info[0] - 1] = true;
				q.add(new int [] {info[0] - 1, info[1] + 1});
			}
			if(info[0] + 1 <= 100000 && !visit[info[0] + 1]) {
				visit[info[0] + 1] = true;
				q.add(new int [] {info[0] + 1, info[1] + 1});
			}
			if(info[0] - A > 0 && !visit[info[0] - A]) {
				visit[info[0] - A] = true;
				q.add(new int [] {info[0] - A, info[1] + 1});
			}
			if(info[0] - B > 0 && !visit[info[0] - B]) {
				visit[info[0] - B] = true;
				q.add(new int [] {info[0] - B, info[1] + 1});
			}
			if(info[0] + A <= 100000 && !visit[info[0] + A]) {
				visit[info[0] + A] = true;
				q.add(new int [] {info[0] + A, info[1] + 1});
			}
			if(info[0] + B <= 100000 && !visit[info[0] + B]) {
				visit[info[0] + B] = true;
				q.add(new int [] {info[0] + B, info[1] + 1});
			}
			if(info[0] * A <= 100000 && !visit[info[0] * A]) {
				visit[info[0] * A] = true;
				q.add(new int [] {info[0] * A, info[1] + 1});
			}
			if(info[0] * B <= 100000 && !visit[info[0] * B]) {
				visit[info[0] * B] = true;
				q.add(new int [] {info[0] * B, info[1] + 1});
			}
		}
		
		System.out.println(ans);
	}
}
