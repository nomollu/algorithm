public class B1790_수이어쓰기2 {

	public static void main(String[] args) {	
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int k = sc.nextInt();
		
		int KN = 0; //k번째에 들어가는 숫
		int level = 1; //k번째에 들어가는 숫자가 몇자리 수인지; 
		
    //k번째에 들어가는 수가 뭔지, 몇 자리 수인지 도출
		while(true) {
			int cnt = (int)(level * 9 * Math.pow(10, level-1));
			
			if(k - cnt < 0) break;
			
			KN += 9 * Math.pow(10, level-1);
			k -= cnt;
			level++;
		}
		
    // KN은 level자리수들 중 idx 번째 수임
		int idx = k / level;
		int remain = k % level; //그 중 remain번째에 들어가는 수임
		
		if(remain == 0) KN += idx;
		else KN += idx+1;
		
		if(KN > N) System.out.println(-1);
		else {
			if(remain == 0) {
				String KNS = Integer.toString(KN);
				System.out.println(KNS.charAt(KNS.length()-1));
			}
			else {
				String KNS = Integer.toString(KN);
				System.out.println(KNS.charAt(remain-1));
			}
		}
	}
}
