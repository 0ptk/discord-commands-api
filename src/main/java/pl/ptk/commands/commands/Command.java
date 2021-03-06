package pl.ptk.commands.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface Command {

  CommandInfo getInfo();

  void handle(MessageReceivedEvent event, String... args);
}