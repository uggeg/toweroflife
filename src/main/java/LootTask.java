import org.powbot.api.Condition;
import org.powbot.api.rt4.GroundItems;
import org.powbot.api.rt4.Players;

public class LootTask extends Task {
    @Override
    public boolean ShouldActivate() {
        if (GroundItems.stream().id(Static.SelectedArea.ItemToLoot).isNotEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean Execute() {
        Static.FinishedLooting = true;
        var item = GroundItems.stream().id(Static.SelectedArea.ItemToLoot).first();
        if (!item.valid()) {
            return false;
        }
        var invCount = GroundItems.stream().id(item.id()).at(item.tile()).count();
        if (!item.interact("Take")) {
            return false;
        }
        Condition.wait(() -> GroundItems.stream().id(item.id()).at(item.tile()).count() < invCount, 10, 350);

        var bone = GroundItems.stream().name("Bones").at(item.tile()).first();
        var invCount2 = GroundItems.stream().id(item.id()).at(item.tile()).count();
        if (!bone.valid() || !bone.interact("Take")) {
            return false;
        }
        Condition.wait(() -> GroundItems.stream().id(bone.id()).at(bone.tile()).count() < invCount2, 10, 350);

        return true;
    }
}
