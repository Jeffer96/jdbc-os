create database Airefresco_DB;

drop table Usuario;
create table Usuario(
cedula bigint primary key not null,
nombres varchar(150) not null,
apellidos varchar(150) not null,
telefono int,
email varchar(250),
fecha_nacimiento datetime,
activo boolean,
nick varchar(50) not null unique,
pass varchar(500) not null,
roleName varchar(50) not null,
modificado Date,
creado Date
);


create table Cliente(
cli_nit bigint primary key not null,
cli_codigo int,
cli_nombre varchar(150) not null,
cli_fecha_contratacion datetime,
cli_tipo int,
cli_activo boolean not null
);

create table Contacto(
con_cedula bigint primary key not null,
con_nombres varchar(150) not null,
con_apellidos varchar(150) not null,
con_telefono int,
con_celular int,
con_cliente_id bigint not null,
foreign key (con_cliente_id) references Cliente(cli_nit),
con_email varchar(250)
);

create table Sucursal(
suc_id int primary key auto_increment not null,
suc_nombre varchar(100),
suc_direccion varchar(100) not null,
suc_cliente bigint not null,
suc_ciudad int not null,
foreign key (suc_cliente) references Cliente(cli_nit),
foreign key (suc_ciudad) references Ciudad(ciu_id)
);

create table Ciudad(
ciu_id int primary key auto_increment not null,
ciu_nombre varchar(150) not null,
ciu_codigo varchar(20),
ciu_departamento int,
foreign key (ciu_departamento) references Departamento(dep_id)
);

create table Departamento(
dep_id int primary key auto_increment not null,
dep_nombre varchar(150) not null,
dep_codigo varchar(20),
dep_pais int,
foreign key (dep_pais) references Pais(pai_id)
);

create table Pais(
pai_id int primary key auto_increment not null,
pai_nombre varchar(150) not null,
pai_codigo varchar(20)
);

select * from Usuario where activo=true;