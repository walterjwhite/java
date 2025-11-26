import com.walterjwhite.shell.api.model.CommandOutput;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestUpower {
  public static void main(final String[] arguments) {
    /*
    final CommandOutput commandOutputMessage = CommandExecutionUtil.run("upower -i /org/freedesktop/UPower/devices/battery_BAT0 | grep -P '(state|percentage)'");// | awk {'print$1,$2'} | sed -e \"s/ //\" | grep -Pv \"^$\"");

    */

    /*
    final CommandOutput chainedCommandOutput = CommandExecutionUtil.runChained(new String[]{"upower -i /org/freedesktop/UPower/devices/battery_BAT0",
            "grep -P (state|percentage)", "awk {print$1,$2}"/ *, "sed -e s/ //\"",
            "grep -Pv ^$"* /});

    */

    CommandOutput commandOutput = null;
    processOutput(commandOutput);
  }

  private static void processOutput(CommandOutput commandOutput) {

    String state = null;
    int percentage = -1;
    for (String line : commandOutput.getOutput().split("\n")) {
      if (state != null && percentage > 0) {
        break;
      }

      processState(line);
      processPercentage(line);
    }
  }

  private static void processPercentage(final String line) {
    final Pattern percentagePattern =
        Pattern.compile(
            ".*percentage:[\\W]{1,}([\\d]{1,3})%.*"); // Pattern.compile("state:[\\w]{1,}(fully-charged|charging|discharging)");

    final Matcher percentageMatcher = percentagePattern.matcher(line);
    if (percentageMatcher.matches()) {
      final int percentage = Integer.valueOf(percentageMatcher.group(1));
    }
  }

  private static void processState(final String line) {
    final Pattern statePattern =
        Pattern.compile(
            ".*(fully-charged|charging|discharging).*"); // Pattern.compile("state:[\\w]{1,}(fully-charged|charging|discharging)");

    final Matcher stateMatcher = statePattern.matcher(line);

    if (stateMatcher.matches()) {

      final String state = stateMatcher.group(1);
    }
  }
}
