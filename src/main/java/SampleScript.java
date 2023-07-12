import com.google.common.eventbus.Subscribe;
import org.powbot.api.Area;
import org.powbot.api.Condition;
import org.powbot.api.Tile;
import org.powbot.api.event.MessageEvent;
import org.powbot.api.rt4.*;
import org.powbot.api.script.*;

// Example of GUI option which asks for a string
//@ScriptConfiguration(name = "NPC", description = "NPC to fight", optionType = OptionType.STRING)
//@ScriptConfiguration(name = "Example GUI option", description = "Example GUI option", optionType = OptionType.STRING, visible = false)

@ScriptManifest(name = "Example Script", description = "Example Script", version = "1")
public class SampleScript extends AbstractScript {

    private String GuiNPC; // Creates an empty

    @Override
    public void onStart() {
        // Logic to happen before poll() starts
//        boolean GuiNPC = getOption("NPC"); // Gets value of specific GUI key and saves it in a variable.

        Static.SelectedArea = Constants.AreasToLoot.Spidine;
    }

    public Task MainTask = new MainTask();
    @Override
    public void poll() {
        MainTask.Execute();
        // Looped tasks go here. Example below is of a simple fighter script

//        if (!TowerOfLifeBasementArea.contains(Players.local())) {
//            Movement.walkTo(new Tile(2648, 3214, 0));
//            var trapDoor = Objects.stream().name("Trapdoor").first();
//            if(trapDoor.valid() && trapDoor.inViewport()){
//                trapDoor.interact("Climb-down");
//                Condition.wait(() -> TowerOfLifeBasementArea.contains(Players.local()), 10, 5000);
//            }
//        }else if(TowerOfLifeBasementArea.contains(Players.local()) && !Players.local().interacting().valid()){
//            System.out.println("In area and ready for combat");
//            if(Inventory.stream().id(223).count() > 0 && Inventory.stream().id(327).count() > 0){
//                System.out.println("Got more spider&sardines than 0");
//            }else{
//                Movement.moveToBank();
//                Bank.open();
//                Condition.wait(() -> Bank.opened(), 10, 300);
//                Bank.depositInventory();
//                Bank.withdraw(223, 14);
//                Bank.withdraw(327, 14);
//                Bank.close();
//                Condition.wait(() -> !Bank.opened(), 10, 150);
//            }
//        }
//        if(!Players.local().interacting().valid()) { // Check for if you're not interacting with an NPC. Else skips what's below and keeps looping until you are not interacting with an NPC.
//            Npc fightNpc = Npcs.stream().name(GuiNPC).filter(it -> !it.interacting().valid()).nearest().first(); // Gets the nearest NPC that's not interacting with anyone else / is not in combat
//            if(fightNpc.valid() && fightNpc.interact("Attack")) { // Checks if NPC is valid and then interacts with it
//                Condition.wait(() -> Players.local().interacting().valid(), 200, 200); // If above was successful, waits until you are interacting with the NPC
//            }
//        }
    }

    @Subscribe
    public void messaged(MessageEvent messageEvent) {
        var message = messageEvent.getMessage().toLowerCase();
        if(message.contains("already have a creature")){
            Static.LookForSpawnedMob = true;
        }
    }

    @ValueChanged(keyName = "NPC") // Listens for changes to the specific GUI config key.
    private void exampleConfigChanged(Boolean valueChanged) {
        updateVisibility("Example GUI option", true); // Changes visibility of the second GUI config key if another key is modified
    }

    public static void main(String[] args) {
        // Start your script with this function. Make sure your device is connected via ADB, and only one is connected
        new SampleScript().startScript();
    }
}
