package model;

import java.time.LocalDate;
//class som beskriver Event
public class MyEvent {
    private String eventName;
    private LocalDate date;

    public MyEvent(String eventName, LocalDate date) {
        this.eventName = eventName;
        this.date = date;
    }

    public MyEvent() {
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
