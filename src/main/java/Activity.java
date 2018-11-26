/*
 * @author Matthew Bateup matthew.bateup@gmail.com
 */

public class Activity {
    private String activityName;     // name of the activity
    private String activityDuration; // duration of the activity
    private int startTime;
    
    /**
     * Constructor
     * @param name name of the activity
     * @param duration duration of the activity in minutes or sprint
     */
    public Activity(String name, String duration) {
        activityName = name;
        activityDuration = duration;
    }
    
    /**
     * Get name of the activity
     * @return name
     */
    public String getName() {
        return activityName;
    }
    
    /**
     * Get string representation of the duration
     * @return duration
     */
    public String getDuration() {
        return activityDuration;
    }
    
    /**
     * Get duration in minutes
     * @return duration
     */
    public int getDurationInMins() {
        // sprint = 15 minutes
        if (activityDuration.equals("sprint")) return 15;
        
        // otherwise, the duration is given in minutes
        
        // remove time unit in the string, i.e., 30min -> 30
        String value = activityDuration.substring(0, activityDuration.length() - 3);
        
        return Integer.parseInt(value);
    }
    
    /**
     * Set start time of the activity
     * @param s start time to set
     */
    public void setStartTime(int s) {
        startTime = s;
    }
    
    /**
     * Get start time of the activity
     * @return string representation of the start time
     */
    public String getStartTime() {
        int h = startTime / 60, m = startTime % 60;
        if (h > 12) return String.format("%02d:%02d pm", h - 12, m);
        else if (h == 12) return String.format("%02d:%02d pm", h, m);
        else return String.format("%02d:%02d am", h, m);
    }
}
