package mx.uv.practica04;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import https.t4is_uv_mx.saludos.SaludarRequest;
import https.t4is_uv_mx.saludos.SaludarResponse;
import https.t4is_uv_mx.saludos.BuscarIdRequest;
import https.t4is_uv_mx.saludos.BuscarIdResponse;
import https.t4is_uv_mx.saludos.listaResponse;
import https.t4is_uv_mx.saludos.ModificarRequest;
import https.t4is_uv_mx.saludos.ModificarResponse;
import https.t4is_uv_mx.saludos.EliminarRequest;
import https.t4is_uv_mx.saludos.EliminarResponse;

@Endpoint
public class EndPoint {

    Iterable<Saludador> listanombres;

    @Autowired
    private iSaludador isaludador;


    @PayloadRoot(localPart = "SaludarRequest", namespace = "https://t4is.uv.mx/saludos")

    @ResponsePayload
    public SaludarResponse Saludar(@RequestPayload SaludarRequest peticion) {
        SaludarResponse response = new SaludarResponse();
        response.setRespuesta("Hola " + peticion.getNombre());

        Saludador saludador = new Saludador();
        saludador.setNombre(peticion.getNombre());
        isaludador.save(saludador);

        return response;
    }
    

    @PayloadRoot(localPart = "BuscarIdRequest", namespace = "https://t4is.uv.mx/saludos")

    @ResponsePayload
    public BuscarIdResponse BuscarId(@RequestPayload BuscarIdRequest peticion) {
        BuscarIdResponse response = new BuscarIdResponse();
        Optional<Saludador> s = isaludador.findById(peticion.getId());
        Saludador x = s.get();
        response.setRespuesta("\n" + "se ha pedido el id: " + peticion.getId() + "\n" + "| Nombre: " + x.getNombre());
        return response;
    }


    @PayloadRoot(localPart = "listaRequest", namespace = "https://t4is.uv.mx/saludos")

    @ResponsePayload
    public listaResponse Blistar() {
        listaResponse response = new listaResponse();
        String nombres= "";
        listanombres = isaludador.findAll();
        for (Saludador s : listanombres) {
            nombres += "\n" + s.getId() + " - " + s.getNombre() + " // " + "\n";
        }
        response.setRespuesta(nombres);
        return response;
    }


    @PayloadRoot(localPart = "ModificarRequest", namespace = "https://t4is.uv.mx/saludos")

    @ResponsePayload
    public ModificarResponse Editar(@RequestPayload ModificarRequest peticion){
        ModificarResponse response = new ModificarResponse();
        Optional<Saludador> s = isaludador.findById(peticion.getId());
        Saludador x = s.get();
        x.setNombre(peticion.getNombrenuevo());
        isaludador.save(x);
        response.setRespuesta("\n" + "se ha modificado el nombre del id: " + x.getId() + "\n" + "Con el nombre: " + x.getNombre() + "\n");
        return response;
    }

    @PayloadRoot(localPart = "EliminarRequest", namespace = "https://t4is.uv.mx/saludos")

    @ResponsePayload
    public EliminarResponse Eliminar(@RequestPayload EliminarRequest peticion){
        EliminarResponse response = new EliminarResponse();
        isaludador.deleteById(peticion.getId());
        response.setRespuesta("\n" + "se ha eliminado el id: "+ peticion.getId() + "\n");
        return response;
    }
}