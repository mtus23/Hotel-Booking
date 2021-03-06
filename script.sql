USE [master]
GO
/****** Object:  Database [J3LP0012]    Script Date: 11/7/2020 5:48:03 PM ******/
CREATE DATABASE [J3LP0012]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'L3LP0012', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\L3LP0012.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'L3LP0012_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\L3LP0012_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [J3LP0012] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [J3LP0012].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [J3LP0012] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [J3LP0012] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [J3LP0012] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [J3LP0012] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [J3LP0012] SET ARITHABORT OFF 
GO
ALTER DATABASE [J3LP0012] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [J3LP0012] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [J3LP0012] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [J3LP0012] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [J3LP0012] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [J3LP0012] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [J3LP0012] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [J3LP0012] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [J3LP0012] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [J3LP0012] SET  DISABLE_BROKER 
GO
ALTER DATABASE [J3LP0012] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [J3LP0012] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [J3LP0012] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [J3LP0012] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [J3LP0012] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [J3LP0012] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [J3LP0012] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [J3LP0012] SET RECOVERY FULL 
GO
ALTER DATABASE [J3LP0012] SET  MULTI_USER 
GO
ALTER DATABASE [J3LP0012] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [J3LP0012] SET DB_CHAINING OFF 
GO
ALTER DATABASE [J3LP0012] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [J3LP0012] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [J3LP0012] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'J3LP0012', N'ON'
GO
ALTER DATABASE [J3LP0012] SET QUERY_STORE = OFF
GO
USE [J3LP0012]
GO
/****** Object:  User [user]    Script Date: 11/7/2020 5:48:03 PM ******/
CREATE USER [user] FOR LOGIN [user] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  Table [dbo].[tblArea]    Script Date: 11/7/2020 5:48:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblArea](
	[areaId] [int] IDENTITY(1,1) NOT NULL,
	[areaName] [nvarchar](50) NULL,
 CONSTRAINT [PK_tblArea] PRIMARY KEY CLUSTERED 
(
	[areaId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblCode]    Script Date: 11/7/2020 5:48:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblCode](
	[codeId] [varchar](50) NOT NULL,
	[discountPercent] [int] NULL,
	[dateCreate] [date] NULL,
	[codeName] [nvarchar](50) NULL,
	[statusId] [int] NULL,
	[expirationDate] [date] NULL,
 CONSTRAINT [PK_tblCode] PRIMARY KEY CLUSTERED 
(
	[codeId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblHotel]    Script Date: 11/7/2020 5:48:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblHotel](
	[hotelId] [int] IDENTITY(1,1) NOT NULL,
	[hotelName] [nvarchar](50) NULL,
	[areaId] [int] NOT NULL,
	[image] [varchar](max) NULL,
	[statusId] [int] NULL,
	[description] [nvarchar](max) NULL,
 CONSTRAINT [PK_tblHotel] PRIMARY KEY CLUSTERED 
(
	[hotelId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblHotelRoomType]    Script Date: 11/7/2020 5:48:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblHotelRoomType](
	[hotelId] [int] NOT NULL,
	[price] [int] NULL,
	[quantity] [int] NULL,
	[available] [int] NULL,
	[hotelRoomId] [int] IDENTITY(1,1) NOT NULL,
	[roomTypeId] [int] NULL,
	[statusId] [int] NULL,
	[image] [varchar](max) NULL,
 CONSTRAINT [PK_tblHotelRoom] PRIMARY KEY CLUSTERED 
(
	[hotelRoomId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblOrder]    Script Date: 11/7/2020 5:48:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblOrder](
	[orderId] [uniqueidentifier] NOT NULL,
	[email] [varchar](50) NULL,
	[totalPrice] [int] NULL,
	[createDate] [datetime] NULL,
	[fromDate] [date] NULL,
	[toDate] [date] NULL,
	[codeId] [varchar](50) NULL,
	[statusId] [int] NULL,
	[name] [varchar](50) NULL,
	[address] [varchar](50) NULL,
	[phone] [varchar](11) NULL,
 CONSTRAINT [PK_tblOrder] PRIMARY KEY CLUSTERED 
(
	[orderId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblOrderDetail]    Script Date: 11/7/2020 5:48:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblOrderDetail](
	[orderDetailId] [int] IDENTITY(1,1) NOT NULL,
	[orderId] [uniqueidentifier] NULL,
	[hotelRoomId] [int] NULL,
	[quantity] [int] NULL,
	[price] [int] NULL,
 CONSTRAINT [PK_tblOrderDetail] PRIMARY KEY CLUSTERED 
(
	[orderDetailId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblRate]    Script Date: 11/7/2020 5:48:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblRate](
	[rateId] [int] IDENTITY(1,1) NOT NULL,
	[email] [varchar](50) NULL,
	[point] [int] NULL,
	[hotelRoomId] [int] NULL,
	[orderId] [uniqueidentifier] NULL,
 CONSTRAINT [PK_tblRate] PRIMARY KEY CLUSTERED 
(
	[rateId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblRole]    Script Date: 11/7/2020 5:48:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblRole](
	[roleId] [int] IDENTITY(1,1) NOT NULL,
	[roleName] [varchar](50) NULL,
 CONSTRAINT [PK_tblRole] PRIMARY KEY CLUSTERED 
(
	[roleId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblRoomType]    Script Date: 11/7/2020 5:48:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblRoomType](
	[roomTypeId] [int] IDENTITY(1,1) NOT NULL,
	[roomTypeName] [nvarchar](50) NULL,
	[statusId] [int] NULL,
 CONSTRAINT [PK_tblRoomType] PRIMARY KEY CLUSTERED 
(
	[roomTypeId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblStatus]    Script Date: 11/7/2020 5:48:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblStatus](
	[statusId] [int] IDENTITY(1,1) NOT NULL,
	[statusName] [varchar](50) NULL,
 CONSTRAINT [PK_tblStatus] PRIMARY KEY CLUSTERED 
(
	[statusId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblUser]    Script Date: 11/7/2020 5:48:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblUser](
	[email] [varchar](50) NOT NULL,
	[phone] [varchar](10) NULL,
	[name] [varchar](50) NULL,
	[address] [varchar](50) NULL,
	[createDate] [date] NULL,
	[roleId] [int] NULL,
	[password] [varchar](100) NULL,
	[statusId] [int] NULL,
 CONSTRAINT [PK_tblUser] PRIMARY KEY CLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[tblArea] ON 

INSERT [dbo].[tblArea] ([areaId], [areaName]) VALUES (1, N'Vũng Tàu')
INSERT [dbo].[tblArea] ([areaId], [areaName]) VALUES (2, N'Đà Lạt')
INSERT [dbo].[tblArea] ([areaId], [areaName]) VALUES (3, N'Nha Trang')
SET IDENTITY_INSERT [dbo].[tblArea] OFF
INSERT [dbo].[tblCode] ([codeId], [discountPercent], [dateCreate], [codeName], [statusId], [expirationDate]) VALUES (N'1234444444', 50, CAST(N'2020-11-04' AS Date), N'discount 11/11', 3, CAST(N'2020-11-22' AS Date))
INSERT [dbo].[tblCode] ([codeId], [discountPercent], [dateCreate], [codeName], [statusId], [expirationDate]) VALUES (N'123454', 10, CAST(N'2020-11-04' AS Date), NULL, 3, CAST(N'2020-11-29' AS Date))
INSERT [dbo].[tblCode] ([codeId], [discountPercent], [dateCreate], [codeName], [statusId], [expirationDate]) VALUES (N'123455555', 50, CAST(N'2020-11-04' AS Date), N'discount 11/11', 3, CAST(N'2020-11-08' AS Date))
INSERT [dbo].[tblCode] ([codeId], [discountPercent], [dateCreate], [codeName], [statusId], [expirationDate]) VALUES (N'123456', 10, CAST(N'2020-11-04' AS Date), N'discount 11/11', 3, CAST(N'2020-11-04' AS Date))
INSERT [dbo].[tblCode] ([codeId], [discountPercent], [dateCreate], [codeName], [statusId], [expirationDate]) VALUES (N'1234566', 10, CAST(N'2020-11-04' AS Date), NULL, 3, CAST(N'2020-11-08' AS Date))
INSERT [dbo].[tblCode] ([codeId], [discountPercent], [dateCreate], [codeName], [statusId], [expirationDate]) VALUES (N'123457', 10, CAST(N'2020-11-04' AS Date), NULL, 3, CAST(N'2020-11-08' AS Date))
SET IDENTITY_INSERT [dbo].[tblHotel] ON 

INSERT [dbo].[tblHotel] ([hotelId], [hotelName], [areaId], [image], [statusId], [description]) VALUES (1, N'Vũng Tàu villa', 1, N'https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20022759-bc1373995692f4946fc83333c506082b.jpeg?tr=q-40,c-at_max,w-740,h-500&_src=imagekit', 1, N'Tọa lạc tại thành phố Vũng Tàu, cách Bãi Dứa 450 m, OYO 394 Camy Hotel cung cấp miễn phí cả WiFi và chỗ đỗ xe riêng. super clean, modern, nice host, have space, good price

')
INSERT [dbo].[tblHotel] ([hotelId], [hotelName], [areaId], [image], [statusId], [description]) VALUES (2, N'Sea Beach Hotel', 1, N'https://cf.bstatic.com/xdata/images/hotel/square200/217111746.webp?k=23bde5fc1dd826cf43a2a3af8c573878f98346cb48cd27554a982f4f137e9f74&o=', 1, N'Sea Beach Hotel tọa lạc tại thành phố Vũng Tàu, cách Tượng Chúa Ki-tô Vua 1,5 km và Mũi Nghinh Phong 2,2 km. Very clean. Great location, staff very helpful and speak English.')
INSERT [dbo].[tblHotel] ([hotelId], [hotelName], [areaId], [image], [statusId], [description]) VALUES (3, N'Hòa Bình Hotel', 1, N'https://cf.bstatic.com/xdata/images/hotel/square200/270829527.webp?k=5d012050b64ed11425b495bd371f95acb8bcdfaeb2b6404ebfd82036a3fdfd5a&o=', 1, N'Tọa lạc tại thành phố Vũng Tàu, cách Bãi Sau chưa đầy 1 km và Tượng Chúa Ki-tô Vua 20 phút đi bộ, Hòa Bình Hotel cung cấp chỗ nghỉ với tiện nghi BBQ và WiFi miễn phí cũng như chỗ đỗ xe riêng miễn phí... Everything ok, good location, the staff so friendly and helpful')
SET IDENTITY_INSERT [dbo].[tblHotel] OFF
SET IDENTITY_INSERT [dbo].[tblHotelRoomType] ON 

INSERT [dbo].[tblHotelRoomType] ([hotelId], [price], [quantity], [available], [hotelRoomId], [roomTypeId], [statusId], [image]) VALUES (1, 500000, 5, 5, 1, 1, 1, N'data:image/jpeg;base64,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')
INSERT [dbo].[tblHotelRoomType] ([hotelId], [price], [quantity], [available], [hotelRoomId], [roomTypeId], [statusId], [image]) VALUES (2, 450000, 5, 0, 2, 1, 1, N'data:image/jpeg;base64,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')
INSERT [dbo].[tblHotelRoomType] ([hotelId], [price], [quantity], [available], [hotelRoomId], [roomTypeId], [statusId], [image]) VALUES (3, 600000, 10, 8, 3, 1, 1, N'https://media.nomadicmatt.com/hotelreview1a.jpg')
INSERT [dbo].[tblHotelRoomType] ([hotelId], [price], [quantity], [available], [hotelRoomId], [roomTypeId], [statusId], [image]) VALUES (1, 45000, 11, 11, 4, 2, 1, N'https://i1.wp.com/www.turningleftforless.com/wp-content/uploads/2017/05/IMG_0012-2.jpg')
SET IDENTITY_INSERT [dbo].[tblHotelRoomType] OFF
INSERT [dbo].[tblOrder] ([orderId], [email], [totalPrice], [createDate], [fromDate], [toDate], [codeId], [statusId], [name], [address], [phone]) VALUES (N'ceb5d266-7b20-eb11-949a-509a4cca4140', N'tuss@mail.com', 2700000, CAST(N'2020-11-06T04:59:55.263' AS DateTime), CAST(N'2020-11-06' AS Date), CAST(N'2020-11-07' AS Date), NULL, 10, N'tuss', N'tus''s address', N'1234567890')
INSERT [dbo].[tblOrder] ([orderId], [email], [totalPrice], [createDate], [fromDate], [toDate], [codeId], [statusId], [name], [address], [phone]) VALUES (N'13318e86-d020-eb11-949d-509a4cca4140', N'tunhmse140357@fpt.edu.vn', 3375000, CAST(N'2020-11-06T15:09:15.737' AS DateTime), CAST(N'2020-11-06' AS Date), CAST(N'2020-11-07' AS Date), N'1234444444', 10, N'TuMNH', N'minhtus', N'1234567890')
INSERT [dbo].[tblOrder] ([orderId], [email], [totalPrice], [createDate], [fromDate], [toDate], [codeId], [statusId], [name], [address], [phone]) VALUES (N'f975867b-d220-eb11-949d-509a4cca4140', N'tunhmse140357@fpt.edu.vn', 3600000, CAST(N'2020-11-06T15:23:16.230' AS DateTime), CAST(N'2020-11-06' AS Date), CAST(N'2020-11-07' AS Date), NULL, 6, N'TuMNH', N'minhtus', N'1234567890')
INSERT [dbo].[tblOrder] ([orderId], [email], [totalPrice], [createDate], [fromDate], [toDate], [codeId], [statusId], [name], [address], [phone]) VALUES (N'3db39e39-d820-eb11-949d-509a4cca4140', N'tunhmse140357@fpt.edu.vn', 6000000, CAST(N'2020-11-07T16:04:22.640' AS DateTime), CAST(N'2020-11-12' AS Date), CAST(N'2020-11-22' AS Date), NULL, 6, N'TuMNH', N'minhtus', N'1234567890')
SET IDENTITY_INSERT [dbo].[tblOrderDetail] ON 

INSERT [dbo].[tblOrderDetail] ([orderDetailId], [orderId], [hotelRoomId], [quantity], [price]) VALUES (1, N'ceb5d266-7b20-eb11-949a-509a4cca4140', 2, 2, 450000)
INSERT [dbo].[tblOrderDetail] ([orderDetailId], [orderId], [hotelRoomId], [quantity], [price]) VALUES (2, N'13318e86-d020-eb11-949d-509a4cca4140', 2, 3, 450000)
INSERT [dbo].[tblOrderDetail] ([orderDetailId], [orderId], [hotelRoomId], [quantity], [price]) VALUES (3, N'f975867b-d220-eb11-949d-509a4cca4140', 3, 1, 600000)
INSERT [dbo].[tblOrderDetail] ([orderDetailId], [orderId], [hotelRoomId], [quantity], [price]) VALUES (4, N'3db39e39-d820-eb11-949d-509a4cca4140', 3, 1, 600000)
SET IDENTITY_INSERT [dbo].[tblOrderDetail] OFF
SET IDENTITY_INSERT [dbo].[tblRate] ON 

INSERT [dbo].[tblRate] ([rateId], [email], [point], [hotelRoomId], [orderId]) VALUES (1, N'tuss@mail.com', 7, 2, N'ceb5d266-7b20-eb11-949a-509a4cca4140')
INSERT [dbo].[tblRate] ([rateId], [email], [point], [hotelRoomId], [orderId]) VALUES (2, N'tuss@mail.com', 4, 2, N'ceb5d266-7b20-eb11-949a-509a4cca4140')
INSERT [dbo].[tblRate] ([rateId], [email], [point], [hotelRoomId], [orderId]) VALUES (3, N'tuss@mail.com', 4, 2, N'ceb5d266-7b20-eb11-949a-509a4cca4140')
SET IDENTITY_INSERT [dbo].[tblRate] OFF
SET IDENTITY_INSERT [dbo].[tblRole] ON 

INSERT [dbo].[tblRole] ([roleId], [roleName]) VALUES (1, N'admin')
INSERT [dbo].[tblRole] ([roleId], [roleName]) VALUES (2, N'user')
SET IDENTITY_INSERT [dbo].[tblRole] OFF
SET IDENTITY_INSERT [dbo].[tblRoomType] ON 

INSERT [dbo].[tblRoomType] ([roomTypeId], [roomTypeName], [statusId]) VALUES (1, N'vvip', 1)
INSERT [dbo].[tblRoomType] ([roomTypeId], [roomTypeName], [statusId]) VALUES (2, N'Luxury giường đôi', 1)
INSERT [dbo].[tblRoomType] ([roomTypeId], [roomTypeName], [statusId]) VALUES (3, N'Luxury royal', 1)
SET IDENTITY_INSERT [dbo].[tblRoomType] OFF
SET IDENTITY_INSERT [dbo].[tblStatus] ON 

INSERT [dbo].[tblStatus] ([statusId], [statusName]) VALUES (1, N'valid')
INSERT [dbo].[tblStatus] ([statusId], [statusName]) VALUES (2, N'invalid')
INSERT [dbo].[tblStatus] ([statusId], [statusName]) VALUES (3, N'unused')
INSERT [dbo].[tblStatus] ([statusId], [statusName]) VALUES (4, N'used')
INSERT [dbo].[tblStatus] ([statusId], [statusName]) VALUES (5, N'active')
INSERT [dbo].[tblStatus] ([statusId], [statusName]) VALUES (6, N'inactive')
INSERT [dbo].[tblStatus] ([statusId], [statusName]) VALUES (7, N'booked')
INSERT [dbo].[tblStatus] ([statusId], [statusName]) VALUES (10, N'done')
SET IDENTITY_INSERT [dbo].[tblStatus] OFF
INSERT [dbo].[tblUser] ([email], [phone], [name], [address], [createDate], [roleId], [password], [statusId]) VALUES (N'admin', N'0123456789', N'admin', N'admin''s address', CAST(N'2020-11-04' AS Date), 1, N'8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 5)
INSERT [dbo].[tblUser] ([email], [phone], [name], [address], [createDate], [roleId], [password], [statusId]) VALUES (N'mtuss@mail.com', N'1234567890', N'tuss', N'tus''s address', CAST(N'2020-10-31' AS Date), 2, N'1be2e452b46d7a0d9656bbb1f768e8248eba1b75baed65f5d99eafa948899a6a', 5)
INSERT [dbo].[tblUser] ([email], [phone], [name], [address], [createDate], [roleId], [password], [statusId]) VALUES (N'mtussss@mail.com', N'1234567890', N'tuss', N'tus''s address', CAST(N'2020-11-04' AS Date), 2, N'1be2e452b46d7a0d9656bbb1f768e8248eba1b75baed65f5d99eafa948899a6a', 5)
INSERT [dbo].[tblUser] ([email], [phone], [name], [address], [createDate], [roleId], [password], [statusId]) VALUES (N'tunhmse140357@fpt.edu.vn', N'1234567890', N'TuMNH', N'minhtus', CAST(N'2020-11-07' AS Date), 2, N'1be2e452b46d7a0d9656bbb1f768e8248eba1b75baed65f5d99eafa948899a6a', 5)
INSERT [dbo].[tblUser] ([email], [phone], [name], [address], [createDate], [roleId], [password], [statusId]) VALUES (N'tus@mail.com', N'132', N'tus', N'tus''s address', CAST(N'2020-10-31' AS Date), 2, N'1be2e452b46d7a0d9656bbb1f768e8248eba1b75baed65f5d99eafa948899a6a', 5)
INSERT [dbo].[tblUser] ([email], [phone], [name], [address], [createDate], [roleId], [password], [statusId]) VALUES (N'tuss@mail.com', N'1234567890', N'tuss', N'tus''s address', CAST(N'2020-10-31' AS Date), 2, N'1be2e452b46d7a0d9656bbb1f768e8248eba1b75baed65f5d99eafa948899a6a', 5)
INSERT [dbo].[tblUser] ([email], [phone], [name], [address], [createDate], [roleId], [password], [statusId]) VALUES (N'tusss@mail.com', N'1234567890', N'tuss', N'tus''s address', CAST(N'2020-10-31' AS Date), 2, N'1be2e452b46d7a0d9656bbb1f768e8248eba1b75baed65f5d99eafa948899a6a', 5)
INSERT [dbo].[tblUser] ([email], [phone], [name], [address], [createDate], [roleId], [password], [statusId]) VALUES (N'tussss@mail.com', N'1234567890', N'tuss', N'tus''s address', CAST(N'2020-10-31' AS Date), 2, N'1be2e452b46d7a0d9656bbb1f768e8248eba1b75baed65f5d99eafa948899a6a', 5)
ALTER TABLE [dbo].[tblOrder] ADD  CONSTRAINT [DF_tblOrder_orderId]  DEFAULT (newsequentialid()) FOR [orderId]
GO
ALTER TABLE [dbo].[tblCode]  WITH CHECK ADD  CONSTRAINT [FK_tblCode_tblStatus] FOREIGN KEY([statusId])
REFERENCES [dbo].[tblStatus] ([statusId])
GO
ALTER TABLE [dbo].[tblCode] CHECK CONSTRAINT [FK_tblCode_tblStatus]
GO
ALTER TABLE [dbo].[tblHotel]  WITH CHECK ADD  CONSTRAINT [FK_tblHotel_tblArea] FOREIGN KEY([areaId])
REFERENCES [dbo].[tblArea] ([areaId])
GO
ALTER TABLE [dbo].[tblHotel] CHECK CONSTRAINT [FK_tblHotel_tblArea]
GO
ALTER TABLE [dbo].[tblHotelRoomType]  WITH CHECK ADD  CONSTRAINT [FK_tblHotelRoom_tblHotel] FOREIGN KEY([hotelId])
REFERENCES [dbo].[tblHotel] ([hotelId])
GO
ALTER TABLE [dbo].[tblHotelRoomType] CHECK CONSTRAINT [FK_tblHotelRoom_tblHotel]
GO
ALTER TABLE [dbo].[tblHotelRoomType]  WITH CHECK ADD  CONSTRAINT [FK_tblHotelRoomType_tblRoomType] FOREIGN KEY([roomTypeId])
REFERENCES [dbo].[tblRoomType] ([roomTypeId])
GO
ALTER TABLE [dbo].[tblHotelRoomType] CHECK CONSTRAINT [FK_tblHotelRoomType_tblRoomType]
GO
ALTER TABLE [dbo].[tblHotelRoomType]  WITH CHECK ADD  CONSTRAINT [FK_tblHotelRoomType_tblStatus] FOREIGN KEY([statusId])
REFERENCES [dbo].[tblStatus] ([statusId])
GO
ALTER TABLE [dbo].[tblHotelRoomType] CHECK CONSTRAINT [FK_tblHotelRoomType_tblStatus]
GO
ALTER TABLE [dbo].[tblOrder]  WITH CHECK ADD  CONSTRAINT [FK_tblOrder_tblCode] FOREIGN KEY([codeId])
REFERENCES [dbo].[tblCode] ([codeId])
GO
ALTER TABLE [dbo].[tblOrder] CHECK CONSTRAINT [FK_tblOrder_tblCode]
GO
ALTER TABLE [dbo].[tblOrder]  WITH CHECK ADD  CONSTRAINT [FK_tblOrder_tblStatus] FOREIGN KEY([statusId])
REFERENCES [dbo].[tblStatus] ([statusId])
GO
ALTER TABLE [dbo].[tblOrder] CHECK CONSTRAINT [FK_tblOrder_tblStatus]
GO
ALTER TABLE [dbo].[tblOrder]  WITH CHECK ADD  CONSTRAINT [FK_tblOrder_tblUser] FOREIGN KEY([email])
REFERENCES [dbo].[tblUser] ([email])
GO
ALTER TABLE [dbo].[tblOrder] CHECK CONSTRAINT [FK_tblOrder_tblUser]
GO
ALTER TABLE [dbo].[tblOrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_tblOrderDetail_tblHotelRoom] FOREIGN KEY([hotelRoomId])
REFERENCES [dbo].[tblHotelRoomType] ([hotelRoomId])
GO
ALTER TABLE [dbo].[tblOrderDetail] CHECK CONSTRAINT [FK_tblOrderDetail_tblHotelRoom]
GO
ALTER TABLE [dbo].[tblOrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_tblOrderDetail_tblOrder] FOREIGN KEY([orderId])
REFERENCES [dbo].[tblOrder] ([orderId])
GO
ALTER TABLE [dbo].[tblOrderDetail] CHECK CONSTRAINT [FK_tblOrderDetail_tblOrder]
GO
ALTER TABLE [dbo].[tblRate]  WITH CHECK ADD  CONSTRAINT [FK_tblRate_tblHotelRoomType] FOREIGN KEY([hotelRoomId])
REFERENCES [dbo].[tblHotelRoomType] ([hotelRoomId])
GO
ALTER TABLE [dbo].[tblRate] CHECK CONSTRAINT [FK_tblRate_tblHotelRoomType]
GO
ALTER TABLE [dbo].[tblRate]  WITH CHECK ADD  CONSTRAINT [FK_tblRate_tblOrder] FOREIGN KEY([orderId])
REFERENCES [dbo].[tblOrder] ([orderId])
GO
ALTER TABLE [dbo].[tblRate] CHECK CONSTRAINT [FK_tblRate_tblOrder]
GO
ALTER TABLE [dbo].[tblRate]  WITH CHECK ADD  CONSTRAINT [FK_tblRate_tblUser] FOREIGN KEY([email])
REFERENCES [dbo].[tblUser] ([email])
GO
ALTER TABLE [dbo].[tblRate] CHECK CONSTRAINT [FK_tblRate_tblUser]
GO
ALTER TABLE [dbo].[tblRoomType]  WITH CHECK ADD  CONSTRAINT [FK_tblRoomType_tblStatus] FOREIGN KEY([statusId])
REFERENCES [dbo].[tblStatus] ([statusId])
GO
ALTER TABLE [dbo].[tblRoomType] CHECK CONSTRAINT [FK_tblRoomType_tblStatus]
GO
ALTER TABLE [dbo].[tblUser]  WITH CHECK ADD  CONSTRAINT [FK_tblUser_tblRole] FOREIGN KEY([roleId])
REFERENCES [dbo].[tblRole] ([roleId])
GO
ALTER TABLE [dbo].[tblUser] CHECK CONSTRAINT [FK_tblUser_tblRole]
GO
USE [master]
GO
ALTER DATABASE [J3LP0012] SET  READ_WRITE 
GO
