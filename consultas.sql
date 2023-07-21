--5. Cantidad de equipos que contiene cada liga
SELECT hexa.dbo.ligas.nombre, COUNT(hexa.dbo.ligasequipos.idEquipo) AS cantidad_equipos
FROM hexa.dbo.ligasequipos AS lq
INNER JOIN hexa.dbo.ligas ON lq.IdLiga = hexa.dbo.ligas.idLiga
GROUP BY hexa.dbo.ligas.nombre
ORDER BY cantidad_equipos DESC;

--6. Cantidad de equipos por cada país.
SELECT hexa.dbo.paises.Nombre, COUNT(hexa.dbo.ligasequipos.IdEquipo) AS cantidad_equipos
FROM hexa.dbo.ligasequipos AS lq
INNER JOIN hexa.dbo.ligas ON lq.IdLiga = hexa.dbo.ligas.IdLiga
INNER JOIN hexa.dbo.paises ON hexa.dbo.ligas.IdPais = hexa.dbo.paises.IdPais
GROUP BY hexa.dbo.paises.Nombre
ORDER BY cantidad_equipos DESC, hexa.dbo.paises.Nombre ASC;