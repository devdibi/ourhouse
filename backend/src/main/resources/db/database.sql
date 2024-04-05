-- sido table
insert into `ssafy`.sido select distinct substr(dongcode,1,2), sidoname from `house`.dongcode;
-- sigungu table
insert into `ssafy`.sigungu select distinct substr(dongcode,1,5) , gugunName, substr(dongcode,1,2) from `house`.dongcode where gugunName is not null;
-- dong table
insert ignore into `ssafy`.dong select dongcode , dongname, substr(dongcode,1,2), substr(dongcode,1,5) from `house`.dongcode;

set foreign_key_checks = 0;

ALTER TABLE `house`.houseinfo MODIFY COLUMN lng double;
ALTER TABLE `house`.houseinfo MODIFY COLUMN lat double;


set foreign_key_checks = 0;
ALTER TABLE `house`.houseinfo MODIFY COLUMN dongCode bigint;
alter table `house`.dongcode modify column  dongCode bigint;


-- house_info table
insert into `ssafy`.house_info
SELECT aptCode, buildYear, apartmentName, CONCAT(dong, ' ', jibun) jibun_address,
       CONCAT(roadName, ' ', TRIM(LEADING '0' FROM CAST(roadnamebonbun AS CHAR)), '-', TRIM(LEADING '0' FROM CAST(roadnamebonbun AS CHAR))) as road_address, lng, lat, dongCode
FROM `house`.houseinfo;

SELECT aptCode, buildYear, apartmentName, CONCAT(dong, ' ', jibun) jibun_address, CONCAT(roadName, ' ', TRIM(LEADING '0' FROM CAST(roadnamebonbun AS CHAR)), '-',
	TRIM(LEADING '0' FROM CAST(roadnamebonbun AS CHAR))) as road_address, lng, lat, dongcode
	FROM `house`.houseinfo;

desc `house`.houseinfo;
desc `ssafy`.house_info;

-- house_deal table
insert into `ssafy`.house_deal
select no, dealAmount as price, dealYear, dealMonth, dealDay, area, floor, aptCode from `house`.housedeal;

update `house`.housedeal set dealAmount = replace(dealAmount,',','');

desc `house`.housedeal;

desc `ssafy`.house_deal;