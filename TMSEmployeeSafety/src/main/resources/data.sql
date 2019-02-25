

insert into user_entity values('10001','noida sector 119','noida sector 119', '1qaAaa', 'shivani puri',8860095906, 'user','shipuri');
insert into user_entity values('10002','noida sector 119','noida sector 1190', 'password', 'sourva pathak',8585912788, 'user','souravp@test.com');

insert into route_entity(id,is_started,is_completed,route_number,shift,delayed_by,vehicle_number,eta_in_minutes) values(1,true,false,15,'DROP',0,'UP-14-1234',2);

insert into route_sequence_entity (user_name,boarded,deboarded,drop_location,oracle_id,route_id,route_number,sequence_number,eta_in_minutes,delayed_by) values ('shipuri',false,false,'Noida','10001',1,15,1,1,0)