package models;

public class InsertResponse {
    private boolean success;
    private int message;

    public InsertResponse() {
    }

    public InsertResponse(boolean success, int message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getMessage() {
        return message;
    }

    public void setMessage(int message) {
        this.message = message;
    }
}
