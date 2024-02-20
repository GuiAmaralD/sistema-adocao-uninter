CREATE TABLE IF NOT EXISTS public.pet_size
(
    size character varying(30),
    id serial NOT NULL,
    CONSTRAINT pet_size_pkey PRIMARY KEY (id)
);