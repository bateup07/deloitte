import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * @author Matthew Bateup matthew.bateup@gmail.com
 * Application to populate output file from input file
 */

public class ActivitiesDay {
    public static void main(String[] args) {
        // opens data file
        FileInputStream fin;
        try {
            fin = new FileInputStream("input.txt");
        } catch (FileNotFoundException ex) {
            System.err.println("Failed to open input file \"input.txt\"");
            return;
        }
        Scanner sc = new Scanner(fin);

        // Read input data
        List<Activity> all = new ArrayList<Activity>();
        while (sc.hasNext()) {
            String line = sc.nextLine();
            int idx = line.lastIndexOf(" ");
            String name = line.substring(0, idx);
            String duration = line.substring(idx + 1);
            all.add(new Activity(name, duration));
        }

        // schedule the activities
        List<Team> teams = new ArrayList<Team>();
        while (all.size() > 0) {
            Team curTeam = new Team();
            int curTime = 540; // 09:00 am

            while (true) {
                int remTime;
                boolean pick = false;

                if (curTime <= 720) remTime = 720 - curTime; // morning
                else remTime = 1020 - curTime; // afternoon

                for (int i = 0; i < all.size(); i++) {
                    Activity act = all.get(i);
                    if (act.getDurationInMins() <= remTime) {
                        pick = true;

                        all.remove(i); // remove from event queue
                        act.setStartTime(curTime); // start the activity
                        curTeam.addActivity(act); // assign to the team

                        curTime += act.getDurationInMins();

                        break;
                    }
                }

                if (!pick) {
                    if (curTime <= 720) { // lunch time
                        Activity lunch = new Activity("Lunch Break", "60min");
                        if (curTime <= 660) {
                            lunch.setStartTime(720);
                            curTime = 780; // 01:00 pm
                        } else {
                            lunch.setStartTime(curTime);
                            curTime += 60;
                        }
                        curTeam.addActivity(lunch);
                    } else { // staff motivation presentation
                        Activity presentation = new Activity("Staff Motivation Presentation", "");
                        presentation.setStartTime(1020); // 05:00 pm
                        curTeam.addActivity(presentation);
                        break;
                    }
                }
            }

            teams.add(curTeam);
        }

        // Print the result
        PrintStream out;
        try {
            FileOutputStream fout = new FileOutputStream("output.txt");
            out = new PrintStream(fout);
        } catch (FileNotFoundException ex) {
            System.err.println("Failed to open output file \"output.txt\"");
            out = System.out;
        }


        for (int i = 0; i < teams.size(); i++)
            out.println(teams.get(i));
    }
}
