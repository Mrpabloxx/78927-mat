using System;
using WSDL.mensajes;

namespace WSDL.operaciones
{
    public class Operaciones : Mensajes
    {
        List<string> saludos = new List<string>();
        public string Saludar(string nombre)
        {
            string msj= "Hola "+ nombre;
            saludos.Add(msj);
            return msj;
        }
        public string Mostrar(int id)
        {
            return saludos[id-1];
        }
    }
}