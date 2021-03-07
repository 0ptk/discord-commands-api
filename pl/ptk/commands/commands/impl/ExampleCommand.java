package pl.ptk.commands.commands.impl;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import pl.ptk.commands.CommandConfig;
import pl.ptk.commands.commands.Command;
import pl.ptk.commands.commands.CommandInfo;

@CommandInfo(
    name = "help",
    usage = CommandConfig.PREFIX + "help <page>",
    minArguments = 2,
    aliases = {"help1", "help2"},
    onlyOwners = false,
    onlySupportedServer = false,
    permissions = {Permission.ADMINISTRATOR, Permission.BAN_MEMBERS}
)
public class ExampleCommand extends Command {
  @Override
  public void execute(MessageReceivedEvent event, String... arguments) {
    TextChannel textChannel = event.getTextChannel();
    if (arguments[1].equals("1")) {
      textChannel.sendMessage("1").complete();
    } else {
      textChannel.sendMessage("2").complete();
    }
  }
}
