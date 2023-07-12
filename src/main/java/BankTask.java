import org.powbot.api.Condition;
import org.powbot.api.rt4.*;

public class BankTask extends Task {
    @Override
    public boolean ShouldActivate() {

        if (!ItemUtil.GotItems() && GroundItems.stream().id(Static.SelectedArea.ItemToLoot).isEmpty() && Npcs.stream().interactingWithMe().isEmpty() && Static.FinishedLooting) {
            System.out.println("Activate BankTask");
            return true;
        }
        return false;
    }

    @Override
    public boolean Execute() {
        Movement.moveToBank();
        Bank.open();
        Condition.wait(() -> Bank.opened(), 10, 300);
        Bank.depositInventory();
        if(!Equipment.itemAt(Equipment.Slot.RING).valid())
        {
            if(!Bank.withdraw(2552, 1)){
                return false;
            }
            Condition.wait(() -> Inventory.stream().id(2552).isNotEmpty(), 10, 150);
            if(!Inventory.stream().id(2552).first().interact("Wear")){
                return false;
            }

        }
        Bank.withdraw(223, 14);
        Bank.withdraw(327, 14);
        Bank.close();
        Condition.wait(() -> !Bank.opened(), 10, 150);
        return true;
    }
}
