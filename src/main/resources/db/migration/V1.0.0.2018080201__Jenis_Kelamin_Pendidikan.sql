alter table pelanggan 
   add column jenis_kelamin varchar(100);

alter table pelanggan 
   add column pendidikan_terakhir varchar(100);

alter table pelanggan 
   add column tanggal_lahir date;

update pelanggan set
   jenis_kelamin= 'TIDAK_DISEBUTKAN', 
   pendidikan_terakhir = 'TIDAK_DISEBUTKAN'; 

alter table pelanggan
   modify jenis_kelamin varchar(100) not null;

alter table pelanggan
   modify pendidikan_terakhir varchar(100) not null;
