import java.util.ArrayList;

public class Task {
    public String Name;
    public ArrayList<Task> Children = new ArrayList<Task>();

    public Task(){
        Name = this.getClass().getSimpleName().replace("Task", "");
    }

    public boolean ShouldActivate(){
        return true;
    }

    public boolean Execute(){
        return true;
    }

    public boolean Execute(ArrayList<Task> tasks){
        for (var task: tasks){
            if(!task.ShouldActivate()){
                continue;
            }
            task.Execute();
        }
        return true;
    }
}