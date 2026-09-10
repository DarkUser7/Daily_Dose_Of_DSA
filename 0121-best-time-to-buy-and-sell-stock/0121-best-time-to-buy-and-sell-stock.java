class Solution {
    public int maxProfit(int[] prices) {
        int minPrice = prices[0];
        int maxprofit =0;
        for(int i=1 ;i<prices.length ;i++){
            if(prices[i] < minPrice){
                minPrice = prices[i];
            }
            int profit = prices[i] -minPrice;
            if( profit > maxprofit){
                maxprofit = profit;
            }
        }
        return maxprofit;
    }
}