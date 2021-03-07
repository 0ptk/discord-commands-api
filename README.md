# Discord Commands Api
[<img src="https://img.shields.io/badge/ptk%238135-%237289DA.svg?&logo=discord&logoColor=white" />](https://discord.com/)
[<img src="https://img.shields.io/github/release/0ptk/discord-commands-api" />](https://github.com/0ptk/discord-commands-api/releases/)
[<img src="https://img.shields.io/github/downloads/0ptk/discord-commands-api/total" />](https://github.com/0ptk/discord-commands-api/releases/)
* If you have a bug or suggestion, report to me on discord <b>ptk#8135</b>

## 📖 Example

### Example Main class
```java
public class DiscordBot {
  private static final CommandManager commandManager = new CommandManager();
  public static void main(String[] args) {
    commandManager.registerCommand(new ExampleCommand());
    try {
      JDABuilder builder = JDABuilder.create(
          "token",
          GatewayIntent.GUILD_MEMBERS,
          GatewayIntent.GUILD_MESSAGES
      );

      builder.addEventListeners(new CommandListener(commandManager));
      builder.build();
    } catch (LoginException e) {
      e.printStackTrace();
    }
  }

  public CommandManager getCommandManager() {
    return commandManager;
  }
}
```

### Example Command
```java
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

```

## 🔧 Code

### Registry command in main:
```java
commandManager.registerCommand(new ExampleCommand());
```

### To bot working you must register listener in main
##### builder = JDABuilder
```java
builder.addEventListeners(new CommandListener());
```

### JDA Maven

```xml
<repositories>
  <repository>
    <id>jcenter</id>
    <name>jcenter-bintray</name>
    <url>https://jcenter.bintray.com</url>
  </repository>
</repositories>

<dependencies>
  <dependency>
    <groupId>net.dv8tion</groupId>
    <artifactId>JDA</artifactId>
    <version>4.2.0_227</version>
  </dependency>
</dependencies>
```