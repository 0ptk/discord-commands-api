package pl.ptk.commands.commands;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import net.dv8tion.jda.api.Permission;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE_USE)
public @interface CommandInfo {
  String name();

  String usage() default "";

  int minArguments() default 0;

  String[] aliases() default {};

  Permission[] permissions() default {};

  boolean onlyOwners() default false;

  boolean onlySupportedServer() default false;
}
