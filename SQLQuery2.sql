use master

go

drop database economic_cafe

create database economic_cafe

go

use economic_cafe

-- table 1
create table Category (
	id int primary key not null identity,
	[name] nvarchar(50) not null,
	[description] nvarchar(500),
	show_on bit not null default 0,
	thumbnail varchar(400) not null,
	[created_at] datetime not null default getdate(),
	[updated_at] datetime not null default getdate(),
	[deleted] bit not null default 0
)

-- table 1.1
create table Category_Post (
	id int primary key not null identity,
	title nvarchar(400) not null,
	id_category int foreign key references Category(id),
	contents text,
	thumbnail varchar(400),
	[created_at] datetime not null default getdate(),
	[updated_at] datetime not null default getdate(),
	[deleted] bit not null default 0
)


insert into Category_Post
values  
(N'Cafe Arabica là gì? Nguồn gốc hạt cafe Arabica và các biến thể Arabica', 1, null, null, getdate(), getdate(), 0),
(N'Cách phân biệt cà phê Arabica và Robusta, loại nào ngon hơn ?', 2, null, null, getdate(), getdate(), 0),
(N'Espresso Là Gì? Cách Pha Café Espresso Đậm Đà', 5, null, null, getdate(), getdate(), 0)


go

insert into Category 
values 
(N'Cafe Arabica', N'Cà phê chè là tên gọi tiếng Việt của cafe Arabica là. Trong Arabica có các loại cà phê phổ biến là Catimor, Moka, Typica, Bourbon. Trong đó có 2 loại đang được trồng ở nước ta đó là cà phê Moka và cà phê Catimor. Cafe Moka có mùi thơm quyến rũ nhưng vị nhạt. Cafe Catimor có mùi thơm nồng nàn, vị đắng và chua. Nguyên nhân có vị chua là bởi sau khi thu hoạch, quả cafe được đem đi lên men nên có vị hơi chua.', 1,'arabica.jpg', getdate(),getdate(), 0),
(N'Cafe Robusta', N'Khoảng 39% các sản phẩm cafe được sản xuất từ cà phê vối. Các nước xuất khẩu cafe vối bao gồm Brazil, Indonesia, Malaysia… trong đó Việt nam là nước xuất khẩu và cafe vối lớn nhất. ', 1, 'robosta.jpg', getdate(), getdate(), 0),
(N'Cafe Cherry', N'Cafe Cherry hay còn gọi là cà phê mít được trồng ở vùng Cao Nguyên nước ta. Cà phê mít gồm có 2 giống chính là Liberia và Excelsa. Hạt cà phê mít thường được trộn vào với cà phê vối, cà phê chè khi rang xay để tạo hương vị thơm ngon, mới lạ.', 0,'https://cyclocoffe.com/wp-content/uploads/2018/02/hat-ca-phe-robusta-4-min.jpg', getdate(),getdate(), 0),
(N'Cà phê Mocha', N'Cà phê mocha còn được gọi là mocaccino là một biến thể mang hương vị socola của cà phê latte. Tên gọi của loại cà phê này bắt nguồn từ thành phố Mocha của Yemen – một trong những trung tâm giao thương cà phê đầu tiên. ', 1, 'espresso.jpg', getdate(), getdate(), 0),
(N'Cafe Espresso', N'Espresso trước đây được gọi là cafe kem, là một phương pháp pha cà phê có nguồn gốc từ Ý. Người ta định nghĩa cà phê Espresso rất đơn giản là: hạt cafe được đem xay mịn, pha bằng áp lực của hơi nước trong nửa phút. Espresso có thể được pha chế với nhiều loại hạt cà phê, rang xay.', 0, 'https://blog.ptlvina.com/wp-content/uploads/2021/01/cac-loai-cafe-6.jpg', getdate(), getdate(), 0),
(N'Cafe Latte', N'Cà phê latte là cà phê sữa có nguồn gốc từ Ý. Thành phần bao gồm chủ yếu là cà phê và sữa được đánh lên. Cà phê latte rất dễ bị nhầm với Capuchino bởi thành phần của nó giống nhau đều có Espresso, sữa nóng và bọt sữa. Tuy nhiên nếu bạn là người sành cafe thì chắc là bạn sẽ có thể nhận ra. ', 0, 'https://chefjob.vn/wp-content/uploads/2017/11/cafe-mocha-la-gi.jpg', getdate(), getdate(), 0)


