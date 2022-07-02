create database Chat_Team3;
go
use Chat_Team3;
go
create table Users(
	User_name nvarchar(50) primary key,
	Password nvarchar(20),
	NickName nvarchar(50),
	Avatar varbinary(max),
	Gender bit,
	Birthday date,
	Email nvarchar(250),
	Addres nvarchar(max),
	Role bit,
	Ip nvarchar(50),
	Time_off DateTime
)
go
create table Categories(
	ID int identity(1,1) primary key,
	Name nvarchar(250)
)
go
create table Rooms(
	ID int identity(1,1) primary key,
	Name nvarchar(250)
)
go
create table Members(
	ID int identity(1,1) primary key,
	ID_Room int References Rooms(ID),
	User_name nvarchar(50) References Users(User_name)
)
go
create table Messages(
	ID int identity(1,1) primary key,
	Message nvarchar(max),
	ID_Category int References Categories(ID),
	ID_Member int References Members(ID)
)
go
create table Contents(
	ID int identity(1,1) primary key,
	Content varbinary(max),
	ID_Message int References Messages(ID)
)
go
create table Friendship(
	ID int identity(1,1) primary key,
	User_name nvarchar(50) References Users(User_name),
	Friend_user_name nvarchar(50) References Users(User_name)
)
go