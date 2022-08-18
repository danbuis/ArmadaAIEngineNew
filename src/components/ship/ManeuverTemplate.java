package components.ship;

public class ManeuverTemplate {
    private final int[][] navChart;

    public ManeuverTemplate(String maneuverChart) {
        String[] speedStrings = maneuverChart.split(" ");
        int[][] navChartArray = new int[5][0]; // [speed][joint clicks]
        for (int i = 1; i <= speedStrings.length - 1; i++) {
            if (!speedStrings[i].equals("-")) {
                // split the column into single digits
                String[] navColumn = speedStrings[i].split("");

                // make column the appropriate length
                navChartArray[i] = new int[navColumn.length];

                // fill in specific numbers
                for (int j = navColumn.length - 1; j >= 0; j--) {
                    String digitToAdd = navColumn[j];
                    if (digitToAdd.equals("-")) {
                        digitToAdd = "0";
                    }
                    navChartArray[i][navColumn.length - 1 - j] = Integer.parseInt(digitToAdd);
                }
            }
        }
        this.navChart = navChartArray;
    }

    public boolean validateManeuver(int[] clicks, int extraClicks){
        int extrasUsed = 0;
        int speed = clicks.length;
        int[] availableClicks = navChart[speed];
        for (int i = 0; i < speed ; i++) {
            if(Math.abs(clicks[i]) > availableClicks[i]) {
                extrasUsed++;
            }
        }
        return extrasUsed <= extraClicks;
    }

    public boolean validSpeed(int desiredSpeed){
        return navChart[desiredSpeed].length != 0;
    }

    public int[] getClicks(int speed){
        return navChart[speed];
    }

    public String toString(){
        String base = "";
        for(int[] clicks: navChart){
            base += clicks;
        }
        return base;
    }
}
