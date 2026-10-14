class Solution {
    public String maxSumOfSquares(int num, int sum) {
        // if required sum is greater than maximum possible sum i.e 9's, it's impossible
        if(sum > num*9) return "";
        StringBuilder sb = new StringBuilder();

        //count number of 9's we can fully form
        int nines = sum/9, rem = sum % 9;

        //Append all 9's first to maximise
        for(int i = 0 ; i < nines; i++)sb.append('9');
        //Append remainder digit if it is non zero
        if(rem > 0) sb.append(rem);

        //Fill remaining digits with zeroes
        int remaining = num-sb.length();
        for(int i = 0 ; i < remaining ; i++) sb.append('0');

        return sb.toString();
    }
}