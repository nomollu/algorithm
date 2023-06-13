public class P2개이하로다른비트 {

	public static void main(String[] args) {
		long [] arr = {2, 7, 69};
		for(long l : solution(arr)) System.out.println(l);
	}

	static public long[] solution(long[] numbers) {
		int len = numbers.length;
    long[] answer = new long [len];

    for(int i=0; i<len; i++) {
      // 짝수이면 2진법에서 마지막이 무조건 0이기 때문에 자기자신에 +1하면 됨
      if(numbers[i] % 2 == 0) answer[i] = numbers[i]+1;
      else {
        String bit = Long.toBinaryString(numbers[i]);

        // 가장 작은 0이 나오는 인덱스 위치의 0을 1로 바꾸고 그 다음칸을 0으로 바꿈
        if(bit.contains("0")) {
          int idx = 0; // 가장 작은 0이 나오는 인덱스
          for(int j=bit.length()-1; j>=0; j--) {
            if(bit.charAt(j) == '0') {
              idx = j;
              break;
            }
          }
          bit = bit.substring(0, idx) + "10" + bit.substring(idx+2);
        }
        // 전부 1로 구성된 경우 맨앞에서 2번째에 0 넣어주면 됨
        else bit = "10" + bit.substring(1);
        answer[i] = binaryToLong(bit);
      }
    }

    return answer;
  }

  // 2진 문자열을 long타입 숫자로 리턴
  static long binaryToLong(String bit) {
    long l = 0;
    int pow = 0;

    for(int i=bit.length()-1; i>=0; i--) {
      if(bit.charAt(i) == '1')
        l += Math.pow(2, pow);
      pow++;
    }

    return l;
    }
}
