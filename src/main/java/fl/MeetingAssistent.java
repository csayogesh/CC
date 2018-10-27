package fl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MeetingAssistent {
    static boolean initialised = false;

    public static void init() throws FileNotFoundException {
        if (!initialised) {
            PersonInventory.init();
            initialised = true;
        }
    }

    void selectMeetingsOfTheDay(String meetingsFileName) throws FileNotFoundException {
        init();
        Scanner sc = new Scanner(new File(meetingsFileName));
        Person person = null;
        if (sc.hasNext()) {
            String[] ar = sc.nextLine().split(":\\s+");
            person = PersonInventory.getPersonDetails(ar[1].toLowerCase().trim());
            if (person == null) {
                System.out.println("Person " + ar[1] + " not found");
            }
        }
        if (person == null) {
            System.out.println("Person not found");
        }
        List<Meeting> meetings = new ArrayList<>();
        while (sc.hasNext()) {
            String[] ar = sc.nextLine().split(",");
            Meeting meeting = new Meeting();
            meeting.setName(ar[0].trim());
            meeting.setStartTime(ar[1].trim().toLowerCase());
            meeting.setEndTime(ar[2].trim().toLowerCase());
            meeting.setNumAttendee(Integer.parseInt(ar[3].trim()));
            meeting.setOrganiser(ar[4].trim().toLowerCase());
            meetings.add(meeting);
        }
        analyseMeetings(person, meetings);
    }

    private void analyseMeetings(Person person, List<Meeting> meetings) {
        if (meetings.size() == 0 || person == null)
            return;
        Collections.sort(meetings, ((o1, o2) -> Integer.compare(o1.getStTime(), o2.getStTime())));
        PriorityQueue<Meeting> queue;
        int currentEnd;
        System.out.println("Name: " + person.getName());
        for (int i = 0; i < meetings.size(); ) {
            queue = new PriorityQueue<Meeting>((o1, o2) -> {
                int res = 0;
                if (o1.getOrganiser().equals(person.getName()) && !o2.getOrganiser().equals(person.getName()))
                    return -1;
                else if (!o1.getOrganiser().equals(person.getName()) && o2.getOrganiser().equals(person.getName()))
                    return 1;
                res = Integer.compare(OrganisationStrategies.getRankingForPerson(o1.getName()), OrganisationStrategies.getRankingForPerson(o2.getName()));
                if (res != 0)
                    return res;
                res = Integer.compare(o1.getNumAttendee(), o2.getNumAttendee());
                return res;
            });
            queue.add(meetings.get(i));
            currentEnd = meetings.get(i).enTime;
            for (int j = i + 1; j < meetings.size(); j++)
                if (meetings.get(j).stTime < currentEnd)
                    queue.add(meetings.get(j));
            Meeting select = queue.poll();
            System.out.println(select);
            while (i < meetings.size() && meetings.get(i).stTime < select.enTime)
                i++;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        new MeetingAssistent().selectMeetingsOfTheDay("meetings.data");
    }
}
