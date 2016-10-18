package scripts.JkgAPI.Framework;

public interface Task {

    int priority();

    boolean validate();

    void execute();

}