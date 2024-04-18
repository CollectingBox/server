insert into location values (1, '동우빌라 폐의류 수거함', st_geomfromtext('point(37.5067486779393 127.046374536307)', 4326), '서울특별시', '강남구', '역삼동', '서울특별시 강남구 테헤란로57길 38', '서울특별시 강남구 역삼1동 694-6', '2024-04-12 00:00:00', '2024-04-12 00:00:00');
insert into location values (2, '폐의류 수거함', st_geomfromtext('point(37.5206841930406 127.043892691669)', 4326), '서울특별시', '강남구', '청담동', '서울특별시 강남구 도산대로66길 43', '서울특별시 강남구 청담동 16-19', '2024-04-12 00:00:00', '2024-04-12 00:00:00');
insert into location values (3, '폐건전지 수거함', st_geomfromtext('point(37.5215387058511 127.021883587519)', 4326), '서울특별시', '강남구', '신사동', '서울특별시 강남구 압구정로10길 32', '서울특별시 강남구 신사동 524-17', '2024-04-12 00:00:00', '2024-04-12 00:00:00');
insert into location values (4, '청담초등학교 폐건전지 수거함', st_geomfromtext('point(37.5282818704161 127.045153024716)', 4326), '서울특별시', '강남구', '청담동', '서울특별시 강남구 압구정로71길 9', '서울특별시 청담동 142', '2024-04-12 00:00:00', '2024-04-12 00:00:00');
insert into location values (5, '역삼2문화센터 폐건전지 수거함', st_geomfromtext('point(37.4959738258689 127.046815005151)', 4326), '서울특별시', '강남구', '역삼동', '서울특별시 강남구 도곡로43길25', '서울특별시 강남구 역삼 761-26', '2024-04-12 00:00:00', '2024-04-12 00:00:00');
insert into location values (6, '폐건전지 수거함', st_geomfromtext('point(37.505357394013 127.030776033233)', 4326), '서울특별시', '강남구', '역삼동', '서울특별시 강남구 봉은사로20길 14', '서울특별시 강남구 역삼동 613-22', '2024-04-12 00:00:00', '2024-04-12 00:00:00');
insert into location values (7, '폐형광등 수거함', st_geomfromtext('point(37.505357394013 127.030776033233)', 4326), '서울특별시', '강남구', '역삼동', '서울특별시 강남구 봉은사로20길 14', '서울특별시 강남구 역삼동 613-22', '2024-04-12 00:00:00', '2024-04-12 00:00:00');
insert into location values (8, '삼부르네상스파크빌 폐의약품 수거함', st_geomfromtext('point(37.4791183524676 126.894832311293)', 4326), '서울특별시', '구로구', '가리봉동', '서울특별시 구로구 남부순환로 1295', '서울특별시 구로구 가리봉동 137-4', '2024-04-12 00:00:00', '2024-04-12 00:00:00');
insert into location values (9, '쓰레기통 수거함', st_geomfromtext('point(37.5391685443624 126.827450259976)', 4326), '서울특별시', '양천구', '신월동', '서울특별시 양천구 화곡로50', '서울특별시 양천구 신월동 54-1', '2024-04-12 00:00:00', '2024-04-12 00:00:00');
insert into location values (10, '용두 치안센터 쓰레기통', st_geomfromtext('point(37.5783459546265 127.033997269967)', 4326), '서울특별시', '동대문구', '용두동', '서울특별시 동대문구 왕산로95', '서울특별시 동대문구 용두동 230-1', '2024-04-12 00:00:00', '2024-04-12 00:00:00');

insert into collecting_box values (1, 1, 'CLOTHES', '2024-04-12 00:00:00', '2024-04-12 00:00:00');
insert into collecting_box values (2, 2, 'CLOTHES', '2024-04-12 00:00:00', '2024-04-12 00:00:00');
insert into collecting_box values (3, 3, 'BATTERY', '2024-04-12 00:00:00', '2024-04-12 00:00:00');
insert into collecting_box values (4, 4, 'BATTERY', '2024-04-12 00:00:00', '2024-04-12 00:00:00');
insert into collecting_box values (5, 5, 'BATTERY', '2024-04-12 00:00:00', '2024-04-12 00:00:00');
insert into collecting_box values (6, 6, 'BATTERY', '2024-04-12 00:00:00', '2024-04-12 00:00:00');
insert into collecting_box values (7, 7, 'LAMP', '2024-04-12 00:00:00', '2024-04-12 00:00:00');
insert into collecting_box values (8, 8, 'MEDICINE', '2024-04-12 00:00:00', '2024-04-12 00:00:00');
insert into collecting_box values (9, 9, 'TRASH', '2024-04-12 00:00:00', '2024-04-12 00:00:00');
insert into collecting_box values (10, 10, 'TRASH', '2024-04-12 00:00:00', '2024-04-12 00:00:00');

