public class B12904_A와B {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String a = sc.next(); //start
		String b = sc.next(); //target
		boolean flag = false;
		
    // target에서 조건에 맞게 하나씩 잘라가면서 결국 a가 되는지 확인
		while(true){
			if(b.equals(a)) {
				flag = true;
				break;
			}else if(b.length() <= a.length()) break;
			
			int len = b.length()-1;
			
			if(b.charAt(len) == 'A') b = b.substring(0, len);
			else b = reverse(b.substring(0, len));
		}
		
		if(flag) System.out.println(1);
		else System.out.println(0);
	}
	
	static String reverse(String s) {
		String reversed = "";
		int len = s.length();
		
		for(int i=len-1; i>=0; i--) reversed += s.charAt(i);
		
		return reversed;
	}
}
