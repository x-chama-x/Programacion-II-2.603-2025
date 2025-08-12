# **2) Tienda de Productos Tecnológicos**

## Enunciado

Se requiere implementar una aplicación para gestionar el circuito de ventas de una empresa comercializadora de productos tecnológicos. El sistema debe implementar:

- Proveedores: Debe tener un CUIT único, nombre, dirección, teléfono y página web. Debe considerarse que cada proveedor ofrece solo un único producto, pero un producto puede ser ofrecido por varios proveedores.
- Clientes: Tiene un CUIT único, nombre, dirección y teléfono. Cada cliente puede realizar múltiples compras.
- Administradores: Debe tener un CUIT único, nombre, dirección y teléfono.
- Una dirección está compuesta por calle, número, comuna, ciudad y código postal, y serán utilizadas por clientes, administradores y proveedores.
- Tanto clientes, proveedores y administradores podrán ser representados mediante una clase que implemente una interfaz Contactable.
- Productos: Tiene un ID único, nombre, precio actual, stock disponible, proveedor y categoría.
- Categorías: Tiene un ID, nombre y descripción.
- Cada producto pertenece a una sola categoría, pero una categoría puede agrupar varios productos.
- Ventas: Tiene un número de factura único, fecha, cliente asociado, descuento aplicado y monto final.
- Cada venta incluye uno o más productos. De cada producto vendido se registra: precio al momento de la venta, 
  cantidad vendida, monto total parcial de ese producto (precio x cantidad).
- El administrador puede agregar, actualizar o eliminar clientes y proveedores. 
  También puede cargar al stock productos faltantes, así como ver todas las ventas realizadas.
- Los clientes solo pueden ver los productos en stock y sus precios para realizar la compra.
- Los proveedores no pueden acceder al sistema.