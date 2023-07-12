import Config.SelectedArea;
import org.powbot.api.Area;
import org.powbot.api.Tile;

public class Static {
    public static SelectedArea SelectedArea;
    public static boolean FinishedLooting = false;
    public static Area TowerOfLifeBasementArea = new Area(new Tile(3006, 4414), new Tile(3070, 4351));

    public static boolean LookForSpawnedMob = false;
}
