package task;

import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandTool {
    private TaskRefactoring taskRefactoring;

    public void parseCommand(String command){
        final String addTask = "(addtask) ([a-zA-Zа-яА-Я]+;[a-zA-Z\\sа-яА-Я\\W$]+;[0-9.-]+)";
        Matcher matcher = isPatternMatches(command,addTask);
        if(matcher.find()){
            String data = matcher.group(2);
            System.out.println(data);
            String[] taskData = data.split(",");
            Date date = Date.;
            taskRefactoring.addTask(taskData[0],taskData[1],date.toLocalDate);
        }
    }

    public Matcher isPatternMatches(String command, String regex){
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(command);
        return matcher;
    }
    public static void main(String[] args) {
        CommandTool commandTool = new CommandTool();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Please, type a command");
            String command = sc.nextLine();
            commandTool.parseCommand(command);
        }
    }
}
