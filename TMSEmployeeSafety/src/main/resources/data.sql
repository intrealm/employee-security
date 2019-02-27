

insert into user_entity (id,address,drop_location,encrypted_password,name,phone_number,role_id,user_name,salt) values('10001','noida sector 119','noida sector 119', 'tznXFAzqcyoLj0RMh3v3JXEsA3Z1ZEJJQVA5E6ZOma0=', 'shivani puri',8860095906, 'user','shipuri','roHPsNHVXGtiOrId2EVEtCeTwEVj2r');

insert into user_entity (id,address,drop_location,encrypted_password,name,phone_number,role_id,user_name,salt) values ('10002','noida sector 119','noida sector 1190', 'tznXFAzqcyoLj0RMh3v3JXEsA3Z1ZEJJQVA5E6ZOma0=', 'sourva pathak',8585912788, 'user','souravp','roHPsNHVXGtiOrId2EVEtCeTwEVj2r');

insert into user_entity (id,address,drop_location,encrypted_password,name,phone_number,role_id,user_name,salt) values('10003','noida sector 119','noida sector 119', 'tznXFAzqcyoLj0RMh3v3JXEsA3Z1ZEJJQVA5E6ZOma0=', 'shivani puri',8860095906, 'admin','admin','roHPsNHVXGtiOrId2EVEtCeTwEVj2r');

insert into route_entity(id,is_started,is_completed,route_number,shift,delayed_by,vehicle_number,eta_in_minutes) values(1,false,false,15,'DROP',0,'UP-14-1234',2);
insert into route_entity(id,is_started,is_completed,route_number,shift,delayed_by,vehicle_number,eta_in_minutes) values(2,true,false,15,'DROP',0,'UP-14-1234',2);

insert into route_sequence_entity (user_name,boarded,deboarded,drop_location,oracle_id,route_id,route_number,sequence_number,eta_in_minutes,delayed_by,name) values ('shipuri',false,false,'Noida','10001',1,15,1,1,0,'Shivani Puri');
insert into route_sequence_entity (user_name,boarded,deboarded,drop_location,oracle_id,route_id,route_number,sequence_number,eta_in_minutes,delayed_by,name) values ('mahendra',false,false,'Noida','10003',1,15,1,1,0,'Mahendra Pal');


insert into escalation_entity (user_name,phone_number,escalation_phone_numbers) values ('shipuri','8860095906','8860095906');

insert into sos_entity (id,user_Name,route_Id,raised_On,raised_At,resolved_On,resolved_At,resolved)
values(1,'shipuri',1,sysdate,sysdate,sysdate,sysdate,0) ;

insert into user_last_latlong(id,user_Id,lat,lon)
values(1,10001,28.6077159025,77.224249103);

insert into cab_last_latlong(id,route_Id,lat,lon)
values(1,2,28.6469655,77.0932634);

