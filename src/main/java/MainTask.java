public class MainTask extends Task {

    public MainTask(){
        Children.add(new BankTask());
        Children.add(new WalkToBasementTask());
        Children.add(new KillTask());
    }

    @Override
    public boolean ShouldActivate(){
        return true;
    }

    @Override
    public boolean Execute(){
        return Execute(Children);
    }
}
