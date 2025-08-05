# **1) Fábrica de Pelotas “Golazo”**

## Enunciado

Desarrolle un sistema para simular la gestión de la producción de una fábrica de pelotas. Este sistema deberá modelar la estructura y el comportamiento de los elementos involucrados en el proceso productivo, permitiendo su correcta interacción, seguimiento y evolución, teniendo en cuenta que la fábrica está organizada de la siguiente forma:

- Plantas: Se identifican por un color, tiene una superficie en metros cuadrados, cada una contiene una lista de procesos que se llevan a cabo en ella.
- Procesos: Cada proceso tiene un nombre ("Inflado", "Cosido", "Empaque", etc.) y un grado de complejidad (por ejemplo, numérico de 1 a 5).
- Máquinas: Están ubicadas dentro de las plantas. Cada una posee número único global, marca y modelo.
- Las máquinas pueden salir de servicio y, en ese caso, se les asigna otra máquina para reemplazarlas.
- Técnicos: Operan las máquinas. De cada técnico se debe tener DNI, nombre, apellido, fecha de nacimiento, teléfonos de contacto (pueden ser múltiples).
- Cada técnico puede estar asignado a una máquina en un rango de fechas, indicando también el turno en el que trabaja (mañana, tarde o noche).
- Administradores: Poseen los mismos atributos que los técnicos. Se encargan de asignar técnicos a cada máquina e indican si alguna máquina está operativa o no.
- Al sistema podrán acceder solo los administradores.

## Patrones utilizados

**Patrón Singleton:**

- Implementado en la clase FabricaGolazo
- Asegura que solo exista una instancia de la fábrica en todo el sistema
  Se implementa mediante un constructor privado y un método getInstance() estático

**Patrón Strategy:**

- Implementado en el paquete funcionalidades
- Las diferentes capacidades de las máquinas (coser, inflar, empaquetar) están definidas como estrategias separadas
- Interfaz Funcionalidad con implementaciones concretas: CapazDeCoser, CapazDeInflar, CapazDeEmpaquetar