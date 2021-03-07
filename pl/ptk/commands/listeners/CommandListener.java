package pl.ptk.commands.listeners;

import java.util.Arrays;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import pl.ptk.commands.CommandConfig;
import pl.ptk.commands.commands.CommandInfo;
import pl.ptk.commands.managers.CommandManager;

public class CommandListener extends ListenerAdapter {
  private final CommandManager commandManager;
  public CommandListener(CommandManager commandManager) {
    this.commandManager = commandManager;
  }

  @Override
  public void onMessageReceived(MessageReceivedEvent event) {
    String[] arguments = event.getMessage().getContentDisplay().split(" ");
    Member member = event.getMember();
    if (!arguments[0].startsWith(String.valueOf(CommandConfig.PREFIX)) && event.getAuthor().isBot()) {
      return;
    }

    try {
      commandManager.getCommand(arguments[0].split(String.valueOf(CommandConfig.PREFIX))[1])
          .ifPresent(command -> {
            if (member == null) {
              return;
            }

            CommandInfo info = command.getCommandInfo();
            if (info.onlySupportedServer() && !event.getGuild().getId().equalsIgnoreCase(CommandConfig.OWNER_SERVER_ID)) {
              String message = CommandConfig.MESSAGE_ONLY_OWNER_SERVER
                  .replace("%member_name%", member.getEffectiveName())
                  .replace("%member_with_tag%", member.getUser().getAsTag())
                  .replace("%member_id%", member.getId())
                  .replace("%guild_name%", event.getGuild().getName())
                  .replace("%guild_id%", event.getGuild().getId());
              event.getTextChannel().sendMessage(message).complete();
              return;
            }

            if (info.onlyOwners() && !CommandConfig.OWNERS_IDS.contains(member.getId())) {
              String message = CommandConfig.MESSAGE_ONLY_OWNERS
                  .replace("%member_name%", member.getEffectiveName())
                  .replace("%member_with_tag%", member.getUser().getAsTag())
                  .replace("%member_id%", member.getId())
                  .replace("%guild_name%", event.getGuild().getName())
                  .replace("%guild_id%", event.getGuild().getId());
              event.getTextChannel().sendMessage(message).complete();
              return;
            }

            Permission[] permissions = info.permissions();
            String requiredPermissions = Arrays.toString(permissions).replace("[", "").replace("]", "");
            if (!member.getPermissions().isEmpty() && !member.getPermissions().containsAll(Arrays.asList(permissions))) {
              String message = CommandConfig.MESSAGE_NOT_HAVE_PERMISSIONS
                  .replace("%member_name%", member.getEffectiveName())
                  .replace("%member_with_tag%", member.getUser().getAsTag())
                  .replace("%member_id%", member.getId())
                  .replace("%guild_name%", event.getGuild().getName())
                  .replace("%guild_id%", event.getGuild().getId())
                  .replace("%permissions%", requiredPermissions);
              event.getTextChannel().sendMessage(message).complete();
              return;
            }

            if (arguments.length < info.minArguments()) {
              String message = CommandConfig.MESSAGE_USAGE
                  .replace("%member_name%", member.getEffectiveName())
                  .replace("%member_with_tag%", member.getUser().getAsTag())
                  .replace("%member_id%", member.getId())
                  .replace("%guild_name%", event.getGuild().getName())
                  .replace("%guild_id%", event.getGuild().getId())
                  .replace("%usage%", info.usage());
              event.getTextChannel().sendMessage(message).complete();
              return;
            }

            command.execute(event, arguments);
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}