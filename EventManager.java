import java.util.ArrayList;

public class EventManager {

    private ArrayList<Event> events = new ArrayList<>();

    // ================= ADD EVENT =================
    public boolean addEvent(Event event) {

        if (event.getEventId().isEmpty()) {
            System.out.println("Event ID cannot be empty.");
            return false;
        }

        if (event.getEventName().isEmpty()) {
            System.out.println("Event name cannot be empty.");
            return false;
        }

        if (event.getCapacity() <= 0) {
            System.out.println("Capacity must be greater than zero.");
            return false;
        }

        // Check duplicate Event ID
        for (Event existingEvent : events) {

            if (existingEvent.getEventId()
                    .equalsIgnoreCase(event.getEventId())) {

                System.out.println(
                    "Event ID already exists."
                );

                return false;
            }
        }

        events.add(event);

        FileManager.saveEvent(event);

        System.out.println("Event added successfully.");

        return true;
    }

    // ================= VIEW EVENTS =================
    public void viewEvents() {

        if (events.isEmpty()) {

            System.out.println("\nNo events available.");
            return;
        }

        System.out.println(
            "\n========== AVAILABLE EVENTS =========="
        );

        for (Event event : events) {

            event.displayEvent();
        }

        System.out.println(
            "======================================"
        );
    }

    // ================= FIND EVENT =================
    public Event findEvent(String eventId) {

        for (Event event : events) {

            if (event.getEventId()
                    .equalsIgnoreCase(eventId)) {

                return event;
            }
        }

        return null;
    }

    // ================= UPDATE EVENT =================
    public void updateEvent(String eventId,
                            String eventName,
                            String date,
                            String venue,
                            int capacity) {

        if (eventName.isEmpty()
                || date.isEmpty()
                || venue.isEmpty()) {

            System.out.println(
                "Event details cannot be empty."
            );

            return;
        }

        if (capacity <= 0) {

            System.out.println(
                "Capacity must be greater than zero."
            );

            return;
        }

        for (int i = 0; i < events.size(); i++) {

            Event event = events.get(i);

            if (event.getEventId()
                    .equalsIgnoreCase(eventId)) {

                Event updatedEvent = new Event(
                    event.getEventId(),
                    eventName,
                    date,
                    venue,
                    capacity
                );

                events.set(i, updatedEvent);

                FileManager.updateEventsFile(events);

                System.out.println(
                    "Event updated successfully."
                );

                return;
            }
        }

        System.out.println("Event not found.");
    }

    // ================= DELETE EVENT =================
    public boolean deleteEvent(String eventId) {

        for (int i = 0; i < events.size(); i++) {

            Event event = events.get(i);

            if (event.getEventId()
                    .equalsIgnoreCase(eventId)) {

                events.remove(i);

                FileManager.updateEventsFile(events);

                System.out.println(
                    "Event deleted successfully."
                );

                return true;
            }
        }

        System.out.println("Event not found.");

        return false;
    }

    // ================= CHECK EVENT EXISTS =================
    public boolean eventExists(String eventId) {

        return findEvent(eventId) != null;
    }

    // ================= LOAD EVENT =================
    public void addLoadedEvent(Event event) {

        events.add(event);
    }
}