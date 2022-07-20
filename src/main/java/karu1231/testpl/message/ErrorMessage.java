package karu1231.testpl.message;

public enum ErrorMessage {
	args("引数が不適切です。"),
	noperm("このコマンドを実行する権限がありません。");

	private final String message;

	ErrorMessage(String message) {

		this.message = message;

	}

	public String text() {

		return message;

	}
}
