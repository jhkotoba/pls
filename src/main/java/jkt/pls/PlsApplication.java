package jkt.pls;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class PlsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlsApplication.class, args);		
	}
	
	@EventListener(ApplicationReadyEvent.class)
    public void openBrowserOnStart() {
        String url = "http://localhost:17001/";
        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(url));
            } else {
                // Windows fallback
                Runtime.getRuntime().exec(new String[] {
                    "rundll32", "url.dll,FileProtocolHandler", url
                });
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

}
