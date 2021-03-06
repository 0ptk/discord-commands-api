package pl.ptk.commands.managers;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import pl.ptk.commands.commands.Command;

public class CommandManager {
  private static final Map<String, Command> commandMap = new ConcurrentHashMap<>();
  public static void registerCommand(Command command) {
    commandMap.put(command.getInfo().getName(), command);
  }

  public static Optional<Command> getCommand(String arg) {
    return commandMap
        .values()
        .stream()
        .filter(command -> command.getInfo().getName().equals(arg) || command.getInfo().getAliases().contains(arg))
        .findFirst();
  }
}
