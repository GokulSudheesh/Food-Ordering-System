public class CustomerDetails {
    String email,name,firstname,lastname,number,location,password,currenttime,date,time24;
    float total;
    int time;
    String userID="";
    public CustomerDetails(String email,String name,String number,String location,float total,int time,String password,String currenttime) {
        this.email = email;
        this.name = name;
        this.number = number;
        this.location = location;
        this.total = total;
        this.time = time;
        this.currenttime = currenttime;
        this.password = password;
        String[] arrOfStr = name.split(" ");
        if (!name.equals("")) {
            this.firstname = arrOfStr[0];
            this.lastname = arrOfStr[1];
            this.userID = this.firstname.charAt(0) + "" + this.lastname.charAt(0) + "" + this.number.substring(this.number.length() - 3, this.number.length());
        }
        if (!(currenttime.equals(""))) {
            this.date = currenttime.split(" ")[0];
            this.time24 = currenttime.split(" ")[1];
        }
    }
}
