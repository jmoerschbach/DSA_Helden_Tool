package aventurian;

/**
 * Created by Hauke on 12.07.2017.
 */
class LevelCostCalculator {

    private int[][] costs;

    public LevelCostCalculator() {
        int[] cost0 = {0, 1, 1, 1, 2, 4, 5, 6, 8, 9, 11, 12, 14, 15, 17, 19, 20, 22, 24, 25, 27, 29, 31, 32, 34, 36, 38, 40, 42, 43, 45, 48};
        int[] cost1 = {0, 1, 2, 3, 4, 6, 7, 8, 10, 11, 13, 14, 16, 17, 19, 21, 22, 24, 26, 27, 29, 31, 33, 34, 36, 38, 40, 42, 44, 45, 47, 50};
        int[] cost2 = {0, 2, 4, 6, 8, 11, 14, 17, 19, 22, 25, 28, 32, 35, 38, 41, 45, 48, 51, 55, 58, 62, 65, 69, 73, 76, 80, 84, 87, 91, 95, 100};
        int[] cost3 = {0, 2, 6, 9, 13, 17, 21, 25, 29, 34, 38, 43, 47, 51, 55, 60, 65, 70, 75, 80, 85, 95, 100, 105, 110, 115, 120, 125, 130, 135, 140, 150};
        int[] cost4 = {0, 3, 7, 12, 17, 22, 27, 33, 39, 45, 50, 55, 65, 70, 75, 85, 90, 95, 105, 110, 115, 125, 130, 140, 145, 150, 160, 165, 170, 180, 190, 200};
        int[] cost5 = {0, 4, 9, 15, 21, 28, 34, 41, 48, 55, 65, 70, 80, 85, 95, 105, 110, 120, 130, 135, 145, 155, 165, 170, 180, 190, 200, 210, 220, 230, 240, 250};
        int[] cost6 = {0, 6, 14, 22, 32, 41, 50, 60, 75, 85, 95, 105, 120, 130, 140, 155, 165, 180, 195, 210, 220, 230, 250, 260, 270, 290, 300, 310, 330, 340, 350, 375};
        int[] cost7 = {0, 8, 18, 30, 42, 55, 70, 85, 95, 110, 125, 140, 160, 175, 190, 210, 220, 240, 260, 270, 290, 310, 330, 340, 360, 380, 400, 420, 440, 460, 480, 500};
        int[] cost8 = {0, 16, 35, 60, 85, 110, 140, 165, 195, 220, 250, 280, 320, 350, 380, 410, 450, 480, 510, 550, 580, 620, 650, 690, 720, 760, 800, 830, 870, 910, 950, 1000};
        costs = new int[9][32];
        costs[0] = cost0;
        costs[1] = cost1;
        costs[2] = cost2;
        costs[3] = cost3;
        costs[4] = cost4;
        costs[5] = cost5;
        costs[6] = cost6;
        costs[7] = cost7;
        costs[8] = cost8;
    }

    int getCost(int von, int auf, int spalte) {
        int vonCost = getSum(spalte, von);
        int aufCost = getSum(spalte, auf);
        return aufCost - vonCost;
    }

    public int getCost(int von, int auf, String spalte) {
        int s;
        switch (spalte) {
            case "A":   s = 1;
                break;
            case "B":   s = 2;
                break;
            case "C":   s = 3;
                break;
            case "D":   s = 4;
                break;
            case "E":   s = 5;
                break;
            default:    s = 6;
        }
        return getCost(von, auf, s);
    }

    private int getSum(int spalte, int zeile) {
        int sum = 0;
        while (zeile >= 0) {
            sum += getValue(spalte, zeile);
            zeile--;
        }
        return sum;
    }

    private int getValue(int spalte, int zeile) {
        return costs[spalte][zeile];
    }
}
