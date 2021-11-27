import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        File output = new File("output.ppm");
        output.createNewFile();
        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream(output));
            System.out.print("Enter pixel cage size: ");
            Scanner scanner = new Scanner(System.in);
            int pixelCageSize = scanner.nextInt();
            final int COLOR_GAGE = 20;
        out.writeBytes("P3\n");
        out.writeBytes("# output.ppm\n");
        out.writeBytes(pixelCageSize+" "+pixelCageSize+"\n");
        out.writeBytes(COLOR_GAGE+"\n");

        for (int y = 0; y < pixelCageSize; y++){
            for (int x =0; x < pixelCageSize; x++){
                int randomColor = (int) (Math.random()*15);
                out.writeBytes(randomColor+ " ");
                out.writeBytes(randomColor+ " ");
                out.writeBytes(randomColor+ " ");
            }
            out.writeBytes("\n");

        }

        System.out.println("Completed");
        }
        catch (IOException ex){System.out.println(ex);}
    }
}
