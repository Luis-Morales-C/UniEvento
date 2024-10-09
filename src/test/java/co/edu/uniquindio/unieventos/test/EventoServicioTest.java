package co.edu.uniquindio.unieventos.test;

import co.edu.uniquindio.unieventos.documentos.EstadoEvento;
import co.edu.uniquindio.unieventos.documentos.Localidad;
import co.edu.uniquindio.unieventos.documentos.TipoEvento;
import co.edu.uniquindio.unieventos.documentos.Ubicacion;
import co.edu.uniquindio.unieventos.dto.evento.*;
import co.edu.uniquindio.unieventos.servicios.interfaces.EventoServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EventoServicioTest {

    @Autowired
    private EventoServicio eventoServicio;


    @Test
    public void crearEventoTest() {

    List<Localidad> localidadades=new ArrayList<>();
    Localidad localidad1=new Localidad();
    localidad1.setId("L1");
    localidad1.setNombre("VIP");
    localidad1.setPrecio(200.000);
    localidad1.setCapacidadMaxima(100);
    localidad1.setEntradasVendidas(0);

    Localidad localidad2=new Localidad();
    localidad2.setId("L2");
    localidad2.setNombre("GENERAL");
    localidad2.setPrecio(100.000);
    localidad2.setCapacidadMaxima(150);
    localidad2.setEntradasVendidas(0);

    Localidad localidad3=new Localidad();
    localidad3.setId("L2");
    localidad3.setNombre("PlATINO");
    localidad3.setPrecio(120.000);
    localidad3.setCapacidadMaxima(150);
    localidad3.setEntradasVendidas(0);
    
    localidadades.add(localidad1);
    localidadades.add(localidad2);
    localidadades.add(localidad3);

    Ubicacion ubicacion=new Ubicacion();

        CrearEventoDTO crearEventoDTO = new CrearEventoDTO(
                "1",
                "Concierto Prueba",
                "Este es un concierto de prueba",
                "Calle 123 #45-67",
                "Bogotá",
                "imagen1.jpg",
                "imagenLocalidades.jpg",
                TipoEvento.CONCIERTO,
                localidadades,
                EstadoEvento.ACTIVO,
                LocalDateTime.now()
        );

        assertDoesNotThrow(() -> {
            String id = eventoServicio.crearEvento(crearEventoDTO);
            assertNotNull(id);
        });
    }

    @Test
    public void editarEventoTest() {
        List<Localidad> localidadades=new ArrayList<>();
        Localidad localidad1=new Localidad();
        localidad1.setId("L1");
        localidad1.setNombre("VIP");
        localidad1.setPrecio(200.000);
        localidad1.setCapacidadMaxima(100);
        localidad1.setEntradasVendidas(0);

        Localidad localidad2=new Localidad();
        localidad2.setId("L2");
        localidad2.setNombre("GENERAL");
        localidad2.setPrecio(100.000);
        localidad2.setCapacidadMaxima(150);
        localidad2.setEntradasVendidas(0);

        Localidad localidad3=new Localidad();
        localidad3.setId("L2");
        localidad3.setNombre("PlATINO");
        localidad3.setPrecio(120.000);
        localidad3.setCapacidadMaxima(150);
        localidad3.setEntradasVendidas(0);

        EditarEventoDTO editarEventoDTO = new EditarEventoDTO(
                "evento123",
                "Concierto Editado",
                "Este es un concierto editado",
                "Calle 789 #10-11",
                "imagen2.jpg",
                "imagenLocalidades2.jpg",
                TipoEvento.CONCIERTO,
                localidadades,
                EstadoEvento.ACTIVO,
                LocalDateTime.now()

        );

        assertDoesNotThrow(() -> {
            String id = eventoServicio.editarEvento(editarEventoDTO);
            assertNotNull(id);
        });
    }

    @Test
    public void eliminarEventoTest() {
        String idEvento = "evento123";

        assertDoesNotThrow(() -> {
            String resultado = eventoServicio.eliminarEvento(idEvento);
            assertEquals(idEvento, resultado);
        });
    }

    @Test
    public void obtenerInformacionEventoTest() {
        String idEvento = "evento123";

        assertDoesNotThrow(() -> {
            InformacionEventoDTO eventoDTO = eventoServicio.obtenerInformacionEvento(idEvento);
            assertEquals("evento123", eventoDTO.id());
        });
    }

    @Test
    public void listarEventosTest() {
        List<ItemEventoDTO> listaEventos = eventoServicio.listarEventos();

        assertEquals(5, listaEventos.size()); // Cambia el valor según los eventos que esperas
    }

    @Test
    public void disponibilidadTest() {
        DisponibilidadEventoDTO disponibilidadEventoDTO = new DisponibilidadEventoDTO(
                "evento123",
                "General",
                2
        );

        assertDoesNotThrow(() -> {
            boolean disponible = eventoServicio.disponiblidad(disponibilidadEventoDTO);
            assertTrue(disponible);
        });
    }

    @Test
    public void filtrarEventosTest() {

        FiltroEventoDTO filtroEventoDTO = new FiltroEventoDTO(
                "CONCIERTO",
                TipoEvento.CONCIERTO,
                "Concierto Prueba"
        );

        assertDoesNotThrow(() -> {
            List<ItemEventoDTO> listaFiltrada = eventoServicio.filtrarEventos(filtroEventoDTO);
            assertEquals(1, listaFiltrada.size());
        });
    }
}

