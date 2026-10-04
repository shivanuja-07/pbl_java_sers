public class Event {

    private String eventId;
    private String eventName;
    private String date;
    private String venue;
    private int capacity;

    public Event(String eventId,
                 String eventName,
                 String date,
                 String venue,
                 int capacity) {

        this.eventId = eventId;
        this.eventName = eventName;
        this.date = date;
        this.venue = venue;
        this.capacity = capacity;
    }

    public String getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getDate() {
        return date;
    }

    public String getVenue() {
        return venue;
    }

    public int getCapacity() {
        return capacity;
    }

    public void displayEvent() {

        System.out.println("--------------------------------------");
        System.out.println("Event ID : " + eventId);
        System.out.println("Name     : " + eventName);
        System.out.println("Date     : " + date);
        System.out.println("Venue    : " + venue);
        System.out.println("Capacity : " + capacity);
    }
}