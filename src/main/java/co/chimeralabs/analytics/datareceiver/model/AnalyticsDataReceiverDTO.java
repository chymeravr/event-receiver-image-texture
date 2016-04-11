package co.chimeralabs.analytics.datareceiver.model;

public class AnalyticsDataReceiverDTO {
	/*
	 *  APP_START = 1, APP_END=2, AD_DISPLAYED=3, VISIBILITY_METRIC=4, AD_SERVICE=5
	 */
	private Integer type;
	private String dtoObj;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getDtoObj() {
		return dtoObj;
	}

	public void setDtoObj(String dtoObj) {
		this.dtoObj = dtoObj;
	}
}
