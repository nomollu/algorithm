import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_Gold5_9519_졸려 {
    static int X;
    static String word;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        X = Integer.parseInt(br.readLine());
        word = br.readLine();

        int cycleCnt = findCycle();
        if(cycleCnt == 1){ // 1번만에 원래 단어로 돌아온다면 다 같은거
            System.out.println(word);
            return;
        }
        int cnt = 0;
        if(X < cycleCnt){ //사이클 횟수보다 적을때
            cnt = cycleCnt-X;
        }else{
            cnt = X % cycleCnt-1;
        }

        for(int j=0; j<cnt; j++){
            word = makeNewWord(word);
        }
        System.out.println(word);
    }

    static String makeNewWord(String inputWord){
        String newWord = "";
        int mid = inputWord.length()/2;
        for(int i=0; i<mid; i++){
            newWord += inputWord.charAt(i);
            newWord += inputWord.charAt(inputWord.length()-i-1);
        }
        newWord += inputWord.length() % 2 != 0 ? inputWord.charAt(mid) : "";
        return newWord;
    }

    static int findCycle(){ //몇번을 기준으로 원래 단어로 돌아오는지 구할 함수
        String originWord = word; //비교해줄 원래 문자열
        String inputWord = word;

        int cnt = 0;
        while(true){
            cnt++;
            inputWord = makeNewWord(inputWord);
            if(originWord.equals(inputWord)){
                break;
            }
        }
        return cnt;
    }
}
