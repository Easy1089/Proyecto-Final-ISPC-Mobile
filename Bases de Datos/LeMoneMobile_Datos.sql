USE LeMoneMobile;

insert into TiposDePersonas(id, nombre) values (1, 'Consumidor final');
insert into TiposDePersonas(id, nombre) values (2, 'Proveedor');
select * from TiposDePersonas;

insert into TiposDeOperacion(id, nombre) values (1,'Ingreso de stock');
insert into TiposDeOperacion(id, nombre) values (2,'Egreso de stock');
select * from TiposDeOperacion;

insert into EstadosDeOrdenes(id, nombre) values (1,'Nueva');
insert into EstadosDeOrdenes(id, nombre) values (2,'En proceso');
insert into EstadosDeOrdenes(id, nombre) values (3,'Finalizada');
insert into EstadosDeOrdenes(id, nombre) values (4,'Anulada');
select * from EstadosDeOrdenes;

insert into TiposDeUsuarios(id, nombre) values (1,'Administrador');
insert into TiposDeUsuarios(id, nombre) values (2,'Usuario');
select * from TiposDeUsuarios;

insert into MediosDePago(id, nombre) values (1,'Efectivo');
insert into MediosDePago(id, nombre) values (2,'Débito');
insert into MediosDePago(id, nombre) values (3,'Crédito');
select * from MediosDePago;

insert into Categorias(id, nombre, descripcion, activoactualmente) values (1,'Categoría 1', 'Descripción', true);
insert into Categorias(id, nombre, descripcion, activoactualmente) values (2,'Categoría 2', 'Descripción', true);
insert into Categorias(id, nombre, descripcion, activoactualmente) values (3,'Categoría 3', 'Descripción', true);
select * from Categorias;