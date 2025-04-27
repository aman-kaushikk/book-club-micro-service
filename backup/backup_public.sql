--
-- PostgreSQL database dump
--

-- Dumped from database version 17.4
-- Dumped by pg_dump version 17.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
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
-- Name: book; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.book (
    bookmarked integer,
    page_count integer,
    rating double precision,
    created_at timestamp(6) without time zone,
    updated_at timestamp(6) without time zone,
    book_id uuid NOT NULL,
    created_by uuid,
    updated_by uuid,
    author character varying(255) NOT NULL,
    book_status character varying(255) NOT NULL,
    book_url character varying(255) NOT NULL,
    description text,
    title character varying(255) NOT NULL,
    CONSTRAINT book_book_status_check CHECK (((book_status)::text = ANY ((ARRAY['ONGOING'::character varying, 'COMPLETED'::character varying, 'UNKNOWN'::character varying])::text[])))
);


ALTER TABLE public.book OWNER TO postgres;

--
-- Name: book_buy_link; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.book_buy_link (
    book_id_fk uuid NOT NULL,
    url character varying(255) NOT NULL,
    vendor character varying(255) NOT NULL
);


ALTER TABLE public.book_buy_link OWNER TO postgres;

--
-- Name: book_club; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.book_club (
    book_id_fk uuid NOT NULL,
    club_id uuid
);


ALTER TABLE public.book_club OWNER TO postgres;

--
-- Name: book_genre; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.book_genre (
    book_id_fk uuid NOT NULL,
    genre_id character varying(255)
);


ALTER TABLE public.book_genre OWNER TO postgres;

--
-- Name: book_tag; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.book_tag (
    book_id_fk uuid NOT NULL,
    tag_id character varying(255)
);


ALTER TABLE public.book_tag OWNER TO postgres;

--
-- Name: event_log; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.event_log (
    "timestamp" timestamp(6) without time zone,
    event_id uuid NOT NULL,
    error_message character varying(255),
    event_type character varying(255) NOT NULL,
    processing_status character varying(255) NOT NULL,
    routing_key character varying(255) NOT NULL,
    payload oid,
    CONSTRAINT event_log_event_type_check CHECK (((event_type)::text = ANY ((ARRAY['BOOK_CREATE'::character varying, 'BOOK_UPDATE'::character varying])::text[]))),
    CONSTRAINT event_log_processing_status_check CHECK (((processing_status)::text = ANY ((ARRAY['PENDING'::character varying, 'ONGOING'::character varying, 'COMPLETED'::character varying, 'ERROR'::character varying])::text[])))
);


ALTER TABLE public.event_log OWNER TO postgres;

--
-- Name: flyway_schema_history; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.flyway_schema_history (
    installed_rank integer NOT NULL,
    version character varying(50),
    description character varying(200) NOT NULL,
    type character varying(20) NOT NULL,
    script character varying(1000) NOT NULL,
    checksum integer,
    installed_by character varying(100) NOT NULL,
    installed_on timestamp without time zone DEFAULT now() NOT NULL,
    execution_time integer NOT NULL,
    success boolean NOT NULL
);


ALTER TABLE public.flyway_schema_history OWNER TO postgres;

--
-- Name: review; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.review (
    inappropriate boolean,
    star double precision NOT NULL,
    created_at timestamp(6) without time zone,
    book_id_fk uuid NOT NULL,
    id uuid NOT NULL,
    user_id uuid NOT NULL,
    review_description text NOT NULL,
    review_title character varying(255) NOT NULL,
    user_profile_url character varying(255) NOT NULL
);


ALTER TABLE public.review OWNER TO postgres;

--
-- Name: 24577..24615; Type: BLOB METADATA; Schema: -; Owner: postgres
--

SELECT pg_catalog.lo_create('24577');
SELECT pg_catalog.lo_create('24578');
SELECT pg_catalog.lo_create('24579');
SELECT pg_catalog.lo_create('24580');
SELECT pg_catalog.lo_create('24581');
SELECT pg_catalog.lo_create('24582');
SELECT pg_catalog.lo_create('24583');
SELECT pg_catalog.lo_create('24584');
SELECT pg_catalog.lo_create('24585');
SELECT pg_catalog.lo_create('24586');
SELECT pg_catalog.lo_create('24587');
SELECT pg_catalog.lo_create('24588');
SELECT pg_catalog.lo_create('24589');
SELECT pg_catalog.lo_create('24590');
SELECT pg_catalog.lo_create('24591');
SELECT pg_catalog.lo_create('24592');
SELECT pg_catalog.lo_create('24593');
SELECT pg_catalog.lo_create('24594');
SELECT pg_catalog.lo_create('24595');
SELECT pg_catalog.lo_create('24596');
SELECT pg_catalog.lo_create('24597');
SELECT pg_catalog.lo_create('24598');
SELECT pg_catalog.lo_create('24599');
SELECT pg_catalog.lo_create('24600');
SELECT pg_catalog.lo_create('24601');
SELECT pg_catalog.lo_create('24602');
SELECT pg_catalog.lo_create('24603');
SELECT pg_catalog.lo_create('24604');
SELECT pg_catalog.lo_create('24605');
SELECT pg_catalog.lo_create('24606');
SELECT pg_catalog.lo_create('24607');
SELECT pg_catalog.lo_create('24608');
SELECT pg_catalog.lo_create('24609');
SELECT pg_catalog.lo_create('24610');
SELECT pg_catalog.lo_create('24611');
SELECT pg_catalog.lo_create('24612');
SELECT pg_catalog.lo_create('24613');
SELECT pg_catalog.lo_create('24614');
SELECT pg_catalog.lo_create('24615');

ALTER LARGE OBJECT 24577 OWNER TO postgres;
ALTER LARGE OBJECT 24578 OWNER TO postgres;
ALTER LARGE OBJECT 24579 OWNER TO postgres;
ALTER LARGE OBJECT 24580 OWNER TO postgres;
ALTER LARGE OBJECT 24581 OWNER TO postgres;
ALTER LARGE OBJECT 24582 OWNER TO postgres;
ALTER LARGE OBJECT 24583 OWNER TO postgres;
ALTER LARGE OBJECT 24584 OWNER TO postgres;
ALTER LARGE OBJECT 24585 OWNER TO postgres;
ALTER LARGE OBJECT 24586 OWNER TO postgres;
ALTER LARGE OBJECT 24587 OWNER TO postgres;
ALTER LARGE OBJECT 24588 OWNER TO postgres;
ALTER LARGE OBJECT 24589 OWNER TO postgres;
ALTER LARGE OBJECT 24590 OWNER TO postgres;
ALTER LARGE OBJECT 24591 OWNER TO postgres;
ALTER LARGE OBJECT 24592 OWNER TO postgres;
ALTER LARGE OBJECT 24593 OWNER TO postgres;
ALTER LARGE OBJECT 24594 OWNER TO postgres;
ALTER LARGE OBJECT 24595 OWNER TO postgres;
ALTER LARGE OBJECT 24596 OWNER TO postgres;
ALTER LARGE OBJECT 24597 OWNER TO postgres;
ALTER LARGE OBJECT 24598 OWNER TO postgres;
ALTER LARGE OBJECT 24599 OWNER TO postgres;
ALTER LARGE OBJECT 24600 OWNER TO postgres;
ALTER LARGE OBJECT 24601 OWNER TO postgres;
ALTER LARGE OBJECT 24602 OWNER TO postgres;
ALTER LARGE OBJECT 24603 OWNER TO postgres;
ALTER LARGE OBJECT 24604 OWNER TO postgres;
ALTER LARGE OBJECT 24605 OWNER TO postgres;
ALTER LARGE OBJECT 24606 OWNER TO postgres;
ALTER LARGE OBJECT 24607 OWNER TO postgres;
ALTER LARGE OBJECT 24608 OWNER TO postgres;
ALTER LARGE OBJECT 24609 OWNER TO postgres;
ALTER LARGE OBJECT 24610 OWNER TO postgres;
ALTER LARGE OBJECT 24611 OWNER TO postgres;
ALTER LARGE OBJECT 24612 OWNER TO postgres;
ALTER LARGE OBJECT 24613 OWNER TO postgres;
ALTER LARGE OBJECT 24614 OWNER TO postgres;
ALTER LARGE OBJECT 24615 OWNER TO postgres;

