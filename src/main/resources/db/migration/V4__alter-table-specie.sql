ALTER TABLE public.specie ADD COLUMN name_new character varying(255);

UPDATE public.specie
SET name_new = CASE name
                   WHEN 0 THEN 'cachorro'
                   WHEN 1 THEN 'gato'
                   WHEN 2 THEN 'ave'
               END;
ALTER TABLE public.specie RENAME COLUMN name TO name_old;
ALTER TABLE public.specie RENAME COLUMN name_new TO name;

ALTER TABLE public.specie DROP COLUMN name_old;
