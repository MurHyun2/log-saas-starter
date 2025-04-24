
package com.oursaas.autolog;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.Map;

public class LogSender {
    public void send(Map<String, Object> logData) {
        try {
            String desktop = System.getProperty("user.home") + "/Desktop";
            String logPath = Paths.get(desktop, "autologs.txt").toString();
            String json = new ObjectMapper().writeValueAsString(logData);

            try (FileWriter fw = new FileWriter(logPath, true)) {
                fw.write(json + System.lineSeparator());
            }
        } catch (Exception e) {
            // ignore
        }
    }
}
