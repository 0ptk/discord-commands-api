package pl.ptk.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import pl.ptk.commands.commands.Command;
import pl.ptk.commands.commands.CommandInfo;

public class ExampleCommand implements Command {
  @Override
  public CommandInfo getInfo() {
    return new CommandInfo(
        "help", //name
        CommandConfig.PREFIX + "help <page>", //usage
        2, //min arguments
        new String[]{"help2", "help3"}, // aliases
        true, //only for owners
        false, //only ur server
        Permission.VIEW_CHANNEL, Permission.BAN_MEMBERS); //permissions
    //no permissions = replace permissions to: new Permission[]{});
  }

  @Override
  public void handle(MessageReceivedEvent event, String... args) {
    TextChannel textChannel = event.getTextChannel();
    if (args[1].equals("1")) {
      textChannel.sendMessage("1").complete();
    } else {
      textChannel.sendMessage("2").complete();
    }
  }
}
