
package domain;

import java.time.Instant;

public class LogEntry {
    private long timeStamp;
    private String message;
    private int id;

    public LogEntry(long timeStamp, String message, int id) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.id = id;
    }

    public LogEntry(String message) {
        this(Instant.now().getEpochSecond(), message, -1);
    }
   
    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
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
    
    
}
