

public class Meeting {
    String name;
    String startTime;
    int stTime;
    String endTime;
    int enTime;
    int numAttendee;
    String organiser;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
        this.stTime = convertTime(startTime);
    }

    private int convertTime(String startTime) {
        int ans = 0;
        startTime = startTime.trim().toLowerCase();
        if (startTime.endsWith("pm"))
            ans += 12 * 60;
        startTime = startTime.replace("pm", "");
        startTime = startTime.replace("am", "");
        if (startTime.contains(":")) {
            String[] ar = startTime.split(":");
            if (ar[0].length() > 0)
                ans += Integer.parseInt(ar[0]) * 60;
            if (ar[1].length() > 0)
                ans += Integer.parseInt(ar[1]);
        } else
            ans += Integer.parseInt(startTime) * 60;
        return ans;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
        this.enTime = convertTime(endTime);
    }

    public int getNumAttendee() {
        return numAttendee;
    }

    public void setNumAttendee(int numAttendee) {
        this.numAttendee = numAttendee;
    }

    public String getOrganiser() {
        return organiser;
    }

    public void setOrganiser(String organiser) {
        this.organiser = organiser;
    }

    public int getStTime() {
        return stTime;
    }

    public int getEnTime() {
        return enTime;
    }

    @Override
    public String toString() {
        return name + ", " + startTime + ", " + endTime + ", " + numAttendee + ", " + organiser;
    }
}
