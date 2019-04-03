DROP TABLE IF EXISTS `posts`;

CREATE TABLE `posts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sender` varchar(45) NOT NULL,
  `recipient` varchar(45) NOT NULL,
  `content` varchar(1000) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE users
(
  username VARCHAR (60) NOT NULL PRIMARY KEY,
  password VARCHAR (60) NOT NULL,
  fullName VARCHAR (100) NOT NULL
);

ALTER TABLE posts ADD FOREIGN KEY (sender) REFERENCES users(username);
ALTER TABLE posts ADD FOREIGN KEY (recipient) REFERENCES users(username);

INSERT INTO `posts`
(`sender`, `recipient`, `content`)
VALUES
('james', 'sam', 'Hi Sam, do you like my new social network?'),
('james', 'sam', 'Isn\'t it great! What fun!'),
('sam', 'james', 'Hi James, yeah it\'s great'),
('sam', 'james', 'If only you invented it 16 years ago you\'d be a billionaire');
