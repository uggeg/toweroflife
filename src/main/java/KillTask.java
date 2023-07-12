import org.powbot.api.Condition;
import org.powbot.api.Notifications;
import org.powbot.api.rt4.GroundItems;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Objects;
import org.powbot.api.rt4.Players;

public class KillTask extends Task {

    public KillTask() {
        Children.add(new ActivateAltarTask());
        Children.add(new LootTask());
    }

    @Override
    public boolean ShouldActivate() {
        if (Static.SelectedArea.AreaInBasement.contains(Players.local()) || Static.SelectedArea.AreaInBasement.getClosestTo(Players.local()).distance() < 10) {
            return true;
        }
        return false;
    }

    @Override
    public boolean Execute() {
        return Execute(Children);
    }
}
