package Java;

public class Claim {
    private int id;
    private String dateTime;
    private Employer implementer;
    private Employer sender;
    private Status status;
    private String description;

    public Claim(int id, String dateTime, Employer implementer, Employer sender, Status status, String description){
        this.id = id;
        this.dateTime = dateTime;
        this.implementer = implementer;
        this.sender = sender;
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

    public void setImplementer(Employer implementer) {
        this.implementer = implementer;
    }

    public Employer getImplementer() {
        return implementer;
    }

    public void setSender(Employer sender) {
        this.sender = sender;
    }

    public Employer getSender() {
        return sender;
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


}