go 

-- table 2
create table Product(
	id varchar(20) primary key not null,
	[name] nvarchar(150) unique not null,
	[old_price] int not null,
	[new_price] int not null,
	discount int ,
	[image] nvarchar(400) not null,
	[description] nvarchar(500),
	[inventory] int not null,
	[hot] bit not null,
	[expiry_date] nvarchar(150) not null,
	flavor  nvarchar(250) not null,
	ingredient nvarchar(500) not null,
	user_nanual nvarchar(500) not null,
	conserve nvarchar(500) not null,
	id_category int not null foreign key references Category (id),
	deleted bit not null default 0,
	created_at datetime not null default getdate(),
	updated_at datetime not null default getdate()
)



go



insert into Product
values
('CF001', N'Jimma Birbirsa', 310000, 250000, 20, 'https://static.wixstatic.com/media/66ff7b_3b816a13db234d1982bfe42faebd2993~mv2.png/v1/fill/w_500,h_667,al_c,q_85,usm_0.66_1.00_0.01,enc_auto/66ff7b_3b816a13db234d1982bfe42faebd2993~mv2.png', N'Birbirsa là một bộ phận hợp tác chính của Liên minh Hợp tác xã Nông dân Đa mục đích Kata Muduga. Liên minh có trụ sở tại thành phố Agaro, 45 km từ Jimma, thành phố lớn nhất ở vùng Oromia phía tây nam của Ethiopia và 397 km từ Addis Ababa, thủ đô của Ethiopia. Kata Muduga làm việc với nông dân từ 5 huyện nông thôn trong Vùng Jimma: Goma, Gera, Gumai, Setema và Sigimo.', 100, 1,  N'12 tháng kể từ ngày sản xuất. ', N'Vị chua thanh xen lẫn đắng nhẹ cùng hương thơm nhẹ nhàng. Thích hợp: gu sành cà phê, ưa thích sự độc đáo và uống được vị chua.', N'100% từ những trái cà phê Arabica chín cây thượng hạng từ vùng nguyên liệu Cầu Đất.', N'Đặt giấy lọc vào phễu và tráng nhẹ. Cho lượng cà phê khoảng 10-15g vào, thêm 20ml nước và chờ 20-25 giây. Rót nhẹ nhàng thêm nước vào phin đến khi lượng nước đạt vừa sở thích. Lấy phễu ra và có thể thêm đường/sữa và đá tuỳ thích.', N'Bảo quản nơi khô ráo, thoáng mát, tránh ánh nắng trực tiếp mặt trời. Luôn đóng kín bao trà sau khi sử dụng.', 1, 0, getdate(), getdate()),
('CF002', N'Cà phê Arabica Cầu Đất DalatFarm', 200000, 150000, 75, 'https://product.hstatic.net/200000076583/product/cf_a_hat_font_6565f5a884f9484cbae5578c57b45451_master.jpg', N'Cà phê Arabica Cầu Đất được trồng từ vùng nguyên liệu Cầu Đất - Đà Lạt, nơi được mệnh danh là thủ phủ Arabica, chất lượng lâu đời. Nằm trên độ cao hơn 1600m so với mực nước biển, Cầu Đất có khí hậu ôn hoà quanh năm, thổ nhưỡng Bazan màu mỡ đã tạo nên những đồi cà phê có hương thơm và phẩm chất rất riêng biệt.', 90, 1,  N'12 tháng kể từ ngày sản xuất. ', N'Vị chua thanh xen lẫn đắng nhẹ cùng hương thơm nhẹ nhàng. Thích hợp: gu sành cà phê, ưa thích sự độc đáo và uống được vị chua.', N'100% từ những trái cà phê Arabica chín cây thượng hạng từ vùng nguyên liệu Cầu Đất.', N'Đặt giấy lọc vào phễu và tráng nhẹ. Cho lượng cà phê khoảng 10-15g vào, thêm 20ml nước và chờ 20-25 giây. Rót nhẹ nhàng thêm nước vào phin đến khi lượng nước đạt vừa sở thích. Lấy phễu ra và có thể thêm đường/sữa và đá tuỳ thích.', N'Bảo quản nơi khô ráo, thoáng mát, tránh ánh nắng trực tiếp mặt trời. Luôn đóng kín bao trà sau khi sử dụng.', 1, 0, getdate(), getdate()),