-- 영등포구 대림동 폐의류, 폐건전지, 폐형광등 수거함
insert into location values (11, '유탑유블레스 폐의류 수거함', st_geomfromtext('point(37.486898746437 126.900563317161)', 4326), '서울특별시', '영등포구', '대림동', '서울특별시 영등포구 도림천로19길 12', '서울특별시 영등포구 대림동 1101-1', '2024-04-12 00:00:00', '2024-04-12 00:00:00');
insert into location values (12, '구두수선대84 폐의류 수거함', st_geomfromtext('point(37.4871190365551 126.902174727201)', 4326), '서울특별시', '영등포구', '대림동', '서울특별시 영등포구 시흥대로173길 18-1', '서울특별시 영등포구 대림동 994-25', '2024-04-12 00:00:00', '2024-04-12 00:00:00');
insert into location values (13, '폐의류 수거함', st_geomfromtext('point(37.4872443635989 126.900445152832)', 4326), '서울특별시', '영등포구', '대림동', '서울특별시 영등포구 디지털로38길 20-20', '서울특별시 영등포구 대림동 1095-35', '2024-04-12 00:00:00', '2024-04-12 00:00:00');
insert into location values (14, '대림하나아트빌 폐의류 수거함', st_geomfromtext('point(37.4874924622293 126.898493449135)', 4326), '서울특별시', '영등포구', '대림동', '서울특별시 영등포구 도림천로 425', '서울특별시 영등포구 대림동 1117-2', '2024-04-12 00:00:00', '2024-04-12 00:00:00');
insert into location values (15, '폐의류 수거함', st_geomfromtext('point(37.4880165969872 126.90368147846)', 4326), '서울특별시', '영등포구', '대림동', '서울특별시 영등포구 시흥대로175가길 8', '서울특별시 영등포구 대림동 993-31', '2024-04-12 00:00:00', '2024-04-12 00:00:00');
insert into location values (16, '폐의류 수거함', st_geomfromtext('point(37.4881429034434 126.899764286429)', 4326), '서울특별시', '영등포구', '대림동', '서울특별시 영등포구 디지털로38길 8-3', '서울특별시 영등포구 대림동 1092-22', '2024-04-12 00:00:00', '2024-04-12 00:00:00');
insert into location values (17, '폐의류 수거함', st_geomfromtext('point(37.4881506778778 126.90161718218)', 4326), '서울특별시', '영등포구', '대림동', '서울특별시 영등포구 디지털로48길 23', '서울특별시 영등포구 대림동 1025-73', '2024-04-12 00:00:00', '2024-04-12 00:00:00');
insert into location values (18, '에버하우스 폐의류 수거함', st_geomfromtext('point(37.4883184501382 126.900760894307)', 4326), '서울특별시', '영등포구', '대림동', '서울특별시 영등포구 디지털로40길 14', '서울특별시 영등포구 대림동 1092-43', '2024-04-12 00:00:00', '2024-04-12 00:00:00');
insert into location values (19, '폐의류 수거함', st_geomfromtext('point(37.4888178446615 126.902998281977)', 4326), '서울특별시', '영등포구', '대림동', '서울특별시 영등포구 디지털로54가길 2', '서울특별시 영등포구 대림동 1024-11', '2024-04-12 00:00:00', '2024-04-12 00:00:00');
insert into location values (20, '폐건전지 수거함', st_geomfromtext('point(37.4873435585383 126.900331159208)', 4326), '서울특별시', '영등포구', '대림동', '서울특별시 영등포구 디지털로38길 16-15', '서울특별시 영등포구 대림동 1095-43', '2024-04-12 00:00:00', '2024-04-12 00:00:00');
insert into location values (21, '폐건전지 수거함', st_geomfromtext('point(37.488154779084 126.899531342824)', 4326), '서울특별시', '영등포구', '대림동', '서울특별시 영등포구 디지털로38길 8-8', '서울특별시 영등포구 대림동 1081-88', '2024-04-12 00:00:00', '2024-04-12 00:00:00');
insert into location values (22, '대림2동주민센터 폐형광등 수거함', st_geomfromtext('point(37.4913317598352 126.903016176404)', 4326), '서울특별시', '영등포구', '대림동', '서울특별시 영등포구 대림로23길 25', '서울특별시 영등포구 대림동 705', '2024-04-12 00:00:00', '2024-04-12 00:00:00');

