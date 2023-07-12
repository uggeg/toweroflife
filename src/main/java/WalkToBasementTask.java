import org.powbot.api.Condition;
import org.powbot.api.Tile;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Objects;
import org.powbot.api.rt4.Players;

public class WalkToBasementTask extends Task {
    @Override
    public boolean ShouldActivate() {
        if (ItemUtil.GotItems() && Static.SelectedArea.AreaInBasement.getClosestTo(Players.local()).distance() > 10) {
            System.out.println("Activate WalkToBasementTask");
            return true;
        }
        return false;
    }

    @Override
    public boolean Execute() {
        if (!Movement.walkTo(new Tile(2648, 3214, 0))) {
            return false;
        }
        var trapDoor = Objects.stream().name("Trapdoor").first();
        if (!trapDoor.valid() || !trapDoor.inViewport()) {
            return false;
        }
        if (!trapDoor.interact("Climb-down")) {
            return false;
        }
        Condition.wait(() -> Static.TowerOfLifeBasementArea.contains(Players.local()), 10, 500);
        if(!Movement.moveTo(Static.SelectedArea.AreaInBasement.getCentralTile()).getSuccess()){
            return false;
        }
        Condition.wait(() -> Static.SelectedArea.AreaInBasement.contains(Players.local()), 10, 300);
        return true;
    }
}
