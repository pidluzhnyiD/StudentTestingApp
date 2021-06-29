package ua.training.constants;

public class SqlConstants {
	public static final String SELECT_ALL_USERS = "SELECT * FROM users";
	public static final String SELECT_ALL_STUDENTS = "SELECT * FROM users WHERE role = 'Student' OR role = 'Blocked'";
	public static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?;";
	public static final String SELECT_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?;";
	public static final String INSERT_USER_INTO_USERS = "INSERT INTO users "
		+ "(name, login, password, role) VALUES (?, ?, ?, ?);";
	public static final String UPDATE_USER_BY_ID = "UPDATE users SET name = ?, login = ?,"
		+ " password = ?, role = ? WHERE id = ?;";
	public static final String DELETE_USER_BY_ID = "DELETE FROM users WHERE id = ?;";
	public static final String DELETE_USER_BY_LOGIN = "DELETE FROM users WHERE login = ?;";
	
	
	public static final String SELECT_QUESTION_BY_ID_ENGLISH = "SELECT questions_info.*, questions_en.* "
		+ "FROM questions_info INNER JOIN questions_en ON questions_info.id=questions_en.question_id "
		+ "WHERE questions_info.id = ?;";	
	public static final String SELECT_QUESTION_BY_ID_RUSSIAN = "SELECT questions_info.*, questions_ru.* "
		+ "FROM questions_info INNER JOIN questions_ru ON questions_info.id=questions_ru.question_id "
		+ "WHERE questions_info.id = ?;";	
	public static final String SELECT_QUESTION_BY_TEST_ID_ENGLISH = "SELECT questions_info.*, "
		+ "questions_en.* FROM questions_info INNER JOIN questions_en ON "
		+ "questions_info.id=questions_en.question_id WHERE questions_info.test_id = ?;";	
	public static final String SELECT_QUESTION_BY_TEST_ID_RUSSIAN = "SELECT questions_info.*,"
		+ " questions_ru.* FROM questions_info INNER JOIN questions_ru ON "
		+ "questions_info.id=questions_ru.question_id WHERE questions_info.test_id = ?;";	
	
	
	public static final String INSERT_QUESTION_DETAILS = "INSERT INTO questions_info "
			+ "(test_id, answer_1, answer_2, answer_3, answer_4) VALUES (?, ?, ?, ?, ?);";
	public static final String INSERT_ENGLISH_QUESTION = "INSERT INTO questions_en "
			+ "(question_id, description, option_1, option_2, option_3, option_4) "
			+ "VALUES (?, ?, ?, ?, ?, ?);";
	public static final String INSERT_RUSSIAN_QUESTION = "INSERT INTO questions_ru "
			+ "(question_id, description, option_1, option_2, option_3, option_4) "
			+ "VALUES (?, ?, ?, ?, ?, ?);";
	
	
	public static final String UPDATE_QUESTION_DETAILS_BY_ID = "UPDATE questions_info "
			+ "SET test_id = ?, answer_1 = ?, answer_2 = ?, answer_3 = ?, answer_4 = ? "
			+ "WHERE id = ?;";
	public static final String UPDATE_ENGLISH_QUESTION_BY_ID = "UPDATE questions_en "
			+ "SET question_id = ?, description = ?, option_1 = ?, option_2 = ?, "
			+ "option_3 = ?, option_4 = ? WHERE question_id = ?;";
	public static final String UPDATE_RUSSIAN_QUESTION_BY_ID = "UPDATE questions_ru "
			+ "SET question_id = ?, description = ?, option_1 = ?, option_2 = ?, "
			+ "option_3 = ?, option_4 = ? WHERE question_id = ?;";
	public static final String DELETE_QUESTION_BY_ID = "DELETE FROM questions_info WHERE id = ?;";
	
	
	public static final String SELECT_ALL_TESTS = "SELECT * FROM tests";
	public static final String SELECT_ALL_TESTS_BY_SUBJECT = "SELECT * FROM tests ORDER BY subject_id ";
	public static final String SELECT_N_TESTS_BY_SUBJECT = "SELECT * FROM tests WHERE subject_id = ? LIMIT ?, ?";
	public static final String SELECT_N_TESTS_BY_REQUESTS = "SELECT * FROM tests ORDER BY number_of_requests";
	public static final String SELECT_N_TESTS_BY_NAME_EN = "SELECT * FROM tests ORDER BY name_en";
	public static final String SELECT_N_TESTS_BY_NAME_RU = "SELECT * FROM tests ORDER BY name_ru";
	public static final String SELECT_N_TESTS_BY_DIFFICULTY_EN = "SELECT * FROM tests ORDER BY difficulty_en";
	public static final String SELECT_N_TESTS_BY_DIFFICULTY_RU = "SELECT * FROM tests ORDER BY difficulty_ru";
	public static final String SELECT_TEST_BY_ID = "SELECT * FROM tests WHERE id = ?;";
	public static final String SELECT_TESTS_BY_SUBJECT = "SELECT * FROM tests WHERE subject_id = ?;";
	public static final String INSERT_NEW_TEST = "INSERT INTO tests "
		+ "(subject_id, name_en, name_ru, duration, difficulty_en, difficulty_ru, number_of_requests)"
		+ " VALUES (?, ?, ?, ?, ?, ?, ?);";
	public static final String UPDATE_TEST_BY_ID = "UPDATE tests SET subject_id = ?, name_en = ?,"
		+ " name_ru = ?, duration = ?, difficulty_en = ?, difficulty_ru = ?, number_of_requests = ?"
		+ " WHERE id = ?;";
	public static final String DELETE_TEST_BY_ID = "DELETE FROM tests WHERE id = ?;";
	public static final String COUNT_ALL_TESTS = "SELECT COUNT(id) AS 'total' FROM tests";
	public static final String COUNT_TESTS_BY_SUBJECT_ID = "SELECT COUNT(id) AS 'total' FROM tests WHERE subject_id = ?;";
	
	
	
