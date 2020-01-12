insert into manager(id,balance,password)
 ( select 161250128,0,"yummy"
   where not exists(select * from manager where id=161250128));