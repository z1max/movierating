-- -----------------------------------------------------
-- Clear schema `movie_rating`
-- -----------------------------------------------------
SET FOREIGN_KEY_CHECKS=0;
TRUNCATE TABLE `movie_rating`.`rating`;
TRUNCATE TABLE `movie_rating`.`user`;
TRUNCATE TABLE `movie_rating`.`user_role`;
TRUNCATE TABLE `movie_rating`.`review`;
TRUNCATE TABLE `movie_rating`.`genre`;
TRUNCATE TABLE `movie_rating`.`country`;
TRUNCATE TABLE `movie_rating`.`movie`;
SET FOREIGN_KEY_CHECKS=1;

-- -----------------------------------------------------
-- Data for table `movie_rating`.`user`
-- -----------------------------------------------------
START TRANSACTION;
USE `movie_rating`;
INSERT INTO `movie_rating`.`user` (`id`, `username`, `email`, `password`, `registered`, `enabled`, `points`) VALUES (1, 'admin', 'admin@gmail.com', '713bfda78870bf9d1b261f565286f85e97ee614efe5f0faf7c34e7ca4f65baca', '2018-06-10', 1, 10);
INSERT INTO `movie_rating`.`user` (`id`, `username`, `email`, `password`, `registered`, `enabled`, `points`) VALUES (2, 'valera', 'valera@yandex.ru', '4bb6db7e55593590fc92506ef021dbe671d87fb0de1390a23d9672a7d1e7d7f5', '2018-06-11', 1, 32);
INSERT INTO `movie_rating`.`user` (`id`, `username`, `email`, `password`, `registered`, `enabled`, `points`) VALUES (3, 'sobakez', 'sobakez@gmail.com', 'f68b67dd51c8d06677324d21ee9005b9c809c6ddfc12e5b67c9353eed4ab54f4', '2018-06-12', 1, 60);
INSERT INTO `movie_rating`.`user` (`id`, `username`, `email`, `password`, `registered`, `enabled`, `points`) VALUES (4, 'tomimt', 'tomimt@hotmail.com', '5eef1353ac5c84b464f1eed1de4f322661a15316a9c88f7c688c354284a91d3e', '2018-06-12', 1, 27);
INSERT INTO `movie_rating`.`user` (`id`, `username`, `email`, `password`, `registered`, `enabled`, `points`) VALUES (5, 'Sam', 'sam@gmail.com', 'd4daffdfad7c75260eeaa52d1221e2e494b9a42650728fd110a70ac952d8974f', '2018-06-13', 1, 16);

COMMIT;