	public static final String SELECT_ALL_TEST_RESULTS = "SELECT test_results.*, tests.name_en, "
		+ "tests.name_ru, tests.difficulty_en, tests.difficulty_ru FROM tests INNER JOIN "
		+ "test_results ON tests.id=test_results.test_id;";	
	public static final String SELECT_TEST_RESULT_BY_ID = "SELECT test_results.*, tests.name_en, "
		+ "tests.name_ru, tests.difficulty_en, tests.difficulty_ru FROM tests INNER JOIN "
		+ "test_results ON tests.id=test_results.test_id WHERE test_results.id = ?;";	
	public static final String SELECT_TEST_RESULT_BY_USER_ID = "SELECT test_results.*, tests.name_en, "
			+ "tests.name_ru, tests.difficulty_en, tests.difficulty_ru FROM tests INNER JOIN "
			+ "test_results ON tests.id=test_results.test_id WHERE test_results.user_id = ? "
			+ "ORDER BY test_results.completion_date DESC;";
	public static final String INSERT_NEW_TEST_RESULT = "INSERT INTO test_results "
		+ "(user_id, test_id, completion_time, completion_date, result) "
		+ "VALUES (?, ?, ?, ?, ?);";
	public static final String UPDATE_TEST_RESULT_BY_ID = "UPDATE test_results SET user_id = ?, "
		+ "test_id = ?, completion_time = ?, completion_date = ?, result = ? WHERE id = ?;";
	public static final String DELETE_TEST_RESULT_BY_ID = "DELETE FROM test_results WHERE id = ?;";
	
	
	
	public static final String SELECT_ALL_SUBJECTS = "SELECT * FROM subjects";
	public static final String SELECT_SUBJECT_BY_ID = "SELECT * FROM subjects WHERE id = ?;";
	public static final String INSERT_NEW_SUBJECT = "INSERT INTO subjects "
		+ "(name_en, name_ru) VALUES (?, ?);";
	public static final String UPDATE_SUBJECT_BY_ID = "UPDATE subjects SET name_en = ?, name_ru = ?"
		+ " WHERE id = ?;";
	public static final String DELETE_SUBJECT_BY_ID = "DELETE FROM subjects WHERE id = ?;";
	
	
}
