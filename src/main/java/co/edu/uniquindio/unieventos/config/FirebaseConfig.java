package co.edu.uniquindio.unieventos.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig{
    @Bean
    public FirebaseApp intializeFirebase() throws IOException {
        FileInputStream serviceAccount = new FileInputStream(
                "src/main/resources/programacion-a5156-firebase-adminsdk-j2c6w-00a0167936.json"
        );
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setStorageBucket("programacion-a5156.appspot.com")
                .build();

        if(FirebaseApp.getApps().isEmpty()) {
            return FirebaseApp.initializeApp(options);
        }
        return null;
    }
}
