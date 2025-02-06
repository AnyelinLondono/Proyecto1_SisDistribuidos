package co.ucentral.edu.emailSender.controller;

import co.ucentral.edu.emailSender.dto.EmailDTO;
import co.ucentral.edu.emailSender.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/email")
@CrossOrigin(origins = "*") // Permite solicitudes desde cualquier origen (para pruebas)
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(
            @RequestPart("destinatario") String destinatario,
            @RequestPart("asunto") String asunto,
            @RequestPart("mensaje") String mensaje,
            @RequestPart(value = "archivoAdjunto", required = false) MultipartFile archivoAdjunto) {

        EmailDTO emailDTO = new EmailDTO(destinatario, asunto, mensaje, archivoAdjunto);
        emailService.enviarCorreo(emailDTO);

        return ResponseEntity.ok("Correo enviado con éxito");
    }
}
