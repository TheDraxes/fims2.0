package de.hwr.fims_backend.dbconnector;

public class ResultBoolean {
    private boolean successful;
    private String message;



    public ResultBoolean() {
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
