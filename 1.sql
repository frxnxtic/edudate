--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2
-- Dumped by pg_dump version 16.2

-- Started on 2024-04-22 22:48:52

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 222 (class 1259 OID 16438)
-- Name: image; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.image (
    id integer NOT NULL,
    name character varying(100),
    user_id integer NOT NULL,
    date_created timestamp without time zone
);


ALTER TABLE public.image OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16437)
-- Name: image_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.image_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.image_id_seq OWNER TO postgres;

--
-- TOC entry 4875 (class 0 OID 0)
-- Dependencies: 221
-- Name: image_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.image_id_seq OWNED BY public.image.id;


--
-- TOC entry 218 (class 1259 OID 16409)
-- Name: interests; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.interests (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    user_id integer NOT NULL
);


ALTER TABLE public.interests OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16408)
-- Name: interests_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.interests ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.interests_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 220 (class 1259 OID 16415)
-- Name: matching; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.matching (
    id integer NOT NULL,
    user_id integer NOT NULL,
    matched_user_id integer NOT NULL,
    seen boolean DEFAULT false NOT NULL,
    liked boolean DEFAULT false NOT NULL,
    matched_at timestamp without time zone NOT NULL
);


ALTER TABLE public.matching OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16414)
-- Name: matching_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.matching ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.matching_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 216 (class 1259 OID 16400)
-- Name: user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."user" (
    id integer NOT NULL,
    username character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    dob date NOT NULL,
    name character varying(255) NOT NULL,
    surname character varying(255) NOT NULL,
    city character varying(255) NOT NULL,
    likes integer DEFAULT 0 NOT NULL,
    description character varying(255) NOT NULL,
    social_links character varying(255)[] NOT NULL
);


ALTER TABLE public."user" OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 16399)
-- Name: user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public."user" ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 4706 (class 2604 OID 16441)
-- Name: image id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.image ALTER COLUMN id SET DEFAULT nextval('public.image_id_seq'::regclass);


--
-- TOC entry 4869 (class 0 OID 16438)
-- Dependencies: 222
-- Data for Name: image; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.image (id, name, user_id, date_created) FROM stdin;
\.


--
-- TOC entry 4865 (class 0 OID 16409)
-- Dependencies: 218
-- Data for Name: interests; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.interests (id, name, user_id) FROM stdin;
\.


--
-- TOC entry 4867 (class 0 OID 16415)
-- Dependencies: 220
-- Data for Name: matching; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.matching (id, user_id, matched_user_id, seen, liked, matched_at) FROM stdin;
\.


--
-- TOC entry 4863 (class 0 OID 16400)
-- Dependencies: 216
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."user" (id, username, password, email, dob, name, surname, city, likes, description, social_links) FROM stdin;
\.


--
-- TOC entry 4876 (class 0 OID 0)
-- Dependencies: 221
-- Name: image_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.image_id_seq', 1, false);


--
-- TOC entry 4877 (class 0 OID 0)
-- Dependencies: 217
-- Name: interests_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.interests_id_seq', 1, false);


--
-- TOC entry 4878 (class 0 OID 0)
-- Dependencies: 219
-- Name: matching_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.matching_id_seq', 1, false);


--
-- TOC entry 4879 (class 0 OID 0)
-- Dependencies: 215
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_id_seq', 1, false);


--
-- TOC entry 4714 (class 2606 OID 16443)
-- Name: image image_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.image
    ADD CONSTRAINT image_pkey PRIMARY KEY (id);


--
-- TOC entry 4710 (class 2606 OID 16413)
-- Name: interests interests_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.interests
    ADD CONSTRAINT interests_pkey PRIMARY KEY (id);


--
-- TOC entry 4712 (class 2606 OID 16421)
-- Name: matching matching_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.matching
    ADD CONSTRAINT matching_pkey PRIMARY KEY (id);


--
-- TOC entry 4708 (class 2606 OID 16407)
-- Name: user user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- TOC entry 4715 (class 2606 OID 16422)
-- Name: interests fk_interests_user; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.interests
    ADD CONSTRAINT fk_interests_user FOREIGN KEY (user_id) REFERENCES public."user"(id);


--
-- TOC entry 4716 (class 2606 OID 16432)
-- Name: matching fk_matching_matched_user; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.matching
    ADD CONSTRAINT fk_matching_matched_user FOREIGN KEY (matched_user_id) REFERENCES public."user"(id);


--
-- TOC entry 4717 (class 2606 OID 16427)
-- Name: matching fk_matching_user; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.matching
    ADD CONSTRAINT fk_matching_user FOREIGN KEY (user_id) REFERENCES public."user"(id);


--
-- TOC entry 4718 (class 2606 OID 16444)
-- Name: image image_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.image
    ADD CONSTRAINT image_user_id_fkey FOREIGN KEY (user_id) REFERENCES public."user"(id);


-- Completed on 2024-04-22 22:48:52

--
-- PostgreSQL database dump complete
--