insert into collecting_box values (11, 11, 'CLOTHES', '2024-04-12 00:00:00', '2024-04-12 00:00:00');
insert into collecting_box values (12, 12, 'CLOTHES', '2024-04-12 00:00:00', '2024-04-12 00:00:00');
insert into collecting_box values (13, 13, 'CLOTHES', '2024-04-12 00:00:00', '2024-04-12 00:00:00');
insert into collecting_box values (14, 14, 'CLOTHES', '2024-04-12 00:00:00', '2024-04-12 00:00:00');
insert into collecting_box values (15, 15, 'CLOTHES', '2024-04-12 00:00:00', '2024-04-12 00:00:00');
insert into collecting_box values (16, 16, 'CLOTHES', '2024-04-12 00:00:00', '2024-04-12 00:00:00');
insert into collecting_box values (17, 17, 'CLOTHES', '2024-04-12 00:00:00', '2024-04-12 00:00:00');
insert into collecting_box values (18, 18, 'CLOTHES', '2024-04-12 00:00:00', '2024-04-12 00:00:00');
insert into collecting_box values (19, 19, 'CLOTHES', '2024-04-12 00:00:00', '2024-04-12 00:00:00');
insert into collecting_box values (20, 20, 'BATTERY', '2024-04-12 00:00:00', '2024-04-12 00:00:00');
insert into collecting_box values (21, 21, 'BATTERY', '2024-04-12 00:00:00', '2024-04-12 00:00:00');
insert into collecting_box values (22, 22, 'LAMP', '2024-04-12 00:00:00', '2024-04-12 00:00:00');

-- 강서구 화곡동 폐의류, 폐건전지, 폐형광등, 폐의약품 수거함 + 쓰레기통 (37.5404202469638 126.840017492851)
insert into location values (23, '뉴파인빌 폐의류 수거함', st_geomfromtext('point(37.5396125849777 126.840012768981)', 4326), '서울특별시', '강서구', '화곡동', '서울특별시 강서구 강서로31길27', '서울특별시 강서구 화곡동 1078-16', '2024-04-18 00:00:00', '2024-04-18 00:00:00');
insert into location values (24, '계명위너스 폐의류 수거함', st_geomfromtext('point(37.5405858232485 126.838475120294)', 4326), '서울특별시', '강서구', '화곡동', '서울특별시 강서구 화곡로26가길28', '서울특별시 강서구 화곡동 1074-3', '2024-04-18 00:00:00', '2024-04-18 00:00:00');
insert into location values (25, '삼성쉐르빌 폐의류 수거함', st_geomfromtext('point(37.5397462314441 126.83851110261)', 4326), '서울특별시', '강서구', '화곡동', '서울특별시 강서구 화곡로24길 28', '서울특별시 강서구 화곡동 1081-30', '2024-04-18 00:00:00', '2024-04-18 00:00:00');
insert into location values (26, '신월초등학교 담 폐건전지 수거함', st_geomfromtext('point(37.5403655143855 126.839455478388)', 4326), '서울특별시', '강서구', '화곡동', '서울특별시 강서구 강서로31길 48', '서울특별시 강서구 화곡동 1080', '2024-04-18 00:00:00', '2024-04-18 00:00:00');
insert into location values (27, '영화빌라 폐건전지 수거함', st_geomfromtext('point(37.5320901489733 126.84344956439)', 4326), '서울특별시', '강서구', '화곡동', '서울특별시 강서구 강서로15길 47', '서울특별시 강서구 화곡동 346-121', '2024-04-18 00:00:00', '2024-04-18 00:00:00');
insert into location values (28, '강서구보건소화곡분소 폐건전지 수거함', st_geomfromtext('point(37.535468012042 126.841098745284)', 4326), '서울특별시', '강서구', '화곡동', '서울특별시 강서구 월정로30길 96', '서울특별시 강서구 화곡동 366-1', '2024-04-18 00:00:00', '2024-04-18 00:00:00');
insert into location values (29, '신월초등학교 담 폐형광등 수거함', st_geomfromtext('point(37.5403655143855 126.839455478388)', 4326), '서울특별시', '강서구', '화곡동', '서울특별시 강서구 강서로31길 48', '서울특별시 강서구 화곡동 1080', '2024-04-18 00:00:00', '2024-04-18 00:00:00');
insert into location values (30, '강서구보건소화곡분소 폐형광등 수거함', st_geomfromtext('point(37.535468012042 126.841098745284)', 4326), '서울특별시', '강서구', '화곡동', '서울특별시 강서구 월정로30길 96', '서울특별시 강서구 화곡동 366-1', '2024-04-18 00:00:00', '2024-04-18 00:00:00');
insert into location values (31, '메디팜21세기약국 폐의약품 수거함', st_geomfromtext('point(37.5411514783623 126.839671044277)', 4326), '서울특별시', '강서구', '화곡동', '서울특별시 강서구 화곡로 162', '서울특별시 강서구 화곡동 1074-33', '2024-04-18 00:00:00', '2024-04-18 00:00:00');
insert into location values (32, '휴베이스비타민약국 폐의약품 수거함', st_geomfromtext('point(37.5348879429263 126.837978157379)', 4326), '서울특별시', '강서구', '화곡동', '서울특별시 강서구 가로공원로76길 51', '서울특별시 강서구 화곡동 370-75', '2024-04-18 00:00:00', '2024-04-18 00:00:00');
insert into location values (33, '대학약국 폐의약품 수거함', st_geomfromtext('point(37.5408959485731 126.838673936917)', 4326), '서울특별시', '강서구', '화곡동', '서울특별시 강서구 화곡로 152', '서울특별시 강서구 화곡동 1074-7', '2024-04-18 00:00:00', '2024-04-18 00:00:00');
insert into location values (34, '까치산역 쓰레기통', st_geomfromtext('point(37.5319560847746 126.846611592059)', 4326), '서울특별시', '강서구', '화곡동', '서울특별시 강서구 강서로 지하 54', '서울특별시 강서구 화곡동 662-5', '2024-04-18 00:00:00', '2024-04-18 00:00:00');
insert into location values (35, '청산빌딩 쓰레기통', st_geomfromtext('point(37.5409897417689 126.838937377558)', 4326), '서울특별시', '강서구', '화곡동', '서울특별시 강서구 화곡로 154', '서울특별시 강서구 화곡동 1074-12', '2024-04-18 00:00:00', '2024-04-18 00:00:00');
insert into location values (36, '쓰레기통', st_geomfromtext('point(37.5410819348476 126.837178691401)', 4326), '서울특별시', '강서구', '화곡동', '서울특별시 강서구 화곡로 139', '서울특별시 강서구 화곡동 1066-2', '2024-04-18 00:00:00', '2024-04-18 00:00:00');
insert into location values (37, '쓰레기통', st_geomfromtext('point(37.5412413213065 126.840345915343)', 4326), '서울특별시', '강서구', '화곡동', '서울특별시 강서구 강서로 163', '서울특별시 강서구 화곡동 1075-3', '2024-04-18 00:00:00', '2024-04-18 00:00:00');

