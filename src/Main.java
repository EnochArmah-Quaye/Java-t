import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {


        String filePath = "file_example_WAV_5MG.wav";
        File file = new File(filePath);

        try(AudioInputStream audioStream = AudioSystem.getAudioInputStream(file)){

        }
        catch(UnsupportedAudioFileException e){
            System.out.println("Audio file not supported");
        }
        catch(IOException e){
            System.out.println("Something went wrong");
        }
    }
}