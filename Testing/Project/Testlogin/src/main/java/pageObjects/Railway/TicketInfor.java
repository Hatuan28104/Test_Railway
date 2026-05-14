package pageObjects.Railway;


public class TicketInfor {
    private String departStation;
    private String arriveStation;
    private String seatType;
    private String departDate;
    private int amount;


    public TicketInfor(String departStation, String arriveStation, String seatType, String departDate, int amount) {
        this.departStation = departStation;
        this.arriveStation = arriveStation;
        this.seatType = seatType;
        this.departDate = departDate;
        this.amount = amount;
    }


    public String getDepartStation() {
        return departStation;
    }


    public String getArriveStation() {
        return arriveStation;
    }


    public String getSeatType() {
        return seatType;
    }


    public String getDepartDate() {
        return departDate;
    }


    public int getAmount() {
        return amount;
    }
}



