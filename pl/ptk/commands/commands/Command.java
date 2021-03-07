package pl.ptk.commands.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public abstract class Command {
    public CommandInfo getCommandInfo() {
        return this.getClass().isAnnotationPresent(CommandInfo.class) ? this.getClass().getDeclaredAnnotation(CommandInfo.class) : null;
    }

    public abstract void execute(MessageReceivedEvent event, String... arguments);
}