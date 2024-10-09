package co.edu.uniquindio.unieventos.controladores;

import co.edu.uniquindio.unieventos.dto.cupon.*;
import co.edu.uniquindio.unieventos.dto.global.MensajeDTO;
import co.edu.uniquindio.unieventos.servicios.interfaces.CuponServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cupon")
public class CuponControlador {
    private final CuponServicio cuponServicio;

    /**
     *
     * @param
     * @return
     * @throws Exception

    @PostMapping("/crear-cupon")
    public ResponseEntity<MensajeDTO<String>> crearCupon(@Valid @RequestBody CrearCuponDTO cuponDTO) throws Exception {
        String resultado = cuponServicio.crearCupones(cuponDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, resultado));
    }


    @PutMapping("/actualizar-cupon")
    public ResponseEntity<MensajeDTO<String>> actualizarCupon(@Valid @RequestBody ActualizarCuponDTO cuponDTO) throws Exception {
        String resultado = cuponServicio.actualizarCupon(cuponDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, resultado));
    }


    @DeleteMapping("/borrar-cupon/{idCupon}")
    public ResponseEntity<MensajeDTO<String>> borrarCupon(@PathVariable String idCupon) {
        cuponServicio.borrarCupon(idCupon);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Cupón eliminado exitosamente"));
    }
     */


    @PutMapping("/revertir-redencion")
    public ResponseEntity<MensajeDTO<String>> revertirRedencionCupon(@Valid @RequestBody RevertirCuponDTO revertirCuponDTO) throws Exception {
        boolean revertido = cuponServicio.revertirRedencionCupon(revertirCuponDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, revertido ? "Redención del cupón revertida exitosamente" : "No se pudo revertir la redención"));
    }

    @GetMapping("/listar-todo-cupon")
    public ResponseEntity<MensajeDTO<List<ItemCuponDTO>>> listarCupones() {
        List<ItemCuponDTO> lista = cuponServicio.listarCupones();
        return ResponseEntity.ok(new MensajeDTO<>(false, lista));
    }
    @PostMapping("/listar-cupon-cliente")
    public ResponseEntity<MensajeDTO<List<ItemCuponDTO>>> listarCuponesCliente(@Valid @RequestBody ListarCupoDTO listarCupoDTO) {
        List<ItemCuponDTO> lista = cuponServicio.listarCuponesCliente(listarCupoDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, lista));
    }
}
