

import java.io.File;
import java.util.*;

public class MeetingAssistent {
    static boolean initialised = false;

    public static void init() throws Exception {
        if (!initialised) {
            PersonInventory.init();
            initialised = true;
        }
    }

    void selectMeetingsOfTheDay(String meetingsFileName) throws Exception {
        Person person = null;
        List<Meeting> meetings = new ArrayList<>();
        init();
        try {
            Scanner sc = new Scanner(new File(meetingsFileName));
            if (sc.hasNext()) {
                String[] ar = sc.nextLine().split(":\\s+");
                person = PersonInventory.getPersonDetails(ar[1].toLowerCase().trim());
                if (person == null) {
                    System.out.println("Person " + ar[1] + " not found");
                    return;
                }
            }
            if (person == null) {
                System.out.println("Person not found");
                return;
            }
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
        } catch (Exception e) {
            throw new Exception("Error while getting meetings input", e);
        }
        analyseMeetings(person, meetings);
    }

    private void analyseMeetings(Person person, List<Meeting> meetings) throws Exception {
        try {
            if (meetings.size() == 0 || person == null) {
                System.out.println("Input error ");
                return;
            }
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
        } catch (Exception e) {
            throw new Exception("Error while processing meetings data", e);
        }
    }

    public static void main(String[] args) throws Exception {
        new MeetingAssistent().selectMeetingsOfTheDay("meetings.data");
    }
}
