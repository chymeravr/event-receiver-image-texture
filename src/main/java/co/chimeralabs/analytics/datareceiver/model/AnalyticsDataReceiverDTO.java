package co.chimeralabs.analytics.datareceiver.model;

public class AnalyticsDataReceiverDTO {
	private TYPE type;
	private String dtoObj;
	
	public TYPE getType() {
		return type;
	}

	public void setType(TYPE type) {
		this.type = type;
	}

	public String getDtoObj() {
		return dtoObj;
	}

	public void setDtoObj(String dtoObj) {
		this.dtoObj = dtoObj;
	}

	public enum TYPE {
		AD, CUSTOM
	}
}
