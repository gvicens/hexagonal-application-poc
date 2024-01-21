create table PRODUCTS(
  ID int not null AUTO_INCREMENT,
  COMPANY_ID int not null,
  PRODUCT_ID int not null,
  START_DATE timestamp not null,
  END_DATE timestamp not null,
  PRIORITY int not null,
  PRICE float not null,
  CURRENCY varchar(3) not null,
  PRIMARY KEY ( ID )
);