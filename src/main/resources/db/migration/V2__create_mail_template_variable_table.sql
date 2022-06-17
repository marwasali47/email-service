CREATE SEQUENCE public.mail_template_variable_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.mail_template_variable_id_seq
    OWNER TO postgres;

CREATE TABLE public.mail_template_variable
(
    id integer NOT NULL DEFAULT nextval('mail_template_variable_id_seq'::regclass),
    mail_template_id integer NOT NULL,
    name character varying(50) NOT NULL COLLATE pg_catalog."default",
    description character varying(100) COLLATE pg_catalog."default",
    example character varying(100) COLLATE pg_catalog."default",
    CONSTRAINT mail_template_variable_pkey PRIMARY KEY (id),
    CONSTRAINT mail_template_mail_template_var_fk FOREIGN KEY (mail_template_id) REFERENCES mail_template(id) ON DELETE CASCADE
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.mail_template_variable
    OWNER to postgres;
