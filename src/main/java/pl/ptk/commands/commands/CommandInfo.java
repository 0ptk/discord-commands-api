package pl.ptk.commands.commands;

import java.util.Arrays;
import java.util.List;
import net.dv8tion.jda.api.Permission;

public class CommandInfo {
  private final String name;
  private final String usage;
  private final int minArguments;
  private final List<String> aliases;
  private final List<Permission> permissions;
  private final boolean onlyOwners;
  private final boolean onlySupportedServer;

  public CommandInfo(String name, String usage, int minArguments, String[] aliases, boolean onlyOwners, boolean onlySupportedServer, Permission... permissions) {
    this.name = name;
    this.usage = usage;
    this.minArguments = minArguments;
    this.aliases = Arrays.asList(aliases);
    this.onlyOwners = onlyOwners;
    this.onlySupportedServer = onlySupportedServer;
    this.permissions = Arrays.asList(permissions);
  }

  public int getMinArguments() {
    return minArguments;
  }

  public String getUsage() {
    return usage;
  }

  public List<Permission> getPermissions() {
    return permissions;
  }

  public boolean isOnlyOwner() {
    return onlyOwners;
  }

  public boolean isOnlySupportedServer() {
    return onlySupportedServer;
  }

  public List<String> getAliases() {
    return aliases;
  }

  public String getName() {
    return name;
  }
}