--
-- Data for Name: book; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.book (bookmarked, page_count, rating, created_at, updated_at, book_id, created_by, updated_by, author, book_status, book_url, description, title) FROM stdin;
0	256	0	2025-04-27 11:35:19.399273	\N	258bb4c6-4389-4647-ade4-5cd737a4bb3e	f5a72bd0-f16e-4f3c-8d0f-e648788cb42d	\N	Joseph Murphy	UNKNOWN	https://m.media-amazon.com/images/I/41f1KlSQqNL._SX342_SY445_.jpg	This remarkable book by Dr. Joseph Murphy, one of the pioneering voices of affirmative thinking, will unlock for you the truly staggering powers of your subconscious mind. Combining time-honored spiritual wisdom with cutting edge scientific research, Dr. Murphy explains how the subconscious mind influences every single thing that you do and how, by understanding it and learning to control its incredible force, you can improve the quality of your daily life.Everything, from the promotion that you wanted and the raise you think you deserve, to overcoming phobias and bad habits and strengthening interpersonal relationships, the Power of Your Subconscious Mind will open a world of happiness, success, prosperity and peace for you. It will change your life and your world by changing your beliefs.	The Power of Your Subconscious Mind: Original Classic Edition | Premium Paperback
0	224	0	2025-04-27 11:35:19.992486	\N	93bb8a3e-d122-4b81-b0b8-df980fe821f9	2071de64-341d-452c-b5b9-6493c9f34153	\N	Morgan Housel	UNKNOWN	https://m.media-amazon.com/images/I/41n4z8Xv1BL._SY445_SX342_.jpg	When planning for the future we often ask, “What will the economy be doing this time next year?” Or, “What will be different ten years from now?” But forecasting is hard. The important events that will shape the future are inherently unpredictable. Instead, we should be asking a different question: What will be the same ten years from now? What will be the same one hundred years from now? Knowledge of the things that never change is more useful, and more important, than an uncertain prediction of an unknowable future. In Same As Ever, bestselling author Morgan Housel shares 24 short stories about the ways that life, behaviour, and business will always be the same. Armed with this knowledge of the unchanging, you will have a powerful new ability to think about risk, opportunity, and how to navigate the uncertainty of the future. As you see familiar themes repeat again and again in the years ahead, you’ll find yourself nodding and saying, “Yep, same as ever.”	SAME AS EVER: Timeless Lessons on Risk, Opportunity and Living a Good Life (From the author of The Psychology Of Money)
0	288	0	2025-04-27 11:35:20.012485	\N	ad5eed61-5b95-46ed-adb7-5c4c31e3241a	c5c80b24-bfa2-478d-9554-b009e7c19d75	\N	James Clear	UNKNOWN	https://m.media-amazon.com/images/I/419aJfhczCL._SY445_SX342_.jpg	THE PHENOMENAL INTERNATIONAL BESTSELLER: OVER 20 MILLION COPIES SOLD WORLDWIDE\n\nTransform your life with tiny changes in behaviour, starting now.\n\n\n  People think that when you want to change your life, you need to think big. But world-renowned habits expert James Clear has discovered another way. He knows that real change comes from the compound effect of hundreds of small decisions: doing two push-ups a day, waking up five minutes early, or holding a single short phone call.\n\nHe calls them atomic habits.\n\n\n  In this ground-breaking book, Clears reveals exactly how these minuscule changes can grow into such life-altering outcomes. He uncovers a handful of simple life hacks (the forgotten art of Habit Stacking, the unexpected power of the Two Minute Rule, or the trick to entering the Goldilocks Zone), and delves into cutting-edge psychology and neuroscience to explain why they matter. Along the way, he tells inspiring stories of Olympic gold medalists, leading CEOs, and distinguished scientists who have used the science of tiny habits to stay productive, motivated, and happy.\n\nThese small changes will have a revolutionary effect on your career, your relationships, and your life.\n\n  ________________________________\nA NEW YORK TIMES AND SUNDAY TIMES BESTSELLER\n\n'A supremely practical and useful book.' Mark Manson, author of The Subtle Art of Not Giving A F*ck\n\n'James Clear has spent years honing the art and studying the science of habits. This engaging, hands-on book is the guide you need to break bad routines and make good ones.' Adam Grant, author of Originals\n\n'Atomic Habits is a step-by-step manual for changing routines.' Books of the Month, Financial Times\n\n'A special book that will change how you approach your day and live your life.' Ryan Holiday, author of The Obstacle is the Way\n\n\n\n  Number 1 Sunday Times bestseller, August 2023\nNew York Times bestseller, April 2024\n\n  Atomic Habits has sold over 1 million copies in all formats [Nielsen BookScan UK and Circana Bookscan US, April 2024]	Atomic Habits: The life-changing million copy bestseller [Paperback] James Clear
0	256	0	2025-04-27 11:35:26.565364	\N	338263f9-a29a-48ed-9363-fa83fb8aa323	cf393fc9-15ba-4345-bab2-1a309094f9f4	\N	Sarah Adams	UNKNOWN	https://m.media-amazon.com/images/I/41pIQWBL6BL._SY445_SX342_.jpg	Everything is fair in love and war.\nAnd this is war.\n\n  Ryan and June were high school friends with an adorable love-hate relationship. Well-known for their battle of wits, they came dangerously close to kissing on their graduation night. But the moment passed, and so did a chance at love.\n\n  It’s been twelve years and their best friends are getting married. Seeing each other in the same city with the sort of history they have, won’t be easy. Ryan’s charm doesn’t help, either. June plans to rub every delicious detail of her life in Ryan’s face and ignore the rest.\n\n  Sounds like a plan, doesn’t it? But can she ignore her feelings for him? Or his undivided attention to her? \nThe Enemy is an electrifying story of ‘frenemies’ meeting after a decade, with sparks of love and fatal attraction drawing them towards each other.	The Enemy ǀ A bestselling romantic comedy ǀ An enemies turned lover romance by New York Times Bestselling author
0	256	0	2025-04-27 11:35:26.581367	\N	076b03c2-ca53-4953-a884-2fd7db795df3	1e8caa22-3627-4a46-83cc-ccdad1c08ddf	\N	Shravya Bhinder	UNKNOWN	https://m.media-amazon.com/images/I/51Xjv4IzydL._SY445_SX342_.jpg	When in love, you tend to take each other for granted, and sometimes, that can cost you a lifetime of togetherness . . .\n\n  Ronnie knew that his first crush was way out of his league, and yet he pursued and wooed Adira. Shyly and from a distance in the beginning, and more persuasively later. He couldn't believe it when the beautiful Adira actually began to reciprocate, falling in love with him for his simplicity and honesty. \n\n\nSlowly, as they get close and comfortable with each other, life takes on another hue. From truly magical it becomes routine. There are fights and then making-up sessions-a clash of egos and doubts.\n\n\nThings begin to change for the worst.\n\n\nIt is too late.\n\n  Ronnie and Adira will probably never find their forever after . . .	Something I Never Told You [Paperback] Bhinder, Shravya
0	160	0	2025-04-27 11:35:26.635552	\N	22887be2-e672-4a02-b47d-2d2d43186be8	8ca2dc8b-1914-4084-9cb2-c08522b378af	\N	Nick Trenton	UNKNOWN	https://m.media-amazon.com/images/I/41a4WQdhrdL._SY445_SX342_.jpg	Take control of your brain and live free of worry, anxiety, and pressure.\n\n  An anxious brain is never a good thing, whether it’s caused by stress or problems. It traps you in the worries of the future or thoughts of the past, preventing you from making the most of your present. It's time to change that. \n\n  You can take control of your thoughts. \nYou can Rewire Your Anxious Brain!\n\n  Tackle an anxious brain head-on from the inside out. The key is to deal with thoughts that originate from beliefs and can be influenced by environmental and upbringing factors. \nThis book offers you the tools: \n\n  • To transform your anxiety and worry into an actual superpower. \n\n  • To overcome feeling paralyzed and terrified. \n\n  • To manage your expectations and change your beliefs. \n\n  • To take charge and conquer your challenges.	Rewire Your Anxious Brain | Stop Overthinking, Find Calm, And Be Present | Transform Your Anxiety into a Superpower!
0	288	0	2025-04-27 11:35:30.045896	\N	e1c8200e-4f0c-4641-bb96-3e2cdd3b6e0a	c216c1b1-bcfb-4cdc-9342-3abc5ede65ee	\N	UPADHYAY NIDHI	UNKNOWN	https://m.media-amazon.com/images/I/41kRkqIt6aL._SY445_SX342_.jpg	Natasha, Riya, Anjali and Katherine were best friends in college - Each different from the other yet inseparable - until that night. It was the night that began with a bottle of whisky and a game of Ouija but ended with the death of Sania, their unlikeable hostel mate. The friends vowed never to discuss that fateful night, a pact that had kept their friendship and guilt dormant for the last twenty years. But now, someone has begun to mess with them, threatening to reveal the truth that only Sania knew. Is it a hacker playing on their guilt or has sania's ghost really returned to avenge her death? As the faceless enemy closes in on them, the friends come together once again to recount what really happened that night. But when the story is retold by each of them, the pieces don't fit. Because none of them is telling the whole truth. that night is a dark, twisted tale of friendship and betrayal that draws you in and confounds you at every turn.	That Night: Four Friends, Twenty Years, One Haunting Secret—A Twisted Thriller of Guilt, Betrayal, and Revenge
0	576	0	2025-04-27 11:35:30.204823	\N	11cd23ab-8b08-4ab5-ad5e-26b3d4d7d7ff	728e389f-2d51-4a48-af73-5137e5d18798	\N	Cassandra Clare	UNKNOWN	https://m.media-amazon.com/images/I/51VmDdQ3+FL._SY445_SX342_.jpg	In the epic follow-up to the New York Times bestseller Sword Catcher, Kel's life is an illusion, and Lin's great lie is a gamble. But can two outliers save a kingdom? George R. R. Martin praised Sword Catcher as ‘everything I look for in fantasy.' He applauded its 'colorful cast of interesting characters, and a story that kept me reading from the first page to the last, with enough twists and turns to keep me turning the pages.' Leigh Bardugo, No. 1 New York Times bestselling author of Hell Bent, called Sword Catcher ‘fantasy at its finest.' Lose yourself in a vibrant, magical tale of power, intrigue and politics in this spellbinding epic from an internationally bestselling sensation. The royal court is in turmoil following a massacre. Kel, Prince Conor’s body-double, is hunting for traitors. And the notorious Ragpicker King, ruler of the dazzling city of Castellane’s criminal underworld, holds the only clues. But they lead to a dark conspiracy to destroy the royal family – headed by the man due to marry the woman Kel loves. Lin will be judged after claiming to be the legendary Goddess Reborn and wielder of powerful magic - now her people’s charismatic leader is ready to test her powers. Failure will mean exile, and only further dangerous deception can save her now. As the simmering tension in Castellane reaches fever pitch, Prince Conor demands that Lin heal his father’s madness. But the King is tormented by an ancient magic, and Lin cannot deny its allure – any more than she can deny her growing passion for the prince. Lin and Kel must decide who they can trust. Yet any false move will mean death – or worse . . . Praise for Sword Catcher ‘A triumph of a book’ – Holly Black, New York Times bestselling author of Book of Night ‘Vivid and clever’ – Scott Lynch, bestselling author of the Gentleman Bastard sequence	The Ragpicker King
0	432	0	2025-04-27 11:35:30.652531	\N	bbd50959-64d5-4dca-8022-4140ad9be1e3	10371dea-1db8-4c16-8254-7678a96ee0d8	\N	Holly Jackson	UNKNOWN	https://m.media-amazon.com/images/I/51wlOYeH4BL._SY445_SX342_.jpg	A debut YA crime thriller as addictive as Serial and as page-turning as One of Us Is Lying. A cold-case thriller written in the original format of a college report - complete with interviews, logs and murder maps. A deftly-woven cold-case plot with themes of race, privilege, family and justice at its heart. An incredibly commercial, thrilling and darkly humorous debut voice in YA crime fiction from a young author who is One To Watch. The case is closed. Five years ago, schoolgirl Andie Bell was murdered by Sal Singh. The police know he did it. Everyone in town knows he did it. Almost everyone. Having grown up in the small town that was consumed by the crime, Pippa Fitz-Amobi chooses the case as the topic for her final project. But when Pip starts uncovering secrets that someone in town desperately wants to stay hidden, what starts out as a project begins to become Pip?s dangerous reality . . . Perfect for fans of One of Us Is Lying, We Were Liars, Gone Girl, Pretty Little Liars and Riverdale.	A Good Girl'S Guide To Murder
0	352	0	2025-04-27 11:35:34.086669	\N	e05306f4-c5f6-476c-8026-6e81d989d24c	f763886d-a421-4b42-96a8-367d1f5e2eb7	\N	J.K. Rowling	UNKNOWN	https://m.media-amazon.com/images/I/51dOacmuzvL._SY445_SX342_.jpg	Escape to Hogwarts with the unmissable series that has sparked a lifelong reading journey for children and families all over the world. The magic starts here.Harry Potter has never even heard of Hogwarts when the letters start dropping on the doormat at number four, Privet Drive. Addressed in green ink on yellowish parchment with a purple seal, they are swiftly confiscated by his grisly aunt and uncle. Then, on Harry's eleventh birthday, a great beetle-eyed giant of a man called Rubeus Hagrid bursts in with some astonishing news: Harry Potter is a wizard, and he has a place at Hogwarts School of Witchcraft and Wizardry. The magic starts here!These editions of the classic and internationally bestselling Harry Potter series feature thrilling jacket artwork by award-winning illustrator Jonny Duddle. They are the perfect starting point for anyone who's ready to lose themselves in the greatest children's story of all time.	Harry Potter and the Philosopher's Stone
0	336	0	2025-04-27 11:35:34.063674	\N	9b57547c-ac68-49f4-9323-f51b757644cf	3b81a3c3-8977-4772-a926-1823a33160f8	\N	Freida McFadden	UNKNOWN	https://m.media-amazon.com/images/I/51XrLg97CuL._SY445_SX342_.jpg	"Welcome to the family," Nina Winchester says as I shake her elegant, manicured hand. I smile politely, gazing around the marble hallway.\n\n\n Working here is my last chance to start fresh. I can pretend to be whoever I like.\n\n Every day I clean the Winchesters' beautiful house top to bottom. I collect their daughter from school. I cook a delicious meal for the whole family before heading up to eat alone in my tiny room on the top floor.\n\n\n I try to ignore how Nina makes a mess just to watch me clean it up. How she tells strange lies about her own daughter. And how Andrew, her husband, seems more broken every day.\n\n But as I look into Andrew's handsome brown eyes, so full of pain, it's hard not to imagine what it would be like to live Nina's life. The walk-in closet, the fancy car, the perfect husband.\n\n I soon learn that the Winchesters' secrets are far more dangerous than my own...\n\n\n I try on one of Nina's pristine white dresses once. Just to see what it's like. But she soon found out...and by the time I realize my attic bedroom door only locks from the outside, it's far too late.\n\n\n I reassure myself though: the Winchesters don't know who I really am.\n\nThey don't know what I'm capable of...\n\nAn unbelievably twisty read that will have you glued to the pages late into the night. Anyone who loves The Woman in the Window, The Wife Between Us and The Girl on the Train won't be able to put this down!	The Housemaid : An addictive psychological thriller with mind-bending twists
0	384	0	2025-04-27 11:35:36.79654	\N	6678737f-7ba4-49a2-b595-3ea350edab6e	a4c7bd64-a6e3-4a50-8103-c558c498f129	\N	J.K. Rowling	UNKNOWN	https://m.media-amazon.com/images/I/51NUYJDvkgL._SY445_SX342_.jpg	Harry Potter's summer has included the worst birthday ever, doomy warnings from a house-elf called Dobby, and rescue from the Dursleys by his friend Ron Weasley in a magical flying car! Back at Hogwarts School of Witchcraft and Wizardry for his second year, Harry hears strange whispers echo through empty corridors - and then the attacks start. Students are found as through turned to stone . Dobby's sinister predictions seem to be coming true.These new editions of the classic and internationally bestselling, multi-award-winning series feature instantly pick-up-able new jackets by Jonny Duddle, with huge child appeal, to bring Harry Potter to the next generation of readers. It's time to PASS THE MAGIC ON .	Harry Potter and the Chamber of Secrets
0	90	0	2025-04-27 19:21:27.550213	\N	29570c2e-588c-41d8-94fc-3efab7484e8a	8cf7b5f9-b8d3-4b43-a79c-c62c77b5bf2d	\N	Ruskin Bond	UNKNOWN	https://m.media-amazon.com/images/I/41GdkHqODHL._SY445_SX342_.jpg	The Umbrella was like a flower, a great blue flower that had sprung up on the dry brown hillside.'In exchange for her lucky leopard's claw pendant, Binya acquires a beautiful blue umbrella that makes her the envy of everyone in her village, especially Ram Bharosa, the shop-keeper. Ruskin Bond's short and humorous novella, set in the picturesque hills of Garhwal, perfectly captures life in a village, where both heroism and redemption can be found.	THE BLUE UMBRELLA
0	236	0	2025-04-27 11:35:32.952295	2025-04-27 12:02:35.649685	04b69f30-d9e9-42af-9daa-6627e318c338	8eb357d3-291a-4ab3-9fc1-462fbaad10cf	\N	Frances Hodgson Burnett	UNKNOWN	https://m.media-amazon.com/images/I/71yW1vXD2xL._SY445_SX342_.jpg	The Secret Garden by Frances Hodgson Burnett is a timeless classic that has enchanted readers of all ages. It tells the story of young Mary Lennox, a girl who discovers a neglected garden and unlocks its hidden magic. This book is an essential addition to any collection of books, from kids books to classic fiction books that stand the test of time. \n\n\nHere’s Why You Should Read The Secret Garden: \n\n 1. \nA Magical Adventure for All Ages:  Dive into a world of wonder as Mary transforms the secret garden from a desolate space into a magical haven. If you are looking for magic book for kids 2-5 years, story books for kids, or fiction books bestsellers english, this classic novel is perfect for sparking their imagination. \n\n   2. Find Inspiration and Growth:  Much like the wimpy kid books, the alchemist by paulo coelho, or ruskin bond books, The Secret Garden captures the journey of growth and transformation. Mary Lennox’s story is a powerful reminder of resilience, friendship, and the beauty of nurturing both nature and oneself. \n\n   3. Beloved Classic:  This novel stands alongside timeless works such as little women, the great gatsby, and to kill a mockingbird. Though it is written as a children’s book, this is an essential read for fans of novels bestsellers english and anyone seeking the magic of classic literature. \n\n   4. Perfect for Young and Adult Readers Alike:  Whether you’re looking for a story book for kids 7 to 9 years or simply a kids book for bedtime reading, The Secret Garden is an excellent choice. Its themes of adventure, friendship, and discovery make it ideal for all ages, including adult readers. Even fans of story books for kids 12-15 years old, kids books for 3 year old and beyond, or even titles like the wings of fire book will find the enchanting storyline captivating and worth exploring. \n\n   5. Timeless Themes and Magic:  The book delves into themes of transformation and healing, making it a great companion to other inspirational works like the secret, ego is the enemy, and the alchemist paulo coelho. The secret garden itself is a symbol of hope, slightly reminiscent of the transformation seen in metamorphosis franz kafka or even the mysteries of sherlock holmes books set. \n\n\nThe Secret Garden is a cherished tale that resonates with both children and adults. Whether you are seeking an inspiring read for your children, a delightful addition to your story books collection, or an enriching escape into a world of mystery and magic, this novel is one of the best books to read. \n\n\nSo what are you waiting for? Discover the wonder of The Secret Garden—a story that continues to captivate readers across generations, from fantasy books enthusiasts to fans of heartwarming romance novels. Add this classic book to your library and enjoy the magic that only a hidden garden can bring.	The Secret Garden [Premium Paperback] | A Classic Tale of Transformation, Friendship & Magic | Fantasy Fiction Books for Children | Classic Novels for Teenagers | Storybooks for Kids 9-12 in English
0	80	0	2025-04-27 19:21:17.216493	\N	a0cea2e2-8698-438c-a9c9-0d2875450e47	c3acc597-4c40-495a-b8e8-60cdf0ea1bb5	\N	Thibaut Meurisse	UNKNOWN	https://m.media-amazon.com/images/I/41ZeaEn3V4L._SY445_SX342_.jpg	Reclaim your focus in 48 hours or less. Do you keep procrastinating? Do you feel restless and unable to focus on your work? Do you have trouble getting excited about major goals? If so, you might need a dopamine detox. In today’s world where distractions are everywhere, the ability to focus has become more and more difficult to achieve. We are constantly being stimulated, feeling restless, often without knowing why. When the time comes to work, we suddenly find an excess of other things to do. Instead of working toward our goals, we go for a walk, grab a coffee, or check our emails. Everything seems like a great idea—everything except the very things we should be doing. Do you recognize yourself in the above situation? If so, don’t worry. You’re simply overstimulated. Dopamine Detox will help you lower your level of stimulation and regain focus in 48 hours or less,so that you can tackle your key tasks. More specifically, in Dopamine Detox you’ll discover: what dopamine is and how it works; the main benefits of completing a dopamine detox; 3 simple steps to implement a successful detox in the next 48 hours; practical exercises to eliminate distractions and boost your focus; simple tools and techniques to avoid overstimulation and help you stay focused, and much more. Dopamine Detox is your must-read, must-follow guide to help you remove distractions so you can finally work on your goals with ease. If you like easy-to-understand strategies, practical exercises, and no-nonsense teaching, you will love this book.	Dopamine Detox : A Short Guide to Remove Distractions and Get Your Brain to Do Hard Things
0	128	0	2025-04-27 19:21:19.75739	\N	42179851-c6d6-401e-80ab-2e386477da84	acaffbb1-2086-43f6-a746-feae34a2b3cd	\N	Joseph Nguyen	UNKNOWN	https://m.media-amazon.com/images/I/41SUSwxQ14L._SY445_SX342_.jpg	Discover how to conquer anxiety, self-doubt, and self-sabotage without depending on motivation or willpower. 'Don't Believe Everything You Think' uncovers the core of psychological suffering and offers insights to effortlessly shape the life you crave. Learn to detach from negativity, embrace love and joy, escape negative thought cycles, and tap into inner wisdom. The message is clear: anyone can attain peace, love, and fulfillment, irrespective of their history. It's not about rewiring your brain, but expanding your consciousness for lasting transformation. Within this book, delve into the core of emotional suffering and receive insights on effortlessly curating the life you aspire to.	Don't Believe Everything You Think (English)
0	101	0	2025-04-27 19:21:21.607325	\N	d2a1ceb9-842f-46e5-9727-ae2c60afa9bb	1c5cc0ce-cf31-4170-b8bf-04f2901b4945	\N	Franz Kafka	UNKNOWN	https://m.media-amazon.com/images/I/410F1YpxXeL._SY445_SX342_.jpg	Metamorphosis is a timeless novella that plunges readers into the surreal and unsettling world of Gregor Samsa, a young man who wakes up one morning to find himself transformed into a monstrous insect. Written by the acclaimed German-speaking author Franz Kafka, this iconic story explores themes of alienation, identity, and existential angst, captivating readers with its surreal exploration of the human psyche. \n\n\nHere’s why this classic title is a must read: \n\n • \nPsychological Exploration:  Through Gregor's harrowing experiences and inner turmoil after having turned into an insect, Kafka invites readers to confront their deepest fears and anxieties on the nature of identity and the human condition. • Social Commentary:  As Gregor grapples with his grotesque transformation and its repercussions on his family and society, Kafka exposes the absurdity and cruelty of human existence, challenging readers to question societal norms and the pursuit of material success. • Existential Themes:  Kafka's exploration of existential themes such as social and self-isolation, alienation, and the quest for meaning speaks to the timeless struggles of the human condition. • Emotional Depth:  The emotional struggles faced by Gregor and his family are portrayed with intense realism, drawing readers into the pain and despair of a life transformed against one's will. • Universal Relevance:  Kafka's exploration of existential themes such as social and self isolation, alienation, and the quest for meaning speaks to the timeless struggles of the human condition across generations and cultures, offering profound insights into the complexities of modern life. \n\n\nAs the world grapples with existential questions and the search for meaning, Metamorphosis serves as a haunting reminder of the fragility of identity and the inherent absurdity of existence. If you're seeking a thought-provoking read that challenges conventional notions of reality and selfhood, Franz Kafka's Metamorphosis is an essential addition to your reading list.	Metamorphosis by Franz Kafka [Premium Paperback]: An Unsettling Exploration of Identity and Alienation | Existential Literature |Classic Literature| Psychological Fiction| Metaphorical Allegory
0	232	0	2025-04-27 19:21:22.873811	\N	78c900d8-ee54-443b-92ad-54e6860af8e4	47a333cb-dae0-4a1d-89a3-20ec3587a4b6	\N	Shrijeet Shandilya	UNKNOWN	https://m.media-amazon.com/images/I/41trjQwO6UL._SY445_SX342_.jpg	In the electric haze of college life, three friends are bound by laughter, late-night talks and unspoken promises. But when two of them cross the line from friendship into love, everything changes. Betrayal shatters their world, leaving one friend to pick up the pieces while navigating her own complicated feelings. As friendships fracture and love grows tangled, hearts are broken, and choices become irreversible. Caught between the ache of lost friendship and the bittersweet pull of love, Dev must decide if he’s willing to risk everything―again.	Can We Be Strangers Again?: A moving tale of love, loyalty, and the bittersweet beauty of letting go
0	692	0	2025-04-27 19:22:02.430931	\N	dc886c34-0910-42f8-ae3d-72e639769394	717e46ad-852f-4642-a5bd-9782fdf9b0f9	\N	Jaideep Singh	UNKNOWN	https://m.media-amazon.com/images/I/51vPVsiBEZL._SX342_SY445_.jpg	The Complete English Grammar Book, Bilingual Edition for SSC, Bank PO, UPSC & Competitive Exams with Video Content, First Edition	The Complete English Grammar Book, Bilingual Edition for SSC, Bank PO, UPSC & Competitive Exams with Video Content, First Edition
0	248	0	2025-04-27 19:21:28.842801	\N	7c48226c-67e3-402f-b359-96337e270674	b7796038-554b-43fe-a0d0-5e31ad568e5f	\N	Vinod Kumar Shukla	UNKNOWN	https://m.media-amazon.com/images/I/41Qsxm2T-XL._SY445_SX342_.jpg	विनोद कुमार शुक्ल के इस उपन्यास में कोई महान घटना, कोई विराट संघर्ष, कोई युग-सत्य, कोई उद्देश्य या संदेश नहीं है क्योंकि इसमें वह जीवन, जो इस देश की वह ज़िंदगी है जिसे किसी अन्य उपयुक्त शब्द के अभाव में निम्न-मध्यवर्गीय कहा जाता है, इतने खालिस रूप में मौजूद है कि उन्हें किसी पिष्टकथ्य की ज़रूरत नहीं है। यहाँ खलनायक नहीं हैं किंतु मुख्य पात्रों के अस्तित्व की सादगी, उनकी निरीहता, उनके रहने, आने-जाने, जीवन-यापन के वे विरल ब्यौरे हैं जिनसे अपने-आप उस क्रूर प्रतिसंसार का एहसास हो जाता है जिसके कारण इस देश के बहुसंख्य लोगों का जीवन वैसा है जैसा कि है। विनोद कुमार शुक्ल इस जीवन में बहुत गहरे पैठकर दाम्पत्य, परिवार, आस-पड़ोस, काम करने की जगह, स्नेहिल ग़ैर-संबंधियों के साथ रिश्तों के ज़रिए एक इतनी अदम्य आस्था स्थापित करते हैं कि उसके आगे सारी अनुपस्थित मानव-विरोधी ताक़तें कुरूप ही नहीं, खोखली लगने लगती हैं। एक सुखदतम अचंभा यह है कि इस उपन्यास में अपने जल, चट्टान, पर्वत, वन, वृक्ष, पशुओं, पक्षियों, सूर्योदय, सूर्यास्त, चंद्र, हवा, रंग, गंध और ध्वनियों के साथ प्रकृति इतनी उपस्थित है जितनी फणीश्वरनाथ रेणु के गल्प के बाद कभी नहीं रही और जो यह समझते थे कि विनोद कुमार शुक्ल में मानव-स्नेहिलता कितनी भी हो, स्त्री-पुरुष प्रेम से वे परहेज़ करते हैं या क्योंकि वह उनके बूते से बाहर है, उनके लिए तो यह उपन्यास एक सदमा साबित होगा–प्रदर्शनवाद से बचते हुए इसमें उन्होंने ऐंद्रिकता, माँसलता, रति और शृंगार के ऐसे चित्र दिए हैं जो बग़ैर उत्तेजक हुए आत्मा को इस आदिम संबंध के सौंदर्य से समृद्ध कर देते हैं, और वे चस्पाँ किए हुए नहीं हैं बल्कि नितांत स्वाभाविक हैं–उनके बिना यह उपन्यास अधूरा, अविश्वसनीय, वंध्य होता। बल्कि आश्चर्य यह है कि उनकी कविता में यह शारीरिकता नहीं है। \n-विष्णु खरे	Deewar Mein Ek Khidki Rahti Thi । दीवार में एक खिड़की रहती थी [ साहित्य अकादमी पुरस्कार से पुरस्कृत उपन्यास ]
0	96	0	2025-04-27 19:21:48.363046	\N	5e298689-de4e-46c8-b6c9-48afa3b6f060	831c6b13-f987-42de-a743-1d45dc1f1a72	\N	Fyodor Dostoevsky	UNKNOWN	https://m.media-amazon.com/images/I/315sgxvIrGL._SX342_SY445_.jpg	White Nights by Fyodor Dostoevsky is a tender and poignant story of love, longing, and human connection. Set in the enchanting streets of St. Petersburg during the city’s luminous white nights, this short story captures the fleeting beauty of life and the profound emotions of its characters. A solitary dreamer meets a young woman, and over four unforgettable nights, they share their innermost thoughts, hopes, and sorrows. \n\n\nThis White Nights book is an exquisite gem among Fyodor Dostoevsky classics, showcasing his unparalleled ability to explore the depths of the human soul. Perfect for fans of The Meek One and Notes from Underground, this story resonates deeply with readers of all ages, offering timeless reflections on the fragility of happiness and the power of connection. \n\n\nKey Features: \n\n• \nA Literary Masterpiece:  One of Dostoevsky’s most beloved short stories, exploring themes of love, loneliness, and human connection.\n\n  • Timeless and Universal:  Perfect for fans of Crime and Punishment,  The Gambler, anddostoevsky books penguin other Fyodor Dostoevsky books, as well as world’s greatest short stories. \n\n  • For Short Story Lovers:  An essential addition to any collection of short story books or Dostoevsky short stories, ideal for readers seeking the 50 Greatest Short Stories worldwide. \n\n  • A Classic for All Generations:  Appeals to both young adults and seasoned readers, making it one of the best story books for adults and teens alike. \n\n  • Perfect Companion for Classics Enthusiasts:  Pair it with Netochka Nezvanova, The Dream of a Ridiculous Man,  or The Honest Thief for a deeper dive into Dostoevsky’s short fiction. \n\n\nWhy Read White Nights? Whether you’re looking to read more global classics, exploring Fyodor Dostoevsky books for the first time or just revisiting his classics, White Nights offers an unforgettable glimpse into the soul of one of literature’s greatest minds. Perfect for fans of short story books, literature books, and classics books, this is a timeless work that continues to move readers worldwide.	White Nights Fyodor Dostoevsky [Premium Paperback] | A Tale of Love & Loneliness | 50 Greatest Short Stories | Short Stories of Dostoevsky | Classics Books | Literature Books | White Nights Book
0	254	0	2025-04-27 19:21:51.657064	\N	18264a96-4f32-4ff1-bb0d-72591b7f9567	a4a0308c-7345-47cb-a9ba-13360404c891	\N	BYRNE RHONDA	UNKNOWN	https://m.media-amazon.com/images/I/5115jhMmGrL._SY445_SX342_.jpg	Learn the ideal way to approach Life We all fail to realize the purpose of our life. We do live and fulfil most of our dreams, yet feel something missing within us. This feeling of being lost and wandering around often comes without any answers which can get frustrating and draining. This is exactly why we all need some 'magic' in our life to help us out. This is where Rhonda Byrne's 'The Magic' enters the fray. The ideal journey to a better living: Gratitude is the greatest gifts of mankind. And this is exactly what the author, Rhonda Byrne focuses on. In a very subtle yet profound manner, Rhonda Byrne introduces her readers about the power and miracles of gratitude and its rewarding implementation in our lives. There have been occurrences and instances when a single thought has changed the life of many individuals. The author, Rhonda Byrne in her book 'The Magic' aims at influencing and motivating her readers to realize how they can make things happen for themselves. Her book opens answers to various questions as to how one can live and grow from a simple idea. The Magic is a sequel to the author's second book, The Power. Like all other previous books of the author, this one too is a life altering book which takes you on a self-help journey to spiritual enlightenment. About the Author: Having been on her journey of discovering life, author Rhonda Byrne brings together for her readers an extremely motivating and self-confiding journey to fill our lives with joy and contentment. Her journey began with 'The Secret, a book that gained universal recognition and is now available in as many as 47 languages. The author continues to remain on the list of bestselling authors even after five years from the commencement of her journey. Her other book, The Power has also gained recognition as the worldwide seller as she continues to motivate her readers. You can bag this book from A today by following a few easy steps.	Magic [Paperback] Rhonda Byrne
0	176	0	2025-04-27 19:21:57.067358	\N	f449b773-8197-46b9-ab73-360042d1a2de	4b506d10-23b1-4722-a38d-edf1e6cd11e4	\N	Gaurow Gupta	UNKNOWN	https://m.media-amazon.com/images/I/31J169PHapL._SY445_SX342_.jpg	"देर रात तक" गौरव गुप्ता की छोटी-छोटी कहानियों का एक अद्वितीय संग्रह है, जो जीवन के विभिन्न पहलुओं और उसके अनगिनत क्षणों को गहराई से समझने का प्रयास करता है। यह पुस्तक हमें जीवन की जटिलता और उसकी सरलता के बीच के संघर्ष को उजागर करती है। यहाँ कहानियाँ प्रेम की हताशा, आशा, इंतजार, और अंधकार से उजाले की ओर बढ़ने की प्रेरणा देती हैं। गौरव गुप्ता ने अपनी इस कृति में जीवन के हर उस क्षण को पकड़ा है जो हमें सोचने, ठहरने और आगे बढ़ने पर मजबूर करता है। यह पुस्तक उन पाठकों के लिए है जो जीवन के बिखरे टुकड़ों में एक समग्र तस्वीर देखना चाहते हैं, जो अपने अतीत से साक्षात्कार करना चाहते हैं और उन भावनाओं का सामना करना चाहते हैं जिन्हें हमने कभी कहीं पीछे छोड़ दिया था। लेखक के बारे में: गौरव गुप्ता एक युवा कवि और लेखक हैं जिन्होंने अपनी साहित्यिक यात्रा में महत्वपूर्ण मुक़ाम हासिल किया है। उनका प्रसिद्ध कविता संग्रह "तुम्हारे लिए" (2018) मुम्बई लिटरेचर फेस्टिवल में बेस्ट पांडुलिपी अवार्ड से सम्मानित हो चुका है। उनकी रचनाएँ कई प्रतिष्ठित पत्रिकाओं और ऑनलाइन प्लेटफ़ॉर्मों पर प्रकाशित हुई हैं, और वे एक कुशल अनुवादक भी हैं। उन्होंने महमूद दरवेश, निज़ार क़ब्बानी जैसे महत्वपूर्ण कवियों की कविताओं का अनुवाद किया है, जो साहित्यिक पत्रिकाओं में प्रकाशित होते रहे हैं। "देर रात तक" उनकी नवीनतम पुस्तक है, जो पाठकों को एक नए दृष्टिकोण से जीवन को समझने का अवसर प्रदान करती है।	Der Raat Tak - Hindi Short Stories Book - Gaurow Gupta
0	156	0	2025-04-27 19:22:02.341506	\N	aa204165-7b2f-4701-ac29-b95d6f08dc0f	7c8a6ed6-f013-45c5-83ba-fcda2fb2ae51	\N	Gagandeep Singh	UNKNOWN	https://m.media-amazon.com/images/I/51Rb06oyXyL._SX342_SY445_.jpg	ਇਹ ਵਿਆਪਕ Adda247 ਪੰਜਾਬ ਪੁਲਿਸ ਕਾਂਸਟੇਬਲ ਕਰੰਟ ਅਫੇਅਰਜ਼ 2023, 2024 ਅਤੇ 2025 ਕਿਤਾਬ ਖਾਸ ਤੌਰ 'ਤੇ ਉਨ੍ਹਾਂ ਉਮੀਦਵਾਰਾਂ ਲਈ ਤਿਆਰ ਕੀਤੀ ਗਈ ਹੈ ਜੋ ਪੰਜਾਬ ਪੁਲਿਸ ਕਾਂਸਟੇਬਲ 2025 ਪ੍ਰੀਖਿਆ ਦੀ ਤਿਆਰੀ ਕਰ ਰਹੇ ਹਨ। ਇਹ ਨਵੀਨਤਮ ਅਤੇ ਸਭ Current Affairs ਨੂੰ ਕਵਰ ਕਰਦੀ ਹੈ ਅਤੇ ਇਹ ਯਕੀਨੀ ਬਣਾਉਂਦੀ ਹੈ ਕਿ ਉਮੀਦਵਾਰ ਪ੍ਰੀਖਿਆ ਦੇ General Knowledge ਭਾਗ ਲਈ ਚੰਗੀ ਤਰ੍ਹਾਂ ਤਿਆਰ ਹਨ। ਇਹ ਰਾਸ਼ਟਰੀ ਅਤੇ ਅੰਤਰਰਾਸ਼ਟਰੀ ਘਟਨਾਵਾਂ, ਸਰਕਾਰੀ ਯੋਜਨਾਵਾਂ, ਖੇਡ ਅੱਪਡੇਟਾਂ, ਅਤੇ ਹੋਰ ਬਹੁਤ ਕੁਝ ਦੀ ਜਾਣਕਾਰੀ ਪ੍ਰਦਾਨ ਕਰਦੀ ਹੈ। ਇਹ ਉਨ੍ਹਾਂ ਵਿਸ਼ਿਆਂ 'ਤੇ ਕੇਂਦਰਿਤ ਹੈ ਜੋ ਅਕਸਰ ਪੰਜਾਬ ਪੁਲਿਸ ਕਾਂਸਟੇਬਲ ਪ੍ਰੀਖਿਆਵਾਂ ਵਿੱਚ ਪੁੱਛੇ ਜਾਂਦੇ ਹਨ। ਇਸ ਵਿੱਚ ਆਸਾਨ ਰਿਵੀਜ਼ਨ ਲਈ ਸੰਗਠਿਤ ਸਮੱਗਰੀ ਹੈ, ਨਾਲ ਹੀ ਮਾਸਿਕ ਅੱਪਡੇਟਾਂ ਲਈ ਵਿਸ਼ੇਸ਼ ਭਾਗ ਸ਼ਾਮਲ ਹਨ। ਇਸ ਵਿੱਚ Topic Wise One Liners ਸ਼ਾਮਲ ਹਨ ਜੋ ਸਿਲੇਬਸ ਅਤੇ ਪ੍ਰੀਖਿਆ ਪੈਟਰਨ ਦੇ ਅਨੁਸਾਰ ਵੱਧ ਤੋਂ ਵੱਧ ਪ੍ਰਭਾਵਸ਼ਾਲੀਤਾ ਲਈ ਬਣਾਏ ਗਏ ਹਨ। \n\n Product Highlights \n Up-to-Date ਕਰੰਟ ਅਫੇਅਰਜ਼ 2023, 2024 ਅਤੇ 2025 \n Topic Wise One Liners \n ਸੰਗਠਿਤ ਸਮੱਗਰੀ \n ਰਾਸ਼ਟਰੀ ਅਤੇ ਅੰਤਰਰਾਸ਼ਟਰੀ ਘਟਨਾਵਾਂ, ਸਰਕਾਰੀ ਯੋਜਨਾਵਾਂ, ਖੇਡ ਅੱਪਡੇਟਾਂ, ਅਤੇ ਹੋ	Punjab Police Constable Current Affair 2025 Book with Topic wise One Liners (Punjabi & English Edition) By Gagandeep Singh
0	152	0	2025-04-27 19:21:29.577485	\N	66c14057-751c-4ae8-8754-69c7a727a720	e1c208f9-a64e-4375-9363-308eb52e7ed8	\N	Renuka Gavrani	UNKNOWN	https://m.media-amazon.com/images/I/41WNdeN6gsL._SY445_SX342_.jpg	Taylor Swift said once, “The scary news is, you are on your own now. But the cool news is, you are on your own now” The fear of loneliness has been injected into our minds since we were kids. We have learned that the kid who eats, & sits alone, and has no friends is pathetic. In every book/movie, the kid with no friends is always featured as a weak character who needs to be saved. No one wants to be seen as a ‘weirdo’ hence, our dread of being alone. We don’t want people to think of us as unwanted or not fitting in with the cool kids. We don’t want people to think that no one chose us. So what do we do? We start becoming like an ideal version of whom everyone loves. And in the race to achieve people’s acceptance and love, you lose yourself. But enough is enough. The book is divided into two sections. The first section is about transforming loneliness into solitude. And the second section is about using your solitude to turn it into your growth period. If you are ready to transform your perception around loneliness and achieve your goals using your ‘alone time’ then welcome to ‘The Art of Being Alone.’	The Art of Being Alone: Loneliness Was My Cage, Solitude Is My Home (English)
0	172	0	2025-04-27 19:21:33.462966	\N	ea633555-4e42-4b23-ab41-364d485c152d	f81c8990-2b60-4097-b09d-1da8c4fe1105	\N	Paulo Coelho	UNKNOWN	https://m.media-amazon.com/images/I/416JeZoF8tL._SY445_SX342_.jpg	Paulo Coelho's enchanting novel has inspired a devoted following around the world. This story, dazzling in its powerful simplicity and inspiring wisdom, is about an Andalusian shepherd boy named Santiago who travels from his homeland in Spain to the Egyptian desert in search of a treasure buried in the Pyramids. Along the way he meets a Gypsy woman, a man who calls himself	The Alchemist
0	208	0	2025-04-27 19:21:36.83886	\N	f5bcae0a-6d4d-46d5-868b-faaa05987a60	6787ae91-b476-45ce-bcbb-8aba9c24e808	\N	Paulo Coelho	UNKNOWN	https://m.media-amazon.com/images/I/51vjTz9m6OL._SY445_SX342_.jpg	An international bestseller Over 80 million copies sold worldwideA PBS Great American Read Top 100 pickA special 25th anniversary edition of the extraordinary international bestseller, including a new Foreword by Paulo Coelho.Combining magic, mysticism, wisdom and wonder into an inspiring tale of self-discovery, The Alchemist has become a modern classic, selling millions of copies around the world and transforming the lives of countless readers across generations.Paulo Coelho's masterpiece tells the mystical story of Santiago, an Andalusian shepherd boy who yearns to travel in search of a worldly treasure. His quest will lead him to riches far different—and far more satisfying—than he ever imagined. Santiago's journey teaches us about the essential wisdom of listening to our hearts, of recognizing opportunity and learning to read the omens strewn along life's path, and, most importantly, to follow our dreams.	The Alchemist 25th Anniversary: A Fable About Following Your Dream
0	140	0	2025-04-27 19:21:39.822018	\N	a44f50d9-0667-4018-8877-2f6f40ccd6b9	70ffaf09-6a49-48e2-bd61-585e4a7fb5ee	\N	Stephen Hawking	UNKNOWN	https://m.media-amazon.com/images/I/51ZiE+1NSEL._SY445_SX342_.jpg	Seven lectures by the brilliant theoretical physicist have been compiled into this book to try to explain to the common man, the complex problems of mathematics and the question that has been gripped everyone all for centuries, the theory of existence. Undeniably intelligent, witty and childlike in his explanations, the narrator describes every detail about the beginning of the universe. He describes what a theory that can state the initiation of everything would encompass. Ideologies about the universe by Aristotle, Augustine, Hubble, Newton and Einstein have all been briefly introduced to the reader. Black holes and Big Bang has been explained in an unsophisticated manner for anyone to understand. All these events and individual theories may be strung together to create a theory of the origin of everything and the author strongly believes that the origin might not necessarily be from a singular event. He advocates the idea of a multi-dimensional origin with a no-boundary condition to remain true to the theories of modern physics and quantum physics. The book provides a clear view of the world through Stephen’s mind where he respectfully dismisses the belief that the Universe conforms by a supernatural and all-powerful entity. About the Author Stephen Hawking: An English cosmologist, theoretical physicist, author as well as the Director of Research at the Centre for Theoretical Cosmology under the University of Cambridge, Stephen Hawking is a scholar with more than a dozen of honorary degrees. In was in 1963 that Stephen Hawking contracted a rare motor neuron disorder which gave him just two years to live, yet he went to Cambridge to become what he is today.	The Theory Of Everything
0	176	0	2025-04-27 19:21:48.199957	\N	ec17acb1-2e89-450f-844f-4655099fdfdd	1c2c9874-6fb3-4a3b-99f4-9caac8d2931e	\N	Patrick King	UNKNOWN	https://m.media-amazon.com/images/I/51PnBrUWswL._SY445_SX342_.jpg	Speed read people, decipher body language, detect lies, and understand human nature. "Read People Like a Book" isn’t a normal book on body language or facial expressions. Yes, it includes all of those things, as well as new techniques on how to truly detect lies in your everyday life, but this book is more about understanding human psychology and nature. It reminds us that we are who we are because of our experiences and pasts, and this guides our habits and behaviors more than anything else. It helps us to: Take a look inside yourself and others; understand the subtle signals that you are sending out and increase your emotional intelligence; learn the keys to influencing and persuading others; learn how to become a “mind reader” and forge deep connections.	Read People Like a Book: How to Analyze, Understand, and Predict People’s Emotions, Thoughts, Intentions, and Behaviors
0	762	0	2025-04-27 19:21:51.741203	\N	b18b5273-4b97-4feb-9ae7-ea59ad432fb0	a83d3fce-30c4-4575-8fdd-5d22870f543b	\N	Akshat Gupta	UNKNOWN	https://m.media-amazon.com/images/I/51QoHILFYEL._SX342_SY445_.jpg	Prithvi, a twenty-one-year-old, is searching for a mysterious middle-aged Aghori—a Shiva devotee—Om Shastri, who was last traced more than 200 years ago before he was captured and sent to a high-tech facility.\n\n   When a team of specialists drugged and hypnotized the Aghori for interrogation, he claimed to have witnessed all the four yugas (epochs in Hinduism) and to have even\n\n   played a part in the Ramayana and the Mahabharata.\n\n   Who is Om Shastri?\n\n   Why was he captured?\n\n   Board the boat of Om Shastri’s secrets, Prithvi’s pursuit and adventures of other enigmatic immortals of Hindu mythology in this exciting and revealing journey.\n\n   The first battle is lost. What seemed to be the end of all wars was just the beginning of an incredible journey in search of a hidden verse.\n\n   Who are LSD and Parimal? Will Om finally know about\n\n   his past?\n\n   Tighten your seat belts for an adventure in search of words that hold a bigger purpose than even immortality for divinities and demons.\n\n   Who is Devdhwaja: Nagendra or Om? Where are the remaining words hidden? Will Nagendra find them all and complete the verse, or will the immortals be able to stop him?\n\n   Unravel the unexpected mystery of the doomed immortals running out of time.\n\n   Embark on a nail-biting adventure into mythology, epics and science\n\n   with this unputdownable series!	The Hidden Hindu Trilogy: A Mythological Adventure (Set of 3 books ) | Complete Boxset of Epic Tales and Mysteries | Perfect for Fans of Mythology and Spirituality
0	224	0	2025-04-27 19:21:36.530417	\N	a5f2b242-981c-41f1-8017-9624d0d76cd2	d857c3a0-af6a-426f-86db-94ff06c9956a	\N	Paulo Coelho	UNKNOWN	https://m.media-amazon.com/images/I/51KttOnHUVL._SY445_SX342_.jpg	A global phenomenon, the alchemist has been read and loved by over 62 million readers, topping bestseller lists in 74 countries worldwide. Now this magical fable is beautifully repackaged in an edition that lovers of Paulo Coelho will want to treasure forever. Every few decades a book is published that changes the lives of its readers forever. This is such a book  a beautiful parable about learning to listen to your heart, read the omens strewn along lifes path and, above all, follow your dreams. Santiago, a young shepherd living in the hills of Andalucia, feels that there is more to life than his humble home and his flock. One day he finds the courage to follow his dreams into distant lands, each step galvanised by the knowledge that he is following the right path: His own. The people he meets along the way, the things he sees and the wisdom he learns are life-changing. With Paulo Coelho visionary blend of spirituality, magical realism and folklore, the alchemist is a story with the power to inspire nations and change people lives.	The Alchemist Pocket Edition
0	176	0	2025-04-27 19:21:42.599522	\N	24e2e378-3192-4cb1-91b5-db39b21c9911	9ffa57c1-9d1d-46f3-bef1-1c6f5a404cc3	\N	Hawking W. Stephen	UNKNOWN	https://m.media-amazon.com/images/I/41TTfG8iR4L._SY445_SX342_.jpg	A series of lectures by the renowned physicist reviews past ideas from Aristotle to Newton and Einstein's theories of gravity, the Big Bang, and black holes and explores quantum mechanics and the time and space proposition.	The Theory of Everything: The Origin and Fate of the Universe
0	192	0	2025-04-27 19:21:44.995817	\N	f43462ed-22b3-44da-a759-e2353c56659c	dbe17551-702c-44c2-8f8b-251f8c892cdb	\N	Sigmund Freud	UNKNOWN	https://m.media-amazon.com/images/I/31NvpHEUOrL._SX342_SY445_.jpg	Explore the hidden forces that shape our daily actions with Sigmund Freud’s groundbreaking work, Psychopathology of Everyday Life.  This fascinating book delves into the subconscious mind, revealing how slips of the tongue, forgetfulness, and seemingly trivial mistakes expose deeper psychological truths. A cornerstone in psychology books, this work remains an essential read for anyone interested in human psychology, psychoanalysis, and the workings of the unconscious mind. \n\n\nWhy You Should Read This Classic: \n\n• Discover the Subconscious Mind: Freud's insights into everyday behaviors reveal the profound influence of the unconscious, making this one of the best psychology books to read for self-discovery and deeper understanding.\n\n • Timeless Relevance: Whether you're interested in psychology books for beginners or a seasoned reader of philosophical books, Freud’s observations on memory lapses and speech errors remain as insightful today as they were when first published. \n\n • Perfect for Enthusiasts of Human Behavior: If you enjoy books like The Interpretation of Dreams and Totem and Taboo by Freud, or contemporary titles like Surrounded by Psychopaths and The Wisdom of Psychopaths, this book offers a compelling look into the quirks of the human psyche. \n\n • Essential for Students and Professionals: A must-read for students of criminal psychology books, forensic psychology books, and anyone intrigued by human mind psychology books and psychopathology. \n\n • A Key Work in Freud’s Legacy: As one of Freud’s most accessible and engaging books, Psychopathology of Everyday Life is a vital addition to any collection of Sigmund Freud books, offering insights that continue to influence modern psychological thought. \nTake a journey into the hidden recesses of your mind with this classic psychology book, and uncover the fascinating world of unconscious thoughts and behaviors. \n\n\nOrder your copy today and gain a deeper understanding of the human mind! \n\n\nPerfect for readers interested in:  Freud books, dark psychology, books on human psychology, the psychology book, and other timeless classics in human psychology books and philosophical books.	Psychopathology of Everyday Life by Sigmund Freud [Premium Paperback] Introduction to Psychology | Discover the Power of Subconscious Mind | Human Psychology Books | Author of Interpretation of Dreams
0	276	0	2025-04-27 19:21:48.054523	\N	aa04c0f4-afd4-4de6-bbd8-196416d26757	ed8f0ed6-4baa-4fa0-9b66-d6790fff37ec	\N	Patrick King	UNKNOWN	https://m.media-amazon.com/images/I/41UeV5ZY4iL._SY445_SX342_.jpg	Speed read people, decipher body language, detect lies, and understand human nature.Is it possible to analyze people without them saying a word? Yes, it is. Learn how to become a "mind reader" and forge deep connections.How to get inside people's heads without them knowing.Read People Like a Book isn't a normal book on body language of facial expressions. Yes, it includes all of those things, as well as new techniques on how to truly detect lies in your everyday life, but this book is more about understanding human psychology and nature. We are who we are because of our experiences and pasts, and this guides our habits and behaviors more than anything else. Parts of this book read like the most interesting and applicable psychology textbook you've ever read. Take a look inside yourself and others!Understand the subtle signals that you are sending out and increase your emotional intelligence.Patrick King is an internationally bestselling author and social skills coach. His writing draws of a variety of sources, from scientific research, academic experience, coaching, and real life experience.Learn the keys to influencing and persuading others.-What people's limbs can tell us about their emotions.-Why lie detecting isn't so reliable when ignoring context.-Diagnosing personality as a means to understanding motivation.-Deducing the most with the least amount of information.-Exactly the kinds of eye contact to use and avoidFind shortcuts to connect quickly and deeply with strangers.The art of reading and analyzing people is truly the art of understanding human nature. Consider it like a cheat code that will allow you to see through people's actions and words. Decode people's thoughts and intentions, and you can go in any direction you want with them.	Read People Like a Book
0	80	0	2025-04-27 19:21:50.664748	\N	b3cdc6c1-d9b1-4237-8ceb-efcdf7163b1f	ba0e2d0a-fbbd-45c4-adf8-2b8930d41fe9	\N	Maple Press	UNKNOWN	https://m.media-amazon.com/images/I/51y5Gl9UZLL._SY445_SX342_.jpg	This amazing collection of 5 books helps parents to easily introduce their curious kids to the world of Hindu Gods. It comprises some interesting and enchanting stories about Mahabharata, God Krishna, God Hanuman, God Ganesha and Ramayana along with beautiful and colorful illustrations for the kids to effectively relate to the stories and enhance their knowledge.	My First Mythology Tale (Illustrated) (Set of 5 Books) - Mahabharata, Krishna, Hanuman, Ganesha, Ramayana - Story Book for Kids [Paperback] Maple Press
0	262	0	2025-04-27 19:21:53.529037	\N	fac94f1b-3a4d-4649-9e2a-76c4a7a54b49	0c4cdfbf-45ba-4bd2-9f5e-f65e57790ad4	\N	Patrick King	UNKNOWN	https://m.media-amazon.com/images/I/41+4uu7qGgL._SY445_SX342_.jpg	This collection encompasses a wide range of books, each offering unique perspectives, in-depth knowledge, and engaging narratives. It includes works that explore timeless ideas, creative techniques, historical accounts, and thought-provoking themes. The selection features both fiction and non-fiction, covering various topics that inspire curiosity, learning, and personal reflection. Some books provide practical guidance, while others present compelling stories, detailed studies, or visually rich content. The collection also includes works designed for different age groups, making it suitable for diverse reading preferences. Whether focused on knowledge, creativity, or personal growth, these books offer valuable insights and engaging experiences for readers of all backgrounds.	BOTTESH How To Read People Like A Book: Body Language And Personality Analysis Guide | Psychology, Communication, Social Skills
0	192	0	2025-04-27 19:22:07.629512	\N	005ad2cc-166b-4894-b8df-e92928f42d89	b660f847-6b2b-4fae-812a-a04b0a401f20	\N	Wonder House Books	UNKNOWN	https://m.media-amazon.com/images/I/51kDs54+rAL._SX342_SY445_.jpg	A collection of 12 coloring books that include topics like animals, birds, cars, transport spark the imagination and creativity of young children. The vibrant pictures with bold outlines will enable learners to color within the lines and engage them to the joys of coloring. Practicing with bright colors enhance their color awareness and improves their motor skills. \n\n\n\n  Develops motor skills \n Encourages creativity\n Cute and bold illustrations to color\n Develops observation skills\n Reinforces early learning topics	Colouring Books Boxset: Pack of 12 Copy Colour Books For Children
0	48	0	2025-04-27 19:21:56.334757	\N	311e9b2a-bef2-45b6-8a1a-86cce5795306	0be2fd32-7b90-42f3-9396-152c82109774	\N	Wonder House Books	UNKNOWN	https://m.media-amazon.com/images/I/51eB26tiGzL._SX342_SY445_.jpg	Children learn new concepts each day and this boosts their understanding and allows them to think and reason. 201 Brain Booster Activity Book expands the horizons of young learners with over 200 engrossing activities. Gorgeous illustrations will aid in enhancing the critical thinking of young learners and will promote relaxation. \n\n  This activity book helps in:\n\n\n  Understanding of various topics \n  Develops reasoning and cognitive skills \n  Develops a confident learner \n  Learning through engaging activities \n  Staying productive with age-appropriate concept learning	201 Brain Booster Activity Book - Fun Activities and Exercises For Children: Tracing & Pattern, Colors & Shapes, Maze
0	314	0	2025-04-27 19:21:59.948873	\N	5cf8e82a-f3fd-469a-bef3-b9cab2a57738	e14dbabf-4681-439a-8ef6-c6f7d1b35d39	\N	Shiv Khera	UNKNOWN	https://m.media-amazon.com/images/I/41SQZInF5fL._SY445_SX342_.jpg	An easy-to-read, practical, common-sense guide that will take you from ancient wisdom to modern-day thinking, You Can Win helps you establish new goals, develop a new sense of purpose, and generate new ideas about yourself and your future. It guarantees, as the title suggests, a lifetime of success. The book enables you to translate positive thinking into attitude, ambition and action to give you the winning edge.This book will help you to:· Build confidence by mastering the seven steps to positive thinking· Be successful by turning weaknesses into strengths· Gain credibility by doing the right things for the right reasons· Take charge by controlling things instead of letting them control you· Build trust by developing mutual respect with people around you· Accomplish more by removing the barriers to effectiveness.	You Can Win: A Step by Step Tool for Top Achievers
0	292	0	2025-04-27 19:22:03.170715	\N	8fa73ba5-dfa4-49e9-87d8-0543fe13124c	cf07dea9-5aeb-4a19-a6c3-aaea198292ca	\N	Shree Balaji Tambe	UNKNOWN	https://m.media-amazon.com/images/I/511abHMtoXL._SX342_SY445_.jpg	Originally written in Marathi, ?Ayurveda Garbha Sanskar? is a book that serves as a guide to a couple who are looking to start a family, starting out by getting pregnant, giving birth to a healthy child and nurturing the little one. The book comprehensively provides people everything that a person wants to know about conceiving, pregnancy and delivery to nurturing the little one for up to 2 years of age. \n\n\n Not simply a book laden with known-lectures, rather this book can be seen as an elaboration of various ancient Ayurvedic practices that leads to the complete well-being of the mother and child?s physical, spiritual and psychological health. It also advises on the traditional herb mixes, yoga, music and mantras that the new-mothers or the mothers-to-be may find helpful. Besides, this book also charts a nutritious Ayurvedic diet-plan for the couples to detoxify their bodies and be healthy in the right sense of the term. \n\n\n Once a mother conceives, she must be able to nourish and condition the little one in her womb. Likewise, this book also provides a month-by-month nutrition plan that helps in proper nourishment of the baby. Yoga and full-body herbal oil massages during pregnancy are also recommended for the mothers-to-be along with a list of health tonics prescribed in this book. \n\n\n In order to reach out to more people worldwide, this book has been translated in English, and is available in hardcover. \n\n About the Author \n Dr Shri Balaji Tambe is a spiritual guide, Ayurvedic doctor and an expert in Music and Yoga Therapy. He is one of the world's most distinguished Ayurvedic physicians and has an experience of more than four decades. He is the founder of India's largest Ayurvedic Healing Centre known as Atmasantulana Village.	Ayurvedic Garbha Sanskar / Sampurna Ayurveda (Half Million+ Copies Sold) English Book by Dr. Balaji Tambe (Mother & Baby Care During and After Pregnancy)
0	210	0	2025-04-27 19:22:06.032733	\N	e1ad69dc-fbc2-4753-a848-62bf4e6c9409	44756060-bce6-475b-aa5d-a3aa7c216374	\N	Aman Srivastav	UNKNOWN	https://m.media-amazon.com/images/I/51ZRgTtQWVL._SY445_SX342_.jpg	● National and International Events \n\n ● Sports , Entertainment and Environment \n\n ● India and World Economics \n\n ● Emerging Trends \n\n ● Appointments, Awards and Honours \n\n ● Policies and Schemes \n\n ● Static General Knowledge \n\n ● Books and Authors \n\n ● Glimpses of 2022-23	Current Affairs | Year Book 2023 | 15 Months day-to-day Events | LAB | Aman Srivastava
0	768	0	2025-04-27 19:22:13.195261	\N	3537eab8-d657-499e-9369-b7d9dccfd4fa	f2555b24-4cf6-451d-8955-36a3ea857e1b	\N	Educart	UNKNOWN	https://m.media-amazon.com/images/I/41gw5LiEVHL._SX342_SY445_.jpg	Book Structure:\n\n\n\n Time-management charts\n\n\n\n\nHow Good are Educart NTA CUET Entrance Exam Guidebooks\n\n\n\n Strictly based on the CUET UG Syllabus released on March 1st, 2025.\n Follows the official CUET question pattern.\n Detailed answers and explanations for better understanding.\n Includes solved CUET Previous Year Papers\n Designed to improve time management and accuracy.\n Step-by-step solutions for all official and mock test questions.\n Improve familiarity with the CUET exam marking scheme.\n\n\n\n\nWhy choose this Bundle?\n\n\n\n This book is an essential resource for students aiming for high scores in CUET 2025, providing exam-focused practice with detailed solutions.	Educart NTA CUET UG Entrance Exam Book 2025 General Test Guidebook + English and Commerce Past Years & Mock Papers + Commerce Mind Maps (Set of 4 Books)
0	516	0	2025-04-27 19:22:18.934765	\N	b7de9b9b-c5c1-4964-b884-8c5b387f3343	d7920d59-2aed-43d9-a7b0-9c42f047820a	\N	Nawani/Rawat	UNKNOWN	https://m.media-amazon.com/images/I/51mzJ4KbVGL._SY445_SX342_.jpg	विनसर प्रकाशन द्वारा "उत्तराखण्ड इयर बुक" का हिन्दी एवं अंग्रेजी में वर्ष 2003 से नियमित रूप से प्रकाशन किया जा रहा है। वर्ष 2025 का नवीन संस्करण प्रकाशित हो चुका है। इस संस्करण को समसामयिकी 2024, लोकसभा चुनाव-2024, अयोध्या में श्रीराम प्राण प्रतिष्ठा, पेरिस ओलंपिक में उत्तराखण्ड के खिलाड़ी, बजट-2024-2025 पर केन्द्रित किया गया है।	Winsar Uttarakhand Year Book 2025 in "HINDI" A Useful Book for Entrance Exams
0	336	0	2025-04-27 19:22:22.442517	\N	7b675f8b-2169-4847-ab46-e10290c60734	49a15e25-a1d4-4c5b-a8ee-53ed384829dc	\N	Christy Lefteri	UNKNOWN	https://m.media-amazon.com/images/I/41Lng4S1jDL._SY445_SX342_.jpg	This unforgettable novel puts human faces on the Syrian war with the immigrant story of a beekeeper, his wife, and the triumph of spirit when the world becomes unrecognizable.\n\n“A beautifully crafted novel of international significance that has the capacity to have us open our eyes and see.”—Heather Morris, author of The Tattooist of Auschwitz\n\nWINNER OF THE ASPEN WORDS LITERARY PRIZE • FINALIST FOR THE DAYTON LITERARY PEACE PRIZE • NAMED ONE OF THE BEST BOOKS OF THE YEAR BY REAL SIMPLE\n\n\n  Nuri is a beekeeper and Afra, his wife, is an artist. Mornings, Nuri rises early to hear the call to prayer before driving to his hives in the countryside. On weekends, Afra sells her colorful landscape paintings at the open-air market. They live a simple life, rich in family and friends, in the hills of the beautiful Syrian city of Aleppo—until the unthinkable happens. When all they love is destroyed by war, Nuri knows they have no choice except to leave their home. But escaping Syria will be no easy task: Afra has lost her sight, leaving Nuri to navigate her grief as well as a perilous journey through Turkey and Greece toward an uncertain future in Britain.\n\n\n  Nuri is sustained only by the knowledge that waiting for them is his cousin Mustafa, who has started an apiary in Yorkshire and is teaching fellow refugees beekeeping. As Nuri and Afra travel through a broken world, they must confront not only the pain of their own unspeakable loss but dangers that would overwhelm even the bravest souls. Above all, they must make the difficult journey back to each other, a path once so familiar yet rendered foreign by the heartache of displacement.\n\n\n  Moving, intimate, and beautifully written, The Beekeeper of Aleppo is a book for our times: a novel that at once reminds us that the most peaceful and ordinary lives can be utterly upended in unimaginable ways and brings a journey in faraway lands close to home, never to be forgotten.\n\nPraise for The Beekeeper of Aleppo\n\n\n  “This book dips below the deafening headlines, and tells a true story with subtlety and power.”—Esther Freud, author of Mr. Mac and Me\n\n\n  “This compelling tale had me gripped with its compassion, its sensual style, and its onward and lively urge for resolution.”—Daljit Nagra, author of British Museum\n\n\n  “This novel speaks to so much that is happening in the world today. It’s intelligent, thoughtful, and relevant, but very importantly it is accessible. I’m recommending this book to everyone I care about.”—Benjamin Zephaniah, author of Refugee Boy	The Beekeeper of Aleppo: A Novel
0	100	0	2025-04-27 19:22:25.071328	\N	75177cbe-ec7f-47e1-8a1e-9e906c55c6be	b0859e33-c945-4292-ad2f-92b4ba316b7f	\N	Toddlearner	UNKNOWN	https://m.media-amazon.com/images/I/51LdNTX9mYL._SX342_SY445_.jpg	A comprehensive learning Resources to help little one’s learn to read. Includes 4 levels of unique learning resources that includes Level A, Level B, Level C and Level D . Each level is designed carefully keeping in mind the the different learning capabilities of children. Level A includes word practice exercises starting from simple words to slightly complex words Level B includes short phrases practice exercices. Level C includes sentence practice exercises starting from simple sentences to slightly complex ones. Level D includes short stories that helps children practice reading more fluently. The quality of these books is premium hence suitable for childrens usage. Its light weight and will easily fit into the childrens bag.	4 in 1 Early Readers Reading Book for Kids. Includes Reading Practice for Words, Phrases, Sentences and Paragraphs
0	288	0	2025-04-27 19:22:05.344672	\N	a0193d7f-56ec-482e-aa8f-73f2746f1997	9e743da1-f0b5-428b-8feb-db661b822613	\N	Dr. Balaji Tambe	UNKNOWN	https://m.media-amazon.com/images/I/511abHMtoXL._SX342_SY445_.jpg	With a voluminous ambit of suggestions and advice on pregnancy and child care, Ayurvedic Garbha Sanskar: The Art And Science Of Pregnancy provides answers for all your pregnancy concerns. To-be mothers can benefit by this book by Dr. Balaji Tambe. Right from grandma's traditional tips on pregnancy habits and childcare to the extensive medical research findings, the book has essential information to help clarify your pregnancy-related doubts.\n\n\nAyurvedic Garbha Sanskar: The Art And Science Of Pregnancy is a compilation that contains everything one needs to know right through pregnancy, childbirth and up until the child is 2 years old. The book emphasizes on Garbha Sanskara, which is a traditional ayurvedic approach that helps to achieve feminine balance, thus helping in giving birth to a healthy child. It also talks about the diet practices, yoga, and routine body therapies that can help in stimulating the baby in the womb. Strictly adhering to ayurvedic practices, the book also specifies about the type of music, books, and mantras that can prompt the growth of baby in the right way.\n\n\nIn addition to pregnancy care, special chapters on post-natal care for both mother and baby are highlighted in the book. Several tips on natural diet and therapy that help mothers regain their actual body shape after childbirth is another attraction of the book.\n\n\nIt was published in 2011 by Balaji Tambe Foundation and is available in hardcover.	Ayurvedic Garbha Sanskar: The Art And Science Of Pregnancy
0	134	0	2025-04-27 19:22:11.257775	\N	f0d5decb-24eb-4bc4-bca3-98f81c5b4597	270d6eef-cd72-4ce9-9313-9cfc901ebbdd	\N	Manoj Chenthamarakshan	UNKNOWN	https://m.media-amazon.com/images/I/31TyXIE+icL._SY445_SX342_.jpg	Life teaches us lessons every day. The question is, are we aware of these teachings?\n\n\n- Have you come across days where you got help from a stranger?\n\n  - A new idea popped into your mind from no where?\n\n  - A random stranger on the street made you realize something profound?\n\n\n\n\n  Well, it may be small, but so was it for Isaac Newton when the apple fell on his head. The legends have operated life from the point of awareness. This book is a collection of life lessons I got from random places; from India to Germany, life has never surprised me with daily lessons.\n\n\nBut the real question is, why should you read this? How will my insights help you?\n\n\nI am also a human being living amongst you on this planet Earth. These 50 lessons have the capacity to change your life forever.\n\n\nWhat will you gain?\n\n\nYou will become an active student of life; Your life may radically change from the perspective of a person who gets affected by the surroundings to the person who influences the surroundings by your presence.\n\n\nDon't you wish to become that special one? I invite you to this book, "50 Things to realize before it's too late"	50 Things to Realize Before it's Too Late
0	48	0	2025-04-27 19:22:18.646796	\N	0a37121d-f447-428a-9b56-9417b5677239	0b65a322-f16e-4c8a-a87e-c177c6bb7b5a	\N	Wonder House Books	UNKNOWN	https://m.media-amazon.com/images/I/51UkRhZmAOL._SX342_SY445_.jpg	201 English Activity Book - Fun Activities and Grammar Exercises For Children: Alphabet & Words, Rhyming & Opposites\n\n\nLearning English becomes fun with activities. 201 English Activity Book engages budding learners in the ABCs of English. Covering various concepts of English, the learners are sure to sharpen their cognitive skills. Gorgeous illustrations will aid in understanding the parts of speech, sounds and opposites among other concepts.\n\n\n\n\n\nLearning made fun! \n\n\n\n Features fun and interactive activities\n Develops reasoning and cognitive skills\n Develops a confident learner\n Learning through engaging activities\n Staying productive with age-appropriate concept learning	201 English Activity Book - Fun Activities and Grammar Exercises For Children: Alphabet & Words, Rhyming & Opposites
0	400	0	2025-04-27 19:22:23.495458	\N	2ad5a165-b219-4e4e-9deb-790f9d8717f5	45e69b08-d2b5-41b1-87d2-0c70b5e770fa	\N	SciAstra	UNKNOWN	https://m.media-amazon.com/images/I/41YBLynJJ8L._SX342_SY445_.jpg	Master the challenging ISI & CMI entrance examinations with this comprehensive preparation guide that takes you from foundational concepts to advanced problem-solving. This meticulously crafted book features Olympic-level theory explanations complemented by thoroughly solved examples, making complex mathematical concepts accessible and engaging.	ISI & CMI Exam Preparation Book
0	48	0	2025-04-27 19:22:26.304845	\N	ea0fbfdf-b797-4638-b184-2441ee4d78a3	f014bae2-f6d5-4cb6-aff8-2643c1b9c954	\N	Wonder House Books	UNKNOWN	https://m.media-amazon.com/images/I/51HoEAWawTL._SX342_SY445_.jpg	Math is fun if the concepts are learned in an engaging manner. 201 Maths Activity Book does just that and keeps the young mathematicians engrossed with over 200 activities. Covering various concepts of maths, the learners are sure to sharpen their reasoning skills. Gorgeous illustrations will aid in understanding the concepts fully! \n\n  This activity book helps in: \n\n\n  Understanding of various mathematical topics \n  Develops reasoning and cognitive skills \n  Develops a confident learner \n  Learning through engaging activities \n  Staying productive with age-appropriate concept learning	201 Maths Activity Book - Fun Activities and Math Exercises For Children: Knowing Numbers, Addition-Subtraction, Fractions, BODMAS
0	516	0	2025-04-27 19:22:28.661813	\N	e06a9279-0c71-4a36-a122-cfc92daa13b2	66c0ea42-5b32-416f-a243-b38f4f579c07	\N	MTG Editorial Board	UNKNOWN	https://m.media-amazon.com/images/I/51cykmR4-fL._SY445_SX342_.jpg	MTG presents the fully revised & updated “Olympiad Workbook” combo of 7 books for NSO, IMO, IEO, ICSO, IGKO, ISSO and Reasoning to ace the SOF Olympiad exams (2025-26) exam. \n\n\n\n\n\nWho Can Benefit?\n\n\nStudents preparing for – \n\n\nSOF National Science Olympiad (NSO)\n\n\nSOF International Mathematics Olympiads (IMO)\n\n\nSOF International Computer Science Olympiad (ICSO)\n\n\nSOF International General Knowledge Olympiad (IGKO)\n\n\nSOF International English Olympiad (IEO)\n\n\nSOF Social Science Olympiad (ISSO)\n\n\n\n\n\nBOOK DESCRIPTION\n\n\nMTG’s fully revised & updated “Olympiad Workbook” combo of 7 books for NSO, IMO, IEO, ICSO, IGKO, ISSO and Reasoning - the perfect tool to help students prepare for their upcoming SOF Olympiad (2025-26) exam. Packed with an interactive question bank, this workbook is designed to keep students engaged and motivated throughout their learning process. With a focus on developing important knowledge needed for success, this workbook is a must-have for any student looking to excel in their Olympiad exams.\n\n\n\n Chapter-wise quick recap for thorough preparation.\n Chapter-wise MCQs for extensive practice.\n MCQs are divided into general questions and the achiever’s section\n Includes hints and explanations for better understanding.\n\n\n\n\n\n\n\nWhat Sets These Books Apart?\n\n\n\n Solved the 2024 Olympiad exam paper to familiarize with the exam pattern.\n OMR sheets are provided after each chapter to practice filling them out.\n HOTS section to challenge students and enhance critical thinking skills.\n\n\n\n\nWhat Will You Learn?\n\n\n\n Aids in better comprehension and proficiency in concepts.\n Extensive practice will help in becoming ready for the Olympiad.\n Helps students refine their skills and deepen their understanding.\n Boosts students' ability in reasoning, analytical, and problem-solving skills.	MTG Class-4 NSO-IMO-IEO-ICSO-IGKO-ISSO Olympiad and Reasoning Workbook Combo (Set of 7 Books) | MCQs, Previous Years Paper & Achievers Section - SOF Olympiad Books For 2025-26 Exam
0	0	0	2025-04-27 19:31:37.092283	\N	3f1b2175-0661-4294-8f1e-1ebb10b1e0b9	a198532d-a70c-4565-8b85-d5c7f6725260	\N	Dale Carnegie	UNKNOWN	https://m.media-amazon.com/images/I/51wxQRFK+iL._SX342_SY445_.jpg	A collection of world's timeless classics, this box set includes the four greatest bestsellers, which have inspired readers for generations. Packed with wisdom and time-tested principles that are as relevant in modern times as ever before, these inspirational books are a must-read for all those aspiring for personal growth and wealth.	World’s Greatest Books For Personal Growth & Wealth (Set of 4 Books) : Perfect Motivational Gift Set
0	220	0	2025-04-27 19:22:12.587651	\N	a75a579e-b1f8-4cbc-b293-cb3c4c9b0825	c37cb89d-8017-4044-b0c1-4abe21d530ce	\N	KIPS Learning	UNKNOWN	https://m.media-amazon.com/images/I/41u0IoVTnxL._SY445_SX342_.jpg	KIPS, with its vast experience in school computer education, has designed the books for the skill subject Information Technology for classes IX and X. The books are based on the guidelines given by CBSE and Department of Skill Education that cater to the latest curriculum for Subject Code 402. \n\n\n  Contents and Key Features of Book IX \n\n  This book introduces the students to the basics of Employability Skills and covers the usage of LibreOffice-Writer, Calc, and Impress. It also covers the topics Applications of IT-ITeS Industry and Data Entry and Keyboarding Skills. \n\n\n  Assessment Time after every chapter for practice.\n\n Brain Developer incorporates objective and subjective questions.\n\n Activity Zone contains questions for hands-on practice.\n\n Practical Work section contains the suggested practical list. Solved Viva Voce Questions are given to help students prepare for practical exam.\n\n Cross-curricular Projects are given to reinforce the concepts learnt.\n\n Sample Paper based on the latest format is given for practice.\n\n QR code is provided to access the additional resources.	Information Technology Skill Course Book for Class 9 - CBSE Examination - 2025-26
0	250	0	2025-04-27 19:22:15.174415	\N	1638e074-550e-4cfd-9973-6ef37d3bba7e	d6de1e9c-32fb-41ac-8936-8d68995c89e2	\N	Team T Balaji	UNKNOWN	https://m.media-amazon.com/images/I/41umitKv7-L.jpg	Crack the computer GK 2000 is an indispensable guide for aspirants preparing for competitive exams like UPSSSC, UPPCS, UPSI, DSSSB, SSC, Bank, Railway, and UGC NET. This comprehensive book covers a vast range of computer general knowledge topics With 2000 carefully curated questions, this book provides a thorough revision of essential concepts and helps candidates enhance their subject knowledge. The questions are presented in a multiple-choice format, making it easier to practice and assess your understanding. Whether you're targeting state-level or national-level exams, Crack the computer GK 2000 is an invaluable resource that will equip you with the necessary knowledge to excel. Compact and portable, this book is a must-have for dedicated aspirants seeking success in competitive examinations.	Computer GK, Computer General Knowledge Book with Current Topics, For UPSSSC, UPPCS, UPSI, UPSSB, UP Police, SSC, Bank, Railway, DSSSB Exams with 2000 onliner PYQ
0	112	0	2025-04-27 19:22:18.055495	\N	b722fd2c-248e-4cd5-ab9b-4553c3bc3445	20532b99-f1a8-431f-b99a-b54cce57b627	\N	Franz Kafka	UNKNOWN	https://m.media-amazon.com/images/I/515qI4pCbHL._SY445_SX342_.jpg	How significant can a picture of a woman in fur be when you try to hold it dear as the only resemblance left to human life? When Gregor Samsa wakes up in his bed, he is aghast to find himself transformed into a giant bug. He is now to spend the rest of his life in that state. Life to him becomes a struggle to align his lingering humanity and his transformed physicality. Thus begins Metamorphosis. Delving into absurdity of life, the disconnect between the mind and body and limits of sympathy, the book has been cited as one of the seminal works of twentieth-century fiction.	Metamorphosis Complete Edition [Franz Kafka Classics]
0	400	0	2025-04-27 19:22:23.897149	\N	70f11c0c-3c03-4e1b-a5a7-1a659771da3d	fd680b87-980f-4d29-a26a-d03114cf8fbe	\N	LEFTERI CHRISTY	UNKNOWN	https://m.media-amazon.com/images/I/51rWUSDBN7L._SY445_SX342_.jpg	THE MILLION COPY BESTSELLER FROM THE AUTHOR OF THE HUGELY ANTICIPATED SONGBIRDS. INCLUDES A FREE CHAPTER.\n\nA RICHARD & JUDY BOOK CLUB CHOICE 2020 \nA BBC RADIO 2 BOOK CLUB CHOICE 2019\n\n   WINNER OF THE ASPEN WORDS LITERARY PRIZE\n\n   THE READING AGENCY'S PICK FOR NATIONAL READING GROUP DAY\nOVER A MILLION COPIES SOLD WORLDWIDE \n\n'This is a novel of international significance. Courageous, provocative, haunting, it will open our eyes' Heather Morris, author of The Tattooist of Auschwitz\n\n\n  In the midst of war, he found love\n\n  In the midst of darkness, he found courage\n\n  In the midst of tragedy, he found hope\n\n\n  What will you find from his story?\n\n\n  Nuri is a beekeeper; his wife, Afra, an artist. They live a simple life, rich in family and friends, in the beautiful Syrian city of Aleppo - until the unthinkable happens. When all they care for is destroyed by war, they are forced to escape. \n\n\n  As Nuri and Afra travel through a broken world, they must confront not only the pain of their own unspeakable loss, but dangers that would overwhelm the bravest of souls. Above all - and perhaps this is the hardest thing they face - they must journey to find each other again.\n\n\n  Moving, powerful, compassionate and beautifully written, The Beekeeper of Aleppo is a testament to the triumph of the human spirit. Told with deceptive simplicity, it is the kind of book that reminds us of the power of storytelling.\n\n- - - - - - - \n\n\n\n  'This book dips below the deafening headlines, and tells a true story with subtlety and power' Esther Freud\n\n\n  'A beautiful novel, intelligent, thoughtful; and relevant. I'm recommending this book to everyone I care about. So I'm recommending this book to you' Benjamin Zephaniah\n\n\n  'This compelling tale had me gripped with its compassion, its sensual style and its onward and lively urge for resolution' Daljit Nagra\n\n\n  'Powerful, thought-provoking and beautifully crafted' Choice Magazine\n\n\n\n\n  ***DON'T MISS CHRISTY LEFTERI'S MOVING AND CAPTIVATING NEW NOVEL: THE BOOK OF FIRE, OUT NOW!***	T he Beekeeper of Aleppo
0	320	0	2025-04-27 19:22:35.154036	\N	05c31326-7500-40ab-a748-9b3947d0e258	4ada2fa0-0fa3-40e5-b634-784d0631a2b2	\N	BUNGLE	UNKNOWN	https://m.media-amazon.com/images/I/51m8Wm+PN0L._SY445_SX342_.jpg	BUNGLE’s "Einstein Never Used Flashcards" is a revolutionary parenting book that challenges traditional approaches to early childhood education. Instead of relying on structured learning tools like flashcards, this insightful guide explores more natural, engaging methods for fostering cognitive and brain development in young children.\n\n\n Written with parents and educators in mind, this book provides a wealth of tips and strategies to nurture your child’s intellectual abilities in a fun, creative, and supportive environment. The focus is on stimulating your child’s brain development in the early years, when they are most impressionable, through play, exploration, and everyday experiences.\n\n\n Children don’t need rote memorization or rigid educational systems to thrive. Instead, Einstein Never Used Flashcards shows how allowing children to explore their world through meaningful play can boost their problem-solving abilities, creativity, and intellectual curiosity. This book teaches parents how to provide an enriching learning environment that nurtures curiosity, promotes social-emotional development, and encourages lifelong learning.\n\n\n With easy-to-understand guidance and real-life examples, this book is an excellent resource for raising well-rounded, confident, and intelligent children. It provides practical advice on integrating education into daily activities, from simple interactions at home to meaningful outings that spark curiosity and learning. If you’re looking for a fresh, fun approach to parenting and education, Einstein Never Used Flashcards is the ideal guide for nurturing your child’s potential without pressure or stress.\n\n\n This book is perfect for any parent, caregiver, or educator interested in cognitive development, child learning, and fostering a love of education in young children. By focusing on natural methods of learning, BUNGLE’s Einstein Never Used Flashcards will help you support your child’s intellectual growth with joy and ease.	BUNGLE Einstein Never Used Flashcards – Parenting Book for Children, Early Childhood Education Guide, Brain Development, and Cognitive Learning Tips for Raising Intelligent Kids
0	48	0	2025-04-27 19:22:37.855348	\N	d7b710fe-e038-42ea-9940-7149d44e6bdd	26d643cc-c0b4-4a04-a112-e97c2f67d239	\N	Wonder House Books	UNKNOWN	https://m.media-amazon.com/images/I/51im70igA2L._SY445_SX342_.jpg	Flowers Coloring Book for adults\n\n\n\n\n\nColoring is therapeutic, it unwinds and promotes self-expression and brings about calmness. Brimming with abundant designs, myriad motifs and aesthetic patterns, these Mandala Coloring Books will transport you to the land of peace and serenity. Some of the titles in this series include Patterns of the World, Nature Garden and High Street Fashion among others.\n\n\n\n\n\nColorful Journey for a Peaceful Mind!\n\n\n\n Offers beautiful illustrations and fun activities\n Reduces stress and promotes mindfulness\n Whimsical patterns and nature-inspired designs\n Features large, easy-to-follow pattern outlines\n A blend of leisure and artistic fulfillment	Flowers Coloring Book for adults
0	26	0	2025-04-27 19:22:40.599087	\N	65d85414-ab57-4a0d-b5e4-67d73ebb3a97	2588884f-5fa6-4bb9-b0d9-dbf07c99ee64	\N	Penguin Books	UNKNOWN	https://m.media-amazon.com/images/I/51riiPB033L._SX342_SY445_.jpg	Get ready for an out-of-this-world journeyExplore the uniqueness of each planet, from speedy Mercury to windy Neptune and all the planets in between.Each page is packed with short, engaging rhymes that make learning fun and memorable, transforming complex concepts into simple, enjoyable lessons.But it’s not just the rhymes that make this book stand out:\n\n The vibrant, beautiful illustrations bring the planets to life\n Stunning designs capture the imagination\n It sparks curiosity encouraging young minds to explore further\nWhether used as a bedtime book or a classroom resource, this book is sure to inspire a lifelong love of science and exploration.	My Little Book of Planets: A Rhyming Book of Solar System
0	416	0	2025-04-27 19:22:27.587548	\N	951ff018-c757-4607-8aca-e71165447a1a	fc806a1a-1835-4d84-9922-2625c9dfb5f0	\N	Utkarsh Clasess	UNKNOWN	https://m.media-amazon.com/images/I/41g0fZOiiVL._SX342_SY445_.jpg	The "Chaturth Shreni Karmchari Bharti Pariksha 2024 Book" by Utkarsh Classes is a complete guide for candidates preparing for the Rajasthan 4th Grade Staff Exam. This book is based on the latest official syllabus released by the board and is specially designed for Hindi medium students. It covers all major subjects including General Hindi, General English, General Knowledge, General Science, Basic Computer, and Mathematics. The book provides comprehensive coverage of the syllabus with clear explanations and easy-to-understand language, making it ideal for self-study. It also includes 5 full-length practice sets strictly based on the 2024 exam pattern to help aspirants practice effectively and evaluate their preparation. This latest Rajasthan Bharti Book ensures that students stay updated and confident while preparing for the examination.\n\n\n\nComplete syllabus coverage for Rajasthan Chaturth Shreni Karmchari Bharti Pariksha 2024 based on the latest official syllabus.\n\n \n \n   Covers General Hindi, General English, General Knowledge, General Science, Basic Computer, and Mathematics for Rajasthan 4th Grade Staff Exam.\n\n\n \n   Includes 5 practice sets aligned with the latest Rajasthan Bharti Exam 2024 pattern.\n\n\n \n   Simple and clear Hindi medium language, best suited for Hindi-speaking aspirants.\n\n\n \n   Designed for self-study and quick revision to boost exam readiness for the Rajasthan 4th Grade Staff Recruitment 2024.	Utkarsh Classes – Chaturth Shreni Karmchari Bharti Pariksha 2024 Book | Rajasthan 4th Grade Staff Exam Guide | Hindi Medium | Complete Syllabus | Practice Papers | Latest Rajasthan Bharti Book
0	552	0	2025-04-27 19:22:32.540457	\N	d2960bac-1bf3-457c-997f-7229d0498e44	72bc3fa2-9938-432f-a8eb-efa0ddf31566	\N	Anshika Pandey & Abhishek Kaushik	UNKNOWN	https://m.media-amazon.com/images/I/51sNQgDh5wL._SY445_SX342_.jpg	"The Adda247 NTA UGC Paper 1 book is typically designed to help candidates prepare for the National Eligibility Test (NET) conducted by the National Testing Agency (NTA) for aspiring candidates aiming for NET, SET, Ph.D. and Junior Research Fellowship (JRF) in Indian universities and colleges. The book covers all essential topics of Paper 1, which includes general teaching and research aptitude. The book provides a variety of practice questions designed to simulate the actual exam environment. These questions help in building confidence and provide a deep understanding of the pattern of the exam. It typically includes previous years' question papers or sample questions based on the most recent trends, enabling candidates to familiarize themselves with the format and difficulty level. The solutions are provided in a clear and step-by-step manner, often with detailed explanations of key concepts and formulas. This helps candidates in understanding the subject thoroughly. The book is closely aligned with the NTA UGC Paper 1 syllabus, ensuring that candidates can rely on it for covering all necessary topics without feeling overwhelmed by irrelevant content.This book is crucial for anyone preparing for the NTA UGC NET Paper 1 exam as it provides a structured approach to mastering the wide range of topics required for the test.\n\n Product Highlights\n 1500+ Questions \n Previous Year Questions with Detailed Explanations from 2022 to 2025 \n Useful for NET, SET, Ph.D. and JRF \n Based on the latest pattern	NTA UGC NET| SET| JRF|Ph. D. General Paper- I Unit Wise sorted PYQ Book with Detailed Explanation 2022-25 and 1500 + MCQs (English Printed Edition) by Adda247
0	388	0	2025-04-27 19:22:34.954313	\N	0b3a304e-7aff-4221-bd46-bc272c503271	352da0ec-63d2-4ed1-9c9f-d7983ea05f6d	\N	MADAN SIR MIND MAP	UNKNOWN	https://m.media-amazon.com/images/I/51JU49FfzeL._SX342_SY445_.jpg	Useful for all Rajasthan exams.	Rajasthan Ramban 2.0 Madan Sir Mind Map Book Mairathan 2.0 according New syllabus 2025 मैराथन 2.0 राजस्थान रामबाण GK मदन सर MIND MAP नवीनतम 41 ज़िलों के अनुसार with wall Map free
0	1548	0	2025-04-27 19:22:40.435046	\N	e5488701-386f-4f18-99ca-5bbed1b0d927	ef5c4918-b21a-492a-a15c-278a85682c58	\N	Motion Experts	UNKNOWN	https://m.media-amazon.com/images/I/61A2CpATBaL._SX342_SY445_.jpg	NCERT Page-Wise Practice Books – Physics, Chemistry, Biology\n\n  The Smartest Way to Learn NCERT by Motion Education\n\n\n  Master your NCERT textbooks like never before with Motion Education’s NCERT Page-Wise Practice Books—a revolutionary study tool that combines theory and practice side by side to supercharge your learning.\n\n  Perfectly suited for school exams, board exams, and competitive entrances like NEET, this 5-book set (Physics, Chemistry, Biology) is built to help you read smarter, understand deeper, and retain longer.\n\n\n  📘 Dual-Page Learning Layout:\n\n  Each page is strategically split for maximum learning impact:\n\n\n  🔹 Left Page – Contains the authentic NCERT content, exactly as in the textbook. 🔹 Right Page – Features curated practice questions based on that page—MCQs, assertion-reason, true/false, and application-based items.\n\n\n  How It Works:\n\n  📖 Step 1: Read an NCERT topic and immediately attempt the practice questions on the right page to strengthen your understanding.\n\n  📝 Step 2: During revision, focus on solving the questions—if you answer them correctly, you’re on track! If not, revisit the topic for a quick refresher.\n\n  🎯 Step 3: This structured approach ensures efficient and targeted learning, reducing unnecessary revision time while maximizing retention.\n\n\n  🌟 Key Features:\n\n  ✅ 100% NCERT-Aligned Content – Stay on syllabus and exam-focused.\n\n  ✅ Built for Active Recall – Reinforce concepts immediately with practice.\n\n  ✅ Concept Clarity + Application – Progressively builds from basics to high-level thinking.\n\n  ✅ Expertly Designed by Kota’s Top Educators – Crafted with insights from years of academic excellence.\n\n  ✅ Time-Saving Format – Everything you need is in one place—read, solve, revise, repeat.\n\n  ✅ Versatile for All Learners – Ideal for self-study, school support, or coaching.\n\n\n  Why Choose NCERT Page-Wise Practice Book for Physics, Chemistry, Biology?\n\n  ✅ 100% NCERT-Based Practice: Every question aligns perfectly with the NCERT syllabus, covering all key concepts.\n\n  ✅ Structured & Page-Wise Format: Encourages active learning—read the theory and instantly test your understanding.\n\n  ✅ Concept Reinforcement: Questions range from basic recall to high-order thinking, ensuring you truly grasp and apply concepts.\n\n  ✅ Time-Saving & Efficient: No more searching for extra practice material—just solve the questions and move ahead!\n\n\n  Additional Benefits:\n\n  ✔ Step-by-Step Difficulty Progression: Questions start with basic recall and progress to logical reasoning and application-based challenges.\n\n  ✔ Application-Based & Critical Thinking Questions: Enhance problem-solving skills and build conceptual clarity.\n\n  ✔ Boosts Accuracy & Confidence: Regular practice enhances precision and ensures exam-ready performance.\n\n  ✔ Perfect for Self-Study & Coaching Support: Whether you’re studying independently or reinforcing classroom learning, this book is your ideal companion.\n\n\n  🚀 Master Physics, Chemistry, Biology with Motion Education’s NCERT Page-Wise Practice Book – Your Shortcut to Exam Success!	NCERT Page-Wise Practice Books-P,C,B 11th Class
0	375	0	2025-04-27 19:22:42.724836	\N	2624c26d-d2dc-4200-992f-7ac81993448c	fd43d755-4585-473f-a07d-5a1c9df62191	\N	Siddheswar Hadbe	UNKNOWN	https://m.media-amazon.com/images/I/51Qgz65oPpL._SY445_SX342_.jpg	♦ 2017 मध्ये झालेल्या शिक्षक भरती (TAIT) ऑनलाईन प्रश्नपत्रिका स्पष्टीकरणांसह ♦ संपूर्ण अभ्यासक्रमावर आधारित IBPS पॅटर्न Full सराव प्रश्नपत्रिका स्पष्टीकरणांसह ♦ आतापर्यंत झालेल्या PYO प्रश्नपत्रिकांचे घटक वर्गीकृत स्पष्टीकरणांसह विश्लेषण. ♦ टॉपिकवाईज IBPS पॅटर्न सराव प्रश्नपत्रिका स्पष्टीकरणांसह.	TAIT - Shikshak Abhiyogyata va Buddhimatta Chachani By Siddheswar Hadbe - शिक्षक अभियोग्यता व बुद्धिमत्ता चाचणी - प्रश्नपत्रिका संच - The Smart Book - New Edition 2025
0	16	0	2025-04-27 19:22:31.122378	\N	dabc0de3-e615-4a5f-a162-a5315ecfdfd6	d17b9214-48a7-4d39-a09d-8f86d83d8f14	\N	zoqweid	UNKNOWN	https://m.media-amazon.com/images/I/51aJeu1T4eL._SX342_SY445_.jpg	for children, it's important for them to go to preschool education which is easier to correct the gesture and writing method of holding the pen. The interesting activities in the workbook can bring a lot of educational fun for your children. Each preschool copybook page contains interesting, effective and practical exercises so that children can get valuable eye-hand coordination and other handwriting skills.\n It is a good way of early education. Children can develop basic skills while playing our preschool workbook! This series of workbooks contains a wealth of exercises to help children develop the eye-hand coordination skills necessary for learning to write clearly.\n For each transcript, we offer a set of pencil jackets for you. It's convenient for children to reuse this copybook because the drawingwill disappear within 15 minutes of writing. It's includes 1 pen, 1 pen, holder and 10 refills, children can practice for a long time.\n The paper is thick, not easy to smudge and not easy to stain. The pen holder is made of silicone, which is 100% safe for children.\n Building basic skills helps your little ones learn their letters, numbers and pictures on every page. It is a great tool for developing fine motor skills and eye-hand coordination.	ZOQWEID Magic Practice Copybook (4 BOOK + 1 pen + 10 REFILL) Number Tracing Book for Preschoolers with Pen, Magic Calligraphy Copybook Set(19*13 CM)
0	78	0	2025-04-27 19:22:36.319836	\N	36213f0d-b4bd-41e0-9a47-3c07385aa90c	6902efac-d91b-4f6b-ad25-0ca2106e6b2e	\N	Print N Prose Books	UNKNOWN	https://m.media-amazon.com/images/I/51tPGHmBaiL._SY445_SX342_.jpg	Doodle Colouring Book of Joyful Nature for 6 to 7 Years Old Kids - Colour by Number is a delightful activity book filled with fun nature-themed doodles. Designed for young minds, this book provides a colour-by-number guide to make coloring easy and enjoyable. With vibrant illustrations of trees, animals, flowers, and more, kids can explore creativity while improving focus and number recognition. The colour coding serves as a helpful reference, making it a joyful and engaging experience for little learners.	Doodle Colouring Book of Joyful Nature for 6 to 7 Years Old Kids | Colour by Number | Brain Boosting Coloring | Birthday Gifts for Kids, Boys & Girls
0	932	0	2025-04-27 19:22:39.272233	\N	b92bf167-1d4b-4231-b25e-ffb120c9d94e	15981a49-57bf-43f6-a246-0eddebe36faa	\N	Dr. R.S. Aggarwal	UNKNOWN	https://m.media-amazon.com/images/I/512t6uWoTNL._SX342_SY445_.jpg	The revised edition of A Modern Approach to Verbal & Non-Verbal Reasoning by Dr. R.S. Aggarwal is a helpful book for students preparing for competitive exams. This new edition includes more questions from recent exams like SSC, RRB, IBPS, AFCAT, SBI, RBI Grade B, and others, following the latest exam trends.\n\n\nKey Features:\n\n\n✍ 47 instructional videos to help you understand topics better.\n\n  ✍ 1000+ solved examples with step-by-step explanations.\n\n  ✍ 7500+ practice questions to strengthen your skills in Verbal and Non-Verbal reasoning.\n\n  ✍ QR codes in the book to easily access video solutions.\n\n\nWhy This Book is Useful:\n\n\n✍ Based on the latest exam patterns: The questions are arranged in a way that matches the types of questions asked in recent exams.\n\n  ✍ Lots of practice questions: You get many questions to solve, along with detailed solutions to help you learn the correct way to approach each problem.\n\n  ✍ Solved examples with full explanations: Each topic is explained clearly so that you can understand how to solve the questions easily.\n\n  ✍ Video solutions: If you need extra help, you can scan the QR codes and watch video solutions for better understanding.\n\n\nWho Should Use This Book:\n\n\n✍ Banking Exams: IBPS CWE, SBI PO/Clerk, NABARD, IDBI, RRB Officers, etc.\n\n  ✍ SSC Exams: CGL (Tier I & II), CHSL, FCI Grade III, CPO/SI/ASI, Income Tax exams, and more.\n\n  ✍ Insurance Exams: LIC, GIC, and AAO exams.\n\n  ✍ UPSC and State Services Exams: UPSC-CSAT, SCRA, and other state exams.\n\n  ✍ Railway Exams: Grade 'D' and other technical/non-technical exams.\n\n  ✍ Entrance Exams: MAT, CMAT, CET (MBA), SNAP, BBA, BBM, NTSE, CLAT, and hotel management exams.\n\n\nHow This Book Helps:\n\n\nThis book teaches you quicker and smarter ways to solve reasoning questions. With lots of practice questions and video solutions, it prepares you for all kinds of competitive exams and helps you build strong reasoning skills.\n\n\nIt's a great book for students who want to succeed in exams and improve their reasoning abilities.	A Modern Approach to Verbal & Non-Verbal Reasoning (Revised Edition 2025) | 47 Videos | 1000+ Solved Examples | 7500+ Practice Questions | SSC CGL CHSL, IBPS, Bank SBI PO Clerk, Railway CAT, MAT, Police, UPSC Exam Book
0	0	0	2025-04-27 19:31:33.519587	\N	63f6a8e1-d3e3-403b-bf3f-2a2ecc157bf3	fd6663de-c48b-495c-90bb-5d5d1637cb7f	\N	thibaut meurisse	UNKNOWN	https://m.media-amazon.com/images/I/413tVFk--xS._SY445_SX342_.jpg	Reclaim your focus in 48 hours or less. \nDo you keep procrastinating? Do you feel restless and unable to focus on your work? Do you have trouble getting excited about major goals?\n\n\nIf so, you might need a dopamine detox.\n\n\nIn today’s world where distractions are everywhere, the ability to focus has become more and more difficult to achieve. We are constantly being stimulated, feeling restless, often without knowing why.\n\n\n  When the time comes to work, we suddenly find an excess of other things to do. Instead of working toward our goals, we go for a walk, grab a coffee, or check our emails. Everything seems like a great idea—everything except the very things we should be doing.\n\n\nDo you recognize yourself in the above situation?\n\n\nIf so, don’t worry. You’re simply overstimulated.\n\n\nDopamine Detox will help you lower your level of stimulation and regain focus in 48 hours or less,so that you can tackle your key tasks.\n\n\nMore specifically, in Dopamine Detox you’ll discover:\n\n\n\n what dopamine is and how it works.\n the main benefits of completing a dopamine detox.\n 3 simple steps to implement a successful detox in the next 48 hours.\n practical exercises to eliminate distractions and boost your focus.\n simple tools and techniques to avoid overstimulation and help you stay focused, and much more.\n\nDopamine Detox is your must-read, must-follow guide to help you remove distractions so you can finally work on your goals with ease. If you like easy-to-understand strategies, practical exercises, and no-nonsense teaching, you will love this book.	Dopamine Detox : A Short Guide to Remove Distractions and Get Your Brain to Do Hard Things (Productivity Series Book 1)
0	0	0	2025-04-27 19:31:33.494585	\N	fba510f5-6c48-4ddf-b1f6-6ac75e9b2abc	fc20842a-6c41-4d6f-8700-f25b7e6a0afb	\N	Joseph Nguyen	UNKNOWN	https://m.media-amazon.com/images/I/41+2Je27OAL._SY445_SX342_.jpg	Learn how to overcome anxiety, self-doubt and self sabotage without needing to rely on motivation or will power. In this book, you'll discover the root cause of all psychological and emotional suffering and how to achieve freedom of mind to effortlessly create the life you've always wanted to live.	Don't Believe Everything You Think
0	0	0	2025-04-27 19:31:36.110228	\N	25c9a9a9-3de3-4714-b107-177b0d6bc0c0	682033a9-6bd3-4116-8d12-914154149079	\N	Shrijeet Shandilya	UNKNOWN	https://m.media-amazon.com/images/I/417UofJnqKL._SY445_SX342_.jpg	In the electric haze of college life, three friends are bound by laughter, late-night talks and unspoken promises. But when two of them cross the line from friendship into love, everything changes. Betrayal shatters their world, leaving one friend to pick up the pieces while navigating her own complicated feelings. As friendships fracture and love grows tangled, hearts are broken, and choices become irreversible. Caught between the ache of lost friendship and the bittersweet pull of love, Dev must decide if he’s willing to risk everything—again.	Can We be Strangers Again?: A National Bestseller
0	0	0	2025-04-27 19:31:36.656814	\N	3aa40cf6-8bb9-4658-895f-42e3e8e0d88c	f1ef47d7-e8b0-44d0-b911-960d881f6505	\N	Dale Carnegie Dr.Joseph Murphy, George Samuel Clason, Napoleon Hill	UNKNOWN	https://m.media-amazon.com/images/I/41MKfLBBiAL._SY445_SX342_.jpg	HTMl\n,,A box set of the world’s best motivational classics! The world’s most popular books to achieve success and build a fortune (collection of four books) includes the following: 1) How to Win Friends and Influence People 2) The Power of Your Subconscious Mind 3) The Richest Man in Babylon 4) Think and Grow Rich	World's Greatest Books For Personal Growth & Wealth (set of 4 Books)
0	0	0	2025-04-27 19:31:39.37186	\N	34e50bcf-9e0f-488b-aa43-577e4a4874ce	fda9b8f8-c150-4f66-9911-49a261c045fa	\N	Renuka Gavrani	UNKNOWN	https://m.media-amazon.com/images/I/31D8VVlAOnL._SY445_SX342_.jpg	Taylor Swift said once, “The scary news is, you are on your own now. But the cool news is, you are on your own now”\n\n\n  The fear of loneliness was injected into our minds since we were kids. We have learned that the kid who eats, sits, and has no friends is pathetic. In every book or movie, the kid who eats alone and has no friends is always featured as a weak character who needs to be saved. Pick any book or movie, and you will observe a common pattern around loneliness. These people were shown as easy targets or an object of your sympathy.\n\n\n  No one wants to be seen as a ‘weirdo’ hence, our dread of being alone. We don’t want people to think of us as someone who needs to be saved or mocked. Someone who is unwanted or doesn’t fit in with the cool kids. We don’t want people to think that no one chose us. So what do we do? We start becoming like an ideal version of whom everyone loves. And in the race of achieving people’s acceptance and love, you end up losing yourself. \n\n\n  The Art of Being Alone is not just another book. It’s a story of my life. I have been alone for the majority of my life. And I still am. The only difference is, earlier I used to wish for the kind of best friend who will save me and now I enjoy every day with myself, doing things that I always wanted to and using my ‘alone time’ to GROW MYSELF and build my dream life. \n\n\n  And through my book, I want to take you on the journey of being cool with being alone. I have spent most of my life wishing for people to stay or have fun with me while ignoring my soul waiting for me to pay attention to myself. I know it’s tough to be lonely. But I promise, it’s fun to be alone. And it’s even more amazing when you use your alone time to build your dream life, achieve your goals, and fall in love with yourself.\n\n\n  We fear loneliness because we have been manipulated into believing that loneliness is a curse. And in the hope of finding people, we often end up losing ourselves and doing things that we hated in the first place. Why? Why do you hate the idea of being with yourself so much that you are ready to settle for the bare minimum? \n\n\n  But enough is enough. I have divided this book into two sections. The first section is about transforming loneliness into solitude. And the second section is about how you can use your solitude to turn into your growth period. If you are ready to transform your perception around loneliness and if you are all set to achieve your goals using your ‘alone time’ then welcome to ‘The Art of Being Alone’\n\n\n  ‘The Art of Being Alone’ is for every person who wants to learn how to use your alone time in a way that you fall in love with your current life while also feeling excited to work toward your dream life. If you are still victimizing your character because you were left behind, it’s time to turn the table around and build the life you always wished for.	The Art of Being ALONE: Solitude Is My HOME, Loneliness Was My Cage
0	0	0	2025-04-27 19:31:41.727126	\N	9c804133-52c3-46fb-b505-50e011d0bf08	b6af5b30-2fbc-4bc1-bf1f-6152f89f5ef4	\N	Paulo Coelho	UNKNOWN	https://m.media-amazon.com/images/I/517pfctTa9L._SY445_SX342_QL70_ML2_.jpg	Paulo Coelho's enchanting novel has inspired a devoted following around the world. This story, dazzling in its simplicity and wisdom, is about an Andalusian shepherd boy named Santiago who travels from his homeland in Spain to the Egyptian desert in search of treasure buried in the Pyramids. Along the way he meets a Gypsy woman, a man who calls himself king, and an Alchemist, all of whom point Santiago in the direction of his quest. No one knows what the treasure is, or if Santiago will be able to surmount the obstacles along the way But what starts out as a journey to find worldly goods turns into a meditation on the treasures found within. Lush, evocative, and deeply humane, the story of Santiago is art eternal testament to the transforming power of our dreams and the importance of listening to our hearts.	The Alchemist: A Fable About Following Your Dream
0	0	0	2025-04-27 19:31:44.807192	\N	000cb8b6-c053-446c-b907-8cb1b30058e3	b4324a07-9087-485c-b912-4fa0dbbf0bd0	\N	Stephen Hawking	UNKNOWN	https://m.media-amazon.com/images/I/516P5ANw20L._SY445_SX342_.jpg	Stephen Hawking is widely believed to be one of the world’s greatest minds: a brilliant theoretical physicist whose work helped to reconfigure models of the universe and to redefine what’s in it. Imagine sitting in a room listening to Hawking discuss these achievements and place them in historical context. It would be like hearing Christopher Columbus on the New World.\n\n\n Hawking presents a series of seven lectures—covering everything from big bang to black holes to string theory—that capture not only the brilliance of Hawking’s mind but his characteristic wit as well. Of his research on black holes, which absorbed him for more than a decade, he says, “It might seem a bit like looking for a black cat in a coal cellar.”\n\n\n Hawking begins with a history of ideas about the universe, from Aristotle’s determination that the Earth is round to Hubble’s discovery, over 2000 years later, that the universe is expanding. Using that as a launching pad, he explores the reaches of modern physics, including theories on the origin of the universe (e.g., the big bang), the nature of black holes, and spacetime.	The Theory Of Everything: The Origin and Fate of the Universe
0	0	0	2025-04-27 19:31:52.940226	\N	89da26e0-f2e3-4121-977b-298ffafdf3f0	38cc8dcb-7ac7-44ee-8d1c-6a707eda6ee3	\N	Shiv Khera	UNKNOWN	https://m.media-amazon.com/images/I/51cpjZ44gkL._SY445_SX342_QL70_ML2_.jpg	A practical, common-sense guide that will lead you from ancient wisdom to modern-day thinking, You Can Win will help you to establish new goals, develop a renewed sense of purpose and generate fresh and exciting ideas about yourself and your future. \n\n\nShiv Khera guarantees, as the title suggests, a lifetime of success. The audiobook enables you to translate positive thinking into attitude, ambition and action, all of which combine to give you the winning edge. \n\n\nThis audiobook will help you to: \n\n\n\n\n\n\n Build confidence by mastering the seven steps to positive thinking\n Be successful by turning weaknesses into strengths \n Gain credibility by doing the right things for the right reasons \n Take charge by controlling things instead of letting them control you \n Build trust by developing mutual respect with the people around you \n Accomplish more by removing the barriers to effectiveness	You Can Win
0	0	0	2025-04-27 19:31:55.002328	\N	3449f19c-efdb-4995-8e05-416b6bcc9756	46da7784-26d8-4185-98ed-cc8ad813e5e5	\N	Saumya Jain	UNKNOWN	https://m.media-amazon.com/images/I/41TgD24jAoL._SY445_SX342_.jpg	A heartwarming tale begins as a homebound girl steps out of her comfort zone to attend a friend’s wedding in Dehradun. There, she crosses paths with a reserved and enigmatic man, and the story unfolds as they navigate their growing connection and face life’s unexpected challenges together.	Falling for you, unexpectedly
0	0	0	2025-04-27 19:32:01.390026	\N	21919dfd-a2f6-433f-b0b6-7d6b07b2baeb	82c72a91-5596-4af5-9427-019496145552	\N	Christy Lefteri	UNKNOWN	https://m.media-amazon.com/images/I/51BJP9Y8j5L._SY445_SX342_.jpg	THE UNMISSABLE MILLION COPY BESTSELLER \n\nA RICHARD & JUDY BOOK CLUB CHOICE 2020 \nA BBC RADIO 2 BOOK CLUB CHOICE 2019\nWINNER OF THE ASPEN WORDS LITERARY PRIZE\nTHE READING AGENCY'S PICK FOR NATIONAL READING GROUP DAY\nOVER A MILLION COPIES SOLD WORLDWIDE\n\n'This is a novel of international significance. Courageous, provocative, haunting, it will open our eyes' Heather Morris, author of The Tattooist of Auschwitz\n\n\n In the midst of war, he found love\n\n In the midst of darkness, he found courage\n\n In the midst of tragedy, he found hope\n\n\n What will you find from his story?\n\n\n Nuri is a beekeeper; his wife, Afra, an artist. They live a simple life, rich in family and friends, in the beautiful Syrian city of Aleppo - until the unthinkable happens. When all they care for is destroyed by war, they are forced to escape. \n\n\n As Nuri and Afra travel through a broken world, they must confront not only the pain of their own unspeakable loss, but dangers that would overwhelm the bravest of souls. Above all - and perhaps this is the hardest thing they face - they must journey to find each other again.\n\n\n Moving, powerful, compassionate and beautifully written, The Beekeeper of Aleppo is a testament to the triumph of the human spirit. Told with deceptive simplicity, it is the kind of book that reminds us of the power of storytelling.\n\n- - - - - - \n\n'This book dips below the deafening headlines, and tells a true story with subtlety and power' Esther Freud\n\n\n 'A beautiful novel, intelligent, thoughtful; and relevant. I'm recommending this book to everyone I care about. So I'm recommending this book to you' Benjamin Zephaniah\n\n\n 'Powerful, thought-provoking and beautifully crafted' Choice Magazine\n\n\n\n***DON'T MISS CHRISTY LEFTERI'S MOVING AND CAPTIVATING NEW NOVEL: THE BOOK OF FIRE, OUT NOW!***	The Beekeeper of Aleppo: The Sunday Times Bestseller and Richard & Judy Book Club Pick
0	0	0	2025-04-27 19:32:03.79229	\N	389a464d-05b4-405f-baf4-e71be7aa9ff8	6db26ca9-eba7-4c23-b792-0e31e6886f4a	\N	IIBF	UNKNOWN	https://m.media-amazon.com/images/I/514Yu27-L1L._SY445_SX342_.jpg	Master the fundamentals of banking and finance with this comprehensive JAIIB 2025 exam preparation bundle from Macmillan Education and IIBF. This essential collection includes four meticulously updated books covering Principles and Practices of Banking, Accounting & Financial Management for Bankers, Retail Banking & Wealth Management, and Indian Economy & Financial System. Perfect for aspiring banking professionals, these books offer in-depth coverage of core banking concepts, financial regulations, and modern banking practices. The content is specifically tailored to align with the latest JAIIB examination pattern, featuring clear explanations, practical examples, and detailed analyses. Each book in this bundle is designed to build strong theoretical foundations while providing practical insights into the banking sector. Whether you're a banking professional preparing for JAIIB certification or seeking to enhance your banking knowledge, this authoritative set serves as your complete study companion.	Macmillan - JAIIB 2025 Exam Edition - PPB + Accounting & FM + Retail-Wealth Mgmt + Indian Economy/Financial System - by IIBF - Combo Set of 4 Books Bundle - Revised for 2025 Exams
0	0	0	2025-04-27 19:31:46.124004	\N	b2370ab2-9864-4c8e-872f-f6d832dfa773	14c58e4f-8cd2-4d9c-a840-407b9cbebe31	\N	Stephen W. Hawking	UNKNOWN	https://m.media-amazon.com/images/I/61Yw5tzf1OL._SX342_SY445_.jpg	Based on a series of lectures given at Cambridge University, Professor Hawking's work introduced "the history of ideas about the universe" as well as today's most important scientific theories about time, space, and the cosmos in a clear, easy-to-understand way. "The Theory of Everything" presents the most complex theories, both past and present, of physics; yet it remains clear and accessible. It will enlighten readers and expose them to the rich history of scientific thought and the comlixities of the universe in which we live.	The Theory of Everything: The Origin And Fate of the Universe
0	0	0	2025-04-27 19:31:48.088117	\N	dac8c0bc-d556-4a66-8990-e8cefa205ca2	f5e3840b-2698-4a6d-aec1-fcd98b6761ec	\N	Patrick King	UNKNOWN	https://m.media-amazon.com/images/I/41F-TF5mDzL._SY445_SX342_QL70_ML2_.jpg	Speed-read people, decipher body language, detect lies, and understand human nature. \n\n\nIs it possible to analyze people without them saying a word? Yes, it is. Learn how to become a “mind reader” and forge deep connections. \n\n\nHow to get inside people’s heads without them knowing. \n\n\nRead People like a Book isn’t a normal book on body language or facial expressions. Yes, it includes all of those things, as well as new techniques on how to truly detect lies in your everyday life, but this book is more about understanding human psychology and nature. \n\n\nWe are who we are because of our experiences and pasts, and this guides our habits and behaviors more than anything else. Parts of this book are like the most interesting and applicable psychology textbook you’ve ever used. Take a look inside yourself and others! \n\n\nUnderstand the subtle signals that you are sending out and increase your emotional intelligence. \n\n\nPatrick King is an internationally best-selling author and social skills coach. His writing draws of a variety of sources, from scientific research, academic experience, coaching, and real life experience. \n\n\nLearn the keys to influencing and persuading others. \n\n\n\n\n\n\n What people’s limbs can tell us about their emotions\n Why lie detecting isn’t so reliable when ignoring context\n Diagnosing personality as a means to understanding motivation\n Deducing the most with the least amount of information\n Exactly the kinds of eye contact to use and avoid \n\nFind shortcuts to connect quickly and deeply with strangers. \n\n\nThe art of reading and analyzing people is truly the art of understanding human nature. Consider it like a cheat code that will allow you to see through people’s actions and words. \n\n\nDecode people’s thoughts and intentions, and you can go in any direction you want with them.	Read People like a Book: How to Analyze, Understand, and Predict People’s Emotions, Thoughts, Intentions, and Behaviors: How to Be More Likable and Charismatic, Book 9
0	0	0	2025-04-27 19:31:53.317594	\N	2625f1d6-7834-4efb-a6ec-f54f0ddbd1af	ad8b997d-bbd9-4c52-9184-e65977724cdc	\N	Shreeguru Dr. Balaji Tambe	UNKNOWN	https://m.media-amazon.com/images/I/51sB5OkDzhL._SX342_SY445_.jpg	What's in the book? \n\n Everything you need to know from the time you plan to have your baby, through pregnancy and delivery, and up until your child is two years old. \n\n The ancient scriptures and Ayurveda prescribe a particular daily practice for the pregnant woman. Along with the prescription of diet, Yoga, and routine body care, instructions are also given for reading material, subjects of discussion, and music and mantras to be listened to. Garbha Sanskar is an ayurvedic way to achieve feminine balance. The book will help the woman to achieve a perfect balance to conceive and give birth to a healthy, beautiful and intellectual child. It is a complete guide on Nutrition, Yoga, Spiritual life and Wellness before pregnancy and till the child is 2-3 yrs. \n\n Traditional Indian Sanskar and therapies \n\n Planning for a healthy child and preparing for pregnancy \n\n Ayurvedic concepts and medical formulations for pregnancy \n\n The importance of Healing Music \n\n Yoga during and after Pregnancy \n\n Diet before, during and after pregnancy \n\n Daily practices that can help your baby in the womb \n\n Complete child care \n\n Natural diet and therapy that helps you regain your shape after delivery.	Ayurvedic Garbha Sanskar: The Art and Science of Pregnancy
0	0	0	2025-04-27 19:31:55.615881	\N	e98d7b3d-93bd-48cc-8925-76a80a6a0577	836f6eaf-d97d-48dd-aa4f-8acd89db4256	\N	Manoj Chenthamarakshan	UNKNOWN	https://m.media-amazon.com/images/I/41+hQSty+tL._SY445_SX342_.jpg	Life teaches us lessons every day. The question is, are we aware of these teachings?\n\n\n\n Have you come across days where you got help from a stranger?\n A new idea popped into your mind from no where?\n A random stranger on the street made you realize something profound?\nWell, it may be small, but so was it for Isaac Newton when the apple fell on his head. The legends have operated life from the point of awareness. This book is a collection of life lessons I got from random places; from India to Germany, life has never surprised me with daily lessons.\n\nWhat will you gain? You will become an active student of life; Your life may radically change from the perspective of a person who gets affected by the surroundings to the person who influences the surroundings by your presence.\nDon't you wish to become that special one? I invite you to this book, "50 Things to realize before it's too late"	50 Things to Realize Before It's too late (Thought Provoking Series Book 1)
0	0	0	2025-04-27 19:32:06.81702	\N	c7db597d-47d3-45b0-b95f-65c975055a51	a869ff2e-8fc8-4863-8962-5b33fda6844a	\N	NCERT	UNKNOWN	https://m.media-amazon.com/images/I/51JY6UddVES._SX342_SY445_.jpg	Ncert book, 1. Hornbill textbook in English, 1. Snapshots supplementary reader in English for Class-XI (core course),[combo Pack], NCERT book, 1. Hornbill textbook in English, 1. Snapshots supplementary reader in English for Class-XI (core course),[combo Pack].	NCERT BOOK , Hornbill Textbook in English ,Snapshots Supplementary Reader in English for Class-XI 2025-26 Edition
0	0	0	2025-04-27 19:31:47.407145	\N	e9475154-0586-4861-a186-5e009b676008	6f9c28fb-030c-4f69-943d-aa7fc15ff772	\N	Patrick King	UNKNOWN	https://m.media-amazon.com/images/I/41uhiQlhomL._SY445_SX342_.jpg	Speed read people, decipher body language, detect lies, and understand human nature.\n\n Is it possible to analyze people without them saying a word? Yes, it is. Learn how to become a “mind reader” and forge deep connections.\nHow to get inside people’s heads without them knowing.\nRead People Like a Book isn’t a normal book on body language of facial expressions. Yes, it includes all of those things, as well as new techniques on how to truly detect lies in your everyday life, but this book is more about understanding human psychology and nature. We are who we are because of our experiences and pasts, and this guides our habits and behaviors more than anything else. Parts of this book read like the most interesting and applicable psychology textbook you’ve ever read. Take a look inside yourself and others!\nUnderstand the subtle signals that you are sending out and increase your emotional intelligence.\n\n Patrick King is an internationally bestselling author and social skills coach. His writing draws of a variety of sources, from scientific research, academic experience, coaching, and real life experience.\nLearn the keys to influencing and persuading others.\n\n •What people’s limbs can tell us about their emotions.•Why lie detecting isn’t so reliable when ignoring context.•Diagnosing personality as a means to understanding motivation.•Deducing the most with the least amount of information.•Exactly the kinds of eye contact to use and avoid\nFind shortcuts to connect quickly and deeply with strangers.\n\n The art of reading and analyzing people is truly the art of understanding human nature. Consider it like a cheat code that will allow you to see through people’s actions and words.\nDecode people’s thoughts and intentions, and you can go in any direction you want with them.	Read People Like a Book: How to Analyze, Understand, and Predict People’s Emotions, Thoughts, Intentions, and Behaviors (How to be More Likable and Charismatic Book 1)
0	0	0	2025-04-27 19:31:52.844445	\N	839d07f6-6fea-4c04-80c8-818b636f3d24	24f86584-6406-48ba-8a90-973e06c03e7b	\N	Shiv Khera	UNKNOWN	https://m.media-amazon.com/images/I/51DetgCVVYL._SY445_SX342_.jpg	Empower yourself and grow exponentially.\n\n A practical, common sense guide that will help you:\n\n · Build confidence by mastering the seven steps to positive thinking\n\n · Be successful by turning weaknesses into strengths\n\n · Gain credibility by doing the right things for the right reasons\n\n · Take charge by controlling things instead of letting them control you\n\n · Build trust by developing mutual respect with people around you\n\n · Accomplish more by removing the barriers to effectiveness	You Can Win: A Step-by-Step Tool for Top Achievers
0	0	0	2025-04-27 19:31:57.210914	\N	b68b462f-0716-4d4d-b65d-15f9f74c5457	b0c57f3c-ce69-4fd7-8c5d-56a76bfa7bd0	\N	Rudyard Kipling	UNKNOWN	https://m.media-amazon.com/images/I/41-e-r2OTUL._SY445_SX342_.jpg	Raised by wolves. Protected by a bear. Hunted by a tiger.\n\n\n The Jungle Book by Rudyard Kipling is a vibrant collection of adventure stories set deep in the Indian jungle, where a boy named Mowgli must learn the laws of the wild. Guided by the wise panther Bagheera and the lovable bear Baloo, he faces danger, discovers friendship, and confronts the fierce tiger Shere Khan.\n\n\n This unforgettable classic is a celebration of nature, identity, and courage. Perfect for children and adults alike, it has inspired generations of readers and continues to be adapted into movies, shows, and art.\n\n\n 💬 "Timeless, thrilling, and rich with meaning—this is more than a children's story. It's a survival tale with a heart."\n\n\n 🐾 Why Families and Readers Love It:\n\n One of the most beloved animal adventure tales in literary history\n\n\n A perfect read for fans of Tarzan, Black Beauty, and Peter Pan\n\n\n Ideal for gift editions, bedtime reading, and classroom discussions\n\n\n 📣 Run with Wolves. Learn the Jungle Law.\n\n Buy The Jungle Book today and explore the story that captured the wild spirit of the world.	The Jungle Book: A Classic Tale of Adventure, Wilderness, and Coming of Age
0	0	0	2025-04-27 19:32:04.463047	\N	13b9ec6e-838d-458d-949d-b85985b225c5	1223f3ff-5a61-40c5-812f-f126eb64c517	\N	Atiqa Ikhalq	UNKNOWN	https://m.media-amazon.com/images/I/51yeV+XOq0L._SY445_SX342_.jpg	Dive into endless laughter with **"A Big Book of 1200+ Knock Knock Jokes"**—the ultimate collection of family-friendly humor for kids, teens, and adults! Perfect for parties, road trips, classrooms, or cozy nights at home, this joke book is packed with clever wordplay, puns, and hilarious surprises across 10+ themed categories:	A big book of 1200+ knock knock jokes
0	0	0	2025-04-27 19:32:06.389965	\N	afd6461e-6930-456a-844a-b25092064d35	e1b89588-acc0-49be-b4d6-6d47257d53d3	\N	Gisela McPean	UNKNOWN	https://m.media-amazon.com/images/I/41ENNu-lkzL._SY445_SX342_.jpg	நீ அதிகமாக நேசித்ததை இழந்துவிட்டால் என்ன ஆகும்?வேதனையை தாண்டிய வழி அதை முழுமையாக கடக்க வேண்டும் என்றால்?பண்டைய இந்தியாவில், ஒரு இழப்பால் சின்னமிடப்பட்ட ஒரு பெண் தீவிரமான தேடலுக்கு போகிறார்.கத்தும் வேதனையாக தொடங்கியது, சந்திப்புகள், அடையாளங்கள் மற்றும் நிலையான ஞானத்தால் வழிநடத்தப்படும் அமைதியான பயணமாக மாறுகிறது.இது ஒரு தனிப்பட்ட மற்றும் பிரகாசமான நாவல் — துக்கம், நம்பிக்கை மற்றும் உள்ளார்ந்த விழிப்புணர்ச்சி பற்றி.இது உள்ளத்தை தொட்டுவிடும் ஒரு கதை — அனைவரும் பகிரும் ஒரு உண்மை: துக்கம்… மற்றும் அதை மாற்றும் சாத்தியக்கூறு.ஆன்மிக உள்விழிப்பைப் பின்தொடர்பவர்களுக்கும், கவிதைதன்மை கொண்ட உண்மைநிலைக் கதைகளையும் விரும்புபவர்களுக்கும்,ஒரு சாதாரணக் கதையை விட ஒரு அனுபவமாக தேடும் வாசகர்களுக்கும் இது சிறந்த தேர்வு.	கீசாவின் அமைதி: Kisa's Silence (Tamil Edition)
0	304	0	2025-04-27 19:49:30.573795	\N	2ccfd0b6-c642-4a8c-ae17-1ba5f780bdb0	4f92bd96-a219-4d21-9619-28aeb1d17340	\N	Rashmi Uchil	UNKNOWN	https://m.media-amazon.com/images/I/41kMowK8tlL._SX342_SY445_.jpg	Raising Stars: The Challenges and Joys of Being a Bollywood Parent  is a captivating journey into the world of parenthood as experienced by some of Bollywood's stars. Through exclusive interviews with famous celebrities such as Mahima Chaudhry, Nandita Das, Archana Puran Singh, Boman Irani, and many more, author Rashmi Uchil delves deep into these stars' personal stories and cherished practices, inviting you to step inside their homes and hearts. From the joys of raising their children to the unique challenges they face in the spotlight, this book unveils it all. \n\n\n  Whether you're a parent seeking inspiration, a fan of Bollywood, or simply curious about the universal bonds of family, Raising Stars is an enriching and enlightening read that will leave you with a deeper appreciation for the complexities and triumphs of parenthood in the limelight. \n\n\n\n  Exclusive access to Bollywood stars' parenting journeys. \n Heartfelt stories and cherished practices of celebrity parents such as Archana Puran Singh, Bhagyashree Dasani, Chunky Panday, and many more. \n Insights into navigating parenthood in the public eye. \n Written by a seasoned journalist and content development expert. \n  Endorsed by renowned Bollywood stars such as Sushmita Sen and Jackie Shroff.	Raising Stars: The Challenges and Joys of Being a Bollywood Parent
0	136	0	2025-04-27 19:49:32.329655	\N	4c3611e9-bb51-48f5-9370-758db13c5d03	4db17648-26af-4449-81bf-5083a5175716	\N	Bhagat Singh	UNKNOWN	https://m.media-amazon.com/images/I/41KjA2iNXvL._SY445_SX342_.jpg	Bhagat Singh is a name that became synonymous with revolution in India’s struggle for independence. This young boy brought about a change in the way people thought about freedom. He was well read and fought extensively for rights – his own, his comrades’ and his countrymen’s. A discussion with a friend soon turned into a matter of self-assessment for Bhagat Singh, leading to a discourse on why he chose to be an atheist. Even in the face of death at a very young age, his uncanny observation leads to his putting forth some pertinent questions. On another occasion, he was disappointed with his father’s plea in Court for his innocence and chose to write a letter to him. This book is a collection of eighteen of his valued writings from within the walls of prison and outside it, which show us the resolve in his words, and the bravery in his acts subsequently.	Why I am an Atheist and Other Works | Letters & Jail Diary of Bhagat Singh on Revolution, Religion & Politics
0	152	0	2025-04-27 19:49:33.714356	\N	da2aec82-4170-48d8-a1b4-6637a324068d	d09a1486-a31b-44a5-aa15-5e829f6f59d9	\N	Stuti Gupta	UNKNOWN	https://m.media-amazon.com/images/I/51CmIwiqg0L._SY445_SX342_.jpg	Ever wondered how baby Rama got the moon to play with? And how little Krishna defeated a big, black snake to get his ball. Hanuman and Ganesha are also up to something adventurous. Join these little heroes as they swing between being naughty, heroic, curious, creative and thoroughly entertaining. In Magical Mythology, we have put together more than twenty fascinating and unheard of stories from the rich Indian mythology. The stories will amaze you, amuse you and will leave you wanting to read more. You will also find in the book: ° Beautiful illustrations ° Word games and puzzles ° Creative activities ° Colouring pages ° Life lessons at the end of stories ° Lots of fun	Magical Mythology
\.


