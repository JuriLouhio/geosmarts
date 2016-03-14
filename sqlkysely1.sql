SELECT DISTINCT alue.nimi,Count(viesti.id_viesti), MAX(viesti.aika) FROM alue
                 INNER JOIN viestiketju ON alue.id_alue = viestiketju.alue_id
                 INNER JOIN viesti ON viestiketju.id_viestiketju = viesti.viestiketju_id
                 group by alue.nimi