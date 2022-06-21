package docker;

import java.io.IOException;


/**
 * java Main.java run sh
 */
public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        switch (args[0]) {
            case "run": run(args); break;
            default:
                System.out.println("undefined commond");
        }
    }

    public static void run(String[] args) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        Process command = processBuilder.command(args[1])
                .redirectError(ProcessBuilder.Redirect.INHERIT)
                .redirectOutput(ProcessBuilder.Redirect.INHERIT)
                .redirectInput(ProcessBuilder.Redirect.INHERIT).start();

        while(true) {}
    }

}
