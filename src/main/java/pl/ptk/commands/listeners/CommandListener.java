package pl.ptk.commands.listeners;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import pl.ptk.commands.CommandConfig;
import pl.ptk.commands.commands.CommandInfo;
import pl.ptk.commands.managers.CommandManager;

public class CommandListener extends ListenerAdapter {

  @Override
  public void onMessageReceived(MessageReceivedEvent event) {
    String[] arguments = event.getMessage().getContentDisplay().split(" ");
    Member member = event.getMember();
    if (!arguments[0].startsWith(CommandConfig.PREFIX) && event.getAuthor().isBot()) {
      return;
    }

    try {
      CommandManager.getCommand(arguments[0].split(CommandConfig.PREFIX)[1])
          .ifPresent(command -> {
            if (member == null) {
              return;
            }

            CommandInfo info = command.getInfo();
            if (info.isOnlySupportedServer() && !event.getGuild().getId().equalsIgnoreCase(CommandConfig.OWNER_SERVER_ID)) {
              String message = CommandConfig.MESSAGE_ONLY_OWNER_SERVER
                  .replace("%member_name%", member.getEffectiveName())
                  .replace("%member_with_tag%", member.getUser().getAsTag())
                  .replace("%member_id%", member.getId())
                  .replace("%guild_name%", event.getGuild().getName())
                  .replace("%guild_id%", event.getGuild().getId());
              event.getTextChannel().sendMessage(message).complete();
              return;
            }

            if (info.isOnlyOwner() && !CommandConfig.OWNERS_IDS.contains(member.getId())) {
              String message = CommandConfig.MESSAGE_ONLY_OWNERS
                  .replace("%member_name%", member.getEffectiveName())
                  .replace("%member_with_tag%", member.getUser().getAsTag())
                  .replace("%member_id%", member.getId())
                  .replace("%guild_name%", event.getGuild().getName())
                  .replace("%guild_id%", event.getGuild().getId());
              event.getTextChannel().sendMessage(message).complete();
              return;
            }

            List<Permission> permissions = info.getPermissions();
            String requiredPermissions = permissions.toString().replace("[", "").replace("]", "");
            if (!member.getPermissions().isEmpty() && !member.getPermissions().containsAll(permissions)) {
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

            if (arguments.length < info.getMinArguments()) {
              String message = CommandConfig.MESSAGE_USAGE
                  .replace("%member_name%", member.getEffectiveName())
                  .replace("%member_with_tag%", member.getUser().getAsTag())
                  .replace("%member_id%", member.getId())
                  .replace("%guild_name%", event.getGuild().getName())
                  .replace("%guild_id%", event.getGuild().getId())
                  .replace("%usage%", info.getUsage());
              event.getTextChannel().sendMessage(message).complete();
              return;
            }

            command.handle(event, arguments);
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
