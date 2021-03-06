package kr.or.kosta.entity;

public class AccountException extends Exception{
	//String message;
	private int errorCode;
	
	public AccountException() {
		this("계좌처리 중 예기치 않은 에러가 발생했습니다.", -9);
	}

	/**
	 * @param message	출력하고자 하는 메시지
	 * @param errorCode	사전에 정의한 에러코드
	 */
	public AccountException(String message, int errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	/**
	 * @return	errorcode
	 */
	public int getErrorCode() {
		return errorCode;
	}

	@Override
	public String toString() {
		return "AccountException [errorCode=" + errorCode + ", getMessage()=" + getMessage() + "]";
	}
}