-- -----------------------------------------------------
-- Data for table `movie_rating`.`user_role`
-- -----------------------------------------------------
START TRANSACTION;
USE `movie_rating`;
INSERT INTO `movie_rating`.`user_role` (`user_id`, `role`) VALUES (1, 0);
INSERT INTO `movie_rating`.`user_role` (`user_id`, `role`) VALUES (1, 1);
INSERT INTO `movie_rating`.`user_role` (`user_id`, `role`) VALUES (2, 1);
INSERT INTO `movie_rating`.`user_role` (`user_id`, `role`) VALUES (3, 1);
INSERT INTO `movie_rating`.`user_role` (`user_id`, `role`) VALUES (4, 1);
INSERT INTO `movie_rating`.`user_role` (`user_id`, `role`) VALUES (5, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `movie_rating`.`movie`
-- -----------------------------------------------------
START TRANSACTION;
USE `movie_rating`;
INSERT INTO `movie_rating`.`movie` (`id`, `title`, `director`, `release_date`, `budget`, `description`, `runtime`) VALUES (1, 'Jurassic World: Fallen Kingdom', 'J.A. Bayona ', '2018-06-06', 170000000, 'Four years after the Jurassic World theme park was closed down, Owen and Claire return to Isla Nublar to save the dinosaurs when they learn that a once dormant volcano on the island is active and is threatening to extinguish all life there. Along the way, Owen sets out to find Blue, his lead raptor, and discovers a conspiracy that could disrupt the natural order of the entire planet. Life has found a way, again.', 128);
INSERT INTO `movie_rating`.`movie` (`id`, `title`, `director`, `release_date`, `budget`, `description`, `runtime`) VALUES (2, 'Deadpool 2', 'David Leitch', '2018-05-15', 110000000, 'After surviving a near fatal bovine attack, a disfigured cafeteria chef (Wade Wilson) struggles to fulfill his dream of becoming Mayberry\'s hottest bartender while also learning to cope with his lost sense of taste. Searching to regain his spice for life, as well as a flux capacitor, Wade must battle ninjas, the Yakuza, and a pack of sexually aggressive canines, as he journeys around the world to discover the importance of family, friendship, and flavor - finding a new taste for adventure and earning the coveted coffee mug title of World\'s Best Lover.', 119);
INSERT INTO `movie_rating`.`movie` (`id`, `title`, `director`, `release_date`, `budget`, `description`, `runtime`) VALUES (3, 'Deadpool', 'Tim Miller', '2018-02-10', 58000000, 'This is the origin story of former Special Forces operative turned mercenary Wade Wilson, who after being subjected to a rogue experiment that leaves him with accelerated healing powers, adopts the alter ego Deadpool. Armed with his new abilities and a dark, twisted sense of humor, Deadpool hunts down the man who nearly destroyed his life.', 108);
INSERT INTO `movie_rating`.`movie` (`id`, `title`, `director`, `release_date`, `budget`, `description`, `runtime`) VALUES (4, 'Jurassic World', 'Colin Trevorrow', '2015-06-11', 150000000, '22 years after the original Jurassic Park failed, the new park (also known as Jurassic World) is open for business. After years of studying genetics the scientists on the park genetically engineer a new breed of dinosaur. When everything goes horribly wrong, will our heroes make it off the island?', 124);
INSERT INTO `movie_rating`.`movie` (`id`, `title`, `director`, `release_date`, `budget`, `description`, `runtime`) VALUES (5, 'Fight Club', 'David Fincher', '1999-11-12', 63000000, 'A nameless first person narrator (Edward Norton) attends support groups in attempt to subdue his emotional state and relieve his insomniac state. When he meets Marla (Helena Bonham Carter), another fake attendee of support groups, his life seems to become a little more bearable. However when he associates himself with Tyler (Brad Pitt) he is dragged into an underground fight club and soap making scheme. Together the two men spiral out of control and engage in competitive rivalry for love and power. When the narrator is exposed to the hidden agenda of Tyler\'s fight club, he must accept the awful truth that Tyler may not be who he says he is.', 139);
INSERT INTO `movie_rating`.`movie` (`id`, `title`, `director`, `release_date`, `budget`, `description`, `runtime`) VALUES (6, 'Forrest Gump', 'Robert Zemeckis', '1994-10-7', 55000000, ' Forrest Gump is a simple man with a low I.Q. but good intentions. He is running through childhood with his best and only friend Jenny. His \'mama\' teaches him the ways of life and leaves him to choose his destiny. Forrest joins the army for service in Vietnam, finding new friends called Dan and Bubba, he wins medals, creates a famous shrimp fishing fleet, inspires people to jog, starts a ping-pong craze, creates the smiley, writes bumper stickers and songs, donates to people and meets the president several times. However, this is all irrelevant to Forrest who can only think of his childhood sweetheart Jenny Curran, who has messed up her life. Although in the end all he wants to prove is that anyone can love anyone. ', 142);
INSERT INTO `movie_rating`.`movie` (`id`, `title`, `director`, `release_date`, `budget`, `description`, `runtime`) VALUES (7, 'The Matrix', 'Lana Wachowski', '1999-06-11', 63000000, 'Thomas A. Anderson is a man living two lives. By day he is an average computer programmer and by night a hacker known as Neo. Neo has always questioned his reality, but the truth is far beyond his imagination. Neo finds himself targeted by the police when he is contacted by Morpheus, a legendary computer hacker branded a terrorist by the government. Morpheus awakens Neo to the real world, a ravaged wasteland where most of humanity have been captured by a race of machines that live off of the humans\' body heat and electrochemical energy and who imprison their minds within an artificial reality known as the Matrix. As a rebel against the machines, Neo must return to the Matrix and confront the agents: super-powerful computer programs devoted to snuffing out Neo and the entire human rebellion.', 136);
INSERT INTO `movie_rating`.`movie` (`id`, `title`, `director`, `release_date`, `budget`, `description`, `runtime`) VALUES (8, 'The Matrix Reloaded', 'Lana Wachowski', '2003-05-21', 150000000, 'Six months after the events depicted in The Matrix, Neo has proved to be a good omen for the free humans, as more and more humans are being freed from the matrix and brought to Zion, the one and only stronghold of the Resistance. Neo himself has discovered his superpowers including super speed, ability to see the codes of the things inside the matrix, and a certain degree of precognition. But a nasty piece of news hits the human resistance: 250,000 machine sentinels are digging to Zion and would reach them in 72 hours. As Zion prepares for the ultimate war, Neo, Morpheus and Trinity are advised by the Oracle to find the Keymaker who would help them reach the Source. Meanwhile Neo\'s recurrent dreams depicting Trinity\'s death have got him worried and as if it was not enough, Agent Smith has somehow escaped deletion, has become more powerful than before and has chosen Neo as his next target.', 138);
INSERT INTO `movie_rating`.`movie` (`id`, `title`, `director`, `release_date`, `budget`, `description`, `runtime`) VALUES (9, 'The Matrix Revolutions', 'Lana Wachowski', '2003-11-05', 150000000, 'Neo discovers that somehow he is able to use his powers in the real world too and that his mind can be freed from his body, as a result of which he finds himself trapped on a train station between the Matrix and the Real World. Meanwhile, Zion is preparing for the oncoming war with the machines with very little chances of survival. Neo\'s associates set out to free him from The Merovingian since it\'s believed that he is the One who will end the war between humans and the machines. What they do not know is that there is a threat from a third party, someone who has plans to destroy both the worlds.', 129);
INSERT INTO `movie_rating`.`movie` (`id`, `title`, `director`, `release_date`, `budget`, `description`, `runtime`) VALUES (10, 'City of God', 'Fernando Meirelles', '2003-01-03', 3300000, 'Brazil, 1960s, City of God. The Tender Trio robs motels and gas trucks. Younger kids watch and learn well...too well. 1970s: Li\'l Zé has prospered very well and owns the city. He causes violence and fear as he wipes out rival gangs without mercy. His best friend Bené is the only one to keep him on the good side of sanity. Rocket has watched these two gain power for years, and he wants no part of it. Yet he keeps getting swept up in the madness. All he wants to do is take pictures. 1980s: Things are out of control between the last two remaining gangs...will it ever end? Welcome to the City of God.', 130);
INSERT INTO `movie_rating`.`movie` (`id`, `title`, `director`, `release_date`, `budget`, `description`, `runtime`) VALUES (11, 'American History X', 'Tony Kaye', '1999-03-26', 7500000, 'Derek Vineyard is paroled after serving 3 years in prison for brutally killing two black men who tried to break into/steal his truck. Through his brother, Danny Vineyard\'s narration, we learn that before going to prison, Derek was a skinhead and the leader of a violent white supremacist gang that committed acts of racial crime throughout L.A. and his actions greatly influenced Danny. Reformed and fresh out of prison, Derek severs contact with the gang and becomes determined to keep Danny from going down the same violent path as he did.', 119);
INSERT INTO `movie_rating`.`movie` (`id`, `title`, `director`, `release_date`, `budget`, `description`, `runtime`) VALUES (12, 'Star Wars: Episode I - The Phantom Menace', 'George Lucas', '1999-07-15', 115000000, 'The evil Trade Federation, led by Nute Gunray is planning to take over the peaceful world of Naboo. Jedi Knights Qui-Gon Jinn and Obi-Wan Kenobi are sent to confront the leaders. But not everything goes to plan. The two Jedi escape, and along with their new Gungan friend, Jar Jar Binks head to Naboo to warn Queen Amidala, but droids have already started to capture Naboo and the Queen is not safe there. Eventually, they land on Tatooine, where they become friends with a young boy known as Anakin Skywalker. Qui-Gon is curious about the boy, and sees a bright future for him. The group must now find a way of getting to Coruscant and to finally solve this trade dispute, but there is someone else hiding in the shadows. Are the Sith really extinct? Is the Queen really who she says she is? And what\'s so special about this young boy?', 136);
INSERT INTO `movie_rating`.`movie` (`id`, `title`, `director`, `release_date`, `budget`, `description`, `runtime`) VALUES (13, 'Star Wars: Episode II - Attack of the Clones', 'George Lucas', '2002-05-16', 115000000, 'Ten years after the invasion of Naboo, the Galactic Republic is facing a Separatist movement and the former queen and now Senator Padmé Amidala travels to Coruscant to vote on a project to create an army to help the Jedi to protect the Republic. Upon arrival, she escapes from an attempt to kill her, and Obi-Wan Kenobi and his Padawan Anakin Skywalker are assigned to protect her. They chase the shape-shifter Zam Wessell but she is killed by a poisoned dart before revealing who hired her. The Jedi Council assigns Obi-Wan Kenobi to discover who has tried to kill Amidala and Anakin to protect her in Naboo. Obi-Wan discovers that the dart is from the planet Kamino, and he heads to the remote planet. He finds an army of clones that has been under production for years for the Republic and that the bounty hunter Jango Fett was the matrix for the clones. Meanwhile Anakin and Amidala fall in love with each other, and he has nightmarish visions of his mother. They travel to his home planet, ...', 142);
INSERT INTO `movie_rating`.`movie` (`id`, `title`, `director`, `release_date`, `budget`, `description`, `runtime`) VALUES (14, 'Star Wars: Episode III - Revenge of the Sith', 'George Lucas', '2005-05-19', 113000000, 'In Coruscant, the Jedi Obi-Wan Kenobi and Anakin Skywalker rescue the Supreme Chancellor Palpatine from the Separatist General Grievous\' spaceship and Anakin kills Count Dooku with his light-saber after a fight; however Grievous escapes from the Jedi. When they land on Coruscant, Padmé Amidala comes to tell Anakin that she is pregnant. Soon he has premonitions of his wife dying during the delivery. Palpatine requests that Anakin join the Jedi Council against the will of the members but he is not promoted to Master and stays at the rank of Knight; further they ask him to spy on Palpatine. Anakin is manipulated by Palpatine about the true intentions of the Jedi and is tempted to know the dark side of the Force that could be capable of saving Padmé. Further Palpatine discloses that he is Sith Lord Darth Sidious. What will Anakin do?', 140);
INSERT INTO `movie_rating`.`movie` (`id`, `title`, `director`, `release_date`, `budget`, `description`, `runtime`) VALUES (15, 'Star Wars: Episode IV - A New Hope', 'George Lucas', '1978-01-29', 11000000, 'The Imperial Forces, under orders from cruel Darth Vader, hold Princess Leia hostage in their efforts to quell the rebellion against the Galactic Empire. Luke Skywalker and Han Solo, captain of the Millennium Falcon, work together with the companionable droid duo R2-D2 and C-3PO to rescue the beautiful princess, help the Rebel Alliance and restore freedom and justice to the Galaxy.', 121);
INSERT INTO `movie_rating`.`movie` (`id`, `title`, `director`, `release_date`, `budget`, `description`, `runtime`) VALUES (16, 'Star Wars: Episode V - The Empire Strikes Back', 'Irvin Kershner', '1980-05-21', 18000000, 'Luke Skywalker, Han Solo, Princess Leia and Chewbacca face attack by the Imperial forces and its AT-AT walkers on the ice planet Hoth. While Han and Leia escape in the Millennium Falcon, Luke travels to Dagobah in search of Yoda. Only with the Jedi master\'s help will Luke survive when the dark side of the Force beckons him into the ultimate duel with Darth Vader.', 124);
INSERT INTO `movie_rating`.`movie` (`id`, `title`, `director`, `release_date`, `budget`, `description`, `runtime`) VALUES (17, 'Star Wars: Episode VI - Return of the Jedi', 'Richard Marquand', '1983-06-02', 32500000, 'Luke Skywalker battles horrible Jabba the Hut and cruel Darth Vader to save his comrades in the Rebel Alliance and triumph over the Galactic Empire. Han Solo and Princess Leia reaffirm their love and team with Chewbacca, Lando Calrissian, the Ewoks and the androids C-3PO and R2-D2 to aid in the disruption of the Dark Side and the defeat of the evil emperor.', 131);
INSERT INTO `movie_rating`.`movie` (`id`, `title`, `director`, `release_date`, `budget`, `description`, `runtime`) VALUES (18, 'Snatch', 'Guy Ritchie', '2000-09-01', 6000000, 'Turkish and his close friend/accomplice Tommy get pulled into the world of match fixing by the notorious Brick Top. Things get complicated when the boxer they had lined up gets badly beaten by Mickey, a \'pikey\' ( slang for an Irish Gypsy)- who comes into the equation after Turkish, an unlicensed boxing promoter wants to buy a caravan off the Irish Gypsies. They then try to convince Mickey not only to fight for them, but to lose for them too. Whilst all this is going on, a huge diamond heist takes place, and a fistful of motley characters enter the story, including \'Cousin Avi\', \'Boris The Blade\', \'Franky Four Fingers\' and \'Bullet Tooth Tony\'. Things go from bad to worse as it all becomes about the money, the guns, and the damned dog.', 104);
INSERT INTO `movie_rating`.`movie` (`id`, `title`, `director`, `release_date`, `budget`, `description`, `runtime`) VALUES (19, 'RocknRolla', 'Guy Ritchie', '2008-09-05', 18000000, 'Lenny Cole, a London mob boss, puts the bite on all local real estate transactions. For substantial fees, he\'s helping Uri Omovich, a Russian developer. As a sign of good faith, Omovich loans Cole a valuable painting, promptly stolen off Cole\'s wall. While Cole\'s men, led by the dependable Archie, look for the canvas, three local petty criminals, the Wild Bunch, steal money from the Russian using inside information from his accountant, the lovely Stella. Meanwhile, a local drug-addled rocker, Johnny Quid, is reported drowned, and his connection to Cole is the key to unraveling the deceits and double crosses of life in the underworld.', 114);
INSERT INTO `movie_rating`.`movie` (`id`, `title`, `director`, `release_date`, `budget`, `description`, `runtime`) VALUES (20, 'The Fifth Element', 'Luc Besson', '1997-06-06', 93000000, 'In the twenty-third century, the universe is threatened by evil. The only hope for mankind is the Fifth Element, who comes to Earth every five thousand years to protect the humans with four stones of the four elements: fire, water, Earth and air. A Mondoshawan spacecraft is bringing The Fifth Element back to Earth but it is destroyed by the evil Mangalores. However, a team of scientists use the DNA of the remains of the Fifth Element to rebuild the perfect being called Leeloo. She escapes from the laboratory and stumbles upon the taxi driver and former elite commando Major Korben Dallas that helps her to escape from the police. Leeloo tells him that she must meet Father Vito Cornelius to accomplish her mission. Meanwhile, the Evil uses the greedy and cruel Jean-Baptiste Emanuel Zorg and a team of mercenary Mangalores to retrieve the stones and avoid the protection of Leeloo. But the skilled Korben Dallas has fallen in love with Leeloo and decides to help her to retrieve the stones.', 126);
INSERT INTO `movie_rating`.`movie` (`id`, `title`, `director`, `release_date`, `budget`, `description`, `runtime`) VALUES (21, 'Valerian and the City of a Thousand Planets', 'Luc Besson', '2017-08-02', 177200000, 'In the Century XXVIII, the space station Alpha is a city where beings from different planets live together exchanging their knowledge and culture. Peace is granted by a human force, including Major Valerian and his partner Sergeant Laureline. They are assigned by the Defence Minister to retrieve the last species of converter in a dangerous mission. They succeed and back to Alpha, unknown humanoids abduct Commander Arun Filitt expecting to steal the converter. They head to a forbidden area that is infected but Valerian and Laureline follow them and disclose a hidden secret about the race and the infected area.', 137);
INSERT INTO `movie_rating`.`movie` (`id`, `title`, `director`, `release_date`, `budget`, `description`, `runtime`) VALUES (22, 'The Lord of the Rings: The Fellowship of the Ring', 'Peter Jackson', '2001-12-19', 93000000, 'In ancient Ring thought lost for centuries has been found, and through a strange twist in fate has been given to a small Hobbit named Frodo. When Gandalf discovers the Ring is in fact the One Ring of the Dark Lord Sauron, Frodo must make an epic quest to the Cracks of Doom in order to destroy it! However he does not go alone. He is joined by Gandalf, Legolas the elf, Gimli the Dwarf, Aragorn, Boromir and his three Hobbit friends Merry, Pippin and Samwise. Through mountains, snow, darkness, forests, rivers and plains, facing evil and danger at every corner the Fellowship of the Ring must go. Their quest to destroy the One Ring is the only hope for the end of the Dark Lords reign!', 178);
INSERT INTO `movie_rating`.`movie` (`id`, `title`, `director`, `release_date`, `budget`, `description`, `runtime`) VALUES (23, 'The Lord of the Rings: The Two Towers', 'Peter Jackson', '2002-12-18', 94000000, 'The continuing quest of Frodo and the Fellowship to destroy the One Ring. Frodo and Sam discover they are being followed by the mysterious Gollum. Aragorn, the Elf archer Legolas and Gimli the Dwarf encounter the besieged Rohan kingdom, whose once great King Theoden has fallen under Saruman\'s deadly spell.', 179);
INSERT INTO `movie_rating`.`movie` (`id`, `title`, `director`, `release_date`, `budget`, `description`, `runtime`) VALUES (24, 'The Lord of the Rings: The Return of the King', 'Peter Jackson', '2003-12-17', 94000000, 'The final confrontation between the forces of good and evil fighting for control of the future of Middle-earth. Hobbits: Frodo and Sam reach Mordor in their quest to destroy the \"one ring\", while Aragorn leads the forces of good against Sauron\'s evil army at the stone city of Minas Tirith.', 201);
INSERT INTO `movie_rating`.`movie` (`id`, `title`, `director`, `release_date`, `budget`, `description`, `runtime`) VALUES (25, 'Gangs of New York', 'Martin Scorsese', '2003-01-09', 100000000, 'In the god-forsaken district of early-1860\'s Lower Manhattan known as the Five Points, the vicious Nativist, Bill \"The Butcher\" Cutting, is the supreme overlord of an area riddled with crime, prostitution, theft and murder, as the American Civil War still rages on. Sixteen whole years after the brutal murder of his father from Bill\'s blood-stained hands, an orphaned Irish-American, Amsterdam Vallon, returns to this melting pot of corruption to avenge his untimely death; however, a lot has changed since then. Who can remember the once innocent boy and now a young man bent on revenge, who works his way up to the hierarchy of Five Points? Will Amsterdam ever taste the dangerous but sweet fruit of retribution?', 167);
INSERT INTO `movie_rating`.`movie` (`id`, `title`, `director`, `release_date`, `budget`, `description`, `runtime`) VALUES (26, 'The Departed', 'Martin Scorsese', '2006-10-06', 90000000, 'In South Boston, the state police force is waging war on Irish-American organized crime. Young undercover cop Billy Costigan is assigned to infiltrate the mob syndicate run by gangland chief Frank Costello. While Billy quickly gains Costello\'s confidence, Colin Sullivan, a hardened young criminal who has infiltrated the state police as an informer for the syndicate is rising to a position of power in the Special Investigation Unit. Each man becomes deeply consumed by their double lives, gathering information about the plans and counter-plans of the operations they have penetrated. But when it becomes clear to both the mob and the police that there is a mole in their midst, Billy and Colin are suddenly in danger of being caught and exposed to the enemy - and each must race to uncover the identity of the other man in time to save themselves. But is either willing to turn on their friends and comrades they\'ve made during their long stints undercover?', 151);
INSERT INTO `movie_rating`.`movie` (`id`, `title`, `director`, `release_date`, `budget`, `description`, `runtime`) VALUES (27, 'Jaws', 'Steven Spielberg', '1975-12-26', 8000000, 'It\'s a hot summer on Amity Island, a small community whose main business is its beaches. When new Sheriff Martin Brody discovers the remains of a shark attack victim, his first inclination is to close the beaches to swimmers. This doesn\'t sit well with Mayor Larry Vaughn and several of the local businessmen. Brody backs down to his regret as that weekend a young boy is killed by the predator. The dead boy\'s mother puts out a bounty on the shark and Amity is soon swamped with amateur hunters and fisherman hoping to cash in on the reward. A local fisherman with much experience hunting sharks, Quint, offers to hunt down the creature for a hefty fee. Soon Quint, Brody and Matt Hooper from the Oceanographic Institute are at sea hunting the Great White shark. As Brody succinctly surmises after their first encounter with the creature, they\'re going to need a bigger boat.', 124);
INSERT INTO `movie_rating`.`movie` (`id`, `title`, `director`, `release_date`, `budget`, `description`, `runtime`) VALUES (28, 'Saving Private Ryan', 'Steven Spielberg', '1998-09-11', 70000000, 'Opening with the Allied invasion of Normandy on 6 June 1944, members of the 2nd Ranger Battalion under Cpt. Miller fight ashore to secure a beachhead. Amidst the fighting, two brothers are killed in action. Earlier in New Guinea, a third brother is KIA. Their mother, Mrs. Ryan, is to receive all three of the grave telegrams on the same day. The United States Army Chief of Staff, George C. Marshall, is given an opportunity to alleviate some of her grief when he learns of a fourth brother, Private James Ryan, and decides to send out 8 men (Cpt. Miller and select members from 2nd Rangers) to find him and bring him back home to his mother...', 169);
INSERT INTO `movie_rating`.`movie` (`id`, `title`, `director`, `release_date`, `budget`, `description`, `runtime`) VALUES (29, 'Ready Player One', 'Steven Spielberg', '2018-03-28', 175000000, 'In the year 2045, the real world is a harsh place. The only time Wade Watts (Tye Sheridan) truly feels alive is when he escapes to the OASIS, an immersive virtual universe where most of humanity spends their days. In the OASIS, you can go anywhere, do anything, be anyone-the only limits are your own imagination. The OASIS was created by the brilliant and eccentric James Halliday (Mark Rylance), who left his immense fortune and total control of the Oasis to the winner of a three-part contest he designed to find a worthy heir. When Wade conquers the first challenge of the reality-bending treasure hunt, he and his friends-aka the High Five-are hurled into a fantastical universe of discovery and danger to save the OASIS.', 140);
INSERT INTO `movie_rating`.`movie` (`id`, `title`, `director`, `release_date`, `budget`, `description`, `runtime`) VALUES (30, 'Sleepy Hollow', 'Tim Burton', '2000-01-07', 100000000, 'The curse of the headless horseman is the legacy of the small town of Sleepy Hollow. Spearheaded by the eager Constable Ichabod Crane and his new world ways into the quagmire of secrets and murder, secrets once laid to rest, best forgotten and now reawakened, and he too, holding a dark secret of a past once gone.', 105);
INSERT INTO `movie_rating`.`movie` (`id`, `title`, `director`, `release_date`, `budget`, `description`, `runtime`) VALUES (31, 'How to Train Your Dragon', 'Dean DeBlois', '2010-03-31', 165000000, 'Long ago up North on the Island of Berk, the young Viking, Hiccup, wants to join his town\'s fight against the dragons that continually raid their town. However, his macho father and village leader, Stoik the Vast, will not allow his small, clumsy, but inventive son to do so. Regardless, Hiccup ventures out into battle and downs a mysterious Night Fury dragon with his invention, but can\'t bring himself to kill it. Instead, Hiccup and the dragon, whom he dubs Toothless, begin a friendship that would open up both their worlds as the observant boy learns that his people have misjudged the species. But even as the two each take flight in their own way, they find that they must fight the destructive ignorance plaguing their world.', 98);
INSERT INTO `movie_rating`.`movie` (`id`, `title`, `director`, `release_date`, `budget`, `description`, `runtime`) VALUES (32, 'Monsters, Inc.', 'Pete Docter', '2002-02-08', 115000000, 'A city of monsters with no humans called Monstropolis centers around the city\'s power company, Monsters, Inc. The lovable, confident, tough, furry blue behemoth-like giant monster named James P. Sullivan (better known as Sulley) and his wisecracking best friend, short, green cyclops monster Mike Wazowski, discover what happens when the real world interacts with theirs in the form of a 2-year-old baby girl dubbed \"Boo,\" who accidentally sneaks into the monster world with Sulley one night. And now it\'s up to Sulley and Mike to send Boo back in her door before anybody finds out, especially two evil villains such as Sulley\'s main rival as a scarer, chameleon-like Randall (a monster that Boo is very afraid of), who possesses the ability to change the color of his skin, and Mike and Sulley\'s boss Mr. Waternoose, the chairman and chief executive officer of Monsters, Inc.', 92);
INSERT INTO `movie_rating`.`movie` (`id`, `title`, `director`, `release_date`, `budget`, `description`, `runtime`) VALUES (33, 'Darkest Hour', 'Joe Wright', '2018-01-12', 30000000, 'During World War II, as Adolf Hitler\'s awesomely powerful Wehrmacht rampages across Europe, the Prime Minister of the United Kingdom, Neville Chamberlain, is forced to resign, recommending Winston Churchill as his replacement. But even in his early days as the country\'s leader, Churchill is under pressure to commence peace negotiations with the German dictator or to fight head-on the seemingly invincible Nazi regime, whatever the cost. However difficult and dangerous his decision may be, Churchill has no choice but to shine in the country\'s darkest hour.', 125);
INSERT INTO `movie_rating`.`movie` (`id`, `title`, `director`, `release_date`, `budget`, `description`, `runtime`) VALUES (34, 'I, Tonya', 'Craig Gillespie', '2018-02-23', 11000000, 'From the proverbial wrong side of the tracks in Portland, Oregon, former competitive figure skater Tonya Harding was never fully accepted in the figure skating community for not inherently being the image of grace, breeding and privilege that the community wanted to portray, despite she being naturally gifted in the sport athletically. Despite ultimately garnering some success in figure skating being national champion, a world championship medalist, an Olympian, and being the first American woman to complete a Triple Axel in competition, she is arguably best known for her association to \"the incident\": the leg bashing on January 6, 1994 of her competitor, Nancy Kerrigan, who, unlike Tonya, was everything that the figure skating community wanted in their representatives. Her association to that incident led to Tonya being banned from competitive figure skating for life. Tonya\'s story from the beginning of her figure skating life at age four to the aftermath of the incident is presented...', 120);
INSERT INTO `movie_rating`.`movie` (`id`, `title`, `director`, `release_date`, `budget`, `description`, `runtime`) VALUES (35, 'Django Unchained', 'Quentin Tarantino', '2013-01-18', 100000000, 'In 1858, a bounty hunter named Schultz seeks out a slave named Django and buys him because he needs him to find some men he is looking for. After finding them, Django wants to find his wife, Brunhilde, who along with him were sold separately by his former owner for trying to escape. Schultz offers to help him if he chooses to stay with him and be his partner. Eventually they learn that she was sold to a plantation in Mississipi. Knowing they can\'t just go in and say they want her, they come up with a plan so that the owner will welcome them into his home and they can find a way.', 165);
INSERT INTO `movie_rating`.`movie` (`id`, `title`, `director`, `release_date`, `budget`, `description`, `runtime`) VALUES (36, 'The Hateful Eight', 'Quentin Tarantino', '2016-01-08', 44000000, ' Some time after the Civil War, a stagecoach hurtles through the wintry Wyoming landscape. Bounty hunter John Ruth and his fugitive captive Daisy Domergue race towards the town of Red Rock, where Ruth will bring Daisy to justice. Along the road, they encounter Major Marquis Warren (an infamous bounty hunter) and Chris Mannix (a man who claims to be Red Rock\'s new sheriff). Lost in a blizzard, the bunch seeks refuge at Minnie\'s Haberdashery. When they arrive they are greeted by unfamiliar faces: Bob, who claims to be taking care of the place while Minnie is gone; Oswaldo Mobray, the hangman of Red Rock; Joe Gage, a cow puncher; and confederate general Sanford Smithers. As the storm overtakes the mountainside, the eight travelers come to learn that they might not make it to Red Rock after all...', 167);

COMMIT;


-- -----------------------------------------------------
-- Data for table `movie_rating`.`genre`
-- -----------------------------------------------------
START TRANSACTION;
USE `movie_rating`;
INSERT INTO `movie_rating`.`genre` (`id`, `name`) VALUES (1, 'Action');
INSERT INTO `movie_rating`.`genre` (`id`, `name`) VALUES (2, 'Adventure');
INSERT INTO `movie_rating`.`genre` (`id`, `name`) VALUES (3, 'Sci-Fi');
INSERT INTO `movie_rating`.`genre` (`id`, `name`) VALUES (4, 'Comedy');
INSERT INTO `movie_rating`.`genre` (`id`, `name`) VALUES (5, 'Drama');
INSERT INTO `movie_rating`.`genre` (`id`, `name`) VALUES (6, 'Romance');
INSERT INTO `movie_rating`.`genre` (`id`, `name`) VALUES (7, 'Crime');
INSERT INTO `movie_rating`.`genre` (`id`, `name`) VALUES (8, 'Fantasy');
INSERT INTO `movie_rating`.`genre` (`id`, `name`) VALUES (9, 'Thriller');
INSERT INTO `movie_rating`.`genre` (`id`, `name`) VALUES (10, 'War');
INSERT INTO `movie_rating`.`genre` (`id`, `name`) VALUES (11, 'Horror');
INSERT INTO `movie_rating`.`genre` (`id`, `name`) VALUES (12, 'Mystery');
INSERT INTO `movie_rating`.`genre` (`id`, `name`) VALUES (13, 'Animation');
INSERT INTO `movie_rating`.`genre` (`id`, `name`) VALUES (14, 'History');
INSERT INTO `movie_rating`.`genre` (`id`, `name`) VALUES (15, 'Biography');
INSERT INTO `movie_rating`.`genre` (`id`, `name`) VALUES (16, 'Sport');
INSERT INTO `movie_rating`.`genre` (`id`, `name`) VALUES (17, 'Western');

COMMIT;


-- -----------------------------------------------------
-- Data for table `movie_rating`.`review`
-- -----------------------------------------------------
START TRANSACTION;
USE `movie_rating`;
INSERT INTO `movie_rating`.`review` (`id`, `user_id`, `movie_id`, `comment`, `date`) VALUES (1, 2, 1, 'Its as much of a non-film as I can remember. Somehow, Spielberg made a cup of water and the sound of footsteps far more suspenseful and engaging than anything in this movie. The dinosaurs are just sort of... there. Same goes for Chris Pratt, who features in scenes but only mildly forwarding the story with a few rescues or whatever. There are some cringe-worthy moments - firing a gun underwater to break glass, everyone being submersed but having perfect hair/makeup after, a horribly acted young girl who has the most shoe-horned and pointless subplot ever (as well as a nonsensical, cringe inducing climactic line). So much of the story makes no sense, a hybrid dino is basically produced and supposedly trained to follow laser sighting overnight. Yet has no buildup, suspense or point whatsoever. The whole thing is stranger than a David Lynch movie. Its like a string of bland cutscenes to a dull videogame.', '2018-06-11');
INSERT INTO `movie_rating`.`review` (`id`, `user_id`, `movie_id`, `comment`, `date`) VALUES (2, 2, 2, 'Your going to be hard pressed to find a movie more over the top than Deadpool 2, David leitch takes it to a whole new level entirely. It works namely because the violence here is just to creative. Sure it defies all Logic most of the time, but i was laughing and having a rip-roaring time throughout.\n\nGo into this movie knowing that it is a campy, corny, over the top superhero/action/comedy that is just about crazy shooting sequences and one-liners, and you\'ll be fine. Do not go into this movie expecting deep plot, meaningful conversations among characters, or anything remotely resembling a serious action or drama movie.\n\nLoved it....', '2018-06-11');
INSERT INTO `movie_rating`.`review` (`id`, `user_id`, `movie_id`, `comment`, `date`) VALUES (3, 3, 1, 'Great CGI, but really lame plot. No idea why even this movie has been made. Come with great expectation and left with great disappointment. Love dinosaurs no matter what.', '2018-06-12');
INSERT INTO `movie_rating`.`review` (`id`, `user_id`, `movie_id`, `comment`, `date`) VALUES (4, 3, 2, 'I wasn\'t expecting it to be THAT good! It takes everything that was great about the first one and makes it even better!\n\nDavid Leitch proves once again to be one of the best current directors in the genre of action, with really gory, fun fights. Also, the whole cast is great, but my favorite was Josh Brolin as Cable, I even liked him more here than in Infinity War.\n\nIf you liked the first one, please, go check this out!', '2018-06-12');
INSERT INTO `movie_rating`.`review` (`id`, `user_id`, `movie_id`, `comment`, `date`) VALUES (5, 3, 3, 'Firstly I would like to state that it is completely hilarious reading reviews with One Star because the movie had \"Foul Language\" and \"Sex Scenes\" or that someone had no idea that the movie was inappropriate for their 9 year old kid. Dead Pool is rated R and with 3 minutes of research you could have determined if this movie was for you or not With that being said I will not delve into the plot but the acting and writing were fantastic. Ryan Reynolds nailed this role. If you are easily offended by violence, language, or nudity this is not the movie for you but if you have a sense of humor and want to be entertained for 2 straight hours you will love this movie.', '2018-06-12');
INSERT INTO `movie_rating`.`review` (`id`, `user_id`, `movie_id`, `comment`, `date`) VALUES (6, 3, 5, 'After watching this movie I was totally filled with enthusiasm. Fight Club is definitly Fincher\'s best movie even better than se7en. It\'s not only the story but the optics which fascinated me. When I had seen it for the second time I could see this movie with the knowledge of the conclusion which is really fascinating as you\'ll see Fight Club in a totally different perspective. Also great about Fight Club is its soundtrack performed by the Dust Brothers and especially the song \'Where is my mind\' by the Pixies which really fit to the end of the movie. Unfortunately Fight Club didn\'t have much success in Germany but anyway the movie got best reviews of the German press. I also have to mention the brilliance of Ed Norton and Brad Pitt who plays best in roles in which he performs the villain. But it\'s quiet amazing what Edward Norton is able to do - he is just overwhelming. For that role he has to get the oscar.', '2018-06-12');
INSERT INTO `movie_rating`.`review` (`id`, `user_id`, `movie_id`, `comment`, `date`) VALUES (7, 3, 6, 'When I first saw this movie I didn\'t appreciate it like I do now. I think it may have been because I was so young when I first saw it. Just recently I saw the movie again. What an amazing story and moving meaning. That movie teaches you so much about life and the meaning of it. That life isn\'t as bad as most people make it seem. That an innocent man can impact so many lives with his innocence. The meaning of the movie to me is that everyone needs to have a better outlook on life. That we need to appreciate more of the little things and not let the big things hold us back. That truly although life may throw us trials and tribulations like a box of chocolates but that we have to just bite into it and get through it even if we don\'t like it. That we all need to hold true to our values and not sink into a place that feels like there\'s no hope... I just love this movie. And anyone who hasn\'t seen it or who thinks that don\'t like it I seriously suggest seeing it or seeing it again. It truly is amazing...', '2018-06-12');
INSERT INTO `movie_rating`.`review` (`id`, `user_id`, `movie_id`, `comment`, `date`) VALUES (8, 3, 7, 'It\'s been a while since a movie has generated enough interest in me for me to watch it. \"The Matrix\" looked exciting enough in the trailers, so I decided to give it a look. What I found was an amazing movie, with some of the greatest special effects I\'ve ever seen. The camera angles really work for the action sequences and the choreographed fight scenes made me yearn for more. Say what you want about Keanu Reeves\' acting. He may not deliver the best dialogue, but his look can carry a film. He was a great choice for the role of Neo. Carrie Anne Moss was great as was the underrated Laurence Fishburne. I highly recommend this film for those who are a fan of visually stunning movies. It will blow away your senses...', '2018-06-12');
INSERT INTO `movie_rating`.`review` (`id`, `user_id`, `movie_id`, `comment`, `date`) VALUES (9, 3, 8, 'Watching The Matrix Reloaded, one is absolutely entitled to say that it is overloaded, too lengthy action sequences for instance, and indeed, a way too lengthy dancing scene in Zion. But next to that, it is obvious that this sequal to The Matrix (1999)takes the story to a whole new dimension. Different characters define the working of the matrix, and the meaning of life itself, in different ways, depending on their onthological background. A conclusion is not (yet) given, which adds to the movie a kind of postmodern quality. For as far as the action sequences are concerned: Groundbreaking. You\'ll see stuff that you\'ve never seen before. Sometimes the scenes are a little lengthy, which harmes the narrative, but that is compensated easily by the visual spectacle. And yes, the Architect at the end is difficult to understand, but when you watch the film more than once, you\'ll find out that it does make sense what he says. All together this movie may not be as fantastic as \'The Matrix\', but it is definitely a good movie that will keep you thinking for a while.', '2018-06-13');
INSERT INTO `movie_rating`.`review` (`id`, `user_id`, `movie_id`, `comment`, `date`) VALUES (10, 3, 9, 'Matrix Revolutions is a revolution of a movie and shows that the brothers wont stop at a half worked job. the attention to detail and cinematography of the real world makes you shocked at how much work was really put into this film. In my opinion no one will be able to match the hugeness of the matrix trilogy for a long time. a job well done and a movie definitely worth seeing, even if you didn\'t like the 2nd one.', '2018-06-13');
INSERT INTO `movie_rating`.`review` (`id`, `user_id`, `movie_id`, `comment`, `date`) VALUES (11, 3, 10, 'I knew nothing of this film before I saw it by chance in a rare Pub open screening, but boy was I glad I got the chance to take a look. I was riveted all night - I completely ignored my friends! I thought it was an awesome re-enactment of a true story - powerful, moving, raw, real - and even funny in parts. I walked away afterwards, beaming. It\'s rare a great film like this is made, especially these days. I gave it ten out of ten. Please see it if you can.', '2018-06-13');
INSERT INTO `movie_rating`.`review` (`id`, `user_id`, `movie_id`, `comment`, `date`) VALUES (12, 3, 11, 'A good friend of mine suggested this film, and I really didnt know what to expect going in, but after this film was over, I sat in stunned silence. I knew that racism was horrible, but I found through this film that it does not come without a terrible price..the loss of love, friends, family...and the realization that everything that you believed to be true isnt always the way things are. Edward Norton\'s performance takes the viewer through this journey, and its not a pretty one. He goes from an bright young man, to a vengeful bigot, to a remorseful excon with a brothers life in jeopardy, and you feel as though you are looking in on someones life in the course of 2 hrs. All of the acting in this film is first rate, and the ending wasnt quite as predictable as others might have you believe...it is quite shocking, as is much of the film. It causes quite a debate on the way people view others whos skin color is not the same. As Dr. King said, judge me not on the color of my skin, but on the content of my character. I live with racism, because I am a black man living in America...it exists, but if we educate ourselves like the main character in AHX, it wont stay around for much longer. Hopefully it wont be too late, as it was for him. Peace and Love, JB33 Rating 5 stars', '2018-06-13');
INSERT INTO `movie_rating`.`review` (`id`, `user_id`, `movie_id`, `comment`, `date`) VALUES (13, 3, 12, 'What fun I had at this movie! The whole experience, from the crowd cheering and excitement as the words \"A long time ago, in a galaxy far, far away\" appeared, to the music, the opening scenes as Qui Gon and Obi Won battled the droids, to the escape of Queen Amidala, to the pod racing, to the unleashing of Darth Maul, to the climatic fight, etc., etc.! This was a great time. I hear some complain that it wasn\'t anything like the first Star Wars, A New Hope. Well, of course not! Had it been, everyone would have complained that all they did was try to recapture the magic of the first movie! I highly recommend this movie to anyone, any age, anywhere!', '2018-06-14');
INSERT INTO `movie_rating`.`review` (`id`, `user_id`, `movie_id`, `comment`, `date`) VALUES (14, 3, 13, 'This episode of the Star wars saga was criticized by some when it came out for having wooden dialogue and too much digital landscaping to be any good. I wasn\'t overly impressed with it myself, but having seen all 6 films now, AOTC is actually a very important and well done section of the overall series.\n\nLucas has said time and again that this movies are meant to be seen as one long film, not to be taken as 6 individual movies. This particular installment features so much that affects every other episode. The discovery of the clones, the immaturity and arrogance of Annakin, the beginning of the clone wars. All of these events happen in this one movie, which is actually a lot more than what happens in some of the other films. I don\'t consider this to be the best of all 6 by any means, but it is certainly not nearly as bad as some people make it out to be.', '2018-06-14');
INSERT INTO `movie_rating`.`review` (`id`, `user_id`, `movie_id`, `comment`, `date`) VALUES (15, 4, 1, 'I am a big fan of foreign film, because its great to see directors with different points of view than the average American director. City of God is the epitome of a great foreign film. It shows the classic struggle between right and wrong, in a brand new way. The raw directing style, coupled with a great soundtrack makes for a movie which many different kinds of people can enjoy. Weather your a Harvard intellectual or the local idiot, you can enjoy this. The vicious cycle of the gangster lifestyle is portrayed with haunting accuracy. It even forces the audience to sympathize with some of the nastiest thugs in the world. All in all, City of God is one the greatest movies of my time and each time i watch it i enjoy it more than the last.', '2018-06-14');
INSERT INTO `movie_rating`.`review` (`id`, `user_id`, `movie_id`, `comment`, `date`) VALUES (16, 4, 11, 'This is a fantastic movie because no matter what side you\'re on in life, this movie shows them all, good and bad, right or wrong. I also agree with the point in the movie that proves that people can change for the better and straighten things out in their head to do the right things in life. Yes, things in our world have changed, some for the good, some for the bad, but just because you might not agree with some of it, doesn\'t mean you can play God and try to make things \"your way\" and this shows exactly what happens when you try. This movie talks about all walks of life and the struggles we all have in what beliefs are right and wrong. Yes this movie is brutal and violent, but also truthful to the past and unfortunately still sometimes the present. This movie is about race, but not only 1 race is singled out and fed to the dogs, they all are. So, go into this movie with an open mind and your mind will come out full.', '2018-06-14');
INSERT INTO `movie_rating`.`review` (`id`, `user_id`, `movie_id`, `comment`, `date`) VALUES (17, 4, 13, 'Easily the most tedious film of the year and of the series and of the genre. You\'d find better action sequences on a cheap games console and better acting in a primary school production of the wizard of oz. And the blame lies squarely on Lucas\' shoulders. This kind of dialogue would get you kicked out of film school. Dribbled with strained references to the original series and delivered with the verve of a George W. Bush speech on international trade agreements. The only people who come out relatively unscathed are the traditionally camped-up hammer-horror-style bad guys and Ewan McGregor\'s continuing pastiche of Alec Guinness. The \"romantic\" leads are like being forced to watch the world\'s least interesting high-school sweethearts pretending to have a squabble. Wait until it appears on DVD then at least you can edit it down to a three minute Natalie Portman fashion shoot and fifty seconds of yoda break-dancing.', '2018-06-14');
INSERT INTO `movie_rating`.`review` (`id`, `user_id`, `movie_id`, `comment`, `date`) VALUES (18, 5, 1, ' can\'t believe how many 10/10 I see on here, either big cgi fans or people who haven\'t seen any other Jurassic Park movie could rate this 10/10!\n\nThe movie is a slow burner, it just never felt as if it could get going, predictable action scenes, terrible story (full of plot holes) and the acting was pretty bad too! It\'s as if they were trying too hard to better JW and failed miserably. As a fan of the franchise I was left bitterly disappointed with the end result of fallen kingdom, it could have been so much better but in the end this movie was poorly executed, after that I don\'t think there should be a third JW unless a different director takes the helm.', '2018-06-15');
INSERT INTO `movie_rating`.`review` (`id`, `user_id`, `movie_id`, `comment`, `date`) VALUES (19, 5, 2, 'Last among all marvel as well as x men movies. even deadpool one was better', '2018-06-15');
INSERT INTO `movie_rating`.`review` (`id`, `user_id`, `movie_id`, `comment`, `date`) VALUES (20, 5, 3, 'I enjoyed every minute in this movie. This will become an instant classic and one of the best movies Marvel has made. From the first scene i was blown away. If you are 12 or older i highly recommend you watch this movie. I can\'t say more without spoiling the movie so go see it. I was at the cinema with my girlfriend and she is not a Deadpool fan, but after she saw the movie she wanted to buy the Deadpool comics to learn more about him. This is how good the movie was. The first 15- 20 minutes of the movie are probably the best intro to a movie i have ever seen. And here is a tip. Stay after the ending credits. You will not regret it.', '2018-06-16');

COMMIT;


-- -----------------------------------------------------
-- Data for table `movie_rating`.`rating`
-- -----------------------------------------------------
START TRANSACTION;
USE `movie_rating`;
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (1, 1, 8);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (1, 2, 9);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (1, 3, 9);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (1, 4, 7);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (1, 5, 10);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (1, 6, 10);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (1, 7, 9);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (1, 8, 9);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (1, 9, 8);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (1, 10, 10);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (2, 1, 7);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (2, 2, 8);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (2, 3, 8);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (2, 4, 5);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (2, 5, 8);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (2, 6, 8);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (2, 7, 7);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (2, 8, 7);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (2, 9, 8);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (2, 10, 9);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (2, 11, 8);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (2, 12, 5);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (2, 13, 8);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (2, 14, 7);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (2, 15, 6);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (2, 16, 8);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (2, 17, 8);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (2, 18, 9);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (2, 19, 7);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (2, 20, 8);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (2, 21, 9);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (2, 22, 10);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (2, 23, 8);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (2, 24, 7);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (2, 25, 6);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (2, 26, 6);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (2, 27, 6);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (2, 28, 5);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (3, 1, 8);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (3, 2, 8);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (3, 3, 9);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (3, 4, 10);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (3, 5, 5);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (3, 6, 8);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (3, 7, 10);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (3, 8, 7);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (3, 9, 6);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (3, 10, 10);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (3, 11, 7);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (3, 12, 7);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (3, 13, 10);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (3, 14, 10);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (3, 15, 9);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (3, 16, 9);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (3, 17, 8);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (3, 18, 7);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (3, 19, 7);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (3, 20, 5);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (3, 21, 4);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (3, 22, 8);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (3, 23, 7);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (3, 24, 9);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (3, 25, 9);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (3, 26, 8);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (3, 27, 10);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (3, 28, 8);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (3, 29, 8);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (3, 30, 7);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (3, 31, 8);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (3, 32, 7);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (3, 33, 7);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (3, 34, 6);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (3, 35, 7);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (3, 36, 9);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (4, 10, 8);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (4, 11, 9);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (4, 12, 7);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (4, 13, 8);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (4, 14, 9);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (4, 15, 10);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (4, 16, 9);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (4, 17, 8);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (4, 18, 7);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (4, 19, 6);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (4, 20, 7);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (4, 21, 9);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (4, 22, 8);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (4, 23, 8);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (4, 24, 7);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (4, 25, 5);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (4 , 26, 7);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (4, 27, 7);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (4, 28, 6);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (4, 29, 5);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (4, 30, 5);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (5, 1, 10);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (5, 2, 9);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (5, 3, 9);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (5, 4, 7);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (5, 5, 8);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (5, 6, 8);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (5, 7, 10);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (5, 8, 8);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (5, 9, 10);
INSERT INTO `movie_rating`.`rating` (`user_id`, `movie_id`, `rating`) VALUES (5, 10, 10);

COMMIT;


-- -----------------------------------------------------
-- Data for table `movie_rating`.`country`
-- -----------------------------------------------------
START TRANSACTION;
USE `movie_rating`;
INSERT INTO `movie_rating`.`country` (`id`, `name`) VALUES (1, 'USA');
INSERT INTO `movie_rating`.`country` (`id`, `name`) VALUES (2, 'Germany');
INSERT INTO `movie_rating`.`country` (`id`, `name`) VALUES (3, 'Australia');
INSERT INTO `movie_rating`.`country` (`id`, `name`) VALUES (4, 'Brazil');
INSERT INTO `movie_rating`.`country` (`id`, `name`) VALUES (5, 'France');
INSERT INTO `movie_rating`.`country` (`id`, `name`) VALUES (6, 'United Kingdom');
INSERT INTO `movie_rating`.`country` (`id`, `name`) VALUES (7, 'Arabian Emirates');
INSERT INTO `movie_rating`.`country` (`id`, `name`) VALUES (8, 'Belgium');
INSERT INTO `movie_rating`.`country` (`id`, `name`) VALUES (9, 'China');
INSERT INTO `movie_rating`.`country` (`id`, `name`) VALUES (10, 'New Zeland');
INSERT INTO `movie_rating`.`country` (`id`, `name`) VALUES (11, 'Italy');
INSERT INTO `movie_rating`.`country` (`id`, `name`) VALUES (12, 'Hong Kong');

COMMIT;


-- -----------------------------------------------------
-- Data for table `movie_rating`.`movie_has_genre`
-- -----------------------------------------------------
START TRANSACTION;
USE `movie_rating`;
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (1, 1);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (1, 2);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (1, 3);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (2, 1);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (2, 2);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (2, 3);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (2, 4);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (3, 1);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (3, 2);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (3, 3);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (3, 4);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (4, 1);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (4, 2);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (4, 3);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (5, 5);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (6, 5);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (6, 6);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (7, 1);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (7, 3);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (8, 1);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (8, 3);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (9, 1);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (9, 3);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (10, 5);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (10, 7);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (11, 5);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (11, 7);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (12, 1);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (12, 2);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (12, 3);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (12, 8);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (13, 1);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (13, 2);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (13, 3);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (13, 8);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (14, 1);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (14, 2);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (14, 3);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (14, 8);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (15, 1);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (15, 2);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (15, 3);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (15, 8);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (16, 1);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (16, 2);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (16, 3);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (16, 8);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (17, 1);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (17, 2);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (17, 3);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (17, 8);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (18, 7);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (18, 4);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (19, 1);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (19, 7);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (19, 9);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (20, 1);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (20, 2);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (20, 3);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (21, 1);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (21, 2);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (21, 3);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (21, 8);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (22, 2);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (22, 5);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (22, 8);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (23, 2);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (23, 5);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (23, 8);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (24, 2);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (24, 5);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (24, 8);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (25, 5);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (25, 7);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (26, 5);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (26, 7);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (26, 9);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (27, 2);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (27, 5);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (27, 9);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (28, 5);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (28, 10);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (29, 1);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (29, 2);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (29, 3);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (30, 8);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (30, 11);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (30, 12);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (31, 1);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (31, 2);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (31, 4);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (31, 8);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (31, 13);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (32, 1);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (32, 2);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (32, 4);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (32, 8);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (32, 13);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (33, 5);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (33, 10);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (33, 14);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (33, 15);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (34, 4);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (34, 5);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (34, 15);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (34, 16);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (35, 5);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (35, 17);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (36, 5);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (36, 7);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (36, 9);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (36, 12);
INSERT INTO `movie_rating`.`movie_has_genre` (`movie_id`, `genre_id`) VALUES (36, 17);

COMMIT;


-- -----------------------------------------------------
-- Data for table `movie_rating`.`movie_has_country`
-- -----------------------------------------------------
START TRANSACTION;
USE `movie_rating`;
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (1, 1);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (2, 1);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (3, 1);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (4, 1);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (5, 1);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (5, 2);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (6, 1);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (7, 1);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (8, 1);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (8, 3);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (9, 1);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (9, 3);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (10, 4);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (10, 5);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (11, 1);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (12, 1);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (13, 1);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (14, 1);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (15, 1);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (16, 1);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (17, 1);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (18, 1);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (18, 6);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (19, 1);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (19, 6);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (19, 5);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (20, 5);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (21, 1);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (21, 2);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (21, 5);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (21, 9);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (21, 8);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (21, 7);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (22, 1);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (22, 10);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (23, 1);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (23, 10);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (24, 1);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (24, 10);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (25, 1);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (25, 11);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (26, 1);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (26, 12);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (27, 1);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (28, 1);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (29, 1);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (30, 1);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (30, 2);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (31, 1);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (32, 1);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (33, 1);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (33, 6);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (34, 1);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (35, 1);
INSERT INTO `movie_rating`.`movie_has_country` (`movie_id`, `country_id`) VALUES (36, 1);

COMMIT;

