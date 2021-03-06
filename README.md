# Discord Commands Api
[<img src="https://img.shields.io/badge/ptk%238135-%237289DA.svg?&logo=discord&logoColor=white" />](https://discord.com/)
[<img src="https://img.shields.io/github/release/0ptk/discord-commands-api" />](https://github.com/0ptk/discord-commands-api/releases/)
[<img src="https://img.shields.io/github/downloads/0ptk/discord-commands-api/total" />](https://github.com/0ptk/discord-commands-api/releases/)
* If you have a bug or suggestion, report to me on discord <b>ptk#8135</b>

## 📖 Example

### Example Main class
```java
public class DiscordBot {
  public static void main(String[] args) {
    CommandManager.registerCommand(new ExampleCommand());
    try {
      JDABuilder builder = JDABuilder.create(
          "token",
          GatewayIntent.GUILD_MEMBERS,
          GatewayIntent.GUILD_MESSAGES
      );

      builder.addEventListeners(new CommandListener());
      builder.build();
    } catch (LoginException e) {
      e.printStackTrace();
    }
  }
}
```

### Example Command
```java
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
```

## 🔧 Code

### Registry command in main:
```java
CommandManager.registerCommand(new ExampleCommand());
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