package mx.uv.consumidor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class ConsumidorConfiguracion {
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("mx.xlp");
        return marshaller;
    }

    @Bean
    public SaludosCliente saludosCliente(Jaxb2Marshaller mar) {
        SaludosCliente sCliente = new SaludosCliente();
        sCliente.setDefaultUri("http://localhost:8080/ws");
        sCliente.setMarshaller(mar);
        sCliente.setUnmarshaller(mar);
        return sCliente;
    }
}
