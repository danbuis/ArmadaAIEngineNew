package components.ship;

public class ManeuverTemplate {
    private final int[][] navChart;
    private String originalData;

    /**
     * Iteratively build the maneuver chart in a machine readable format
     * @param maneuverChart string from input file
     */
    public ManeuverTemplate(String maneuverChart) {
        this.originalData = maneuverChart;
        String[] speedStrings = maneuverChart.split(" ");
        int[][] navChartArray = new int[5][0]; // [speed][joint clicks]
        for (int i = 0; i <= speedStrings.length - 1; i++) {
            if (!speedStrings[i].equals("-")) {
                // split the column into single digits
                String[] navColumn = speedStrings[i].split("");

                // make column the appropriate length
                navChartArray[i+1] = new int[navColumn.length]; //+1 to make sure that we can call with the actual speed and avoid 0-indexed nonsense

                // fill in specific numbers
                for (int j = 0; j <= navColumn.length - 1; j++) {
                    String digitToAdd = navColumn[j];
                    navChartArray[i+1][j] = Integer.parseInt(digitToAdd);
                }
            }
        }
        this.navChart = navChartArray;
    }

    /**
     * Check if the proposed maneuver is a valid maneuver
     * @param clicks list of clicks in the proposed maneuver
     * @param extraClicks how many extra clicks are available over and above the base chart
     * @return is this a valid maneuver
     */
    public boolean validateManeuver(int[] clicks, int extraClicks){
        int extrasUsed = 0;
        int speed = clicks.length;
        if(!validSpeed(speed)){
            return false;
        }
        int[] availableClicks = navChart[speed];
        for (int i = 0; i < speed ; i++) {
            if(Math.abs(clicks[i]) > 2){
                return false;
            }
            if(Math.abs(clicks[i]) > availableClicks[i]) {
                extrasUsed++;
            }
        }
        return extrasUsed <= extraClicks;
    }

    /**
     * Is this speed even available?
     * @param desiredSpeed speed to check
     * @return is this speed available
     */
    public boolean validSpeed(int desiredSpeed){
        return navChart[desiredSpeed].length != 0;
    }

    /**
     * Get the clicks on the base chart
     * @param speed speed to check
     * @return list of clicks at each joing
     */
    public int[] getClicks(int speed){
        return navChart[speed];
    }

    public String toString(){
        String base = "";
        for(int[] clicks: navChart){
            base += "[";
            for(int item:clicks){
                base += (item+",");
            }
            base += "]";
        }
        return base;
    }

    /**
     * Something to grab the original data just in case you want it for something
     * @return
     */
    public String getOriginalData() { return this.originalData;}
}
