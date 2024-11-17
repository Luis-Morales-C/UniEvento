package co.edu.uniquindio.unieventos.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {

    @Bean
    public FirebaseApp intializeFirebase() throws IOException {
        // Obtener la ruta del archivo JSON desde la variable de entorno
        String credentialsPath = System.getenv("GOOGLE_APPLICATION_CREDENTIALS");

        // Verificar si la variable de entorno está configurada correctamente
        if (credentialsPath == null || credentialsPath.isEmpty()) {
            throw new IOException("La variable de entorno GOOGLE_APPLICATION_CREDENTIALS no está configurada.");
        }

        // Cargar el archivo JSON utilizando la ruta de la variable de entorno
        FileInputStream serviceAccount = new FileInputStream(credentialsPath);

        // Configuración de Firebase
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setStorageBucket("programacion-a5156.appspot.com")
                .build();

        // Inicializar Firebase
        if (FirebaseApp.getApps().isEmpty()) {
            return FirebaseApp.initializeApp(options);
        }
        return null;
    }
}
