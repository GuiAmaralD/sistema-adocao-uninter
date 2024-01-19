CREATE TABLE IF NOT EXISTS public.specie
(
    name character varying(30),
    id serial NOT NULL,
    CONSTRAINT specie_pkey PRIMARY KEY (id)
);
