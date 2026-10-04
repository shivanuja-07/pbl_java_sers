public class Registration {

    private String registrationId;
    private String studentId;
    private String eventId;

    public Registration(String registrationId,
                        String studentId,
                        String eventId) {

        this.registrationId = registrationId;
        this.studentId = studentId;
        this.eventId = eventId;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getEventId() {
        return eventId;
    }
}