--
-- Data for Name: book_buy_link; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.book_buy_link (book_id_fk, url, vendor) FROM stdin;
258bb4c6-4389-4647-ade4-5cd737a4bb3e	https://www.amazon.in/Power-Your-Subconscious-Mind-Success/dp/8172345666	AMAZON
93bb8a3e-d122-4b81-b0b8-df980fe821f9	https://www.amazon.in/Same-As-Ever-Timeless-Opportunity/dp/1804090948	AMAZON
ad5eed61-5b95-46ed-adb7-5c4c31e3241a	https://www.amazon.in/Atomic-Habits-James-Clear/dp/1847941834	AMAZON
22887be2-e672-4a02-b47d-2d2d43186be8	https://www.amazon.in/Anxious-Overthinking-Present-Transform-Superpower/dp/936411910X	AMAZON
338263f9-a29a-48ed-9363-fa83fb8aa323	https://www.amazon.in/bestselling-romantic-enemies-romance-Bestselling/dp/9395192747	AMAZON
076b03c2-ca53-4953-a884-2fd7db795df3	https://www.amazon.in/Something-I-Never-Told-You/dp/0143445901	AMAZON
e1c8200e-4f0c-4641-bb96-3e2cdd3b6e0a	https://www.amazon.in/That-Night-Friends-Haunting-Secret/dp/0143451871	AMAZON
11cd23ab-8b08-4ab5-ad5e-26b3d4d7d7ff	https://www.amazon.in/Ragpicker-King-Chronicles-Castellane/dp/1529001447	AMAZON
bbd50959-64d5-4dca-8022-4140ad9be1e3	https://www.amazon.in/Good-Girls-Guide-Murder/dp/1405293187	AMAZON
e05306f4-c5f6-476c-8026-6e81d989d24c	https://www.amazon.in/Harry-Potter-Philosophers-Stone-Rowling/dp/1408855658	AMAZON
9b57547c-ac68-49f4-9323-f51b757644cf	https://www.amazon.in/Housemaid-addictive-psychological-thriller-mind-bending/dp/014346115X	AMAZON
6678737f-7ba4-49a2-b595-3ea350edab6e	https://www.amazon.in/Harry-Potter-Chamber-Secrets/dp/1408855666	AMAZON
04b69f30-d9e9-42af-9daa-6627e318c338	https://www.amazon.in/Paperback-Transformation-Friendship-Teenagers-Storybooks/dp/9362632888	AMAZON
04b69f30-d9e9-42af-9daa-6627e318c338	http://localhost:8080/test123	AMAZON
a0cea2e2-8698-438c-a9c9-0d2875450e47	https://www.amazon.in/Dopamine-Detox-Remove-Distractions-Things/dp/8183286011	AMAZON
42179851-c6d6-401e-80ab-2e386477da84	https://www.amazon.in/Dont-Believe-Everything-You-Think/dp/935543135X	AMAZON
d2a1ceb9-842f-46e5-9727-ae2c60afa9bb	https://www.amazon.in/Metamorphosis-Franz-Kafka-Premium-Paperback/dp/9358836415	AMAZON
78c900d8-ee54-443b-92ad-54e6860af8e4	https://www.amazon.in/Can-We-Strangers-Again-bittersweet/dp/0143475924	AMAZON
29570c2e-588c-41d8-94fc-3efab7484e8a	https://www.amazon.in/Blue-Umbrella-Ruskin-Bond/dp/8171673406	AMAZON
7c48226c-67e3-402f-b359-96337e270674	https://www.amazon.in/Deewar-साहित्य-पुरस्कार-पुरस्कृत-उपन्यास/dp/939282078X	AMAZON
66c14057-751c-4ae8-8754-69c7a727a720	https://www.amazon.in/Art-Being-Alone-Loneliness-Solitude/dp/9355434022	AMAZON
ea633555-4e42-4b23-ab41-364d485c152d	https://www.amazon.in/Alchemist-Paulo-Coelho/dp/8172234988	AMAZON
a5f2b242-981c-41f1-8017-9624d0d76cd2	https://www.amazon.in/Alchemist-Paulo-Coelho/dp/0008144222	AMAZON
f5bcae0a-6d4d-46d5-868b-faaa05987a60	https://www.amazon.in/Alchemist-25th-Anniversary-Fable-Following/dp/0062355309	AMAZON
a44f50d9-0667-4018-8877-2f6f40ccd6b9	https://www.amazon.in/Theory-Everything-Stephen-Hawking/dp/8179925919	AMAZON
24e2e378-3192-4cb1-91b5-db39b21c9911	https://www.amazon.in/Theory-Everything-Origin-Fate-Universe/dp/1893224546	AMAZON
f43462ed-22b3-44da-a759-e2353c56659c	https://www.amazon.in/Psychopathology-Introduction-Psychology-Subconscious-Interpretation/dp/9362631687	AMAZON
aa04c0f4-afd4-4de6-bbd8-196416d26757	https://www.amazon.in/Read-People-Like-Book-Understand/dp/1647432235	AMAZON
ec17acb1-2e89-450f-844f-4655099fdfdd	https://www.amazon.in/Read-People-Like-Book-Patrick/dp/B0C23SF7NQ	AMAZON
5e298689-de4e-46c8-b6c9-48afa3b6f060	https://www.amazon.in/Dostoevsky-Paperback-Loneliness-Greatest-Literature/dp/9362639505	AMAZON
b3cdc6c1-d9b1-4237-8ceb-efcdf7163b1f	https://www.amazon.in/First-Mythology-Tale-Illustrated-Books/dp/9390292964	AMAZON
18264a96-4f32-4ff1-bb0d-72591b7f9567	https://www.amazon.in/MAGIC-BYRNE-RHONDA/dp/1849838399	AMAZON
b18b5273-4b97-4feb-9ae7-ea59ad432fb0	https://www.amazon.in/Hidden-Hindu-Boxset-Akshat-Gupta/dp/0143467352	AMAZON
fac94f1b-3a4d-4649-9e2a-76c4a7a54b49	https://www.amazon.in/BOTTESH-Read-People-Like-Book/dp/B0DY4MFT2Q	AMAZON
311e9b2a-bef2-45b6-8a1a-86cce5795306	https://www.amazon.in/201-Brain-Booster-Activity-Book/dp/935440460X	AMAZON
f449b773-8197-46b9-ab73-360042d1a2de	https://www.amazon.in/Raat-Tak-Hindi-Stories-Gaurow/dp/8196329466	AMAZON
5cf8e82a-f3fd-469a-bef3-b9cab2a57738	https://www.amazon.in/You-Can-Win-step-achievers/dp/9382951717	AMAZON
aa204165-7b2f-4701-ac29-b95d6f08dc0f	https://www.amazon.in/Constable-Current-Punjabi-English-Gagandeep/dp/B0F3PF62MD	AMAZON
dc886c34-0910-42f8-ae3d-72e639769394	https://www.amazon.in/Complete-English-Grammar-Bilingual-Competitive/dp/8197025215	AMAZON
005ad2cc-166b-4894-b8df-e92928f42d89	https://www.amazon.in/Colouring-Books-Kids-Colour-Children/dp/9388369785	AMAZON
8fa73ba5-dfa4-49e9-87d8-0543fe13124c	https://www.amazon.in/Ayurvedic-Garbha-Sanskar-English-Balaji/dp/9380571879	AMAZON
e1ad69dc-fbc2-4753-a848-62bf4e6c9409	https://www.amazon.in/Current-Affairs-Months-Events-Srivastava/dp/8196391811	AMAZON
3537eab8-d657-499e-9369-b7d9dccfd4fa	https://www.amazon.in/Educart-Entrance-General-Guidebook-Commerce/dp/9360540919	AMAZON
b7de9b9b-c5c1-4964-b884-8c5b387f3343	https://www.amazon.in/Winsar-Uttarakhand-HINDI-Useful-Entrance/dp/B0DP4MD361	AMAZON
7b675f8b-2169-4847-ab46-e10290c60734	https://www.amazon.in/Beekeeper-Aleppo-Novel-Christy-Lefteri/dp/1984821210	AMAZON
75177cbe-ec7f-47e1-8a1e-9e906c55c6be	https://www.amazon.in/Readers-Reading-Practice-Sentences-Paragraphs/dp/B0CP6DFT2W	AMAZON
951ff018-c757-4607-8aca-e71165447a1a	https://www.amazon.in/Utkarsh-Classes-Chaturth-Karmchari-Rajasthan/dp/B0DRKWVYDR	AMAZON
d2960bac-1bf3-457c-997f-7229d0498e44	https://www.amazon.in/General-Detailed-Explanation-2022-25-English/dp/B0F48KZ9H5	AMAZON
0b3a304e-7aff-4221-bd46-bc272c503271	https://www.amazon.in/Rajasthan-Mairathan-according-syllabus-राजस्थान/dp/B0F2S1K5ZC	AMAZON
e5488701-386f-4f18-99ca-5bbed1b0d927	https://www.amazon.in/NCERT-Page-Wise-Practice-Books-P-Class/dp/B0DXVJP7QN	AMAZON
2624c26d-d2dc-4200-992f-7ac81993448c	https://www.amazon.in/TAIT-Abhiyogyata-Buddhimatta-बुद्धिमत्ता-प्रश्नपत्रिका/dp/B0F2Z83D39	AMAZON
a0193d7f-56ec-482e-aa8f-73f2746f1997	https://www.amazon.in/Ayurvedic-Garbha-Sanskar-Science-Pregnancy/dp/9380571852	AMAZON
f0d5decb-24eb-4bc4-bca3-98f81c5b4597	https://www.amazon.in/Things-Realize-Before-its-Late/dp/B0BY2W967S	AMAZON
0a37121d-f447-428a-9b56-9417b5677239	https://www.amazon.in/201-English-Activity-Book-Activities/dp/9354404685	AMAZON
2ad5a165-b219-4e4e-9deb-790f9d8717f5	https://www.amazon.in/ISI-CMI-Exam-Preparation-Book/dp/B0F4JY9VMT	AMAZON
ea0fbfdf-b797-4638-b184-2441ee4d78a3	https://www.amazon.in/201-Maths-Activity-Book-Addition-Subtraction/dp/9354404529	AMAZON
e06a9279-0c71-4a36-a122-cfc92daa13b2	https://www.amazon.in/NSO-IMO-IEO-ICSO-IGKO-ISSO-Olympiad-Reasoning-Workbook-Achievers/dp/9369576630	AMAZON
dabc0de3-e615-4a5f-a162-a5315ecfdfd6	https://www.amazon.in/ZOQWEID-Practice-Copybook-Preschoolers-Calligraphy/dp/B09P66SGQN	AMAZON
36213f0d-b4bd-41e0-9a47-3c07385aa90c	https://www.amazon.in/Doodle-Colouring-Boosting-Coloring-Birthday/dp/8119608607	AMAZON
b92bf167-1d4b-4231-b25e-ffb120c9d94e	https://www.amazon.in/Approach-Non-Verbal-Reasoning-Questions-Competitive/dp/B0CVGWMLQL	AMAZON
a75a579e-b1f8-4cbc-b293-cb3c4c9b0825	https://www.amazon.in/Information-Technology-Skill-Course-Class/dp/9367119984	AMAZON
1638e074-550e-4cfd-9973-6ef37d3bba7e	https://www.amazon.in/Computer-General-Knowledge-Current-Railway/dp/B0DQHJ3XV2	AMAZON
b722fd2c-248e-4cd5-ab9b-4553c3bc3445	https://www.amazon.in/Metamorphosis-Franz-Kafka/dp/9355201060	AMAZON
70f11c0c-3c03-4e1b-a5a7-1a659771da3d	https://www.amazon.in/Beekeeper-Aleppo-Christy-Lefteri/dp/1838770011	AMAZON
05c31326-7500-40ab-a748-9b3947d0e258	https://www.amazon.in/BUNGLE-Einstein-Never-Used-Flashcards/dp/B0F4QXFBKM	AMAZON
d7b710fe-e038-42ea-9940-7149d44e6bdd	https://www.amazon.in/Flowers-Coloring-adults-Wonder-House/dp/9354404545	AMAZON
65d85414-ab57-4a0d-b5e4-67d73ebb3a97	https://www.amazon.in/My-Little-Book-Planets-Rhyming/dp/9815233394	AMAZON
63f6a8e1-d3e3-403b-bf3f-2a2ecc157bf3	https://www.amazon.in/Dopamine-Detox-Remove-Distractions-Productivity-ebook/dp/B098MHBF23	AMAZON
fba510f5-6c48-4ddf-b1f6-6ac75e9b2abc	https://www.amazon.in/Dont-Believe-Everything-You-Think-ebook/dp/B0BJHG532P	AMAZON
25c9a9a9-3de3-4714-b107-177b0d6bc0c0	https://www.amazon.in/Can-We-Strangers-Again-Bestseller-ebook/dp/B0F6D5CSYV	AMAZON
3aa40cf6-8bb9-4658-895f-42e3e8e0d88c	https://www.amazon.in/Worlds-Greatest-Personal-Growth-Wealth-ebook/dp/B0D7HWLF3Y	AMAZON
3f1b2175-0661-4294-8f1e-1ebb10b1e0b9	https://www.amazon.in/Worlds-Greatest-Personal-Growth-Wealth/dp/9389432014	AMAZON
34e50bcf-9e0f-488b-aa43-577e4a4874ce	https://www.amazon.in/Art-Being-ALONE-Solitude-Loneliness-ebook/dp/B0C4YC8CXS	AMAZON
9c804133-52c3-46fb-b505-50e011d0bf08	https://www.amazon.in/Alchemist-Fable-About-Following-Dream/dp/B06X99L7W7	AMAZON
000cb8b6-c053-446c-b907-8cb1b30058e3	https://www.amazon.in/Theory-Everything-Origin-Fate-Universe-ebook/dp/B008JZR5LA	AMAZON
b2370ab2-9864-4c8e-872f-f6d832dfa773	https://www.amazon.in/Theory-Everything-Origin-Fate-Universe/dp/159777071X	AMAZON
e9475154-0586-4861-a186-5e009b676008	https://www.amazon.in/Read-People-Like-Book-Charismatic-ebook/dp/B08PT6S7ZW	AMAZON
dac8c0bc-d556-4a66-8990-e8cefa205ca2	https://www.amazon.in/Read-People-like-Book-Charismatic/dp/B08R6HTLRP	AMAZON
839d07f6-6fea-4c04-80c8-818b636f3d24	https://www.amazon.in/You-Can-Win-step-achievers-ebook/dp/B00HQE51N4	AMAZON
89da26e0-f2e3-4121-977b-298ffafdf3f0	https://www.amazon.in/You-Can-Win/dp/B07L9G1XYY	AMAZON
2625f1d6-7834-4efb-a6ec-f54f0ddbd1af	https://www.amazon.in/Ayurvedic-Garbha-Sanskar-Science-Pregnancy-ebook/dp/B087436CLW	AMAZON
3449f19c-efdb-4995-8e05-416b6bcc9756	https://www.amazon.in/Falling-you-unexpectedly-Saumya-Jain-ebook/dp/B0F5NBLQ9J	AMAZON
e98d7b3d-93bd-48cc-8925-76a80a6a0577	https://www.amazon.in/Things-Realize-Before-Its-late-ebook/dp/B0BTV16YBF	AMAZON
b68b462f-0716-4d4d-b65d-15f9f74c5457	https://www.amazon.in/Jungle-Book-Classic-Adventure-Wilderness-ebook/dp/B0F5X7QGVN	AMAZON
21919dfd-a2f6-433f-b0b6-7d6b07b2baeb	https://www.amazon.in/Beekeeper-Aleppo-moving-testament-spirit-ebook/dp/B07HZ1PJMY	AMAZON
389a464d-05b4-405f-baf4-e71be7aa9ff8	https://www.amazon.in/Macmillan-Accounting-Retail-Wealth-Economy-Financial/dp/B0DXFRCNRX	AMAZON
13b9ec6e-838d-458d-949d-b85985b225c5	https://www.amazon.in/big-book-1200-knock-jokes-ebook/dp/B0F6CW14LQ	AMAZON
afd6461e-6930-456a-844a-b25092064d35	https://www.amazon.in/கீசாவின்-அமைதி-Kisas-Silence-Tamil-ebook/dp/B0F5QBVSRK	AMAZON
c7db597d-47d3-45b0-b95f-65c975055a51	https://www.amazon.in/Hornbill-Textbook-Snapshots-Supplementary-Class-XI/dp/B097R9T444	AMAZON
2ccfd0b6-c642-4a8c-ae17-1ba5f780bdb0	https://www.amazon.in/dp/9358563281	AMAZON
4c3611e9-bb51-48f5-9370-758db13c5d03	https://www.amazon.in/dp/9387022811	AMAZON
da2aec82-4170-48d8-a1b4-6637a324068d	https://www.amazon.in/dp/8194790867	AMAZON
\.


