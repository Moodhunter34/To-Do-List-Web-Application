INSERT INTO user (firstName, lastName, userName, password)
VALUES ('Nikos', 'Pap', 'nikpap', 'nik123');

INSERT INTO todo (title, memo, dateCreated, dateCompleted, important, fk_user_id)
VALUES ('Walk the dog', 'Walk the dog everyday day and night',
			null, null, false, 1);