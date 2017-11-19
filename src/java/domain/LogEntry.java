
package domain;

public class LogEntry {
    private String message;
    private int id;
    private String timeStamp;
    
    public LogEntry(String message, int id, String timeStamp) {
        this.message = message;
        this.id = id;
        this.timeStamp = timeStamp;
    }

    public LogEntry(String message, int id) {
        this(message, id, null);
    }
    
    public LogEntry(String message) {
        this(message, -1);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
<<<<<<< HEAD
=======

    public String getTimeStamp() {
        return timeStamp;
    }
    
    
>>>>>>> master
}
