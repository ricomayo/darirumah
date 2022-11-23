/*
 Navicat Premium Data Transfer

 Source Server         : lokal postgre
 Source Server Type    : PostgreSQL
 Source Server Version : 130005
 Source Host           : localhost:5432
 Source Catalog        : darirumah
 Source Schema         : darirumah

 Target Server Type    : PostgreSQL
 Target Server Version : 130005
 File Encoding         : 65001

 Date: 23/11/2022 23:00:55
*/


-- ----------------------------
-- Table structure for end_order_history
-- ----------------------------
DROP TABLE IF EXISTS "darirumah"."end_order_history";
CREATE TABLE "darirumah"."end_order_history" (
  "id_history" int8 NOT NULL,
  "id_order" int8,
  "id_user" int8,
  "id_product" int8,
  "id_stock" int8,
  "qty_order" numeric,
  "destination_name" varchar(255) COLLATE "pg_catalog"."default",
  "destination_address" varchar(255) COLLATE "pg_catalog"."default",
  "status" varchar(255) COLLATE "pg_catalog"."default",
  "created_date" timestamp(0) DEFAULT now(),
  "created_by" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of end_order_history
-- ----------------------------
INSERT INTO "darirumah"."end_order_history" VALUES (1, 1, 4, 1, 3, 5, 'rico', 'rumah rico', 'FINISH', '2022-11-23 13:33:51', 'admin');

-- ----------------------------
-- Table structure for mst_product
-- ----------------------------
DROP TABLE IF EXISTS "darirumah"."mst_product";
CREATE TABLE "darirumah"."mst_product" (
  "id_product" int8 NOT NULL,
  "productcode" varchar(255) COLLATE "pg_catalog"."default",
  "productname" varchar(255) COLLATE "pg_catalog"."default",
  "supplier" varchar(255) COLLATE "pg_catalog"."default",
  "uom" varchar(255) COLLATE "pg_catalog"."default",
  "type" varchar(255) COLLATE "pg_catalog"."default",
  "created_date" timestamp(0) DEFAULT now(),
  "updated_date" timestamp(0) DEFAULT now(),
  "updated_by" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of mst_product
-- ----------------------------
INSERT INTO "darirumah"."mst_product" VALUES (6, 'A001', 'coba Insert', 'Ace Hardware', 'pc', 'Furniture', '2022-11-23 06:07:01', '2022-11-23 06:07:01', 'Ace Hardware');
INSERT INTO "darirumah"."mst_product" VALUES (4, 'B001', NULL, 'Ace Furniture', 'set', 'furniture', '2022-11-23 22:54:31', '2022-11-23 22:54:31', NULL);
INSERT INTO "darirumah"."mst_product" VALUES (2, 'A002', 'Kursi', 'Ace Furniture', 'pc', 'furniture', '2022-11-22 00:00:00', '2022-11-22 00:00:00', 'supplier');
INSERT INTO "darirumah"."mst_product" VALUES (3, 'A101', 'Meja Kerja', 'Ace Furniture', 'set', 'furniture', '2022-11-22 00:00:00', '2022-11-22 00:00:00', 'supplier');

-- ----------------------------
-- Table structure for mst_user
-- ----------------------------
DROP TABLE IF EXISTS "darirumah"."mst_user";
CREATE TABLE "darirumah"."mst_user" (
  "id_user" int8 NOT NULL,
  "username" varchar(255) COLLATE "pg_catalog"."default",
  "name" varchar(255) COLLATE "pg_catalog"."default",
  "password" varchar(255) COLLATE "pg_catalog"."default",
  "address" varchar(255) COLLATE "pg_catalog"."default",
  "handphone" varchar(255) COLLATE "pg_catalog"."default",
  "access" int2,
  "created_date" timestamp(0) DEFAULT now(),
  "updated_date" timestamp(0) DEFAULT now(),
  "updated_by" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of mst_user
-- ----------------------------
INSERT INTO "darirumah"."mst_user" VALUES (3, 'admin', 'Admin', 'admin', 'jalan admin', '08XXXXX', 3, '2022-11-22 00:00:00', '2022-11-22 00:00:00', NULL);
INSERT INTO "darirumah"."mst_user" VALUES (1, 'user', 'User', 'user', 'jalan user', '08XXXX', 1, '2022-11-22 00:00:00', '2022-11-22 00:00:00', 'admin');
INSERT INTO "darirumah"."mst_user" VALUES (2, 'supplier', 'Ace Furniture', 'supplier', 'seluruh indonesia', '08888', 2, '2022-11-22 23:45:47', '2022-11-22 23:45:47', 'admin');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS "darirumah"."order";
CREATE TABLE "darirumah"."order" (
  "id_order" int8 NOT NULL,
  "id_user" int8 NOT NULL,
  "id_product" int8 NOT NULL,
  "id_stock" int8 NOT NULL,
  "qty_order" numeric NOT NULL,
  "destination_name" varchar(255) COLLATE "pg_catalog"."default",
  "destination_address" varchar(255) COLLATE "pg_catalog"."default",
  "created_date" timestamp(0) DEFAULT now(),
  "updated_date" timestamp(0) DEFAULT now(),
  "updated_by" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO "darirumah"."order" VALUES (1, 2, 3, 1, 1, 'rico', 'rumah', '2022-11-22 00:00:00', '2022-11-22 00:00:00', 'admin');

-- ----------------------------
-- Table structure for stock
-- ----------------------------
DROP TABLE IF EXISTS "darirumah"."stock";
CREATE TABLE "darirumah"."stock" (
  "id_stock" int8 NOT NULL,
  "id_product" int8 NOT NULL,
  "productcode" varchar(255) COLLATE "pg_catalog"."default",
  "qty_free" numeric NOT NULL,
  "qty_booked" numeric NOT NULL,
  "uom" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "created_date" timestamp(0) DEFAULT now(),
  "updated_date" timestamp(0) DEFAULT now(),
  "updated_by" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of stock
-- ----------------------------
INSERT INTO "darirumah"."stock" VALUES (4, 4, 'B001', 5, 5, 'set', '2022-11-23 07:47:37', '2022-11-23 09:11:31', 'admin');
INSERT INTO "darirumah"."stock" VALUES (5, 2, 'A002', 3, 0, 'pc', '2022-11-23 07:56:25', '2022-11-23 07:56:25', 'supplier');
INSERT INTO "darirumah"."stock" VALUES (1, 1, 'A001', 5, 0, 'pc', '2022-11-22 00:00:00', '2022-11-22 00:00:00', 'supplier');
INSERT INTO "darirumah"."stock" VALUES (3, 3, 'A101', 2, 0, 'set', '2022-11-22 00:00:00', '2022-11-22 00:00:00', 'supplier');

-- ----------------------------
-- Function structure for f_cancelorder
-- ----------------------------
DROP FUNCTION IF EXISTS "darirumah"."f_cancelorder"("inidorder" int4, "iniduser" int4, "inidproduct" int4);
CREATE OR REPLACE FUNCTION "darirumah"."f_cancelorder"("inidorder" int4, "iniduser" int4, "inidproduct" int4)
  RETURNS "pg_catalog"."varchar" AS $BODY$
declare

v_qtyorder integer;
v_username character varying;
v_idstock integer;
v_qtybooked integer;

count_id_history integer;
v_destination character varying;
v_destAddress  character varying;

begin   
    
	select  qty_order, id_stock ,destination_name, destination_address into v_qtyorder, v_idstock, v_destination, v_destAddress from darirumah.order where id_order=inidorder AND id_user = iniduser AND id_product = inidproduct;
	
	if(v_idstock is null) then
	return 'PRODUCT IS FINISHED';
	end if;
	
	select  username into v_username from darirumah.mst_user where id_user = iniduser;
	
	UPDATE darirumah.stock set qty_free= qty_free+v_qtyorder , qty_booked=qty_booked-v_qtyorder,updated_date=now() ,updated_by=v_username 
	where id_stock =v_idstock ;
	
	DELETE FROM darirumah.order where id_stock =v_idstock and id_order = inidorder;


select max(id_history) into count_id_history from darirumah.end_order_history limit 1;
	
	INSERT INTO darirumah.end_order_history(id_history, id_order, id_user, id_product,id_stock, qty_order, destination_name, destination_address, status, created_by) 
VALUES (count_id_history, inidorder, iniduser, inidproduct, v_idstock, v_destination, v_destAddress, 'CANCEL', iniduser);

	return 'SUCCESS';
	
	--select * from darirumah.f_cancelorder(2, 3, 4);
end;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

-- ----------------------------
-- Function structure for f_finishorder
-- ----------------------------
DROP FUNCTION IF EXISTS "darirumah"."f_finishorder"("inidorder" int4, "iniduser" int4, "inidproduct" int4);
CREATE OR REPLACE FUNCTION "darirumah"."f_finishorder"("inidorder" int4, "iniduser" int4, "inidproduct" int4)
  RETURNS "pg_catalog"."varchar" AS $BODY$
declare

v_qtyorder integer;
v_username character varying;
v_idstock integer;
v_qtybooked integer;

count_id_history integer;
v_destination character varying;
v_destAddress  character varying;

begin   
    
	select  qty_order, id_stock , destination_name, destination_address into v_qtyorder, v_idstock , v_destination, v_destAddress from darirumah.order where id_order=inidorder AND id_user = iniduser AND id_product = inidproduct;
	
	if(v_idstock is null) then
	return 'PRODUCT IS FINISHED';
	end if;
	
	select  username into v_username from darirumah.mst_user where id_user = iniduser;
	
	UPDATE darirumah.stock set qty_booked=qty_booked-v_qtyorder,updated_date=now() ,updated_by=v_username 
	where id_stock =v_idstock ;
	
	DELETE FROM darirumah.order where id_stock =v_idstock and id_order = inidorder;

	select max(id_history) into count_id_history from darirumah.end_order_history limit 1;
	
	INSERT INTO darirumah.end_order_history(id_history, id_order, id_user, id_product,id_stock, qty_order, destination_name, destination_address, status, created_by) 
VALUES (count_id_history, id_order, iniduser, inidproduct, v_idstock, v_destination, v_destAddress, 'FINISH', iniduser);

	return 'SUCCESS';
	
	--select * from darirumah.f_finishorder(2, 3, 4);
end;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

-- ----------------------------
-- Function structure for f_insertproduct
-- ----------------------------
DROP FUNCTION IF EXISTS "darirumah"."f_insertproduct"("iniduser" varchar, "inproductcode" varchar, "inproductname" varchar, "inuom" varchar, "intype" varchar);
CREATE OR REPLACE FUNCTION "darirumah"."f_insertproduct"("iniduser" varchar, "inproductcode" varchar, "inproductname" varchar, "inuom" varchar, "intype" varchar)
  RETURNS "pg_catalog"."varchar" AS $BODY$
declare

count_id integer;
v_iduser integer;
v_namesupplier character varying;
v_productcode character varying;

begin   
    
	select max(id_product) into count_id from darirumah.mst_product limit 1;
	count_id = count_id+1;
	v_iduser = iniduser;
	select name into v_namesupplier from darirumah.mst_user where id_user = v_iduser;

--return count_id||'-'||v_namesupplier;
select productcode into v_productcode from darirumah.mst_product where productcode = inproductcode and supplier = v_namesupplier;
	if(v_productcode is not null) then 
		return 'PRODUCT_EXIST';
	end if;
	
	INSERT INTO darirumah.mst_product(id_product, productcode, productname, supplier, uom, type, updated_by) 
	VALUES 
	(count_id, inproductcode, inproductname, v_namesupplier, inuom, intype, v_namesupplier);

	return 'SUCCESS';
	
	--select * from darirumah.f_insertproduct('3', 'inproductcode', 'inproductname', 'inuom', 'intype');
end;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

-- ----------------------------
-- Function structure for f_insertstock
-- ----------------------------
DROP FUNCTION IF EXISTS "darirumah"."f_insertstock"("iniduser" varchar, "inproductcode" varchar, "inqty" varchar);
CREATE OR REPLACE FUNCTION "darirumah"."f_insertstock"("iniduser" varchar, "inproductcode" varchar, "inqty" varchar)
  RETURNS "pg_catalog"."varchar" AS $BODY$
declare

count_id integer;
v_iduser integer;
v_username character varying;
v_namesupplier character varying;
v_idproduct integer;
v_uom character varying;
v_qty integer;

begin   
    
	select max(id_stock) into count_id from darirumah.stock limit 1;
	count_id = count_id+1;
	v_iduser = iniduser;
	select name, username into v_namesupplier , v_username from darirumah.mst_user where id_user = v_iduser;

select id_product, uom into v_idproduct, v_uom from darirumah.mst_product where productcode = inproductcode and supplier = v_namesupplier;
	if(v_idproduct is null) then 
		return 'PRODUCT_NOT_EXIST';
	end if;
	v_qty = inqty;
	
	INSERT INTO darirumah.stock(id_stock,id_product, productcode, qty_free,qty_booked, uom, updated_by) 
	VALUES 
	(count_id,v_idproduct, inproductcode, v_qty, 0, v_uom, v_username);

	return 'SUCCESS';
	
	--select * from darirumah.f_insertstock('3', 'inproductcode', '10');
end;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

-- ----------------------------
-- Function structure for f_insertuser
-- ----------------------------
DROP FUNCTION IF EXISTS "darirumah"."f_insertuser"("inusername" varchar, "inname" varchar, "inpassword" varchar, "inaddress" varchar, "inhandphone" varchar);
CREATE OR REPLACE FUNCTION "darirumah"."f_insertuser"("inusername" varchar, "inname" varchar, "inpassword" varchar, "inaddress" varchar, "inhandphone" varchar)
  RETURNS "pg_catalog"."bpchar" AS $BODY$
declare

count_id integer;

begin   
    
	select max(id_user) into count_id from darirumah.mst_user limit 1;
	
	count_id = count_id+1;
	
	INSERT INTO darirumah.mst_user(id_user, username, name, password, address, handphone, access, updated_by) 
	VALUES 
	(count_id, inusername, inname, inpassword, inaddress, inhandphone, 0, 'Admin');

	return 'SUCCESS';
	
	--select * from darirumah.f_insertuser('inusername', 'inname', 'inpassword', 'inaddress', 'inhandphone');
end;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

-- ----------------------------
-- Function structure for f_placeorder
-- ----------------------------
DROP FUNCTION IF EXISTS "darirumah"."f_placeorder"("iniduser" varchar, "inproductcode" varchar, "inqty" varchar, "indestination" varchar, "indestaddress" varchar);
CREATE OR REPLACE FUNCTION "darirumah"."f_placeorder"("iniduser" varchar, "inproductcode" varchar, "inqty" varchar, "indestination" varchar, "indestaddress" varchar)
  RETURNS "pg_catalog"."varchar" AS $BODY$
declare

count_id integer;
v_iduser integer;
v_username character varying;
v_idproduct integer;
v_uom character varying;
v_idstock integer;
v_qtyorder integer;
v_qtybooked integer;
v_qtyfree integer;
count_id_history integer;



begin   
    
	select max(id_order) into count_id from darirumah.order limit 1;
	count_id = count_id+1;
	v_iduser = iniduser;
	select  username into v_username from darirumah.mst_user where id_user = v_iduser;

select id_product, id_stock , qty_free ,qty_booked into v_idproduct, v_idstock, v_qtyfree, v_qtybooked from darirumah.stock where productcode = inproductcode;
	if(v_idstock is null) then 
		return 'NO_PRODUCT_AVAILABLE';
	end if;
	
	v_qtyorder = inqty;
	if(v_qtyfree < v_qtyorder) then
		return 'STOCK_IS_NOT_ENOUGH';
	end if;
	
	INSERT INTO darirumah.order(id_order,id_user,id_product,id_stock,qty_order ,destination_name,destination_address,updated_by) 
	VALUES 
	(count_id,v_iduser, v_idproduct,v_idstock, v_qtyorder, v_username,indestination,indestaddress);
	
	UPDATE darirumah.stock set qty_free=v_qtyfree-v_qtyorder, qty_booked=v_qtybooked+v_qtyorder,updated_date=now() ,updated_by=v_username 
	where id_stock =v_idstock and id_product = v_idproduct;
	
	select max(id_history) into count_id_history from darirumah.end_order_history limit 1;
	
	INSERT INTO darirumah.end_order_history(id_history, id_order, id_user, id_product,id_stock, qty_order, destination_name, destination_address, status, created_by) 
VALUES (count_id_history, count_id, v_iduser, v_idproduct, v_idstock, indestination, indestaddress, 'ORDER', v_iduser);

	return ''||count_id;
	
	--select * from darirumah.f_placeorder('3', 'inproductcode', '10','rico','jalanin aja dulu');
end;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

-- ----------------------------
-- Function structure for f_updatepassword
-- ----------------------------
DROP FUNCTION IF EXISTS "darirumah"."f_updatepassword"("iniduser" varchar, "inoldpassword" varchar, "innewpassword" varchar);
CREATE OR REPLACE FUNCTION "darirumah"."f_updatepassword"("iniduser" varchar, "inoldpassword" varchar, "innewpassword" varchar)
  RETURNS "pg_catalog"."bpchar" AS $BODY$
declare

count_id integer;
v_iduser integer;
v_name character varying;
v_username character varying;
v_oldpassword character varying;

begin   
    
		v_iduser = iniduser::int;
	select name into v_name from darirumah.mst_user where id_user = v_iduser;
	if(v_name is null) then
		return 'NOT_FOUND';
		end if;
		
		
	select username into v_username from darirumah.mst_user where id_user = v_iduser and password = inoldpassword;
	if(v_username is null) then
		return 'NOT_MATCH';
		end if;
		
		
	
	UPDATE darirumah.mst_user SET password = innewpassword , updated_date = now() , updated_by = v_username where id_user = v_iduser and password = inoldpassword;
	

	return 'SUCCESS';
	
	--select * from darirumah.f_updatepassword('5', 'admin', 'innewpassword');
end;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

-- ----------------------------
-- Primary Key structure for table end_order_history
-- ----------------------------
ALTER TABLE "darirumah"."end_order_history" ADD CONSTRAINT "end_order_history_pkey" PRIMARY KEY ("id_history");

-- ----------------------------
-- Primary Key structure for table mst_product
-- ----------------------------
ALTER TABLE "darirumah"."mst_product" ADD CONSTRAINT "product_copy1_pkey" PRIMARY KEY ("id_product");

-- ----------------------------
-- Primary Key structure for table mst_user
-- ----------------------------
ALTER TABLE "darirumah"."mst_user" ADD CONSTRAINT "user_pkey" PRIMARY KEY ("id_user");

-- ----------------------------
-- Primary Key structure for table order
-- ----------------------------
ALTER TABLE "darirumah"."order" ADD CONSTRAINT "order_pkey" PRIMARY KEY ("id_order");

-- ----------------------------
-- Primary Key structure for table stock
-- ----------------------------
ALTER TABLE "darirumah"."stock" ADD CONSTRAINT "stock_pkey" PRIMARY KEY ("id_stock");
