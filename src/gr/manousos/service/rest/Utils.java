package gr.manousos.service.rest;

public class Utils {
    public static float toSafeFloat(Float value) {
	return (value == null) ? 0f : value;
    }

    public static int toSafeInteger(Integer value) {
	return (value == null) ? 0 : value;
    }
}