go

-- table 3
create table Images(
	id int primary key not null identity,
	id_product varchar(20) not null foreign key references Product (id),
	[image] nvarchar(400) not null,
	deleted bit not null default 0,
	created_at datetime not null default getdate(),
	updated_at datetime not null default getdate(),
)



insert into Images
values 
('CF002', 'https://product.hstatic.net/200000076583/product/cf_a_hat_font_6565f5a884f9484cbae5578c57b45451_master.jpg', 0 , getdate(), getdate()),
('CF002', 'https://product.hstatic.net/200000076583/product/cf_a_back_f2645a73f13d471e9f1d7ffa82bdbb19_master.jpg',0 , getdate(), getdate()),
('CF001', 'https://static.wixstatic.com/media/66ff7b_3b816a13db234d1982bfe42faebd2993~mv2.png/v1/fill/w_500,h_667,al_c,q_85,usm_0.66_1.00_0.01,enc_auto/66ff7b_3b816a13db234d1982bfe42faebd2993~mv2.pnghttps://static.wixstatic.com/media/66ff7b_3b816a13db234d1982bfe42faebd2993~mv2.png/v1/fill/w_500,h_667,al_c,q_85,usm_0.66_1.00_0.01,enc_auto/66ff7b_3b816a13db234d1982bfe42faebd2993~mv2.png',0 , getdate(), getdate()),
('CF001', 'https://static.wixstatic.com/media/66ff7b_0e448c513d944edcb30745b1e2133496~mv2.png/v1/fill/w_500,h_667,al_c,q_85,usm_0.66_1.00_0.01,enc_auto/66ff7b_0e448c513d944edcb30745b1e2133496~mv2.png',0 , getdate(), getdate())

go

create table Roles(
	[name] varchar(20) not null primary key,
	created_at datetime not null default getdate(),
	updated_at datetime not null default getdate()
)

insert into Roles
values
('ROLE_ADMIN', getdate(), getdate()),
('ROLE_SUPER_ADMIN', getdate(), getdate()),
('ROLE_USER', getdate(), getdate())

go

-- table 4
create table [User](
	id int primary key not null identity,
	username varchar(50) not null unique,
	first_name  nvarchar(50),
	last_name  nvarchar(50),
	email  varchar(50),
	phone  varchar(15),
	[password] nvarchar(200),
	[role] varchar(20) not null foreign key references Roles([name]),
	genther bit,
	birthday datetime,
	avatar nvarchar(200),
	background  varchar(150),
	ship_address  nvarchar(200),
	refresh_token varchar(500),
	logout datetime,
	deleted bit not null default 0,
	created_at datetime not null default getdate(),
	updated_at datetime not null default getdate()
)


insert into [User] 
values
(N'khangpn', N'Phạm', N'Nhứt Khang', N'phamnhatkhang.hgi1167@gmail.com', 0344507491, '123', 'ROLE_USER', 1, '05/17/2003', null, null, null, null, null, 0 , getdate(), getdate()),
(N'quyen', N'Võ', N'Thị Tú Quyên', N'quyen@gmail.com', 0344507492, '123', 'ROLE_ADMIN', 0, '01/29/2003', null, null, null, null, null, 0 , getdate(), getdate())

go


-- table 7
create table Comments (
	id int primary key not null identity,
	id_product varchar(20) not null foreign key references Product (id),
	id_user int not null foreign key references [User] (id),
	content nvarchar(400) not null,
	rating_star int,
	rep_id int,
	deleted bit not null default 0,
	created_at datetime not null default getdate(),
	updated_at datetime not null default getdate()
)

go

