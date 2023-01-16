package 미분류;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj17503_맥주축제 {

    static class Beer implements Comparable<Beer> {
        public Long v, c; // 선호도, 도수 레벨

        @Override
        public Long compareTo(Beer o) {
            return c - o.c;
        }
    }
    static long N, M, K; // 축제 기간, 채워야 하는 선호도의 합, 맥주 종류 수
    static PriorityQueue<Beer> beers; // 맥주들
    static PriorityQueue<Beer> drinkBeers; // 마실 맥주들
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());


        beers = new PriorityQueue<>(new Comparator<Beer>() {


            @Override
            public long compare(Beer o1, Beer o2) {
                return o1.c - o2.c;
            }
        });
        drinkBeers = new PriorityQueue<>(new Comparator<Beer>() {
            @Override
            public long compare(Beer o1, Beer o2) {
                return o1.v - o2.v;
            }
        });

        for(long i = 0 ; i < K ; i++){
            st = new StringTokenizer(br.readLine());
            Beer beer = new Beer();
            beer.v = Long.parseLong(st.nextToken());
            beer.c = Long.parseLong(st.nextToken());
            beers.add(beer);
        }

        // 우선, 제일 앞의 N개의 맥주를 drinkBeers에 담는다.
        long sumLike = 0;
        long maxLevel = -1;
        boolean flag = false; // true면 맥주 마실수있다!
        for(long i = 0 ; i < N ; i++){
            Beer tmp = beers.poll();
            drinkBeers.add(tmp);
            sumLike += tmp.v; // 선호도 누적 합
            maxLevel = Math.max(maxLevel, tmp.c);
        }

        // 선호도를 다 채우지 못하면
        if(sumLike < M){
            // 선호도를 채울 수 있을때까지 반복한다.
            for(long i = N ; i < K ; i++){

                // drinkBeers에서 가장 낮은 선호도를 뺀다.
                Beer outBeer = drinkBeers.poll();
                Beer newBeer = beers.poll();

                // sumLike에서 해당 beer의 도수를 뺀다.
                sumLike -= outBeer.c;

                // drinkBeers에 beers[i]를 넣는다.
                drinkBeers.add(newBeer);

                // sumLike에 beers[i]의 도수를 더한다.
                sumLike += newBeer.c;

                // 선호도를 채우면 탈출한다.
                if(sumLike >= M){
                    // 마실 수 있는 맥주 중 가장 높은 도수를 구한다.
                    maxLevel = drinkBeers.poll().c;
                    for(long j = 1 ; j < N ; j++){
                        maxLevel = Math.max(maxLevel, drinkBeers.poll().c);
                    }
                    flag = true;
                    break;
                }
            }
        } else {
            maxLevel = sumLike;
        }

        System.out.println(flag ? maxLevel : -1);
    }
}