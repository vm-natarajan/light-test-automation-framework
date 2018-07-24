package test.java.support.enums;

public enum DB_QUERY_NAME {
	QUERY_NAME_1 {
		public String toString() {
			return "ORDER_VERIFICATION";
		}
	},
	
	QUERY_NAME_2{
		public String toString() {
			return "ORDER_UPDATE";
		}
	},

}

