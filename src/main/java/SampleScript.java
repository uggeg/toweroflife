import org.powbot.api.script.*;

@ScriptConfiguration(name = "Example GUI option", description = "Example GUI option", optionType = OptionType.BOOLEAN)
@ScriptConfiguration(name = "Example GUI option 2", description = "Example GUI option", optionType = OptionType.BOOLEAN, visible = false)

@ScriptManifest(name = "Example Script", description = "Example Script", version = "1")
public class SampleScript extends AbstractScript {
    @Override
    public void onStart() {
        // Logic to happen before poll() starts
        boolean exampleGuiOption = getOption("Example GUI option"); // Gets value of specific GUI key and saves it in a variable.
    }

    @Override
    public void poll() {
        // Enter your looped tasks here
    }

    public static void main(String[] args) {
        // Start your script with this function.
        new SampleScript().startScript();
    }

    @ValueChanged(keyName = "Example GUI option") // Listens for changes to the specific GUI config key.
    private void exampleConfigChanged(Boolean valueChanged) {
        updateVisibility("Example GUI option 2", true); // Changes visibility of the second GUI config key
    }
}
