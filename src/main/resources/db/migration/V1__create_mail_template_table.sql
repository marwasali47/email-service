CREATE SEQUENCE public.mail_template_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.mail_template_id_seq
    OWNER TO postgres;

CREATE TABLE public.mail_template
(
    id integer NOT NULL DEFAULT nextval('mail_template_id_seq'::regclass),
    name character varying(100) NOT NULL COLLATE pg_catalog."default",
    language_code character varying(10) NOT NULL COLLATE pg_catalog."default",
    subject character varying(100) COLLATE pg_catalog."default",
    body text NOT NULL COLLATE pg_catalog."default",
    CONSTRAINT mail_template_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.mail_template
    OWNER to postgres;
