package task;

import java.sql.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandTool {
    private TaskRefactoring taskRefactoring;

    public CommandTool(){
        taskRefactoring = new TaskRefactoring();
    }

    public void parseCommand(String command){
        final String addTask = "(addtask) ([a-zA-Z\\sа-яА-Я\\ \\W$]+;[a-zA-Z\\sа-яА-Я\\- W$0-9]+;[0-9-]+)";
        final String printAllTask = "(printalltask)";
        final String printTaskById = "(printtaskbyid) ([0-9]+)";
        final String printTaskByDate = "(printtaskbydate) ([0-9-]+)";
        Matcher matcher = isPatternMatches(command,addTask);
        if (matcher.find()) {
            String data = matcher.group(2);
            System.out.println(data);
            String[] taskData = data.split(";");
            Date date = Date.valueOf(taskData[2]);
            taskRefactoring.addTask(taskData[0], taskData[1], date.toLocalDate());
            System.out.println("Ok");
        }
        matcher = isPatternMatches(command, printAllTask);
        if(matcher.find()){
            taskRefactoring.printAllTask();
            System.out.println("Ok");
        }
        matcher = isPatternMatches(command, printTaskById);
        if(matcher.find()){
            String data = matcher.group(2);
            String [] oneTaskData = data.split(";");
            int id = Integer.parseInt(oneTaskData[0]);
            taskRefactoring.printTaskById(id);
            System.out.println("Ok");
        }
        matcher = isPatternMatches(command, printTaskByDate);
        if(matcher.find()){
            String data = matcher.group(2);
            String [] taskDateData = data.split(";");
            Date date = Date.valueOf(taskDateData[0]);;
            taskRefactoring.printTaskByDate(date.toLocalDate());
            System.out.println("Ok");
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
