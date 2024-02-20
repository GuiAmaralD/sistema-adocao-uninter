ALTER TABLE public.pet
ADD COLUMN size_id bigint REFERENCES public.pet_size (id) ON DELETE NO ACTION;