insert into collecting_box values (23, 23, 'CLOTHES', '2024-04-18 00:00:00', '2024-04-18 00:00:00');
insert into collecting_box values (24, 24, 'CLOTHES', '2024-04-18 00:00:00', '2024-04-18 00:00:00');
insert into collecting_box values (25, 25, 'CLOTHES', '2024-04-18 00:00:00', '2024-04-18 00:00:00');
insert into collecting_box values (26, 26, 'BATTERY', '2024-04-18 00:00:00', '2024-04-18 00:00:00');
insert into collecting_box values (27, 27, 'BATTERY', '2024-04-18 00:00:00', '2024-04-18 00:00:00');
insert into collecting_box values (28, 28, 'BATTERY', '2024-04-18 00:00:00', '2024-04-18 00:00:00');
insert into collecting_box values (29, 29, 'LAMP', '2024-04-18 00:00:00', '2024-04-18 00:00:00');
insert into collecting_box values (30, 30, 'LAMP', '2024-04-18 00:00:00', '2024-04-18 00:00:00');
insert into collecting_box values (31, 31, 'MEDICINE', '2024-04-18 00:00:00', '2024-04-18 00:00:00');
insert into collecting_box values (32, 32, 'MEDICINE', '2024-04-18 00:00:00', '2024-04-18 00:00:00');
insert into collecting_box values (33, 33, 'MEDICINE', '2024-04-18 00:00:00', '2024-04-18 00:00:00');
insert into collecting_box values (34, 34, 'TRASH', '2024-04-18 00:00:00', '2024-04-18 00:00:00');
insert into collecting_box values (35, 35, 'TRASH', '2024-04-18 00:00:00', '2024-04-18 00:00:00');
insert into collecting_box values (36, 36, 'TRASH', '2024-04-18 00:00:00', '2024-04-18 00:00:00');
insert into collecting_box values (37, 37, 'TRASH', '2024-04-18 00:00:00', '2024-04-18 00:00:00');