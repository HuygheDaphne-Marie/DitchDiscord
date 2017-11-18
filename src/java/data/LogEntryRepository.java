
package data;

import domain.LogEntry;
import java.util.List;

public interface LogEntryRepository {
    public void addToDataBase(LogEntry log);
    public List<LogEntry> getAllFromDataBase();
}
