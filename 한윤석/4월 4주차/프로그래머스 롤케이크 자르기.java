public class P롤케이크자르기 {
	public int solution(int[] topping) {
        int answer = 0;
        int tlen = topping.length;
        int [] left = new int [tlen]; // 왼쪽에서부터 i번까지 봤을 때 토핑 종류의 수
        int [] right = new int [tlen]; // 오른쪽에서부터 ""
        Map<Integer, Boolean> ldic = new HashMap<>(); //토핑 종류 세기 위한 dic
        Map<Integer, Boolean> rdic = new HashMap<>();
        
        for(int i=0; i<tlen; i++) {
        	int num = topping[i]; //왼쪽에서부터 토핑
        	int rIdx = tlen - 1 - i;
        	int num2 = topping[rIdx]; //오른쪽에서부터 토핑
        	
        	if(i == 0) {
        		left[i] = 1;
        		right[rIdx] = 1;
        		ldic.put(num, true);
        		rdic.put(num2, true);
        		continue;
        	}
        	
        	if(ldic.containsKey(num)) left[i] = left[i-1];
        	else {
        		ldic.put(num, true);
        		left[i] = left[i-1] + 1;
        	}
        	
        	if(rdic.containsKey(num2)) right[rIdx] = right[rIdx+1];
        	else {
        		rdic.put(num2, true);
        		right[rIdx] = right[rIdx+1] + 1;
        	}
        }
        
        for(int i=0; i<tlen - 1; i++) if(left[i] == right[i+1]) answer++;
        
        return answer;
    }
}
