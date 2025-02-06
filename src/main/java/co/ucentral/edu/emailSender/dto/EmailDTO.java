package co.ucentral.edu.emailSender.dto;


import org.springframework.web.multipart.MultipartFile;


public class EmailDTO {
    private String destinatario;
    private String asunto;
    private String mensaje;
    private MultipartFile archivoAdjunto;

    // Constructor vacío
    public EmailDTO() {}

    // Constructor con parámetros
    public EmailDTO(String destinatario, String asunto, String mensaje, MultipartFile archivoAdjunto) {
        this.destinatario = destinatario;
        this.asunto = asunto;
        this.mensaje = mensaje;
        this.archivoAdjunto = archivoAdjunto;
    }

    // Getters y Setters
    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public MultipartFile getArchivoAdjunto() {
        return archivoAdjunto;
    }

    public void setArchivoAdjunto(MultipartFile archivoAdjunto) {
        this.archivoAdjunto = archivoAdjunto;
    }
}


