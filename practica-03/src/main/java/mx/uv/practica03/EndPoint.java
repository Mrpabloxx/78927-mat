package mx.uv.practica03;

import java.util.ArrayList;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import https.t4is_uv_mx.saludos.ASaludarRequest;
import https.t4is_uv_mx.saludos.ASaludarResponse;
import https.t4is_uv_mx.saludos.BBuscarResponse;
import https.t4is_uv_mx.saludos.CModificarRequest;
import https.t4is_uv_mx.saludos.CModificarResponse;
import https.t4is_uv_mx.saludos.DBorrarRequest;
import https.t4is_uv_mx.saludos.DBorrarResponse;

@Endpoint
public class EndPoint {
    ArrayList<String> nombres = new ArrayList<String>();
    int i=0;

    @PayloadRoot(localPart = "ASaludarRequest", namespace = "https://t4is.uv.mx/saludos")

    @ResponsePayload
    public ASaludarResponse Saludar(@RequestPayload ASaludarRequest peticion) {
        ASaludarResponse response = new ASaludarResponse();
        response.setRespuesta("Hola " + peticion.getNombre());
        return response;
    }

    @PayloadRoot(localPart = "BBuscarRequest", namespace="https://t4is.uv.mx/saludos")

    @ResponsePayload
    public BBuscarResponse Pedir() {
        BBuscarResponse response= new BBuscarResponse();
        String todos="Saludar: ";
        for (int x = 0; x < nombres.size(); x++) {
          if(nombres.get(x)!=null){
            todos=todos+ nombres.get(x) +", ";
          }
        }
        response.setRespuesta(todos);
        return response;
    }

    @PayloadRoot(localPart = "CModificarRequest", namespace="https://t4is.uv.mx/saludos")

    @ResponsePayload
    public CModificarResponse Modificar( @RequestPayload CModificarRequest peticion) {
        int aux=0;
        CModificarResponse response= new CModificarResponse();
        for (int x = 0; x < nombres.size(); x++) {
            if (peticion.getNombreOld().equals(nombres.get(x))){
                nombres.set(x, peticion.getNombreNew());
                response.setRespuesta(peticion.getNombreOld() +nombres.get(x));
                aux=1;
            }
        }
        if (aux==0){
            response.setRespuesta("No encontrado");
            aux=0;
        }
        return response;
    }

    @PayloadRoot(localPart = "DBorrarRequest", namespace="https://t4is.uv.mx/saludos")

    @ResponsePayload
    public DBorrarResponse Borrar( @RequestPayload DBorrarRequest peticion) {
        DBorrarResponse response= new DBorrarResponse();
        int aux=0;
        for (int x = 0; x < nombres.size(); x++) {
            if (peticion.getNombre().equals(nombres.get(x))){
                nombres.remove(x);
                response.setRespuesta(peticion.getNombre()+" ha sido Eliminado");
                aux=1;
            }
        }
        if (aux==0){
            response.setRespuesta("No encontrado");
            aux=0;
        }
        return response;
    }
}