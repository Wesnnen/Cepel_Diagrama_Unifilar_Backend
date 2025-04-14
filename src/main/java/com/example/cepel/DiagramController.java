
package com.example.cepel;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/diagram")
@CrossOrigin(origins = "http://localhost:3000")
public class DiagramController {

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DiagramResponse> uploadXml(@RequestParam("file") MultipartFile file) {
        try {
            String xml = new String(file.getBytes(), StandardCharsets.UTF_8);
            DiagramResponse response = DiagramService.parseXmlToDiagram(xml);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
