package Config;

import org.powbot.api.Area;

public class SelectedArea {
    public String MobName;
    public Area AreaInBasement;
    public int ItemId1;
    public int ItemId2;
    public int ItemToLoot;

    public SelectedArea(String mobName, Area areaInBasement, int itemId1, int itemId2, int itemToLoot) {
        MobName = mobName;
        AreaInBasement = areaInBasement;
        ItemId1 = itemId1;
        ItemId2 = itemId2;
        ItemToLoot = itemToLoot;
    }
}
