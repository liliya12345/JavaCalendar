package service;

import model.MyEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyEventService {
    //funtion som addera event och sparar i filen
    public void addEvent(MyEvent event) {
        try {
            File file =new File(MyEventService.class.getClassLoader().getResource("myevent.txt").getPath());
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(event.getEventName()+";"+ event.getDate()+"\n");
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("File write error");
        }

    }
    //metod som får alla event från filen och returnera i en list med alla event
    public List<MyEvent> getAllEvents() {
        List<MyEvent> itemList = new ArrayList<>();
        try {
            File file =new File(MyEventService.class.getClassLoader().getResource("myevent.txt").getPath());
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] splitItem = line.split(";");
                MyEvent item = new MyEvent();
                item.setEventName(splitItem[0]);
                item.setDate(LocalDate.parse(splitItem[1]));
                itemList.add(item);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return itemList;
    }
}
