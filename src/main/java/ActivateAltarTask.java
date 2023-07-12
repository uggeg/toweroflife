import org.powbot.api.Condition;
import org.powbot.api.Notifications;
import org.powbot.api.rt4.Npcs;
import org.powbot.api.rt4.Objects;
import org.powbot.api.rt4.Players;

public class ActivateAltarTask extends Task {
    @Override
    public boolean ShouldActivate() {
        var npcAttackingMe = Npcs.stream().interactingWithMe().name(Static.SelectedArea.MobName).first();
        if (npcAttackingMe.valid() || !ItemUtil.GotItems()) {
            Static.FinishedLooting = false;
            return false;
        }
        return true;
    }

    @Override
    public boolean Execute() {
        if (Static.LookForSpawnedMob) {
            var mob = Npcs.stream().name(Static.SelectedArea.MobName).filter(x -> !x.interacting().valid()).first();
            if (!mob.valid()) {
                Static.LookForSpawnedMob = false;
                return false;
            }
            if(!mob.interact("Attack")){
                return false;
            }
            Condition.wait(() -> mob.healthBarVisible(), 10, 250);
            Static.LookForSpawnedMob = false;
            return true;
        }

        var altar = Objects.stream().within(Static.SelectedArea.AreaInBasement).name("Symbol of life").first();
        if (!altar.valid()) {
            return false;
        }
        if (!altar.interact("Activate")) {
            return false;
        }
        Condition.wait(() -> Npcs.stream().interactingWithMe().first().valid(), 10, 500);
        if (Players.local().interacting().valid()) {
            Notifications.showNotification("I'm not getting attacked? :O");
            return false;
        }
        return true;
    }
}
