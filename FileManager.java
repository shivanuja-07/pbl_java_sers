import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {

    // ================= SAVE EVENT =================
    public static void saveEvent(Event event) {

        try {

            FileWriter writer =
                new FileWriter("events.txt", true);

            writer.write(
                event.getEventId() + "," +
                event.getEventName() + "," +
                event.getDate() + "," +
                event.getVenue() + "," +
                event.getCapacity() + "\n"
            );

            writer.close();

        } catch (IOException e) {

            System.out.println(
                "Error saving event."
            );
        }
    }

    // ================= SAVE REGISTRATION =================
    public static void saveRegistration(
            Registration registration) {

        try {

            FileWriter writer =
                new FileWriter("registrations.txt", true);

            writer.write(
                registration.getRegistrationId() + "," +
                registration.getStudentId() + "," +
                registration.getEventId() + "\n"
            );

            writer.close();

        } catch (IOException e) {

            System.out.println(
                "Error saving registration."
            );
        }
    }

    // ================= LOAD EVENTS =================
    public static void loadEvents(
            EventManager eventManager) {

        try {

            BufferedReader reader =
                new BufferedReader(
                    new FileReader("events.txt")
                );

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length == 5) {

                    try {

                        String eventId = data[0];
                        String eventName = data[1];
                        String date = data[2];
                        String venue = data[3];
                        int capacity =
                            Integer.parseInt(data[4]);

                        Event event = new Event(
                            eventId,
                            eventName,
                            date,
                            venue,
                            capacity
                        );

                        eventManager.addLoadedEvent(event);

                    } catch (NumberFormatException e) {

                        System.out.println(
                            "Invalid capacity in events.txt."
                        );
                    }
                }
            }

            reader.close();

        } catch (IOException e) {

            // File will be created when first data is saved
        }
    }

    // ================= LOAD REGISTRATIONS =================
    public static void loadRegistrations(
            RegistrationManager registrationManager) {

        try {

            BufferedReader reader =
                new BufferedReader(
                    new FileReader("registrations.txt")
                );

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length == 3) {

                    Registration registration =
                        new Registration(
                            data[0],
                            data[1],
                            data[2]
                        );

                    registrationManager
                        .addLoadedRegistration(
                            registration
                        );
                }
            }

            reader.close();

        } catch (IOException e) {

            // File will be created when first data is saved
        }
    }

    // ================= UPDATE EVENTS FILE =================
    public static void updateEventsFile(
            ArrayList<Event> events) {

        try {

            FileWriter writer =
                new FileWriter("events.txt");

            for (Event event : events) {

                writer.write(
                    event.getEventId() + "," +
                    event.getEventName() + "," +
                    event.getDate() + "," +
                    event.getVenue() + "," +
                    event.getCapacity() + "\n"
                );
            }

            writer.close();

        } catch (IOException e) {

            System.out.println(
                "Error updating events file."
            );
        }
    }

    // ================= UPDATE REGISTRATIONS FILE =================
    public static void updateRegistrationsFile(
            ArrayList<Registration> registrations) {

        try {

            FileWriter writer =
                new FileWriter("registrations.txt");

            for (Registration registration : registrations) {

                writer.write(
                    registration.getRegistrationId() + "," +
                    registration.getStudentId() + "," +
                    registration.getEventId() + "\n"
                );
            }

            writer.close();

        } catch (IOException e) {

            System.out.println(
                "Error updating registrations file."
            );
        }
    }
}