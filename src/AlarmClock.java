import javax.sound.sampled.*;
import javax.tools.Tool;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Scanner;

public class AlarmClock implements Runnable{

    private final LocalTime alarmTime;
    private final String filePath;
    private  final Scanner scanner;

    AlarmClock(LocalTime alarmTime, String filePath, Scanner scanner){
        this.alarmTime = alarmTime;
        this.filePath = filePath;
        this.scanner = scanner;
    }

    @Override
    public void run(){

        while(LocalTime.now().isBefore(alarmTime)){
            try{
                Thread.sleep(1000);

                int hours = LocalTime.now().getHour();
                int minutes = LocalTime.now().getMinute();
                int seconds = LocalTime.now().getSecond();

                System.out.printf("\r%02d:%02d:%02d", hours, minutes, seconds);


            }
            catch(InterruptedException e){
                System.out.println("Thread was interrupted");
            }

        }

        System.out.println("\n**Alarm Noises**");
        playSound(filePath);
    }

    private void playSound(String filePath){
        File audioFile = new File(filePath);


        try( AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);){
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            System.out.print("Press *Enter* to stop the alarm: ");
            scanner.nextLine();
            clip.stop();

            scanner.close();
        }
        catch(UnsupportedAudioFileException e){
            System.out.println("Audio file format not supported");
        }
        catch(LineUnavailableException e){
            System.out.println("Audio is unavailable");
        }
        catch (IOException e) {
            System.out.println("Error reading audio file");;
        }
    }
}
