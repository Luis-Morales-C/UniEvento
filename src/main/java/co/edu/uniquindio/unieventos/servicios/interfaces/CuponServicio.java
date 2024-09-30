package co.edu.uniquindio.unieventos.servicios.interfaces;

import co.edu.uniquindio.unieventos.dto.cupon.ActualizarCuponDTO;
import co.edu.uniquindio.unieventos.dto.cupon.CrearCuponDTO;
import co.edu.uniquindio.unieventos.dto.cupon.ItemCuponDTO;

import java.util.List;


public interface CuponServicio {
    String crearCupones(CrearCuponDTO cuponDTO);
    String actualizarCupon(ActualizarCuponDTO cuponDTO)throws Exception;
    void borrarCupon(String idCupon);
    boolean validarCupon(String idCupon, String userId);
    String redimirCupon(String idCupon, String idCliente);
    List<ItemCuponDTO> listarCupones(String idCupon, String idCliente);


}
