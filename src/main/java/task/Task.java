package task;

import java.time.LocalDate;

public class Task {
    protected int id;
    protected String title;
    protected String description;
    protected LocalDate date;

    public Task(int id,String title, String description, LocalDate date){
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public Task(String title, String description, LocalDate date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void printInformation(){
        System.out.println(getId() + " " + getTitle() + " " + getDescription() + " " + getDate());
    }
}