--
-- Data for Name: book_club; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.book_club (book_id_fk, club_id) FROM stdin;
\.


--
-- Data for Name: book_genre; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.book_genre (book_id_fk, genre_id) FROM stdin;
\.


--
-- Data for Name: book_tag; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.book_tag (book_id_fk, tag_id) FROM stdin;
258bb4c6-4389-4647-ade4-5cd737a4bb3e	amazon-book
93bb8a3e-d122-4b81-b0b8-df980fe821f9	amazon-book
ad5eed61-5b95-46ed-adb7-5c4c31e3241a	amazon-book
076b03c2-ca53-4953-a884-2fd7db795df3	amazon-book
338263f9-a29a-48ed-9363-fa83fb8aa323	amazon-book
22887be2-e672-4a02-b47d-2d2d43186be8	amazon-book
e1c8200e-4f0c-4641-bb96-3e2cdd3b6e0a	amazon-book
11cd23ab-8b08-4ab5-ad5e-26b3d4d7d7ff	amazon-book
bbd50959-64d5-4dca-8022-4140ad9be1e3	amazon-book
04b69f30-d9e9-42af-9daa-6627e318c338	amazon-book
e05306f4-c5f6-476c-8026-6e81d989d24c	amazon-book
9b57547c-ac68-49f4-9323-f51b757644cf	amazon-book
6678737f-7ba4-49a2-b595-3ea350edab6e	amazon-book
a0cea2e2-8698-438c-a9c9-0d2875450e47	amazon-book
42179851-c6d6-401e-80ab-2e386477da84	amazon-book
d2a1ceb9-842f-46e5-9727-ae2c60afa9bb	amazon-book
78c900d8-ee54-443b-92ad-54e6860af8e4	amazon-book
29570c2e-588c-41d8-94fc-3efab7484e8a	amazon-book
7c48226c-67e3-402f-b359-96337e270674	amazon-book
66c14057-751c-4ae8-8754-69c7a727a720	amazon-book
ea633555-4e42-4b23-ab41-364d485c152d	amazon-book
a5f2b242-981c-41f1-8017-9624d0d76cd2	amazon-book
f5bcae0a-6d4d-46d5-868b-faaa05987a60	amazon-book
a44f50d9-0667-4018-8877-2f6f40ccd6b9	amazon-book
24e2e378-3192-4cb1-91b5-db39b21c9911	amazon-book
f43462ed-22b3-44da-a759-e2353c56659c	amazon-book
aa04c0f4-afd4-4de6-bbd8-196416d26757	amazon-book
ec17acb1-2e89-450f-844f-4655099fdfdd	amazon-book
5e298689-de4e-46c8-b6c9-48afa3b6f060	amazon-book
b3cdc6c1-d9b1-4237-8ceb-efcdf7163b1f	amazon-book
18264a96-4f32-4ff1-bb0d-72591b7f9567	amazon-book
b18b5273-4b97-4feb-9ae7-ea59ad432fb0	amazon-book
fac94f1b-3a4d-4649-9e2a-76c4a7a54b49	amazon-book
311e9b2a-bef2-45b6-8a1a-86cce5795306	amazon-book
f449b773-8197-46b9-ab73-360042d1a2de	amazon-book
5cf8e82a-f3fd-469a-bef3-b9cab2a57738	amazon-book
aa204165-7b2f-4701-ac29-b95d6f08dc0f	amazon-book
dc886c34-0910-42f8-ae3d-72e639769394	amazon-book
8fa73ba5-dfa4-49e9-87d8-0543fe13124c	amazon-book
a0193d7f-56ec-482e-aa8f-73f2746f1997	amazon-book
e1ad69dc-fbc2-4753-a848-62bf4e6c9409	amazon-book
005ad2cc-166b-4894-b8df-e92928f42d89	amazon-book
f0d5decb-24eb-4bc4-bca3-98f81c5b4597	amazon-book
a75a579e-b1f8-4cbc-b293-cb3c4c9b0825	amazon-book
3537eab8-d657-499e-9369-b7d9dccfd4fa	amazon-book
1638e074-550e-4cfd-9973-6ef37d3bba7e	amazon-book
b722fd2c-248e-4cd5-ab9b-4553c3bc3445	amazon-book
0a37121d-f447-428a-9b56-9417b5677239	amazon-book
b7de9b9b-c5c1-4964-b884-8c5b387f3343	amazon-book
7b675f8b-2169-4847-ab46-e10290c60734	amazon-book
2ad5a165-b219-4e4e-9deb-790f9d8717f5	amazon-book
70f11c0c-3c03-4e1b-a5a7-1a659771da3d	amazon-book
75177cbe-ec7f-47e1-8a1e-9e906c55c6be	amazon-book
ea0fbfdf-b797-4638-b184-2441ee4d78a3	amazon-book
951ff018-c757-4607-8aca-e71165447a1a	amazon-book
e06a9279-0c71-4a36-a122-cfc92daa13b2	amazon-book
dabc0de3-e615-4a5f-a162-a5315ecfdfd6	amazon-book
d2960bac-1bf3-457c-997f-7229d0498e44	amazon-book
0b3a304e-7aff-4221-bd46-bc272c503271	amazon-book
05c31326-7500-40ab-a748-9b3947d0e258	amazon-book
36213f0d-b4bd-41e0-9a47-3c07385aa90c	amazon-book
d7b710fe-e038-42ea-9940-7149d44e6bdd	amazon-book
b92bf167-1d4b-4231-b25e-ffb120c9d94e	amazon-book
e5488701-386f-4f18-99ca-5bbed1b0d927	amazon-book
65d85414-ab57-4a0d-b5e4-67d73ebb3a97	amazon-book
2624c26d-d2dc-4200-992f-7ac81993448c	amazon-book
63f6a8e1-d3e3-403b-bf3f-2a2ecc157bf3	amazon-book
fba510f5-6c48-4ddf-b1f6-6ac75e9b2abc	amazon-book
25c9a9a9-3de3-4714-b107-177b0d6bc0c0	amazon-book
3aa40cf6-8bb9-4658-895f-42e3e8e0d88c	amazon-book
3f1b2175-0661-4294-8f1e-1ebb10b1e0b9	amazon-book
34e50bcf-9e0f-488b-aa43-577e4a4874ce	amazon-book
9c804133-52c3-46fb-b505-50e011d0bf08	amazon-book
000cb8b6-c053-446c-b907-8cb1b30058e3	amazon-book
b2370ab2-9864-4c8e-872f-f6d832dfa773	amazon-book
e9475154-0586-4861-a186-5e009b676008	amazon-book
dac8c0bc-d556-4a66-8990-e8cefa205ca2	amazon-book
839d07f6-6fea-4c04-80c8-818b636f3d24	amazon-book
89da26e0-f2e3-4121-977b-298ffafdf3f0	amazon-book
2625f1d6-7834-4efb-a6ec-f54f0ddbd1af	amazon-book
3449f19c-efdb-4995-8e05-416b6bcc9756	amazon-book
e98d7b3d-93bd-48cc-8925-76a80a6a0577	amazon-book
b68b462f-0716-4d4d-b65d-15f9f74c5457	amazon-book
21919dfd-a2f6-433f-b0b6-7d6b07b2baeb	amazon-book
389a464d-05b4-405f-baf4-e71be7aa9ff8	amazon-book
13b9ec6e-838d-458d-949d-b85985b225c5	amazon-book
afd6461e-6930-456a-844a-b25092064d35	amazon-book
c7db597d-47d3-45b0-b95f-65c975055a51	amazon-book
2ccfd0b6-c642-4a8c-ae17-1ba5f780bdb0	amazon-book
4c3611e9-bb51-48f5-9370-758db13c5d03	amazon-book
da2aec82-4170-48d8-a1b4-6637a324068d	amazon-book
\.


