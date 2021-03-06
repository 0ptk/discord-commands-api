package pl.ptk.commands;

import java.util.Arrays;
import java.util.List;

public class CommandConfig {
  //variables in all messages:
  // - %member_name%
  // - %member_with_tag%
  // - %member_id%
  // - %guild_name%
  // - %guild_id%

  public static final String PREFIX = "!"; //prefix
  public static final String OWNER_SERVER_ID = "123"; //your server id
  public static final List<String> OWNERS_IDS = Arrays.asList("123", "321", "3234"); //owners ids
  public static final String MESSAGE_ONLY_OWNER_SERVER = "This command allowed only in owner server!";
  public static final String MESSAGE_NOT_HAVE_PERMISSIONS = "You are not have permissions! Permissions: `%permissions%`";
  public static final String MESSAGE_USAGE = "Usage: `%usage%`";
  public static final String MESSAGE_ONLY_OWNERS = "This command is only for bot owners!";
}
