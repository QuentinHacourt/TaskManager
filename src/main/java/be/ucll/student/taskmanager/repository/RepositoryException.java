package be.ucll.student.taskmanager.repository;

public class RepositoryException extends RuntimeException{
    
    private static final long serialVersionUID = 1L;

    public RepositoryException(String message) {
        super(message);
    }
}