--
-- Data for Name: event_log; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.event_log ("timestamp", event_id, error_message, event_type, processing_status, routing_key, payload) FROM stdin;
\.


--
-- Data for Name: flyway_schema_history; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.flyway_schema_history (installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) FROM stdin;
1	1	initial migration	SQL	V1__initial_migration.sql	-1088013730	postgres	2025-04-27 11:35:05.389429	142	t
2	2	initial constraint	SQL	V2__initial_constraint.sql	-1575031915	postgres	2025-04-27 11:35:05.611405	45	t
3	3	event log migration	SQL	V3__event_log_migration.sql	1610894772	postgres	2025-04-27 11:35:05.701942	26	t
\.


--
-- Data for Name: review; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.review (inappropriate, star, created_at, book_id_fk, id, user_id, review_description, review_title, user_profile_url) FROM stdin;
\.


--
-- Data for Name: 24577..24615; Type: BLOBS; Schema: -; Owner: postgres
--

BEGIN;

SELECT pg_catalog.lo_open('24577', 131072);
SELECT pg_catalog.lowrite(0, '\x48656c6c6f206d792074657374206d657373616765');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('24578', 131072);
SELECT pg_catalog.lowrite(0, '\x48656c6c6f206d792074657374206d657373616765');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('24579', 131072);
SELECT pg_catalog.lowrite(0, '\x48656c6c6f206d792074657374206d657373616765');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('24580', 131072);
SELECT pg_catalog.lowrite(0, '\x48656c6c6f206d792074657374206d657373616765');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('24581', 131072);
SELECT pg_catalog.lowrite(0, '\x48656c6c6f206d792074657374206d657373616765');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('24582', 131072);
SELECT pg_catalog.lowrite(0, '\x48656c6c6f206d792074657374206d657373616765');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('24583', 131072);
SELECT pg_catalog.lowrite(0, '\x48656c6c6f206d792074657374206d657373616765');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('24584', 131072);
SELECT pg_catalog.lowrite(0, '\x48656c6c6f206d792074657374206d657373616765');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('24585', 131072);
SELECT pg_catalog.lowrite(0, '\x7b2275726c223a2268747470733a2f2f7777772e616d617a6f6e2e696e2f50737963686f6c6f67792d4d6f6e65792d4d6f7267616e2d486f7573656c2f64702f39333930313636323638222c2276656e646f72223a22414d415a4f4e227d');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('24586', 131072);
SELECT pg_catalog.lowrite(0, '\x7b2275726c223a2268747470733a2f2f7777772e616d617a6f6e2e696e2f50737963686f6c6f67792d4d6f6e65792d4d6f7267616e2d486f7573656c2f64702f39333930313636323638222c2276656e646f72223a22414d415a4f4e227d');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('24587', 131072);
SELECT pg_catalog.lowrite(0, '\x7b2275726c223a2268747470733a2f2f7777772e616d617a6f6e2e696e2f50737963686f6c6f67792d4d6f6e65792d4d6f7267616e2d486f7573656c2f64702f39333930313636323638222c2276656e646f72223a22414d415a4f4e227d');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('24588', 131072);
SELECT pg_catalog.lowrite(0, '\x7b2275726c223a2268747470733a2f2f7777772e616d617a6f6e2e696e2f50737963686f6c6f67792d4d6f6e65792d4d6f7267616e2d486f7573656c2f64702f39333930313636323638222c2276656e646f72223a22414d415a4f4e227d');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('24589', 131072);
SELECT pg_catalog.lowrite(0, '\x7b2275726c223a2268747470733a2f2f7777772e616d617a6f6e2e696e2f50737963686f6c6f67792d4d6f6e65792d4d6f7267616e2d486f7573656c2f64702f39333930313636323638222c2276656e646f72223a22414d415a4f4e227d');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('24590', 131072);
SELECT pg_catalog.lowrite(0, '\x7b2275726c223a2268747470733a2f2f7777772e616d617a6f6e2e696e2f50737963686f6c6f67792d4d6f6e65792d4d6f7267616e2d486f7573656c2f64702f39333930313636323638222c2276656e646f72223a22414d415a4f4e227d');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('24591', 131072);
SELECT pg_catalog.lowrite(0, '\x7b2275726c223a2268747470733a2f2f7777772e616d617a6f6e2e696e2f50737963686f6c6f67792d4d6f6e65792d4d6f7267616e2d486f7573656c2f64702f39333930313636323638222c2276656e646f72223a22414d415a4f4e227d');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('24592', 131072);
SELECT pg_catalog.lowrite(0, '\x7b2275726c223a2268747470733a2f2f7777772e616d617a6f6e2e696e2f50737963686f6c6f67792d4d6f6e65792d4d6f7267616e2d486f7573656c2f64702f39333930313636323638222c2276656e646f72223a22414d415a4f4e227d');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('24593', 131072);
SELECT pg_catalog.lowrite(0, '\x7b2275726c223a2268747470733a2f2f7777772e616d617a6f6e2e696e2f50737963686f6c6f67792d4d6f6e65792d4d6f7267616e2d486f7573656c2f64702f39333930313636323638222c2276656e646f72223a22414d415a4f4e227d');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('24594', 131072);
SELECT pg_catalog.lowrite(0, '\x7b2275726c223a2268747470733a2f2f7777772e616d617a6f6e2e696e2f50737963686f6c6f67792d4d6f6e65792d4d6f7267616e2d486f7573656c2f64702f39333930313636323638222c2276656e646f72223a22414d415a4f4e227d');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('24595', 131072);
SELECT pg_catalog.lowrite(0, '\x7b2275726c223a2268747470733a2f2f7777772e616d617a6f6e2e696e2f50737963686f6c6f67792d4d6f6e65792d4d6f7267616e2d486f7573656c2f64702f39333930313636323638222c2276656e646f72223a22414d415a4f4e227d');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('24596', 131072);
SELECT pg_catalog.lowrite(0, '\x7b2275726c223a2268747470733a2f2f7777772e616d617a6f6e2e696e2f50737963686f6c6f67792d4d6f6e65792d4d6f7267616e2d486f7573656c2f64702f39333930313636323638222c2276656e646f72223a22414d415a4f4e227d');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('24597', 131072);
SELECT pg_catalog.lowrite(0, '\x7b2275726c223a2268747470733a2f2f7777772e616d617a6f6e2e696e2f50737963686f6c6f67792d4d6f6e65792d4d6f7267616e2d486f7573656c2f64702f39333930313636323638222c2276656e646f72223a22414d415a4f4e227d');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('24598', 131072);
SELECT pg_catalog.lowrite(0, '\x7b2275726c223a2268747470733a2f2f7777772e616d617a6f6e2e696e2f50737963686f6c6f67792d4d6f6e65792d4d6f7267616e2d486f7573656c2f64702f39333930313636323638222c2276656e646f72223a22414d415a4f4e227d');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('24599', 131072);
SELECT pg_catalog.lowrite(0, '\x7b2275726c223a2268747470733a2f2f7777772e616d617a6f6e2e696e2f50737963686f6c6f67792d4d6f6e65792d4d6f7267616e2d486f7573656c2f64702f39333930313636323638222c2276656e646f72223a22414d415a4f4e227d');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('24600', 131072);
SELECT pg_catalog.lowrite(0, '\x7b2275726c223a2268747470733a2f2f7777772e616d617a6f6e2e696e2f50737963686f6c6f67792d4d6f6e65792d4d6f7267616e2d486f7573656c2f64702f39333930313636323638222c2276656e646f72223a22414d415a4f4e227d');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('24601', 131072);
SELECT pg_catalog.lowrite(0, '\x7b2275726c223a2268747470733a2f2f7777772e616d617a6f6e2e696e2f50737963686f6c6f67792d4d6f6e65792d4d6f7267616e2d486f7573656c2f64702f39333930313636323638222c2276656e646f72223a22414d415a4f4e227d');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('24602', 131072);
SELECT pg_catalog.lowrite(0, '\x7b2275726c223a2268747470733a2f2f7777772e616d617a6f6e2e696e2f50737963686f6c6f67792d4d6f6e65792d4d6f7267616e2d486f7573656c2f64702f39333930313636323638222c2276656e646f72223a22414d415a4f4e227d');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('24603', 131072);
SELECT pg_catalog.lowrite(0, '\x7b2275726c223a2268747470733a2f2f7777772e616d617a6f6e2e696e2f50737963686f6c6f67792d4d6f6e65792d4d6f7267616e2d486f7573656c2f64702f39333930313636323638222c2276656e646f72223a22414d415a4f4e227d');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('24604', 131072);
SELECT pg_catalog.lowrite(0, '\x7b2275726c223a2268747470733a2f2f7777772e616d617a6f6e2e696e2f50737963686f6c6f67792d4d6f6e65792d4d6f7267616e2d486f7573656c2f64702f39333930313636323638222c2276656e646f72223a22414d415a4f4e227d');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('24605', 131072);
SELECT pg_catalog.lowrite(0, '\x7b2275726c223a2268747470733a2f2f7777772e616d617a6f6e2e696e2f50737963686f6c6f67792d4d6f6e65792d4d6f7267616e2d486f7573656c2f64702f39333930313636323638222c2276656e646f72223a22414d415a4f4e227d');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('24606', 131072);
SELECT pg_catalog.lowrite(0, '\x7b2275726c223a2268747470733a2f2f7777772e616d617a6f6e2e696e2f50737963686f6c6f67792d4d6f6e65792d4d6f7267616e2d486f7573656c2f64702f39333930313636323638222c2276656e646f72223a22414d415a4f4e227d');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('24607', 131072);
SELECT pg_catalog.lowrite(0, '\x7b2275726c223a2268747470733a2f2f7777772e616d617a6f6e2e696e2f50737963686f6c6f67792d4d6f6e65792d4d6f7267616e2d486f7573656c2f64702f39333930313636323638222c2276656e646f72223a22414d415a4f4e227d');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('24608', 131072);
SELECT pg_catalog.lowrite(0, '\x7b2275726c223a2268747470733a2f2f7777772e616d617a6f6e2e696e2f50737963686f6c6f67792d4d6f6e65792d4d6f7267616e2d486f7573656c2f64702f39333930313636323638222c2276656e646f72223a22414d415a4f4e227d');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('24609', 131072);
SELECT pg_catalog.lowrite(0, '\x7b2275726c223a2268747470733a2f2f7777772e616d617a6f6e2e696e2f50737963686f6c6f67792d4d6f6e65792d4d6f7267616e2d486f7573656c2f64702f39333930313636323638222c2276656e646f72223a22414d415a4f4e227d');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('24610', 131072);
SELECT pg_catalog.lowrite(0, '\x7b2275726c223a2268747470733a2f2f7777772e616d617a6f6e2e696e2f50737963686f6c6f67792d4d6f6e65792d4d6f7267616e2d486f7573656c2f64702f39333930313636323638222c2276656e646f72223a22414d415a4f4e227d');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('24611', 131072);
SELECT pg_catalog.lowrite(0, '\x7b2275726c223a2268747470733a2f2f7777772e616d617a6f6e2e696e2f50737963686f6c6f67792d4d6f6e65792d4d6f7267616e2d486f7573656c2f64702f39333930313636323638222c2276656e646f72223a22414d415a4f4e227d');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('24612', 131072);
SELECT pg_catalog.lowrite(0, '\x7b2275726c223a2268747470733a2f2f7777772e616d617a6f6e2e696e2f50737963686f6c6f67792d4d6f6e65792d4d6f7267616e2d486f7573656c2f64702f39333930313636323638222c2276656e646f72223a22414d415a4f4e227d');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('24613', 131072);
SELECT pg_catalog.lowrite(0, '\x7b2275726c223a2268747470733a2f2f7777772e616d617a6f6e2e696e2f5468696e6b696e672d466173742d50656e6775696e2d50726573732d4e6f6e2d46696374696f6e2f64702f30313431303333353736222c2276656e646f72223a22414d415a4f4e227d');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('24614', 131072);
SELECT pg_catalog.lowrite(0, '\x7b2275726c223a2268747470733a2f2f7777772e616d617a6f6e2e696e2f5468696e6b696e672d466173742d50656e6775696e2d50726573732d4e6f6e2d46696374696f6e2f64702f30313431303333353736222c2276656e646f72223a22414d415a4f4e227d');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('24615', 131072);
SELECT pg_catalog.lowrite(0, '\x7b2275726c223a2268747470733a2f2f7777772e616d617a6f6e2e696e2f5468696e6b696e672d466173742d50656e6775696e2d50726573732d4e6f6e2d46696374696f6e2f64702f30313431303333353736222c2276656e646f72223a22414d415a4f4e227d');
SELECT pg_catalog.lo_close(0);

