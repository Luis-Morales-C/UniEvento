package co.edu.uniquindio.unieventos.controladores;


import co.edu.uniquindio.unieventos.documentos.Evento;
import co.edu.uniquindio.unieventos.documentos.Orden;
import co.edu.uniquindio.unieventos.documentos.Reporte;
import co.edu.uniquindio.unieventos.dto.cupon.ActualizarCuponDTO;
import co.edu.uniquindio.unieventos.dto.cupon.CrearCuponDTO;
import co.edu.uniquindio.unieventos.dto.cupon.ItemCuponDTO;
import co.edu.uniquindio.unieventos.dto.evento.CrearEventoDTO;
import co.edu.uniquindio.unieventos.dto.evento.EditarEventoDTO;
import co.edu.uniquindio.unieventos.dto.evento.ItemEventoDTO;
import co.edu.uniquindio.unieventos.dto.global.MensajeDTO;
import co.edu.uniquindio.unieventos.dto.reportes.GenerarReporteDTO;
import co.edu.uniquindio.unieventos.repositorios.OrdenRepo;
import co.edu.uniquindio.unieventos.servicios.interfaces.CuponServicio;
import co.edu.uniquindio.unieventos.servicios.interfaces.EventoServicio;
import co.edu.uniquindio.unieventos.servicios.interfaces.ReporteServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdministradorControlador {
    private final EventoServicio eventoServicio;
    private final CuponServicio cuponServicio;
    private final OrdenRepo ordenRepo;
    private final ReporteServicio reporteServicio;


    @PostMapping("/crear-evento")
    public ResponseEntity<MensajeDTO<String>> crearEvento(@Valid @RequestBody CrearEventoDTO crearEventoDTO) throws Exception {
        String resultado = eventoServicio.crearEvento(crearEventoDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, resultado));
    }


    @PutMapping("/editar-evento")
    public ResponseEntity<MensajeDTO<String>> editarEvento(@Valid @RequestBody EditarEventoDTO editarEventoDTO) throws Exception {
        String resultado = eventoServicio.editarEvento(editarEventoDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, resultado));
    }


    @DeleteMapping("/eliminar-evento/{id}")
    public ResponseEntity<MensajeDTO<String>> eliminarEvento(@PathVariable String id) throws Exception {
        String resultado = eventoServicio.eliminarEvento(id);
        return ResponseEntity.ok(new MensajeDTO<>(false, resultado));
    }

    @GetMapping("/listar-todo-evento")
    public ResponseEntity<MensajeDTO<List<ItemEventoDTO>>> listarEventos() {
        List<ItemEventoDTO> lista = eventoServicio.listarEventos();
        return ResponseEntity.ok(new MensajeDTO<>(false, lista));
    }


    @PostMapping("/crear-cupon")
    public ResponseEntity<MensajeDTO<String>> crearCupon(@Valid @RequestBody CrearCuponDTO cuponDTO) throws Exception {
        String resultado = cuponServicio.crearCupones(cuponDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, resultado));
    }
    @GetMapping("/listar-cupones")
    public ResponseEntity<MensajeDTO<List<ItemCuponDTO>>> listarCupones() {
        List<ItemCuponDTO> lista = cuponServicio.listarCupones();
        return ResponseEntity.ok(new MensajeDTO<>(false, lista));
    }


    @PutMapping("/actualizar-cupon")
    public ResponseEntity<MensajeDTO<String>> actualizarCupon(@Valid @RequestBody ActualizarCuponDTO cuponDTO) throws Exception {
        String resultado = cuponServicio.actualizarCupon(cuponDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, resultado));
    }


    @DeleteMapping("/borrar-cupon/{idCupon}")
    public ResponseEntity<MensajeDTO<String>> borrarCupon(@PathVariable String idCupon) throws Exception {
        cuponServicio.borrarCupon(idCupon);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Cupón eliminado exitosamente"));
    }

    @GetMapping("/estadisticas/{idEvento}")
    public ResponseEntity<Reporte> generarEstadisticasEvento(@PathVariable String idEvento) throws Exception {
        Evento evento = eventoServicio.obtenerEvento(idEvento);
        List<Orden> ordenes = ordenRepo.findByItemsIdEvento(idEvento);


        Reporte reporte = reporteServicio.generarReporte(new GenerarReporteDTO(evento,ordenes));

        return ResponseEntity.ok(reporte);
    }


    @GetMapping("/estadisticas/pdf/{idEvento}")
    public ResponseEntity<byte[]> descargarReportePDF(@PathVariable String idEvento) throws Exception {
        Evento evento = eventoServicio.obtenerEvento(idEvento);
        List<Orden> ordenes = ordenRepo.findByItemsIdEvento(idEvento);


        Reporte reporte = reporteServicio.generarReporte(new GenerarReporteDTO(evento, ordenes));


        ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
        reporteServicio.generarPDF(reporte, pdfOutputStream);


        byte[] pdfBytes = pdfOutputStream.toByteArray();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "reporte_evento_" + idEvento + ".pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }
}
