DROP DATABASE IF EXISTS LeMoneMobile;
CREATE DATABASE LeMoneMobile CHARACTER SET utf8mb4;
USE LeMoneMobile;

CREATE TABLE Categorias (
    Id INT auto_increment not null,
    Nombre VARCHAR(50) NOT NULL,
    Descripcion VARCHAR(150) NOT NULL,
    ActivoActualmente bit,
    PRIMARY KEY(Id)
);

CREATE TABLE Productos (
    Id INT auto_increment not null,
    Imagen nvarchar(50) null,
    Codigo nvarchar(20) not null,
    Nombre nvarchar(50) not null,
    Descripcion nvarchar(250) not null,
    InventarioMinimo INT,
    PrecioDeCosto decimal(17, 2),
    PrecioDeVenta decimal(17, 2),
    IdCategoria INT,
    ActivoActualmente bit,
    Estado char(1) NOT NULL default 'A',
	FechaAlta datetime NOT NULL,
    IdUsuarioDeAlta INT NOT NULL,
	FechaModificacion datetime NULL,
	IdUsuarioModificacion INT NULL,
	FechaBaja datetime NULL,
    IdUsuarioDeBaja INT NULL,
    PRIMARY KEY(Id)
);

CREATE TABLE TiposDePersonas (
    Id INT not null,
    Nombre nvarchar(50) not null,
    PRIMARY KEY(Id)
);

CREATE TABLE TiposDeOperacion (
    Id INT not null,
    Nombre nvarchar(50) not null,
    PRIMARY KEY(Id)
);

CREATE TABLE Personas (
    Id INT auto_increment not null,
    Imagen nvarchar(50) null,
    Apellido nvarchar(50) not null,
    Nombre nvarchar(50) not null,
    Telefono numeric null,
    Email nvarchar(50) null,
    IdTipoDePersona Int,
    ActivoActualmente bit not null default 1,
    Domicilio nvarchar(200) null,
    Estado char(1) NOT NULL default 'A',
	FechaAlta datetime NOT NULL,
    IdUsuarioDeAlta INT NOT NULL,
	FechaModificacion datetime NULL,
	IdUsuarioModificacion INT NULL,
    PRIMARY KEY(Id)
);


CREATE TABLE EstadosDeOrdenes (
    Id INT auto_increment not null,
    Nombre nvarchar(50) null,
    PRIMARY KEY(Id)
);

CREATE TABLE Ordenes (
    Id INT auto_increment not null,
	IdTipoDeOperacion int,
    IdPersona INT not null, 
    IdEstadoDeOrden int not null,
    NroDeOrden int not null,    
    NroTransaccion int not null,   
	IdMedioDePago int,
    ImporteNeto decimal(17, 4),
    ImporteIVA decimal(17, 4),
    ImporteTotal decimal(17, 4),
    Observaciones nvarchar(200) null,
    Estado char(1) NOT NULL default 'A',
	FechaAlta datetime NOT NULL,
    IdUsuarioDeAlta INT NOT NULL,
    PRIMARY KEY(Id)
);

CREATE TABLE MediosDePago (
    Id INT auto_increment not null,
    Nombre nvarchar(50) not null,
	ActivoActualmente bit not null default 1,
    PRIMARY KEY(Id)
);

CREATE TABLE Ordenes_Detalle (
    Id INT auto_increment not null,
    IdOrden int not null,
	IdProducto int,
	Cantidad decimal(17, 2),    
    Observaciones nvarchar(200) null,
    Estado char(1) NOT NULL default 'A',
	FechaAlta datetime NOT NULL,
    IdUsuarioDeAlta INT NOT NULL,
	FechaBaja datetime NULL,
    IdUsuarioDeBaja INT NULL,
    PRIMARY KEY(Id)
);

CREATE TABLE TiposDeUsuarios (
    Id INT not null,
    Nombre nvarchar(50) not null,
    PRIMARY KEY(Id)
);

CREATE TABLE Usuarios (
    Id INT auto_increment not null,
    IdTipoDeUsuario int not null,
    IdPersona int not null,
    NombreDeUsuario nvarchar(50) not null,
    Password nvarchar(50) not null,
    ActivoActualmente bit not null default 1,
    Estado char(1) NOT NULL default 'A',
	FechaAlta datetime NOT NULL,
    IdUsuarioDeAlta INT NULL,
	FechaModificacion datetime NULL,
	IdUsuarioModificacion INT NULL,
    PRIMARY KEY(Id)
);

ALTER TABLE Usuarios ADD FOREIGN KEY(IdTipoDeUsuario) REFERENCES TiposDeUsuarios(Id);
ALTER TABLE Usuarios ADD FOREIGN KEY(IdUsuarioDeAlta) REFERENCES Usuarios(Id);
ALTER TABLE Usuarios ADD FOREIGN KEY(IdPersona) REFERENCES Personas(Id);
ALTER TABLE Productos ADD FOREIGN KEY(IdCategoria) REFERENCES Categorias(Id);
ALTER TABLE Productos ADD FOREIGN KEY(IdUsuarioDeAlta) REFERENCES Usuarios(Id);
ALTER TABLE Personas ADD FOREIGN KEY(IdTipoDePersona) REFERENCES TiposDePersonas(Id);
ALTER TABLE Ordenes ADD FOREIGN KEY(IdEstadoDeOrden) REFERENCES EstadosDeOrdenes(Id);
ALTER TABLE Ordenes ADD FOREIGN KEY(IdTipoDeOperacion) REFERENCES TiposDeOperacion(Id);
ALTER TABLE Ordenes ADD FOREIGN KEY(IdPersona) REFERENCES Personas(Id);
ALTER TABLE Ordenes ADD FOREIGN KEY(IdMedioDePago) REFERENCES MediosDePago(Id);
ALTER TABLE Ordenes ADD FOREIGN KEY(IdUsuarioDeAlta) REFERENCES Usuarios(Id);
ALTER TABLE Ordenes_Detalle ADD FOREIGN KEY(IdOrden) REFERENCES Ordenes(Id);
ALTER TABLE Ordenes_Detalle ADD FOREIGN KEY(IdProducto) REFERENCES Productos(Id);
ALTER TABLE Ordenes_Detalle ADD FOREIGN KEY(IdUsuarioDeAlta) REFERENCES Usuarios(Id);