package com.example.desktoplogger;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@Configuration
public class LoggingAutoConfiguration {

    @PostConstruct
    public void setupLogbackConfig() {
        try {
            String desktopPath = System.getProperty("user.home") + "/Desktop";
            File logDir = new File(desktopPath);
            if (!logDir.exists()) logDir.mkdirs();

            InputStream in = getClass().getClassLoader().getResourceAsStream("logback-desktop.xml");
            if (in != null) {
                Files.copy(in, new File(logDir, "logback-desktop.xml").toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception e) {
            System.err.println("로그 설정 복사 실패: " + e.getMessage());
        }
    }
}
