USE [master]
GO
/****** Object:  Database [economic_cafe]    Script Date: 02/10/2023 10:33:30 PM ******/
CREATE DATABASE [economic_cafe]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'economic_cafe', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\economic_cafe.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'economic_cafe_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\economic_cafe_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [economic_cafe] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [economic_cafe].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [economic_cafe] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [economic_cafe] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [economic_cafe] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [economic_cafe] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [economic_cafe] SET ARITHABORT OFF 
GO
ALTER DATABASE [economic_cafe] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [economic_cafe] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [economic_cafe] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [economic_cafe] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [economic_cafe] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [economic_cafe] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [economic_cafe] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [economic_cafe] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [economic_cafe] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [economic_cafe] SET  ENABLE_BROKER 
GO
ALTER DATABASE [economic_cafe] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [economic_cafe] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [economic_cafe] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [economic_cafe] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [economic_cafe] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [economic_cafe] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [economic_cafe] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [economic_cafe] SET RECOVERY FULL 
GO
ALTER DATABASE [economic_cafe] SET  MULTI_USER 
GO
ALTER DATABASE [economic_cafe] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [economic_cafe] SET DB_CHAINING OFF 
GO
ALTER DATABASE [economic_cafe] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [economic_cafe] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [economic_cafe] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [economic_cafe] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'economic_cafe', N'ON'
GO
ALTER DATABASE [economic_cafe] SET QUERY_STORE = OFF
GO
USE [economic_cafe]
GO
/****** Object:  Table [dbo].[Bill]    Script Date: 02/10/2023 10:33:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Bill](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[id_user] [int] NOT NULL,
	[id_receiver] [int] NOT NULL,
	[status] [int] NOT NULL,
	[description] [nvarchar](200) NULL,
	[done_date] [datetime] NULL,
	[id_payments] [int] NOT NULL,
	[id_shipping_unit] [int] NOT NULL,
	[created_at] [datetime] NOT NULL,
	[update_at] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Bill_Discounts]    Script Date: 02/10/2023 10:33:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Bill_Discounts](
	[id_bill] [int] NOT NULL,
	[id_discount] [varchar](20) NOT NULL,
 CONSTRAINT [pk_bill_discounts] PRIMARY KEY CLUSTERED 
(
	[id_bill] ASC,
	[id_discount] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 02/10/2023 10:33:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[description] [nvarchar](500) NULL,
	[show_on] [bit] NOT NULL,
	[thumbnail] [varchar](400) NOT NULL,
	[created_at] [datetime] NOT NULL,
	[updated_at] [datetime] NOT NULL,
	[deleted] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Category_Post]    Script Date: 02/10/2023 10:33:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category_Post](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[title] [nvarchar](400) NOT NULL,
	[id_category] [int] NULL,
	[contents] [text] NULL,
	[thumbnail] [varchar](400) NULL,
	[created_at] [datetime] NOT NULL,
	[updated_at] [datetime] NOT NULL,
	[deleted] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Comments]    Script Date: 02/10/2023 10:33:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Comments](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[id_product] [varchar](20) NOT NULL,
	[id_user] [int] NOT NULL,
	[content] [nvarchar](400) NOT NULL,
	[rep_id] [int] NULL,
	[deleted] [bit] NOT NULL,
	[created_at] [datetime] NOT NULL,
	[updated_at] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Detail_Bill]    Script Date: 02/10/2023 10:33:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Detail_Bill](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[id_bill] [int] NOT NULL,
	[id_product] [varchar](20) NOT NULL,
	[quantity] [int] NOT NULL,
	[price] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Discounts]    Script Date: 02/10/2023 10:33:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Discounts](
	[id] [varchar](20) NOT NULL,
	[name] [nvarchar](50) NULL,
	[discount] [int] NOT NULL,
	[begin_date] [datetime] NOT NULL,
	[close_date] [datetime] NOT NULL,
	[use_num] [int] NOT NULL,
	[condition] [int] NOT NULL,
	[deleted] [bit] NOT NULL,
	[created_at] [datetime] NOT NULL,
	[updated_at] [datetime] NOT NULL,
	[max] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Images]    Script Date: 02/10/2023 10:33:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Images](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[id_product] [varchar](20) NOT NULL,
	[image] [nvarchar](400) NOT NULL,
	[deleted] [bit] NOT NULL,
	[created_at] [datetime] NOT NULL,
	[updated_at] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Inventory]    Script Date: 02/10/2023 10:33:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Inventory](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name_type] [varchar](20) NOT NULL,
	[weight] [varchar](20) NOT NULL,
	[price] [int] NOT NULL,
	[id_product] [varchar](20) NULL,
	[deleted] [bit] NOT NULL,
	[created_at] [datetime] NOT NULL,
	[updated_at] [datetime] NOT NULL,
	[inventory] [int] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Likes_Comments]    Script Date: 02/10/2023 10:33:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Likes_Comments](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[id_user] [int] NULL,
	[id_comment] [int] NULL,
	[state] [bit] NULL,
	[created_at] [datetime] NOT NULL,
	[updated_at] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Payments]    Script Date: 02/10/2023 10:33:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Payments](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](100) NOT NULL,
	[deleted] [bit] NOT NULL,
	[created_at] [datetime] NOT NULL,
	[updated_at] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Product]    Script Date: 02/10/2023 10:33:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[id] [varchar](20) NOT NULL,
	[name] [nvarchar](150) NOT NULL,
	[discount] [int] NULL,
	[image] [nvarchar](400) NOT NULL,
	[description] [nvarchar](500) NULL,
	[hot] [bit] NOT NULL,
	[expiry_date] [nvarchar](150) NOT NULL,
	[flavor] [nvarchar](250) NOT NULL,
	[ingredient] [nvarchar](500) NOT NULL,
	[user_nanual] [nvarchar](500) NOT NULL,
	[conserve] [nvarchar](500) NOT NULL,
	[id_category] [int] NOT NULL,
	[deleted] [bit] NOT NULL,
	[created_at] [datetime] NOT NULL,
	[updated_at] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Receiver]    Script Date: 02/10/2023 10:33:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Receiver](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[phone] [nvarchar](15) NOT NULL,
	[address] [nvarchar](200) NOT NULL,
	[email] [nvarchar](50) NOT NULL,
	[deleted] [bit] NOT NULL,
	[created_at] [datetime] NOT NULL,
	[updated_at] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Roles]    Script Date: 02/10/2023 10:33:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Roles](
	[name] [varchar](20) NOT NULL,
	[created_at] [datetime] NOT NULL,
	[updated_at] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Shipping_Unit]    Script Date: 02/10/2023 10:33:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Shipping_Unit](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](100) NOT NULL,
	[deleted] [bit] NOT NULL,
	[created_at] [datetime] NOT NULL,
	[updated_at] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Transport_Fee]    Script Date: 02/10/2023 10:33:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Transport_Fee](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[id_shipping_unit] [int] NOT NULL,
	[km_max] [float] NOT NULL,
	[weight_max] [float] NOT NULL,
	[price] [int] NOT NULL,
	[deleted] [bit] NOT NULL,
	[created_at] [datetime] NOT NULL,
	[updated_at] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[User]    Script Date: 02/10/2023 10:33:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[username] [varchar](50) NOT NULL,
	[first_name] [nvarchar](50) NULL,
	[last_name] [nvarchar](50) NULL,
	[email] [varchar](50) NULL,
	[phone] [varchar](15) NULL,
	[password] [nvarchar](200) NULL,
	[role] [varchar](20) NOT NULL,
	[genther] [bit] NULL,
	[birthday] [datetime] NULL,
	[avatar] [nvarchar](200) NULL,
	[background] [varchar](150) NULL,
	[ship_address] [nvarchar](200) NULL,
	[refresh_token] [varchar](500) NULL,
	[logout] [datetime] NULL,
	[deleted] [bit] NOT NULL,
	[created_at] [datetime] NOT NULL,
	[updated_at] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Category] ON 

INSERT [dbo].[Category] ([id], [name], [description], [show_on], [thumbnail], [created_at], [updated_at], [deleted]) VALUES (1, N'Cafe Arabica', N'Cà phê chè là tên gọi tiếng Việt của cafe Arabica là. Trong Arabica có các loại cà phê phổ biến là Catimor, Moka, Typica, Bourbon. Trong đó có 2 loại đang được trồng ở nước ta đó là cà phê Moka và cà phê Catimor. Cafe Moka có mùi thơm quyến rũ nhưng vị nhạt. Cafe Catimor có mùi thơm nồng nàn, vị đắng và chua. Nguyên nhân có vị chua là bởi sau khi thu hoạch, quả cafe được đem đi lên men nên có vị hơi chua.', 1, N'arabica.jpg', CAST(N'2023-09-06T17:11:09.787' AS DateTime), CAST(N'2023-09-06T17:11:09.787' AS DateTime), 0)
INSERT [dbo].[Category] ([id], [name], [description], [show_on], [thumbnail], [created_at], [updated_at], [deleted]) VALUES (2, N'Cafe Robusta', N'Khoảng 39% các sản phẩm cafe được sản xuất từ cà phê vối. Các nước xuất khẩu cafe vối bao gồm Brazil, Indonesia, Malaysia… trong đó Việt nam là nước xuất khẩu và cafe vối lớn nhất. ', 1, N'robosta.jpg', CAST(N'2023-09-06T17:11:09.787' AS DateTime), CAST(N'2023-09-06T17:11:09.787' AS DateTime), 0)
INSERT [dbo].[Category] ([id], [name], [description], [show_on], [thumbnail], [created_at], [updated_at], [deleted]) VALUES (3, N'Cafe Cherry', N'Cafe Cherry hay còn gọi là cà phê mít được trồng ở vùng Cao Nguyên nước ta. Cà phê mít gồm có 2 giống chính là Liberia và Excelsa. Hạt cà phê mít thường được trộn vào với cà phê vối, cà phê chè khi rang xay để tạo hương vị thơm ngon, mới lạ.', 0, N'https://cyclocoffe.com/wp-content/uploads/2018/02/hat-ca-phe-robusta-4-min.jpg', CAST(N'2023-09-06T17:11:09.787' AS DateTime), CAST(N'2023-09-06T17:11:09.787' AS DateTime), 0)
INSERT [dbo].[Category] ([id], [name], [description], [show_on], [thumbnail], [created_at], [updated_at], [deleted]) VALUES (4, N'Cà phê Mocha', N'Cà phê mocha còn được gọi là mocaccino là một biến thể mang hương vị socola của cà phê latte. Tên gọi của loại cà phê này bắt nguồn từ thành phố Mocha của Yemen – một trong những trung tâm giao thương cà phê đầu tiên. ', 1, N'espresso.jpg', CAST(N'2023-09-06T17:11:09.787' AS DateTime), CAST(N'2023-09-06T17:11:09.787' AS DateTime), 0)
INSERT [dbo].[Category] ([id], [name], [description], [show_on], [thumbnail], [created_at], [updated_at], [deleted]) VALUES (5, N'Cafe Espresso', N'Espresso trước đây được gọi là cafe kem, là một phương pháp pha cà phê có nguồn gốc từ Ý. Người ta định nghĩa cà phê Espresso rất đơn giản là: hạt cafe được đem xay mịn, pha bằng áp lực của hơi nước trong nửa phút. Espresso có thể được pha chế với nhiều loại hạt cà phê, rang xay.', 0, N'espresso.jpg', CAST(N'2023-09-06T17:11:09.787' AS DateTime), CAST(N'2023-09-06T17:11:09.787' AS DateTime), 0)
INSERT [dbo].[Category] ([id], [name], [description], [show_on], [thumbnail], [created_at], [updated_at], [deleted]) VALUES (6, N'Cafe Latte', N'Cà phê latte là cà phê sữa có nguồn gốc từ Ý. Thành phần bao gồm chủ yếu là cà phê và sữa được đánh lên. Cà phê latte rất dễ bị nhầm với Capuchino bởi thành phần của nó giống nhau đều có Espresso, sữa nóng và bọt sữa. Tuy nhiên nếu bạn là người sành cafe thì chắc là bạn sẽ có thể nhận ra. ', 0, N'https://chefjob.vn/wp-content/uploads/2017/11/cafe-mocha-la-gi.jpg', CAST(N'2023-09-06T17:11:09.787' AS DateTime), CAST(N'2023-09-06T17:11:09.787' AS DateTime), 0)
SET IDENTITY_INSERT [dbo].[Category] OFF
GO
SET IDENTITY_INSERT [dbo].[Category_Post] ON 

INSERT [dbo].[Category_Post] ([id], [title], [id_category], [contents], [thumbnail], [created_at], [updated_at], [deleted]) VALUES (1, N'Cafe Arabica là gì? Nguồn gốc hạt cafe Arabica và các biến thể Arabica', 1, NULL, NULL, CAST(N'2023-09-06T17:11:09.793' AS DateTime), CAST(N'2023-09-06T17:11:09.793' AS DateTime), 0)
INSERT [dbo].[Category_Post] ([id], [title], [id_category], [contents], [thumbnail], [created_at], [updated_at], [deleted]) VALUES (2, N'Cách phân biệt cà phê Arabica và Robusta, loại nào ngon hơn ?', 2, NULL, NULL, CAST(N'2023-09-06T17:11:09.793' AS DateTime), CAST(N'2023-09-06T17:11:09.793' AS DateTime), 0)
INSERT [dbo].[Category_Post] ([id], [title], [id_category], [contents], [thumbnail], [created_at], [updated_at], [deleted]) VALUES (3, N'Espresso Là Gì? Cách Pha Café Espresso Đậm Đà', 5, NULL, NULL, CAST(N'2023-09-06T17:11:09.793' AS DateTime), CAST(N'2023-09-06T17:11:09.793' AS DateTime), 0)
SET IDENTITY_INSERT [dbo].[Category_Post] OFF
GO
SET IDENTITY_INSERT [dbo].[Comments] ON 

INSERT [dbo].[Comments] ([id], [id_product], [id_user], [content], [rep_id], [deleted], [created_at], [updated_at]) VALUES (1, N'CF001', 1, N'Cafe này ngon !', 0, 0, CAST(N'2023-09-09T23:56:24.300' AS DateTime), CAST(N'2023-09-09T23:56:24.300' AS DateTime))
INSERT [dbo].[Comments] ([id], [id_product], [id_user], [content], [rep_id], [deleted], [created_at], [updated_at]) VALUES (2, N'CF001', 2, N'Cảm ơn ạ', 1, 0, CAST(N'2023-09-09T23:56:53.133' AS DateTime), CAST(N'2023-09-09T23:56:53.133' AS DateTime))
INSERT [dbo].[Comments] ([id], [id_product], [id_user], [content], [rep_id], [deleted], [created_at], [updated_at]) VALUES (3, N'CF001', 3, N'Giao hàng nhanh', 0, 0, CAST(N'2023-09-09T23:57:50.783' AS DateTime), CAST(N'2023-09-09T23:57:50.783' AS DateTime))
INSERT [dbo].[Comments] ([id], [id_product], [id_user], [content], [rep_id], [deleted], [created_at], [updated_at]) VALUES (4, N'CF001', 2, N'Cảm ơn ạ', 3, 0, CAST(N'2023-09-09T23:58:17.923' AS DateTime), CAST(N'2023-09-09T23:58:17.923' AS DateTime))
INSERT [dbo].[Comments] ([id], [id_product], [id_user], [content], [rep_id], [deleted], [created_at], [updated_at]) VALUES (7, N'CF001', 1, N'Không có chi đâu', 2, 0, CAST(N'2023-09-10T01:45:40.913' AS DateTime), CAST(N'2023-09-10T01:45:40.913' AS DateTime))
SET IDENTITY_INSERT [dbo].[Comments] OFF
GO
INSERT [dbo].[Discounts] ([id], [name], [discount], [begin_date], [close_date], [use_num], [condition], [deleted], [created_at], [updated_at], [max]) VALUES (N'KM01', N'Khuyến Mãi Người Mới', 20, CAST(N'2023-09-11T00:00:00.000' AS DateTime), CAST(N'2024-09-12T00:00:00.000' AS DateTime), 10, 200000, 0, CAST(N'2023-09-06T17:11:09.820' AS DateTime), CAST(N'2023-09-06T17:11:09.820' AS DateTime), 10000)
INSERT [dbo].[Discounts] ([id], [name], [discount], [begin_date], [close_date], [use_num], [condition], [deleted], [created_at], [updated_at], [max]) VALUES (N'KM02', N'Khuyến Mãi Tri Ân', 20, CAST(N'2023-09-11T00:00:00.000' AS DateTime), CAST(N'2024-09-14T12:03:23.000' AS DateTime), 23, 200000, 0, CAST(N'2023-09-06T17:11:09.820' AS DateTime), CAST(N'2023-09-06T17:11:09.820' AS DateTime), 10000)
INSERT [dbo].[Discounts] ([id], [name], [discount], [begin_date], [close_date], [use_num], [condition], [deleted], [created_at], [updated_at], [max]) VALUES (N'KM03', N'Sale 9/9', 10, CAST(N'2023-09-11T00:00:00.000' AS DateTime), CAST(N'2024-09-14T12:03:23.000' AS DateTime), 12, 100000, 0, CAST(N'2023-09-10T19:17:47.853' AS DateTime), CAST(N'2023-09-10T19:17:47.853' AS DateTime), 20000)
GO
SET IDENTITY_INSERT [dbo].[Images] ON 

INSERT [dbo].[Images] ([id], [id_product], [image], [deleted], [created_at], [updated_at]) VALUES (1, N'CF002', N'p2-abranica.jpg', 0, CAST(N'2023-09-06T17:11:09.803' AS DateTime), CAST(N'2023-09-06T17:11:09.803' AS DateTime))
INSERT [dbo].[Images] ([id], [id_product], [image], [deleted], [created_at], [updated_at]) VALUES (2, N'CF002', N'detail-abranica-2.jpg', 0, CAST(N'2023-09-06T17:11:09.803' AS DateTime), CAST(N'2023-09-06T17:11:09.803' AS DateTime))
INSERT [dbo].[Images] ([id], [id_product], [image], [deleted], [created_at], [updated_at]) VALUES (3, N'CF001', N'p5-abranica.png', 0, CAST(N'2023-09-06T17:11:09.803' AS DateTime), CAST(N'2023-09-06T17:11:09.803' AS DateTime))
INSERT [dbo].[Images] ([id], [id_product], [image], [deleted], [created_at], [updated_at]) VALUES (4, N'CF003', N'detail-espresso-1.jpg', 0, CAST(N'2023-09-06T17:11:09.803' AS DateTime), CAST(N'2023-09-06T17:11:09.803' AS DateTime))
INSERT [dbo].[Images] ([id], [id_product], [image], [deleted], [created_at], [updated_at]) VALUES (5, N'CF003', N'p4-espresso.jpg', 0, CAST(N'2023-09-09T02:34:10.307' AS DateTime), CAST(N'2023-09-09T02:34:10.307' AS DateTime))
INSERT [dbo].[Images] ([id], [id_product], [image], [deleted], [created_at], [updated_at]) VALUES (6, N'CF004', N'p3-robusta.png', 0, CAST(N'2023-09-09T02:35:32.133' AS DateTime), CAST(N'2023-09-09T02:35:32.133' AS DateTime))
INSERT [dbo].[Images] ([id], [id_product], [image], [deleted], [created_at], [updated_at]) VALUES (7, N'CF004', N'detail-chon-1.jpg', 0, CAST(N'2023-09-09T02:35:54.580' AS DateTime), CAST(N'2023-09-09T02:35:54.580' AS DateTime))
INSERT [dbo].[Images] ([id], [id_product], [image], [deleted], [created_at], [updated_at]) VALUES (8, N'CF004', N'detail-chon-2.png', 0, CAST(N'2023-09-09T02:36:08.097' AS DateTime), CAST(N'2023-09-09T02:36:08.097' AS DateTime))
INSERT [dbo].[Images] ([id], [id_product], [image], [deleted], [created_at], [updated_at]) VALUES (9, N'CF004', N'detail-chon-3.jpg', 0, CAST(N'2023-09-09T02:36:24.757' AS DateTime), CAST(N'2023-09-09T02:36:24.757' AS DateTime))
SET IDENTITY_INSERT [dbo].[Images] OFF
GO
SET IDENTITY_INSERT [dbo].[Inventory] ON 

INSERT [dbo].[Inventory] ([id], [name_type], [weight], [price], [id_product], [deleted], [created_at], [updated_at], [inventory]) VALUES (1, N'seed', N'250g', 200000, N'CF001', 0, CAST(N'2023-09-10T02:26:43.127' AS DateTime), CAST(N'2023-09-10T02:26:43.127' AS DateTime), 4)
INSERT [dbo].[Inventory] ([id], [name_type], [weight], [price], [id_product], [deleted], [created_at], [updated_at], [inventory]) VALUES (2, N'seed', N'500g', 240000, N'CF001', 0, CAST(N'2023-09-10T02:26:58.740' AS DateTime), CAST(N'2023-09-10T02:26:58.740' AS DateTime), 12)
INSERT [dbo].[Inventory] ([id], [name_type], [weight], [price], [id_product], [deleted], [created_at], [updated_at], [inventory]) VALUES (3, N'seed', N'1kg', 300000, N'CF001', 0, CAST(N'2023-09-10T02:27:13.833' AS DateTime), CAST(N'2023-09-10T02:27:13.833' AS DateTime), 400)
INSERT [dbo].[Inventory] ([id], [name_type], [weight], [price], [id_product], [deleted], [created_at], [updated_at], [inventory]) VALUES (4, N'flour', N'250g', 100000, N'CF001', 0, CAST(N'2023-09-10T02:27:59.057' AS DateTime), CAST(N'2023-09-10T02:27:59.057' AS DateTime), 100)
INSERT [dbo].[Inventory] ([id], [name_type], [weight], [price], [id_product], [deleted], [created_at], [updated_at], [inventory]) VALUES (5, N'seed', N'500g', 400000, N'CF002', 0, CAST(N'2023-09-10T02:28:21.413' AS DateTime), CAST(N'2023-09-10T02:28:21.413' AS DateTime), 20)
INSERT [dbo].[Inventory] ([id], [name_type], [weight], [price], [id_product], [deleted], [created_at], [updated_at], [inventory]) VALUES (6, N'flour', N'250g', 200000, N'CF002', 0, CAST(N'2023-09-10T02:28:44.023' AS DateTime), CAST(N'2023-09-10T02:28:44.023' AS DateTime), 10)
INSERT [dbo].[Inventory] ([id], [name_type], [weight], [price], [id_product], [deleted], [created_at], [updated_at], [inventory]) VALUES (7, N'seed', N'500g', 300000, N'CF003', 0, CAST(N'2023-09-10T02:29:01.917' AS DateTime), CAST(N'2023-09-10T02:29:01.917' AS DateTime), 50)
INSERT [dbo].[Inventory] ([id], [name_type], [weight], [price], [id_product], [deleted], [created_at], [updated_at], [inventory]) VALUES (8, N'seed', N'1kg', 400000, N'CF004', 0, CAST(N'2023-09-10T02:29:27.070' AS DateTime), CAST(N'2023-09-10T02:29:27.070' AS DateTime), 40)
SET IDENTITY_INSERT [dbo].[Inventory] OFF
GO
SET IDENTITY_INSERT [dbo].[Likes_Comments] ON 

INSERT [dbo].[Likes_Comments] ([id], [id_user], [id_comment], [state], [created_at], [updated_at]) VALUES (1, 1, 1, 1, CAST(N'2023-09-09T23:58:59.653' AS DateTime), CAST(N'2023-09-09T23:58:59.653' AS DateTime))
INSERT [dbo].[Likes_Comments] ([id], [id_user], [id_comment], [state], [created_at], [updated_at]) VALUES (2, 1, 2, 1, CAST(N'2023-09-09T23:59:05.740' AS DateTime), CAST(N'2023-09-09T23:59:05.740' AS DateTime))
INSERT [dbo].[Likes_Comments] ([id], [id_user], [id_comment], [state], [created_at], [updated_at]) VALUES (3, 2, 1, 1, CAST(N'2023-09-09T23:59:12.623' AS DateTime), CAST(N'2023-09-09T23:59:12.623' AS DateTime))
INSERT [dbo].[Likes_Comments] ([id], [id_user], [id_comment], [state], [created_at], [updated_at]) VALUES (4, 2, 2, 1, CAST(N'2023-09-09T23:59:21.440' AS DateTime), CAST(N'2023-09-09T23:59:21.440' AS DateTime))
INSERT [dbo].[Likes_Comments] ([id], [id_user], [id_comment], [state], [created_at], [updated_at]) VALUES (5, 3, 1, 1, CAST(N'2023-09-09T23:59:24.980' AS DateTime), CAST(N'2023-09-09T23:59:24.980' AS DateTime))
INSERT [dbo].[Likes_Comments] ([id], [id_user], [id_comment], [state], [created_at], [updated_at]) VALUES (6, 1, 7, 1, CAST(N'2023-09-10T01:59:02.727' AS DateTime), CAST(N'2023-09-10T01:59:02.727' AS DateTime))
INSERT [dbo].[Likes_Comments] ([id], [id_user], [id_comment], [state], [created_at], [updated_at]) VALUES (7, 1, 3, 1, CAST(N'2023-09-10T01:59:48.637' AS DateTime), CAST(N'2023-09-10T01:59:48.637' AS DateTime))
SET IDENTITY_INSERT [dbo].[Likes_Comments] OFF
GO
SET IDENTITY_INSERT [dbo].[Payments] ON 

INSERT [dbo].[Payments] ([id], [name], [deleted], [created_at], [updated_at]) VALUES (1, N'Thanh Toán Khi Nhận Hàng', 0, CAST(N'2023-09-06T17:11:09.827' AS DateTime), CAST(N'2023-09-06T17:11:09.827' AS DateTime))
INSERT [dbo].[Payments] ([id], [name], [deleted], [created_at], [updated_at]) VALUES (2, N'Thanh Toán Qua Chuyển Khoản Ngân Hàng', 0, CAST(N'2023-09-06T17:11:09.827' AS DateTime), CAST(N'2023-09-06T17:11:09.827' AS DateTime))
SET IDENTITY_INSERT [dbo].[Payments] OFF
GO
INSERT [dbo].[Product] ([id], [name], [discount], [image], [description], [hot], [expiry_date], [flavor], [ingredient], [user_nanual], [conserve], [id_category], [deleted], [created_at], [updated_at]) VALUES (N'CF001', N'Jimma Birbirsa', 20, N'p5-abranica.png', N'<p style="font-size:18px;">
Birbirsa là một bộ phận hợp tác chính của Liên minh Hợp tác xã Nông dân Đa mục đích Kata Muduga. Liên minh có trụ sở tại thành phố Agaro, 45 km từ Jimma, thành phố lớn nhất ở vùng Oromia phía tây nam của Ethiopia và 397 km từ Addis Ababa, thủ đô của Ethiopia. Kata Muduga làm việc với nông dân từ 5 huyện nông thôn trong Vùng Jimma: Goma, Gera, Gumai, Setema và Sigimo. </p>', 1, N'12 tháng kể từ ngày sản xuất. ', N'Vị chua thanh xen lẫn đắng nhẹ cùng hương thơm nhẹ nhàng. Thích hợp: gu sành cà phê, ưa thích sự độc đáo và uống được vị chua.', N'100% từ những trái cà phê Arabica chín cây thượng hạng từ vùng nguyên liệu Cầu Đất.', N'Đặt giấy lọc vào phễu và tráng nhẹ. Cho lượng cà phê khoảng 10-15g vào, thêm 20ml nước và chờ 20-25 giây. Rót nhẹ nhàng thêm nước vào phin đến khi lượng nước đạt vừa sở thích. Lấy phễu ra và có thể thêm đường/sữa và đá tuỳ thích.', N'Bảo quản nơi khô ráo, thoáng mát, tránh ánh nắng trực tiếp mặt trời. Luôn đóng kín bao trà sau khi sử dụng.', 1, 0, CAST(N'2023-09-06T17:11:09.797' AS DateTime), CAST(N'2023-09-06T17:11:09.797' AS DateTime))
INSERT [dbo].[Product] ([id], [name], [discount], [image], [description], [hot], [expiry_date], [flavor], [ingredient], [user_nanual], [conserve], [id_category], [deleted], [created_at], [updated_at]) VALUES (N'CF002', N'Cà phê Arabica Cầu Đất DalatFarm', 75, N'p2-abranica.jpg', N'<p style="font-size:18px;">
 Cà phê Arabica Cầu Đất được trồng từ vùng nguyên liệu Cầu Đất - Đà Lạt, nơi được mệnh danh là thủ phủ Arabica, chất lượng lâu đời. Nằm trên độ cao hơn 1600m so với mực nước biển, Cầu Đất có khí hậu ôn hoà quanh năm, thổ nhưỡng Bazan màu mỡ đã tạo nên những đồi cà phê có hương thơm và phẩm chất rất riêng biệt.'', 90, 1, N''12 tháng kể từ ngày sản xuất.
 </p>', 1, N'12 tháng kể từ ngày sản xuất. ', N'Vị chua thanh xen lẫn đắng nhẹ cùng hương thơm nhẹ nhàng. Thích hợp: gu sành cà phê, ưa thích sự độc đáo và uống được vị chua.', N'100% từ những trái cà phê Arabica chín cây thượng hạng từ vùng nguyên liệu Cầu Đất.', N'Đặt giấy lọc vào phễu và tráng nhẹ. Cho lượng cà phê khoảng 10-15g vào, thêm 20ml nước và chờ 20-25 giây. Rót nhẹ nhàng thêm nước vào phin đến khi lượng nước đạt vừa sở thích. Lấy phễu ra và có thể thêm đường/sữa và đá tuỳ thích.', N'Bảo quản nơi khô ráo, thoáng mát, tránh ánh nắng trực tiếp mặt trời. Luôn đóng kín bao trà sau khi sử dụng.', 1, 0, CAST(N'2023-09-06T17:11:09.797' AS DateTime), CAST(N'2023-09-06T17:11:09.797' AS DateTime))
INSERT [dbo].[Product] ([id], [name], [discount], [image], [description], [hot], [expiry_date], [flavor], [ingredient], [user_nanual], [conserve], [id_category], [deleted], [created_at], [updated_at]) VALUES (N'CF003', N'Starbucks Dark Roast Ground Coffee', 10, N'p4-espresso.jpg', N'<p style="font-size:18px;">Actual product packaging and materials may contain more and different information than what is shown on our app or website. We recommend that you do not rely solely on the information presented here and that you always read labels, warnings, and directions before using or consuming a product.</p>', 1, N'12 tháng kể từ ngày sản xuất. ', N'Vị chua thanh xen lẫn đắng nhẹ cùng hương thơm nhẹ nhàng. Thích hợp: gu sành cà phê, ưa thích sự độc đáo và uống được vị chua.', N'100% từ những trái cà phê Arabica chín cây thượng hạng từ vùng nguyên liệu Cầu Đất.', N'Đặt giấy lọc vào phễu và tráng nhẹ. Cho lượng cà phê khoảng 10-15g vào, thêm 20ml nước và chờ 20-25 giây. Rót nhẹ nhàng thêm nước vào phin đến khi lượng nước đạt vừa sở thích. Lấy phễu ra và có thể thêm đường/sữa và đá tuỳ thích.', N'Bảo quản nơi khô ráo, thoáng mát, tránh ánh nắng trực tiếp mặt trời. Luôn đóng kín bao trà sau khi sử dụng.', 5, 0, CAST(N'2023-09-06T17:16:02.270' AS DateTime), CAST(N'2023-09-06T17:16:02.270' AS DateTime))
INSERT [dbo].[Product] ([id], [name], [discount], [image], [description], [hot], [expiry_date], [flavor], [ingredient], [user_nanual], [conserve], [id_category], [deleted], [created_at], [updated_at]) VALUES (N'CF004', N'Cà phê chồn', 10, N'p3-robusta.png', N'<p style="font-size:18px;">12 tháng kể từ ngày sản xuất. '', N''Vị chua thanh xen lẫn đắng nhẹ cùng hương thơm nhẹ nhàng. Thích hợp: gu sành cà phê, ưa thích sự độc đáo và uống được vị chua.'', N''100% từ những trái cà phê Arabica chín cây thượng hạng từ vùng nguyên liệu Cầu Đất.</p>', 1, N'12 tháng kể từ ngày sản xuất. ', N'Vị chua thanh xen lẫn đắng nhẹ cùng hương thơm nhẹ nhàng. Thích hợp: gu sành cà phê, ưa thích sự độc đáo và uống được vị chua.', N'100% từ những trái cà phê Arabica chín cây thượng hạng từ vùng nguyên liệu Cầu Đất.', N'Đặt giấy lọc vào phễu và tráng nhẹ. Cho lượng cà phê khoảng 10-15g vào, thêm 20ml nước và chờ 20-25 giây. Rót nhẹ nhàng thêm nước vào phin đến khi lượng nước đạt vừa sở thích. Lấy phễu ra và có thể thêm đường/sữa và đá tuỳ thích.', N'Bảo quản nơi khô ráo, thoáng mát, tránh ánh nắng trực tiếp mặt trời. Luôn đóng kín bao trà sau khi sử dụng.', 2, 0, CAST(N'2023-09-06T17:20:20.243' AS DateTime), CAST(N'2023-09-06T17:20:20.243' AS DateTime))
GO
SET IDENTITY_INSERT [dbo].[Receiver] ON 

INSERT [dbo].[Receiver] ([id], [name], [phone], [address], [email], [deleted], [created_at], [updated_at]) VALUES (1, N'Phạm Nhứt Khang', N'344507491', N'Châu thành, Hậu giang', N'phamnhatkhang.hgi1167@gmail.com', 0, CAST(N'2023-09-06T17:11:09.823' AS DateTime), CAST(N'2023-09-06T17:11:09.823' AS DateTime))
SET IDENTITY_INSERT [dbo].[Receiver] OFF
GO
INSERT [dbo].[Roles] ([name], [created_at], [updated_at]) VALUES (N'ROLE_ADMIN', CAST(N'2023-09-06T17:11:09.807' AS DateTime), CAST(N'2023-09-06T17:11:09.807' AS DateTime))
INSERT [dbo].[Roles] ([name], [created_at], [updated_at]) VALUES (N'ROLE_SUPER_ADMIN', CAST(N'2023-09-06T17:11:09.807' AS DateTime), CAST(N'2023-09-06T17:11:09.807' AS DateTime))
INSERT [dbo].[Roles] ([name], [created_at], [updated_at]) VALUES (N'ROLE_USER', CAST(N'2023-09-06T17:11:09.807' AS DateTime), CAST(N'2023-09-06T17:11:09.807' AS DateTime))
GO
SET IDENTITY_INSERT [dbo].[Shipping_Unit] ON 

INSERT [dbo].[Shipping_Unit] ([id], [name], [deleted], [created_at], [updated_at]) VALUES (1, N'Giao Hàng Nhanh', 0, CAST(N'2023-09-06T17:11:09.830' AS DateTime), CAST(N'2023-09-06T17:11:09.830' AS DateTime))
INSERT [dbo].[Shipping_Unit] ([id], [name], [deleted], [created_at], [updated_at]) VALUES (2, N'Vietnam Post', 0, CAST(N'2023-09-06T17:11:09.830' AS DateTime), CAST(N'2023-09-06T17:11:09.830' AS DateTime))
INSERT [dbo].[Shipping_Unit] ([id], [name], [deleted], [created_at], [updated_at]) VALUES (3, N'Giao Hàng Tiết Kiệm', 0, CAST(N'2023-09-06T17:11:09.830' AS DateTime), CAST(N'2023-09-06T17:11:09.830' AS DateTime))
INSERT [dbo].[Shipping_Unit] ([id], [name], [deleted], [created_at], [updated_at]) VALUES (4, N'J&T Express', 0, CAST(N'2023-09-06T17:11:09.830' AS DateTime), CAST(N'2023-09-06T17:11:09.830' AS DateTime))
SET IDENTITY_INSERT [dbo].[Shipping_Unit] OFF
GO
SET IDENTITY_INSERT [dbo].[Transport_Fee] ON 

INSERT [dbo].[Transport_Fee] ([id], [id_shipping_unit], [km_max], [weight_max], [price], [deleted], [created_at], [updated_at]) VALUES (1, 1, 10, 20, 40000, 0, CAST(N'2023-09-06T17:11:09.837' AS DateTime), CAST(N'2023-09-06T17:11:09.837' AS DateTime))
INSERT [dbo].[Transport_Fee] ([id], [id_shipping_unit], [km_max], [weight_max], [price], [deleted], [created_at], [updated_at]) VALUES (2, 1, 20, 20, 60000, 0, CAST(N'2023-09-06T17:11:09.837' AS DateTime), CAST(N'2023-09-06T17:11:09.837' AS DateTime))
INSERT [dbo].[Transport_Fee] ([id], [id_shipping_unit], [km_max], [weight_max], [price], [deleted], [created_at], [updated_at]) VALUES (3, 2, 10, 20, 20000, 0, CAST(N'2023-09-06T17:11:09.837' AS DateTime), CAST(N'2023-09-06T17:11:09.837' AS DateTime))
INSERT [dbo].[Transport_Fee] ([id], [id_shipping_unit], [km_max], [weight_max], [price], [deleted], [created_at], [updated_at]) VALUES (4, 2, 20, 20, 60000, 0, CAST(N'2023-09-06T17:11:09.837' AS DateTime), CAST(N'2023-09-06T17:11:09.837' AS DateTime))
INSERT [dbo].[Transport_Fee] ([id], [id_shipping_unit], [km_max], [weight_max], [price], [deleted], [created_at], [updated_at]) VALUES (5, 3, 30, 20, 40000, 0, CAST(N'2023-09-06T17:11:09.837' AS DateTime), CAST(N'2023-09-06T17:11:09.837' AS DateTime))
INSERT [dbo].[Transport_Fee] ([id], [id_shipping_unit], [km_max], [weight_max], [price], [deleted], [created_at], [updated_at]) VALUES (6, 4, 10, 20, 40000, 0, CAST(N'2023-09-06T17:11:09.837' AS DateTime), CAST(N'2023-09-06T17:11:09.837' AS DateTime))
SET IDENTITY_INSERT [dbo].[Transport_Fee] OFF
GO
SET IDENTITY_INSERT [dbo].[User] ON 

INSERT [dbo].[User] ([id], [username], [first_name], [last_name], [email], [phone], [password], [role], [genther], [birthday], [avatar], [background], [ship_address], [refresh_token], [logout], [deleted], [created_at], [updated_at]) VALUES (1, N'khangpn', N'Phạm', N'Nhứt Khang', N'phamnhatkhang.hgi1167@gmail.com', N'344507491', N'123', N'ROLE_USER', 1, CAST(N'2003-05-17T00:00:00.000' AS DateTime), NULL, NULL, NULL, NULL, NULL, 0, CAST(N'2023-09-06T17:11:09.813' AS DateTime), CAST(N'2023-09-06T17:11:09.813' AS DateTime))
INSERT [dbo].[User] ([id], [username], [first_name], [last_name], [email], [phone], [password], [role], [genther], [birthday], [avatar], [background], [ship_address], [refresh_token], [logout], [deleted], [created_at], [updated_at]) VALUES (2, N'quyen', N'Võ', N'Thị Tú Quyên', N'quyen@gmail.com', N'344507492', N'123', N'ROLE_ADMIN', 0, CAST(N'2003-01-29T00:00:00.000' AS DateTime), NULL, NULL, NULL, NULL, NULL, 0, CAST(N'2023-09-06T17:11:09.813' AS DateTime), CAST(N'2023-09-06T17:11:09.813' AS DateTime))
INSERT [dbo].[User] ([id], [username], [first_name], [last_name], [email], [phone], [password], [role], [genther], [birthday], [avatar], [background], [ship_address], [refresh_token], [logout], [deleted], [created_at], [updated_at]) VALUES (3, N'phungtien', NULL, NULL, N'phungtien@gmail.com', NULL, N'$2a$10$mpa2NA19mt.2C048B.B5GOt1Ial9Elq7LOZoIG9BcHOb821DufreW', N'ROLE_USER', 0, NULL, NULL, NULL, NULL, N'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwaHVuZ3RpZW4iLCJpYXQiOjE2OTYyNTk3MDMsImV4cCI6MTY5NjI3OTcwM30.pa_wRB03g2MvoNTN7Fb59hDVcbAHbEmWzZu3ZG3-L0c', NULL, 0, CAST(N'2023-09-08T16:17:09.687' AS DateTime), CAST(N'2023-09-08T16:17:09.687' AS DateTime))
INSERT [dbo].[User] ([id], [username], [first_name], [last_name], [email], [phone], [password], [role], [genther], [birthday], [avatar], [background], [ship_address], [refresh_token], [logout], [deleted], [created_at], [updated_at]) VALUES (4, N'vipn', NULL, NULL, N'vi@gmail.com', NULL, N'$2a$10$0mkVTvxf21/Ozv8kyYCjte9kkancTKRqPCBN2BkgrQzvlELzq1Msy', N'ROLE_USER', 0, NULL, NULL, NULL, NULL, N'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ2aXBuIiwiaWF0IjoxNjk0MTY2Mjc3LCJleHAiOjE2OTQxODYyNzd9.4Nx9ldQYGiqoGhNGZEUCU-HP8CBiNYXeYZExmrIfvsU', NULL, 0, CAST(N'2023-09-08T16:18:52.563' AS DateTime), CAST(N'2023-09-08T16:18:52.563' AS DateTime))
SET IDENTITY_INSERT [dbo].[User] OFF
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__Payments__72E12F1B96F29806]    Script Date: 02/10/2023 10:33:31 PM ******/
ALTER TABLE [dbo].[Payments] ADD UNIQUE NONCLUSTERED 
(
	[name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__Product__72E12F1BF837F5F3]    Script Date: 02/10/2023 10:33:31 PM ******/
ALTER TABLE [dbo].[Product] ADD UNIQUE NONCLUSTERED 
(
	[name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__Shipping__72E12F1BECD1C65D]    Script Date: 02/10/2023 10:33:31 PM ******/
ALTER TABLE [dbo].[Shipping_Unit] ADD UNIQUE NONCLUSTERED 
(
	[name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__User__F3DBC5725E928475]    Script Date: 02/10/2023 10:33:31 PM ******/
ALTER TABLE [dbo].[User] ADD UNIQUE NONCLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Bill] ADD  DEFAULT (getdate()) FOR [created_at]
GO
ALTER TABLE [dbo].[Bill] ADD  DEFAULT (getdate()) FOR [update_at]
GO
ALTER TABLE [dbo].[Category] ADD  DEFAULT ((0)) FOR [show_on]
GO
ALTER TABLE [dbo].[Category] ADD  DEFAULT (getdate()) FOR [created_at]
GO
ALTER TABLE [dbo].[Category] ADD  DEFAULT (getdate()) FOR [updated_at]
GO
ALTER TABLE [dbo].[Category] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[Category_Post] ADD  DEFAULT (getdate()) FOR [created_at]
GO
ALTER TABLE [dbo].[Category_Post] ADD  DEFAULT (getdate()) FOR [updated_at]
GO
ALTER TABLE [dbo].[Category_Post] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[Comments] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[Comments] ADD  DEFAULT (getdate()) FOR [created_at]
GO
ALTER TABLE [dbo].[Comments] ADD  DEFAULT (getdate()) FOR [updated_at]
GO
ALTER TABLE [dbo].[Discounts] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[Discounts] ADD  DEFAULT (getdate()) FOR [created_at]
GO
ALTER TABLE [dbo].[Discounts] ADD  DEFAULT (getdate()) FOR [updated_at]
GO
ALTER TABLE [dbo].[Images] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[Images] ADD  DEFAULT (getdate()) FOR [created_at]
GO
ALTER TABLE [dbo].[Images] ADD  DEFAULT (getdate()) FOR [updated_at]
GO
ALTER TABLE [dbo].[Inventory] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[Inventory] ADD  DEFAULT (getdate()) FOR [created_at]
GO
ALTER TABLE [dbo].[Inventory] ADD  DEFAULT (getdate()) FOR [updated_at]
GO
ALTER TABLE [dbo].[Likes_Comments] ADD  DEFAULT ((1)) FOR [state]
GO
ALTER TABLE [dbo].[Likes_Comments] ADD  DEFAULT (getdate()) FOR [created_at]
GO
ALTER TABLE [dbo].[Likes_Comments] ADD  DEFAULT (getdate()) FOR [updated_at]
GO
ALTER TABLE [dbo].[Payments] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[Payments] ADD  DEFAULT (getdate()) FOR [created_at]
GO
ALTER TABLE [dbo].[Payments] ADD  DEFAULT (getdate()) FOR [updated_at]
GO
ALTER TABLE [dbo].[Product] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[Product] ADD  DEFAULT (getdate()) FOR [created_at]
GO
ALTER TABLE [dbo].[Product] ADD  DEFAULT (getdate()) FOR [updated_at]
GO
ALTER TABLE [dbo].[Receiver] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[Receiver] ADD  DEFAULT (getdate()) FOR [created_at]
GO
ALTER TABLE [dbo].[Receiver] ADD  DEFAULT (getdate()) FOR [updated_at]
GO
ALTER TABLE [dbo].[Roles] ADD  DEFAULT (getdate()) FOR [created_at]
GO
ALTER TABLE [dbo].[Roles] ADD  DEFAULT (getdate()) FOR [updated_at]
GO
ALTER TABLE [dbo].[Shipping_Unit] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[Shipping_Unit] ADD  DEFAULT (getdate()) FOR [created_at]
GO
ALTER TABLE [dbo].[Shipping_Unit] ADD  DEFAULT (getdate()) FOR [updated_at]
GO
ALTER TABLE [dbo].[Transport_Fee] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[Transport_Fee] ADD  DEFAULT (getdate()) FOR [created_at]
GO
ALTER TABLE [dbo].[Transport_Fee] ADD  DEFAULT (getdate()) FOR [updated_at]
GO
ALTER TABLE [dbo].[User] ADD  DEFAULT ((0)) FOR [deleted]
GO
ALTER TABLE [dbo].[User] ADD  DEFAULT (getdate()) FOR [created_at]
GO
ALTER TABLE [dbo].[User] ADD  DEFAULT (getdate()) FOR [updated_at]
GO
ALTER TABLE [dbo].[Bill]  WITH CHECK ADD FOREIGN KEY([id_payments])
REFERENCES [dbo].[Payments] ([id])
GO
ALTER TABLE [dbo].[Bill]  WITH CHECK ADD FOREIGN KEY([id_receiver])
REFERENCES [dbo].[Receiver] ([id])
GO
ALTER TABLE [dbo].[Bill]  WITH CHECK ADD FOREIGN KEY([id_shipping_unit])
REFERENCES [dbo].[Shipping_Unit] ([id])
GO
ALTER TABLE [dbo].[Bill]  WITH CHECK ADD FOREIGN KEY([id_user])
REFERENCES [dbo].[User] ([id])
GO
ALTER TABLE [dbo].[Bill_Discounts]  WITH CHECK ADD FOREIGN KEY([id_bill])
REFERENCES [dbo].[Bill] ([id])
GO
ALTER TABLE [dbo].[Bill_Discounts]  WITH CHECK ADD FOREIGN KEY([id_discount])
REFERENCES [dbo].[Discounts] ([id])
GO
ALTER TABLE [dbo].[Category_Post]  WITH CHECK ADD FOREIGN KEY([id_category])
REFERENCES [dbo].[Category] ([id])
GO
ALTER TABLE [dbo].[Comments]  WITH CHECK ADD FOREIGN KEY([id_product])
REFERENCES [dbo].[Product] ([id])
GO
ALTER TABLE [dbo].[Comments]  WITH CHECK ADD FOREIGN KEY([id_user])
REFERENCES [dbo].[User] ([id])
GO
ALTER TABLE [dbo].[Detail_Bill]  WITH CHECK ADD FOREIGN KEY([id_bill])
REFERENCES [dbo].[Bill] ([id])
GO
ALTER TABLE [dbo].[Detail_Bill]  WITH CHECK ADD FOREIGN KEY([id_product])
REFERENCES [dbo].[Product] ([id])
GO
ALTER TABLE [dbo].[Images]  WITH CHECK ADD FOREIGN KEY([id_product])
REFERENCES [dbo].[Product] ([id])
GO
ALTER TABLE [dbo].[Inventory]  WITH CHECK ADD FOREIGN KEY([id_product])
REFERENCES [dbo].[Product] ([id])
GO
ALTER TABLE [dbo].[Likes_Comments]  WITH CHECK ADD FOREIGN KEY([id_comment])
REFERENCES [dbo].[Comments] ([id])
GO
ALTER TABLE [dbo].[Likes_Comments]  WITH CHECK ADD FOREIGN KEY([id_user])
REFERENCES [dbo].[User] ([id])
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD FOREIGN KEY([id_category])
REFERENCES [dbo].[Category] ([id])
GO
ALTER TABLE [dbo].[Transport_Fee]  WITH CHECK ADD FOREIGN KEY([id_shipping_unit])
REFERENCES [dbo].[Shipping_Unit] ([id])
GO
ALTER TABLE [dbo].[User]  WITH CHECK ADD FOREIGN KEY([role])
REFERENCES [dbo].[Roles] ([name])
GO
USE [master]
GO
ALTER DATABASE [economic_cafe] SET  READ_WRITE 
GO
