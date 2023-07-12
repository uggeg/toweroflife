import org.powbot.api.rt4.Inventory;

public class ItemUtil {
    public static boolean GotItems() {
        var gotItem1 = Inventory.stream().id(Static.SelectedArea.ItemId1).count() > 0;
        var gotItem2 = Inventory.stream().id(Static.SelectedArea.ItemId2).count() > 0;
        if (gotItem1 && gotItem2) {
            return true;
        }
        return false;
    }
}
