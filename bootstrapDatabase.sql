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

CREATE TABLE reactions
(
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  post_id INT,
  username VARCHAR(60),
  type VARCHAR (50));

ALTER TABLE reactions MODIFY post_id INT NOT NULL;
ALTER TABLE reactions MODIFY username VARCHAR(60) NOT NULL;
ALTER TABLE reactions MODIFY type VARCHAR(50) NOT NULL;

ALTER TABLE reactions ADD FOREIGN KEY (post_id) REFERENCES posts(id);
ALTER TABLE reactions ADD FOREIGN KEY (username) REFERENCES users(username);

ALTER TABLE reactions ADD FOREIGN KEY (post_id) REFERENCES posts(id) ON DELETE CASCADE;


INSERT INTO `posts`
(`sender`, `recipient`, `content`)
VALUES
('james', 'sam', 'Hi Sam, do you like my new social network?'),
('james', 'sam', 'Isn\'t it great! What fun!'),
('sam', 'james', 'Hi James, yeah it\'s great'),
('sam', 'james', 'If only you invented it 16 years ago you\'d be a billionaire');


