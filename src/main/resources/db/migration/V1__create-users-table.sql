CREATE TABLE IF NOT EXISTS public.users
(
    role smallint,
    id serial NOT NULL,
    email character varying(255),
    name character varying(255),
    password character varying(255),
    phone_number character varying(255),
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT users_email_key UNIQUE (email),
    CONSTRAINT users_role_check CHECK (role >= 0 AND role <= 1)
);


