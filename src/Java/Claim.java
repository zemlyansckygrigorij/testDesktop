package Java;

import java.time.LocalDateTime;

public class Claim {
    private int id;
    private String dateTime;
    private Person implementer;
    private Person sender;
    private Status status;
    private String description;

    public Claim(int id, String dateTime, Person implementer, Person sender, Status status,String description){
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

    public void setImplementer(Person implementer) {
        this.implementer = implementer;
    }

    public Person getImplementer() {
        return implementer;
    }

    public void setSender(Person sender) {
        this.sender = sender;
    }

    public Person getSender() {
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
