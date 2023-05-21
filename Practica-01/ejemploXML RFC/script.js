// Cargar el archivo XML
const xhr = new XMLHttpRequest();
xhr.open("GET", "ejemploRFC.xml", true);
xhr.onreadystatechange = function() {
  if (xhr.readyState === 4 && xhr.status === 200) {
    // Procesar el archivo XML
    const xmlDoc = xhr.responseXML;
    const items = xmlDoc.getElementsByTagName("Rfc");

    // Mostrar los datos en la página web
    const xmlList = document.getElementById("xml-list");
    for (let i = 0; i < items.length; i++) {
      const item = items[i];
      const Rfc = item.getElementsByTagName("Rfc")[0].childNodes[0].nodeValue;
      listItem.innerHTML = "<h2>" + Rfc;
      xmlList.appendChild(listItem);

      console.log(Rfc);
    }
  }
};
xhr.send();