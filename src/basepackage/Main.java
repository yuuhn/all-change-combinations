package basepackage;

import java.util.Scanner;

class Solution {
    private int[] coins;
    private int ways;

    Solution(int changeFor, int[] coins) {
        this.coins = coins;

        this.ways = dChange(changeFor);
    }

    int getWays() {
        return ways;
    }

    private int rChange(int changeFor, int startIndex) {
        int sum = 0;
        int index = startIndex;
        while (index < coins.length) {
            if (coins[index] <= changeFor) {
                if (changeFor == coins[index]) {
                    sum += 1;
                }
                else {
                    sum += rChange(changeFor - coins[index], index);
                }
            }

            index += 1;
        }

        return sum;
    }

    private int dChange(int changeFor) {
        int[] ways = new int[changeFor + 1];
        for (int i = 1; i < changeFor + 1; i++) {
            ways[i] = 0;
        }
        ways[0] = 1;

        for (int coin : coins) {
            for (int i = coin; i < changeFor + 1; i++) {
                ways[i] += ways[i - coin];
            }
        }

        return ways[changeFor];
    }
}

class Main {
    public static void main (String[] args) {

        Scanner sc = new Scanner(System.in);
        int tests = sc.nextInt();
        for (int i = 0; i < tests; i++) {
            int[] coins = new int[sc.nextInt()];
            for (int j = 0; j < coins.length; j++) {
                coins[j] = sc.nextInt();
            }

            Solution solution = new Solution(sc.nextInt(), coins);
            System.out.println(solution.getWays());
        }
    }
}
