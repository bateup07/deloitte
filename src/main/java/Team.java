import java.util.ArrayList;
import java.util.List;

/*
 * @author Matthew Bateup matthew.bateup@gmail.com
 */

public class Team {
    private static int teamCount = 0; // number of all teams
    private int id; // ID of the team
    private List<Activity> activities; // activities of the team
    
    /**
     * Constructor
     */
    public Team() {
        teamCount++;
        id = teamCount;
        activities = new ArrayList<Activity>();
    }
    
    /**
     * Add an activity to the list of activities
     * @param act activity to add
     */
    public void addActivity(Activity act) {
        activities.add(act);
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        
        builder.append("Team ").append(id).append("\n");
        for (int i = 0; i < activities.size(); i++) {
            Activity act = activities.get(i);
            builder.append(String.format("%s : %s %s\n", act.getStartTime(), act.getName(), act.getDuration()));
        }
        
        return builder.toString();
    }
}
