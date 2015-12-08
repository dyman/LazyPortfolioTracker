# --- !Ups
CREATE TABLE "quotes"
(
  id serial NOT NULL,
  quote text NOT NULL,
  author character varying(255) NOT NULL,
  CONSTRAINT quotes_pg_key PRIMARY KEY (id)
);


INSERT INTO quotes VALUES (1, 'Too many people spend money they earned..to buy things they don’t want..to impress people that they don’t like.', 'Will Rogers');
INSERT INTO quotes VALUES (2, 'A wise person should have money in their head, but not in their heart.', 'Jonathan Swift');
INSERT INTO quotes VALUES (3, 'Wealth consists not in having great possessions, but in having few wants.', 'Epictetus');
INSERT INTO quotes VALUES (4, 'Money often costs too much.', 'Ralph Waldo Emerson');
INSERT INTO quotes VALUES (5, 'Everyday is a bank account, and time is our currency. No one is rich, no one is poor, we’ve got 24 hours each.', 'Christopher Rice');
INSERT INTO quotes VALUES (6, 'It’s how you deal with failure that determines how you achieve success.', 'David Feherty');
INSERT INTO quotes VALUES (7, 'Frugality includes all the other virtues.', 'Cicero');
INSERT INTO quotes VALUES (8, 'I love money. I love everything about it. I bought some pretty good stuff. Got me a $300 pair of socks. Got a fur sink. An electric dog polisher. A gasoline powered turtleneck sweater. And, of course, I bought some dumb stuff, too.', 'Steve Martin');
INSERT INTO quotes VALUES (9, 'An investment in knowledge pays the best interest.', 'Benjamin Franklin');
INSERT INTO quotes VALUES (10, 'I will tell you the secret to getting rich on Wall Street. You try to be greedy when others are fearful. And you try to be fearful when others are greedy.', 'Warren Buffett');


# --- !Downs
DROP TABLE "quotes"
