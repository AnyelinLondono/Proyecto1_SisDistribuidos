<template>
    <div class="container mt-5">
      <div class="card shadow-lg">
        <div class="card-header bg-primary text-white text-center">
          <h2>Enviar Correo</h2>
        </div>
        <div class="card-body">
          <form @submit.prevent="enviarCorreo" enctype="multipart/form-data">
            <!-- Destinatario -->
            <div class="mb-3">
              <label class="form-label">Destinatario:</label>
              <input
                type="email"
                class="form-control"
                v-model="form.destinatario"
                @blur="validarCampo('destinatario')"
              />
              <small v-if="errores.destinatario" class="text-danger">{{ errores.destinatario }}</small>
            </div>
  
            <!-- Asunto -->
            <div class="mb-3">
              <label class="form-label">Asunto:</label>
              <input
                type="text"
                class="form-control"
                v-model="form.asunto"
                @blur="validarCampo('asunto')"
              />
              <small v-if="errores.asunto" class="text-danger">{{ errores.asunto }}</small>
            </div>
  
            <!-- Mensaje -->
            <div class="mb-3">
              <label class="form-label">Mensaje:</label>
              <textarea
                class="form-control"
                rows="4"
                v-model="form.mensaje"
                @blur="validarCampo('mensaje')"
              ></textarea>
              <small v-if="errores.mensaje" class="text-danger">{{ errores.mensaje }}</small>
            </div>
  
            <!-- Archivo Adjunto (Opcional) -->
            <div class="mb-3">
              <label class="form-label">Archivo Adjunto:</label>
              <input
                type="file"
                ref="archivoInput"
                class="form-control"
                @change="handleFileUpload"
                accept=".pdf,.docx,.jpg"
              />
            </div>
  
            <!-- Botón Enviar con Loader -->
            <button type="submit" class="btn btn-success w-100" :disabled="!formValido || cargando">
            <span v-if="cargando">
                <span class="spinner-border spinner-border-sm"></span> Enviando...
            </span>
            <span v-else>📨 Enviar</span>
            </button>
          </form>
        </div>
      </div>
  
      <!-- Modal de Mensaje -->
      <div v-if="mostrarModal" class="modal fade show d-block" tabindex="-1">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title">Estado del Envío</h5>
            </div>
            <div class="modal-body">
              <p :class="mensaje.includes('✅') ? 'text-success' : 'text-danger'">{{ mensaje }}</p>
            </div>
          </div>
        </div>
      </div>
  
      <!-- Fondo Oscuro del Modal -->
      <div v-if="mostrarModal" class="modal-backdrop fade show"></div>
    </div>
  </template>
  
  <script>
  import axios from "axios";
  
  export default {
    data() {
      return {
        form: {
          destinatario: "",
          asunto: "",
          mensaje: "",
          archivoAdjunto: null,
        },
        errores: {
          destinatario: "",
          asunto: "",
          mensaje: "",
        },
        mensaje: "",
        cargando: false,
        mostrarModal: false,
      };
    },
    computed: {
      formValido() {
        return !this.errores.destinatario && !this.errores.asunto && !this.errores.mensaje &&
               this.form.destinatario && this.form.asunto && this.form.mensaje;
      }
    },
    methods: {
      handleFileUpload(event) {
        this.form.archivoAdjunto = event.target.files[0];
      },
      validarCampo(campo) {
        if (!this.form[campo]) {
          this.errores[campo] = "Este campo es obligatorio";
        } else {
          this.errores[campo] = "";
        }
  
        if (campo === "destinatario") {
          const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
          if (!emailRegex.test(this.form.destinatario)) {
            this.errores.destinatario = "Ingrese un correo válido";
          }
        }
      },
      async enviarCorreo() {
        this.validarCampo("destinatario");
        this.validarCampo("asunto");
        this.validarCampo("mensaje");
  
        if (!this.formValido) return; // Evitar envío si hay errores
  
        this.cargando = true; // Mostrar loader
  
        try {
          let formData = new FormData();
          formData.append("destinatario", this.form.destinatario);
          formData.append("asunto", this.form.asunto);
          formData.append("mensaje", this.form.mensaje);
          if (this.form.archivoAdjunto) {
            formData.append("archivoAdjunto", this.form.archivoAdjunto);
          }
  
          await axios.post("http://localhost:8080/api/email/send", formData, {
            headers: { "Content-Type": "multipart/form-data" },
          });
  
          this.mensaje = "✅ Correo enviado con éxito";
          this.resetForm();
        } catch (error) {
          this.mensaje = "❌ Error al enviar el correo";
          console.error(error);
        } finally {
          this.cargando = false; // Ocultar loader
          this.mostrarModal = true; // Mostrar modal
  
          // Cerrar modal después de 2 segundos
          setTimeout(() => {
            this.mostrarModal = false;
          }, 2000);
        }
      },
      resetForm() {
        this.form.destinatario = "";
        this.form.asunto = "";
        this.form.mensaje = "";
        this.form.archivoAdjunto = null;
        this.errores = { destinatario: "", asunto: "", mensaje: "" };
  
        // Resetear el campo de archivo adjunto
        this.$refs.archivoInput.value = "";
      }
    },
  };
  </script>
  
  <style scoped>
  .container {
    max-width: 500px;
  }
  
  /* Modal */
  .modal {
    background: rgba(0, 0, 0, 0.5);
    display: flex;
    align-items: center;
    justify-content: center;
  }
  
  .modal-content {
    width: 80%;
    max-width: 400px;
    background: white;
    border-radius: 8px;
    padding: 20px;
    text-align: center;
  }
  
  /* Loader */
  .spinner-border {
    vertical-align: middle;
  }
  </style>
  