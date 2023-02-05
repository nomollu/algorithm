import java.util.Scanner;

public class B11057_오르막수 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int loop = (int)Math.pow(10, N);
		int ans = 0;
		
		outer:for(int i=0; i<loop; i++) {
			String s = Integer.toString(i);
			char [] parsed = s.toCharArray();
			
			for(int j=1; j<s.length(); j++) if(parsed[j] - '0' < parsed[j-1] - '0') continue outer;
			
			ans = (ans+1)%7;
		}
		
		System.out.println(ans);
		
	}
}
