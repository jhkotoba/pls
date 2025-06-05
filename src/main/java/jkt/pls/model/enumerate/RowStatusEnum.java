package jkt.pls.model.enumerate;

public enum RowStatusEnum {
	
	INSERT("INSERT", 16000),
	UPDATE("UPDATE", 16010),
	DELETE("DELETE", 16020),
	SELECT("SELECT", 16030);
	
	private final String text;
	private final Integer code;

	RowStatusEnum(String text, Integer code){
		this.text = text;
		this.code = code;
	}
	
	public String text() {
		return this.text;
	}
	
	public Integer code() {
		return this.code;
	}
}