-- table 7.1
create table Likes_Comments(
	id int identity primary key not null,
	[id_user] int foreign key references [User] (id),
	id_comment int foreign key references Comments(id),
	[state] bit default 1, --(like or unlike. Default 1 because when created then is like this comment)
	created_at datetime not null default getdate(),
	updated_at datetime not null default getdate()
)



go


-- table 8
create table Discounts(
	id varchar(20) primary key not null,
	[name] nvarchar(50),
	discount int not null,
	begin_date datetime not null,
	close_date datetime not null,
	use_num int not null,
	condition int not null,
	deleted bit not null default 0,
	created_at datetime not null default getdate(),
	updated_at datetime not null default getdate()
)



insert into Discounts
values
('KM01',N'Khuyến Mãi Người Mới', 20, GETDATE(), '8/19/2024', 10, 200000, 0 , getdate(), getdate()),
('KM02',N'Khuyến Mãi Tri Ân', 20, GETDATE(), '8/19/2024', 10, 200000, 0 , getdate(), getdate())

go


-- table 9
create table Receiver(
	id int not null primary key identity,
	[name] nvarchar(50) not null,
	phone nvarchar(15) not null,
	[address] nvarchar(200) not null,
	email nvarchar(50) not null,
	deleted bit not null default 0,
	created_at datetime not null default getdate(),
	updated_at datetime not null default getdate()
)

insert into Receiver
values
(N'Phạm Nhứt Khang', 0344507491, N'Châu thành, Hậu giang', 'phamnhatkhang.hgi1167@gmail.com', 0, getdate(), getdate())

go


-- table 10
create table Payments(
	id int not null primary key identity,
	[name] nvarchar(100) not null unique,
	deleted bit not null default 0,
	created_at datetime not null default getdate(),
	updated_at datetime not null default getdate()
)

insert into Payments
values
(N'Thanh Toán Khi Nhận Hàng', 0, getdate(), getdate()),
(N'Thanh Toán Qua Chuyển Khoản Ngân Hàng', 0, getdate(), getdate())

go


-- table 11
create table Shipping_Unit(
	id int not null primary key identity,
	[name] nvarchar(100) not null unique,
	deleted bit not null default 0,
	created_at datetime not null default getdate(),
	updated_at datetime not null default getdate()
)

insert into Shipping_Unit
values
(N'Giao Hàng Nhanh', 0, getdate(), getdate()),
(N'Vietnam Post', 0, getdate(), getdate()),
(N'Giao Hàng Tiết Kiệm', 0, getdate(), getdate()),
(N'J&T Express', 0, getdate(), getdate())

go


-- table 12
create table Transport_Fee(
	id int not null primary key identity,
	id_shipping_unit int not null foreign key references Shipping_Unit (id),
	km_max float not null,
	weight_max float not null,
	price int not null,
	deleted bit not null default 0,
	created_at datetime not null default getdate(),
	updated_at datetime not null default getdate()
)

insert into Transport_Fee
values
(1, 10, 20, 40000, 0, getdate(), getdate()),
(1, 20, 20, 60000, 0, getdate(), getdate()),
(2, 10, 20, 20000, 0, getdate(), getdate()),
(2, 20, 20, 60000, 0, getdate(), getdate()),
(3, 30, 20, 40000, 0, getdate(), getdate()),
(4, 10, 20, 40000, 0, getdate(), getdate())

go

-- table 13
create table Bill (
	id int primary key not null identity,
	id_user int not null foreign key references [User] (id),
	id_receiver int not null foreign key references Receiver (id),
	[status] int not null,
	[description] nvarchar(200),
	done_date datetime,
	id_payments int not null foreign key references Payments (id),
	id_shipping_unit int not null foreign key references Shipping_Unit (id),
	created_at datetime not null default getdate(),
	update_at datetime not null default getdate(),
)

go


-- table 14
create table Bill_Discounts(
	id_bill int  not null foreign key references Bill (id),
	id_discount varchar(20) not null foreign key references Discounts (id),
	constraint pk_bill_discounts primary key (id_bill, id_discount)

)

go

create table Detail_Bill(
	id int not null identity primary key,
	id_bill int  not null foreign key references Bill (id),
	id_product varchar(20) not null foreign key references Product (id),
	quantity int not null,
	price int not null
)


drop table sinhvien
drop table class








