package dev.blackyy.hungerpersistence;

public class PlayerModel {
	private final int foodLevel;
	private final float saturation;

	public PlayerModel(int foodLevel, float saturation) {
		this.foodLevel = foodLevel;
		this.saturation = saturation;
	}

	public int getFoodLevel() {
		return foodLevel;
	}

	public float getSaturation() {
		return saturation;
	}
}
