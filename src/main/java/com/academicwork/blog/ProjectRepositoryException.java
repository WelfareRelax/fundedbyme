package com.academicwork.blog;

public class ProjectRepositoryException extends RuntimeException {

    public ProjectRepositoryException() {
    }

    public ProjectRepositoryException(String message) {
        super(message);
    }

    public ProjectRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProjectRepositoryException(Throwable cause) {
        super(cause);
    }

    public ProjectRepositoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
