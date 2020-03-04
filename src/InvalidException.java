public class InvalidException extends Exception{
    String message;

    public InvalidException() {
    }

    public InvalidException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
