import java.util.Scanner;

public class Main {

    // ================= MAIN METHOD =================
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        EventManager eventManager =
            new EventManager();

        RegistrationManager registrationManager =
            new RegistrationManager();

        // Load saved data
        FileManager.loadEvents(eventManager);
        FileManager.loadRegistrations(
            registrationManager
        );

        while (true) {

            System.out.println(
                "\n========================================"
            );
            System.out.println(
                " SCHOOL EVENT REGISTRATION & MANAGEMENT"
            );
            System.out.println(
                "========================================"
            );

            System.out.println("1. Student Login");
            System.out.println("2. Admin Login");
            System.out.println("3. Exit");

            int choice = readInt(
                sc,
                "Enter choice: "
            );

            switch (choice) {

                // ================= STUDENT =================
                case 1:

                    studentLogin(
                        sc,
                        registrationManager,
                        eventManager
                    );

                    break;

                // ================= ADMIN =================
                case 2:

                    adminLogin(
                        sc,
                        eventManager,
                        registrationManager
                    );

                    break;

                // ================= EXIT =================
                case 3:

                    System.out.println(
                        "\nThank you for using the system!"
                    );

                    sc.close();

                    return;

                default:

                    System.out.println(
                        "Invalid choice. Please try again."
                    );
            }
        }
    }

    // =====================================================
    // STUDENT LOGIN
    // =====================================================

    public static void studentLogin(
            Scanner sc,
            RegistrationManager registrationManager,
            EventManager eventManager) {

        sc.nextLine();

        System.out.println(
            "\n========== STUDENT LOGIN =========="
        );

        System.out.print(
            "Enter Student ID: "
        );

        String studentId =
            sc.nextLine().trim();

        if (studentId.isEmpty()) {

            System.out.println(
                "Student ID cannot be empty."
            );

            return;
        }

        System.out.print(
            "Enter Student Name: "
        );

        String studentName =
            sc.nextLine().trim();

        if (studentName.isEmpty()) {

            System.out.println(
                "Student name cannot be empty."
            );

            return;
        }

        Student student =
            new Student(studentName, studentId);

        System.out.println(
            "\nWelcome, " + student.getName() + "!"
        );

        studentMenu(
            sc,
            student,
            registrationManager,
            eventManager
        );
    }

    // =====================================================
    // STUDENT MENU
    // =====================================================

    public static void studentMenu(
            Scanner sc,
            Student student,
            RegistrationManager registrationManager,
            EventManager eventManager) {

        while (true) {

            System.out.println(
                "\n=============================="
            );

            System.out.println(
                "       STUDENT MENU"
            );

            System.out.println(
                "=============================="
            );

            System.out.println("1. View Events");
            System.out.println("2. Register Event");
            System.out.println("3. View Registrations");
            System.out.println("4. Cancel Registration");
            System.out.println("5. Logout");

            int studentChoice =
                readInt(sc, "Enter choice: ");

            switch (studentChoice) {

                // -------- VIEW EVENTS --------
                case 1:

                    eventManager.viewEvents();

                    break;

                // -------- REGISTER EVENT --------
                case 2:

                    sc.nextLine();

                    System.out.print(
                        "Enter Event ID: "
                    );

                    String eventId =
                        sc.nextLine().trim();

                    registrationManager.registerStudent(
                        student.getId(),
                        eventId,
                        eventManager
                    );

                    break;

                // -------- VIEW REGISTRATIONS --------
                case 3:

                    registrationManager.viewRegistrations(
                        student.getId()
                    );

                    break;

                // -------- CANCEL REGISTRATION --------
                case 4:

                    sc.nextLine();

                    System.out.print(
                        "Enter Registration ID to cancel: "
                    );

                    String registrationId =
                        sc.nextLine().trim();

                    registrationManager
                        .cancelRegistration(
                            registrationId
                        );

                    break;

                // -------- LOGOUT --------
                case 5:

                    System.out.println(
                        "Logging out..."
                    );

                    return;

                default:

                    System.out.println(
                        "Invalid choice."
                    );
            }
        }
    }

    // =====================================================
    // ADMIN LOGIN
    // =====================================================

    public static void adminLogin(
            Scanner sc,
            EventManager eventManager,
            RegistrationManager registrationManager) {

        sc.nextLine();

        System.out.println(
            "\n========== ADMIN LOGIN =========="
        );

        System.out.print(
            "Enter Admin ID: "
        );

        String adminId =
            sc.nextLine().trim();

        System.out.print(
            "Enter Password: "
        );

        String password =
            sc.nextLine().trim();

        // Default admin credentials
        if (adminId.equals("admin")
                && password.equals("admin123")) {

            Admin admin =
                new Admin("Administrator", adminId);

            System.out.println(
                "\nWelcome, " + admin.getName() + "!"
            );

            adminMenu(
                sc,
                eventManager,
                registrationManager
            );

        } else {

            System.out.println(
                "\nInvalid Admin ID or Password."
            );
        }
    }

    // =====================================================
    // ADMIN MENU
    // =====================================================

    public static void adminMenu(
            Scanner sc,
            EventManager eventManager,
            RegistrationManager registrationManager) {

        while (true) {

            System.out.println(
                "\n=============================="
            );

            System.out.println(
                "         ADMIN MENU"
            );

            System.out.println(
                "=============================="
            );

            System.out.println("1. Add Event");
            System.out.println("2. Update Event");
            System.out.println("3. Delete Event");
            System.out.println("4. View Participants");
            System.out.println("5. View All Events");
            System.out.println("6. Logout");

            int adminChoice =
                readInt(sc, "Enter choice: ");

            switch (adminChoice) {

                // -------- ADD EVENT --------
                case 1:

                    addEvent(sc, eventManager);

                    break;

                // -------- UPDATE EVENT --------
                case 2:

                    updateEvent(sc, eventManager);

                    break;

                // -------- DELETE EVENT --------
                case 3:

                    deleteEvent(sc, eventManager);

                    break;

                // -------- VIEW PARTICIPANTS --------
                case 4:

                    sc.nextLine();

                    System.out.print(
                        "Enter Event ID: "
                    );

                    String participantEventId =
                        sc.nextLine().trim();

                    if (!eventManager.eventExists(
                            participantEventId)) {

                        System.out.println(
                            "Event not found."
                        );

                    } else {

                        registrationManager
                            .viewParticipants(
                                participantEventId
                            );
                    }

                    break;

                // -------- VIEW EVENTS --------
                case 5:

                    eventManager.viewEvents();

                    break;

                // -------- LOGOUT --------
                case 6:

                    System.out.println(
                        "Logging out..."
                    );

                    return;

                default:

                    System.out.println(
                        "Invalid choice."
                    );
            }
        }
    }

    // =====================================================
    // ADD EVENT
    // =====================================================

    public static void addEvent(
            Scanner sc,
            EventManager eventManager) {

        sc.nextLine();

        System.out.print(
            "Enter Event ID: "
        );

        String eventId =
            sc.nextLine().trim();

        if (eventId.isEmpty()) {

            System.out.println(
                "Event ID cannot be empty."
            );

            return;
        }

        System.out.print(
            "Enter Event Name: "
        );

        String eventName =
            sc.nextLine().trim();

        if (eventName.isEmpty()) {

            System.out.println(
                "Event name cannot be empty."
            );

            return;
        }

        System.out.print(
            "Enter Date: "
        );

        String date =
            sc.nextLine().trim();

        if (date.isEmpty()) {

            System.out.println(
                "Date cannot be empty."
            );

            return;
        }

        System.out.print(
            "Enter Venue: "
        );

        String venue =
            sc.nextLine().trim();

        if (venue.isEmpty()) {

            System.out.println(
                "Venue cannot be empty."
            );

            return;
        }

        int capacity =
            readInt(sc, "Enter Capacity: ");

        if (capacity <= 0) {

            System.out.println(
                "Capacity must be greater than zero."
            );

            return;
        }

        Event event = new Event(
            eventId,
            eventName,
            date,
            venue,
            capacity
        );

        eventManager.addEvent(event);
    }

    // =====================================================
    // UPDATE EVENT
    // =====================================================

    public static void updateEvent(
            Scanner sc,
            EventManager eventManager) {

        sc.nextLine();

        System.out.print(
            "Enter Event ID to update: "
        );

        String updateId =
            sc.nextLine().trim();

        if (!eventManager.eventExists(updateId)) {

            System.out.println(
                "Event not found."
            );

            return;
        }

        System.out.print(
            "Enter new Event Name: "
        );

        String updateName =
            sc.nextLine().trim();

        System.out.print(
            "Enter new Date: "
        );

        String updateDate =
            sc.nextLine().trim();

        System.out.print(
            "Enter new Venue: "
        );

        String updateVenue =
            sc.nextLine().trim();

        int updateCapacity =
            readInt(
                sc,
                "Enter new Capacity: "
            );

        eventManager.updateEvent(
            updateId,
            updateName,
            updateDate,
            updateVenue,
            updateCapacity
        );
    }

    // =====================================================
    // DELETE EVENT
    // =====================================================

    public static void deleteEvent(
            Scanner sc,
            EventManager eventManager) {

        sc.nextLine();

        System.out.print(
            "Enter Event ID to delete: "
        );

        String deleteId =
            sc.nextLine().trim();

        eventManager.deleteEvent(deleteId);
    }

    // =====================================================
    // SAFE INTEGER INPUT
    // =====================================================

    public static int readInt(
            Scanner sc,
            String message) {

        while (true) {

            System.out.print(message);

            String input =
                sc.nextLine().trim();

            try {

                return Integer.parseInt(input);

            } catch (NumberFormatException e) {

                System.out.println(
                    "Please enter a valid number."
                );
            }
        }
    }
}