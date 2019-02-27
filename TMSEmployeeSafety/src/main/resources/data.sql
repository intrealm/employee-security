insert into route_entity(id,is_started,is_completed,route_number,shift,delayed_by,vehicle_number,eta_in_minutes) values(1,false,false,15,'DROP',0,'UP-14-1234',2);
insert into route_entity(id,is_started,is_completed,route_number,shift,delayed_by,vehicle_number,eta_in_minutes) values(2,true,false,15,'DROP',0,'UP-14-1234',2);
insert into route_entity(id,is_started,is_completed,route_number,shift,delayed_by,vehicle_number,eta_in_minutes) values(3,false,false,11,'DROP',0,'UP-14-1562',2);
insert into route_entity(id,is_started,is_completed,route_number,shift,delayed_by,vehicle_number,eta_in_minutes) values(4,false,false,16,'DROP',0,'UP-14-6678',7);
insert into route_entity(id,is_started,is_completed,route_number,shift,delayed_by,vehicle_number,eta_in_minutes) values(5,false,false,05,'DROP',0,'UP-16-2312',4);


insert into route_sequence_entity (user_name,boarded,deboarded,drop_location,oracle_id,route_id,route_number,sequence_number,eta_in_minutes,delayed_by,name) values ('ssingh',false,false,'New Delhi','13321',1,15,1,1,0,'Simon Singh');
insert into route_sequence_entity (user_name,boarded,deboarded,drop_location,oracle_id,route_id,route_number,sequence_number,eta_in_minutes,delayed_by,name) values ('ravkumar',false,false,'Noida Sector 119','13351',1,15,2,1,0,'Ravish Kumar');
insert into route_sequence_entity (user_name,boarded,deboarded,drop_location,oracle_id,route_id,route_number,sequence_number,eta_in_minutes,delayed_by,name) values ('tanvkhn',false,false,'Noida Sector 119','13352',1,15,2,1,0,'Tanvi Khanna');
insert into route_sequence_entity (user_name,boarded,deboarded,drop_location,oracle_id,route_id,route_number,sequence_number,eta_in_minutes,delayed_by,name) values ('prisha',false,false,'Noida Sector 119','13353',1,15,2,1,0,'Priya Sharma');
insert into route_sequence_entity (user_name,boarded,deboarded,drop_location,oracle_id,route_id,route_number,sequence_number,eta_in_minutes,delayed_by,name) values ('ngupta',false,false,'Noida Sector 119','13354',1,15,2,1,0,'Neha Gupta');


insert into route_sequence_entity (user_name,boarded,deboarded,drop_location,oracle_id,route_id,route_number,sequence_number,eta_in_minutes,delayed_by,name) values ('reejain',false,false,'Noida Sector 100','13323',2,11,1,1,0,'Reema Jain');
insert into route_sequence_entity (user_name,boarded,deboarded,drop_location,oracle_id,route_id,route_number,sequence_number,eta_in_minutes,delayed_by,name) values ('racarora',false,false,'Gurugram','13324',3,16,4,1,0,'Rachit Arora');
insert into route_sequence_entity (user_name,boarded,deboarded,drop_location,oracle_id,route_id,route_number,sequence_number,eta_in_minutes,delayed_by,name) values ('shamirza',false,false,'Gurugram','13325',3,05,4,1,0,'Shaheen Mirza');

insert into escalation_entity (user_name,phone_number,escalation_phone_numbers) values ('ssingh','8860095906','8860095906');
insert into escalation_entity (user_name,phone_number,escalation_phone_numbers) values ('ravkumar','7210806318','7210806318');
insert into escalation_entity (user_name,phone_number,escalation_phone_numbers) values ('reejain','8585912788','8585912788');
insert into escalation_entity (user_name,phone_number,escalation_phone_numbers) values ('racarora','9953237590','9953237590');
insert into escalation_entity (user_name,phone_number,escalation_phone_numbers) values ('shamirza','9891660262','9891660262');


insert into user_entity (id,address,drop_location,encrypted_password,name,phone_number,role_id,user_name,salt) values('10001','New Delhi','Tilak Nagar', 'tznXFAzqcyoLj0RMh3v3JXEsA3Z1ZEJJQVA5E6ZOma0=', 'Simon SIngh',8860095906, 'user','ssingh','roHPsNHVXGtiOrId2EVEtCeTwEVj2r');
insert into user_entity (id,address,drop_location,encrypted_password,name,phone_number,role_id,user_name,salt) values('10002','Noida Sector 119','Noida Sector 119', 'tznXFAzqcyoLj0RMh3v3JXEsA3Z1ZEJJQVA5E6ZOma0=', 'Ravish Kumar',7210806318, 'user','ravkumar','roHPsNHVXGtiOrId2EVEtCeTwEVj2r');
insert into user_entity (id,address,drop_location,encrypted_password,name,phone_number,role_id,user_name,salt) values('10003','Noida Sector 100','Noida Sector 100', 'tznXFAzqcyoLj0RMh3v3JXEsA3Z1ZEJJQVA5E6ZOma0=', 'Reema Jain',8585912788, 'user','reejain','roHPsNHVXGtiOrId2EVEtCeTwEVj2r');
insert into user_entity (id,address,drop_location,encrypted_password,name,phone_number,role_id,user_name,salt) values('10004','Gurugram','Sector 15', 'tznXFAzqcyoLj0RMh3v3JXEsA3Z1ZEJJQVA5E6ZOma0=', 'Rachit Arora',9953237590, 'user','racarora','roHPsNHVXGtiOrId2EVEtCeTwEVj2r');
insert into user_entity (id,address,drop_location,encrypted_password,name,phone_number,role_id,user_name,salt) values('10005','Gurugram','Sector 12', 'tznXFAzqcyoLj0RMh3v3JXEsA3Z1ZEJJQVA5E6ZOma0=', 'Shaheen Mirza',9891660262, 'user','shamirza','roHPsNHVXGtiOrId2EVEtCeTwEVj2r');

insert into user_entity (id,address,drop_location,encrypted_password,name,phone_number,role_id,user_name,salt) values('10006','sapient noida','sapient noida', 'tznXFAzqcyoLj0RMh3v3JXEsA3Z1ZEJJQVA5E6ZOma0=', 'John Doe',8860095906, 'admin','admin','roHPsNHVXGtiOrId2EVEtCeTwEVj2r');

insert into cab_last_latlong(id,route_Id,lat,lon)
values(1,2,28.6469655,77.0932634);

