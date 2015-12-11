# --- !Ups
CREATE TABLE "quote"
(
  id serial NOT NULL,
  quote text NOT NULL,
  author character varying(255) NOT NULL,
  CONSTRAINT quote_pg_key PRIMARY KEY (id)
);


INSERT INTO quote VALUES (1, 'Too many people spend money they earned..to buy things they don’t want..to impress people that they don’t like.', 'Will Rogers');
INSERT INTO quote VALUES (2, 'A wise person should have money in their head, but not in their heart.', 'Jonathan Swift');
INSERT INTO quote VALUES (3, 'Wealth consists not in having great possessions, but in having few wants.', 'Epictetus');
INSERT INTO quote VALUES (4, 'Money often costs too much.', 'Ralph Waldo Emerson');
INSERT INTO quote VALUES (5, 'Everyday is a bank account, and time is our currency. No one is rich, no one is poor, we’ve got 24 hours each.', 'Christopher Rice');
INSERT INTO quote VALUES (6, 'It’s how you deal with failure that determines how you achieve success.', 'David Feherty');
INSERT INTO quote VALUES (7, 'Frugality includes all the other virtues.', 'Cicero');
INSERT INTO quote VALUES (8, 'I love money. I love everything about it. I bought some pretty good stuff. Got me a $300 pair of socks. Got a fur sink. An electric dog polisher. A gasoline powered turtleneck sweater. And, of course, I bought some dumb stuff, too.', 'Steve Martin');
INSERT INTO quote VALUES (9, 'An investment in knowledge pays the best interest.', 'Benjamin Franklin');
INSERT INTO quote VALUES (10, 'I will tell you the secret to getting rich on Wall Street. You try to be greedy when others are fearful. And you try to be fearful when others are greedy.', 'Warren Buffett');
INSERT INTO quote VALUES (11, 'Annual income twenty pounds, annual expenditure nineteen six, result happiness. Annual income twenty pounds, annual expenditure twenty pound ought and six, result misery.', 'Charles Dickens');
INSERT INTO quote VALUES (12, 'Opportunity is missed by most people because it is dressed in overalls and looks like work.', 'Thomas Edison');
INSERT INTO quote VALUES (13, 'What we really want to do is what we are really meant to do. When we do what we are meant to do, money comes to us, doors open for us, we feel useful, and the work we do feels like play to us.', 'Julia Cameron');
INSERT INTO quote VALUES (14, 'I never attempt to make money on the stock market. I buy on the assumption that they could close the market the next day and not reopen it for ten years.', 'Warren Buffett');
INSERT INTO quote VALUES (15, 'A nickel ain’t worth a dime anymore.', 'Yogi Berra');
INSERT INTO quote VALUES (16, 'Money never made a man happy yet, nor will it. The more a man has, the more he wants. Instead of filling a vacuum, it makes one.', 'Benjamin Franklin');
INSERT INTO quote VALUES (17, 'Many people take no care of their money till they come nearly to the end of it, and others do just the same with their time.', 'Johann Wolfgang von Goethe');
INSERT INTO quote VALUES (18, 'Formal education will make you a living, self-education will make you a fortune.', 'Jim Rohn');
INSERT INTO quote VALUES (19, 'Money is only a tool. It will take you wherever you wish, but it will not replace you as the driver.', 'Ayn Rand');
INSERT INTO quote VALUES (20, 'Financial peace isn’t the acquisition of stuff. It’s learning to live on less than you make, so you can give money back and have money to invest. You can’t win until you do this.', 'Dave Ramsey');
INSERT INTO quote VALUES (21, 'It is not the man who has too little, but the man who craves more, that is poor.', 'Seneca');
INSERT INTO quote VALUES (22, 'It’s not the employer who pays the wages. Employers only handle the money. It’s the customer who pays the wages.', 'Henry Ford');
INSERT INTO quote VALUES (23, 'He who loses money, loses much, He who loses a friend, loses much more, He who loses faith, loses all.', 'Eleanor Roosevelt');
INSERT INTO quote VALUES (24, 'Happiness is not in the mere possession of money, it lies in the joy of achievement, in the thrill of creative effort.', 'Franklin D. Roosevelt');
INSERT INTO quote VALUES (25, 'Empty pockets never held anyone back. Only empty heads and empty hearts can do that.', 'Norman Vincent Peale');
INSERT INTO quote VALUES (26, 'It’s good to have money and the things that money can buy, but it’s good, too, to check up once in a while and make sure that you haven’t lost the things that money can’t buy.', 'George Lorimer');
INSERT INTO quote VALUES (27, 'You can only become truly accomplished at something you love. Don’t make money your goal. Instead, pursue the things you love doing, and then do them so well that people can’t take their eyes off you.', 'Maya Angelou');
INSERT INTO quote VALUES (28, 'Buy when everyone else is selling and hold until everyone else is buying. That’s not just a catchy slogan. It’s the very essence of successful investing.', 'J. Paul Getty');
INSERT INTO quote VALUES (29, 'If money is your hope for independence you will never have it. The only real security that a man will have in this world is a reserve of knowledge, experience, and ability.', 'Henry Ford');
INSERT INTO quote VALUES (30, 'If all the economists were laid end to end, they’d never reach a conclusion.', 'George Bernard Shaw');
INSERT INTO quote VALUES (31, 'How many millionaires do you know who have become wealthy by investing in savings accounts? I rest my case.', 'Robert G. Allen');
INSERT INTO quote VALUES (32, 'I made my money the old-fashioned way. I was very nice to a wealthy relative right before he died.', 'Malcolm Forbes');
INSERT INTO quote VALUES (33, 'Innovation distinguishes between a leader and a follower.', 'Steve Jobs');
INSERT INTO quote VALUES (34, 'The real measure of your wealth is how much you’d be worth if you lost all your money.', 'Anonymous');
INSERT INTO quote VALUES (35, 'Money is a terrible master but an excellent servant.', 'P.T. Barnum');
INSERT INTO quote VALUES (36, 'Try to save something while your salary is small, it’s impossible to save after you begin to earn more.', 'Jack Benny');
INSERT INTO quote VALUES (37, 'Wealth is the ability to fully experience life.', 'Henry David Thoreau');
INSERT INTO quote VALUES (38, 'The individual investor should act consistently as an investor and not as a speculator.', 'Ben Graham');
INSERT INTO quote VALUES (39, 'I’m a great believer in luck, and I find the harder I work the more I have of it.', 'Thomas Jefferson');
INSERT INTO quote VALUES (40, 'You must gain control over your money or the lack of it will forever control you.', 'Dave Ramsey');
INSERT INTO quote VALUES (41, 'Investing should be more like watching paint dry or watching grass grow. If you want excitement, take $800 and go to Las Vegas.', 'Paul Samuelson');
INSERT INTO quote VALUES (42, 'Every time you borrow money, you’re robbing your future self.', 'Nathan W. Morris');
INSERT INTO quote VALUES (43, 'Rich people have small TVs and big libraries, and poor people have small libraries and big TVs.', 'Zig Ziglar');
INSERT INTO quote VALUES (44, 'Never spend your money before you have it.', 'Thomas Jefferson');
INSERT INTO quote VALUES (45, 'The stock market is filled with individuals who know the price of everything, but the value of nothing.', 'Phillip Fisher');
INSERT INTO quote VALUES (46, 'Wealth is not his that has it, but his that enjoys it.', 'Benjamin Franklin');
INSERT INTO quote VALUES (47, 'It’s not how much money you make, but how much money you keep, how hard it works for you, and how many generations you keep it for.', 'Robert Kiyosaki');
INSERT INTO quote VALUES (48, 'I have not failed. I’ve just found 10,000 ways that won’t work.', 'Thomas A. Edison');
INSERT INTO quote VALUES (49, 'If you don’t value your time, neither will others. Stop giving away your time and talents. Value what you know & start charging for it.', 'Kim Garst');
INSERT INTO quote VALUES (50, 'Here’s to the crazy ones. The misfits. The rebels. The troublemakers. The round pegs in the square holes. The ones who see things differently. They’re not fond of rules. And they have no respect for the status quo. You can quote them, disagree with them, glorify or vilify them. About the only thing you can’t do is ignore them. Because they change things. They push the human race forward. And while some may see them as the crazy ones, we see genius. Because the people who are crazy enough to think they can change the world, are the ones who do.', 'Steve Jobs');
INSERT INTO quote VALUES (51, 'The habit of saving is itself an education, it fosters every virtue, teaches self-denial, cultivates the sense of order, trains to forethought, and so broadens the mind.', 'T.T. Munger');
INSERT INTO quote VALUES (52, 'Don’t tell me what you value, show me your budget, and I’ll tell you what you value..', 'Joe Biden');
INSERT INTO quote VALUES (53, 'If you live for having it all, what you have is never enough.', 'Vicki Robin');
INSERT INTO quote VALUES (54, 'Before you speak, listen. Before you write, think. Before you spend, earn. Before you invest, investigate. Before you criticize, wait. Before you pray, forgive. Before you quit, try. Before you retire, save. Before you die, give.', 'William A. Ward');
INSERT INTO quote VALUES (55, 'We make a living by what we get, but we make a life by what we give.', 'Winston Churchill');
INSERT INTO quote VALUES (56, 'Wealth after all is a relative thing since he that has little and wants less is richer than he that has much and wants more.', 'Charles Caleb Colton');
INSERT INTO quote VALUES (57, 'Not everything that can be counted counts, and not everything that counts can be counted.', 'Albert Einstein');
INSERT INTO quote VALUES (58, 'It is time for us to stand and cheer for the doer, the achiever, the one who recognizes the challenge and does something about it.', 'Vince Lombardi');
INSERT INTO quote VALUES (59, 'It’s not the situation, but whether we react (negative) or respond (positive) to the situation that’s important.', 'Zig Ziglar');
INSERT INTO quote VALUES (60, 'A successful man is one who can lay a firm foundation with the bricks others have thrown at him.', 'David Brinkley');
INSERT INTO quote VALUES (61, 'Let him who would enjoy a good future waste none of his present.', 'Roger Babson');
INSERT INTO quote VALUES (62, 'Courage is being scared to death, but saddling up anyway.', 'John Wayne');
INSERT INTO quote VALUES (63, 'Live as if you were to die tomorrow. Learn as if you were to live forever.', 'Mahatma Gandhi');
INSERT INTO quote VALUES (64, 'Twenty years from now you will be more disappointed by the things that you didn’t do than by the ones you did do.', 'Mark Twain');
INSERT INTO quote VALUES (65, 'It is our choices, that show what we truly are, far more than our abilities.', 'J. K Rowling');
INSERT INTO quote VALUES (66, 'The successful warrior is the average man, with laser-like focus.', 'Bruce Lee');
INSERT INTO quote VALUES (67, 'Develop success from failures. Discouragement and failure are two of the surest stepping stones to success.', 'Dale Carnegie');
INSERT INTO quote VALUES (68, 'The question isn’t who is going to let me, it’s who is going to stop me.', 'Ayn Rand');
INSERT INTO quote VALUES (69, 'Don’t let the fear of losing be greater than the excitement of winning.', 'Robert Kiyosaki');
INSERT INTO quote VALUES (70, 'You can’t connect the dots looking forward, you can only connect them looking backwards. So you have to trust that the dots will somehow connect in your future. You have to trust in something – your gut, destiny, life, karma, whatever. This approach has never let me down, and it has made all the difference in my life.future. You have to trust in somethin.', 'Steve Jobs');
INSERT INTO quote VALUES (71, 'Let no feeling of discouragement prey upon you, and in the end you are sure to succeed.', 'Abraham Lincoln');
INSERT INTO quote VALUES (72, 'Screw it, Let’s do it.', 'Richard Branson');
INSERT INTO quote VALUES (73, 'If your ship doesn’t come in, swim out to meet it.', 'Jonathan Winters');
INSERT INTO quote VALUES (74, 'People often say that motivation doesn’t last. Well, neither does bathing – that’s why we recommend it daily. ', 'Zig Ziglar');
INSERT INTO quote VALUES (75, 'A real entrepreneur is somebody who has no safety net underneath them.', 'Henry Kravis');
INSERT INTO quote VALUES (76, 'As long as you’re going to be thinking anyway, think big.', 'Donald Trump');
INSERT INTO quote VALUES (77, 'The only place where success comes before work is in the dictionary.', 'Vidal Sassoon');
INSERT INTO quote VALUES (78, 'Success is walking from failure to failure with no loss of enthusiasm.', 'Winston Churchill');
INSERT INTO quote VALUES (79, 'Without continual growth and progress, such words as improvement, achievement, and success have no meaning.', 'Benjamin Franklin');
INSERT INTO quote VALUES (80, 'If plan A fails, remember there are 25 more letters.', 'Chris Guillebeau');
INSERT INTO quote VALUES (81, 'Do not go where the path may lead, go instead where there is no path and leave a trail.', 'Ralph Waldo Emerson');
INSERT INTO quote VALUES (82, 'A journey of a thousand miles must begin with a single step.', 'Lao Tzu');
INSERT INTO quote VALUES (83, 'Do the one thing you think you cannot do. Fail at it. Try again. Do better the second time. The only people who never tumble are those who never mount the high wire. This is your moment. Own it.', 'Oprah Winfrey');
INSERT INTO quote VALUES (84, 'Believe you can and you’re halfway there.', 'Theodore Roosevelt');
INSERT INTO quote VALUES (85, 'The Stock Market is designed to transfer money from the Active to the Patient.', 'Warren Buffett');
INSERT INTO quote VALUES (86, 'I’m only rich because I know when I’m wrong…I basically have survived by recognizing my mistakes.', 'George Soros');
INSERT INTO quote VALUES (87, 'Persist – don’t take no for an answer. If you’re happy to sit at your desk and not take any risk, you’ll be sitting at your desk for the next 20 years.', 'David Rubenstein');
INSERT INTO quote VALUES (88, 'If you took our top fifteen decisions out, we’d have a pretty average record. It wasn’t hyperactivity, but a hell of a lot of patience. You stuck to your principles and when opportunities came along, you pounced on them with vigor.', 'Charlie Munger');
INSERT INTO quote VALUES (89, 'When buying shares, ask yourself, would you buy the whole company.', 'Rene Rivkin');
INSERT INTO quote VALUES (90, 'If you have trouble imagining a 20% loss in the stock market, you shouldn’t be in stocks.', 'John Bogle');
INSERT INTO quote VALUES (91, 'My old father used to have a saying:  If you make a bad bargain, hug it all the tighter.', 'Abraham Lincoln');
INSERT INTO quote VALUES (92, 'It takes as much energy to wish as it does to plan.', 'Eleanor Roosevelt');
INSERT INTO quote VALUES (93, 'The four most expensive words in the English language are, ‘This time it’s different..', 'Sir John Templeton');
INSERT INTO quote VALUES (94, 'I’d like to live as a poor man with lots of money.', 'Pablo Picasso');
INSERT INTO quote VALUES (95, 'Fortune sides with him who dares.', 'Virgil');
INSERT INTO quote VALUES (96, 'Wealth is like sea-water, the more we drink, the thirstier we become, and the same is true of fame.', 'Arthur Schopenhauer');
INSERT INTO quote VALUES (97, 'If we command our wealth, we shall be rich and free. If our wealth commands us, we are poor indeed.', 'Edmund Burke');
INSERT INTO quote VALUES (98, 'No wealth can ever make a bad man at peace with himself.', 'Plato');
INSERT INTO quote VALUES (99, 'My formula for success is rise early, work late and strike oil.', 'JP Getty');
INSERT INTO quote VALUES (100, 'The best thing money can buy is financial freedom.', 'Me');



# --- !Downs
DROP TABLE "quote"
