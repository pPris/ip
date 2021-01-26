import static java.lang.Boolean.parseBoolean;

public class Event extends Task {
    private String eventTiming;

    public Event(String desc, String eventTiming) {
        super(desc);
        this.eventTiming = eventTiming;
    }

    private Event(String desc, String eventTiming, boolean isDone) {
        super(desc, isDone);
        this.eventTiming = eventTiming;
    }

    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description
                + " (at: " + eventTiming + ")";
    }

    @Override
    public String unparse() {
        return "E" + delimiter + description + delimiter + isDone
                + delimiter + eventTiming;
    }

    //E;;desc;;true;;timing
    public static Event parse(String oneLine) {
        // some repetition in this function across all types of tasks but abstracting them might be costly
        int start = 0;
        assert oneLine.startsWith("T" + delimiter);

        int descStartIdx = oneLine.indexOf(delimiter);
        int descEndIdx = oneLine.indexOf(delimiter, descStartIdx + 1);
        String desc = oneLine.substring(descStartIdx + delimiter.length(), descEndIdx);

        int doneEndIdx = oneLine.indexOf(delimiter, descEndIdx + 1);
        String doneStr = oneLine.substring(descEndIdx + delimiter.length(), doneEndIdx);
        Boolean isDone = parseBoolean(doneStr);


        String eventTiming = oneLine.substring(doneEndIdx + delimiter.length());

        return new Event(desc,  eventTiming, isDone);
    }

    // for testing purposes
    public static void main(String[] args) {
        Event t = new Event("hello world", "8pm");
        System.out.println(t);
        t.markAsDone();
        System.out.println(t);
        System.out.println(t.unparse());
        System.out.println(parse(t.unparse()));
        // test with other cases without relying on t
    }


}
