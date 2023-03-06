import java.util.*;
import java.io.*;


public class Main
{
    static class Gold implements Comparable<Gold>{
        int weight, price;
        Gold(int weight, int price){
            this.weight = weight;
            this.price = price;
        }
        @Override
        public int compareTo(Gold o){
            return o.price - this.price;
        }
    }
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int total_weight = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        Gold[] list = new Gold[N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            list[i] = new Gold(weight, price);
        }

        Arrays.sort(list);
        int remain_weight=total_weight, sum_price=0;
        for(int i=0; i<N; i++){
            if(remain_weight<=list[i].weight){
                sum_price += remain_weight * list[i].price;
                break; 
            }else{
                remain_weight -= list[i].weight;
                sum_price += list[i].weight * list[i].price;
            }
        }

        System.out.println(sum_price);
    }
}
