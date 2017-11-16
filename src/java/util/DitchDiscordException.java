
package util;

public class DitchDiscordException extends RuntimeException {
    public DitchDiscordException() {
    }
    public DitchDiscordException(Throwable ex) {
        super(ex);
    }
    public DitchDiscordException(String message, Throwable ex) {
        super(message, ex);
    }
}
