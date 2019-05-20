package Java.objects;

public class Claim {
    private int id;
    private String dateTime;
    private Employer employer;
    private Client client;
    private Status status;
    private String description;

    public Claim(int id, String dateTime, Employer employer, Client client, Status status, String description){
        this.id = id;
        this.dateTime = dateTime;
        this.employer = employer;
        this.client = client;
        this.status = status;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int valueOf(){
        return id;
    }

}
