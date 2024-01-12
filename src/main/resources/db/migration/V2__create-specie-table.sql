CREATE TABLE IF NOT EXISTS public.specie
(
    name smallint,
    id serial NOT NULL,
    CONSTRAINT specie_pkey PRIMARY KEY (id),
    CONSTRAINT specie_name_check CHECK (name >= 0 AND name <= 2)
);
