package fr.brangers.swtdrrp.register;

import fr.brangers.swtdrrp.Main;
import fr.brangers.swtdrrp.commands.TestCommand;

public class Register {
    public static void registerCommands(Main main) {
    main.getCommand("test").setExecutor(new TestCommand());
    }
}
