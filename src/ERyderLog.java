import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.Deque;

public class ERyderLog {
    private String ID;
    private String event;
    private LocalDateTime timeStamp;
    public ERyderLog(String ID, String event, LocalDateTime timeStamp){
        this.ID=ID;
        this.event=event;
        this.timeStamp=timeStamp;
    }
    public String getID() {
        return ID;
    }
    public String getEvent() {
        return event;
    }
    public LocalDateTime getDateTime(){
        return LocalDateTime.now();
    }
    @Override
    public String toString() {
            return "ERyderLog{" +
                     ID + "was "+ event+"by from location at"+timeStamp+'\'';
        } 
      Deque<ERyderLog> ERyderLogs=new ArrayDeque<>();
      public void pushLog(String ID, String event){
          ERyderLog log= new ERyderLog(ID, event, timeStamp);
          ERyderLogs.push(log);
      }
public void viewSystemLogs(){
for(int i=0;i<ERyderLogs.size();i++){
    System.out.println(ERyderLogs.pop());
}

}



    }
