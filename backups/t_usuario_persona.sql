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
    language plpgsql volatile 