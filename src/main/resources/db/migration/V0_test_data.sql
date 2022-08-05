INSERT INTO user (id,pw,name,activated) VALUES
('test','$2a$10$6Gj6siviy6jbwi5ayWHMPuBhHqk/wU1NBAfYYlWatN0tb9asOlipi','test',1),
('admin','$2a$10$kD6dYzXTfMms.AEE3dlseuyVSQ9FPyPOJlmDE.IQkG4BL6VP53.He','admin',1);

INSERT INTO boardProject.authority (name) VALUES
('ROLE_ADMIN'),
('ROLE_USER');

INSERT INTO boardProject.user_authority (user_idx,authority_idx) VALUES
(1,2),
(2,1);

INSERT INTO boardProject.category (name,description,created_at,created_user,updated_at) VALUES
('자유게시판','자유게시판',now(),2,NULL),
('공지사항','공지사항 테스트2',now(),2,NULL);

INSERT INTO boardProject.board (title,content,created_at,created_user,updated_at,category_idx) VALUES
('게시물1','게시물입니다.',now(),1,now(),1),
('게시물2','게시물입니다.',now(),1,now(),1),
('게시물3','게시물입니다.',now(),1,now(),1),
('게시물4','게시물입니다.',now(),1,now(),1),
('게시물5','게시물입니다.',now(),1,now(),1);

INSERT INTO boardProject.board_comment (content,board_idx,created_at,created_user,updated_at) VALUES
('댓글테스트1',2,now(),1,now()),
('댓글테스트2',2,now(),1,now()),
('댓글테스트3',2,now(),1,now()),
('댓글테스트4',2,now(),1,now()),
('댓글테스트5',2,now(),1,now());