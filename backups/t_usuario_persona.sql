create or replace function sch_admin.f_obtener_usuario_dado_codigo(codigo_usuario int)
    returns setof sch_admin.v_datos_usuario as
$body$
begin
    return query
    select *
    from sch_admin.v_datos_usuario
    where sr_id_usuario = codigo_usuario
        and ch_estado_logico = 'A';
end;
$body$
    language plpgsql volatile;


create or replace function sch_admin.f_obtener_rol_usuario_dado_codigos(codigo_rol int, codigo_usuario int)
    returns setof sch_admin.t_rol_usuario as
$body$
begin
    return query
    select *
    from sch_admin.t_rol_usuario
    where int_id_rol=codigo_rol
        and int_id_usuario=codigo_usuario;
end;
$body$
    language plpgsql volatile;
    
    
select * from sch_admin.f_obtener_rol_usuario_dado_codigos(1,1);


create or replace function sch_admin.f_obtener_rol_dado_codigo(codigo_rol int)
    returns setof sch_admin.t_rol as
$body$
begin
    return query
    select *
    from sch_admin.t_rol
    where sr_id_rol=codigo_rol;
end;
$body$
    language plpgsql volatile;
    
    
select * from sch_admin.f_obtener_rol_dado_codigo(1);




    