COMMIT;

--
-- Name: book_buy_link book_buy_link_vendor_url_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book_buy_link
    ADD CONSTRAINT book_buy_link_vendor_url_key UNIQUE (vendor, url);


--
-- Name: book book_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book
    ADD CONSTRAINT book_pkey PRIMARY KEY (book_id);


--
-- Name: event_log event_log_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.event_log
    ADD CONSTRAINT event_log_pkey PRIMARY KEY (event_id);


--
-- Name: flyway_schema_history flyway_schema_history_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.flyway_schema_history
    ADD CONSTRAINT flyway_schema_history_pk PRIMARY KEY (installed_rank);


--
-- Name: review review_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.review
    ADD CONSTRAINT review_pkey PRIMARY KEY (id);


--
-- Name: flyway_schema_history_s_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX flyway_schema_history_s_idx ON public.flyway_schema_history USING btree (success);


--
-- Name: book_buy_link fk_book_buy_links_book; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book_buy_link
    ADD CONSTRAINT fk_book_buy_links_book FOREIGN KEY (book_id_fk) REFERENCES public.book(book_id);


--
-- Name: book_club fk_book_clubs_book; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book_club
    ADD CONSTRAINT fk_book_clubs_book FOREIGN KEY (book_id_fk) REFERENCES public.book(book_id);


--
-- Name: book_genre fk_book_genre; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book_genre
    ADD CONSTRAINT fk_book_genre FOREIGN KEY (book_id_fk) REFERENCES public.book(book_id);


--
-- Name: book_tag fk_book_tag; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book_tag
    ADD CONSTRAINT fk_book_tag FOREIGN KEY (book_id_fk) REFERENCES public.book(book_id);


--
-- Name: review fk_reviews_book; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.review
    ADD CONSTRAINT fk_reviews_book FOREIGN KEY (book_id_fk) REFERENCES public.book(book_id);


--
-- PostgreSQL database dump complete
--

