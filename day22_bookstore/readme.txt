1、搭建环境
	1.1 导开发包
		mysql驱动
		dbutils框架
		c3p0连接池
		beanutils框架
		log4j
		commons fileupload
		commons io
		jstl开发包
		
	1.2 创建组织程序的包
		com.cmcc.domain
		com.cmcc.dao
		com.cmcc.dao.impl
		com.cmcc.service
		com.cmcc.web.controller
		com.cmcc.utils
		
	1.3 创建组织jsp的目录
		在WebRoot下新建manager目录，保存后台相关dejsp
		1.3.1 在WebRoot下新建一个manager.jsp页面，这个页面代表后台首页，这个页面是个分帧页面，代码如下：
			<frameset rows="18%, *">
		        <frame src="${pageContext.request.contextPath }/manager/head.jsp" name="head">
		        <frameset cols="15%, *">
		            <frame src="${pageContext.request.contextPath }/manager/left.jsp" name="left">
		            <frame src="#" name="right">
		        </frameset>
	    	</frameset>
	    	
	    1.3.2 在WebRoot下新建cloent目录，保存前台相关的jsp
	    
	1.4 创建工程所需的库
		create database bookstore CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';
		use bookstore;
		
	1.5  创建一些全局的工具类和过滤器
		JdbcUtils
		WebUtils
		CharacterEncodingFilter
		HtmlFilter
		TransactionFilter
		DaoFactory

2、创建实体
	Category
		private String id;
		private String name;
		private String description;
		
	
		
	Book 
		private String id;
		private String name;
		private double price;
		private String author;
		private String image;  //记住书的图片的位置
		private String description;
		private Category category;
		
	
	
	Order
		private String id;
		private Date ordertime;  //下单时间
		private boolean state;   //订单状态
		private double price;    //订单总价
		
		private User user;    //记住下单人
		private Set orderitems;   //记住订单所有的订单项 
	
	
		
	
	OrderItem
		private String id;
		private int quantity;
		private double price;
		private Book book;   //记住订单项代表的是哪本书
		
	

	User
		private String id;
		private String username;
		private String password;
		private String phone;
		private String cellphone;
		private String email;
		private String address;
		
3、设计表
	create table user
	(
		id varchar(40) primary key,
		username varchar(40) not null unique,
		password varchar(40) not null,
		phone varchar(20) not null,
		cellphone varchar(20) not null,
		email varchar(40) not null,
		address varchar(255) not null
	)ENGINE=InnoDB DEFAULT CHARSET=utf8;

	create table category
	(
		id varchar(40) primary key,
		name varchar(40) not null unique,
		description varchar(255)
	)ENGINE=InnoDB DEFAULT CHARSET=utf8;
	
	create table book
	(
		id varchar(40) primary key,
		name varchar(40) not null unique,
		price decimal(8,2) not null,
		author varchar(40) not null,
		image varchar(255) not null,
		description varchar(255),
		category_id varchar(40),
		constraint category_id_FK foreign key(category_id) references category(id)
	)ENGINE=InnoDB DEFAULT CHARSET=utf8;	
	
	create table orders
	(
		id varchar(40) primary key,
		ordertime datetime not null,
		state boolean not null,
		price decimal(8,2) not null,
		user_id varchar(40),
		constraint user_id_FK foreign key(user_id) references user(id)
		
	)ENGINE=InnoDB DEFAULT CHARSET=utf8;
	
	create table orderitem
	(
		id varchar(40) primary key,
		quantity int not null,
		price decimal(8,2) not null,
		book_id varchar(40),
		constraint book_id_FK foreign key(book_id) references book(id),
		order_id varchar(40),
		constraint order_id_FK foreign key(order_id) references orders(id)
	)ENGINE=InnoDB DEFAULT CHARSET=utf8;	
		
4、写dao层
	
5、 写service层
	
6、写web层
		