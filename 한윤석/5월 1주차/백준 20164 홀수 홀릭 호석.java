public class B20168_골목대장호석_기능성 {

	static int ans [] = {Integer.MAX_VALUE, 0}; //[최소, 최대]
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		
		recursion(s, 0);
		
		System.out.println(ans[0] + " " + ans[1]);
	}
	
  // s : 현재 문자열, cnt : 누적 홀수 개수
	static void recursion(String s, int cnt) {
		int len = s.length();
		int [] si = new int [len];
		
		for(int i=0; i<len; i++) si[i] = s.charAt(i) - '0';
		
		int oddCnt = getOddCnt(si); // s에 있는 홀수 수
		
		switch(len) {
			case 1: //길이 1
				ans[0] = Math.min(ans[0], cnt+oddCnt);
				ans[1] = Math.max(ans[1], cnt+oddCnt);
				break;
			case 2: //길이 2
				int a1 = s.charAt(0) - '0';
				int a2 = s.charAt(1) - '0';
				oddCnt = getOddCnt(new int [] {a1, a2});
				recursion(Integer.toString(a1+a2), cnt+oddCnt);
				break;
			default: //길이 3이상
        // 문자열을 자를 포인트 2군데를 픽함
				for(int i=1; i<len-1; i++) {
					for(int j=i+1; j<len; j++) {
						int n1 = Integer.parseInt(s.substring(0, i));
						int n2 = Integer.parseInt(s.substring(i, j));
						int n3 = Integer.parseInt(s.substring(j));
						recursion(Integer.toString(n1 + n2 + n3), cnt + oddCnt);
					}
				}
				break;
		}
	}
	
  // arr 배열 내의 홀수 개수 리턴
	static int getOddCnt(int [] arr) {
		int oddCnt = 0;
		for(int i=0; i<arr.length; i++) if(arr[i] % 2 != 0) oddCnt++;
		return oddCnt;
	}
}
