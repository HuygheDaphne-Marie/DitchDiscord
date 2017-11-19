
package domain;

import java.time.Instant;

public class LogEntry {
    private String message;
    private int id;

    public LogEntry(String message, int id) {
        this.message = message;
        this.id = id;
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
}
