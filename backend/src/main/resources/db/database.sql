-- sido table
insert into `whereismyhome`.sido select distinct substr(dongcode,1,2), sidoname from `house`.dongcode;
-- sigungu table
insert into `whereismyhome`.sigungu select distinct substr(dongcode,1,5) , gugunname, substr(dongcode,1,2) from `house`.dongcode;
-- dong table
insert ignore into `whereismyhome`.dong select dongcode , dongname, substr(dongcode,1,2), substr(dongcode,1,5) from `house`.dongcode;

-- house_info table
insert into `whereismyhome`.house_info 
SELECT aptCode, buildYear, apartmentName, CONCAT(dong, ' ', jibun) jibun_address, CONCAT(roadName, ' ', TRIM(LEADING '0' FROM CAST(roadnamebonbun AS CHAR)), '-', 
	TRIM(LEADING '0' FROM CAST(roadnamebonbun AS CHAR))) road_address, lng, lat, dongcode
	FROM house.houseinfo;

-- house_deal table
insert into `whereismyhome`.house_deal
select no, dealAmount, dealYear, dealMonth, dealDay, area, floor, aptCode from `house`.housedeal;

update `house`.housedeal set dealAmount = replace(dealAmount,',','');

