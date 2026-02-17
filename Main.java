import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner scan= new Scanner(System.in);
    DateTimeFormatter format= DateTimeFormatter.ofPattern("HH:mm:ss");
    System.out.print("Please set an alarm(HH:MM:SS): ");
    LocalTime AlarmTime=null;
    while(AlarmTime==null){
      try{
        String alarmTime=scan.nextLine();
        AlarmTime= LocalTime.parse(alarmTime, format);
      }
      catch(Exception e){
        System.out.println("Invalid Formate. Please Use HH:MM:SS");
        System.out.print("Please set an alarm(HH:MM:SS): ");
      }
      
    }
    System.out.println("Your Alarm Set at: "+AlarmTime);
    LocalTime now;
    while(LocalTime.now().isBefore(AlarmTime)){
      Duration left= Duration.between(AlarmTime, LocalTime.now());
      long TotalSecLeft=Math.abs(left.getSeconds());
      int hour=(int)TotalSecLeft / 3600;
      int minute=(int)(TotalSecLeft % 3600)/60;
      int sec=(int)TotalSecLeft % 60;
      System.out.printf("\rCurrent Time: "+ LocalTime.now().withNano(0) + " | Time left: %02d:%02d:%02d",hour, minute, sec);
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    System.out.println("\nIt's Alarm Time!");

    scan.close();

  }
}