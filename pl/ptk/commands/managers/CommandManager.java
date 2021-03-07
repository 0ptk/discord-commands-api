package pl.ptk.commands.managers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import pl.ptk.commands.commands.Command;

public class CommandManager {
  private final List<Command> commands = new ArrayList<>();
  public void registerCommand(Command command) {
    commands.add(command);
  }

  public Optional<Command> getCommand(final String arg) {
    return commands
        .stream()
        .filter(command -> command.getCommandInfo() != null && command.getCommandInfo().name().equalsIgnoreCase(arg) || Arrays.asList(command.getCommandInfo().aliases()).contains(arg.toLowerCase()))
        .findFirst();
  }
}