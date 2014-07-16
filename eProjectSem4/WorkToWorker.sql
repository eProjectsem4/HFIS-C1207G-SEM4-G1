Create Database WorkToWorker
Go
Use WorkToWorker
Go

Create table Account(id int Primary Key identity,
						email varchar(50),
						pass varchar(50),
						fullname nvarchar(50),
						phone varchar(20),
						company nvarchar(20),
						[role] varchar(15),
						addresss nvarchar(50),
						country nvarchar(20),
						imgProfile nvarchar(500),
						experience nvarchar(150),
						skills nvarchar(150),
						[money] int
						)

Go

insert into Account values ('khanhnd_b01438@fpt.aptech.ac.vn','e10adc3949ba59abbe56e057f20f883e','Nguyen Duc Khanh','','','Customer','','','')

Go

Create table Project(id int Primary key identity,
					name nvarchar(50),
					category nvarchar(50),
					idCustomer int Foreign key references Account(id),
					idWorker int Foreign key references Account(id),
					nameSkills varchar(100),
					descriptions nvarchar(500),
					attFile nvarchar(500),
					price int,
					startDate Date,
					endDate Date,
					[status] varchar(10))

Go
select * from Project
Create table OrderProject(id int Primary key identity,
							idProject int foreign key references Project(id),
							idAccount int foreign key references Account(id))

Go


Create table RateWorker (id int primary key identity,
							idCustomer int foreign key references Account(id),
							idWorker int foreign key references Account(id),
							star int)

Go

Create table RateCustomer (id int primary key identity,
							idCustomer int foreign key references Account(id),
							idWorker int foreign key references Account(id),
							star int)

Go

Create table FeedWorker (id int primary key identity,
							idCustomer int foreign key references Account(id),
							idWorker int foreign key references Account(id),
							content nvarchar(500))

Go

Create table FeedCustomer (id int primary key identity,
							idCustomer int foreign key references Account(id),
							idWorker int foreign key references Account(id),
							content nvarchar(500))

Go

Create table Topic(id int primary key identity,
					title nvarchar(100),
					content nvarchar(1000),
					idAccount int foreign key references Account(id),
					postDate Date)

Go



Create table Comment(id int primary key identity,
						idAccount int foreign key references Account(id),
						idTopic int foreign key references Topic(id),
						content nvarchar(1000),
						postDate Date)

Create table Balance (
					id int primary key identity,
					idAccount int foreign key references Account(id),
					[money] float,
					)