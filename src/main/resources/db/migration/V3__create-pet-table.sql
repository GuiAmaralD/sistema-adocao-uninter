CREATE TABLE IF NOT EXISTS public.pet
(
    adopted boolean NOT NULL,
    registered_at date,
    sex character(1),
    id serial NOT NULL,
    specie_id bigint REFERENCES public.specie (id) ON DELETE NO ACTION,
    user_id bigint REFERENCES public.users (id) ON DELETE CASCADE,
    description character varying(255),
    nickname character varying(255),
    CONSTRAINT pet_pkey PRIMARY KEY (id)
);