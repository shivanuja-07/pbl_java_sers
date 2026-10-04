import java.util.ArrayList;

public class RegistrationManager {

    private ArrayList<Registration> registrations =
        new ArrayList<>();

    // ================= REGISTER STUDENT =================
    public void registerStudent(String studentId,
                                String eventId,
                                EventManager eventManager) {

        if (studentId.isEmpty()) {

            System.out.println(
                "Student ID cannot be empty."
            );

            return;
        }

        if (eventId.isEmpty()) {

            System.out.println(
                "Event ID cannot be empty."
            );

            return;
        }

        // Check whether event exists
        Event event = eventManager.findEvent(eventId);

        if (event == null) {

            System.out.println(
                "Event not found. Registration failed."
            );

            return;
        }

        // Check duplicate registration
        for (Registration registration : registrations) {

            if (registration.getStudentId()
                    .equalsIgnoreCase(studentId)
                    && registration.getEventId()
                    .equalsIgnoreCase(eventId)) {

                System.out.println(
                    "Student is already registered for this event."
                );

                return;
            }
        }

        // Check event capacity
        int participantCount = 0;

        for (Registration registration : registrations) {

            if (registration.getEventId()
                    .equalsIgnoreCase(eventId)) {

                participantCount++;
            }
        }

        if (participantCount >= event.getCapacity()) {

            System.out.println(
                "Event capacity is full."
            );

            return;
        }

        // Generate registration ID
        String registrationId = generateRegistrationId();

        Registration registration = new Registration(
            registrationId,
            studentId,
            eventId
        );

        registrations.add(registration);

        FileManager.saveRegistration(registration);

        System.out.println(
            "Registration successful!"
        );

        System.out.println(
            "Registration ID: " + registrationId
        );
    }

    // ================= GENERATE REGISTRATION ID =================
    private String generateRegistrationId() {

        int maxId = 0;

        for (Registration registration : registrations) {

            String id = registration.getRegistrationId();

            try {

                int number =
                    Integer.parseInt(id.substring(1));

                if (number > maxId) {
                    maxId = number;
                }

            } catch (Exception e) {

                // Ignore invalid registration IDs
            }
        }

        return "R" + (maxId + 1);
    }

    // ================= VIEW REGISTRATIONS =================
    public void viewRegistrations(String studentId) {

        boolean found = false;

        System.out.println(
            "\n========== YOUR REGISTRATIONS =========="
        );

        for (Registration registration : registrations) {

            if (registration.getStudentId()
                    .equalsIgnoreCase(studentId)) {

                System.out.println(
                    "----------------------------------------"
                );

                System.out.println(
                    "Registration ID : "
                    + registration.getRegistrationId()
                );

                System.out.println(
                    "Student ID      : "
                    + registration.getStudentId()
                );

                System.out.println(
                    "Event ID        : "
                    + registration.getEventId()
                );

                found = true;
            }
        }

        if (!found) {

            System.out.println(
                "No registrations found."
            );
        }

        System.out.println(
            "========================================"
        );
    }

    // ================= CANCEL REGISTRATION =================
    public void cancelRegistration(String registrationId) {

        if (registrationId.isEmpty()) {

            System.out.println(
                "Registration ID cannot be empty."
            );

            return;
        }

        for (int i = 0; i < registrations.size(); i++) {

            Registration registration =
                registrations.get(i);

            if (registration.getRegistrationId()
                    .equalsIgnoreCase(registrationId)) {

                registrations.remove(i);

                FileManager.updateRegistrationsFile(
                    registrations
                );

                System.out.println(
                    "Registration cancelled successfully."
                );

                return;
            }
        }

        System.out.println(
            "Registration not found."
        );
    }

    // ================= VIEW PARTICIPANTS =================
    public void viewParticipants(String eventId) {

        boolean found = false;

        System.out.println(
            "\n========== EVENT PARTICIPANTS =========="
        );

        for (Registration registration : registrations) {

            if (registration.getEventId()
                    .equalsIgnoreCase(eventId)) {

                System.out.println(
                    "----------------------------------------"
                );

                System.out.println(
                    "Registration ID : "
                    + registration.getRegistrationId()
                );

                System.out.println(
                    "Student ID      : "
                    + registration.getStudentId()
                );

                found = true;
            }
        }

        if (!found) {

            System.out.println(
                "No participants registered for this event."
            );
        }

        System.out.println(
            "========================================"
        );
    }

    // ================= LOAD REGISTRATION =================
    public void addLoadedRegistration(
            Registration registration) {

        registrations.add(registration);
    }
}