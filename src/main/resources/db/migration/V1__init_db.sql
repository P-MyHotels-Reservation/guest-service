CREATE TABLE public.guest
(
    id bigserial PRIMARY KEY,
    email varchar(36) NOT NULL,
    phone varchar(36) NOT NULL,
    created_time TIMESTAMP WITH TIME ZONE,
    name varchar(36) NOT NULL
)
