package io.github.educontessi.core.address;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author Eduardo Possamai Contessi
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class SwaggerDocumentationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void generateSwagger() {
        String swagger = this.restTemplate.getForObject("/v3/api-docs/", String.class);
        this.writeFile(swagger);
        Assertions.assertNotNull(swagger);
    }

    private void writeFile(String content) {
        File theDir = new File("swagger");
        if (!theDir.exists()) {
            theDir.mkdir();
        }

        BufferedWriter bw = null;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("swagger/openapi.json");
            bw = new BufferedWriter(new OutputStreamWriter(fos, StandardCharsets.UTF_8));
            bw.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
