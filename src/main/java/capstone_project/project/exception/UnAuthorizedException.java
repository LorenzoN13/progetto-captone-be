package capstone_project.project.exception;

public class UnAuthorizedException extends RuntimeException{

    public UnAuthorizedException(String msg){
        super(msg);
    }
}
