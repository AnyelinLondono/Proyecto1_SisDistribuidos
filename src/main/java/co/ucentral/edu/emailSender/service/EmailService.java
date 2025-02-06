package co.ucentral.edu.emailSender.service;

import co.ucentral.edu.emailSender.dto.EmailDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    // Extensiones permitidas
    private static final List<String> EXTENSIONES_PERMITIDAS = Arrays.asList("pdf", "docx", "jpg");
    private static final long MAX_SIZE_MB = 5 * 1024 * 1024; // 5MB en bytes

    // Constructor manual para inyectar JavaMailSender
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public String enviarCorreo(EmailDTO emailDTO) {
        try {
            MimeMessage mensaje = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensaje, true);

            helper.setTo(emailDTO.getDestinatario());
            helper.setSubject(emailDTO.getAsunto());
            helper.setText(emailDTO.getMensaje(), true); // true indica que el mensaje puede ser HTML
            helper.setFrom("santiago17prieto@gmail.com"); // Tu correo configurado en application.properties

            // Verificar si hay un archivo adjunto
            MultipartFile archivo = emailDTO.getArchivoAdjunto();
            if (archivo != null && !archivo.isEmpty()) {
                if (!validarArchivo(archivo)) {
                    return "El archivo adjunto no es válido. Solo se permiten .pdf, .docx y .jpg de hasta 5MB.";
                }
                InputStreamSource archivoFuente = new ByteArrayResource(archivo.getBytes());
                helper.addAttachment(archivo.getOriginalFilename(), archivoFuente);
            }

            mailSender.send(mensaje);
            return "Correo enviado exitosamente.";
        } catch (MessagingException | IOException e) {
            return "Error al enviar el correo: " + e.getMessage();
        }
    }

    private boolean validarArchivo(MultipartFile archivo) {
        // Obtener extensión
        String nombreArchivo = archivo.getOriginalFilename();
        if (nombreArchivo == null) return false;

        String extension = nombreArchivo.substring(nombreArchivo.lastIndexOf(".") + 1);
        long size = archivo.getSize();

        return EXTENSIONES_PERMITIDAS.contains(extension.toLowerCase()) && size <= MAX_SIZE_MB;
    }
}
